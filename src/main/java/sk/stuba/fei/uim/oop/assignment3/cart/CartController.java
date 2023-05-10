package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart(){
        return new ResponseEntity<>(new CartResponse(this.cartService.createCart()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CartResponse getShoppingCart(@PathVariable("id") Long cartId) throws NotFoundException {
        return new CartResponse(this.cartService.getById(cartId));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Long cartId) throws NotFoundException {
        this.cartService.delete(cartId);
    }

    @PostMapping("/{id}/add")
    public CartResponse addToCart(@PathVariable("id") Long cartId, @RequestBody ProductAddRequest body) throws NotFoundException, IllegalOperationException {
        return new CartResponse(this.cartService.addToCart(cartId, body));
    }

    @GetMapping("/{id}/pay")
    public String payCart(@PathVariable("id") Long cartId) throws NotFoundException, IllegalOperationException {
        return "" + this.cartService.priceOfCart(cartId);
    }

}
