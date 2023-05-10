package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

public interface ICartService {
    Cart createCart();

    Cart getById(Long cartId) throws NotFoundException;

    void delete(Long cartId) throws NotFoundException;

    Cart addToCart(Long cartId, ProductAddRequest body) throws NotFoundException, IllegalOperationException;

    double priceOfCart(Long cartId) throws NotFoundException, IllegalOperationException;

    Cart unPayed(Long id) throws NotFoundException, IllegalOperationException;
}
