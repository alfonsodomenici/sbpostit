package it.sbcourse.sbpostit.postit.control;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.ResourceNotFoundException;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class PostItService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PostItRepository repo;

    public Page<PostIt> search(String search, Pageable pageable) {
        return search == null ? repo.findAll(pageable) : repo.findByMsgContains(search, pageable);
    }

    public PostIt create(PostIt entity) {
        return repo.save(entity);
    }

    public Optional<PostIt> find(Integer id) {
        return repo.findById(id);
    }

    public PostIt update(PostIt entity) {
        PostIt toupdate = repo.findById(entity.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Nessuna risorsa PostIt trovata con id:" + entity.getId()));
        toupdate.setMsg(entity.getMsg());
        toupdate.setQuando(entity.getQuando());
        return repo.save(toupdate);
    }

    public void delete(Integer id) {
        repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nessuna risorsa PostIt trovata con id:" + id));
        repo.deleteById(id);
    }

    public List<PostIt> findByCategory(Integer categoryId) {
        return repo.findByCategory(categoryId);
    }

}
