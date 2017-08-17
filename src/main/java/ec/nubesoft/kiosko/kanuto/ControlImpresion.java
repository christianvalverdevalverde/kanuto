package ec.nubesoft.kiosko.kanuto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: ControlImpresion
 *
 */
@Entity

public class ControlImpresion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ControlImpresion() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String mac;
	private Integer lineasImpresas;
	private Integer umbral;
	private boolean alarmado;
	private boolean atendido;
	private Integer rollo;
	

	public boolean isAlarmado() {
		return alarmado;
	}
	public void setAlarmado(boolean alarmado) {
		this.alarmado = alarmado;
	}
	public boolean isAtendido() {
		return atendido;
	}
	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}
	public Integer getRollo() {
		return rollo;
	}
	public void setRollo(Integer rollo) {
		this.rollo = rollo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Integer getLineasImpresas() {
		return lineasImpresas;
	}
	public void setLineasImpresas(Integer lineasImpresas) {
		this.lineasImpresas = lineasImpresas;
	}
	public Integer getUmbral() {
		return umbral;
	}
	public void setUmbral(Integer umbral) {
		this.umbral = umbral;
	}
	
   
}
