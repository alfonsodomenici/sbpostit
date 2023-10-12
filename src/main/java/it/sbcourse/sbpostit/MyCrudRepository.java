package it.sbcourse.sbpostit;

import java.util.Optional;

public interface MyCrudRepository<T extends BaseEntity> {

    T create(T entity);

    Optional<T> find(Integer id);

    T update(T entity);

    void delete(Integer id);

}
