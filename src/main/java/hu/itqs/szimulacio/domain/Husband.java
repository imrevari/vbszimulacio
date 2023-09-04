package hu.itqs.szimulacio.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Husband implements Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer consumedBear;

    public Husband() {
    }

    public Husband(Integer id, String name, Integer consumedBear) {
        this.id = id;
        this.name = name;
        this.consumedBear = consumedBear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConsumedBear() {
        return consumedBear;
    }

    public void setConsumedBear(Integer consumedBear) {
        this.consumedBear = consumedBear;
    }


    @Override
    public void watchAMatch(Match match) {
        Integer newlyConsumedBear = (match.getRateOfMatchSuccess() < 50) ? 2 : 3;
        setConsumedBear(getConsumedBear() + newlyConsumedBear);
    }
}
