package org.spring.service.profile;

public interface CredentialsService {

    String generateUsername(String name, String lastname);

    String generatePassword();
}
