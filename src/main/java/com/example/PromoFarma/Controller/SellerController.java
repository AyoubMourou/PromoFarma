package com.example.PromoFarma.Controller;

import com.example.PromoFarma.Model.Seller;
import com.example.PromoFarma.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<Seller> addSeller(@RequestBody Map<String,String> request) {
        Seller seller = sellerService.addSeller(request.get("name"));
        return ResponseEntity.status(HttpStatus.CREATED).body(seller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.ok("Seller deleted successfully");
    }
}
