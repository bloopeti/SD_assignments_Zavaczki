package model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "points_p1")
    private int points_p1;

    @Column(name = "points_p2")
    private int points_p2;

//    @Column(name = "match_id")
//    private int match_id;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn
    private Match match;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints_p1() {
        return points_p1;
    }

    public void setPoints_p1(int points_p1) {
        this.points_p1 = points_p1;
    }

    public int getPoints_p2() {
        return points_p2;
    }

    public void setPoints_p2(int points_p2) {
        this.points_p2 = points_p2;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getMatch_id() {
        return match.getId();
    }
}
