package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_zaal")
public class Zaal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int nummer;

    @ManyToOne
    private Cinemacomplex cinemacomplex;

    @OneToMany
    private Set<Zone> zones = new HashSet<Zone>();

    @OneToMany
    private Set<Vertoning> vertoningen = new HashSet<Vertoning>();

    private Zaal() {

        this(0, null);

    }

    public Zaal(int nummer, Cinemacomplex cinemacomplex) {

        this.nummer = nummer;
        this.cinemacomplex = cinemacomplex;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public int getNummer() {

        return nummer;

    }

    public void setNummer(int nummer) {

        this.nummer = nummer;

    }

    public Cinemacomplex getCinemacomplex() {

        return cinemacomplex;

    }

    public void setCinemacomplex(Cinemacomplex cinemacomplex) {

        this.cinemacomplex = cinemacomplex;

    }

    public Set<Zone> getZones() {

        return zones;

    }

    public void setZones(Set<Zone> zones) {

        this.zones = zones;

    }

    public Set<Vertoning> getVertoningen() {

        return vertoningen;

    }

    public void setVertoningen(Set<Vertoning> vertoningen) {

        this.vertoningen = vertoningen;

    }

}
