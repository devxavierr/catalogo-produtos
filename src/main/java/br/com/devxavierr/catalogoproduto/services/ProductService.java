package br.com.devxavierr.catalogoproduto.services;

import br.com.devxavierr.catalogoproduto.domain.category.Category;
import br.com.devxavierr.catalogoproduto.domain.category.CategoryDTO;
import br.com.devxavierr.catalogoproduto.domain.category.exceptions.CategoryNotFoundException;
import br.com.devxavierr.catalogoproduto.domain.product.Product;
import br.com.devxavierr.catalogoproduto.domain.product.ProductDTO;
import br.com.devxavierr.catalogoproduto.domain.product.exceptions.ProductNotFoundException;
import br.com.devxavierr.catalogoproduto.repositories.CategoryRepository;
import br.com.devxavierr.catalogoproduto.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository repository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository){
        this.categoryService = categoryService;
        this.repository = productRepository;
    }

    public Product insert(ProductDTO productData){
        Category category = this.categoryService.getByid(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        if(productData.categoryId() != null){
            this.categoryService.getByid(productData.categoryId())
                    .ifPresent(product::setCategory);
        }

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());

        this.repository.save(product);
        return product;
    }

    public List<Product> getAll(){
        return this.repository.findAll();
    }


    public void delete(String id) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }
}
