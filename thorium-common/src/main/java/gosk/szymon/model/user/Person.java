package gosk.szymon.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "`Person`")
public class Person implements Serializable {

    @Id
    @Column(name = "`PersonId`", nullable = false)
    private Long id;

    @Column(name = "`Code`", nullable = false)
    private String code;

    @Column(name = "`Name`", nullable = false)
    private String name;

    @Column(name = "`SecondName`")
    private String secondName;

    @Column(name = "`Surname`", nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "`EducationType`", nullable = false)
    private EducationType educationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "`EducationLevel`", nullable = false)
    private EducationLevel educationLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
