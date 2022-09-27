package gosk.szymon.dev;

import gosk.szymon.model.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@DevOnly
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { }
