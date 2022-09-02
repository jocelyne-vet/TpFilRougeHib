INSERT INTO personnes ( nom,            prenom,     id_adresse,                            mdp,    role            ,email                     , date_naissance) VALUES
                      ( 'berger',       'michel',   		3,  				        'pass', 'Client'			          ,'miche.berger@test.fr'    , '2012-01-01'),
                      ( 'brassens',     'georges',  		4,               			'pass', 'Administrateur'					  ,'georges.brassens@free.fr', '1950-01-01'),
                      ( 'clerc',        'julien',   		5,    						'pass', 'Gerant'        			  ,'julien.clerc@free.fr'    , '1970-01-01'),
                      ( 'francois',     'claude',   		6,        					'pass', 'Client'        			  ,'claude.francois@free.fr' ,  '1960-02-02'),
                      ( 'sebastien',    'patrick',  		7,          				'pass', 'Client'        			  ,'patrick.sebastien@free.fr', '2010-01-01'),
                      ( 'bruel',        'patrick',  		8,         					'pass', 'Gerant'        			  ,'patrick.bruel@free.fr'    , '1970-01-01'),
                      ( 'aznavour',     'charles',  		9,           				'pass', 'Client'        			  ,'charles.aznavour@free.fr' ,  '1970-02-03');

INSERT INTO cinemas (   id_adresse,                    id_gerant,  nom, affiche ) VALUES
                    (   1,    3,           'Cinema artemont', 'https://i.pinimg.com/originals/23/bb/d9/23bbd9ad07d0a3bb038fd29dab46c9a1.png'),
                    (   2,      6,           'Cinema Arlety' , 'https://www.saintquayportrieux.com/wp-content/uploads/2017/03/cinema_arletty_saint_quay_portrieux.jpg');

INSERT INTO salles  (   id_cinema,  numero,     nb_places) VALUES
                    (   1,          1,          120      ),
                    (   1,          2,          120      ),
                    (   1,          3,          80       ),
                    (   2,          1,          150      ),
                    (   2,          2,          140      ),
                    (   2,          3,          140      ),
                    (   2,          4,          100      ),
                    (   2,          5,          100      ),
                    (   2,          6,          80       ),
                    (   2,          7,          80       );

INSERT INTO films   (   titre,                                      description,                                                    duree,  age_minimum , affiche) VALUES
                    (   'Star Wars',                                'Une trilogie sur la guerre des etoiles',                       360,    12          ,'https://th.bing.com/th/id/OIP.4JVJiq5HuQlkBgsXkI3QPAHaKf?pid=ImgDet&rs=1'),
                    (   'Le seigneur des anneaux',                  'Une trilogie qui parle d''anneaux',                            540,    14          ,'https://th.bing.com/th/id/R.195953ce05359b5230e6359daab8fec1?rik=JIc0LQBbOAntPw&pid=ImgRaw&r=0'),
                    (   'La cite de la peur',                       'Un film humoristique au festival de Cannes',                   120,    6           ,'https://www.american-cosmograph.fr/images/stories/flexicontent/item_1022_field_25/l_la-cite-de-la-peur-affiche.jpg'),
                    (   'Le grand blond avec une chaussure noire',  'Un film humoristique sur un musicien et des agents secrets',   120,    6           ,'https://th.bing.com/th/id/R.10115478c6409036907da1f7b4fec6db?rik=mkyJfJRTnWKb1Q&pid=ImgRaw&r=0'),
                    (   'Retour vers le futur',                     'Une trilogie sur une Dolorean de compet''',                    360,    8           ,'https://th.bing.com/th/id/OIP.qRfNUWNWTS6WwOmRmN834AHaLH?pid=ImgDet&rs=1'),
                    (   'Coco',                                     'Un dessin animé sur les Cubanistos',                           90,     0           ,'https://th.bing.com/th/id/OIP.YoqGIaL2G9g4liOWCGFr3wHaKC?pid=ImgDet&rs=1');

INSERT INTO seances (   id_film,    id_salle,   date_seance,    heure       ) VALUES
                    (   1,          1,          '2022-10-20',   '18:00:00'  ),
                    (   1,          1,          '2022-10-20',   '8:00:00'   ),
                    (   1,          1,          '2022-10-21',   '18:00:00'  ),
                    (   1,          1,          '2022-10-21',   '8:00:00'   ),
                    (   1,          1,          '2022-10-22',   '18:00:00'  ),
                    (   1,          1,          '2022-10-22',   '8:00:00'   ),
                    (   2,          1,          '2022-10-23',   '18:00:00'  ),
                    (   2,          1,          '2022-10-23',   '8:00:00'   ),
                    (   2,          1,          '2022-10-24',   '18:00:00'  ),
                    (   2,          1,          '2022-10-24',   '8:00:00'   ),
                    (   2,          1,          '2022-10-25',   '18:00:00'  ),
                    (   2,          1,          '2022-10-25',   '8:00:00'   ),
                    (   3,          3,          '2022-10-20',   '12:00:00'  ),
                    (   3,          3,          '2022-10-20',   '14:30:00'  ),
                    (   3,          3,          '2022-10-20',   '17:00:00'  ),
                    (   3,          3,          '2022-10-20',   '19:30:00'  ),
                    (   3,          3,          '2022-10-20',   '22:00:00'  ),				
					(   4,          4,          '2022-10-20',   '12:00:00'  ),
                    (   4,          4,          '2022-10-20',   '14:30:00'  ),
                    (   4,          4,          '2022-10-20',   '17:00:00'  ),
                    (   4,          4,          '2022-10-20',   '19:30:00'  ),
                    (   4,          4,          '2022-10-20',   '22:00:00'  ),
					(   5,          2,          '2022-10-20',   '12:00:00'  ),
                    (   5,          2,          '2022-10-20',   '14:30:00'  ),
                    (   5,          2,          '2022-10-20',   '17:00:00'  ),
                    (   5,          2,          '2022-10-20',   '19:30:00'  ),
                    (   5,          2,          '2022-10-20',   '22:00:00'  ),
					(   6,          5,          '2022-10-20',   '12:00:00'  ),
                    (   6,          5,          '2022-10-20',   '14:30:00'  ),
                    (   6,          5,          '2022-10-20',   '17:00:00'  ),
                    (   6,          5,          '2022-10-20',   '19:30:00'  ),
                    (   6,          5,          '2022-10-20',   '22:00:00'  );

INSERT INTO reservations    (   id_seance,  id_personne,    nb_places   ) VALUES
                            (   1,          1,              3           ),
                            (   1,          3,              5           ),
                            (   1,          5,              1           ),
                            (   1,          7,              2           ),
                            (   7,          1,              3           ),
                            (   7,          3,              5           ),
                            (   7,          5,              1           ),
                            (   7,          7,              2           ),
                            (   13,         1,              1           ),
                            (   13,         5,              1           );

insert into adresses (numero_voie, type_voie, nom_voie           , code_postal, ville)VALUES
					 ( 33        , 'rue'     ,'Charles Xavier'    , 44000      , 'NANTES'  ),
					 (  1        , 'rue'     ,'Obiwan Kenobi'     , 35000      , 'RENNES'  ),
					 (6          , 'rue'     ,'General de Gaulles', 44000      , 'NANTES'  ),
					 (42         , 'rue'     , 'du Calvaire'      , 44000      , 'NANTES'  ),
					 (17         ,'avenue'   , 'Charles Gauthier' , 44000      , 'NANTES'  ),
					 (28         , 'impasse' ,'du grille-pain'    , 35000      , 'RENNES'  ),
					 (312        , 'boulevard', 'de Doulon'       , 35000      , 'RENNES'  ),
					 (29         , 'boulevard', 'du Massacre'     , 35000      , 'RENNES'  ),
					 (24         , 'avenue'   ,  'de la Boheme'   , 44000      ,  'NANTES' );
					 
					 
insert into roles    ( libelle) VALUES
					 ( 'client'),
                     ('gerant'),
                     ('administrateur');					 