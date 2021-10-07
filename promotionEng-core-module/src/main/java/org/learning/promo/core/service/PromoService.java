package org.learning.promo.core.service;

import org.learning.promo.data.Cart;
import org.learning.promo.data.CartDto;
import org.learning.promo.data.Rule;

import java.math.BigDecimal;

public interface PromoService {
    void validate(Cart cart);

    BigDecimal applyPromotions(Cart cart);

    BigDecimal applyPromotion(CartDto cart, Rule rule);

}
