package it.sbcourse.sbpostit.postit.control;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class PostItServiceImpl implements PostItService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager em;

    @Override
    public Collection<PostIt> search(String search) {
        return em.createNamedQuery(PostIt.FIND_BY_MESSAGE_CONTAINS, PostIt.class)
                .setParameter(1, search == null ? "%" : "%" + search + "%")
                .getResultList();
    }

    @Override
    public PostIt create(PostIt entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public Optional<PostIt> find(Integer id) {
        PostIt found = em.find(PostIt.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    @Override
    public PostIt update(PostIt entity) {
        Optional<PostIt> opt = find(entity.getId());
        if (opt.isEmpty()) {
            return entity; // error
        }
        PostIt toupdate = opt.get();
        toupdate.setMsg(entity.getMsg());
        toupdate.setQuando(entity.getQuando());
        return em.merge(toupdate);
    }

    @Override
    public void delete(Integer id) {
        Optional<PostIt> opt = find(id);
        if (opt.isPresent()) {
            PostIt todelete = opt.get();
            em.remove(todelete);
        }

    }

    @Override
    public List<PostIt> findByCategory(Integer categoryId) {
        return em.createNamedQuery(PostIt.FIND_BY_CATEGORY, PostIt.class)
                .setParameter(1, categoryId)
                .getResultList();
    }

}
