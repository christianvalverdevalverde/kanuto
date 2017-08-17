/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.nubesoft.kisoko.monedero;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author chrisvv
 */
@Entity
public class InventarioTubo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mac;
    private Integer orden;
    private Integer moneda_01;
    private Integer moneda_05;
    private Integer moneda_10;
    private Integer moneda_25;
    private Integer moneda_50;
    private Integer moneda_100;
    private Integer mes;
    private Integer dia;
    private Integer anio;
    private BigDecimal total;
    @Enumerated(value = EnumType.ORDINAL)
    private TipoMovimientoInventarioTubo tipoMovimeinto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Integer getMoneda_01() {
    	if(moneda_01==null) {
    		moneda_01=0;
    	}
        return moneda_01;
    }

    public void setMoneda_01(Integer moneda_01) {
        this.moneda_01 = moneda_01;
    }

    public Integer getMoneda_05() {
    	if(moneda_05==null) {
    		moneda_05=0;
    	}
        return moneda_05;
    }

    public void setMoneda_05(Integer moneda_05) {
        this.moneda_05 = moneda_05;
    }

    public Integer getMoneda_10() {
    	if(moneda_10==null) {
    		moneda_10=0;
    	}
        return moneda_10;
    }

    public void setMoneda_10(Integer moneda_10) {
        this.moneda_10 = moneda_10;
    }

    public Integer getMoneda_25() {
    	if(moneda_25==null) {
    		moneda_25=0;
    	}
        return moneda_25;
    }

    public void setMoneda_25(Integer moneda_25) {
        this.moneda_25 = moneda_25;
    }

    public Integer getMoneda_50() {
    	if(moneda_50==null) {
    		moneda_50=0;
    	}
        return moneda_50;
    }

    public void setMoneda_50(Integer moneda_50) {
        this.moneda_50 = moneda_50;
    }

    public Integer getMoneda_100() {
    	if(moneda_100==null) {
    		moneda_100=0;
    	}
        return moneda_100;
    }

    public void setMoneda_100(Integer moneda_100) {
        this.moneda_100 = moneda_100;
    }
    

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
        if (!(object instanceof InventarioTubo)) {
            return false;
        }
        InventarioTubo other = (InventarioTubo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.nubesoft.kisoko.monedero.InventarioTubo[ id=" + id + " ]";
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoMovimientoInventarioTubo getTipoMovimeinto() {
        return tipoMovimeinto;
    }

    public void setTipoMovimeinto(TipoMovimientoInventarioTubo tipoMovimeinto) {
        this.tipoMovimeinto = tipoMovimeinto;
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

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public void acutlizarTotal() {
		total = BigDecimal.ZERO;
		if (getMoneda_01() >= 0)
			total = total.add(new BigDecimal(getMoneda_01()).multiply(new BigDecimal("0.01")));
		if (getMoneda_05() >= 0)
			total = total.add(new BigDecimal(getMoneda_05()).multiply(new BigDecimal("0.05")));
		if (getMoneda_10() >= 0)
			total = total.add(new BigDecimal(getMoneda_10()).multiply(new BigDecimal("0.10")));
		if (getMoneda_25() >= 0)
			total = total.add(new BigDecimal(getMoneda_25()).multiply(new BigDecimal("0.25")));
		if (getMoneda_50() >= 0)
			total = total.add(new BigDecimal(getMoneda_50()).multiply(new BigDecimal("0.50")));
		if (getMoneda_100() >= 0)
			total = total.add(new BigDecimal(getMoneda_100()));
		
	}
    
    
}
