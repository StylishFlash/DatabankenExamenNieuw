package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_waardering")
public class Waardering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date datum;

    @Column
    private String inhoud;

    @Column
    private int Score;

    @ManyToOne
    private Klant klant;

    @ManyToOne
    private Film film;

    private Waardering() {

        this(null, null, 0, null, null);

    }

    public Waardering(Date datum, String inhoud, int score, Klant klant, Film film) {

        this.datum = datum;
        this.inhoud = inhoud;
        Score = score;
        this.klant = klant;
        this.film = film;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public Date getDatum() {

        return datum;

    }

    public void setDatum(Date datum) {

        this.datum = datum;

    }

    public String getInhoud() {

        return inhoud;

    }

    public void setInhoud(String inhoud) {

        this.inhoud = inhoud;

    }

    public int getScore() {

        return Score;

    }

    public void setScore(int score) {

        Score = score;

    }

    public Klant getKlant() {

        return klant;

    }

    public void setKlant(Klant klant) {

        this.klant = klant;

    }

    public Film getFilm() {

        return film;

    }

    public void setFilm(Film film) {

        this.film = film;

    }

}
