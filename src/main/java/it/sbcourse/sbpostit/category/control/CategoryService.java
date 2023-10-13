package it.sbcourse.sbpostit.category.control;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.ResourceNotFoundException;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItRepository;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class CategoryService {

    @Autowired
    CategoryRepository repo;
    @Autowired
    PostItRepository postitRepo;

    public Collection<Category> search(String search) {
        return search == null ? repo.findAll() : repo.findByNomeContains(search);
    }

    public Category create(Category entity) {
        return repo.save(entity);
    }

    public Optional<Category> find(Integer id) {
        return repo.findById(id);
    }

    public Category update(Category entity) {
        Category toupdate = repo.findById(entity.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Nessuna risorsa Categoria trovata con id:" + entity.getId()));
        toupdate.setNome(entity.getNome());
        return repo.save(toupdate);
    }

    public void delete(Integer id) {
        Category toupdate = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nessuna risorsa Categoria trovata con id:" + id));
        postitRepo.deleteByCategory(id);
        repo.deleteById(id);
    }

}
