package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.clover.vo.ItemModifiersVO;
import com.foodkonnekt.model.ItemModifiers;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;

public interface ModifierService {

    /**
     * Save modifier and modifier group
     * 
     * @param modifierJson
     * @param merchant
     */
    public void saveModifierAndModifierGroup(String modifierJson, Merchant merchant);

    /**
     * Find modifiers by merchantId
     * 
     * @param merchantId
     * @return List<Modifiers>
     */
    public List<Modifiers> findModifiersByMerchantId(Integer modifierGroupId);

    /**
     * Find modifierGroups by merchantId
     * 
     * @param merchantId
     * @return List<ModifierGroup>
     */
    public List<ModifierGroup> findModifierGroupsByMerchantById(int merchantId);

    /**
     * Find Modifers by merchantId
     * 
     * @param merchantId
     * @return List<Modifiers>
     */
    public List<Modifiers> findModifierByMerchantById(int merchantId);

    /**
     * Find modifierGroup count by merchantId
     * 
     * @param merchantId
     * @return Long
     */
    public Long modifierGroupCountByMerchantId(Integer merchantId);

    /**
     * Find modifier count by merchantId
     * 
     * @param merchantId
     * @return Long
     */
    public Long modifierCountByMerchantId(Integer merchantId);

    /**
     * Find modifier count of modifierGroup
     * 
     * @param modifierGroupId
     * @param modiferCount
     * @return String
     */
    public String findModifierCountOfModifierGroup(Integer modifierGroupId, Integer modiferCount);

    public String findModifierByMerchantById(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter);

    public String findModifierGroupsByMerchantById(Integer merchantId, Integer pageDisplayLength, Integer pageNumber,
                    String searchParameter);

    public String searchModifiersByTxt(Integer merchantId, String searchTxt);

    public String searchModifierGroupByTxt(Integer merchantId, String searchTxt);
    
    public List<ItemModifiersVO> findModifiersbyModifierGroup(Integer modifierGroupId,Integer itemId);
}
