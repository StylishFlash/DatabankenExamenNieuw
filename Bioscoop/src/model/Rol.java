package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String beschrijving;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Medewerker medewerker;

    @OneToMany(mappedBy = "rol")
    private Set<Oscar> oscars = new HashSet<Oscar>();

    private Rol() {

        this(null, null);

    }

    public Rol(String beschrijving, Film film) {

        this.beschrijving = beschrijving;
        this.film = film;

    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {

        this.id = id;

    }

    public String getBeschrijving() {

        return beschrijving;

    }

    public void setBeschrijving(String beschrijving) {

        this.beschrijving = beschrijving;

    }

    public Film getFilm() {

        return film;

    }

    public void setFilm(Film film) {

        this.film = film;

    }

    public Medewerker getMedewerker() {

        return medewerker;

    }

    public void setMedewerker(Medewerker medewerker) {

        this.medewerker = medewerker;

    }

    public Set<Oscar> getOscars() {

        return oscars;

    }

    public void setOscars(Set<Oscar> oscars) {

        this.oscars = oscars;

    }

    public void addOscar(Oscar oscar) {

        oscars.add(oscar);

    }

    public void removeOscar(Oscar oscar) {

        oscars.remove(oscar);

    }

}
