
    alter table t_cinemacomplex 
        drop 
        foreign key FKFE9D6814E8D420F5

    alter table t_film 
        drop 
        foreign key FKCB5CD66FE8D420F5

    alter table t_oscar 
        drop 
        foreign key FKA0C133E550ED9AF5

    alter table t_oscar 
        drop 
        foreign key FKA0C133E547BE96FF

    alter table t_rol 
        drop 
        foreign key FK68F8F84E60B1E9F

    alter table t_rol 
        drop 
        foreign key FK68F8F8447BE96FF

    alter table t_socialmediabericht 
        drop 
        foreign key FK6A5C45E947BE96FF

    alter table t_socialmediabericht 
        drop 
        foreign key FK6A5C45E94011E335

    alter table t_socialmediabericht 
        drop 
        foreign key FK6A5C45E91F767AF5

    alter table t_ticket 
        drop 
        foreign key FK7F60BD371F40FD5

    alter table t_ticket 
        drop 
        foreign key FK7F60BD37866CC95

    alter table t_ticket 
        drop 
        foreign key FK7F60BD3758B422D5

    alter table t_tienbeurtenkaart 
        drop 
        foreign key FKB9F272B958B422D5

    alter table t_verkoop 
        drop 
        foreign key FKD083839D43C6B4D5

    alter table t_verkoop 
        drop 
        foreign key FKD083839D4011E335

    alter table t_vertoning 
        drop 
        foreign key FKCD0C2D075B81F43F

    alter table t_vertoning 
        drop 
        foreign key FKCD0C2D0747BE96FF

    alter table t_waardering 
        drop 
        foreign key FK14B09B1743C6B4D5

    alter table t_waardering 
        drop 
        foreign key FK14B09B1747BE96FF

    alter table t_zaal 
        drop 
        foreign key FKCB65CE7D4011E335

    alter table t_zaal_t_zone 
        drop 
        foreign key FKE0DF5C991713E774

    alter table t_zaal_t_zone 
        drop 
        foreign key FKE0DF5C99AD578D84

    alter table t_zetel 
        drop 
        foreign key FKA1561985741A05FF

    alter table t_zetel 
        drop 
        foreign key FKA1561985A6EA6CBF

    alter table t_zone 
        drop 
        foreign key FKCB6604979EC9CD5F

    alter table t_zone 
        drop 
        foreign key FKCB6604975B81F43F

    drop table if exists t_bioscoopholding

    drop table if exists t_cinemacomplex

    drop table if exists t_film

    drop table if exists t_klant

    drop table if exists t_medewerker

    drop table if exists t_oscar

    drop table if exists t_rol

    drop table if exists t_socialmedia

    drop table if exists t_socialmediabericht

    drop table if exists t_ticket

    drop table if exists t_tienbeurtenkaart

    drop table if exists t_verkoop

    drop table if exists t_vertoning

    drop table if exists t_waardering

    drop table if exists t_zaal

    drop table if exists t_zaal_t_zone

    drop table if exists t_zetel

    drop table if exists t_zone

    drop table if exists t_zonetype

    create table t_bioscoopholding (
        id integer not null auto_increment,
        adres varchar(255),
        btwNummer varchar(255),
        naam varchar(255),
        primary key (id)
    ) ENGINE=InnoDB

    create table t_cinemacomplex (
        id integer not null auto_increment,
        adres varchar(255),
        naam varchar(255),
        telefoonNummer integer,
        bioscoopholding_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_film (
        id integer not null auto_increment,
        genre varchar(255),
        inhoud varchar(255),
        land varchar(255),
        minimumLeeftijd integer,
        speelduur integer,
        titel varchar(255),
        wordtVertoond boolean,
        bioscoopholding_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_klant (
        id integer not null auto_increment,
        adres varchar(255),
        naam varchar(255),
        primary key (id)
    ) ENGINE=InnoDB

    create table t_medewerker (
        id integer not null auto_increment,
        geboorteDatum datetime,
        land varchar(255),
        naam varchar(255),
        primary key (id)
    ) ENGINE=InnoDB

    create table t_oscar (
        id integer not null auto_increment,
        categorie varchar(255),
        jaar integer,
        film_id integer,
        rol_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_rol (
        id integer not null auto_increment,
        beschrijving varchar(255),
        film_id integer,
        medewerker_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_socialmedia (
        id integer not null auto_increment,
        naam varchar(255),
        primary key (id)
    ) ENGINE=InnoDB

    create table t_socialmediabericht (
        id integer not null auto_increment,
        inhoud varchar(255),
        tijdstip datetime,
        cinemacomplex_id integer,
        film_id integer,
        socialmedia_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_ticket (
        id integer not null auto_increment,
        barcode integer,
        datum datetime,
        tarief varchar(255),
        verkoop_id integer,
        vertoning_id integer,
        zetel_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_tienbeurtenkaart (
        id integer not null auto_increment,
        prijs double precision,
        verkoop_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_verkoop (
        id integer not null auto_increment,
        verkoopPunt varchar(255),
        cinemacomplex_id integer,
        klant_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_vertoning (
        id integer not null auto_increment,
        is3D boolean,
        taal varchar(255),
        tijdstip datetime,
        zetelReservatie boolean,
        film_id integer,
        zaal_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_waardering (
        id integer not null auto_increment,
        Score integer,
        datum datetime,
        inhoud varchar(255),
        film_id integer,
        klant_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_zaal (
        id integer not null auto_increment,
        nummer integer,
        cinemacomplex_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_zaal_t_zone (
        t_zaal_id integer not null,
        zones_id integer not null,
        primary key (t_zaal_id, zones_id),
        unique (zones_id)
    ) ENGINE=InnoDB

    create table t_zetel (
        id integer not null auto_increment,
        gereserveerd boolean,
        nummer integer,
        ticket_id integer,
        zone_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_zone (
        id integer not null auto_increment,
        zaal_id integer,
        zoneType_id integer,
        primary key (id)
    ) ENGINE=InnoDB

    create table t_zonetype (
        id integer not null auto_increment,
        naam varchar(255),
        prijs double precision,
        primary key (id)
    ) ENGINE=InnoDB

    alter table t_cinemacomplex 
        add index FKFE9D6814E8D420F5 (bioscoopholding_id), 
        add constraint FKFE9D6814E8D420F5 
        foreign key (bioscoopholding_id) 
        references t_bioscoopholding (id)

    alter table t_film 
        add index FKCB5CD66FE8D420F5 (bioscoopholding_id), 
        add constraint FKCB5CD66FE8D420F5 
        foreign key (bioscoopholding_id) 
        references t_bioscoopholding (id)

    alter table t_oscar 
        add index FKA0C133E550ED9AF5 (rol_id), 
        add constraint FKA0C133E550ED9AF5 
        foreign key (rol_id) 
        references t_rol (id)

    alter table t_oscar 
        add index FKA0C133E547BE96FF (film_id), 
        add constraint FKA0C133E547BE96FF 
        foreign key (film_id) 
        references t_film (id)

    alter table t_rol 
        add index FK68F8F84E60B1E9F (medewerker_id), 
        add constraint FK68F8F84E60B1E9F 
        foreign key (medewerker_id) 
        references t_medewerker (id)

    alter table t_rol 
        add index FK68F8F8447BE96FF (film_id), 
        add constraint FK68F8F8447BE96FF 
        foreign key (film_id) 
        references t_film (id)

    alter table t_socialmediabericht 
        add index FK6A5C45E947BE96FF (film_id), 
        add constraint FK6A5C45E947BE96FF 
        foreign key (film_id) 
        references t_film (id)

    alter table t_socialmediabericht 
        add index FK6A5C45E94011E335 (cinemacomplex_id), 
        add constraint FK6A5C45E94011E335 
        foreign key (cinemacomplex_id) 
        references t_cinemacomplex (id)

    alter table t_socialmediabericht 
        add index FK6A5C45E91F767AF5 (socialmedia_id), 
        add constraint FK6A5C45E91F767AF5 
        foreign key (socialmedia_id) 
        references t_socialmedia (id)

    alter table t_ticket 
        add index FK7F60BD371F40FD5 (vertoning_id), 
        add constraint FK7F60BD371F40FD5 
        foreign key (vertoning_id) 
        references t_vertoning (id)

    alter table t_ticket 
        add index FK7F60BD37866CC95 (zetel_id), 
        add constraint FK7F60BD37866CC95 
        foreign key (zetel_id) 
        references t_zetel (id)

    alter table t_ticket 
        add index FK7F60BD3758B422D5 (verkoop_id), 
        add constraint FK7F60BD3758B422D5 
        foreign key (verkoop_id) 
        references t_verkoop (id)

    alter table t_tienbeurtenkaart 
        add index FKB9F272B958B422D5 (verkoop_id), 
        add constraint FKB9F272B958B422D5 
        foreign key (verkoop_id) 
        references t_verkoop (id)

    alter table t_verkoop 
        add index FKD083839D43C6B4D5 (klant_id), 
        add constraint FKD083839D43C6B4D5 
        foreign key (klant_id) 
        references t_klant (id)

    alter table t_verkoop 
        add index FKD083839D4011E335 (cinemacomplex_id), 
        add constraint FKD083839D4011E335 
        foreign key (cinemacomplex_id) 
        references t_cinemacomplex (id)

    alter table t_vertoning 
        add index FKCD0C2D075B81F43F (zaal_id), 
        add constraint FKCD0C2D075B81F43F 
        foreign key (zaal_id) 
        references t_zaal (id)

    alter table t_vertoning 
        add index FKCD0C2D0747BE96FF (film_id), 
        add constraint FKCD0C2D0747BE96FF 
        foreign key (film_id) 
        references t_film (id)

    alter table t_waardering 
        add index FK14B09B1743C6B4D5 (klant_id), 
        add constraint FK14B09B1743C6B4D5 
        foreign key (klant_id) 
        references t_klant (id)

    alter table t_waardering 
        add index FK14B09B1747BE96FF (film_id), 
        add constraint FK14B09B1747BE96FF 
        foreign key (film_id) 
        references t_film (id)

    alter table t_zaal 
        add index FKCB65CE7D4011E335 (cinemacomplex_id), 
        add constraint FKCB65CE7D4011E335 
        foreign key (cinemacomplex_id) 
        references t_cinemacomplex (id)

    alter table t_zaal_t_zone 
        add index FKE0DF5C991713E774 (t_zaal_id), 
        add constraint FKE0DF5C991713E774 
        foreign key (t_zaal_id) 
        references t_zaal (id)

    alter table t_zaal_t_zone 
        add index FKE0DF5C99AD578D84 (zones_id), 
        add constraint FKE0DF5C99AD578D84 
        foreign key (zones_id) 
        references t_zone (id)

    alter table t_zetel 
        add index FKA1561985741A05FF (zone_id), 
        add constraint FKA1561985741A05FF 
        foreign key (zone_id) 
        references t_zone (id)

    alter table t_zetel 
        add index FKA1561985A6EA6CBF (ticket_id), 
        add constraint FKA1561985A6EA6CBF 
        foreign key (ticket_id) 
        references t_ticket (id)

    alter table t_zone 
        add index FKCB6604979EC9CD5F (zoneType_id), 
        add constraint FKCB6604979EC9CD5F 
        foreign key (zoneType_id) 
        references t_zonetype (id)

    alter table t_zone 
        add index FKCB6604975B81F43F (zaal_id), 
        add constraint FKCB6604975B81F43F 
        foreign key (zaal_id) 
        references t_zaal (id)
