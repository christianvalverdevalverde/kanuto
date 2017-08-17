package ec.nubesoft.kiosko.ec.nubesoft.kioksko.dominioutils;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.NotNull;

//import javax.validation.constraints.NotNull;

public class MovimientoBean implements Serializable {
	private static final long serialVersionUID = 5260133511458590841L;	
	private BigDecimal billete_01;

	private BigDecimal billete_02;
	private BigDecimal billete_05;

	private BigDecimal billete_10;
	private BigDecimal billete_100;

	private BigDecimal billete_20;
	private BigDecimal billete_50;
	@NotNull
	private String mac;
	private BigDecimal moneda_01;
	private BigDecimal moneda_05;
	private BigDecimal moneda_10;
	
	
	
	
	
	
	private BigDecimal moneda_100;
	private BigDecimal moneda_25;
	private BigDecimal moneda_50;
	@NotNull
	private Integer numeroOrden;

	public void anadeMoneda01() {
		anadeMoneda01(new BigDecimal(0.01));
	}

	/**
	 * 
	 */
	
	public void anadeMoneda01(BigDecimal monto) {
		moneda_01=getMoneda_01().add(monto);
	}

	public void anadeMoneda05() {
		anadeMoneda05(new BigDecimal(0.05));
	}

	public void anadeMoneda05(BigDecimal monto) {
		moneda_05=getMoneda_05().add(monto);
	}

	public void anadeMoneda10() {
		anadeMoneda10(new BigDecimal(0.10));
	}

	public void anadeMoneda10(BigDecimal monto) {
		moneda_10=getMoneda_10().add(monto);
	}

	public void anadeMoneda100() {
		anadeMoneda100(new BigDecimal(1.00));
	}

	public void anadeMoneda100(BigDecimal monto) {
		moneda_100=getMoneda_100().add(monto);
	}

	public void anadeMoneda25() {
		anadeMoneda25(new BigDecimal(0.25));
	}

	public void anadeMoneda25(BigDecimal monto) {
		moneda_25=getMoneda_25().add(monto);
	}

	public void anadeMoneda50() {
		anadeMoneda50(new BigDecimal(0.50));
	}

	public void anadeMoneda50(BigDecimal monto) {
		moneda_50=getMoneda_50().add(monto);
	}

	public BigDecimal getBillete_01() {
		if(billete_01==null) {
			billete_01=BigDecimal.ZERO;
		}
		return billete_01.setScale(2,RoundingMode.HALF_UP);
	}

	public BigDecimal getBillete_02() {
		if(billete_02==null) {
			billete_02=BigDecimal.ZERO;
		}
		return billete_02.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getBillete_05() {
		if(billete_05==null) {
			billete_05=BigDecimal.ZERO;
		}
		return billete_05.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getBillete_10() {
		if(billete_10==null) {
			billete_10=BigDecimal.ZERO;
		}
		return billete_10.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getBillete_100() 
	{
		if(billete_100==null) {
			billete_100=BigDecimal.ZERO;
		}
		return billete_100.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getBillete_20() {
		if(billete_20==null) {
			billete_20=BigDecimal.ZERO;
		}
		return billete_20.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getBillete_50() {
		if(billete_50==null) {
			billete_50=BigDecimal.ZERO;
		}
		return billete_50.setScale(2,RoundingMode.HALF_UP);
	}
	public String getMac() {
		return mac;
	}
	public BigDecimal getMoneda_01() {
		if(moneda_01==null) {
			moneda_01=BigDecimal.ZERO;
		}
		return moneda_01.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getMoneda_05() {
		if(moneda_05==null) {
			moneda_05=BigDecimal.ZERO;
		}
		return moneda_05.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getMoneda_10() {
		if(moneda_10==null) {
			moneda_10=BigDecimal.ZERO;
		}
		return moneda_10.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getMoneda_100() {
		if(moneda_100==null) {
			moneda_100=BigDecimal.ZERO;
		}
		return moneda_100.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getMoneda_25() {
		if(moneda_25==null) {
			moneda_25=BigDecimal.ZERO;
		}
		return moneda_25.setScale(2,RoundingMode.HALF_UP);
	}
	public BigDecimal getMoneda_50() {
		if(moneda_50==null) {
			moneda_50=BigDecimal.ZERO;
		}
		return moneda_50.setScale(2,RoundingMode.HALF_UP);
	}
	

	public Integer getNumeroOrden() {
		return numeroOrden;
	}

	public void setBillete_01(BigDecimal billete_01) {
		this.billete_01 = billete_01;
	}

	public void setBillete_02(BigDecimal billete_02) {
		this.billete_02 = billete_02;
	}

	public void setBillete_05(BigDecimal billete_05) {
		this.billete_05 = billete_05;
	}

	public void setBillete_10(BigDecimal billete_10) {
		this.billete_10 = billete_10;
	}

	public void setBillete_100(BigDecimal billete_100) {
		this.billete_100 = billete_100;
	}

	public void setBillete_20(BigDecimal billete_20) {
		this.billete_20 = billete_20;
	}

	public void setBillete_50(BigDecimal billete_50) {
		this.billete_50 = billete_50;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public void setMoneda_01(BigDecimal moneda_01) {
		this.moneda_01 = moneda_01;
	}

	public void setMoneda_05(BigDecimal moneda_05) {
		this.moneda_05 = moneda_05;
	}

	public void setMoneda_10(BigDecimal moneda_10) {
		this.moneda_10 = moneda_10;
	}

	public void setMoneda_100(BigDecimal moneda_100) {
		this.moneda_100 = moneda_100;
	}

	public void setMoneda_25(BigDecimal moneda_25) {
		this.moneda_25 = moneda_25;
	}

	public void setMoneda_50(BigDecimal moneda_50) {
		this.moneda_50 = moneda_50;
	}

	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

    @Override
    public String toString() {
        return "MovimientoBean{" + "billete_01=" + billete_01 + ", billete_02=" + billete_02 + ", billete_05=" + billete_05 + ", billete_10=" + billete_10 + ", billete_100=" + billete_100 + ", billete_20=" + billete_20 + ", billete_50=" + billete_50 + ", mac=" + mac + ", moneda_01=" + moneda_01 + ", moneda_05=" + moneda_05 + ", moneda_10=" + moneda_10 + ", moneda_100=" + moneda_100 + ", moneda_25=" + moneda_25 + ", moneda_50=" + moneda_50 + ", numeroOrden=" + numeroOrden + '}';
    }


}
