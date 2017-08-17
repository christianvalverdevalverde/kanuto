/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.nubesoft.kiosko.kanuto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author chrisvv
 */
@Entity
public class MovimientoKiosko implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mac;
    private Integer anio;
    private Integer mes;
    private Integer dia;
    private Integer numeroOrden;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private BigDecimal moneda_01;
    private BigDecimal moneda_05;
    private BigDecimal moneda_10;
    private BigDecimal moneda_25;
    private BigDecimal moneda_50;
    private BigDecimal moneda_100;
    private BigDecimal billete_01;
    private BigDecimal billete_02;
    private BigDecimal billete_05;
    private BigDecimal billete_10;
    private BigDecimal billete_20;
    private BigDecimal billete_50;
    private BigDecimal billete_100;
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoKiosko)) {
            return false;
        }
        MovimientoKiosko other = (MovimientoKiosko) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.nubesoft.kiosko.kanuto.MovimientoKiosko[ id=" + id + " ]";
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMoneda_01() {
    	if(moneda_01==null) {
    		moneda_01=BigDecimal.ZERO;
    	}
        return moneda_01;
    }

    public void setMoneda_01(BigDecimal moneda_01) {
        this.moneda_01 = moneda_01;
    }

    public BigDecimal getMoneda_05() {
    	if(moneda_05==null) {
    		moneda_05=BigDecimal.ZERO;
    	}
        return moneda_05;
    }

    public void setMoneda_05(BigDecimal moneda_05) {
        this.moneda_05 = moneda_05;
    }

    public BigDecimal getMoneda_10() {
    	if(moneda_10==null) {
    		moneda_10=BigDecimal.ZERO;
    	}
        return moneda_10;
    }

    public void setMoneda_10(BigDecimal moneda_10) {
        this.moneda_10 = moneda_10;
    }

    public BigDecimal getMoneda_25() {
    	if(moneda_25==null) {
    		moneda_25=BigDecimal.ZERO;
    	}
        return moneda_25;
    }

    public void setMoneda_25(BigDecimal moneda_25) {
        this.moneda_25 = moneda_25;
    }

    public BigDecimal getMoneda_50() {
    	if(moneda_50==null) {
    		moneda_50=BigDecimal.ZERO;
    	}
        return moneda_50;
    }

    public void setMoneda_50(BigDecimal moneda_50) {
        this.moneda_50 = moneda_50;
    }

    public BigDecimal getMoneda_100() {
    	if(moneda_100==null) {
    		moneda_100=BigDecimal.ZERO;
    	}
        return moneda_100;
    }

    public void setMoneda_100(BigDecimal moneda_100) {
        this.moneda_100 = moneda_100;
    }

    public BigDecimal getBillete_01() {
    	if(billete_01==null) {
    		billete_01=BigDecimal.ZERO;
    	}
        return billete_01;
    }

    public void setBillete_01(BigDecimal billete_01) {
        this.billete_01 = billete_01;
    }

    public BigDecimal getBillete_02() {
    	if(billete_02==null) {
    		billete_02=BigDecimal.ZERO;
    	}
        return billete_02;
    }

    public void setBillete_02(BigDecimal billete_02) {
        this.billete_02 = billete_02;
    }

    public BigDecimal getBillete_05() {
    	if(billete_05==null) {
    		billete_05=BigDecimal.ZERO;
    	}
        return billete_05;
    }

    public void setBillete_05(BigDecimal billete_05) {
        this.billete_05 = billete_05;
    }

    public BigDecimal getBillete_10() {
    	if(billete_10==null) {
    		billete_10=BigDecimal.ZERO;
    	}
        return billete_10;
    }

    public void setBillete_10(BigDecimal billete_10) {
        this.billete_10 = billete_10;
    }

    public BigDecimal getBillete_20() {
    	if(billete_20==null) {
    		billete_20=BigDecimal.ZERO;
    	}
        return billete_20;
    }

    public void setBillete_20(BigDecimal billete_20) {
        this.billete_20 = billete_20;
    }

    public BigDecimal getBillete_50() {
    	if(billete_50==null) {
    		billete_50=BigDecimal.ZERO;
    	}
        return billete_50;
    }

    public void setBillete_50(BigDecimal billete_50) {
        this.billete_50 = billete_50;
    }

    public BigDecimal getBillete_100() {
    	if(billete_100==null) {
    		billete_100=BigDecimal.ZERO;
    	}
        return billete_100;
    }

    public void setBillete_100(BigDecimal billete_100) {
        this.billete_100 = billete_100;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    
    @PrePersist
    public void totalizar() {
    	total=BigDecimal.ZERO;
    	total=total.add(moneda_01);
    	total=total.add(moneda_05);
    	total=total.add(moneda_10);
    	total=total.add(moneda_25);
    	total=total.add(moneda_50);
    	total=total.add(moneda_100);
    	total=total.add(billete_01);
    	total=total.add(billete_02);
    	total=total.add(billete_05);
    	total=total.add(billete_10);
    	total=total.add(billete_20);
    	total=total.add(billete_50);
    	total=total.add(billete_100);
    }

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
    
}
