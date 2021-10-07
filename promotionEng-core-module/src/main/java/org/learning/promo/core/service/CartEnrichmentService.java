package org.learning.promo.core.service;

import org.learning.promo.data.Cart;
import org.learning.promo.data.CartDto;

public interface CartEnrichmentService {
    CartDto enrichService(Cart cart);
}
