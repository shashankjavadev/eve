package com.foodkonnekt.serviceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.ExcelWorkbook;
import com.foodkonnekt.model.ExcelWorksheet;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.repository.CategoryItemRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemModifiersRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.ModifierGroupRepository;
import com.foodkonnekt.repository.ModifierModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.service.ImportExcelService;
import com.foodkonnekt.util.IConstant;

@Service
public class ImportExcelServiceImpl implements ImportExcelService{
	
	public ExcelToJsonConverterConfig config = null;
	
	@Autowired
    private TaxRateRepository taxRateRepository;
	
	 @Autowired
	    private ModifierGroupRepository modifierGroupRepository;

	    @Autowired
	    private ItemmRepository itemmRepository;

	    @Autowired
	    private ItemModifierGroupRepository itemModifierGroupRepository;
	    
	    @Autowired
	    private ModifierModifierGroupRepository modifierModifierGroupRepository;
	    
	    @Autowired
	    private ItemModifiersRepository itemModifiersRepository;

	    @Autowired
	    private ModifiersRepository modifiersRepository;
	    
	    @Autowired
	    private CategoryRepository categoryRepository;
	    
	    @Autowired
	    private CategoryItemRepository categoryItemRepository;
	
	  /*  @Override*/
public  String saveInventoryByExcel(ExcelToJsonConverterConfig config,Merchant merchant) throws InvalidFormatException, IOException {
		
		String taxRateSheetColumns[]={"Name","Tax Rate","Default"};
		int noOfTaxRateSheetColumns=3;
		String modifierGroupsSheetColumns[]={"Modifier Group Name","Modifier","Price"};
		int noOfModifierGroupsSheetColumns=3;
		String itemsSheetColumns[]={"Name","Price","Price Type","Tax Rates","Modifier Groups","Hidden"};
		int noOfItemsSheetColumns=6;
		String categoriesSheetColumns[]={"Category Name","Items in Category"};
		int noOfCategoriesSheetColumns=2;
		
		String response="";
		this.config=config;
		
		ExcelWorkbook book = new ExcelWorkbook();

		InputStream inp = new FileInputStream(config.getSourceFile());
		Workbook wb = WorkbookFactory.create(inp);

		book.setFileName(config.getSourceFile());
		
		int loopLimit =  wb.getNumberOfSheets();
		if(loopLimit==config.getNumberOfSheets()){
		/*if (config.getNumberOfSheets() > 0 && loopLimit > config.getNumberOfSheets()) {
			loopLimit = config.getNumberOfSheets();
		}*/
			int taxRateSheetIndex=wb.getSheetIndex("Tax Rates");
			int modifierGroupsSheetIndex=wb.getSheetIndex("Modifier Groups");
			int itemsSheetIndex=wb.getSheetIndex("Items");
			int categoriesSheetIndex=wb.getSheetIndex("Categories");
			
			if(taxRateSheetIndex==0&&modifierGroupsSheetIndex==1&&itemsSheetIndex==2&&categoriesSheetIndex==3){

		for (int i = 0; i < loopLimit; i++) {
			Sheet sheet = wb.getSheetAt(i);
			
			
			
			if (sheet == null) {
				continue;
			}
			ExcelWorksheet tmp = new ExcelWorksheet();
			tmp.setName(sheet.getSheetName());
			
			if(sheet.getSheetName().equals("Tax Rates")){
				boolean status= validateColumns(sheet,noOfTaxRateSheetColumns,taxRateSheetColumns);
				 if(status){
					 ArrayList<TaxRates> taxRates = 	setTaxRates(sheet,merchant);
						taxRateRepository.save(taxRates);
				 }else{
					 response="Tax Rates sheet: Please make sure you have the following headers in the following order: Name,Tax Rate, Default";
					 break;
				 }
				
			}
			if(sheet.getSheetName().equals("Modifier Groups")){
				boolean status= validateColumns(sheet,noOfModifierGroupsSheetColumns,modifierGroupsSheetColumns);
				 if(status){
					 ArrayList<ModifierGroup> modifierGroups =setModifierGroups(sheet,merchant);
						modifierGroupRepository.save(modifierGroups);
				 }else{
					 response="Modifier Groups sheet: Please make sure you have the following headers in the following order: Modifier Group Name,Modifier,Price";
					 break;
				 }
				
			}
			if(sheet.getSheetName().equals("Items")){
				boolean status= validateColumns(sheet,noOfItemsSheetColumns,itemsSheetColumns);
				 if(status){
					 ArrayList<Item> items =setLineItems(sheet,merchant);
                       itemmRepository.save(items);
						for(Item item:items){
							itemModifierGroupRepository.save(item.getItemModifierGroups());
							List<ItemModifierGroup> itemModifierGroups=item.getItemModifierGroups();
							for(ItemModifierGroup itemModifierGroup:itemModifierGroups){
								
								ModifierGroup modifierGroup=itemModifierGroup.getModifierGroup();
								List<Modifiers> modifiers=modifierModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
								for(Modifiers modifier:modifiers){
									ItemModifiers itemModifiers=new ItemModifiers();
									itemModifiers.setItem(item);
									itemModifiers.setModifierGroup(modifierGroup);
									itemModifiers.setModifiers(modifier);
									itemModifiers.setModifierStatus(IConstant.BOOLEAN_TRUE);
									itemModifiersRepository.save(itemModifiers);
								}
							}
						}
				 }else{
					 response="Items sheet: Please make sure you have the following headers in the following order: Name,Price,Price Type,Tax Rates,Modifier Groups,Hidden";
					 break;
				 }
				
				
			}
			if(sheet.getSheetName().equals("Categories")){
				
				boolean status= validateColumns(sheet,noOfCategoriesSheetColumns,categoriesSheetColumns);
				 if(status){
					 List<Category> categories=setCategories(sheet,merchant);
						for(Category category:categories){
							Set<Item> items=category.getItems();
							category.setItems(null);
							categoryRepository.save(category);
							for(Item item:items){
		                     CategoryItem categoryItem = new CategoryItem();
		                     categoryItem.setCategory(category);
		                     categoryItem.setItem(item);
		                     categoryItemRepository.save(categoryItem);
							}
						}
						response="All the inventory imported successfully.";
				 }else{
					 response="Categories sheet: Please make sure you have the following headers in the following order: Category Name,Items in Category";
					 break;
				 }
				
			}
				
			book.addExcelWorksheet(tmp);
		}
		
			}else{
				response="You must have four sheets in the following order: Tax Rates,Modifier Groups, Items, Categories ";
			}
  			}else{
  				response="You must have four sheets in the following order: Tax Rates,Modifier Groups, Items, Categories ";
		}
		System.out.println(book.toJson(true));
		return response;
		
	}
	
	public boolean validateColumns(Sheet sheet,int noOfColumns,String columnArray[]){
		boolean status=false;
		Row firstRow = sheet.getRow(0);
		System.out.println(firstRow.getLastCellNum());
		if(firstRow!=null && firstRow.getLastCellNum()==noOfColumns){
			for(int k=0;k<firstRow.getLastCellNum();k++){
				
				Cell cell = firstRow.getCell(k);
    			if(cell!=null) {
    				String value = (String)cellToObject(cell);
    				if(value.equals(columnArray[k]))
    				{
    					status=true;
    				}else{
    					status=false;
    					break;
    				}
    			}else{
    				status=false;
    				break;
    			}
			}
		
		}else{
			status=false;
		}
		return status;
	}

	public Object cellToObject(Cell cell) {
		int type = cell.getCellType();
		
		if(type == Cell.CELL_TYPE_STRING) {
			return cleanString(cell.getStringCellValue());
		}
		
		if(type == Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue();
		}
		
		if(type == Cell.CELL_TYPE_NUMERIC) {
			
			if (cell.getCellStyle().getDataFormatString().contains("%")) {
		        return cell.getNumericCellValue() * 100;
		    }
			
			return numeric(cell);
		}
		
		if(type == Cell.CELL_TYPE_FORMULA) {
	        switch(cell.getCachedFormulaResultType()) {
	            case Cell.CELL_TYPE_NUMERIC:
	    			return numeric(cell);
	            case Cell.CELL_TYPE_STRING:
	    			return cleanString(cell.getRichStringCellValue().toString());
	        }
		}
		
		return null;

	}
	
	public String cleanString(String str) {
		return str.replace("\n", "").replace("\r", "");
	}

	public Object numeric(Cell cell) {
		if(HSSFDateUtil.isCellDateFormatted(cell)) {
			if(config.getFormatDate()!=null) {
				return config.getFormatDate().format(cell.getDateCellValue());	
			}
			return cell.getDateCellValue();
		}
		return Double.valueOf(cell.getNumericCellValue());
	}
public ArrayList<ModifierGroup> setModifierGroups(Sheet sheet,Merchant merchant){
		
		ArrayList<ModifierGroup> modifierGroups = new ArrayList<ModifierGroup>();
		
		ModifierGroup duplicatModifierGroup=null;
		 Set<Modifiers> modifiers=null;
    	for(int j=1; j<=sheet.getLastRowNum(); j++) {
    		boolean isDuplicateModifierGroup=true;
    		ModifierGroup modifierGroup=duplicatModifierGroup;
    		
    		
    		
    		Row row = sheet.getRow(j);
    		if(row==null) {
    			continue;
    		}
    		boolean hasValues = false;
    		Modifiers modifier = new Modifiers();
    		modifier.setMerchant(merchant);
    		
    		for(int k=0; k<=row.getLastCellNum(); k++) {
    			Cell cell = row.getCell(k);
    			if(cell!=null) {
    				Object value = cellToObject(cell);
    				
    				hasValues = hasValues || value!=null;
    				switch(k){
    				case 0:if(value!=null){
    					String modifierGroupName=(String)value;
    					List<ModifierGroup> groups=modifierGroupRepository.findByMerchantIdAndName(merchant.getId(), modifierGroupName);
    					if(groups!=null && groups.size()>0){
    						for(ModifierGroup group:groups){
    							modifierGroup=group;
    							break;
    						}
    					}else{
    					modifierGroup=new ModifierGroup();
    					modifierGroup.setMerchant(merchant);
    					modifierGroup.setName((String)value);
    					modifierGroup.setShowByDefault(IConstant.BOOLEAN_TRUE);
    					}
    					duplicatModifierGroup=modifierGroup;
    					modifiers=new HashSet<Modifiers>();
    					isDuplicateModifierGroup=false; 
    					}else{
    				            	  isDuplicateModifierGroup=true;  
    				              }
    				       break;
    				
    				case 1: if(value!=null){
    					String modifierName=(String)value;
    					List<Modifiers> modifierList=modifiersRepository.findByMerchantIdAndName(merchant.getId(), modifierName);
    					if(modifierList!=null && modifierList.size()>0){
    						for(Modifiers modi:modifierList){
    							modifier=modi;
    							break;
    						}
    					}else{
    					modifier.setName((String)value);
    					}}break;
    				case 2: modifier.setPrice((Double)value);  
    				
    				}
    			} else {
                }
    		}
    		modifiersRepository.save(modifier);
    		modifiers.add(modifier);
    		/*if(hasValues||!config.isOmitEmpty()) {
				currentRowOffset++;
    			if (rowLimit > 0 && totalRowsAdded == rowLimit) {
    				break;
				}
				if (startRowOffset > 0 && currentRowOffset < startRowOffset) {
    				continue;
				}
    			//tmp.addRow(rowData);
    			totalRowsAdded++;
    		}*/
    		if(!isDuplicateModifierGroup){
    			modifierGroup.setModifiers(modifiers);
    			modifierGroups.add(modifierGroup);
    		}
    	}
		return modifierGroups;
	}
	
	public ArrayList<Item> setLineItems(Sheet sheet,Merchant merchant){
		
		ArrayList<Item> items = new ArrayList<Item>();
		Item duplicateItem= null;
		Set<TaxRates> taxRates= null;
		List<ItemModifierGroup> itemModifierGroups = null;
    	for(int j=1; j<=sheet.getLastRowNum(); j++) {
    		boolean isDuplicateItem=true;
    		Item item= duplicateItem;
    		
    		
    		
    		
    		Row row = sheet.getRow(j);
    		if(row==null) {
    			continue;
    		}
    		boolean hasValues = false;
    		
    		
    		for(int k=0; k<=row.getLastCellNum(); k++) {
    			Cell cell = row.getCell(k);
    			if(cell!=null) {
    				Object value = cellToObject(cell);
    				
    				hasValues = hasValues || value!=null;
    				switch(k){
    				case 0:if(value!=null){
    					String itemName=(String)value;
    					List<Item> itms=itemmRepository.findByMerchantIdAndName(merchant.getId(), itemName);
    					if(itms!=null && itms.size()>0){
    						for(Item itm:itms){
    							item=itm;
    							break;
    						}
    					}else{
    						 item=new Item();
      					   item.setName((String)value);
      					   item.setMerchant(merchant);
      					 item.setItemStatus(IConstant.BOOLEAN_FALSE);
    					}
    					
    					  
    					   duplicateItem=item;
    					   taxRates= new HashSet<TaxRates>();
    			    	   itemModifierGroups = new ArrayList<ItemModifierGroup>();
    					   isDuplicateItem=false;
    				}
    					   break;
    				case 1:item.setPrice((Double)value); break;
    				case 2:item.setPriceType((String)value); break;
    				case 3: String taxName=(String)value;
    					if(taxName.toUpperCase().equals("DEFAULT")){
    						item.setIsDefaultTaxRates(true);
    					List<TaxRates> detaulTaxList=taxRateRepository.findByMerchantIdAndIsDefault(merchant.getId(), 1);
    					Set<TaxRates> detaulTaxSet = new HashSet<TaxRates>(detaulTaxList);
    					taxRates.addAll(detaulTaxSet);
    					}else{
    						List<TaxRates> detaulTaxList=taxRateRepository.findByMerchantIdAndName(merchant.getId(),taxName);
    						Set<TaxRates> detaulTaxSet = new HashSet<TaxRates>(detaulTaxList);
    						taxRates.addAll(detaulTaxSet);
    					}break;
    					
    				case 4:String modifierGroupName=(String)value; 
    				List<ModifierGroup> modifierGroup= modifierGroupRepository.findByMerchantIdAndName(merchant.getId(),modifierGroupName);
    				   for(ModifierGroup mg:modifierGroup){
    					   if(item!=null && item.getId()!=null){
    					   ItemModifierGroup itemModifierGroup=itemModifierGroupRepository.findByModifierGroupIdAndItemId(mg.getId(), item.getId());
    					   if(itemModifierGroup!=null){
    						   itemModifierGroups.add(itemModifierGroup);break;
    					   }}else{
    					   ItemModifierGroup itemModifierGroup= new ItemModifierGroup();
    					   itemModifierGroup.setItem(item);
    					   itemModifierGroup.setModifierGroup(mg);
    					   itemModifierGroup.setModifierGroupStatus(IConstant.BOOLEAN_TRUE);
    					   itemModifierGroups.add(itemModifierGroup);
    					   break;
    					   }
    				   }break;
    				case 5:item.setIsHidden((Boolean)value); break;
    				
    				
    				
    				}
    			} else {
                }
    		}
    		
    		if(!isDuplicateItem){
    			item.setTaxes(taxRates);
    			
    			item.setItemModifierGroups(itemModifierGroups);
    			
    			items.add(item);
    		}
    		
    	}
		return items;
	}
	
	
public ArrayList<Category> setCategories(Sheet sheet,Merchant merchant){
		
		ArrayList<Category> categories = new ArrayList<Category>();
		Category duplicatCategory=null;
		 Set<Item> items=null;
		 int categoryOrder=1;
   	for(int j=1; j<=sheet.getLastRowNum(); j++) {
   		boolean isDuplicateCategory=true;
   		Category category=duplicatCategory;
   		
   		
   		
   		Row row = sheet.getRow(j);
   		if(row==null) {
   			continue;
   		}
   		boolean hasValues = false;
   		
   		
   		
   		for(int k=0; k<=row.getLastCellNum(); k++) {
   			Cell cell = row.getCell(k);
   			if(cell!=null) {
   				Object value = cellToObject(cell);
   				
   				hasValues = hasValues || value!=null;
   				switch(k){
   				case 0:if(value!=null){
   					String cateogyName=(String)value;
					List<Category> cats=categoryRepository.findByMerchantIdAndName(merchant.getId(), cateogyName);
					if(cats!=null && cats.size()>0){
						for(Category cat:cats){
							category=cat;
							break;
						}
					}else{
						category=new Category();
	   					category.setMerchant(merchant);
	   					category.setName((String)value);
	   					category.setItemStatus(IConstant.BOOLEAN_FALSE);
	   					category.setSortOrder(categoryOrder++);
					}
   					
   					duplicatCategory=category;
   					items=new HashSet<Item>();
   					isDuplicateCategory=false; 
   				              }else{
   				            	isDuplicateCategory=true;  
   				              }
   				       break;
   				
   				case 1:String itemName=(String)value;
   				List<Item> itemList=itemmRepository.findByMerchantIdAndName(merchant.getId(), itemName);
   				for(Item item:itemList){
   					item.setCategories(null);
   				items.add(item);break;
   				}
   				break;
   				
   				}
   			} else {
               }
   		}
   		
   		/*if(hasValues||!config.isOmitEmpty()) {
				currentRowOffset++;
   			if (rowLimit > 0 && totalRowsAdded == rowLimit) {
   				break;
				}
				if (startRowOffset > 0 && currentRowOffset < startRowOffset) {
   				continue;
				}
   			//tmp.addRow(rowData);
   			totalRowsAdded++;
   		}*/
   		if(!isDuplicateCategory){
   			category.setItems(items);
   			categories.add(category);
   		}
   	}
		return categories;
	}
	
public ArrayList<TaxRates> setTaxRates(Sheet sheet,Merchant merchant){
	
	
		
		ArrayList<TaxRates> taxRates = new ArrayList<TaxRates>();
    	for(int j=1; j<=sheet.getLastRowNum(); j++) {
    		TaxRates taxrate= new TaxRates();
    		taxrate.setMerchant(merchant);
    		Row row = sheet.getRow(j);
    		if(row==null) {
    			continue;
    		}
    		boolean hasValues = false;
    		
    		
    		for(int k=0; k<=row.getLastCellNum(); k++) {
    			Cell cell = row.getCell(k);
    			if(cell!=null) {
    				Object value = cellToObject(cell);
    				
    				hasValues = hasValues || value!=null;
    				switch(k){
    				case 0:  String taxName=(String)value;
    				         List<TaxRates> taxes=taxRateRepository.findByMerchantIdAndName(merchant.getId(),taxName);
    				         if(taxes!=null & taxes.size()>0){
    				        	 for(TaxRates rates:taxes){
    				               	 taxrate=rates;
    				        	 break;
    				         }}else{
    				        	 taxrate.setName(taxName);
    				         }
    						 break;
    				case 1:taxrate.setRate((Double)value); break;
    				case 2:String isDefault =(String)value;
    				            if(isDefault.equals("Yes"))
    				            	taxrate.setIsDefault(IConstant.BOOLEAN_TRUE);
    				            else
    				            	taxrate.setIsDefault(IConstant.BOOLEAN_FALSE);
    				            break;
    				
    				}
    			} else {
                }
    		}
    		/*if(hasValues||!config.isOmitEmpty()) {
				currentRowOffset++;
    			if (rowLimit > 0 && totalRowsAdded == rowLimit) {
    				break;
				}
				if (startRowOffset > 0 && currentRowOffset < startRowOffset) {
    				continue;
				}
    			//tmp.addRow(rowData);
    			totalRowsAdded++;
    		}*/
    		taxRates.add(taxrate);
    	}
		return taxRates;
	}

}
