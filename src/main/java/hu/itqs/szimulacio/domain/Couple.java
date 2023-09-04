package hu.itqs.szimulacio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Couple {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Husband husband;

    @OneToOne
    private Wife wife;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Match> whatchedMathes;

    public Couple() {
    }

    public Couple(Integer id, Husband husband, Wife wife, List<Match> whatchedMathes) {
        this.id = id;
        this.husband = husband;
        this.wife = wife;
        this.whatchedMathes = whatchedMathes;
    }

    public Couple(Husband husband, Wife wife) {
        this.husband = husband;
        this.wife = wife;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    public List<Match> getWhatchedMathes() {
        return whatchedMathes;
    }

    public void setWhatchedMathes(List<Match> whatchedMathes) {
        this.whatchedMathes = whatchedMathes;
    }

    public void watchAMatch(Match match){
        List<Match> matchList = Objects.isNull(getWhatchedMathes()) ? new ArrayList<>() : getWhatchedMathes();
        matchList.add(match);
        setWhatchedMathes(matchList);
        this.husband.watchAMatch(match);
        this.wife.watchAMatch(match);
    }
}
