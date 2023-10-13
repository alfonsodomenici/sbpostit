package it.sbcourse.sbpostit.category.control;

import java.util.List;

import org.springframework.data.domain.Pageable;

import it.sbcourse.sbpostit.category.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Category> findCategoriesCountaingPostitWithMessage(String msg) {
        return em.createQuery("select e from Category e join e.postits p where p.msg like ?1", Category.class)
                .setParameter(1, "%" + msg + "%")
                .getResultList();

    }

}
