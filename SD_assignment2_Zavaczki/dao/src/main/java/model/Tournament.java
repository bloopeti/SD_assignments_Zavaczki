package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "fee")
    private int fee;

    @Column(name = "total_pot")
    private int total_pot;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<Match> matches = new HashSet<Match>();

    @ManyToMany
    @JoinTable(name = "enrolments", joinColumns =
        @JoinColumn(name = "tournament_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> users = new HashSet<User>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getTotal_pot() {
        return total_pot;
    }

    public void setTotal_pot(int total_pot) {
        this.total_pot = total_pot;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
