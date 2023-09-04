package hu.itqs.szimulacio.domain;


import javax.persistence.*;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer time;

    private Integer rateOfMatchSuccess;

    @ManyToOne(fetch = FetchType.EAGER)
    private FootballTeam team1;

    @ManyToOne(fetch = FetchType.EAGER)
    private FootballTeam team2;

    public Match() {
    }

    public Match(Integer id, Integer time, Integer rateOfMatchSuccess, FootballTeam team1, FootballTeam team2) {
        this.id = id;
        this.time = time;
        this.rateOfMatchSuccess = rateOfMatchSuccess;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Match(Integer extraTime, Integer rateOfMatchSuccess, FootballTeam team1, FootballTeam team2) {
        this.time = 90 + extraTime;
        this.team1 = team1;
        this.team2 = team2;
        this.rateOfMatchSuccess = rateOfMatchSuccess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public FootballTeam getTeam1() {
        return team1;
    }

    public void setTeam1(FootballTeam team1) {
        this.team1 = team1;
    }

    public FootballTeam getTeam2() {
        return team2;
    }

    public void setTeam2(FootballTeam team2) {
        this.team2 = team2;
    }

    public Integer getRateOfMatchSuccess() {
        return rateOfMatchSuccess;
    }

    public void setRateOfMatchSuccess(Integer rateOfMatchSuccess) {
        this.rateOfMatchSuccess = rateOfMatchSuccess;
    }
}
