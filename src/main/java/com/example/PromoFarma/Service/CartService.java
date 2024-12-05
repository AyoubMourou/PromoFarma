package com.example.PromoFarma.Service;

import com.example.PromoFarma.Model.Cart;
import com.example.PromoFarma.Model.Product;
import com.example.PromoFarma.Repository.CartRepository;
import com.example.PromoFarma.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // add to cart
    public Cart addToCart(Long idUser, Long idProduct, int quantity) {
        Product product = findProductById(idProduct);

        Cart cart = new Cart();
        cart.setIdUser(idUser);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

        return cartRepository.save(cart);
    }

    // if we had more methods, we can reuse this validation method, improving reusability
    private Product findProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // update quantity in a cart
    public void updateQuantity(Long idCart, int quantity) {
        Cart cart = findCartById(idCart);

        if (quantity == 0) {
            cartRepository.delete(cart);
        } else {
            cart.setQuantity(quantity);
            cart.setPrice(cart.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)));
            cartRepository.save(cart);
        }
    }

    // if we had more methods, we can reuse this validation method, improving reusability
    private Cart findCartById(Long idCart) {
        return cartRepository.findById(idCart)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // remove product from cart
    public void removeFromCart(Long idUser, Long idProduct) {
        cartRepository.deleteByUserIdAndProductId(idUser, idProduct);
    }

    // get total of a cart
    public BigDecimal getTotalPrice(Long idUser) {
        final BigDecimal[] totalPrice = {BigDecimal.ZERO};

        cartRepository.findByUserId(idUser).forEach(cart ->
                totalPrice[0] = totalPrice[0].add(cart.getPrice())
        );

        return totalPrice[0];
    }

    // clear the cart
    public void clearCart(Long idCart) {
        cartRepository.deleteByUserId(idCart);
    }

    // confirming the cart
    public void confirmCart(Long idUser) {
        List<Cart> carts = cartRepository.findByUserId(idUser);
        if(carts.isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        carts.forEach((cart) -> {
            System.out.printf("Order confirmed for idUser: %d, quantity: %d%n",
                    cart.getIdUser(), cart.getQuantity());
        });

        cartRepository.deleteByUserId(idUser);
    }
}
