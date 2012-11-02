package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_socialmediabericht")
public class SocialmediaBericht {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date tijdstip;

    @Column
    private String inhoud;

    @ManyToOne
    private Socialmedia socialmedia;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Cinemacomplex cinemacomplex;

    private SocialmediaBericht() {

        this(null, null, null);

    }

    public SocialmediaBericht(Date tijdstip, String inhoud, Socialmedia socialmedia) {

        this.tijdstip = tijdstip;
        this.inhoud = inhoud;
        this.socialmedia = socialmedia;

    }

    public Integer getId() {

        return id;
    }

    private void setId(Integer id) {

        this.id = id;

    }

    public Date getTijdstip() {

        return tijdstip;

    }

    public void setTijdstip(Date tijdstip) {

        this.tijdstip = tijdstip;

    }

    public String getInhoud() {

        return inhoud;

    }

    public void setInhoud(String inhoud) {

        this.inhoud = inhoud;

    }

    public Socialmedia getSocialmedia() {

        return socialmedia;

    }

    public void setSocialmedia(Socialmedia socialmedia) {

        this.socialmedia = socialmedia;

    }

    public Film getFilm() {

        return film;

    }

    public void setFilm(Film film) {

        this.film = film;

    }

    public Cinemacomplex getCinemacomplex() {

        return cinemacomplex;

    }

    public void setCinemacomplex(Cinemacomplex cinemacomplex) {

        this.cinemacomplex = cinemacomplex;

    }

}
