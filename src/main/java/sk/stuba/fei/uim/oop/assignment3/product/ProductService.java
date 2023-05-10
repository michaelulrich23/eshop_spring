package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product createProduct(ProductRequest body){
        Product p = new Product(body);
        return this.repository.save(p);
    }

    @Override
    public Product getById(Long productId) throws NotFoundException{
        Product p = this.repository.findProductById(productId);
        if(p == null){
            throw new NotFoundException();
        }
        return p;
    }

    @Override
    public Product updateById(Long productId, ProductUpdateRequest body) throws NotFoundException {
        Product p = this.getById(productId);
        if(body.getName() != null){
            p.setName(body.getName());
        }
        if(body.getDescription() != null){
            p.setDescription(body.getDescription());
        }
        return this.repository.save(p);
    }

    @Override
    public void delete(Long productId) throws NotFoundException {
        Product p = this.getById(productId);
        this.repository.delete(p);
    }

    @Override
    public int getAmount(Long productId) throws NotFoundException {
        return this.getById(productId).getAmount();
    }

    @Override
    public int addAmount(Long productId, int amount) throws NotFoundException{
        Product p = this.getById(productId);
        p.setAmount(p.getAmount()+amount);
        this.repository.save(p);
        return p.getAmount();
    }

    @Override
    public void decreaseAmount(Product product, int amount){
        product.setAmount(product.getAmount() - amount);
        this.repository.save(product);
    }


}
