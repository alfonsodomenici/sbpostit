package it.sbcourse.sbpostit.postit.control;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class PostItService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PostItRepository repo;

    public PostItService(PostItRepository repo) {
        this.repo = repo;
    }

    public Collection<PostIt> search(String search) {
        return null;
    }

    public PostIt create(PostIt entity) {
        return repo.save(entity);
    }

    public Optional<PostIt> find(Integer id) {
        return repo.findById(id);
    }

    public PostIt update(PostIt entity) {
        Optional<PostIt> opt = repo.findById(entity.getId());
        if (opt.isEmpty()) {
            return entity; // error
        }
        PostIt toupdate = opt.get();
        toupdate.setMsg(entity.getMsg());
        toupdate.setQuando(entity.getQuando());
        return repo.save(toupdate);
    }

    public void delete(Integer id) {
        Optional<PostIt> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.deleteById(id);
        }

    }

    public List<PostIt> findByCategory(Integer categoryId) {
        return repo.findByCategory(categoryId);
    }

}
