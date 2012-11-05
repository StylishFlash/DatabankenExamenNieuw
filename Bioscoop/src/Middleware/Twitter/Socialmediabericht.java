package Middleware.Twitter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 4/11/12
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */

// POJO klasse

public class Socialmediabericht {

    String sociaalnetwerk;
    String inhoud;
    Date tijdstip;
    String film;
    String cinemacomplex;

    public String getSociaalnetwerk() {

        return sociaalnetwerk;

    }

    public void setSociaalnetwerk(String sociaalnetwerk) {

        this.sociaalnetwerk = sociaalnetwerk;

    }

    public String getInhoud() {

        return inhoud;

    }

    public void setInhoud(String inhoud) {

        this.inhoud = inhoud;

    }

    public Date getTijdstip() {

        return tijdstip;

    }

    public void setTijdstip(Date tijdstip) {

        this.tijdstip = tijdstip;

    }

    public String getFilm() {

        return film;

    }

    public void setFilm(String film) {

        this.film = film;

    }

    public String getCinemacomplex() {

        return cinemacomplex;

    }

    public void setCinemacomplex(String cinemacomplex) {

        this.cinemacomplex = cinemacomplex;

    }

}
