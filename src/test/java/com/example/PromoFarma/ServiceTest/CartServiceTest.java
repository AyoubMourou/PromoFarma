package com.example.PromoFarma.ServiceTest;

import com.example.PromoFarma.Model.Cart;
import com.example.PromoFarma.Model.Product;
import com.example.PromoFarma.Repository.CartRepository;
import com.example.PromoFarma.Repository.ProductRepository;
import com.example.PromoFarma.Service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private Product product;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(new BigDecimal("10.00"));

        cart = new Cart();
        cart.setIdUser(1L);
        cart.setProduct(product);
        cart.setQuantity(2);
        cart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(2)));
    }

    @Test
    public void testAddToCart() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart addedCart = cartService.addToCart(1L, 1L, 2);

        assertNotNull(addedCart);
        assertEquals(2, addedCart.getQuantity());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testUpdateQuantity() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        cartService.updateQuantity(1L, 3);

        assertEquals(3, cart.getQuantity());
        assertEquals(new BigDecimal("30.00"), cart.getPrice());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testRemoveFromCart() {
        cartService.removeFromCart(1L, 1L);
        verify(cartRepository, times(1)).deleteByUserIdAndProductId(1L, 1L);
    }
}
