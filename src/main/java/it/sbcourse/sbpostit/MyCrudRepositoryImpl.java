package it.sbcourse.sbpostit;

import java.util.Optional;

import org.springframework.core.GenericTypeResolver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class MyCrudRepositoryImpl<T extends BaseEntity> implements MyCrudRepository<T> {

    private final Class<T> genericType;

    @PersistenceContext
    protected EntityManager em;

    
    public MyCrudRepositoryImpl() {
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), MyCrudRepository.class);
    }


    @Override
    public T create(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public Optional<T> find(Integer id) {
       T opt = em.find(genericType,id);
       return opt == null ? Optional.empty() : Optional.of(opt);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Integer id) {
        Optional<T> opt = find(id);
        if(opt.isPresent()){
            em.remove(opt.get());
        }
    }
    
}
