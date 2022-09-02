DROP TABLE IF EXISTS reservations, seances, films, salles, cinemas, personnes, adresses, roles;

CREATE TABLE cinemas (
    id          int             PRIMARY KEY     AUTO_INCREMENT  ,
	nom         varchar(50)     NOT NULL                        ,
    id_adresse  int             NOT NULL                        ,
    id_gerant   int             NOT NULL                        ,
	affiche     varchar(200)    NULL 
);

CREATE TABLE salles (
    id          int             PRIMARY KEY     AUTO_INCREMENT  ,
    id_cinema   int             NOT NULL                        ,
    numero      int             NOT NULL                        ,
    nb_places   int             NOT NULL
);

CREATE TABLE films (
    id          int             PRIMARY KEY     AUTO_INCREMENT  ,
    titre       varchar(50)     NOT NULL                        ,
    description varchar(5000)                                    ,
    duree       int             NOT NULL                        ,
    age_minimum int             NOT NULL                        ,
    affiche     varchar(200)
);

CREATE TABLE seances (
    id          int             PRIMARY KEY     AUTO_INCREMENT  ,
    id_film     int             NOT NULL                        ,
    id_salle    int             NOT NULL                        ,
    date_seance date            NOT NULL                        ,
    heure       time            NOT NULL                        ,
	nb_inscrits int             NOT NULL         Default 0
);

CREATE TABLE personnes (
    id          int             PRIMARY KEY     AUTO_INCREMENT  ,
    nom         varchar(50)     NOT NULL                        ,
    prenom      varchar(50)     NOT NULL                        ,
    date_naissance date         NOT NULL                        ,
    id_adresse  int             NOT NULL                        ,
	email       varchar(50)     NOT NULL                        ,
    mdp         varchar(50)     NOT NULL                        ,
	role        varchar(30)     NOT NULL                        
);

CREATE TABLE reservations (
    id          int             PRIMARY KEY     AUTO_INCREMENT  ,
    id_seance   int                                             ,
    id_personne int                                             ,
    nb_places   int
);

CREATE TABLE adresses (
	id 			int				PRIMARY KEY     AUTO_INCREMENT  ,
	numero_voie int             NOT NULL                        ,
    type_voie   varchar(30)     NOT NULL                        ,
    nom_voie    VARCHAR(100)    NOT NULL                        ,
    code_postal varchar(10)     NOT NULL	                    ,
	ville       VARCHAR(50)     NOT NULL
);	

/**CREATE TABLE roles (
	id          int             PRIMARY KEY     AUTO_INCREMENT  ,
	libelle    varchar(30)     NOT NULL    
);**/

/*ALTER TABLE personnes ADD CONSTRAINT fk_personnes_roles FOREIGN KEY (id_role) REFERENCES roles (id);*/
	
ALTER TABLE cinemas      ADD CONSTRAINT fk_cinemas_adresses FOREIGN KEY (id_adresse) REFERENCES adresses(id);
ALTER TABLE personnes      ADD CONSTRAINT fk_personnes_adresses FOREIGN KEY (id_adresse) REFERENCES adresses(id);

ALTER TABLE reservations ADD CONSTRAINT fk_reservations_seances FOREIGN KEY (id_seance) REFERENCES seances(id);
ALTER TABLE reservations ADD CONSTRAINT fk_reservations_personnes FOREIGN KEY (id_personne) REFERENCES personnes(id);
ALTER TABLE seances ADD CONSTRAINT fk_seances_films FOREIGN KEY (id_film) REFERENCES films(id) on DELETE CASCADE;
ALTER TABLE seances ADD CONSTRAINT fk_seances_salles FOREIGN KEY (id_salle) REFERENCES salles(id) ON DELETE CASCADE;
ALTER TABLE salles ADD CONSTRAINT fk_salles_cinemas FOREIGN KEY (id_cinema) REFERENCES cinemas(id);


ALTER TABLE cinemas ADD CONSTRAINT fk_cinemas_personnes FOREIGN KEY (id_gerant) REFERENCES personnes(id);

