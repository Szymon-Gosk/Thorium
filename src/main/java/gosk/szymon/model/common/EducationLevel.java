package gosk.szymon.model.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EducationLevel")
public class EducationLevel implements Serializable {

    @Id
    @Column(name = "EducationLevelId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ClassName", nullable = false)
    private int classLevel;

    @Column(name = "ClassId", nullable = false)
    private String classId;

    public Long getId() {
        return id;
    }

    public Optional<Integer> getClassLevel() {
        return classLevel < 1 || classLevel > 3 ? Optional.empty() : Optional.of(classLevel);
    }

    public Optional<String> getClassId() {
        return StringUtils.isEmpty(classId) ? Optional.empty() : Optional.of(classId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EducationLevel educationLevel = (EducationLevel) o;
        return id != null && Objects.equals(id, educationLevel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
