package it.sbcourse.sbpostit.category.control;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import it.sbcourse.sbpostit.category.entity.Category;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category,Integer>, CategoryCustomRepository  {
    
    public List<Category> findByNomeContains(String search);

}
