package com.example.PromoFarma.Service;

import com.example.PromoFarma.Model.Product;
import com.example.PromoFarma.Model.Seller;
import com.example.PromoFarma.Repository.ProductRepository;
import com.example.PromoFarma.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    // add a product
    public Product addProduct(Long idSeller, String name, BigDecimal price) {
        Seller seller = findSellerById(idSeller);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setSeller(seller);

        return productRepository.save(product);
    }

    // if we had more methods, we can reuse this validation method, improving reusability
    private Seller findSellerById(Long idSeller) {
        return sellerRepository.findById(idSeller)
                .orElseThrow(() -> new RuntimeException("Seller with ID " + idSeller + " not found"));
    }

    // delete a product
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }
}
