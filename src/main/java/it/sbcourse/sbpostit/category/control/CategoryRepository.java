package it.sbcourse.sbpostit.category.control;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.category.entity.Category;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category,Integer>,CategoryCustomRepository  {
    
    public List<Category> findByNomeContains(String search);

    public List<Category> findByName(String search);

    @Query(name = Category.FIND_BY_NAME)
    public List<Category> cercaPerNome(String search);

    @Query("select e from Category e where e.nome like ?1")
    public List<Category> ancoraCercaPerNome(String search);

}
