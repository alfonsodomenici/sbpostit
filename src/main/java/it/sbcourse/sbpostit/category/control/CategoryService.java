package it.sbcourse.sbpostit.category.control;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.category.entity.Category;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
@Transactional(value = TxType.REQUIRED)
public class CategoryService  {

    @Autowired
    CategoryCustomRepository repo;

    public Collection<Category> search(String search) {
        return repo.search(search);
    }

    public Category create(Category entity) {
        return repo.create(entity);
    }


    public Optional<Category> find(Integer id) {
        return repo.find(id);
    }


    public Category update(Category entity) {
        Optional<Category> opt = repo.find(entity.getId());
        if(opt.isPresent()){
            Category toupdate = opt.get();
            toupdate.setNome(entity.getNome());
            return repo.update(toupdate);
        }
        return null;
    }


    public void delete(Integer id) {
        Optional<Category> opt = repo.find(id);
        if(opt.isPresent()){
            repo.delete(id);
        }
    }
    
}
