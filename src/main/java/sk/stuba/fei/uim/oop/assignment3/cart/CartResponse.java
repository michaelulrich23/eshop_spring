package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {
    private Long id;
    private List<ProductInCartResponse> shoppingList;
    private boolean payed;

    public CartResponse(Cart c){
        this.id = c.getId();
        this.shoppingList = c.getShoppingList().stream().map(ProductInCartResponse::new).collect(Collectors.toList());
        this.payed = c.isPayed();
    }
}
