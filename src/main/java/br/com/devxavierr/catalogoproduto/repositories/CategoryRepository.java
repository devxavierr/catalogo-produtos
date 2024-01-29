package br.com.devxavierr.catalogoproduto.repositories;

import br.com.devxavierr.catalogoproduto.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
