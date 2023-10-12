package it.sbcourse.sbpostit.category.control;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.MyCrudRepositoryImpl;
import it.sbcourse.sbpostit.category.entity.Category;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional(value = TxType.REQUIRED)
public class CategoryCustomRepository extends MyCrudRepositoryImpl<Category> {
    public Collection<Category> search(String search) {
        return em.createNamedQuery(Category.FIND_BY_NAME, Category.class)
            .setParameter(1, search == null ? "%" : "%" + search + "%")
            .getResultList();
    }
}
