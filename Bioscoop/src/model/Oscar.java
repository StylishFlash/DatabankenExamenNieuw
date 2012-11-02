package model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_oscar")
public class Oscar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int jaar;

    @Column
    private String categorie;

    @ManyToOne
    private Rol rol;

    @ManyToOne
    private Film film;

    private Oscar() {

        this(0, null);

    }

    public Oscar(int jaar, String categorie) {

        this.jaar = jaar;
        this.categorie = categorie;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public int getJaar() {

        return jaar;

    }

    public void setJaar(int jaar) {

        this.jaar = jaar;

    }

    public String getCategorie() {

        return categorie;

    }

    public void setCategorie(String categorie) {

        this.categorie = categorie;

    }

}
