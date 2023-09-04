package hu.itqs.szimulacio.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Wife implements Human{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer spareTime;

    public Wife() {
    }

    public Wife(Integer id, String name, Integer spareTime) {
        this.id = id;
        this.name = name;
        this.spareTime = spareTime;
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

    public Integer getSpareTime() {
        return spareTime;
    }

    public void setSpareTime(Integer spareTime) {
        this.spareTime = spareTime;
    }


    @Override
    public void watchAMatch(Match match) {
        setSpareTime(getSpareTime() + match.getTime());
    }
}
