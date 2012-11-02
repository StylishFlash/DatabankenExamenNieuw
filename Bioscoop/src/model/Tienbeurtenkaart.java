package model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_tienbeurtenkaart")
public class Tienbeurtenkaart {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private double prijs;

    @ManyToOne
    private Verkoop verkoop;

    private Tienbeurtenkaart() {

        this(0, null);

    }

    public Tienbeurtenkaart(double prijs, Verkoop verkoop) {

        this.prijs = prijs;
        this.verkoop = verkoop;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public double getPrijs() {

        return prijs;

    }

    public void setPrijs(double prijs) {

        this.prijs = prijs;

    }

    public Verkoop getVerkoop() {

        return verkoop;

    }

    public void setVerkoop(Verkoop verkoop) {

        this.verkoop = verkoop;

    }

}
