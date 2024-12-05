package com.example.PromoFarma.ServiceTest;

import com.example.PromoFarma.Model.Product;
import com.example.PromoFarma.Model.Seller;
import com.example.PromoFarma.Repository.ProductRepository;
import com.example.PromoFarma.Repository.SellerRepository;
import com.example.PromoFarma.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private ProductService productService;

    private Seller seller;
    private Product product;

    @BeforeEach
    public void setUp() {
        seller = new Seller();
        seller.setId(1L);
        seller.setName("Test Seller");

        product = new Product();
        product.setName("Test Product");
        product.setPrice(new BigDecimal("10.00"));
        product.setSeller(seller);
    }

    @Test
    public void testAddProduct() {
        when(sellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.addProduct(1L, "Test Product", new BigDecimal("10.00"));

        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}