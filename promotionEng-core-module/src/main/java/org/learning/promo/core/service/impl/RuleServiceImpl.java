package org.learning.promo.core.service.impl;

import org.learning.promo.core.service.RuleService;
import org.learning.promo.data.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RuleServiceImpl implements RuleService {

    @Override
    public List<Rule> getRules() {
        return Collections.emptyList();
    }

    @Override
    public boolean applicable(CartDto cartDto, Rule rule) {
        Map<String, CartDto.CartEntry> groupedBySkuIdMap = cartDto.getCartEntries().stream()
                .collect(Collectors.toMap(CartDto.CartEntry::getSkuId, Function.identity()));
        if(rule.getPromotionType().compareTo(PromotionType.SKU_N_QUANTITY)==0) {
             SKUNQuantity baseCondition = (SKUNQuantity) rule.getBaseCondition();
             String skuId = baseCondition.getSkuId();
             int minQuantityForPromotion = baseCondition.getQuantity();

             if(groupedBySkuIdMap.containsKey(skuId)
                     && groupedBySkuIdMap.get(skuId).getQuantity() >= minQuantityForPromotion) {
                 CartDto.CartEntry cartEntry = groupedBySkuIdMap.get(skuId);
                 int noOfApplicableGroups = cartEntry.getQuantity()/minQuantityForPromotion;
                 int remainingQuantity =  cartEntry.getQuantity()%minQuantityForPromotion;
                 BigDecimal totalPriceAfterDiscount = baseCondition.getDiscount().multiply
                         (BigDecimal.valueOf(noOfApplicableGroups)).add(
                                 BigDecimal.valueOf(remainingQuantity).multiply(cartEntry.getPrice()));
                 cartEntry.setTotalPriceAfterDiscount(totalPriceAfterDiscount);
                 cartEntry.setPromoApplied(true);
                 return true;
             }
        } else if(rule.getPromotionType().compareTo(PromotionType.SKU_COMB)==0) {
            SKUComb skuComb = (SKUComb) rule.getBaseCondition();
            List<String> skuList = skuComb.getSkuList();
            Set<String> skuIds = cartDto.getCartEntries().stream()
                    .map(CartDto.CartEntry::getSkuId)
                    .collect(Collectors.toSet());
            for(String skuId : skuList) {
                if(!skuIds.contains(skuId)) {
                    return false;
                }
            }
            for(String skuId : skuList) {
                CartDto.CartEntry cartEntry = groupedBySkuIdMap.get(skuId);
                //TODO- setTotalPriceAfterDiscount
                cartEntry.setPromoApplied(true);
            }
            return true;
        }
        return false;
    }
}
