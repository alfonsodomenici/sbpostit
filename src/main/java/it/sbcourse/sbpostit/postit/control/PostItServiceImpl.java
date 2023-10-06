package it.sbcourse.sbpostit.postit.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.sbcourse.sbpostit.postit.entity.PostIt;

@Service
public class PostItServiceImpl implements PostItService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Map<Integer, PostIt> repo = new HashMap<>();

    @Override
    public Collection<PostIt> search(String search) {
        return search== null ? repo.values() :
            repo.values()
            .stream()
            .filter(v -> v.getMsg().contains(search))
            .toList();
    }

    @Override
    public PostIt create(PostIt entity) {
        repo.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<PostIt> find(Integer id) {
        PostIt found = repo.get(id);
        return found==null ? Optional.empty() : Optional.of(found);
    }

    @Override
    public PostIt update(PostIt entity) {
        repo.put(entity.getId(), entity);
        return repo.get(entity.getId());
    }

    @Override
    public void delete(Integer id) {
        repo.remove(id);
    }

}
