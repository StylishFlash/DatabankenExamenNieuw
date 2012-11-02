package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_bioscoopholding")
public class Bioscoopholding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naam;

    @Column
    private String adres;

    @Column
    private String btwNummer;

    @OneToMany(mappedBy = "bioscoopholding")
    private Set<Cinemacomplex> cinemacomplexen = new HashSet<Cinemacomplex>();

    @OneToMany(mappedBy = "bioscoopholding")
    private Set<Film> films = new HashSet<Film>();

    private Bioscoopholding() {

        this(null, null, null);

    }

    public Bioscoopholding(String naam, String adres, String btwNummer) {

        this.naam = naam;
        this.adres = adres;
        this.btwNummer = btwNummer;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public String getNaam() {

        return naam;

    }

    public void setNaam(String naam) {

        this.naam = naam;

    }

    public String getAdres() {

        return adres;

    }

    public void setAdres(String adres) {

        this.adres = adres;

    }

    public String getBtwNummer() {

        return btwNummer;

    }

    public void setBtwNummer(String btwNummer) {

        this.btwNummer = btwNummer;

    }

    public Set<Cinemacomplex> getCinemacomplexen() {

        return cinemacomplexen;

    }

    public void setCinemacomplexen(Set<Cinemacomplex> cinemacomplexen) {

        this.cinemacomplexen = cinemacomplexen;

    }

    public void addCinemacomplex(Cinemacomplex cinemacomplex) {

        cinemacomplexen.add(cinemacomplex);

    }

    public void removeCinemacomplex(Cinemacomplex cinemacomplex) {

        cinemacomplexen.remove(cinemacomplex);

    }

    public Set<Film> getFilms() {

        return films;

    }

    public void setFilms(Set<Film> films) {

        this.films = films;

    }

    public void addFilm(Film film) {

        films.add(film);

    }

    public void removeFilm(Film film) {

        films.remove(film);

    }

}
