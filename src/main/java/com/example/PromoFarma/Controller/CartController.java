package com.example.PromoFarma.Controller;

import com.example.PromoFarma.Model.Cart;
import com.example.PromoFarma.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Map<String,String> request) {
        Cart cart = cartService.addToCart(
                Long.parseLong(request.get("idUser")),
                Long.parseLong(request.get("idProduct")),
                Integer.parseInt(request.get("quantity"))
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PatchMapping("/{idCart}")
    public ResponseEntity<String> updateCart(@PathVariable Long idCart, @RequestBody Map<String,Integer> request) {
        cartService.updateQuantity(idCart, request.get("quantity"));
        return ResponseEntity.ok("Cart updated successfully");
    }

    @GetMapping("/total/{idUser}")
    public ResponseEntity<Map<String, BigDecimal>> getCartTotal(@PathVariable Long idUser) {
        BigDecimal total = cartService.getTotalPrice(idUser);
        return ResponseEntity.ok(Map.of("total", total));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> deleteCart(@PathVariable Long idUser) {
        cartService.clearCart(idUser);
        return ResponseEntity.ok("Cart deleted successfully");
    }

    @DeleteMapping("/{idUser}/{idProduct}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long idUser, @PathVariable Long idProduct) {
        cartService.removeFromCart(idUser, idProduct);
        return ResponseEntity.ok("Product removed from cart successfully");
    }

    @PostMapping("/{idUser}/confirm")
    public ResponseEntity<String> confirmCart(@PathVariable Long idUser) {
        cartService.confirmCart(idUser);
        return ResponseEntity.ok("Cart confirmation successfully");
    }
}

