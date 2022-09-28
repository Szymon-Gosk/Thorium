package gosk.szymon.users;

import gosk.szymon.encryption.PasswordEncryptor;
import gosk.szymon.model.user.Person;
import gosk.szymon.model.user.RegistrationInformationDTO;
import gosk.szymon.model.user.User;
import gosk.szymon.repositories.PersonRepository;
import gosk.szymon.repositories.UserRepository;
import gosk.szymon.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    @Autowired
    public UserService(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    public ResponseEntity<String> createUser(RegistrationInformationDTO informationDTO) {
        try {
            return tryCreatingUser(informationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }

    private ResponseEntity<String> tryCreatingUser(RegistrationInformationDTO informationDTO) {
        if (passwordsMatches(informationDTO)) {
            if (isUsernameUnique(informationDTO)) {
                personRepository
                        .findByCode(informationDTO.getPersonCode())
                        .ifPresent(person -> createAndSaveUser(informationDTO, person));
            }
            return createBadRequest("Username is not unique");
        }
        return createBadRequest("Passwords did not match");
    }

    private boolean passwordsMatches(RegistrationInformationDTO informationDTO) {
        return passwordValidator.validate(informationDTO.getPassword(), informationDTO.getRepeatedPassword());
    }

    private boolean isUsernameUnique(RegistrationInformationDTO informationDTO) {
        return userRepository.findByUsername(informationDTO.getUsername()).isEmpty();
    }

    private User mapToUser(RegistrationInformationDTO informationDTO, Person person) {
        return User
                .builder()
                .username(informationDTO.getUsername())
                .email(informationDTO.getEmail())
                .passwordHash(passwordEncryptor.hash(informationDTO.getPassword()))
                .person(person)
                .build();
    }

    private void createAndSaveUser(RegistrationInformationDTO informationDTO, Person person) {
        userRepository.save(mapToUser(informationDTO, person));
    }

    private ResponseEntity<String> createBadRequest(String message) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

}

