package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.AnexoContratoVO;
import VO.ContratoVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.EstructuraCobroVO;
import VO.MaquinaContadorVO;
import VO.TipoCambioVO;
import VO.UbicacionVO;

public class AnexoContratoDAO {
	static Connection conexion=null;
	static Statement state=null;
	
	public AnexoContratoDAO() {
		super();
		
	}

	private static void getCon() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			 conexion=(Connection) DriverManager.getConnection
		    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
	}
	
	public static void disconect(){
		try {
			state.close();
			conexion.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static int getNewId(){
		getCon();
		
		int newID=1;
		
		String SQL= ""
	    		+ " SELECT MAX(anexo_contrato.anc_id) AS anc_id "
	    		+ " FROM anexo_contrato";
	    	
		
		System.out.println(SQL);
		ArrayList<String> ubicaciones = new ArrayList<String>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				newID=(RS.getInt("anc_id")+1);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return newID;
	}
	public static void getAnexoContrato(AnexoContratoVO anexo){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		`anc_cargo_fijo_unico` "
	    		+ "		,`anc_observaciones` "
	    		+ "		,`anc_nombre` "
	    		+ "		,DATE_FORMAT(`feccreacion`,'%d-%m-%Y') as feccreacion "
	    		+ "		,DATE_FORMAT(`anc_fecha_desde`,'%d-%m-%Y') as anc_fecha_desde "
	    		+ "		,DATE_FORMAT(`anc_fecha_hasta`,'%d-%m-%Y') as anc_fecha_hasta "
			    	+ "		,`contrato`.`contrato_nombre` "
	    		+ "		,`estados_vig_novig`.`estados_vig_novig_nombre` "
	    		+ "		,`estados_vig_novig`.`estados_vig_novig_id` "
	    	    
	    		+ "		, `tipo_cambio`.`tipo_cambio_nombre` "
	    		+ "		,`empresas`.`empresas_nombrenof` " 
	    		+ "		,`empresas`.`empresas_id` " 
	    		
	    		+ "		,`grupos`.`grupos_nombre` " 
	    		+ "		,`empresas`.`empresas_razonsocial` "
	    		+ "		,`empresas`.`empresas_rut` "
		    	  
	    		+ "	FROM `anexo_contrato` " + 
	    		"	INNER JOIN `contrato` ON `contrato`.`contrato_id`= `anexo_contrato`.`contrato_id`" + 
	    		"	INNER JOIN `estados_vig_novig` ON estados_vig_novig.`estados_vig_novig_id`=`anexo_contrato`.`estados_vig_novig_id` " + 
	    		"	INNER JOIN `tipo_cambio` ON `tipo_cambio`.`tipo_cambio_id`=`anexo_contrato`.`tipo_cambio_id`" + 
	    		"	INNER JOIN `empresas` ON `empresas`.`empresas_id`=`anexo_contrato`.`empresas_id`" +
	    		"	INNER JOIN `grupos` ON `grupos`.`grupos_id`=`empresas`.`grupos_id`  " +
	    		
	    		"	WHERE `anexo_contrato`.`anc_id`="+anexo.getAnc_id() +  
	    		"";
	    	
		
		System.out.println(SQL);
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				anexo.setCargoFijoUnico(RS.getString("anc_cargo_fijo_unico"));
				anexo.setObservacion(RS.getString("anc_observaciones"));
				anexo.setFecha_desde(RS.getString("anc_fecha_desde"));
				anexo.setFecha_hasta(RS.getString("anc_fecha_hasta"));
				anexo.setNombre(RS.getString("anc_nombre"));
				ContratoVO contrato = new ContratoVO();
				contrato.setContrato_nombre(RS.getString("contrato_nombre"));
				
				anexo.setContrato(contrato);
				
				EstadosVigNoVigVO estadovignovig= new EstadosVigNoVigVO();
				estadovignovig.setNombre(RS.getString("estados_vig_novig_nombre"));
				estadovignovig.setId(RS.getString("estados_vig_novig_id"));
				
				anexo.setEstadoVignoVig(estadovignovig);
				
				TipoCambioVO tipocambio = new TipoCambioVO();
				tipocambio.setNombre(RS.getString("tipo_cambio_nombre"));
				anexo.setTipocambio(tipocambio);
				
				EmpresaVO empresa = new EmpresaVO();
				empresa.setEmpresas_nombrenof(RS.getString("empresas_nombrenof"));
				empresa.setGrupos_nombre(RS.getString("grupos_nombre"));
				empresa.setEmpresas_razonsocial(RS.getString("empresas_razonsocial"));
				empresa.setEmpresas_rut(RS.getString("empresas_rut"));
				empresa.setEmpresas_id(RS.getString("empresas_id"));
				
				anexo.setEmpresa(empresa);
				
				anexo.setFecha(RS.getString("feccreacion"));
				
				//seteamos ubicaciones que pertenecen al anexo 
				
				UbicacionVO ubiAnexo= new UbicacionVO();
				ubiAnexo.setAnc_id(anexo.getAnc_id());
				
				//anexo.setMaquinasContadores(UbicacionesDAO.getUbicaciones(ubiAnexo));
				
				//seteamos estructuras de cobro 
				
				EstructuraCobroVO estructura= new EstructuraCobroVO();
				estructura.setAnc_id(anexo.getAnc_id());
				
				anexo.setEstructurasCobro(EstructuraCobroDAO.getEstructurasDeCobro(estructura));
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	 
	}
	public static String insertAnexoContrato(AnexoContratoVO anexoContrato,String id_usu_crea){
		 getCon();
		
		String id=null;
		
		String fdesde_ar[]=anexoContrato.getFecha_desde().split("-");
		String fhasta_ar[]=anexoContrato.getFecha_hasta().split("-");
		
		
		
		String SQL= ""
	    		+ " INSERT INTO `anexo_contrato` ("
	    		+ "		contrato_id"
	    		+ "		,tipo_cambio_id"
	    		+ "		,anc_cargo_fijo_unico"
	    		+ "		,anc_observaciones"
	    		+ "		,empresas_id"
	    		+ "		,anc_fecha_desde"
	    		+ "		,anc_fecha_hasta"
	    		+ "		,anc_nombre"
		    	+ "		,creador"
	    		+ "		,feccreacion"
	    		+ "		) " 
				+ "	VALUES ("
				+ "	'"+anexoContrato.getContrato().getContrato_id()+"'"
				+ "	,'"+anexoContrato.getTipoCambio_id()+"'"
				+ "	,'"+anexoContrato.getCargoFijoUnico()+"'"
				+ "	,'"+anexoContrato.getObservacion()+"'"
				+ "	,'"+anexoContrato.getEmpresa().getEmpresas_id()+"'"
				+ "	,'"+fdesde_ar[2]+"-"+fdesde_ar[1]+"-"+fdesde_ar[0]+"'"
				+ "	,'"+fhasta_ar[2]+"-"+fhasta_ar[1]+"-"+fhasta_ar[0]+"'"
				+ "	,'"+anexoContrato.getNombre()+"'"
				+ "	,'"+id_usu_crea+"'"
				+ "	,now()"
				+ "	)";
	    		
		
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL,Statement.RETURN_GENERATED_KEYS);
			
			ResultSet generatedKeys = null;
	    	generatedKeys = state.getGeneratedKeys();
	    	if (generatedKeys.next()) {
	    		id=generatedKeys.getString(1);
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	    
	    disconect();
	    
	    return id;
	}
	
	
	public static String updateAnexoContrato(AnexoContratoVO anexoContrato,String id_usu_mod){
		 getCon();
		
		String id=null;
		
		String SQL= ""
	    		+ " UPDATE `anexo_contrato` SET fecmod=now(),modificador='"+id_usu_mod+"' ";
				if(anexoContrato.getEstadoVignoVig()!=null) SQL+=" ,estados_vig_novig_id="+anexoContrato.getEstadoVignoVig().getId();
				if(anexoContrato.getCargoFijoUnico()!=null) SQL+=" ,anc_cargo_fijo_unico='"+anexoContrato.getCargoFijoUnico()+"' ";
				if(anexoContrato.getNombre()!=null) SQL+=" ,anc_nombre='"+anexoContrato.getNombre()+"' ";
				if(anexoContrato.getObservacion()!=null) SQL+=" ,anc_observaciones='"+anexoContrato.getObservacion()+"' ";
				if(anexoContrato.getTipoCambio_id()!=null) SQL+=" ,tipo_cambio_id="+anexoContrato.getTipoCambio_id();
				
				SQL+= "	WHERE anexo_contrato.anc_id="+anexoContrato.getAnc_id();
	    		
		
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL,Statement.RETURN_GENERATED_KEYS);
			
			ResultSet generatedKeys = null;
	    	generatedKeys = state.getGeneratedKeys();
	    	if (generatedKeys.next()) {
	    		id=generatedKeys.getString(1);
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	    
	    disconect();
	    
	    return id;
	}
	
	
	
	public static void disableUbisAnexo(AnexoContratoVO anexo) {
		getCon();
		String SQL= ""
	    		+ " UPDATE `ubicacion` SET "
	    		+ "		anc_id=NULL "
	    	
	    		+ "	WHERE  "
	    		+ "		ubicacion.anc_id='"+anexo.getAnc_id()+"' "; 
	    	
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	    
	    disconect();
	    
	}
	
	
	public static void disableEstructurasAnexo(AnexoContratoVO anexo) {
		getCon();
		String SQL= ""
	    		+ " UPDATE `estructura_cobro` SET "
	    		+ "		estados_vig_novig_id=2 "
	    	
	    		+ "	WHERE  "
	    		+ "		estructura_cobro.anc_id='"+anexo.getAnc_id()+"' "; 
	    	
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	    
	    disconect();
	    
	}
	
	
	public static void setAnexoUbisToEstructurasC(AnexoContratoVO anexoContrato){
		getCon();
		
		//eliminamos todo lo que sea del anexo 
		String SQLDEL= ""
	    		+ " UPDATE  ubi_estrcobro SET estados_vig_novig_id=2 WHERE anc_id="+anexoContrato.getAnc_id();
		try {
			state.executeUpdate(SQLDEL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//establecemos relaciones 
		
			for(EstructuraCobroVO estructura :anexoContrato.getEstructurasCobro()){
				
				for(MaquinaContadorVO maq : estructura.getMaquinasContador()){
					
					String SQL= ""
				    		+ " INSERT INTO ubi_estrcobro (ubi_id,estrcobro_id,activo_tptc_id,anc_id,feccrea) VALUES ('"+maq.getUbi_id()+"','"+estructura.getEstrcobro_id()+"','"+maq.getActivo_tptc_id()+"',"+anexoContrato.getAnc_id()+",now()) ";
					System.out.println(SQL);
					
					try {
						state.executeUpdate(SQL);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		
		
		
	    disconect();
	   
	}
	
}
