-- CREATE DATABASE gestion_avion;
-- use gestion_avion;

CREATE TABLE avions (
    id_avion SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(20) UNIQUE,
    modele VARCHAR(50),
    business_seats INT CHECK (business_seats >= 0),
    economy_seats INT CHECK (economy_seats >= 0)
); 
INSERT INTO avions (nom, code, modele, business_seats, economy_seats) VALUES
('Airbus A320', 'A320-01', 'A320', 20, 120),
('Boeing 737', 'B737-02', '737 Max', 18, 150);

CREATE TABLE statuts_vol (
    id_statut_vol SERIAL PRIMARY KEY,
    statut VARCHAR(20)
);
INSERT INTO statuts_vol (statut) VALUES 
('prévu'), ('en vol'), ('retardé'), ('annulé'), ('terminé');

CREATE TABLE vols (
    id_vol SERIAL PRIMARY KEY,
    numero_vol VARCHAR(10) NOT NULL,
    compagnie VARCHAR(100) NOT NULL,
    ville_depart VARCHAR(100) NOT NULL,
    ville_arrivee VARCHAR(100) NOT NULL,
    date_depart TIMESTAMP NOT NULL,
    date_arrivee TIMESTAMP NOT NULL,
    duree INT NOT NULL,
    id_statut_vol INT DEFAULT 1,
    id_avion INT NOT NULL,
    FOREIGN KEY (id_statut_vol) REFERENCES statuts_vol(id_statut_vol),
    FOREIGN KEY (id_avion) REFERENCES avions(id_avion)
); -- c'est crud cote admin donc dans code

CREATE TABLE classes (
    id_classe SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);
INSERT INTO classes (nom) VALUES 
('Economique'), ('Business');

CREATE TABLE tarifs (
    id_tarif SERIAL PRIMARY KEY,
    id_vol INT NOT NULL,
    id_classe INT NOT NULL,
    quantite INT NOT NULL CHECK (quantite > 0),
    prix DECIMAL(10,2) NOT NULL CHECK (prix >= 0),
    pourcentage INT CHECK (pourcentage BETWEEN 0 AND 100),
    date_limite_paiement DATE, 
    FOREIGN KEY (id_vol) REFERENCES vols(id_vol),
    FOREIGN KEY (id_classe) REFERENCES classes(id_classe)
); -- crud cote admin 

CREATE TABLE clients (
    id_client SERIAL PRIMARY KEY,
    nom_client VARCHAR(100) NOT NULL,
    email_client VARCHAR(100),
    mdp VARCHAR(100)
); 
INSERT INTO clients (nom_client, email_client, mdp) VALUES
('Jean Rakoto', 'jean@gmail.com', 'mdp123'),
('Soa Rami', 'soa@gmail.com', 'secret456');

CREATE TABLE statuts_reservation (
    id_statuts_reservation SERIAL PRIMARY KEY,
    statut VARCHAR(20)
);
INSERT INTO statuts_reservation (statut) VALUES 
('en attente'), ('payé'), ('expiré');

CREATE TABLE reservations (
    id_reservation SERIAL PRIMARY KEY, 
    id_vol INT NOT NULL,
    id_classe INT NOT NULL,
    id_client INT,
    quantite INT NOT NULL,
    id_statut_reservation INT DEFAULT 1, 
    date_reservation TIMESTAMP,
    FOREIGN KEY (id_vol) REFERENCES vols(id_vol),
    FOREIGN KEY (id_classe) REFERENCES classes(id_classe),
    FOREIGN KEY (id_client) REFERENCES clients(id_client),
    FOREIGN KEY (id_statut_reservation) REFERENCES statuts_reservation(id_statuts_reservation)
);-- crud dans code cote client

CREATE TABLE categories_age (
    id_categorie SERIAL PRIMARY KEY,
    type_personne VARCHAR(50), 
    age_min INT,
    age_max INT
);
INSERT INTO categories_age (type_personne, age_min, age_max) VALUES
('bébé', 0, 1),
('enfant', 2, 11),
('adolescent', 12, 17),
('jeune adulte', 18, 29),
('adulte', 30, 59),
('senior', 60, 120);

CREATE TABLE passagers (
    id_passager SERIAL PRIMARY KEY,
    nom_passager VARCHAR(100),
    genre_passager CHAR(1) CHECK (genre_passager IN ('M', 'F')),
    id_categorie_age INT,
    FOREIGN KEY (id_categorie_age) REFERENCES categories_age(id_categorie)
); 
INSERT INTO passagers (nom_passager, genre_passager, id_categorie_age) VALUES
('Lala', 'F', 4),
('Tom', 'M', 3),
('Sarah', 'F', 2),
('Kevin', 'M', 5),
('Ines', 'F', 1),
('Paul', 'M', 4),
('Zoé', 'F', 6),
('Noah', 'M', 3),
('Emma', 'F', 3),
('Lucas', 'M', 2),
('Anna', 'F', 4),
('Leo', 'M', 4),
('Mia', 'F', 5),
('Nathan', 'M', 4),
('Eva', 'F', 6),
('Adam', 'M', 1),
('Lina', 'F', 3),
('Yanis', 'M', 5),
('Nina', 'F', 2),
('Ali', 'M', 4),
('Sofia', 'F', 5),
('Rayan', 'M', 3),
('Chloé', 'F', 6),
('Jules', 'M', 4),
('Naïa', 'F', 4);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    id_reservation INT NOT NULL,
    id_passager INT NOT NULL,
    numero_ticket VARCHAR(50) UNIQUE,
    FOREIGN KEY (id_reservation) REFERENCES reservations(id_reservation),
    FOREIGN KEY (id_passager) REFERENCES passagers(id_passager)
); -- depend de la reservation donc inserer dans code

CREATE TABLE admins (
    id_admin SERIAL PRIMARY KEY,
    nom_admin VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mdp VARCHAR(100) NOT NULL
);
INSERT INTO admins (nom_admin, email, mdp) VALUES
('Admin', 'admin@gmail.com', 'admin123');
