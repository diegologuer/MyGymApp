package org.spring.repository.user;

import org.spring.model.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    User getById(int id);
    List<User> getAll();
    User removeById(int id);
    Boolean checkUsernameExistence(String username);
    int nextAvailableId();
}
