package org.learning.promo.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.learning.promo.core.service.CartEnrichmentService;
import org.learning.promo.core.service.PromoService;
import org.learning.promo.core.service.RuleService;
import org.learning.promo.data.Cart;
import org.learning.promo.data.CartDto;
import org.learning.promo.data.Rule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class PromoServiceImpl implements PromoService {

    CartEnrichmentService cartEnrichmentService;
    RuleService ruleService;


    public  PromoServiceImpl(CartEnrichmentService cartEnrichmentService,RuleService ruleService) {
         this.cartEnrichmentService = cartEnrichmentService;
         this.ruleService = ruleService;
    }

    @Override
    public void validate(Cart cart) {
       //TODO
    }

    @Override
    public BigDecimal applyPromotions(Cart cart) {
        validate(cart);
        CartDto cartDto = cartEnrichmentService.enrichService(cart);
        List<Rule> ruleList=  ruleService.getRules();
        BigDecimal totalOrderValue = BigDecimal.ZERO;
        for (Rule rule: ruleList) {
            if(ruleService.applicable(cartDto,rule)) {
                applyPromotion(cartDto,rule);
            }
        }
        totalOrderValue= totalOrderValue.add(cartDto.getCartEntries().stream()
                .map(CartDto.CartEntry::getTotalPriceAfterDiscount)
                .reduce(BigDecimal.ZERO,BigDecimal::add));
        return totalOrderValue;
    }

    @Override
    public void applyPromotion(CartDto cartDto, Rule rule) {
        log.debug("applied promotion with ruleId {}, ruleDesc {} on cart {}",rule.getRuleId(),
                rule.getDesc(),cartDto.getCartEntries());
    }
}
