package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest body){
        return new ResponseEntity<>(new ProductResponse(this.productService.createProduct(body)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long productId) throws NotFoundException {
        return new ProductResponse(this.productService.getById(productId));
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long productId, @RequestBody ProductUpdateRequest body) throws NotFoundException {
        return new ProductResponse(this.productService.updateById(productId, body));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws NotFoundException {
        this.productService.delete(productId);
    }

    @GetMapping("/{id}/amount")
    public Amount getAmount(@PathVariable("id") Long productId) throws NotFoundException{
        return new Amount(this.productService.getAmount(productId));
    }

    @PostMapping("/{id}/amount")
    public Amount addAmount(@PathVariable("id") Long productId, @RequestBody Amount body) throws NotFoundException {
        return new Amount(this.productService.addAmount(productId, body.getAmount()));
    }

}
