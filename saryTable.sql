-- TABLE compagnie {
--     id_compagnie SERIAL [PRIMARY KEY]
--     nom_compagnie VARCHAR(100) [NOT NULL]
-- }

-- TABLE avion {
--     id_avion SERIAL [PRIMARY KEY]
--     id_compagnie INT [NOT NULL]
--     nom_avion VARCHAR(100) [NOT NULL]
--     modele VARCHAR(50) [NOT NULL]
-- } 

-- Ref: avion.id_compagnie > compagnie.id_compagnie

-- TABLE statut_vol {
--     id_statut_vol SERIAL [PRIMARY KEY]
--     statut VARCHAR(20) [NOT NULL]
-- } 

-- TABLE vol {
--     id_vol SERIAL [PRIMARY KEY]
--     id_avion INT
--     ville_depart VARCHAR(100)
--     ville_arrivee VARCHAR(100)
--     date_depart TIMESTAMP
--     date_arrivee TIMESTAMP
--     duree INT
--     id_statut_vol INT [DEFAULT: 1]
-- } 

-- Ref: vol.id_avion > avion.id_avion
-- Ref: vol.id_statut_vol > statut_vol.id_statut_vol

-- TABLE classe {
--     id_classe SERIAL [PRIMARY KEY]
--     nom_classe VARCHAR(50)
-- } 

-- TABLE classe_vol {
--     id_classe_vol SERIAL [PRIMARY KEY]
--     id_vol INT
--     id_classe INT
--     nbr_place INT
-- } 

-- Ref: classe_vol.id_vol > vol.id_vol
-- Ref: classe_vol.id_classe > classe.id_classe

-- TABLE param_vol {
--     id_param_vol SERIAL [PRIMARY KEY]
--     id_classe_vol INT
--     prix DECIMAL(102)
--     quantite INT
--     date_limite_paiement TIMESTAMP
--     en_cours BOOLEAN [DEFAULT: FALSE]
-- } 

-- Ref: param_vol.id_classe_vol > classe_vol.id_classe_vol

-- TABLE reservation {
--     id_reservation SERIAL [PRIMARY KEY]
--     date_reservation TIMESTAMP
--     id_param_vol INT
--     quantite INT
--     est_payer BOOLEAN [DEFAULT: FALSE]
-- } 

-- Ref: reservation.id_param_vol > param_vol.id_param_vol

-- TABLE enregistrement_reservation {
--     id_enregistrement SERIAL [PRIMARY KEY]
--     id_reservation INT
--     num_reference VARCHAR(10)
--     est_annule BOOLEAN [DEFAULT: FALSE]
-- }

-- Ref: enregistrement_reservation.id_reservation > reservation.id_reservation

-- TABLE admin {
--     id_admin SERIAL [PRIMARY KEY]
--     nom VARCHAR(100)
--     email VARCHAR(100)
--     mdp VARCHAR(50)
-- }