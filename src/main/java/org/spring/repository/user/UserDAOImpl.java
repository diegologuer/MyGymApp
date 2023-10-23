package org.spring.repository.user;

import org.spring.model.Trainee;
import org.spring.model.User;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class UserDAOImpl implements UserDAO {

    private Storage storage;

    @Autowired
    public UserDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(User user) {
        int id = user.getId();
        //Save trainer in storage
        storage.getUserMap().put(id, user);
        System.out.println("Saving User...");
        //Check existence of previously saved trainer in the storage
        if (storage.getUserMap().containsKey(id)) {
            //Trainer successfully saved
            System.out.println("User successfully saved with id: " + id);
            return user.getId();
        } else {
            //Throws an exception in case trainee is not found
            throw new RuntimeException("Error saving user");
        }

    }

    @Override
    public User getById(int id) {
        System.out.println("Searching for user in the storage...");
        User user = storage.getUserMap().get(id);
        if (user == null) {
            throw new NoSuchElementException("The given id: " + id + " doesn't match with any user in storage");
        }
        System.out.println("User found in storage");
        return user;
    }

    @Override
    public User removeById(int id) {
        System.out.println("Checking for user in storage...");
        User user = storage.getUserMap().get(id);
        if (user == null) {
            throw new NoSuchElementException("User not found in storage");
        }
        System.out.println("Deleting User...");
        storage.getUserMap().remove(id);
        return user;
    }

    @Override
    public Boolean usernameExists(String username) {
        List<User> userlist = new ArrayList<>(storage.getUserMap().values());
        for (User user : userlist) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int nextAvailableId() {
        return storage.nextAvailableUserId();
    }
}
