package it.sbcourse.sbpostit.postit.control;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class PostItService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PostItCustomRepository repo;

   
    public Collection<PostIt> search(String search) {
        return repo.search(search);
    }

    
    public PostIt create(PostIt entity) {
        return repo.create(entity);
    }

    
    public Optional<PostIt> find(Integer id) {
        return repo.find(id);
    }

    
    public PostIt update(PostIt entity) {
        Optional<PostIt> opt = repo.find(entity.getId());
        if (opt.isEmpty()) {
            return entity; // error
        }
        PostIt toupdate = opt.get();
        toupdate.setMsg(entity.getMsg());
        toupdate.setQuando(entity.getQuando());
        return repo.update(toupdate);
    }

    
    public void delete(Integer id) {
        Optional<PostIt> opt = repo.find(id);
        if (opt.isPresent()) {
            repo.delete(id);
        }

    }

    
    public List<PostIt> findByCategory(Integer categoryId) {
        return repo.findByCategory(categoryId);
    }

}
