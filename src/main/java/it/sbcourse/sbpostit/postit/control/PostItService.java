package it.sbcourse.sbpostit.postit.control;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

import it.sbcourse.sbpostit.postit.entity.PostIt;

public interface PostItService {

    Collection<PostIt> search(String search);

    PostIt create(PostIt entity);

    Optional<PostIt> find(Integer id);

    PostIt update(PostIt entity);

    void delete(Integer id);

    List<PostIt> findByCategory(Integer categoryId);
}
