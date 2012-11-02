package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_medewerker")
public class Medewerker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naam;

    @Column
    private Date geboorteDatum;

    @Column
    private String land;

    @OneToMany(mappedBy = "medewerker")
    private Set<Rol> rollen = new HashSet<Rol>();

    private Medewerker() {

        this(null, null, null);

    }

    public Medewerker(String naam, Date geboorteDatum, String land) {

        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
        this.land = land;

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

    public Date getGeboorteDatum() {

        return geboorteDatum;

    }

    public void setGeboorteDatum(Date geboorteDatum) {

        this.geboorteDatum = geboorteDatum;

    }

    public String getLand() {

        return land;

    }

    public void setLand(String land) {

        this.land = land;

    }

    public Set<Rol> getRollen() {

        return rollen;

    }

    public void setRollen(Set<Rol> rollen) {

        this.rollen = rollen;

    }

}
