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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "`User`")
public class User {

    @Id
    @Column(name = "`UserId`", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "`Username`", unique = true, nullable = false)
    private String username;

    @Column(name = "`Email`", unique = true)
    private String email;

    @Column(name = "`PasswordHash`")
    private String passwordHash;

    @OneToOne
    @JoinColumn(name = "`PersonId`", unique = true)
    private Person person;

    @Builder
    public User(String username, String email, String passwordHash, Person person) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.person = person;
    }

    @Builder
    public User(String username, String passwordHash, Person person) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
