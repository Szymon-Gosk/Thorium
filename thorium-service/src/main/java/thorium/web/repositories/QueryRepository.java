package thorium.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thorium.web.models.Query;

import java.math.BigInteger;

@Repository
public interface QueryRepository extends JpaRepository<Query, BigInteger> {



}
