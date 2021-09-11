package application;

public class Supervisor {
	private String encadrant,service,accord;
	
	public Supervisor() {}
	public Supervisor(String encadrant, String service, String accord) {
		this.encadrant = encadrant;
		this.service = service;
		this.accord = accord;
	}

	public String getEncadrant() {
		return encadrant;
	}

	public void setEncadrant(String encadrant) {
		this.encadrant = encadrant;
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
	
}
