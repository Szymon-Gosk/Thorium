package gosk.szymon.model.user;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public enum EducationLevel {

    A1(1, "A"),
    B1(1, "B"),
    A2(2, "A"),
    B2(2, "B"),
    A3(3, "A"),
    B3(3, "B"),
    NONE(0, "");

    private final int classLevel;
    private final String classId;

    EducationLevel(int classLevel, String classId) {
        this.classLevel = classLevel;
        this.classId = classId;
    }

    public Optional<Integer> getClassLevel() {
        return classLevel < 1 || classLevel > 3 ? Optional.empty() : Optional.of(classLevel);
    }

    public Optional<String> getClassId() {
        return StringUtils.isEmpty(classId) ? Optional.empty() : Optional.of(classId);
    }

}
