package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_klant")
public class Klant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String naam;
    @Column
    private String adres;

    @OneToMany(mappedBy = "klant")
    private Set<Verkoop> verkopen = new HashSet<Verkoop>();

    @OneToMany(mappedBy = "klant")
    private Set<Waardering> waarderingen = new HashSet<Waardering>();

    private Klant() {

        this(null, null);

    }

    public Klant(String naam, String adres) {

        this.naam = naam;
        this.adres = adres;

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

    public Set<Verkoop> getVerkopen() {

        return verkopen;

    }

    public void setVerkopen(Set<Verkoop> verkopen) {

        this.verkopen = verkopen;

    }

    public void addVerkoop(Verkoop verkoop) {

        verkopen.add(verkoop);

    }

    public void removeVerkoop(Verkoop verkoop) {

        verkopen.remove(verkoop);

    }

    public Set<Waardering> getWaarderingen() {

        return waarderingen;

    }

    public void setWaarderingen(Set<Waardering> waarderingen) {

        this.waarderingen = waarderingen;

    }

    public void addWaardering(Waardering waardering) {

        waarderingen.add(waardering);

    }

    public void removeWaardering(Waardering waardering) {

        waarderingen.remove(waardering);

    }

}
