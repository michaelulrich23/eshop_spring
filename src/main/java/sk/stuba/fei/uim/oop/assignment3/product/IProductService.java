package sk.stuba.fei.uim.oop.assignment3.product;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    Product createProduct(ProductRequest body);

    Product getById(Long productId) throws NotFoundException;

    Product updateById(Long productId, ProductUpdateRequest body) throws NotFoundException;

    void delete(Long productId) throws NotFoundException;

    int getAmount(Long productId) throws NotFoundException;

    int addAmount(Long productId, int amount) throws NotFoundException;

    void decreaseAmount(Product product, int amount);
}
