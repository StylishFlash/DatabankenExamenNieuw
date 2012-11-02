package model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_zetel")
public class Zetel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int nummer;

    @Column
    private boolean gereserveerd;

    @OneToOne
    private Ticket ticket;

    @ManyToOne
    private Zone zone;

    private Zetel() {

        this(0, false, null);

    }

    public Zetel(int nummer, boolean gereserveerd, Zone zone) {

        this.nummer = nummer;
        this.gereserveerd = gereserveerd;
        this.zone = zone;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public int getNummer() {

        return nummer;

    }

    public void setNummer(int nummer) {

        this.nummer = nummer;

    }

    public boolean isGereserveerd() {

        return gereserveerd;

    }

    public void setGereserveerd(boolean gereserveerd) {

        this.gereserveerd = gereserveerd;

    }

    public Ticket getTicket() {

        return ticket;

    }

    public void setTicket(Ticket ticket) {

        this.ticket = ticket;

    }

    public Zone getZone() {

        return zone;

    }

    public void setZone(Zone zone) {

        this.zone = zone;

    }

}
