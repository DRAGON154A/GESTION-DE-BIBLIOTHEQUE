package application.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDB {

    public static void initialize() {
        try (Connection conn = DataBase.getConnection();
             Statement stmt = conn.createStatement()) {

            // Activer les clés étrangères
            stmt.execute("PRAGMA foreign_keys = ON;");

            // Table Bibliothecaires
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS bibliothecaires (
                    bibliothecaire_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nom TEXT UNIQUE NOT NULL,
                    mot_de_passe_hash TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    telephone TEXT,
                    prenom TEXT,
                    date_naissance TEXT,
                    ville TEXT,
                    pays TEXT,
                    poste TEXT,
                    date_creation_compte DATETIME DEFAULT CURRENT_TIMESTAMP,
                    derniere_connexion TEXT,
                    est_actif INTEGER DEFAULT 1
                );
            """);

            // Table Membres
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS membres (
                    membre_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nom_utilisateur TEXT UNIQUE NOT NULL,
                    mot_de_passe_hash TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    telephone TEXT,
                    nom_famille TEXT,
                    prenom TEXT,
                    date_naissance TEXT,
                    ville TEXT,
                    pays TEXT,
                    matricule TEXT UNIQUE,
                    date_inscription DATETIME DEFAULT CURRENT_TIMESTAMP,
                    total_emprunts_en_cours INTEGER DEFAULT 0,
                    penalites_cumulees REAL DEFAULT 0,
                    date_creation_compte TEXT,
                    derniere_connexion TEXT,
                    est_actif INTEGER DEFAULT 1
                );
            """);

            // Table Administrateurs
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS administrateurs (
                    administrateur_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nom_utilisateur TEXT UNIQUE NOT NULL,
                    mot_de_passe_hash TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    telephone TEXT,
                    nom_famille TEXT,
                    prenom TEXT,
                    date_naissance TEXT,
                    ville TEXT,
                    pays TEXT,
                    derniere_action_admin TEXT,
                    date_creation_compte DATETIME DEFAULT CURRENT_TIMESTAMP,
                    derniere_connexion TEXT,
                    est_actif INTEGER DEFAULT 1
                );
            """);

            // Table Categories
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS categories (
                    categorie_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nom_categorie TEXT NOT NULL,
                    description_categorie TEXT,
                    nombre_livres REAL DEFAULT 0
                );
            """);

            // Table Livres
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS livres (
                    livre_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titre TEXT NOT NULL,
                    sous_titre TEXT,
                    auteur_principal TEXT,
                    isbn TEXT UNIQUE,
                    annee_publication INTEGER,
                    editeur TEXT,
                    langue TEXT,
                    nombre_pages INTEGER,
                    resume TEXT,
                    id_categorie INTEGER,
                    date_ajout_catalogue DATETIME DEFAULT CURRENT_TIMESTAMP,
                    nombre_exemplaires REAL,
                    FOREIGN KEY(id_categorie) REFERENCES categories(categorie_id)
                );
            """);

            // Table Exemplaires
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS exemplaires (
                    exemplaire_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_livre INTEGER NOT NULL,
                    ibsn TEXT UNIQUE,
                    statut_exemplaire TEXT,
                    provenance TEXT,
                    FOREIGN KEY(id_livre) REFERENCES livres(livre_id)
                );
            """);

            // Table Emprunts
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS emprunts (
                    emprunt_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_membre INTEGER NOT NULL,
                    id_exemplaire INTEGER NOT NULL,
                    date_emprunt DATETIME DEFAULT CURRENT_TIMESTAMP,
                    date_retour_prevue DATE,
                    date_retour_reelle TEXT,
                    id_bibliothecaire_emprunt INTEGER,
                    id_bibliothecaire_retour INTEGER,
                    est_prolonge INTEGER DEFAULT 0,
                    nombre_prolongations INTEGER DEFAULT 0,
                    amende_encourue REAL DEFAULT 0,
                    amende_payee REAL DEFAULT 0,
                    date_paiement_amende TEXT,
                    FOREIGN KEY(id_membre) REFERENCES membres(membre_id),
                    FOREIGN KEY(id_exemplaire) REFERENCES exemplaires(exemplaire_id),
                    FOREIGN KEY(id_bibliothecaire_emprunt) REFERENCES bibliothecaires(bibliothecaire_id),
                    FOREIGN KEY(id_bibliothecaire_retour) REFERENCES bibliothecaires(bibliothecaire_id)
                );
            """);

            // Table Historique Systeme
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS historique_systeme (
                    historique_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    date_action TEXT,
                    id_acteur_responsable INTEGER,
                    type_action TEXT,
                    details_action TEXT,
                    table_affectee TEXT,
                    id_element_affecte INTEGER,
                    valeur_precedente TEXT,
                    nouvelle_valeur TEXT
                );
            """);

            // Table Statistiques
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS statistiques_bibliotheque (
                    statistique_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    date_enregistrement TEXT,
                    type_statistique TEXT,
                    valeur_numerique REAL,
                    valeur_texte TEXT,
                    periode_statistique TEXT,
                    id_reference INTEGER,
                    notes TEXT
                );
            """);

            // Table Reservations
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS reservations (
                    reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_membre INTEGER NOT NULL,
                    id_livre INTEGER NOT NULL,
                    date_reservation DATETIME DEFAULT CURRENT_TIMESTAMP,
                    date_expiration_reservation TEXT,
                    statut_reservation TEXT,
                    id_exemplaire_attribue INTEGER,
                    FOREIGN KEY(id_membre) REFERENCES membres(membre_id),
                    FOREIGN KEY(id_livre) REFERENCES livres(livre_id),
                    FOREIGN KEY(id_exemplaire_attribue) REFERENCES exemplaires(exemplaire_id)
                );
            """);

            // Table Amendes_Penalites
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS amendes_penalites (
                    amende_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_emprunt INTEGER,
                    id_membre INTEGER,
                    type_amende TEXT,
                    montant_total REAL,
                    date_creation_amende DATETIME DEFAULT CURRENT_TIMESTAMP,
                    date_limite_paiement TEXT,
                    statut_amende TEXT,
                    date_paiement TEXT,
                    FOREIGN KEY(id_emprunt) REFERENCES emprunts(emprunt_id),
                    FOREIGN KEY(id_membre) REFERENCES membres(membre_id)
                );
            """);

            System.out.println("Base de données initialisée avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'initialisation de la base de données :");
            e.printStackTrace();
        }
    }

    private static void modifierTable(Statement stmt , String sql)
    {
        try
        {
            stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            System.err.println("Erreur de modification de la table ");
            e.printStackTrace();
        }
    }
}
