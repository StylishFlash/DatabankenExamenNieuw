package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 31/10/12
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String titel;

    @Column
    private String genre;

    @Column
    private String inhoud;

    @Column
    private int speelduur;

    @Column
    private String land;

    @Column
    private int minimumLeeftijd;

    @Column
    private boolean wordtVertoond;

    @ManyToOne
    private Bioscoopholding bioscoopholding;

    @OneToMany(mappedBy = "film")
    private Set<Vertoning> vertoningen = new HashSet<Vertoning>();

    @OneToMany(mappedBy = "film")
    private Set<SocialmediaBericht> socialmediaBerichten = new HashSet<SocialmediaBericht>();

    @OneToMany(mappedBy = "film")
    private Set<Rol> rollen = new HashSet<Rol>();

    @OneToMany(mappedBy = "film")
    private Set<Oscar> oscars = new HashSet<Oscar>();

    @OneToMany(mappedBy = "film")
    private Set<Waardering> waarderingen = new HashSet<Waardering>();

    private Film() {

        this(null, null, null, 0, null, 0, false);

    }

    public Film(String titel, String genre, String inhoud, int speelduur, String land, int minimumLeeftijd, boolean wordtVertoond) {

        this.titel = titel;
        this.genre = genre;
        this.inhoud = inhoud;
        this.speelduur = speelduur;
        this.land = land;
        this.minimumLeeftijd = minimumLeeftijd;
        this.wordtVertoond = wordtVertoond;

    }

    public Integer getId() {

        return id;

    }

    private void setId(Integer id) {

        this.id = id;

    }

    public String getTitel() {

        return titel;

    }

    public void setTitel(String titel) {

        this.titel = titel;

    }

    public String getGenre() {

        return genre;

    }

    public void setGenre(String genre) {

        this.genre = genre;

    }

    public String getInhoud() {

        return inhoud;

    }

    public void setInhoud(String inhoud) {

        this.inhoud = inhoud;

    }

    public int getSpeelduur() {

        return speelduur;

    }

    public void setSpeelduur(int speelduur) {

        this.speelduur = speelduur;

    }

    public String getLand() {

        return land;

    }

    public void setLand(String land) {

        this.land = land;

    }

    public int getMinimumLeeftijd() {

        return minimumLeeftijd;

    }

    public void setMinimumLeeftijd(int minimumLeeftijd) {

        this.minimumLeeftijd = minimumLeeftijd;

    }

    public boolean isWordtVertoond() {

        return wordtVertoond;

    }

    public void setWordtVertoond(boolean wordtVertoond) {

        this.wordtVertoond = wordtVertoond;

    }

    public Bioscoopholding getBioscoopholding() {

        return bioscoopholding;

    }

    public void setBioscoopholding(Bioscoopholding bioscoopholding) {

        this.bioscoopholding = bioscoopholding;

    }

    public Set<Vertoning> getVertoningen() {

        return vertoningen;

    }

    public void setVertoningen(Set<Vertoning> vertoningen) {

        this.vertoningen = vertoningen;

    }

    public void addVertooning(Vertoning vertoning) {

        vertoningen.add(vertoning);

    }

    public void removeVertooning(Vertoning vertoning) {

        vertoningen.remove(vertoning);

    }

    public Set<SocialmediaBericht> getSocialmediaBerichten() {

        return socialmediaBerichten;

    }

    public void setSocialmediaBerichten(Set<SocialmediaBericht> socialmediaBerichten) {

        this.socialmediaBerichten = socialmediaBerichten;

    }

    public void addSocialmediaBericht(SocialmediaBericht socialmediaBericht) {

        socialmediaBerichten.add(socialmediaBericht);

    }

    public void removeSocialmediaBericht(SocialmediaBericht socialmediaBericht) {

        socialmediaBerichten.remove(socialmediaBericht);

    }

    public Set<Rol> getRollen() {

        return rollen;

    }

    public void setRollen(Set<Rol> rollen) {

        this.rollen = rollen;

    }

    public void addRol(Rol rol) {

        rollen.add(rol);

    }

    public void removeRoll(Rol rol) {

        rollen.remove(rol);

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

    public Set<Waardering> getWaarderingen() {

        return waarderingen;

    }

    public void setWaarderingen(Set<Waardering> waarderingen) {

        this.waarderingen = waarderingen;

    }

    public void addWaardering(Waardering waardering) {

        waarderingen.add(waardering);

    }

    public void removeWaardering(Waardering waardering) {

        waarderingen.remove(waardering);

    }

}
