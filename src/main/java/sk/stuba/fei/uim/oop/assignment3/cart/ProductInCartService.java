package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInCartService implements IProductInCartService{

    @Autowired
    private IProductInCartRepository repository;

    @Override
    public ProductInCart save(ProductInCart item) {
        return this.repository.save(item);
    }
}
