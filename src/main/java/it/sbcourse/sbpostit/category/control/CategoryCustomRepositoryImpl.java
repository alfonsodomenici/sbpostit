package it.sbcourse.sbpostit.category.control;

import java.util.List;

import it.sbcourse.sbpostit.category.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CategoryCustomRepositoryImpl implements CategoryCustomRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Category> categoriesContainsPostItsWithMessage(String message) {
        return em.createQuery("select e from Category e join e.postits p where p.msg like ?1 order  by e.nome", Category.class)
            .setParameter(1, "%" + message + "%")
            .getResultList();
    }
    
}
