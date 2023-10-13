package it.sbcourse.sbpostit.postit.control;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.postit.entity.PostIt;

@Repository
public interface PostItRepository extends ListCrudRepository<PostIt,Integer>, PagingAndSortingRepository<PostIt,Integer>{
  
    @Query(name = PostIt.FIND_BY_MESSAGE_CONTAINS)
    public Page<PostIt> findByMessage(String message, Pageable pageable);

    @Query(name = PostIt.FIND_BY_CATEGORY)
    public List<PostIt> findByCategory(Integer categoryId);
    
    @Modifying
    @Query("delete from PostIt e where e.categoria.id = ?1")
    public void deleteByCategory(Integer categoryId);
}
