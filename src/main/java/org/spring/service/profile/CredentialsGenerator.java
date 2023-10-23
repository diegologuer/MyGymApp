package org.spring.service.profile;

public interface CredentialsGenerator {

    String generateUsername(String name, String lastname);
    String generatePassword();
}
