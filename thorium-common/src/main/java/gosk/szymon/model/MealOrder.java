package gosk.szymon.model;

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MealOrder")
public class MealOrder implements Serializable {

    @Id
    @Column(name = "OrderID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Uuid", nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "IsConfirmed", nullable = false)
    private boolean isConfirmed = false;

    @Column(name = "Date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "MealType", nullable = false)
    private MealType mealType;

    @ManyToOne
    @JoinColumn(name = "RecipientId")
    private Recipient recipient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MealOrder mealOrder = (MealOrder) o;
        return id != null && Objects.equals(id, mealOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
