package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.UserDAO;
import lk.ijse.dep9.app.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(String pk) {

    }

    @Override
    public Optional<User> findById(String pk) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existById(String pk) {
        return false;
    }
}
