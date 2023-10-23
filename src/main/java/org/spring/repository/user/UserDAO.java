package org.spring.repository.user;

import org.spring.model.User;

import java.util.List;

public interface UserDAO {
    int save(User user);
    User getById(int id);
    List<User> getAll();
    User removeById(int id);
    Boolean usernameExistence(String username);
    int nextAvailableId();
}
