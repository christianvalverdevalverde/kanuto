package ec.nubesoft.kiosko.alarmas;

import java.io.Serializable;

public class Alarma implements Serializable{
	private static final long serialVersionUID = 8161048010526914077L;
	private String macAdress;
	private String descripcionAlarma;
	public String getMacAdress() {
		return macAdress;
	}
	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}
	public String getDescripcionAlarma() {
		return descripcionAlarma;
	}
	public void setDescripcionAlarma(String descripcionAlarma) {
		this.descripcionAlarma = descripcionAlarma;
	}
	

}
