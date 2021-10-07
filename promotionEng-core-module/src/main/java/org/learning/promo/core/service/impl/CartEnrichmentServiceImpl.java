package org.learning.promo.core.service.impl;

import org.learning.promo.dao.entity.SKU;
import org.learning.promo.dao.repository.SKURepository;
import org.learning.promo.core.service.CartEnrichmentService;
import org.learning.promo.data.Cart;
import org.learning.promo.data.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class CartEnrichmentServiceImpl implements CartEnrichmentService {

    private final SKURepository skuRepository;

    @Autowired
    public CartEnrichmentServiceImpl(SKURepository skuRepository) {
        this.skuRepository = skuRepository;
    }


    @Override
    public CartDto enrichService(Cart cart) {
        List<String> skuIdList =  cart.getCartItems().stream()
                .map(Cart.CartItem::getSkuId).collect(Collectors.toList());
        Set<SKU> skuEntities = skuRepository.findBySkuIdIn(skuIdList);

        Map<String, SKU> groupedBySkuIdMap = skuEntities.stream()
                .collect(Collectors.toMap(SKU::getSkuId, Function.identity()));
        CartDto cartDto = new CartDto();
        List<CartDto.CartEntry> cartEntries = cartDto.getCartEntries();
        for(Cart.CartItem cartItem : cart.getCartItems()) {
            SKU sku = groupedBySkuIdMap.get(cartItem.getSkuId());
            CartDto.CartEntry cartEntry = getCartEntry(sku,cartItem.getQuantity());
            cartEntries.add(cartEntry);
        }
        cartDto.setCartEntries(cartEntries);
        return cartDto;

    }

    private CartDto.CartEntry getCartEntry(SKU sku,int quantity) {
        CartDto.CartEntry cartEntry = new CartDto.CartEntry();
        cartEntry.setSkuId(sku.getSkuId());
        cartEntry.setPrice(sku.getPrice());
        cartEntry.setPromoApplied(false);
        cartEntry.setQuantity(quantity);
        cartEntry.setTotalPriceAfterDiscount(cartEntry.getPrice().multiply(BigDecimal.valueOf(cartEntry.getQuantity())));
        return cartEntry;

    }
}
