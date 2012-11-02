package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_cinemacomplex")
public class Cinemacomplex {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naam;

    @Column
    private String adres;

    @Column
    private int telefoonNummer;

    @OneToMany(mappedBy = "cinemacomplex")
    private Set<SocialmediaBericht> socialmediaBerichten = new HashSet<SocialmediaBericht>();

    @OneToMany(mappedBy = "cinemacomplex")
    private Set<Zaal> zalen = new HashSet<Zaal>();

    @OneToMany(mappedBy = "cinemacomplex")
    private Set<Verkoop> verkopen = new HashSet<Verkoop>();

    @ManyToOne
    private Bioscoopholding bioscoopholding;

    private Cinemacomplex() {

        this(null, null, 0);

    }

    public Cinemacomplex(String naam, String adres, int telefoonNummer) {

        this.naam = naam;
        this.adres = adres;
        this.telefoonNummer = telefoonNummer;

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

    public int getTelefoonNummer() {

        return telefoonNummer;

    }

    public void setTelefoonNummer(int telefoonNummer) {

         this.telefoonNummer = telefoonNummer;

     }

    public Set<SocialmediaBericht> getSocialmediaBerichten() {

        return socialmediaBerichten;

    }

    public void setSocialmediaBerichten(Set<SocialmediaBericht> socialmediaBerichten) {

        this.socialmediaBerichten = socialmediaBerichten;

    }

    public Set<Zaal> getZalen() {

        return zalen;

    }

    public void setZalen(Set<Zaal> zalen) {

        this.zalen = zalen;

    }

    public Set<Verkoop> getVerkopen() {

        return verkopen;

    }

    public void setVerkopen(Set<Verkoop> verkopen) {

        this.verkopen = verkopen;

    }

    public Bioscoopholding getBioscoopholding() {

        return bioscoopholding;

    }

    public void setBioscoopholding(Bioscoopholding bioscoopholding) {

        this.bioscoopholding = bioscoopholding;

    }

}
