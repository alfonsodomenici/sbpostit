package it.sbcourse.sbpostit.postit.control;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.sbcourse.sbpostit.MyCrudRepositoryImpl;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional(value = TxType.REQUIRED)
public class PostItCustomRepository extends MyCrudRepositoryImpl<PostIt> {

    public Collection<PostIt> search(String search) {
        return em.createNamedQuery(PostIt.FIND_BY_MESSAGE_CONTAINS, PostIt.class)
                .setParameter(1, search == null ? "%" : "%" + search + "%")
                .getResultList();
    }

    public List<PostIt> findByCategory(Integer categoryId) {
        return em.createNamedQuery(PostIt.FIND_BY_CATEGORY, PostIt.class)
                .setParameter(1, "xx")
                .getResultList();
    }
}
