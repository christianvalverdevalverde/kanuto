package ec.nubesoft.kiosko.kanuto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ControlEfectivoKiosko implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5226727708204764627L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String mac;
	private boolean alarmado;
	private boolean atendido;
	@Column(name="m01")
	private Integer monedas_01;
	@Column(name="um01")
	private Integer umbralmonedas_01;
	@Column(name="m05")
	private Integer monedas_05;
	@Column(name="um05")
	private Integer umbralmonedas_05;
	@Column(name="m10")
	private Integer monedas_10;
	@Column(name="um10")
	private Integer umbralmonedas_10;
	@Column(name="m25")
	private Integer monedas_25;
	@Column(name="um25")
	private Integer umbralmonedas_25;
	@Column(name="m50")
	private Integer monedas_50;
	@Column(name="um50")
	private Integer umbralmonedas_50;
	@Column(name="m100")
	private Integer monedas_100;
	@Column(name="um100")
	private Integer umbralmonedas_100;
	@Column(name="b01")
	private Integer billete_01;
	@Column(name="ub01")
	private Integer umbralbillete_01;
	@Column(name="b02")
	private Integer billete_02;
	@Column(name="ub02")
	private Integer umbralbillete_02;
	@Column(name="b05")
	private Integer billete_05;
	@Column(name="ub05")
	private Integer umbralbillete_05;
	@Column(name="b10")
	private Integer billete_10;
	@Column(name="ub10")
	private Integer umbralbillete_10;
	@Column(name="b20")
	private Integer billete_20;
	@Column(name="ub20")
	private Integer umbralbillete_20;
	@Column(name="b50")
	private Integer billete_50;
	@Column(name="ub50")
	private Integer umbralbillete_50;
	@Column(name="b100")
	private Integer billete_100;
	@Column(name="ub100")
	private Integer umbralbillete_100;		
	@Column(name="total",columnDefinition="decimal(10,2)")
	private BigDecimal total;
	
	public void sumarm01(int cantidad) {
		monedas_01=monedas_01+cantidad;
		if(monedas_01<=umbralmonedas_01) {
			alarmado=true;
		}
	}
	public void sumarm05(int cantidad) {
		monedas_05=monedas_05+cantidad;
		if(monedas_05<=umbralmonedas_05) {
			alarmado=true;
		}
	}
	public void sumarm10(int cantidad) {
		monedas_10=monedas_10+cantidad;
		if(monedas_10<=umbralmonedas_10) {
			alarmado=true;
		}
	}
	public void sumarm25(int cantidad) {
		monedas_25=monedas_25+cantidad;
		if(monedas_25<=umbralmonedas_25) {
			alarmado=true;
		}
	}
	public void sumarm50(int cantidad) {
		monedas_50=monedas_50+cantidad;
		if(monedas_50<=umbralmonedas_50) {
			alarmado=true;
		}
	}
	public void sumarm100(int cantidad) {
		monedas_100=monedas_100+cantidad;
		if(monedas_100<=umbralmonedas_100) {
			alarmado=true;
		}
	}
	public void sumarb01(int cantidad) {
		billete_01=billete_01+cantidad;
		if(billete_01<=umbralbillete_01) {
			alarmado=true;
		}
	}
	public void sumarb02(int cantidad) {
		billete_02=billete_02+cantidad;
		if(billete_02<=umbralbillete_02) {
			alarmado=true;
		}
	}
	public void sumarb05(int cantidad) {
		billete_05=billete_05+cantidad;
		if(billete_05<=umbralbillete_05) {
			alarmado=true;
		}
	}
	public void sumarb10(int cantidad) {
		billete_10=billete_10+cantidad;
		if(billete_10<=umbralbillete_10) {
			alarmado=true;
		}
	}
	
	public void sumarb20(int cantidad) {
		billete_20=billete_20+cantidad;
		if(billete_20<=umbralbillete_20) {
			alarmado=true;
		}
	}
	public void sumarb50(int cantidad) {
		billete_50=billete_50+cantidad;
		if(billete_50<=umbralbillete_50) {
			alarmado=true;
		}
	}
	public void sumarb100(int cantidad) {
		billete_100=billete_100+cantidad;
		if(billete_100<=umbralbillete_100) {
			alarmado=true;
		}
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
	public Integer getMonedas_01() {
		if(monedas_01==null) {
			monedas_01=0;
		}
		return monedas_01;
	}
	public void setMonedas_01(Integer monedas_01) {
		this.monedas_01 = monedas_01;
	}
	public Integer getUmbralmonedas_01() {
		return umbralmonedas_01;
	}
	public void setUmbralmonedas_01(Integer umbralmonedas_01) {
		this.umbralmonedas_01 = umbralmonedas_01;
	}
	public Integer getMonedas_05() {
		if(monedas_05==null) {
    		monedas_05=0;
    	}
		return monedas_05;
	}
	public void setMonedas_05(Integer monedas_05) {
		this.monedas_05 = monedas_05;
	}
	public Integer getUmbralmonedas_05() {
		return umbralmonedas_05;
	}
	public void setUmbralmonedas_05(Integer umbralmonedas_05) {
		this.umbralmonedas_05 = umbralmonedas_05;
	}
	public Integer getMonedas_10() {
		if(monedas_10==null) {
    		monedas_10=0;
    	}
		return monedas_10;
	}
	public void setMonedas_10(Integer monedas_10) {
		this.monedas_10 = monedas_10;
	}
	public Integer getUmbralmonedas_10() {
		return umbralmonedas_10;
	}
	public void setUmbralmonedas_10(Integer umbralmonedas_10) {
		this.umbralmonedas_10 = umbralmonedas_10;
	}
	public Integer getMonedas_25() {
		if(monedas_25==null) {
    		monedas_25=0;
    	}
		return monedas_25;
	}
	public void setMonedas_25(Integer monedas_25) {
		this.monedas_25 = monedas_25;
	}
	public Integer getUmbralmonedas_25() {
		return umbralmonedas_25;
	}
	public void setUmbralmonedas_25(Integer umbralmonedas_25) {
		this.umbralmonedas_25 = umbralmonedas_25;
	}
	public Integer getMonedas_50() {
		if(monedas_50==null) {
    		monedas_50=0;
    	}
		return monedas_50;
	}
	public void setMonedas_50(Integer monedas_50) {
		this.monedas_50 = monedas_50;
	}
	public Integer getUmbralmonedas_50() {
		return umbralmonedas_50;
	}
	public void setUmbralmonedas_50(Integer umbralmonedas_50) {
		this.umbralmonedas_50 = umbralmonedas_50;
	}
	public Integer getMonedas_100() {
		if(monedas_100==null) {
    		monedas_100=0;
    	}
		return monedas_100;
	}
	public void setMonedas_100(Integer monedas_100) {
		this.monedas_100 = monedas_100;
	}
	public Integer getUmbralmonedas_100() {
		return umbralmonedas_100;
	}
	public void setUmbralmonedas_100(Integer umbralmonedas_100) {
		this.umbralmonedas_100 = umbralmonedas_100;
	}
	public Integer getBillete_01() {
		if(billete_01==null) {
			billete_01=0;
		}
		return billete_01;
	}
	public void setBillete_01(Integer billete_01) {
		this.billete_01 = billete_01;
	}
	public Integer getUmbralbillete_01() {
		return umbralbillete_01;
	}
	public void setUmbralbillete_01(Integer umbralbillete_01) {
		this.umbralbillete_01 = umbralbillete_01;
	}
	public Integer getBillete_02() {
		if(billete_02==null) {
			billete_02=0;
		}
		return billete_02;
	}
	public void setBillete_02(Integer billete_02) {
		this.billete_02 = billete_02;
	}
	public Integer getUmbralbillete_02() {
		return umbralbillete_02;
	}
	public void setUmbralbillete_02(Integer umbralbillete_02) {
		this.umbralbillete_02 = umbralbillete_02;
	}
	
	public Integer getBillete_05() {
		if(billete_05==null) {
			billete_05=0;
		}
		return billete_05;
	}
	public void setBillete_05(Integer billete_05) {
		this.billete_05 = billete_05;
	}
	public Integer getUmbralbillete_05() {
		return umbralbillete_05;
	}
	public void setUmbralbillete_05(Integer umbralbillete_05) {
		this.umbralbillete_05 = umbralbillete_05;
	}
	public Integer getBillete_10() {
		if(billete_10==null) {
			billete_10=0;
		}
		return billete_10;
	}
	public void setBillete_10(Integer billete_10) {
		this.billete_10 = billete_10;
	}
	public Integer getUmbralbillete_10() {
		return umbralbillete_10;
	}
	public void setUmbralbillete_10(Integer umbralbillete_10) {
		this.umbralbillete_10 = umbralbillete_10;
	}
	public Integer getBillete_20() {
		if(billete_20==null) {
			billete_20=0;
		}
		return billete_20;
	}
	public void setBillete_20(Integer billete_20) {
		this.billete_20 = billete_20;
	}
	public Integer getUmbralbillete_20() {
		return umbralbillete_20;
	}
	public void setUmbralbillete_20(Integer umbralbillete_20) {
		this.umbralbillete_20 = umbralbillete_20;
	}
	public Integer getBillete_50() {
		if(billete_50==null) {
			billete_50=0;
		}
		return billete_50;
	}
	public void setBillete_50(Integer billete_50) {
		this.billete_50 = billete_50;
	}
	public Integer getUmbralbillete_50() {
		return umbralbillete_50;
	}
	public void setUmbralbillete_50(Integer umbralbillete_50) {
		this.umbralbillete_50 = umbralbillete_50;
	}
	public Integer getBillete_100() {
		if(billete_100==null) {
			billete_100=0;
		}
		return billete_100;
	}
	public void setBillete_100(Integer billete_100) {
		this.billete_100 = billete_100;
	}
	public Integer getUmbralbillete_100() {
		return umbralbillete_100;
	}
	public void setUmbralbillete_100(Integer umbralbillete_100) {
		this.umbralbillete_100 = umbralbillete_100;
	}

	public void actualizarTotal() {
		total = BigDecimal.ZERO;
		if (getMonedas_01() >= 0)
			total = total.add(new BigDecimal(getMonedas_01()).multiply(new BigDecimal("0.01")));
		if (getMonedas_05() >= 0)
			total = total.add(new BigDecimal(getMonedas_05()).multiply(new BigDecimal("0.05")));
		if (getMonedas_10() >= 0)
			total = total.add(new BigDecimal(getMonedas_10()).multiply(new BigDecimal("0.10")));
		if (getMonedas_25() >= 0)
			total = total.add(new BigDecimal(getMonedas_25()).multiply(new BigDecimal("0.25")));
		if (getMonedas_50() >= 0)
			total = total.add(new BigDecimal(getMonedas_50()).multiply(new BigDecimal("0.50")));
		if (getMonedas_100() >= 0)
			total = total.add(new BigDecimal(getMonedas_100()));
		if (getBillete_01() >= 0)
			total = total.add(new BigDecimal(getBillete_01()));
		if (getBillete_02() >= 0)
			total = total.add(new BigDecimal(getBillete_02()).multiply(new BigDecimal("2")));
		if (getBillete_05() >= 0)
			total = total.add(new BigDecimal(getBillete_05()).multiply(new BigDecimal("5")));
		if (getBillete_10() >= 0)
			total = total.add(new BigDecimal(getBillete_10()).multiply(new BigDecimal("10")));
		if (getBillete_20() >= 0)
			total = total.add(new BigDecimal(getBillete_20()).multiply(new BigDecimal("20")));
		if (getBillete_50() >= 0)
			total = total.add(new BigDecimal(getBillete_50()).multiply(new BigDecimal("50")));
		if (getBillete_100() >= 0)
			total = total.add(new BigDecimal(getBillete_100()).multiply(new BigDecimal("100")));		
	}
	
	

}
