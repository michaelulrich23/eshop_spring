package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(orphanRemoval = true)
    private List<ProductInCart> shoppingList;
    private boolean payed;

    public Cart(){
        this.shoppingList = new ArrayList<>();
        this.payed = false;
    }

    public boolean hasProductWithProductId(Long productId) {
        return shoppingList.stream().anyMatch(item -> item.getProductId().equals(productId));
    }

    public ProductInCart getProductWithProductId(Long productId) {
        return shoppingList.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

}
