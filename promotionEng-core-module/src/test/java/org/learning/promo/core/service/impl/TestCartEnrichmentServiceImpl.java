package org.learning.promo.core.service.impl;

import org.junit.jupiter.api.Test;
import org.learning.promo.core.service.PromoService;
import org.learning.promo.data.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestCartEnrichmentServiceImpl {


    @Autowired
    PromoService promoService;


    @Test
    public void testScenarioA() {
        Cart cart = new Cart();
        Cart.CartItem cartItemA = new Cart.CartItem();
        cartItemA.setSkuId("A");
        cartItemA.setQuantity(1);
        Cart.CartItem cartItemB = new Cart.CartItem();
        cartItemB.setSkuId("B");
        cartItemB.setQuantity(1);
        Cart.CartItem cartItemC = new Cart.CartItem();
        cartItemC.setSkuId("C");
        cartItemC.setQuantity(1);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItemA);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cart.setCartItems(cartItems);
        BigDecimal cartTotal = promoService.applyPromotions(cart);
        assertEquals(100,cartTotal.intValue());
    }

    @Test
    public void testScenarioB() {
        Cart cart = new Cart();
        Cart.CartItem cartItemA = new Cart.CartItem();
        cartItemA.setSkuId("A");
        cartItemA.setQuantity(5);
        Cart.CartItem cartItemB = new Cart.CartItem();
        cartItemB.setSkuId("B");
        cartItemB.setQuantity(5);
        Cart.CartItem cartItemC = new Cart.CartItem();
        cartItemC.setSkuId("C");
        cartItemC.setQuantity(1);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItemA);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cart.setCartItems(cartItems);
        BigDecimal cartTotal = promoService.applyPromotions(cart);
        assertEquals(370,cartTotal.intValue());
    }

    @Test
    public void testScenarioC() {
        Cart cart = new Cart();
        Cart.CartItem cartItemA = new Cart.CartItem();
        cartItemA.setSkuId("A");
        cartItemA.setQuantity(3);
        Cart.CartItem cartItemB = new Cart.CartItem();
        cartItemB.setSkuId("B");
        cartItemB.setQuantity(5);
        Cart.CartItem cartItemC = new Cart.CartItem();
        cartItemC.setSkuId("C");
        cartItemC.setQuantity(1);
        Cart.CartItem cartItemD = new Cart.CartItem();
        cartItemD.setSkuId("D");
        cartItemD.setQuantity(1);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItemA);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cartItems.add(cartItemD);
        cart.setCartItems(cartItems);
        BigDecimal cartTotal = promoService.applyPromotions(cart);
        assertEquals(280,cartTotal.intValue());
    }
}
