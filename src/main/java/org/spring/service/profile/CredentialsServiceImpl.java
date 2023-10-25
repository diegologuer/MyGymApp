package org.spring.service.profile;

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
}
