package com.models.modelsClass;

public class Bibliothecaire {
	private String nom;
	private String prenom;
	private String password;
	private String email;
	private String telephone;
	private String dateNaissance;
	private String pays;
	private String ville;
	private String poste;
	private String dateCreationCompte;
	private String derniereConnexion;
	private int estActif;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getDateCreationCompte() {
		return dateCreationCompte;
	}
	public void setDateCreationCompte(String dateCreationCompte) {
		this.dateCreationCompte = dateCreationCompte;
	}
	public String getDerniereConnexion() {
		return derniereConnexion;
	}
	public void setDerniereConnexion(String derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}
	public int getEstActif() {
		return estActif;
	}
	public void setEstActif(int estActif) {
		this.estActif = estActif;
	}
	public Bibliothecaire(String nom, String prenom, String password, String email, String telephone,
			String dateNaissance, String pays, String ville, String poste, String dateCreationCompte,
			String derniereConnexion, int estActif) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.pays = pays;
		this.ville = ville;
		this.poste = poste;
		this.dateCreationCompte = dateCreationCompte;
		this.derniereConnexion = derniereConnexion;
		this.estActif = estActif;
	}
	
	public Bibliothecaire() {
	}
	
	public Bibliothecaire(String nom, String prenom, String password, String email, String telephone,
			String dateNaissance, String pays, String ville, String poste) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.pays = pays;
		this.ville = ville;
		this.poste = poste;
	}
	
	

}
