package it.sbcourse.sbpostit.postit.control;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.postit.entity.PostIt;
import java.util.List;

@Repository
public interface PostItRepository extends ListCrudRepository<PostIt, Integer> {

    public List<PostIt> findByMsgContains(String search); // crea la query al volo dal nome del metodo

    public List<PostIt> findByCategory(Integer categoryId); // usa la @NameQuery con nome uguale al metodo

    @Query(name = PostIt.FIND_BY_CATEGORY)
    public List<PostIt> dellaCategoria(Integer categoryId);

    @Query(value = "select e from PostIt e where e.categoria.id = ?1")
    public List<PostIt> ancoraDellaCategoria(Integer categoryId);

    @Modifying
    @Query(name = PostIt.DELETE_BY_CATEGORY )
    public void deleteByCategory(Integer categoryId);
}
