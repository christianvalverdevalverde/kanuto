package ec.nubesoft.kiosko.servicio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import ec.nubesoft.kiosko.alarmas.Alarma;
import ec.nubesoft.kiosko.ec.nubesoft.kioksko.dominioutils.MovimientoBean;
import ec.nubesoft.kiosko.ec.nubesoft.kioksko.dominioutils.RegistroImpresion;
import ec.nubesoft.kiosko.ec.nubesoft.kioksko.dominioutils.RegistroInventarioMonedero;
import ec.nubesoft.kiosko.kanuto.ControlEfectivoKiosko;
import ec.nubesoft.kiosko.kanuto.ControlImpresion;
import ec.nubesoft.kiosko.kanuto.MovimientoKiosko;
import ec.nubesoft.kisoko.monedero.InventarioTubo;
import ec.nubesoft.kisoko.monedero.TipoMovimientoInventarioTubo;


@Stateless
@Path("/v1")
public class RegistradorMovimientoKiosko {

    @PersistenceContext
    private EntityManager em;
    static final Logger loger=Logger.getLogger(RegistradorMovimientoKiosko.class.getCanonicalName());
    @POST
    @Path("/movimientos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarMovimiento(@Valid MovimientoBean movimeinto) {
        loger.info("por persistir "+movimeinto.toString());
        MovimientoKiosko mkiosko=new MovimientoKiosko();
        FechaSeparada fecha=obtenerFechaSeparada();
        mkiosko.setAnio(fecha.getAnio());
        mkiosko.setMes(fecha.getMes());
        mkiosko.setDia(fecha.getDia());
        mkiosko.setMac(movimeinto.getMac());
        mkiosko.setNumeroOrden(movimeinto.getNumeroOrden());
        mkiosko.setMoneda_01(movimeinto.getMoneda_01());
        mkiosko.setMoneda_05(movimeinto.getMoneda_05());
        mkiosko.setMoneda_10(movimeinto.getMoneda_10());
        mkiosko.setMoneda_25(movimeinto.getMoneda_25());
        mkiosko.setMoneda_50(movimeinto.getMoneda_50());
        mkiosko.setMoneda_100(movimeinto.getMoneda_100());
        mkiosko.setBillete_01(movimeinto.getBillete_01());
        mkiosko.setBillete_02(movimeinto.getBillete_02());
        mkiosko.setBillete_05(movimeinto.getBillete_05());
        mkiosko.setBillete_10(movimeinto.getBillete_10());
        mkiosko.setBillete_20(movimeinto.getBillete_20());
        mkiosko.setBillete_50(movimeinto.getBillete_50());
        mkiosko.setBillete_100(movimeinto.getBillete_100());
        mkiosko.setFecha(fecha.getFecha());
        em.persist(mkiosko);
        //tiempo para acutalizar generalizado
        Query qestado=em.createQuery("select c from ControlEfectivoKiosko c where c.mac=?1");
        qestado.setParameter(1, movimeinto.getMac());
        @SuppressWarnings("unchecked")
		List<ControlEfectivoKiosko>listadoControl=qestado.getResultList();
        if(!listadoControl.isEmpty()) {
        	ControlEfectivoKiosko c=em.find(ControlEfectivoKiosko.class, listadoControl.get(0).getId());
        	c.sumarm01((movimeinto.getMoneda_01().divide(new BigDecimal("0.01"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarm05((movimeinto.getMoneda_05().divide(new BigDecimal("0.05"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarm10((movimeinto.getMoneda_10().divide(new BigDecimal("0.01"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarm25((movimeinto.getMoneda_25().divide(new BigDecimal("0.25"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarm50((movimeinto.getMoneda_50().divide(new BigDecimal("0.5"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarm100((movimeinto.getMoneda_100().intValue()));
        	c.sumarb01((movimeinto.getBillete_01()).intValue());
        	c.sumarb02((movimeinto.getBillete_02().divide(new BigDecimal("2"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarb05((movimeinto.getBillete_05().divide(new BigDecimal("5"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarb10((movimeinto.getBillete_10().divide(new BigDecimal("10"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarb20((movimeinto.getBillete_20().divide(new BigDecimal("20"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarb50((movimeinto.getBillete_50().divide(new BigDecimal("50"),2,RoundingMode.HALF_UP)).intValue());
        	c.sumarb100((movimeinto.getBillete_100().divide(new BigDecimal("100"),2,RoundingMode.HALF_UP)).intValue());
        	c.actualizarTotal();
        	if(c.isAlarmado()) {
        		alarmarCaja(movimeinto.getMac());
        	}
        	
        }else {
        	ControlEfectivoKiosko c=new ControlEfectivoKiosko();
        	c.setMac(movimeinto.getMac());
        	c.setAlarmado(false);
        	c.setAtendido(false);
        	c.setUmbralmonedas_01(-1);
        	c.setUmbralmonedas_05(-1);
        	c.setUmbralmonedas_10(-1);
        	c.setUmbralmonedas_25(-1);
        	c.setUmbralmonedas_50(-1);
        	c.setUmbralmonedas_100(-1);
        	c.setUmbralbillete_01(-1);
        	c.setUmbralbillete_02(-1);
        	c.setUmbralbillete_05(-1);
        	c.setUmbralbillete_10(-1);
        	c.setUmbralbillete_20(-1);
        	c.setUmbralbillete_50(-1);
        	c.setUmbralbillete_100(-1);
        	
        	c.setMonedas_01((movimeinto.getMoneda_01().divide(new BigDecimal("0.01"),2,RoundingMode.HALF_UP)).intValue());
        	c.setMonedas_05((movimeinto.getMoneda_05().divide(new BigDecimal("0.05"),2,RoundingMode.HALF_UP)).intValue());
        	c.setMonedas_10((movimeinto.getMoneda_10().divide(new BigDecimal("0.1"),2,RoundingMode.HALF_UP)).intValue());
        	c.setMonedas_25((movimeinto.getMoneda_25().divide(new BigDecimal("0.25"),2,RoundingMode.HALF_UP)).intValue());
        	c.setMonedas_50((movimeinto.getMoneda_50().divide(new BigDecimal("0.5"),2,RoundingMode.HALF_UP)).intValue());
        	c.setMonedas_100((movimeinto.getMoneda_01()).intValue());
        	c.setBillete_01(movimeinto.getBillete_01().intValue());
        	c.setBillete_02((movimeinto.getBillete_02().divide(new BigDecimal("2"),2,RoundingMode.HALF_UP)).intValue());
        	c.setBillete_05((movimeinto.getBillete_05().divide(new BigDecimal("5"),2,RoundingMode.HALF_UP)).intValue());
        	c.setBillete_10((movimeinto.getBillete_10().divide(new BigDecimal("10"),2,RoundingMode.HALF_UP)).intValue());
        	c.setBillete_20((movimeinto.getBillete_20().divide(new BigDecimal("20"),2,RoundingMode.HALF_UP)).intValue());
        	c.setBillete_50((movimeinto.getBillete_50().divide(new BigDecimal("50"),2,RoundingMode.HALF_UP)).intValue());
        	c.setBillete_100((movimeinto.getBillete_100().divide(new BigDecimal("100"),2,RoundingMode.HALF_UP)).intValue());
        	c.actualizarTotal();
        	em.persist(c);
        	
        }
        loger.info("se ha terminado de persistir el movimiento");
        return Response.ok().build();
    }
    
    @GET
    @Path("reportemovimientoshoy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteMovimientosDiaDelMesActual() {
    	Date fecha=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");    	    	
    	return Response.ok(reportarMovimientos(sdf.format(fecha)),MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("reportemovimientoshoyxls")
    @Produces("application/vnd.ms-excel")
    public Response obtenerReportesHoy(){
    	Date fecha=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");   
    	List<MovimientoKiosko> reportarMovimientos = reportarMovimientos(sdf.format(fecha));
    	
        
        Response.ResponseBuilder ok = Response.ok();
        ok.header("Content-Disposition","attachment; filename=movimientoskioskos_"+sdf.format(fecha)+".xls");
        
        
        return ok.build();
    }
    @GET
    @Path("reportemovimientosayerxls")
    @Produces("application/vnd.ms-excel")
    public Response obtenerReportesayer(){
    	Date fecha=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");   
    	List<MovimientoKiosko> reportarMovimientos = reportarMovimientos(sdf.format(fecha));
    	
        
        Response.ResponseBuilder ok = Response.ok();
        ok.header("Content-Disposition","attachment; filename=movimientoskioskos_"+sdf.format(fecha)+".xls");
        
        
        return ok.build();
    }
    
//    @GET
//    @Path("reportemovimientosayer")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response obtenerReporteMovimientosAyerDelMesActual() {
//    	FechaSeparada fecha=obtenerFechaSeparada();
//    	StringBuilder ayer=new StringBuilder();
//    	ayer.append(fecha.getAnio());
//    	ayer.append(fecha.getMes());
//    	ayer.append(str)
//    	return Response.ok(reportarMovimientos(sdf.format(fecha)),MediaType.APPLICATION_JSON).build();
//    }
    public List<MovimientoKiosko>reportarMovimientos(String yyyyMMdd){
    	Query q=em.createQuery("select m from MovimientoKiosko m where m.anio=?1 and m.mes=?2 and m.dia=?3 order by m.mac , m.fecha asc");    	
    	q.setParameter(1, Integer.valueOf(yyyyMMdd.substring(0,4)));
    	q.setParameter(2, Integer.valueOf(yyyyMMdd.substring(4,6)));
    	q.setParameter(3, Integer.valueOf(yyyyMMdd.substring(6)));
    	@SuppressWarnings("unchecked")
		List<MovimientoKiosko>listaResultados=q.getResultList();
    	return listaResultados;
    }
    
    @POST
    @Path("/inventario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarInventarioMonedero(@Valid RegistroInventarioMonedero registroinventario) {
    	if(registroinventario.getMoneda_01()==-1) {
    		registroinventario.setMoneda_01(0);
    	}
    	if(registroinventario.getMoneda_05()==-1) {
    		registroinventario.setMoneda_05(0);
    	}
    	if(registroinventario.getMoneda_10()==-1) {
    		registroinventario.setMoneda_10(0);
    	}
    	if(registroinventario.getMoneda_25()==-1) {
    		registroinventario.setMoneda_25(0);
    	}
    	if(registroinventario.getMoneda_50()==-1) {
    		registroinventario.setMoneda_50(0);
    	}
    	if(registroinventario.getMoneda_100()==-1) {
    		registroinventario.setMoneda_100(0);
    	}
        loger.info("por persistir "+registroinventario.toString());
        InventarioTubo inventario=new InventarioTubo();
        FechaSeparada fecha=obtenerFechaSeparada();
        if(registroinventario.getTipo()==null) {
        	registroinventario.setTipo(0);
        }
        if(registroinventario.getTipo()==0){
            inventario.setTipoMovimeinto(TipoMovimientoInventarioTubo.inventarioincial);
        }else{
             inventario.setTipoMovimeinto(TipoMovimientoInventarioTubo.inventariofinal);
        }
        inventario.setMac(registroinventario.getMac());
        inventario.setFecha(fecha.getFecha());
        inventario.setMes(fecha.getMes());
        inventario.setDia(fecha.getDia());
        inventario.setAnio(fecha.getAnio());
        inventario.setMoneda_01(registroinventario.getMoneda_01());
        inventario.setMoneda_05(registroinventario.getMoneda_05());
        inventario.setMoneda_10(registroinventario.getMoneda_10());
        inventario.setMoneda_25(registroinventario.getMoneda_25());
        inventario.setMoneda_50(registroinventario.getMoneda_50());
        inventario.setMoneda_100(registroinventario.getMoneda_100());
        inventario.setOrden(registroinventario.getNumeroOrden());
        inventario.acutlizarTotal();
        em.persist(inventario);
        if(registroinventario.getTipo()==1) {
        	//hay que buscar el anterior y hacer registro de movimeinto
                //Query q=em.createQuery("select i from InventarioTubo i");
        	Query q=em.createQuery("select i from InventarioTubo i where i.mac=?1 and i.orden=?2 and i.anio=?3 and i.mes=?4 and i.dia=?5 and i.tipoMovimeinto=?6");
        	q.setParameter(1, registroinventario.getMac());
        	q.setParameter(2, registroinventario.getNumeroOrden());
        	q.setParameter(3, fecha.getAnio());
        	q.setParameter(4, fecha.getMes());
        	q.setParameter(5, fecha.getDia());
        	q.setParameter(6, TipoMovimientoInventarioTubo.inventarioincial);
        	@SuppressWarnings("unchecked")
			List<InventarioTubo> resultList = q.getResultList();
        	if(!resultList.isEmpty()) {
        		InventarioTubo inventarioInicial=em.find(InventarioTubo.class, resultList.get(0).getId());
        		int monedas_01=0;
        		int monedas_05=0;
        		int monedas_10=0;
        		int monedas_25=0;
        		int monedas_50=0;
        		int monedas_100=0;
        		if(inventario.getMoneda_01()<=inventarioInicial.getMoneda_01()) {
        			monedas_01=inventarioInicial.getMoneda_01()-registroinventario.getMoneda_01();
        		}
        		if(inventario.getMoneda_05()<inventarioInicial.getMoneda_05()) {
        			monedas_05=inventarioInicial.getMoneda_05()-registroinventario.getMoneda_05();
        		}
        		if(inventario.getMoneda_10()<inventarioInicial.getMoneda_10()) {
        			monedas_10=inventarioInicial.getMoneda_10()-registroinventario.getMoneda_10();
        		}
        		if(inventario.getMoneda_25()<inventarioInicial.getMoneda_25()) {
        			monedas_25=inventarioInicial.getMoneda_25()-registroinventario.getMoneda_25();
        		}
        		if(inventario.getMoneda_50()<inventarioInicial.getMoneda_50()) {
        			monedas_50=inventarioInicial.getMoneda_50()-registroinventario.getMoneda_50();
        		}
        		if(inventario.getMoneda_100()<inventarioInicial.getMoneda_100()) {
        			monedas_100=inventarioInicial.getMoneda_100()-registroinventario.getMoneda_100();
        		}
        		MovimientoBean movimientoSalida=new MovimientoBean();
        		movimientoSalida.setMac(inventario.getMac());
        		movimientoSalida.setNumeroOrden(inventario.getOrden());
        		movimientoSalida.setMoneda_01(new BigDecimal(monedas_01*0.01*-1));
        		movimientoSalida.setMoneda_05(new BigDecimal(monedas_05*0.05*-1));
        		movimientoSalida.setMoneda_10(new BigDecimal(monedas_10*0.10*-1));
        		movimientoSalida.setMoneda_25(new BigDecimal(monedas_25*0.25*-1));
        		movimientoSalida.setMoneda_50(new BigDecimal(monedas_50*0.50*-1));
        		movimientoSalida.setMoneda_100(new BigDecimal(monedas_100*-1));
        		movimientoSalida.setBillete_01(BigDecimal.ZERO);
        		movimientoSalida.setBillete_02(BigDecimal.ZERO);
        		movimientoSalida.setBillete_05(BigDecimal.ZERO);
        		movimientoSalida.setBillete_10(BigDecimal.ZERO);
        		movimientoSalida.setBillete_20(BigDecimal.ZERO);
        		movimientoSalida.setBillete_50(BigDecimal.ZERO);
        		movimientoSalida.setBillete_100(BigDecimal.ZERO);
        		registrarMovimiento(movimientoSalida);
        		loger.info("se ha registrar movimiento de salida");		
        	}
        	
        }
        loger.info("se ha terminado de persistir el movimiento");
        return Response.ok().build();
    }
    public FechaSeparada obtenerFechaSeparada() {
    	Date fecha=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    	FechaSeparada retorno=new FechaSeparada();
    	String enTexto=sdf.format(fecha);
    	retorno.setAnio(Integer.valueOf(enTexto.substring(0,4)));
    	retorno.setMes(Integer.valueOf(enTexto.substring(4,6)));
    	retorno.setDia(Integer.valueOf(enTexto.substring(6)));
    	retorno.setFecha(fecha);
    	return retorno;
    }
    @POST
    @Path("/impresion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarImpresion(@Valid RegistroImpresion registroImpresion) {
    	String mac=registroImpresion.getMac();
    	Query q=em.createQuery("select c from ControlImpresion c where c.mac=?1");
    	q.setParameter(1, mac);
    	@SuppressWarnings("unchecked")
		List<ControlImpresion>listaControlImpresion=q.getResultList();
    	if(!listaControlImpresion.isEmpty()) {
    		ControlImpresion control=em.find(ControlImpresion.class, listaControlImpresion.get(0).getId());
    		control.setLineasImpresas(control.getLineasImpresas()+registroImpresion.getLineasEnviadas().size());
    		if(control.getLineasImpresas()>=control.getUmbral()) {
    			//disparar alarma.
    			control.setAlarmado(true);
    			//tiempo para mandar correo
    			alarmarImpresion(mac);
    		}
    	}else {
    		ControlImpresion c=new ControlImpresion();
    		c.setMac(mac);
    		c.setUmbral(5000);
    		c.setLineasImpresas(registroImpresion.getLineasEnviadas().size());
    		c.setRollo(1);
    		c.setAlarmado(false);
    		c.setAtendido(false);
    		em.persist(c);
    		
    	}
    	return Response.ok().build();
    }
    @Schedule(dayOfMonth="*",hour="0",minute="0",second="2")
    public void registrarExistenciaInicialNuevoDia() {
    	Query q=em.createQuery("select e from ControlEfectivoKiosko e");
    	@SuppressWarnings("unchecked")
		List<ControlEfectivoKiosko>listadoEstadoActual=q.getResultList();
    	for(ControlEfectivoKiosko c:listadoEstadoActual) {    		
            MovimientoKiosko mkiosko=new MovimientoKiosko();
            FechaSeparada fecha=obtenerFechaSeparada();
            mkiosko.setAnio(fecha.getAnio());
            mkiosko.setMes(fecha.getMes());
            mkiosko.setDia(fecha.getDia());
            mkiosko.setMac(c.getMac());
            mkiosko.setNumeroOrden(0);
            mkiosko.setMoneda_01(new BigDecimal(c.getMonedas_01()*0.01));
            mkiosko.setMoneda_05(new BigDecimal("0.05").multiply(new BigDecimal(c.getMonedas_05())));
            mkiosko.setMoneda_10(new BigDecimal("0.1").multiply(new BigDecimal(c.getMonedas_10())));
            mkiosko.setMoneda_25(new BigDecimal("0.25").multiply(new BigDecimal(c.getMonedas_25())));
            mkiosko.setMoneda_50(new BigDecimal("0.5").multiply(new BigDecimal(c.getMonedas_50())));
            mkiosko.setMoneda_100(new BigDecimal(c.getMonedas_100()));
            mkiosko.setBillete_01(new BigDecimal(c.getBillete_01()));
            mkiosko.setBillete_02(new BigDecimal(2).multiply(new BigDecimal(c.getBillete_02())));
            mkiosko.setBillete_05(new BigDecimal(5).multiply(new BigDecimal(c.getBillete_05())));
            mkiosko.setBillete_10(new BigDecimal(10).multiply(new BigDecimal(c.getBillete_10())));
            mkiosko.setBillete_20(new BigDecimal(20).multiply(new BigDecimal(c.getBillete_20())));
            mkiosko.setBillete_50(new BigDecimal(50).multiply(new BigDecimal(c.getBillete_50())));
            mkiosko.setBillete_100(new BigDecimal(100).multiply(new BigDecimal(c.getBillete_100())));
            mkiosko.setFecha(fecha.getFecha());
            em.persist(mkiosko);
    	}
    	
    	
    }
    
    @POST
    @Path("alarma")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recibirNotificacionAlarma(Alarma payload) {    	
    	alarmaSensor(payload.getMacAdress(), payload.getDescripcionAlarma());
    	return Response.ok().build();
    }
    
    
    public void alarmarImpresion(String mac) {
    	loger.info("alarmando por impresora"+mac);
    	
    }
    public void alarmarCaja(String mac) {
    	loger.info("alarmando por caja"+mac);
    }
    public void alarmaSensor(String mac, String descripcion) {
    	loger.info("alarmando sensor "+mac+"  "+descripcion);
    }
    public String develoverNombreKiosko(String macAddress) {
    	return "kiosko1";
    }
    public String obtenerSucursal(String nombreKiosko) {
    	return "ALBORADA";
    }
    
    public String obtenerReferencia(Integer numeroOrden) {
    	return "001-002-000002342";
    }
    public String obtenerMontoDesdeReferencia(String referencia) {
    	return "$0.00";
    }
    public void generarArchivoExcel(List<MovimientoKiosko> movim){
    	Map<String,String>mapaMacNombreKiosko=new HashedMap<>();
    	Map<String,Boolean>mapaCuadriculaNueva=new HashMap<>();
    	Map<String,String>mapaKioskoSucursal=new HashMap<>();
    	HSSFWorkbook libro = new HSSFWorkbook();
    	
    	Set<String>nombresDeKioskos=new TreeSet<>();
    	for(MovimientoKiosko movimiento:movim) {
    		if(!mapaMacNombreKiosko.containsKey(movimiento.getMac())) {
    			String nombreKioskoDevuelto = develoverNombreKiosko(movimiento.getMac());
    			mapaMacNombreKiosko.put(movimiento.getMac(), nombreKioskoDevuelto);
    			nombresDeKioskos.add(nombreKioskoDevuelto);    
    			mapaCuadriculaNueva.put(nombreKioskoDevuelto, Boolean.TRUE);
    		}
    		
    		
    	}
    	for(String nombreKiosko:nombresDeKioskos) {
    		libro.createSheet(nombreKiosko);	
    	}
    	int contadorLinea=2;
    	
    	int columnaSucursal=1;
    	int columnakiosko=2;
    	int columnareferencia=3;
    	int columnadiferencia=4;
    	int columnamonto=5;
    	int columnarecibido=6;
    	int columnaentregado=7;
    	int columnamoneda_01=8;
    	int columnamoneda_05=9;
    	int columnamoneda_10=10;
    	int columnamoneda_25=11;
    	int columnamoneda_50=12;
    	int columnamoneda_100=13;
    	int columnabillete_01=14;
    	int columnabillete_02=15;
    	int columnabillete_05=16;
    	int columnabillete_10=17;
    	int columnabillete_20=18;
    	int columnabillete_50=19;
    	int columnabillete_100=20;
    	HSSFSheet sheet=null;
    	HSSFSheet sheetAux=null;
    	for(MovimientoKiosko movimiento:movim) {    		
    		sheet = libro.getSheet(mapaMacNombreKiosko.get(movimiento.getMac()));
    		if(mapaCuadriculaNueva.get(mapaMacNombreKiosko.get(movimiento.getMac()))) {    			    		
    			if((sheetAux==null) || (sheet!=sheetAux)) {
    				//hacer cuadrícula
    				contadorLinea=2;
        			Row cabecera=sheet.createRow(contadorLinea);
        			cabecera.createCell(columnaSucursal).setCellValue("SUCURSAL");
        			cabecera.createCell(columnakiosko).setCellValue("KIOSKO");
        			cabecera.createCell(columnareferencia).setCellValue("REFERENCIA");
        			cabecera.createCell(columnadiferencia).setCellValue("DIFERENCIA");
        			cabecera.createCell(columnamonto).setCellValue("MONTO");
        			cabecera.createCell(columnarecibido).setCellValue("RECIBIDO");
        			cabecera.createCell(columnaentregado).setCellValue("ENTREGADO");
        			cabecera.createCell(columnamoneda_01).setCellValue("MONEDA_01");
        			cabecera.createCell(columnamoneda_05).setCellValue("MONEDA_05");
        			cabecera.createCell(columnamoneda_10).setCellValue("MONEDA_10");
        			cabecera.createCell(columnamoneda_25).setCellValue("MONEDA_25");
        			cabecera.createCell(columnamoneda_50).setCellValue("MONEDA_50");
        			cabecera.createCell(columnamoneda_100).setCellValue("MONEDA_100");
        			cabecera.createCell(columnabillete_01).setCellValue("BILLETE_01");
        			cabecera.createCell(columnabillete_02).setCellValue("BILLETE_02");
        			cabecera.createCell(columnabillete_05).setCellValue("BILLETE_05");
        			cabecera.createCell(columnabillete_10).setCellValue("BILLETE_10");
        			cabecera.createCell(columnabillete_20).setCellValue("BILLETE_20");
        			cabecera.createCell(columnabillete_50).setCellValue("BILLETE_50");    			
        			cabecera.createCell(columnabillete_100).setCellValue("BILLETE_100");
        			contadorLinea++;	
        			sheetAux=sheet;
    			} else {
    				//poner  resumen de linea
    				Row sumarizado=sheet.createRow(contadorLinea);
    				Cell rentregado = sumarizado.createCell(columnaentregado);
    				rentregado.setCellType(CellType.FORMULA);
    				rentregado.setCellFormula("SUM(H"+3+":H"+(contadorLinea-1)+")");
    				
    				Cell rrecibido = sumarizado.createCell(columnarecibido);
    				rrecibido.setCellType(CellType.FORMULA);
    				rrecibido.setCellFormula("SUM(G"+3+":G"+(contadorLinea-1)+")");
    				
    				Cell rm01 = sumarizado.createCell(columnamoneda_01);
    				rm01.setCellType(CellType.FORMULA);
    				rm01.setCellFormula("SUM(I"+3+":I"+(contadorLinea-1)+")");
    				
    				Cell rm05 = sumarizado.createCell(columnamoneda_05);
    				rm05.setCellType(CellType.FORMULA);
    				rm05.setCellFormula("SUM(J"+3+":J"+(contadorLinea-1)+")");
    				
    				Cell rm10 = sumarizado.createCell(columnamoneda_10);
    				rm10.setCellType(CellType.FORMULA);
    				rm10.setCellFormula("SUM(K"+3+":K"+(contadorLinea-1)+")");
    				
    				Cell rm25 = sumarizado.createCell(columnamoneda_25);
    				rm25.setCellType(CellType.FORMULA);
    				rm25.setCellFormula("SUM(L"+3+":L"+(contadorLinea-1)+")");
    				
    				Cell rm50 = sumarizado.createCell(columnamoneda_50);
    				rm50.setCellType(CellType.FORMULA);
    				rm50.setCellFormula("SUM(M"+3+":M"+(contadorLinea-1)+")");
    				
    				Cell rm100 = sumarizado.createCell(columnamoneda_100);
    				rm100.setCellType(CellType.FORMULA);
    				rm100.setCellFormula("SUM(N"+3+":N"+(contadorLinea-1)+")");
    				
    				Cell rb01 = sumarizado.createCell(columnabillete_01);
    				rb01.setCellType(CellType.FORMULA);
    				rb01.setCellFormula("SUM(O"+3+":O"+(contadorLinea-1)+")");
    				
    				
    				Cell rb02 = sumarizado.createCell(columnabillete_02);
    				rb02.setCellType(CellType.FORMULA);
    				rb02.setCellFormula("SUM(P"+3+":P"+(contadorLinea-1)+")");
    				
    				Cell rb05 = sumarizado.createCell(columnabillete_05);
    				rb05.setCellType(CellType.FORMULA);
    				rb05.setCellFormula("SUM(Q"+3+":Q"+(contadorLinea-1)+")");
    				
    				Cell rb10 = sumarizado.createCell(columnabillete_10);
    				rb10.setCellType(CellType.FORMULA);
    				rb10.setCellFormula("SUM(R"+3+":R"+(contadorLinea-1)+")");
    				
    				Cell rb20 = sumarizado.createCell(columnabillete_20);
    				rb20.setCellType(CellType.FORMULA);
    				rb20.setCellFormula("SUM(S"+3+":S"+(contadorLinea-1)+")");
    				
    				Cell rb50 = sumarizado.createCell(columnabillete_50);
    				rb50.setCellType(CellType.FORMULA);
    				rb50.setCellFormula("SUM(T"+3+":T"+(contadorLinea-1)+")");
    				Cell rb100 = sumarizado.createCell(columnabillete_100);
    				rb100.setCellType(CellType.FORMULA);
    				rb100.setCellFormula("SUM(U"+3+":U"+(contadorLinea-1)+")");
    				
    				
    			}
    		}
    		Row detalle=sheet.createRow(contadorLinea);
    		String sucursal=null;
    		if(mapaKioskoSucursal.containsKey(movimiento.getMac())) {
    			sucursal=mapaKioskoSucursal.get(movimiento.getMac());
    		}else {
    			sucursal=obtenerSucursal(movimiento.getMac());
    			mapaKioskoSucursal.put(movimiento.getMac(), sucursal);
    		}
    		detalle.createCell(columnaSucursal).setCellValue(sucursal);
    		String kiosko=null;
    		if(mapaMacNombreKiosko.containsKey(movimiento.getMac())) {
    			kiosko=mapaMacNombreKiosko.get(movimiento.getMac());
    		}else {
    			kiosko=develoverNombreKiosko(movimiento.getMac());
    			mapaMacNombreKiosko.put(movimiento.getMac(), kiosko);
    		}
    		detalle.createCell(columnakiosko).setCellValue(kiosko);
    		String referencia=obtenerReferencia(movimiento.getNumeroOrden());
    		detalle.createCell(columnareferencia).setCellValue(referencia);
    		detalle.createCell(columnamonto).setCellValue(obtenerMontoDesdeReferencia(referencia));
    		BigDecimal total=movimiento.getTotal();
    		if(total.doubleValue()>=0) {
    			Cell recibido = detalle.createCell(columnarecibido);
    			recibido.setCellType(CellType.NUMERIC);
    			recibido.setCellValue(total.setScale(2,RoundingMode.HALF_UP).doubleValue());
    			Cell entregado = detalle.createCell(columnaentregado);
    			entregado.setCellType(CellType.NUMERIC);
    			entregado.setCellValue(0d);    		    			
    		}
    		if(total.doubleValue()<0) {
    			Cell entregado = detalle.createCell(columnaentregado);
    			entregado.setCellType(CellType.NUMERIC);
    			entregado.setCellValue(total.setScale(2,RoundingMode.HALF_UP).doubleValue());
    			
    			Cell recibido = detalle.createCell(columnarecibido);
    			recibido.setCellType(CellType.NUMERIC);
    			recibido.setCellValue(0d);
    		}
    		Cell cellm01 = detalle.createCell(columnamoneda_01);
    		cellm01.setCellType(CellType.NUMERIC);
    		cellm01.setCellValue(movimiento.getMoneda_01().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellm05 = detalle.createCell(columnamoneda_05);
    		cellm05.setCellType(CellType.NUMERIC);
    		cellm05.setCellValue(movimiento.getMoneda_05().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellm10 = detalle.createCell(columnamoneda_10);
    		cellm10.setCellType(CellType.NUMERIC);
    		cellm10.setCellValue(movimiento.getMoneda_10().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellm25 = detalle.createCell(columnamoneda_25);
    		cellm25.setCellType(CellType.NUMERIC);
    		cellm25.setCellValue(movimiento.getMoneda_25().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellm50 = detalle.createCell(columnamoneda_50);
    		cellm50.setCellType(CellType.NUMERIC);
    		cellm50.setCellValue(movimiento.getMoneda_50().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellm100 = detalle.createCell(columnamoneda_100);
    		cellm100.setCellType(CellType.NUMERIC);
    		cellm100.setCellValue(movimiento.getMoneda_100().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb01 = detalle.createCell(columnabillete_01);
    		cellb01.setCellType(CellType.NUMERIC);
    		cellb01.setCellValue(movimiento.getBillete_01().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb02 = detalle.createCell(columnabillete_02);
    		cellb02.setCellType(CellType.NUMERIC);
    		cellb02.setCellValue(movimiento.getBillete_02().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb05 = detalle.createCell(columnabillete_05);    		
    		cellb05.setCellType(CellType.NUMERIC);
    		cellb05.setCellValue(movimiento.getBillete_05().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb10 = detalle.createCell(columnabillete_10);
    		cellb10.setCellType(CellType.NUMERIC);
    		cellb10.setCellValue(movimiento.getBillete_10().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb20 = detalle.createCell(columnabillete_20);
    		cellb20.setCellType(CellType.NUMERIC);
    		cellb20.setCellValue(movimiento.getBillete_20().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb50 = detalle.createCell(columnabillete_50);
    		cellb50.setCellType(CellType.NUMERIC);
    		cellb50.setCellValue(movimiento.getBillete_50().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		
    		Cell cellb100 = detalle.createCell(columnabillete_100);
    		cellb100.setCellType(CellType.NUMERIC);
    		cellb100.setCellValue(movimiento.getBillete_100().setScale(2, RoundingMode.HALF_UP).doubleValue());
    		contadorLinea++;
    	}
    }

}