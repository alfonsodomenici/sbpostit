package it.sbcourse.sbpostit.category.control;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.ResourceNotFoundException;
import it.sbcourse.sbpostit.postit.control.PostItRepository;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class CategoryService {

    private final CategoryRepository repo;
    private final PostItRepository postitRepo;

    public CategoryService(CategoryRepository repo, PostItRepository postitRepo) {
        this.repo = repo;
        this.postitRepo = postitRepo;
    }

    public Collection<Category> search(String search) {
        return search == null ? repo.findAll() : repo.findByNomeContains("*" + search + "*");
    }

    public Category create(Category entity) {
        return repo.save(entity);
    }

    public Optional<Category> find(Integer id) {
        return repo.findById(id);
    }

    public Category update(Integer id, Category entity) {
        Category toupdate = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Risorsa Category non trovata: id=" + id));
        toupdate.setNome(entity.getNome());
        return repo.save(entity);
    }

    public void delete(Integer id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Risorsa Category non trovata: id=" + id));
        postitRepo.deleteByCategory(id);
        repo.deleteById(id);
    }

}
