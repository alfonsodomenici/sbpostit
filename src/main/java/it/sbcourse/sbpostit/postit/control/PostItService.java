package it.sbcourse.sbpostit.postit.control;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.ResourceNotFoundException;
import it.sbcourse.sbpostit.postit.boundary.PostItIncomingDTO;
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

    public Page<PostIt> search(String search, Pageable pageable) {
        return search == null ? repo.findAll(pageable) : repo.findByMessage("%" + search + "%", pageable);
    }

    public PostIt create(PostIt entity) {
        return repo.save(entity);
    }

    public PostIt create(Category cat, PostItIncomingDTO dto) {
        return repo.save(PostIt.fromDTO(dto).setCategoria(cat));
    }

    public PostIt find(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Risorsa PostIt non trovata: id=" + id));
    }

    public PostIt update(Integer id, PostIt entity) {
        PostIt toupdate = find(id);
        toupdate.setMsg(entity.getMsg());
        toupdate.setQuando(entity.getQuando());
        return repo.save(toupdate);
    }

    public PostIt update(Integer id, PostItIncomingDTO dto) {
        PostIt toupdate = find(id);
        toupdate.merge(dto);
        return repo.save(toupdate);
    }

    public void delete(Integer id) {
        find(id);
        repo.deleteById(id);
    }

    public List<PostIt> findByCategory(Integer categoryId) {
        return repo.findByCategory(categoryId);
    }

}
