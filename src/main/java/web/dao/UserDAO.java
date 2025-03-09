package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void add(User user);

    void update(User user);

    void delete(long id);

    User findById(long id);
}
