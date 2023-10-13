package it.sbcourse.sbpostit.category.control;

import java.util.List;

import it.sbcourse.sbpostit.category.entity.Category;

public interface CategoryCustomRepository {
    
    public List<Category> findCategoriesCountaingPostitWithMessage(String msg);
}
