package ec.nubesoft.kiosko.ec.nubesoft.kioksko.dominioutils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class RegistroImpresion implements Serializable{

	private static final long serialVersionUID = -1345241886909364937L;	
	private List<String>lineasEnviadas;	
	@NotNull
	private String mac;
	@NotNull
	private Integer numeroOrden;
	public RegistroImpresion() {
		super();
		lineasEnviadas=new ArrayList<>();
	}
	public void anadirLinea(String linea) {
		lineasEnviadas.add(linea);
	}

	public List<String> getLineasEnviadas() {
		return lineasEnviadas;
	}
	public String getMac() {
		return mac;
	}
	public Integer getNumeroOrden() {
		return numeroOrden;
	}
	public void setLineasEnviadas(List<String> lineasEnviadas) {
		this.lineasEnviadas = lineasEnviadas;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	
}
