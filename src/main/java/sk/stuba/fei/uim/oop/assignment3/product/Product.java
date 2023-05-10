package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    public Product(ProductRequest r){
        this.name = r.getName();
        this.description = r.getDescription();
        this.amount = r.getAmount();
        this.unit = r.getUnit();
        this.price = r.getPrice();
    }
}
