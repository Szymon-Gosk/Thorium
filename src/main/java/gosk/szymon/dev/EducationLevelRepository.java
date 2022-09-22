package gosk.szymon.dev;

import gosk.szymon.model.common.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@DevOnly
@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> { }
