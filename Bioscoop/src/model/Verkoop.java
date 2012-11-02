package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_verkoop")
public class Verkoop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String verkoopPunt;

    @ManyToOne
    private Cinemacomplex cinemacomplex;

    @OneToMany(mappedBy = "verkoop")
    private Set<Ticket> tickets = new HashSet<Ticket>();

    @ManyToOne
    private Klant klant;

    @OneToMany(mappedBy = "verkoop")
    private Set<Tienbeurtenkaart> tienbeurtenkaarten = new HashSet<Tienbeurtenkaart>();

    private Verkoop() {

        this(null, null);

    }

    public Verkoop(String verkoopPunt, Cinemacomplex cinemacomplex) {

        this.verkoopPunt = verkoopPunt;
        this.cinemacomplex = cinemacomplex;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public String getVerkoopPunt() {

        return verkoopPunt;

    }

    public void setVerkoopPunt(String verkoopPunt) {

        this.verkoopPunt = verkoopPunt;

    }

    public Cinemacomplex getCinemacomplex() {

        return cinemacomplex;

    }

    public void setCinemacomplex(Cinemacomplex cinemacomplex) {

        this.cinemacomplex = cinemacomplex;

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

    public Klant getKlant() {

        return klant;

    }

    public void setKlant(Klant klant) {

        this.klant = klant;

    }

    public Set<Tienbeurtenkaart> getTienbeurtenkaarten() {

        return tienbeurtenkaarten;

    }

    public void setTienbeurtenkaarten(Set<Tienbeurtenkaart> tienbeurtenkaarten) {

        this.tienbeurtenkaarten = tienbeurtenkaarten;

    }

    public void addTienbeurtenkaart(Tienbeurtenkaart tienbeurtenkaart) {

        tienbeurtenkaarten.add(tienbeurtenkaart);

    }

    public void removeTienbeurtenkaart(Tienbeurtenkaart tienbeurtenkaart) {

        tienbeurtenkaarten.remove(tienbeurtenkaart);

    }


}
