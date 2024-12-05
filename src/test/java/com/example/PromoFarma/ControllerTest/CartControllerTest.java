package com.example.PromoFarma.ControllerTest;

import com.example.PromoFarma.Controller.CartController;
import com.example.PromoFarma.Service.CartService;
import com.example.PromoFarma.Model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private Cart cart;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        cart = new Cart();
        cart.setIdUser(1L);
    }

    @Test
    public void testAddToCart() throws Exception {
        when(cartService.addToCart(1L, 1L, 2)).thenReturn(cart);

        mockMvc.perform(post("/cart")
                        .contentType("application/json")
                        .content("{\"idUser\":\"1\", \"idProduct\":\"1\", \"quantity\":\"2\"}"))
                .andExpect(status().isCreated());
    }
}

