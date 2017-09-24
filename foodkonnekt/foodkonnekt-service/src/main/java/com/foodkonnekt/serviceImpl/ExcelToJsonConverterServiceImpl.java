package com.foodkonnekt.serviceImpl;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.foodkonnekt.model.ExcelWorkbook;
import com.foodkonnekt.model.ExcelWorksheet;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Items;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.service.ExcelToJsonConverterService;
import com.foodkonnekt.util.IConstant;


public class ExcelToJsonConverterServiceImpl implements ExcelToJsonConverterService {
	
	public ExcelToJsonConverterConfig config = null;
	
	public ExcelToJsonConverterServiceImpl(ExcelToJsonConverterConfig config) {
		this.config = config;
	}
	
	/*public static ExcelWorkbook convert(ExcelToJsonConverterConfig config) throws InvalidFormatException, IOException {
		return new ExcelToJsonConverterServiceImpl(config).convert();
	}*/
	
	public  ExcelWorkbook saveInventoryByExcel(ExcelToJsonConverterConfig config,Merchant merchant) throws InvalidFormatException, IOException {
		
		ExcelWorkbook book = new ExcelWorkbook();

		InputStream inp = new FileInputStream(config.getSourceFile());
		Workbook wb = WorkbookFactory.create(inp);

		book.setFileName(config.getSourceFile());
		int loopLimit =  wb.getNumberOfSheets();
		if (config.getNumberOfSheets() > 0 && loopLimit > config.getNumberOfSheets()) {
			loopLimit = config.getNumberOfSheets();
		}
		int rowLimit 			= config.getRowLimit();
		int startRowOffset 		= config.getRowOffset();
		int currentRowOffset 	= -1;
		int totalRowsAdded 		= 0;


		for (int i = 0; i < loopLimit; i++) {
			Sheet sheet = wb.getSheetAt(i);
			
			if (sheet == null) {
				continue;
			}
			ExcelWorksheet tmp = new ExcelWorksheet();
			tmp.setName(sheet.getSheetName());
			
			if(sheet.getSheetName().equals("Tax Rates")){
				setTaxRates(sheet,merchant);
			}
			if(sheet.getSheetName().equals("Modifier Groups")){
				setModifierGroups(sheet,merchant);
			}
			if(sheet.getSheetName().equals("Items")){
				setLineItems(sheet,merchant);
			}
			if(sheet.getSheetName().equals("Categories")){
				setCategories(sheet,merchant);
			}
			
			
			
			
			ArrayList<Items> items = new ArrayList<Items>();
        	for(int j=sheet.getFirstRowNum(); j<=sheet.getLastRowNum(); j++) {
        		Items item= new Items();
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
	    				case 0:item.setName(value);
	    				case 1:item.setPrice(value);
	    				case 2:item.setCategories(value);
	    				case 3:item.setModifierGroups(value);
	    			    
	    				
	    				}
	    			} else {
                    }
	    		}
	    		if(hasValues||!config.isOmitEmpty()) {
					currentRowOffset++;
	    			if (rowLimit > 0 && totalRowsAdded == rowLimit) {
	    				break;
					}
					if (startRowOffset > 0 && currentRowOffset < startRowOffset) {
	    				continue;
					}
	    			//tmp.addRow(rowData);
	    			totalRowsAdded++;
	    		}
	    		items.add(item);
	    	}
        	tmp.setItems(items);
        	/*if(config.isFillColumns()) {
        		tmp.fillColumns();
        	}*/
			book.addExcelWorksheet(tmp);
		}

		return null;
		
	}
	
	public ExcelWorkbook convert()
			throws InvalidFormatException, IOException {
		
		Merchant merchant=null;
		ExcelWorkbook book = new ExcelWorkbook();

		InputStream inp = new FileInputStream(config.getSourceFile());
		Workbook wb = WorkbookFactory.create(inp);

		book.setFileName(config.getSourceFile());
		int loopLimit =  wb.getNumberOfSheets();
		if (config.getNumberOfSheets() > 0 && loopLimit > config.getNumberOfSheets()) {
			loopLimit = config.getNumberOfSheets();
		}
		int rowLimit 			= config.getRowLimit();
		int startRowOffset 		= config.getRowOffset();
		int currentRowOffset 	= -1;
		int totalRowsAdded 		= 0;


		for (int i = 0; i < loopLimit; i++) {
			Sheet sheet = wb.getSheetAt(i);
			
			if (sheet == null) {
				continue;
			}
			ExcelWorksheet tmp = new ExcelWorksheet();
			tmp.setName(sheet.getSheetName());
			
			if(sheet.getSheetName().equals("Tax Rates")){
				setTaxRates(sheet,merchant);
			}
			if(sheet.getSheetName().equals("Modifier Groups")){
				setModifierGroups(sheet,merchant);
			}
			if(sheet.getSheetName().equals("Items")){
				setLineItems(sheet,merchant);
			}
			if(sheet.getSheetName().equals("Categories")){
				setCategories(sheet,merchant);
			}
			
			
			
			
			ArrayList<Items> items = new ArrayList<Items>();
        	for(int j=sheet.getFirstRowNum(); j<=sheet.getLastRowNum(); j++) {
        		Items item= new Items();
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
	    				case 0:item.setName(value);
	    				case 1:item.setPrice(value);
	    				case 2:item.setCategories(value);
	    				case 3:item.setModifierGroups(value);
	    			    
	    				
	    				}
	    			} else {
                    }
	    		}
	    		if(hasValues||!config.isOmitEmpty()) {
					currentRowOffset++;
	    			if (rowLimit > 0 && totalRowsAdded == rowLimit) {
	    				break;
					}
					if (startRowOffset > 0 && currentRowOffset < startRowOffset) {
	    				continue;
					}
	    			//tmp.addRow(rowData);
	    			totalRowsAdded++;
	    		}
	    		items.add(item);
	    	}
        	tmp.setItems(items);
        	/*if(config.isFillColumns()) {
        		tmp.fillColumns();
        	}*/
			book.addExcelWorksheet(tmp);
		}

		return book;
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
    		
    		for(int k=0; k<=row.getLastCellNum(); k++) {
    			Cell cell = row.getCell(k);
    			if(cell!=null) {
    				Object value = cellToObject(cell);
    				
    				hasValues = hasValues || value!=null;
    				switch(k){
    				case 0:if(value!=null){
    					modifierGroup=new ModifierGroup();
    					modifierGroup.setMerchant(merchant);
    					modifierGroup.setName((String)value);
    					duplicatModifierGroup=modifierGroup;
    					modifiers=new HashSet<Modifiers>();
    					isDuplicateModifierGroup=false; 
    				              }else{
    				            	  isDuplicateModifierGroup=true;  
    				              }
    				       break;
    				
    				case 1:modifier.setName((String)value);break;
    				case 2: modifier.setPrice((Double)value);  
    				
    				}
    			} else {
                }
    		}
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
    	for(int j=1; j<=sheet.getLastRowNum(); j++) {
    		Item item= new Item();
    		item.setMerchant(merchant);
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
    				case 0:item.setName((String)value); break;
    				/*case 1:item.setRate((Double)value); break;
    				case 2:   String isDefault =(String)value;
    				            if(isDefault.equals("Yes"))
    				            	taxrate.setIsDefault(IConstant.BOOLEAN_TRUE);
    				            else
    				            	taxrate.setIsDefault(IConstant.BOOLEAN_FALSE);
    				            break;*/
    				
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
    		items.add(item);
    	}
		return items;
	}
	
	
public ArrayList<Item> setCategories(Sheet sheet,Merchant merchant){
		
		ArrayList<Item> items = new ArrayList<Item>();
    	for(int j=1; j<=sheet.getLastRowNum(); j++) {
    		Item item= new Item();
    		item.setMerchant(merchant);
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
    				case 0:item.setName((String)value); break;
    				/*case 1:item.setRate((Double)value); break;
    				case 2:   String isDefault =(String)value;
    				            if(isDefault.equals("Yes"))
    				            	taxrate.setIsDefault(IConstant.BOOLEAN_TRUE);
    				            else
    				            	taxrate.setIsDefault(IConstant.BOOLEAN_FALSE);
    				            break;*/
    				
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
    		items.add(item);
    	}
		return items;
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
    				case 0:taxrate.setName((String)value); break;
    				case 1:taxrate.setRate((Double)value); break;
    				case 2:   String isDefault =(String)value;
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
