package com.foodkonnekt.foodtronix.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodkonnekt.foodtronix.service.FoodTronixService;
import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.FoodTronix;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.PizzaSize;
import com.foodkonnekt.model.PizzaTemplate;
import com.foodkonnekt.model.PizzaTemplateSize;
import com.foodkonnekt.model.PizzaTopping;
import com.foodkonnekt.model.PizzaToppingSize;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.ModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.repository.PizzaSizeRepository;
import com.foodkonnekt.repository.PizzaTemplateRepository;
import com.foodkonnekt.repository.PizzaTemplateSizeRepository;
import com.foodkonnekt.repository.PizzaToppingRepository;
import com.foodkonnekt.repository.PizzaToppingSizeRepository;
import com.foodkonnekt.repository.RoleRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.util.FoodTronixJsonUtil;
import com.foodkonnekt.util.FoodtronixUrlUtil;
import com.foodkonnekt.util.IConstant;

@Service
@Transactional
public class FoodTronixServiceImpl implements FoodTronixService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
    private TaxRateRepository taxRateRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModifiersRepository modifiersRepository;

	@Autowired
	private ModifierGroupRepository modifierGroupRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PizzaTemplateRepository pizzaTemplateRepository;

	@Autowired
	private PizzaTemplateSizeRepository pizzaTemplateSizeRepository;

	@Autowired
	private PizzaToppingRepository pizzaToppingRepository;

	@Autowired
	private PizzaToppingSizeRepository pizzaToppingSizeRepository;

	@Autowired
	private ItemmRepository itemRepository;

	@Autowired
	private PizzaSizeRepository pizzaSizeRepository;

	@Autowired
	private ItemModifierGroupRepository itemModifierGroupRepository;

	/**
	 * Save vendor,merchant and address
	 */
	public Merchant saveMerchant(FoodTronix foodTronix) {

		String restaurantDetails = FoodtronixUrlUtil.getRestaurantDetails(foodTronix);

		Merchant newMerchant = FoodTronixJsonUtil.setFoodTronixCompany(restaurantDetails);
		Merchant oldMerchant = merchantRepository.findByStoreId(foodTronix.getStoreId());
		Merchant merchant;
		if (null != oldMerchant) {
			merchant = oldMerchant;
		} else {
			Vendor vendor = vendorRepository.findByEmail(newMerchant.getOwner().getEmail());
			if (null != vendor) {
				newMerchant.setOwner(vendor);
			} else {
				newMerchant.getOwner()
						.setRole(roleRepository.findByRoleName(newMerchant.getOwner().getRole().getRoleName()));
				newMerchant.getOwner().setCompanyId(foodTronix.getCompanyId());
				vendorRepository.save(newMerchant.getOwner());
			}
			newMerchant.setAccessToken(foodTronix.getAuthToken());
			newMerchant.setStoreId(foodTronix.getStoreId());
			merchant = merchantRepository.save(newMerchant);
			Address address = FoodTronixJsonUtil.setAddress(restaurantDetails, merchant);
			addressRepository.save(address);
			
			List<TaxRates> rates = FoodTronixJsonUtil.setTaxRates(restaurantDetails, merchant);
	        if(rates!=null)
	        taxRateRepository.save(rates);
		}
		return merchant;
	}

	public void saveItemProperty(FoodTronix foodTronix, Merchant merchant) {

		// Merchant merchant = saveMerchant(foodTronix);
		String itemPropertyJson = FoodtronixUrlUtil.getItemProperty(foodTronix);
		List<Modifiers> modifierList = setItemProperty(itemPropertyJson, merchant);
		modifiersRepository.save(modifierList);
		// TODO Auto-generated method stub

	}

	public List<Modifiers> setItemProperty(String itemPropertyJson, Merchant merchant) {
		JSONObject Object = new JSONObject(itemPropertyJson);
		Iterator keys = Object.keys();
		List<Modifiers> modifierList = new ArrayList<Modifiers>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();

			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);
			Modifiers modifiers = modifiersRepository.findByPosModifierIdAndMerchantId(
					Integer.toString(currentDynamicValue.getInt("mItemPropertyID")), merchant.getId());
			if (modifiers == null) {
				modifiers = new Modifiers();
			}

			if (itemPropertyJson.contains("mItemPropertyID"))
				modifiers.setPosModifierId(Integer.toString(currentDynamicValue.getInt("mItemPropertyID")));
			if (itemPropertyJson.contains("mDescription"))
				modifiers.setName(currentDynamicValue.getString("mDescription"));
			if (itemPropertyJson.contains("mPrice"))
				modifiers.setPrice(currentDynamicValue.getDouble("mPrice"));

			modifiers.setMerchant(merchant);
			modifierList.add(modifiers);
		}

		return modifierList;
	}

	public void saveItemPropertyGroup(FoodTronix foodTronix, Merchant merchant) {

		// Merchant merchant = saveMerchant(foodTronix);
		String itemPropertyGroupJson = FoodtronixUrlUtil.getItemPropertyGroup(foodTronix);
		JSONObject Object = new JSONObject(itemPropertyGroupJson);
		Iterator keys = Object.keys();
		List<ModifierGroup> modifierGroupList = new ArrayList<ModifierGroup>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();

			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);
			ModifierGroup modifierGroup = modifierGroupRepository.findByPosModifierGroupIdAndMerchantId(
					Integer.toString(currentDynamicValue.getInt("mItemPropertyGroupID")), merchant.getId());

			if (modifierGroup == null) {
				modifierGroup = new ModifierGroup();
			}
			if (currentDynamicValue.toString().contains("mActive")) {
				if (currentDynamicValue.getBoolean("mActive")) {
					modifierGroup.setShowByDefault(IConstant.BOOLEAN_TRUE);
				} else {
					modifierGroup.setShowByDefault(IConstant.BOOLEAN_FALSE);
				}
			}
			if (itemPropertyGroupJson.contains("mItemPropertyGroupID"))
				modifierGroup
						.setPosModifierGroupId(Integer.toString(currentDynamicValue.getInt("mItemPropertyGroupID")));
			if (itemPropertyGroupJson.contains("mDescription"))
				modifierGroup.setName(currentDynamicValue.getString("mDescription"));

			JSONArray jsonArray = currentDynamicValue.getJSONArray("mItemPropertyIDs");
			Set<Modifiers> modifierSet = new HashSet<Modifiers>(0);
			for (int i = 0; i < jsonArray.length(); i++) {
				Integer modifierPosId = jsonArray.getInt(i);

				Modifiers modifiers = modifiersRepository
						.findByPosModifierIdAndMerchantId(Integer.toString(modifierPosId), merchant.getId());
				modifierSet.add(modifiers);

			}

			modifierGroup.setModifiers(modifierSet);
			modifierGroup.setMerchant(merchant);
			modifierGroupRepository.save(modifierGroup);
			modifierGroupList.add(modifierGroup);
		}

		// List<ModifierGroup>
		// modifierGroupList=FoodTronixJsonUtil.setItemPropertyGroup(itemPropertyGroupJson,
		// merchant);
		//modifierGroupRepository.save(modifierGroupList);
		// TODO Auto-generated method stub

	}

	public void saveDishCategory(FoodTronix foodTronix, Merchant merchant) {

		// Merchant merchant = saveMerchant(foodTronix);
		String dishCategoryJson = FoodtronixUrlUtil.getDishCategory(foodTronix);

		JSONObject Object = new JSONObject(dishCategoryJson);
		Iterator keys = Object.keys();
		List<Category> categoryList = new ArrayList<Category>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();
			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);

			Category category = categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(),
					Integer.toString(currentDynamicValue.getInt("mDishCategoryID")));

			// TODO Auto-generated method stub

			if (category == null) {
				category = new Category();
				category.setItemStatus(IConstant.BOOLEAN_FALSE);
			}
			category.setPosCategoryId(Integer.toString(currentDynamicValue.getInt("mDishCategoryID")));
			category.setMerchant(merchant);
			;
			category.setName(currentDynamicValue.getString("mDescription"));
			JSONArray dishJsonArray = currentDynamicValue.getJSONArray("mDishes");
			JSONArray pizzaJsonArray = currentDynamicValue.getJSONArray("mPizzaSizes");

			Set<Item> items = getItemForCategory(dishJsonArray, merchant);

			// Set<PizzaSize> pizzaSizes = getPizzaSizes(pizzaJsonArray,
			// merchant);

			// categoryList.add(category);

			if (!items.isEmpty() || items != null) {
				category.setItems(items);
			}
			/*
			 * if (!pizzaSizes.isEmpty() || pizzaSizes != null) {
			 * category.setPizzaSizes(pizzaSizes); }
			 */

			categoryRepository.save(category);
			// insert data for pizza template

		}
		// categoryRepository.save(categoryList);
		savePizzaTemplate(foodTronix, merchant);
	}

	private Set<Item> getItemForCategory(JSONArray itemJsonArray, Merchant merchant) {
		Set<Item> itemSet = new HashSet<Item>();
		for (int i = 0; i < itemJsonArray.length(); i++) {
			JSONObject itemJson = itemJsonArray.getJSONObject(i);
			String posItemId = Integer.toString(itemJson.getInt("mDishID"));
			Item item = null;
			item = itemRepository.findByPosItemIdAndMerchantId(posItemId, merchant.getId());
			if (item != null) {
				if(item.getItemStatus()==null)
				item.setItemStatus(IConstant.BOOLEAN_FALSE);
				itemSet.add(item);
			} else {
				item = createNewLineItem(itemJson, merchant);
				itemSet.add(item);
			}
		}
		return itemSet;
	}

	private Set<PizzaSize> getPizzaSizes(JSONArray pizzaSizeJsonArray, Merchant merchant) {
		Set<PizzaSize> pizzaSizes = new HashSet<PizzaSize>();
		for (int i = 0; i < pizzaSizeJsonArray.length(); i++) {
			JSONObject pizzaSizeJson = pizzaSizeJsonArray.getJSONObject(i);
			String posPizzaSizeID = Integer.toString(pizzaSizeJson.getInt("pizzaSizeID"));
			PizzaSize pizzaSize = null;
			pizzaSize = pizzaSizeRepository.findByPosPizzaSizeIdAndMerchantId(posPizzaSizeID, merchant.getId());
			if (pizzaSize != null) {
				pizzaSizes.add(pizzaSize);
			} else {
				pizzaSize = createNewPizzaSize(pizzaSizeJson, merchant);
				pizzaSizes.add(pizzaSize);
			}
		}
		return pizzaSizes;
	}

	private Item createNewLineItem(JSONObject itemJson, Merchant merchant) {
		Item item = new Item();

		item.setMerchant(merchant);
		if (itemJson.toString().contains("mDescription"))
			item.setName(itemJson.getString("mDescription"));

		if (itemJson.toString().contains("mDishID"))
			item.setPosItemId(Integer.toString(itemJson.getInt("mDishID")));

		if (itemJson.toString().contains("mPrice"))
			item.setPrice(itemJson.getDouble("mPrice"));
		
		if (itemJson.toString().contains("mActive")){
			if(itemJson.getBoolean("mActive")){
				item.setItemStatus(IConstant.BOOLEAN_FALSE);
			}else{
				item.setItemStatus(IConstant.BOOLEAN_TRUE);
			}
		}
		
		if (itemJson.toString().contains("mTaxable")){
				item.setIsDefaultTaxRates(itemJson.getBoolean("mTaxable"));
				if(itemJson.getBoolean("mTaxable")){
					List<TaxRates> taxRates	=taxRateRepository.findByMerchantIdAndIsDefault(merchant.getId(),1);
					Set<TaxRates> taxRateSet = new HashSet(taxRates);
					item.setTaxes(taxRateSet);
				}
		}else{
			item.setIsDefaultTaxRates(false);
		}
		
			item.setPrice(itemJson.getDouble("mPrice"));

		// mItemPropertyGroupIDs
		
		
		itemRepository.save(item);

		JSONArray jsonArray = itemJson.getJSONArray("mItemPropertyGroupIDs");
		System.out.println("mItemPropertyGroupIDs->"+jsonArray.toString());
		for (int i = 0; i < jsonArray.length(); i++) {
			Integer modifierGroupPosId = jsonArray.getInt(i);
			ItemModifierGroup itemModifierGroup = new ItemModifierGroup();

			ModifierGroup modifierGroup = modifierGroupRepository
					.findByPosModifierGroupIdAndMerchantId(Integer.toString(modifierGroupPosId), merchant.getId());
			itemModifierGroup.setItem(item);
			itemModifierGroup.setModifierGroup(modifierGroup);
			itemModifierGroupRepository.save(itemModifierGroup);
		}

		return item;
	}

	private PizzaSize createNewPizzaSize(JSONObject pizzaSizeJson, Merchant merchant) {

		PizzaSize pizzaSize = new PizzaSize();

		pizzaSize.setMerchant(merchant);
		if (pizzaSizeJson.toString().contains("description"))
			pizzaSize.setDescription(pizzaSizeJson.getString("description"));

		if (pizzaSizeJson.toString().contains("active")) {
			if (pizzaSizeJson.getBoolean("active")) {

				pizzaSize.setActive(IConstant.BOOLEAN_TRUE);

			} else {
				pizzaSize.setActive(IConstant.BOOLEAN_FALSE);
			}
		}
		pizzaSize.setPosPizzaSizeId(Integer.toString(pizzaSizeJson.getInt("pizzaSizeID")));

		JSONArray jsonArray = pizzaSizeJson.getJSONArray("allowedPizzaTemplateIDs");
		Set<PizzaTemplate> pizzaTemplates = new HashSet<PizzaTemplate>();
		for (int i = 0; i < jsonArray.length(); i++) {
			Integer posPizzaTemplateID = jsonArray.getInt(i);
			PizzaTemplate pizzaTemplate = pizzaTemplateRepository
					.findByMerchantIdAndPosPizzaTemplateId(merchant.getId(), posPizzaTemplateID.toString());
			pizzaTemplates.add(pizzaTemplate);
		}
		pizzaSize.setPizzaTemplates(pizzaTemplates);
		// mItemPropertyGroupIDs
		pizzaSizeRepository.save(pizzaSize);

		return pizzaSize;

	}

	public void savePizzaTemplate(FoodTronix foodTronix, Merchant merchant) {
		// Merchant merchant = saveMerchant(foodTronix);
		String pizzaTemplateJson = FoodtronixUrlUtil.getPizzaTemplate(foodTronix);

		JSONObject Object = new JSONObject(pizzaTemplateJson);
		Iterator keys = Object.keys();
		List<PizzaTemplate> pizzaTemplateList = new ArrayList<PizzaTemplate>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();
			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);

			PizzaTemplate pizzaTemplate = pizzaTemplateRepository.findByMerchantIdAndPosPizzaTemplateId(
					merchant.getId(), Integer.toString(currentDynamicValue.getInt("mPizzaTemplateID")));

			// TODO Auto-generated method stub

			if (pizzaTemplate == null) {
				pizzaTemplate = new PizzaTemplate();
			}
			pizzaTemplate.setPosPizzaTemplateId(Integer.toString(currentDynamicValue.getInt("mPizzaTemplateID")));

			if (currentDynamicValue.toString().contains("mActive")) {
				if (currentDynamicValue.getBoolean("mActive")) {

					pizzaTemplate.setActive(IConstant.BOOLEAN_TRUE);

				} else {
					pizzaTemplate.setActive(IConstant.BOOLEAN_FALSE);
				}
			}
			pizzaTemplate.setDescription(currentDynamicValue.getString("mDescription"));

			Category category = categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(),
					Integer.toString(currentDynamicValue.getInt("mDishCategoryID")));

			pizzaTemplate.setCategory(category);
			pizzaTemplate.setMerchant(merchant);

			pizzaTemplateList.add(pizzaTemplate);

		}
		pizzaTemplateRepository.save(pizzaTemplateList);

	}

	public void savePizzaTemplateSize(FoodTronix foodTronix, Merchant merchant) {
		// Merchant merchant =
		// merchantRepository.findByStoreId(foodTronix.getStoreId());//saveMerchant(foodTronix);
		String pizzaTemplateSizeJson = FoodtronixUrlUtil.getPizzaTemplateSize(foodTronix);

		JSONObject Object = new JSONObject(pizzaTemplateSizeJson);
		Iterator keys = Object.keys();
		List<PizzaTemplateSize> pizzaTemplateSizeList = new ArrayList<PizzaTemplateSize>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();
			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);

			PizzaTemplateSize pizzaTemplateSize = null;// categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(),Integer.toString(currentDynamicValue.getInt("mDishCategoryID")));

			// TODO Auto-generated method stub

			if (pizzaTemplateSize == null) {
				pizzaTemplateSize = new PizzaTemplateSize();
			}
			pizzaTemplateSize
					.setPosPizzaTemplateSizeId(Integer.toString(currentDynamicValue.getInt("mPizzaTemplateSizeID")));

			if (currentDynamicValue.toString().contains("mActive")) {
				if (currentDynamicValue.getBoolean("mActive")) {

					pizzaTemplateSize.setActive(IConstant.BOOLEAN_TRUE);

				} else {
					pizzaTemplateSize.setActive(IConstant.BOOLEAN_FALSE);
				}
			}
			pizzaTemplateSize.setDescription(currentDynamicValue.getString("mDescription"));

			PizzaSize pizzaSize = pizzaSizeRepository.findByPosPizzaSizeIdAndMerchantId(
					Integer.toString(currentDynamicValue.getInt("mPizzaSizeID")), merchant.getId());

			PizzaTemplate pizzaTemplate = pizzaTemplateRepository.findByMerchantIdAndPosPizzaTemplateId(
					merchant.getId(), Integer.toString(currentDynamicValue.getInt("mPizzaTemplateID")));

			pizzaTemplateSize.setPizzaSize(pizzaSize);
			pizzaTemplateSize.setPizzaTemplate(pizzaTemplate);
			pizzaTemplateSize.setPrice(currentDynamicValue.getDouble("mPrice"));

			pizzaTemplateSizeList.add(pizzaTemplateSize);

		}
		pizzaTemplateSizeRepository.save(pizzaTemplateSizeList);

	}

	public void savePizzaTopping(FoodTronix foodTronix, Merchant merchant) {

		// Merchant merchant =
		// merchantRepository.findByStoreId(foodTronix.getStoreId());//saveMerchant(foodTronix);
		String pizzaToppingJson = FoodtronixUrlUtil.getPizzaTopping(foodTronix);

		JSONObject Object = new JSONObject(pizzaToppingJson);
		Iterator keys = Object.keys();
		List<PizzaTopping> pizzaToppingList = new ArrayList<PizzaTopping>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();
			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);

			PizzaTopping pizzaTopping = pizzaToppingRepository.findByMerchantIdAndPosPizzaToppingId(merchant.getId(),
					Integer.toString(currentDynamicValue.getInt("mPizzaToppingID")));

			// TODO Auto-generated method stub

			if (pizzaTopping == null) {
				pizzaTopping = new PizzaTopping();
			}
			pizzaTopping.setPosPizzaToppingId(Integer.toString(currentDynamicValue.getInt("mPizzaToppingID")));

			if (currentDynamicValue.toString().contains("mActive")) {
				if (currentDynamicValue.getBoolean("mActive")) {

					pizzaTopping.setActive(IConstant.BOOLEAN_TRUE);

				} else {
					pizzaTopping.setActive(IConstant.BOOLEAN_FALSE);
				}
			}
			pizzaTopping.setDescription(currentDynamicValue.getString("mDescription"));

			pizzaTopping.setMerchant(merchant);

			pizzaToppingList.add(pizzaTopping);

		}
		pizzaToppingRepository.save(pizzaToppingList);
		// TODO Auto-generated method stub

	}

	public void savePizzaToppingSize(FoodTronix foodTronix, Merchant merchant) {
		// TODO Auto-generated method stub

		// Merchant merchant =
		// merchantRepository.findByStoreId(foodTronix.getStoreId());//saveMerchant(foodTronix);
		String pizzaToppingSizeJson = FoodtronixUrlUtil.getPizzaToppingSize(foodTronix);

		JSONObject Object = new JSONObject(pizzaToppingSizeJson);
		Iterator keys = Object.keys();
		List<PizzaToppingSize> pizzaToppingSizeList = new ArrayList<PizzaToppingSize>();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();
			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);

			PizzaToppingSize pizzaToppingSize = null;// categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(),Integer.toString(currentDynamicValue.getInt("mDishCategoryID")));

			// TODO Auto-generated method stub

			if (pizzaToppingSize == null) {
				pizzaToppingSize = new PizzaToppingSize();
			}
			pizzaToppingSize
					.setPosPizzaToppingSizeId(Integer.toString(currentDynamicValue.getInt("mPizzaSizeToppingID")));

			if (currentDynamicValue.toString().contains("mActive")) {
				if (currentDynamicValue.getBoolean("mActive")) {

					pizzaToppingSize.setActive(IConstant.BOOLEAN_TRUE);

				} else {
					pizzaToppingSize.setActive(IConstant.BOOLEAN_FALSE);
				}
			}
			pizzaToppingSize.setPrice(currentDynamicValue.getDouble("mPrice"));

			PizzaSize pizzaSize = pizzaSizeRepository.findByPosPizzaSizeIdAndMerchantId(
					Integer.toString(currentDynamicValue.getInt("mPizzaSizeID")), merchant.getId());

			PizzaTopping pizzaTopping = pizzaToppingRepository.findByMerchantIdAndPosPizzaToppingId(merchant.getId(),
					Integer.toString(currentDynamicValue.getInt("mPizzaToppingID")));

			pizzaToppingSize.setPizzaSize(pizzaSize);
			pizzaToppingSize.setPizzaTopping(pizzaTopping);
			;

			pizzaToppingSizeList.add(pizzaToppingSize);

		}
		pizzaToppingSizeRepository.save(pizzaToppingSizeList);

	}

	public void savePizzaSize(FoodTronix foodTronix, Merchant merchant) {
		// TODO Auto-generated method stub

		// Merchant merchant =
		// merchantRepository.findByStoreId(foodTronix.getStoreId());

		String pizzaSizeJson = FoodtronixUrlUtil.getPizzaSize(foodTronix);

		JSONObject Object = new JSONObject(pizzaSizeJson);
		Iterator keys = Object.keys();
		while (keys.hasNext()) {
			// loop to get the dynamic key
			String currentDynamicKey = (String) keys.next();
			// get the value of the dynamic key
			JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);
			PizzaSize pizzaSize = null;
			pizzaSize = pizzaSizeRepository.findByPosPizzaSizeIdAndMerchantId(
					Integer.toString(currentDynamicValue.getInt("pizzaSizeID")), merchant.getId());
			if (pizzaSize == null) {
				pizzaSize = new PizzaSize();
			} /*
				 * else { pizzaSize = createNewPizzaSize(currentDynamicValue,
				 * merchant); //pizzaSizes.add(pizzaSize); }
				 */

			pizzaSize.setMerchant(merchant);
			if (pizzaSizeJson.toString().contains("description"))
				pizzaSize.setDescription(currentDynamicValue.getString("description"));

			if (pizzaSizeJson.toString().contains("active")) {
				if (currentDynamicValue.getBoolean("active")) {

					pizzaSize.setActive(IConstant.BOOLEAN_TRUE);

				} else {
					pizzaSize.setActive(IConstant.BOOLEAN_FALSE);
				}
			}
			pizzaSize.setPosPizzaSizeId(Integer.toString(currentDynamicValue.getInt("pizzaSizeID")));

			JSONArray jsonArray = currentDynamicValue.getJSONArray("allowedPizzaTemplateIDs");
			Set<PizzaTemplate> pizzaTemplates = new HashSet<PizzaTemplate>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Integer posPizzaTemplateID = jsonArray.getInt(i);
				PizzaTemplate pizzaTemplate = pizzaTemplateRepository
						.findByMerchantIdAndPosPizzaTemplateId(merchant.getId(), posPizzaTemplateID.toString());
				pizzaTemplates.add(pizzaTemplate);
			}
			pizzaSize.setPizzaTemplates(pizzaTemplates);
			// mItemPropertyGroupIDs
			pizzaSizeRepository.save(pizzaSize);

		}

	}

}
