package com.foodkonnekt.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foodkonnekt.clover.vo.ItemModifiersVO;
import com.foodkonnekt.clover.vo.ModifierGroupViewJsonVo;
import com.foodkonnekt.clover.vo.ModifierGroupViewVo;
import com.foodkonnekt.clover.vo.ModifierViewVo;
import com.foodkonnekt.clover.vo.ModifirViewJsonVo;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.repository.ItemModifierGroupRepository;
import com.foodkonnekt.repository.ItemModifiersRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.ModifierGroupRepository;
import com.foodkonnekt.repository.ModifierModifierGroupRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.service.ModifierService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class ModifierServiceImpl implements ModifierService {

    @Autowired
    private ModifierGroupRepository modifierGroupRepository;

    @Autowired
    private ItemmRepository itemmRepository;

    @Autowired
    private ItemModifierGroupRepository itemModifierGroupRepository;
    
    @Autowired
    private ItemModifiersRepository   itemModifiersRepository;

    @Autowired
    private ModifiersRepository modifiersRepository;

    @Autowired
    private ModifierModifierGroupRepository modifierModifierGroupRepository;

    /**
     * Save modifier group and modifier
     */
    public void saveModifierAndModifierGroup(String modifierJson, Merchant merchant) {
        JSONObject modifierJObject = new JSONObject(modifierJson);
        JSONArray modifierJSONArray = modifierJObject.getJSONArray("elements");
        for (Object jObj : modifierJSONArray) {

            JSONObject modifierJsonObject = (JSONObject) jObj;

            ModifierGroup modifierGroup = JsonUtil.setModifier(modifierJsonObject, merchant);

            Set<Modifiers> modifiers = JsonUtil.getModifiers(modifierJsonObject, modifierGroup, merchant);
            if (modifiers != null && !modifiers.isEmpty()) {
                modifiersRepository.save(modifiers);
            }
            modifierGroup.setModifiers(modifiers);
            modifierGroupRepository.save(modifierGroup);

            JSONObject itemsJsonObject = modifierJsonObject.getJSONObject("items");
            JSONArray itemJSONArray = itemsJsonObject.getJSONArray("elements");
            if (modifierJSONArray != null) {
                for (Object itemjObj : itemJSONArray) {
                    ItemModifierGroup itemModifierGroup = new ItemModifierGroup();
                    itemModifierGroup.setModifierGroup(modifierGroup);
                    itemModifierGroup.setModifierGroupStatus(IConstant.BOOLEAN_TRUE);
                    JSONObject itemObject = (JSONObject) itemjObj;
                        String itemPosId=null;
                    if (itemObject.toString().contains("id"))
                    	itemPosId=itemObject.getString("id");
                    
                    Item item=null;
                    if (itemPosId != null) {
                    	item = itemmRepository.findByPosItemIdAndMerchantId(itemPosId,merchant.getId());
                        if (item != null) {
                            itemModifierGroup.setItem(item);
                        } else {
                        	 item = JsonUtil.getItem(itemObject);
                            if (item != null) {
                                item.setMerchant(merchant);
                                itemmRepository.save(item);
                                itemModifierGroup.setItem(item);
                            }
                        }
                          if(item!=null){
                        	  for(Modifiers modifier:modifiers){
                        		  ItemModifiers itemModifiers = new ItemModifiers();
                        		  itemModifiers.setItem(item);
                        		  itemModifiers.setModifiers(modifier);
                        		  itemModifiers.setModifierStatus(IConstant.BOOLEAN_TRUE);
                        		  itemModifiers.setModifierGroup(modifierGroup);
                        		  itemModifiersRepository.save(itemModifiers);
                        	  }
                          }
                        
                        itemModifierGroupRepository.save(itemModifierGroup);
                    }
                }
            }
        }
    }

    /**
     * Find modifiers by modifierGroupId
     */
    public List<Modifiers> findModifiersByMerchantId(Integer modifierGroupId) {
        return modifiersRepository.findByModifierGroupId(modifierGroupId);
    }

    /**
     * Find modifierGroups by merchantId
     */
    public List<ModifierGroup> findModifierGroupsByMerchantById(int merchantId) {
        List<ModifierGroup> modifierGroups = modifierGroupRepository.findByMerchantId(merchantId);
        for (ModifierGroup modifierGroup : modifierGroups) {
            String modifierName = "";
            List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
            int i = 0;
            for (Modifiers modifier : modifiers) {
                modifierName = modifierName + "," + modifier.getName();
                i++;
                if (i == 3)
                    break;
            }
            modifierName = modifierName.trim();
            if (!modifierName.isEmpty())
                modifierGroup.setModifierNames((modifierName.substring(1, modifierName.length() - 1)) + "...");
        }
        return modifierGroups;
    }

    /**
     * Find Modifiers by merchantId
     */
    public List<Modifiers> findModifierByMerchantById(int merchantId) {
        List<Modifiers> result = new ArrayList<Modifiers>();
        List<Modifiers> modifiers = modifiersRepository.findByMerchantId(merchantId);
        for (Modifiers modifier : modifiers) {
            Set<ModifierGroup> groups = modifier.getModifierGroup();
            int count = 0;
            for (ModifierGroup modifierGroup : groups) {
                List<ItemModifierGroup> list = itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                if (list != null) {
                    if (!list.isEmpty()) {
                        count = count + list.size();
                    }
                }
            }
            modifier.setItemCount(count);
            modifier.setModifierGroup(null);
            modifier.setId(null);
            modifier.setMerchant(null);
            modifier.setPosModifierId(null);
            result.add(modifier);
        }
        return result;
    }

    public List<Modifiers> getModifiers(int merchantId) {
        List<Modifiers> modifiers = modifiersRepository.findByMerchantId(merchantId);
        for (Modifiers modifier : modifiers) {
            Set<ModifierGroup> groups = modifier.getModifierGroup();
            int count = 0;
            for (ModifierGroup modifierGroup : groups) {
                List<ItemModifierGroup> list = itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                if (list != null) {
                    if (!list.isEmpty()) {
                        count = count + list.size();
                    }
                }
            }
            modifier.setItemCount(count);
        }
        return modifiers;
    }

    /**
     * Find modifierGroup count by merchantId
     */
    public Long modifierGroupCountByMerchantId(Integer merchantId) {
        return modifierGroupRepository.modifierGroupCountByMerchantId(merchantId);
    }

    /**
     * Find modifier count by merchantId
     */
    public Long modifierCountByMerchantId(Integer merchantId) {
        return modifiersRepository.modifierCountByMerchantId(merchantId);
    }

    /**
     * Find modifier count of modifierGroup
     */
    public String findModifierCountOfModifierGroup(Integer modifierGroupId, Integer modiferCount) {
        List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(modifierGroupId);
        if (modifiers != null) {
            if (!modifiers.isEmpty()) {
                int modiferSize = modifiers.size();
                if (modiferCount <= modiferSize) {
                    return "false";
                } else {
                    return "true";
                }
            }
        }
        return "true";
    }

    public String findModifierByMerchantById(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.ASC, "id");
        List<ModifierViewVo> result = new ArrayList<ModifierViewVo>();
        Page<Modifiers> modifiers = modifiersRepository.findByMerchantId(merchantId, pageable);

        for (Modifiers modifier : modifiers.getContent()) {
            
            Set<ModifierGroup> groups = modifier.getModifierGroup();
            int count = 0;
            for (ModifierGroup modifierGroup : groups) {
            	if(modifierGroup.getShowByDefault()!=null && modifierGroup.getShowByDefault()!=IConstant.SOFT_DELETE){
                List<ItemModifierGroup> list = itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                if (list != null) {
                    if (!list.isEmpty()) {
                        count = count + list.size();
                    }
                }
            	}
            }
            if(count>0){
            ModifierViewVo modifierViewVo = new ModifierViewVo();
            modifierViewVo.setId(modifier.getId());
            modifierViewVo.setName(modifier.getName());
            modifierViewVo.setPrice(modifier.getPrice());
            modifierViewVo.setProductUsed(count);
            result.add(modifierViewVo);
            }
        }
        result = getListBasedOnSearchParameter(searchParameter, result);
        ModifirViewJsonVo modifirViewJsonVo = new ModifirViewJsonVo();
        modifirViewJsonVo.setiTotalDisplayRecords((int)modifiers.getTotalElements());
        modifirViewJsonVo.setiTotalRecords((int)modifiers.getTotalElements());
        modifirViewJsonVo.setAaData(result);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(modifirViewJsonVo);
    }

    private List<ModifierViewVo> getListBasedOnSearchParameter(String searchParameter, List<ModifierViewVo> result) {
        if (null != searchParameter && !searchParameter.equals("")) {
            List<ModifierViewVo> personsListForSearch = new ArrayList<ModifierViewVo>();
            searchParameter = searchParameter.toUpperCase();
            for (ModifierViewVo modifierViewVo : result) {
                if (modifierViewVo.getName().toUpperCase().indexOf(searchParameter) != -1
                                || modifierViewVo.getPrice().toString().indexOf(searchParameter) != -1
                                || modifierViewVo.getProductUsed().toString().indexOf(searchParameter) != -1) {
                    personsListForSearch.add(modifierViewVo);
                }
            }
            result = personsListForSearch;
            personsListForSearch = null;
        }
        return result;
    }

    public String findModifierGroupsByMerchantById(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageDisplayLength, Sort.Direction.ASC, "id");
        Page<ModifierGroup> modifierGroups = modifierGroupRepository.findByMerchantIdAndShowByDefaultNot(merchantId, pageable,IConstant.SOFT_DELETE);
        List<ModifierGroupViewVo> result = new ArrayList<ModifierGroupViewVo>();
        for (ModifierGroup modifierGroup : modifierGroups.getContent()) {
        	if(modifierGroup.getShowByDefault()!=null && modifierGroup.getShowByDefault()!=IConstant.SOFT_DELETE){
            ModifierGroupViewVo modifierGroupViewVo = new ModifierGroupViewVo();
            String modifierName = "";
            List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
            int i = 0;
            for (Modifiers modifier : modifiers) {
                modifierName = modifierName + "," + modifier.getName();
                i++;
                if (i == 3)
                    break;
            }
            modifierName = modifierName.trim();
            if (!modifierName.isEmpty()) {
                modifierGroupViewVo.setModifiers((modifierName.substring(1, modifierName.length() - 1)) + "...");
            } else {
                modifierGroupViewVo.setModifiers("");
            }
            modifierGroupViewVo.setId(modifierGroup.getId());
            modifierGroupViewVo.setModifierGroupName(modifierGroup.getName());
            result.add(modifierGroupViewVo);
        }
        }
        result = getGroupListBasedOnSearchParameter(searchParameter, result);
        ModifierGroupViewJsonVo modifirGroupViewJsonVo = new ModifierGroupViewJsonVo();
        modifirGroupViewJsonVo.setiTotalDisplayRecords((int)modifierGroups.getTotalElements());
        modifirGroupViewJsonVo.setiTotalRecords((int)modifierGroups.getTotalElements());
        modifirGroupViewJsonVo.setAaData(result);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(modifirGroupViewJsonVo);
    }

    private List<ModifierGroupViewVo> getGroupListBasedOnSearchParameter(String searchParameter,
                    List<ModifierGroupViewVo> result) {
        if (null != searchParameter && !searchParameter.equals("")) {
            List<ModifierGroupViewVo> personsListForSearch = new ArrayList<ModifierGroupViewVo>();
            searchParameter = searchParameter.toUpperCase();
            for (ModifierGroupViewVo modifierGroupViewVo : result) {
                if (modifierGroupViewVo.getModifierGroupName().toUpperCase().indexOf(searchParameter) != -1
                                || modifierGroupViewVo.getModifiers().toString().indexOf(searchParameter) != -1) {
                    personsListForSearch.add(modifierGroupViewVo);
                }
            }
            result = personsListForSearch;
            personsListForSearch = null;
        }
        return result;
    }

    public String searchModifiersByTxt(Integer merchantId, String searchTxt) {
        List<ModifierViewVo> result = new ArrayList<ModifierViewVo>();
        List<Modifiers> modifiers = modifiersRepository.findByMerchantIdAndModifierName(merchantId, searchTxt);

        for (Modifiers modifier : modifiers) {
            ModifierViewVo modifierViewVo = new ModifierViewVo();
            Set<ModifierGroup> groups = modifier.getModifierGroup();
            int count = 0;
            for (ModifierGroup modifierGroup : groups) {
                List<ItemModifierGroup> list = itemModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
                if (list != null) {
                    if (!list.isEmpty()) {
                        count = count + list.size();
                    }
                }
            }
            modifierViewVo.setId(modifier.getId());
            modifierViewVo.setName(modifier.getName());
            modifierViewVo.setPrice(modifier.getPrice());
            modifierViewVo.setProductUsed(count);
            result.add(modifierViewVo);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(result);
    }

    public String searchModifierGroupByTxt(Integer merchantId, String searchTxt) {
        List<ModifierGroup> modifierGroups = modifierGroupRepository.findByMerchantIdAndModifierGroupName(merchantId, searchTxt);
        List<ModifierGroupViewVo> result = new ArrayList<ModifierGroupViewVo>();
        for (ModifierGroup modifierGroup : modifierGroups) {
        	if(modifierGroup.getShowByDefault()!=null && modifierGroup.getShowByDefault()!=IConstant.SOFT_DELETE){
            ModifierGroupViewVo modifierGroupViewVo = new ModifierGroupViewVo();
            String modifierName = "";
            List<Modifiers> modifiers = modifierModifierGroupRepository.findByModifierGroupId(modifierGroup.getId());
            int i = 0;
            for (Modifiers modifier : modifiers) {
                modifierName = modifierName + "," + modifier.getName();
                i++;
                if (i == 3)
                    break;
            }
            modifierName = modifierName.trim();
            if (!modifierName.isEmpty()) {
                modifierGroupViewVo.setModifiers((modifierName.substring(1, modifierName.length() - 1)) + "...");
            } else {
                modifierGroupViewVo.setModifiers("");
            }
            modifierGroupViewVo.setId(modifierGroup.getId());
            modifierGroupViewVo.setModifierGroupName(modifierGroup.getName());
            result.add(modifierGroupViewVo);
        	}
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(result);
    }

	public List<ItemModifiersVO> findModifiersbyModifierGroup(Integer modifierGroupId,Integer itemId) {
		// TODO Auto-generated method stub
		ItemModifierGroup itemModifierGroup=itemModifierGroupRepository.findOne(modifierGroupId);
		List<ItemModifiers> itemModifiers=itemModifiersRepository.findByModifierGroupIdAndItemId(itemModifierGroup.getModifierGroup().getId(),itemId);
		List<ItemModifiersVO> itemModifiersVOs= null;
		if(itemModifiers!=null && itemModifiers.size()>0){
			itemModifiersVOs= new ArrayList<ItemModifiersVO>();
			for(ItemModifiers itemModifier:itemModifiers){
				ItemModifiersVO itemModifiersVO = new ItemModifiersVO();
				itemModifiersVO.setId(itemModifier.getId());
				itemModifiersVO.setModifierId(itemModifier.getModifiers().getId());
				itemModifiersVO.setModifierNmae(itemModifier.getModifiers().getName());
				itemModifiersVO.setModifierGroupName(itemModifier.getModifierGroup().getName());
				itemModifiersVO.setModifierStatus(itemModifier.getModifierStatus());
				itemModifiersVOs.add(itemModifiersVO);
			}
			
		}
		
		
		return itemModifiersVOs;
	}
}