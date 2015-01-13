
public class Persona {
	private String dni, primerCognom,segonCognom;
	private int edat;
	public Persona(){
		
	}
	
	public Persona(String dni, String primerCognom, String segonCognom, int edat) {
		this.dni = dni;
		this.primerCognom = primerCognom;
		this.segonCognom = segonCognom;
		this.edat = edat;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getPrimerCognom() {
		return primerCognom;
	}
	public void setPrimerCognom(String primerCognom) {
		this.primerCognom = primerCognom;
	}
	public String getSegonCognom() {
		return segonCognom;
	}
	public void setSegonCognom(String segonCognom) {
		this.segonCognom = segonCognom;
	}
	public int getEdat() {
		return edat;
	}
	public void setEdat(int edat) {
		this.edat = edat;
	}
}
