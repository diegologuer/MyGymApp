package org.spring.service.profile;

import org.spring.model.User;
import org.spring.repository.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.logging.Logger;


@Component
public class CredentialsServiceImpl implements CredentialsService {

    private final UserDAO userDAO;
    private static final Logger logger = Logger.getLogger(CredentialsServiceImpl.class.getName());

    @Autowired
    public CredentialsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String generateUsername(String name, String lastname) {
        String username = name + "." + lastname;
        int serial = 1;
        while (userDAO.usernameExists(username)) {
            username = name + "." + lastname + serial;
            serial++;
        }
        logger.info("Created username: " + username);
        return username;
    }

    public String generatePassword() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:,.<>?";
        StringBuilder randomString = new StringBuilder(10);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }
        logger.info("Created Password");
        return randomString.toString();
    }

    public int createPersonProfile(String name, String lastname){

        logger.info("Validating name and lastname...");
        //Validate name and lastname
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid name");
        }
        if (lastname == null || lastname.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid username");
        }

        logger.info("Assigning credentials...");
        //Assigning username and password
        String username = generateUsername(name, lastname);
        String password = generatePassword();

        //Assigning available IDs
        int userId = userDAO.nextAvailableId();

        logger.info("new user saved");
        //Saving user
        userDAO.save(new User(userId, name, lastname, username, password, true));

        return userId;
    }
}
