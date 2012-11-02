package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_vertoning")
public class Vertoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean is3D;

    @Column
    private boolean zetelReservatie;

    @Column
    private String taal;

    @Column
    private Date tijdstip;

    @ManyToOne
    private Zaal zaal;

    @ManyToOne
    private Film film;

    @OneToMany(mappedBy = "vertoning")
    private Set<Ticket> tickets = new HashSet<Ticket>();

    private Vertoning() {

        this(false, false, null, null, null, null);

    }

    public Vertoning(boolean is3D, boolean zetelReservatie, String taal, Date tijdstip, Zaal zaal, Film film) {

        this.is3D = is3D;
        this.zetelReservatie = zetelReservatie;
        this.taal = taal;
        this.tijdstip = tijdstip;
        this.zaal = zaal;
        this.film = film;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public boolean getIs3D() {

        return is3D;

    }

    public void setIs3D(boolean is3D) {

        this.is3D = is3D;

    }

    public boolean getZetelReservatie() {

        return zetelReservatie;

    }

    public void setZetelReservatie(boolean zetelReservatie) {

        this.zetelReservatie = zetelReservatie;

    }

    public String getTaal() {

        return taal;

    }

    public void setTaal(String taal) {

        this.taal = taal;

    }

    public Date getTijdstip() {

        return tijdstip;

    }

    public void setTijdstip(Date tijdstip) {

        this.tijdstip = tijdstip;

    }

    public Zaal getZaal() {

        return zaal;

    }

    public void setZaal(Zaal zaal) {

        this.zaal = zaal;

    }

    public Film getFilm() {

        return film;

    }

    public void setFilm(Film film) {

        this.film = film;

    }

    public Set<Ticket> getTickets() {

        return tickets;

    }

    public void setTickets(Set<Ticket> tickets) {

        this.tickets = tickets;

    }

    public void addTicket(Ticket ticket) {

        tickets.add(ticket);

    }

    public void removeTicket(Ticket ticket) {

        tickets.remove(ticket);

    }

}
