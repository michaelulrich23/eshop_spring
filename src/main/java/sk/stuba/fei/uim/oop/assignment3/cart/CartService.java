package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository repository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductInCartService productInCartService;

    @Override
    public Cart createCart() {
        Cart c = new Cart();
        this.repository.save(c);
        return c;
    }

    @Override
    public Cart getById(Long cartId) throws NotFoundException {
        Cart c = this.repository.findCartById(cartId);
        if(c == null){
            throw new NotFoundException();
        }
        return c;
    }

    @Override
    public void delete(Long cartId) throws NotFoundException{
        Cart c = this.getById(cartId);
        if(!c.isPayed()){
            for(ProductInCart p : c.getShoppingList()){
                this.productService.addAmount(p.getId(), p.getAmount());
            }
        }
        this.repository.delete(c);
    }

    @Override
    public Cart addToCart(Long cartId, ProductAddRequest body) throws NotFoundException, IllegalOperationException {
        Cart c = this.unPayed(cartId);
        Product p = this.productService.getById(body.getProductId());
        if(p.getAmount() < body.getAmount()){
            throw new IllegalOperationException();
        }

        ProductInCart item = new ProductInCart(p.getId());

        if(!c.hasProductWithProductId(item.getProductId())){
            c.getShoppingList().add(item);
        }

        item = c.getProductWithProductId(item.getProductId());
        this.productService.decreaseAmount(p, body.getAmount());
        item.setAmount(item.getAmount() + body.getAmount());

        this.productInCartService.save(item);
        return this.repository.save(c);
    }

    @Override
    public double priceOfCart(Long cartId) throws NotFoundException, IllegalOperationException {
        Cart c = this.unPayed(cartId);

        double total = 0.0;
        for(ProductInCart item : c.getShoppingList()){
            Product p = this.productService.getById(item.getProductId());
            total = total + p.getPrice()*item.getAmount();
        }

        c.setPayed(true);
        this.repository.save(c);

        return total;
    }

    @Override
    public Cart unPayed(Long id) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(id);
        if(cart.isPayed()){
            throw new IllegalOperationException();
        }
        return cart;
    }


}
