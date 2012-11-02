package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int barcode;

    @Column
    private Date datum;

    @Column
    private String tarief;

    @ManyToOne
    private Verkoop verkoop;

    @OneToOne
    private Zetel zetel;

    @ManyToOne
    private Vertoning vertoning;

    private Ticket() {

        this(0, null, null, null, null);

    }

    public Ticket(int barcode, Date datum, String tarief, Verkoop verkoop, Vertoning vertoning) {

        this.barcode = barcode;
        this.datum = datum;
        this.tarief = tarief;
        this.verkoop = verkoop;
        this.vertoning = vertoning;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public int getBarcode() {

        return barcode;

    }

    public void setBarcode(int barcode) {

        this.barcode = barcode;

    }

    public Date getDatum() {

        return datum;

    }

    public void setDatum(Date datum) {

        this.datum = datum;

    }

    public String getTarief() {

        return tarief;

    }

    public void setTarief(String tarief) {

        this.tarief = tarief;

    }

    public Verkoop getVerkoop() {

        return verkoop;

    }

    public void setVerkoop(Verkoop verkoop) {

        this.verkoop = verkoop;

    }

    public Zetel getZetel() {

        return zetel;

    }

    public void setZetel(Zetel zetel) {

        this.zetel = zetel;

    }

    public Vertoning getVertoning() {

        return vertoning;

    }

    public void setVertoning(Vertoning vertoning) {

        this.vertoning = vertoning;

    }

}
