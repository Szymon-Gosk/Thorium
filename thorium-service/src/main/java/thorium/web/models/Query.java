package thorium.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Query {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 65536)
    private String content;

}
