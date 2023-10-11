package it.sbcourse.sbpostit.category.control;

import java.util.Collection;
import java.util.Optional;

import it.sbcourse.sbpostit.category.entity.Category;

public interface CategoryService {

    Collection<Category> search(String search);

    Category create(Category entity);

    Optional<Category> find(Integer id);

    Category update(Category entity);

    void delete(Integer id);
}
