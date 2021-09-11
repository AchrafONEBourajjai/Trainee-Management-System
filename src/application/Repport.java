package application;

public class Repport {
	String cin,nom,prenom,ville,niveau,specialite,service,accord,theme,debut,fin,encadrant;
	
	public Repport() {
		
	}

	public Repport(String cin, String nom, String prenom, String ville, String niveau, String specialite,
			String service, String accord, String theme, String debut, String fin, String encadrant) {
		
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.niveau = niveau;
		this.specialite = specialite;
		this.service = service;
		this.accord = accord;
		this.theme = theme;
		this.debut = debut;
		this.fin = fin;
		this.encadrant = encadrant;
	}
	

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAccord() {
		return accord;
	}

	public void setAccord(String accord) {
		this.accord = accord;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getEncadrant() {
		return encadrant;
	}

	public void setEncadrant(String encadrant) {
		this.encadrant = encadrant;
	}
}
