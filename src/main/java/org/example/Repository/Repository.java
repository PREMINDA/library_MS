package org.example.Repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    public Optional<T> findById(ID id);
    public  void deleteById(ID id);
    public T save(T entity);
     public List<T> findAll();

}
