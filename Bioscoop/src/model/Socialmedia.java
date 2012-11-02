package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_socialmedia")
public class Socialmedia {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String naam;

    @OneToMany(mappedBy = "socialmedia")
    private Set<SocialmediaBericht> socialmediaBerichten = new HashSet<SocialmediaBericht>();

    private Socialmedia() {

        this(null);

    }

    public Socialmedia(String naam) {

        this.naam = naam;

    }

    public int getId() {

        return id;

    }

    private void setId(int id) {

        this.id = id;

    }

    public String getNaam() {

        return naam;

    }

    public void setNaam(String naam) {

        this.naam = naam;

    }

    public Set<SocialmediaBericht> getSocialmediaBerichten() {

        return socialmediaBerichten;

    }

    public void setSocialmediaBerichten(Set<SocialmediaBericht> socialmediaBerichten) {

        this.socialmediaBerichten = socialmediaBerichten;

    }

}
