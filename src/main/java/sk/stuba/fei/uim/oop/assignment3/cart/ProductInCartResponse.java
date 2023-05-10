package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;

@Getter
public class ProductInCartResponse {
    private Long productId;
    private int amount;

    public ProductInCartResponse(ProductInCart item){
        this.productId = item.getProductId();
        this.amount = item.getAmount();
    }
}
