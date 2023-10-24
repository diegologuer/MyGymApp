package org.spring.repository.user;

import org.spring.model.User;

public interface UserDAO {
    int save(User user);

    User getById(int id);

    User removeById(int id);

    Boolean usernameExists(String username);

    int nextAvailableId();
}
