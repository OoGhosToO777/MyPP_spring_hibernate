package hiber.model;

import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car")
@Component("car")
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "series")
    private int series;
    @Column(name = "model")
    private String model;

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

}
