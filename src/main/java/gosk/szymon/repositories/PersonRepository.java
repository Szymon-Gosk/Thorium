package gosk.szymon.repositories;

import gosk.szymon.dev.DevOnly;
import gosk.szymon.model.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { }
