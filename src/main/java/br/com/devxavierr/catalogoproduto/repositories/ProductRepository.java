package br.com.devxavierr.catalogoproduto.repositories;

import br.com.devxavierr.catalogoproduto.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
