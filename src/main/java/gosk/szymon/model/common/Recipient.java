package gosk.szymon.model.common;

import gosk.szymon.model.MealOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Recipient")
public class Recipient implements Serializable {

    @Id
    @Column(name = "RecipientId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "SecondName")
    private String secondName;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "EducationType", nullable = false)
    private EducationType educationType;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "EducationLevelId",nullable = false)
    private EducationLevel educationLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipient recipient = (Recipient) o;
        return id != null && Objects.equals(id, recipient.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
