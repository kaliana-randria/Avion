-- CREATE DATABASE gestion_avion2;
-- use gestion_avion2;

CREATE TABLE compagnie (
    id_compagnie SERIAL PRIMARY KEY,
    nom_compagnie VARCHAR(100) NOT NULL
); 

INSERT INTO compagnie (nom_compagnie) VALUES
('Air Madagascar'),
('Air France'),
('Turkish Airlines'),
('Ethiopian Airlines'),
('Corsair');

CREATE TABLE avion (
    id_avion SERIAL PRIMARY KEY,
    id_compagnie INT NOT NULL,
    nom_avion VARCHAR(100) NOT NULL,
    modele VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_compagnie) REFERENCES compagnie(id_compagnie)
); 

INSERT INTO avion (id_compagnie, nom_avion, modele) VALUES
(1, 'Tsaradia 101', 'ATR 72'),
(2, 'Airbus A320 AF', 'A320'),
(3, 'Boeing 737 Turkish', 'B737'),
(4, 'Dreamliner 787', 'B787'),
(5, 'Corsair 900', 'A330');

CREATE TABLE statut_vol (
    id_statut_vol SERIAL PRIMARY KEY,
    statut VARCHAR(20) NOT NULL
); 

INSERT INTO statut_vol (statut) VALUES
('prevu'),
('en vol'),
('termine'),
('annule');

CREATE TABLE vol (
    id_vol SERIAL PRIMARY KEY,
    id_avion INT,
    ville_depart VARCHAR(100),
    ville_arrivee VARCHAR(100),
    date_depart TIMESTAMP,
    date_arrivee TIMESTAMP,
    duree INT,
    id_statut_vol INT DEFAULT 1,
    FOREIGN KEY (id_avion) REFERENCES avion(id_avion),
    FOREIGN KEY (id_statut_vol) REFERENCES statut_vol(id_statut_vol)
); 

CREATE TABLE classe (
    id_classe SERIAL PRIMARY KEY,
    nom_classe VARCHAR(50)
); 

INSERT INTO classe (nom_classe) VALUES
('Economique'),
('Business'),
('First'),
('VIP');

CREATE TABLE classe_vol (
    id_classe_vol SERIAL PRIMARY KEY,
    id_vol INT,
    id_classe INT,
    nbr_place INT,
    FOREIGN KEY (id_vol) REFERENCES vol(id_vol),
    FOREIGN KEY (id_classe) REFERENCES classe(id_classe)
); 

INSERT INTO classe_vol (id_vol, id_classe, nbr_place) VALUES
(1, 1, 100),
(1, 2, 20),

(3, 1, 50),
(3, 2, 15);

-- (4, 1, 100),
-- (4, 2, 25);

CREATE TABLE param_vol (
    id_param_vol SERIAL PRIMARY KEY,
    id_classe_vol INT,
    prix DECIMAL(10,2),
    quantite INT,
    date_limite_paiement TIMESTAMP,
    en_cours BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_classe_vol) REFERENCES classe_vol(id_classe_vol)
); 

INSERT INTO param_vol (id_classe_vol, prix, quantite, date_limite_paiement) VALUES
(1, 100, 10, '2025-06-27 07:00:00'),
(1, 150, 20, '2025-06-28 00:00:00'),
(1, 200, 15, '2025-06-29 07:00:00'),
(1, 300, 30, '2025-06-30 07:00:00'),
(1, 350, 10, '2025-07-01 07:00:00'),
(1, 400, 15, '2025-07-02 07:00:00'),

(2, 150, 5, '2025-06-27 07:00:00'),
(2, 300, 15, '2025-06-28 00:00:00'),

(5, 100, 10, '2025-06-27 07:00:00'),
(5, 150, 20, '2025-06-28 00:00:00'),
(5, 200, 15, '2025-06-29 07:00:00'),
(5, 300, 5, '2025-06-30 07:00:00'),

(6, 150, 10, '2025-06-27 07:00:00'),
(6, 300, 5, '2025-06-28 00:00:00');

-- (7, 100, 10, '2025-07-10 07:00:00'),
-- (7, 150, 20, '2025-07-12 00:00:00'),
-- (7, 200, 15, '2025-07-14 07:00:00'),
-- (7, 300, 30, '2025-07-16 07:00:00'),
-- (7, 350, 10, '2025-07-18 07:00:00'),
-- (7, 400, 15, '2025-07-19 07:00:00'),

-- (8, 150, 5, '2025-07-10 07:00:00'),
-- (8, 300, 15, '2025-07-12 00:00:00'),
-- (8, 300, 5, '2025-07-14 00:00:00');

CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    date_reservation TIMESTAMP,
    id_param_vol INT,
    quantite INT,
    est_payer BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_param_vol) REFERENCES param_vol(id_param_vol)
); 

CREATE TABLE enregistrement_reservation (
    id_enregistrement SERIAL PRIMARY KEY,
    id_reservation INT,
    num_reference VARCHAR(10),
    est_annule BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_reservation) REFERENCES reservation(id_reservation)
);

CREATE TABLE admin (
    id_admin SERIAL PRIMARY KEY,
    nom VARCHAR(100),
    email VARCHAR(100),
    mdp VARCHAR(50)
); 

INSERT INTO admin (nom, email, mdp) VALUES
('Admin', 'admin@gmail.com', 'admin123');
