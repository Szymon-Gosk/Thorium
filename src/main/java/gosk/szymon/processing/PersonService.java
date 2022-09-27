package gosk.szymon.processing;

import gosk.szymon.dev.DevOnly;
import gosk.szymon.repositories.PersonRepository;
import gosk.szymon.model.user.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@DevOnly
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity<String> createPersons(List<Person> people) {
        try {
            personRepository.saveAll(people);
            return ResponseEntity
                    .ok("Persons created");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating persons: " + e.getMessage());
        }
    }

}
