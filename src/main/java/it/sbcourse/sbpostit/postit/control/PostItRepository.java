package it.sbcourse.sbpostit.postit.control;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.postit.entity.PostIt;

@Repository
public interface PostItRepository extends CrudRepository<PostIt,Integer> {
    
}
