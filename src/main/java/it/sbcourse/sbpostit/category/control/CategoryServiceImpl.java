package it.sbcourse.sbpostit.category.control;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.category.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class CategoryServiceImpl implements CategoryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Category> search(String search) {
        return em.createNamedQuery(Category.FIND_BY_NAME, Category.class)
            .setParameter(1, search == null ? "%" : "%" + search + "%")
            .getResultList();
    }

    @Override
    public Category create(Category entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public Optional<Category> find(Integer id) {
        Category found = em.find(Category.class,id);
        return found == null ? Optional.empty() : Optional.of(found)
 ;   }

    @Override
    public Category update(Category entity) {
        Optional<Category> opt = find(entity.getId());
        if(opt.isPresent()){
            Category toupdate = opt.get();
            toupdate.setNome(entity.getNome());
            return em.merge(toupdate);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Optional<Category> opt = find(id);
        if(opt.isPresent()){
            em.remove(opt.get());
        }
    }
    
}
