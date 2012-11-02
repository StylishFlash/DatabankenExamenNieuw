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
@Table(name = "t_zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ZoneType zoneType;

    @OneToMany(mappedBy = "zone")
    private Set<Zetel> zetels = new HashSet<Zetel>();

    @ManyToOne
    private Zaal zaal;

    private Zone() {

        this(null, null);

    }

    public Zone(ZoneType zoneType, Zaal zaal) {

        this.zoneType = zoneType;
        this.zaal = zaal;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public ZoneType getZoneType() {

        return zoneType;

    }

    public void setZoneType(ZoneType zoneType) {

        this.zoneType = zoneType;

    }

    public Set<Zetel> getZetels() {

        return zetels;

    }

    public void setZetels(Set<Zetel> zetels) {

        this.zetels = zetels;

    }

    public void addZetel(Zetel zetel) {

        zetels.add(zetel);

    }

    public void removeZetel(Zetel zetel) {

        zetels.remove(zetel);

    }

    public Zaal getZaal() {

        return zaal;

    }

    public void setZaal(Zaal zaal) {

        this.zaal = zaal;

    }

}
