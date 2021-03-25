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
public class Render {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 256)
    private String name;

    @Column(length = 65536)
    private String latex;

}
