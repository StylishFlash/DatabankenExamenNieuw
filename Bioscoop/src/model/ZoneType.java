package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_zonetype")
public class ZoneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naam;

    @Column
    private double prijs;

    @OneToMany
    private Set<Zone> zones = new HashSet<Zone>();

    private ZoneType() {

        this(null, 0);

    }

    public ZoneType(String naam, double prijs) {

        this.naam = naam;
        this.prijs = prijs;

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

    public double getPrijs() {

        return prijs;

    }

    public void setPrijs(double prijs) {

        this.prijs = prijs;

    }

    public Set<Zone> getZones() {

        return zones;

    }

    public void setZones(Set<Zone> zones) {

        this.zones = zones;

    }

}
