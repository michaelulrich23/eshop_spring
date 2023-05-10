package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductInCartRepository extends JpaRepository<ProductInCart, Long> {
    ProductInCart findProductByProductId(Long id);
}
