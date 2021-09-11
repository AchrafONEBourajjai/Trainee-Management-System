package application;

public class TableVieuwInfo {
	
	public TableVieuwInfo(String cin, String nom, String prenom, String ville, String service, String agrement,
			String theme, String idStage, String supervisor) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.service = service;
		this.agrement = agrement;
		this.theme = theme;
		this.idStage = idStage;
		this.supervisor = supervisor;
	}

	String cin,nom,prenom,ville,service,agrement,theme,idStage,supervisor;

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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAgrement() {
		return agrement;
	}

	public void setAgrement(String agrement) {
		this.agrement = agrement;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getIdStage() {
		return idStage;
	}

	public void setIdStage(String idStage) {
		this.idStage = idStage;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
}
