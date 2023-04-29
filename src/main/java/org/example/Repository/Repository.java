package org.example.Repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    public Optional<T> findById(ID id);
    public  void deleteById(ID id);
    public <S extends T> S save(S entity);
    public List<T> getAll();
    public void update(T entity);

}
