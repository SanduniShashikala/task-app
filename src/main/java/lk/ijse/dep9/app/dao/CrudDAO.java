package lk.ijse.dep9.app.dao;

import lk.ijse.dep9.app.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity, ID extends Serializable> extends SuperDAO{
    public T save(T t);

    public void update(T t);

    public void deleteById(ID pk);

    public Optional<T> findById(ID pk);

    public List<T> findAll();

    public long count();

    public boolean existById(ID pk);
}
