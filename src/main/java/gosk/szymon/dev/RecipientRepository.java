package gosk.szymon.dev;

import gosk.szymon.model.common.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@DevOnly
@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> { }
