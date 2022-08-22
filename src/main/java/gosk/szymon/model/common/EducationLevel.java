package gosk.szymon.model.common;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@AllArgsConstructor
public class EducationLevel {

    private final int classLevel;
    private final String classId;

    public Optional<Integer> getClassLevel() {
        return classLevel < 1 || classLevel > 3 ? Optional.of(classLevel) : Optional.empty();
    }

    public Optional<String> getClassId() {
        return StringUtils.isEmpty(classId) ? Optional.of(classId) : Optional.empty();
    }

}
