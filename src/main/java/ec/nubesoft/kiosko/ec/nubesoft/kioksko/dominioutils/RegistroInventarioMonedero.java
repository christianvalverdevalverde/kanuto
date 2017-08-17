/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.nubesoft.kiosko.ec.nubesoft.kioksko.dominioutils;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 *
 * @author chrisvv
 */
public class RegistroInventarioMonedero implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5199240096272428857L;
	@NotNull
	private String mac;
    @NotNull
    private Integer tipo;
    @NotNull
    private Integer numeroOrden;
    private Integer moneda_01;
    private Integer moneda_05;
    private Integer moneda_10;
    private Integer moneda_25;
    private Integer moneda_50;
    private Integer moneda_100;

    @Override
    public String toString() {
        return "RegistroInventarioMonedero{" + "mac=" + mac + ", tipo=" + tipo + ", numeroOrden=" + numeroOrden + ", moneda_01=" + moneda_01 + ", moneda_05=" + moneda_05 + ", moneda_10=" + moneda_10 + ", moneda_25=" + moneda_25 + ", moneda_50=" + moneda_50 + ", moneda_100=" + moneda_100 + '}';
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Integer getMoneda_01() {
        return moneda_01;
    }

    public void setMoneda_01(Integer moneda_01) {
        this.moneda_01 = moneda_01;
    }

    public Integer getMoneda_05() {
        return moneda_05;
    }

    public void setMoneda_05(Integer moneda_05) {
        this.moneda_05 = moneda_05;
    }

    public Integer getMoneda_10() {
        return moneda_10;
    }

    public void setMoneda_10(Integer moneda_10) {
        this.moneda_10 = moneda_10;
    }

    public Integer getMoneda_25() {
        return moneda_25;
    }

    public void setMoneda_25(Integer moneda_25) {
        this.moneda_25 = moneda_25;
    }

    public Integer getMoneda_50() {
        return moneda_50;
    }

    public void setMoneda_50(Integer moneda_50) {
        this.moneda_50 = moneda_50;
    }

    public Integer getMoneda_100() {
        return moneda_100;
    }

    public void setMoneda_100(Integer moneda_100) {
        this.moneda_100 = moneda_100;
    }
    
}
