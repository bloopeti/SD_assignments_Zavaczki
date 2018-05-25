package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "matches") // note to self: its bad to have keywords as table/row names in sql
public class Match {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lvl")
    private int lvl;

    //@Column(name = "player1")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private User player1;

    //@Column(name = "player2")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private User player2;

    //@Column(name = "tournament_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private Set<Game> games = new HashSet<Game>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
