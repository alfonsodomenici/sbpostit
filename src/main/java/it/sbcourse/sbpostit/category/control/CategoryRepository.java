package it.sbcourse.sbpostit.category.control;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.category.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer>  {
    
}
