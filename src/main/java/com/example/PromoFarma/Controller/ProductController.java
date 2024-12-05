package com.example.PromoFarma.Controller;

import com.example.PromoFarma.Model.Product;
import com.example.PromoFarma.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ResponseEntity<Product> addProduct(@PathVariable Long idSeller, @RequestBody Map<String, Object> request) {
        Product product = productService.addProduct(
                idSeller,
                request.get("name").toString(),
                new BigDecimal(request.get("price").toString())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
