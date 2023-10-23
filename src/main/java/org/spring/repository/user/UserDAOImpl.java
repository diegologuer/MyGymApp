package org.spring.repository.user;

import org.spring.model.User;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO{

    private Storage storage;
    private static int id = 1;

    @Autowired
    public UserDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(User user) {
        storage.getUserMap().put(user.getId(), user);
    }

    @Override
    public User getById(int id) {
        return storage.getUserMap().get(id);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User removeById(int id) {
        return null;
    }

    @Override
    public Boolean checkUsernameExistence(String username) {
        var map = storage.getUserMap();
        for(Map.Entry<Integer, User> entry : map.entrySet()){
            String json = String.valueOf(entry.getValue());
            if (json.contains("\"username\":\"" + username + "\"")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int nextAvailableId() {
        var map = storage.getUserMap();
        while(map.containsKey(id)) {
            id++;
        }
        return id;
    }
}
