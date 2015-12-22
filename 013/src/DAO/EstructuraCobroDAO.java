package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.ContratoVO;
import VO.EstadosVigNoVigVO;
import VO.EstructuraCobroVO;
import VO.RangoEstructuraCobroVO;
import VO.TipoCambioVO;
import VO.TipoEstcobroVO;

public class EstructuraCobroDAO {
	
	static Connection conexion=null;
	static Statement state=null;
	
	public EstructuraCobroDAO() {
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
	    		+ " SELECT MAX(estructura_cobro.estrcobro_id) AS estrcobro_id "
	    		+ " FROM estructura_cobro";
	    	
		
		System.out.println(SQL);
		ArrayList<String> ubicaciones = new ArrayList<String>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				newID=(RS.getInt("estrcobro_id")+1);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return newID;
	}
	
	
	public static ContratoVO getContrato(String id_estr){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		estructura_cobro.estrcobro_id"
	    		+ "		, estructura_cobro.estrcobro_observaciones "
	    		+ "		, estructura_cobro.estrcobro_nombre "
				+ "		, estructura_cobro.tipo_cambio_id "
	    		+ "		, estructura_cobro.estrcobro_cxa "
	    		+ "		, estructura_cobro.estados_vig_novig_id "
	    		+ "		, estructura_cobro.tipo_estcobro_id "
	    	
				+ "		, contrato.contrato_id "
				+ "		, contrato.contrato_nombre "
				
				    		
	    		+ " FROM estructura_cobro "
	    		+ "	INNER JOIN anexo_contrato on anexo_contrato.anc_id=estructura_cobro.anc_id "
	    		+ "	INNER JOIN contrato on contrato.contrato_id=anexo_contrato.contrato_id "
	    		
	    		+ "	WHERE 1=1  ";
		
		SQL+=" AND estructura_cobro.estrcobro_id= "+id_estr;
		
	    	
		
		System.out.println(SQL);
		ContratoVO contrato = new ContratoVO();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				
				contrato.setContrato_id(RS.getString("contrato_id"));
				contrato.setContrato_nombre(RS.getString("contrato_nombre"));
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return contrato;
	}
	public static ArrayList<EstructuraCobroVO> getEstructurasDeCobro(EstructuraCobroVO estructura){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		estructura_cobro.estrcobro_id"
	    		+ "		, estructura_cobro.estrcobro_observaciones "
	    		+ "		, estructura_cobro.estrcobro_nombre "
				+ "		, estructura_cobro.tipo_cambio_id "
	    		+ "		, estructura_cobro.estrcobro_cxa "
	    		+ "		, estructura_cobro.estados_vig_novig_id "
	    		+ "		, estructura_cobro.tipo_estcobro_id "
	    		
	    		
	    		+ " FROM estructura_cobro "
	    		
	    		+ "	WHERE estados_vig_novig_id=1  ";
		
		if(estructura.getAnc_id()!=null) SQL+=" AND estructura_cobro.anc_id= "+estructura.getAnc_id();
		
	    	
		
		System.out.println(SQL);
		ArrayList<EstructuraCobroVO> estructuras = new ArrayList<EstructuraCobroVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				EstructuraCobroVO estructura_ = new EstructuraCobroVO();
				estructura_.setEstrcobro_id(RS.getString("estrcobro_id"));
				estructura_.setEstrcobro_observaciones(RS.getString("estrcobro_observaciones"));
				estructura_.setEstrcobro_nombre(RS.getString("estrcobro_nombre"));
				
				estructura_.setTipo_cambio_id(RS.getString("tipo_cambio_id"));
				estructura_.setEstrcobro_cxa(RS.getString("estrcobro_cxa"));
				RangoEstructuraCobroVO rango= new RangoEstructuraCobroVO();
				rango.setEstrcobro_id(RS.getString("estrcobro_id"));
				
				EstadosVigNoVigVO estadoVignovig= new EstadosVigNoVigVO();
				estadoVignovig.setId(RS.getString("estados_vig_novig_id"));
				
				TipoEstcobroVO tipoEst = new TipoEstcobroVO();
				tipoEst.setId(RS.getString("tipo_estcobro_id"));
				
				estructura_.setTipo_estructuraC(tipoEst);
				
				
				estructura_.setEstadoVignoVig(estadoVignovig);
				
				estructura_.setRangosEstCobro(RangoEstructuraCobroDAO.getRangosEstructuraDeCobro(rango));
				estructura_.setMaquinasContador(UbicacionesDAO.getUbicacionesConAnexo(estructura.getAnc_id(), RS.getString("estrcobro_id")));
				estructuras.add(estructura_);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return estructuras;
	}
	
	
	public static EstructuraCobroVO getEstructuraDeCobro(String id_estructura){
		getCon();
		
		String SQL= ""
	    		+ " SELECT 	estructura_cobro.estrcobro_id"
	    		+ "			, estructura_cobro.estrcobro_observaciones "
	    		+ "			, estructura_cobro.estrcobro_nombre "
		    	+ "			, estructura_cobro.anc_id "
	    		+ "			, estructura_cobro.estrcobro_cxa "
	    		+ "			, estructura_cobro.tipo_cambio_id "
	    		+ "			, `tipo_cambio`.`tipo_cambio_nombre` "
	    		+ "			, estructura_cobro.tipo_estcobro_id "
	    		+ "			, tipo_estcobro.tipo_estcobro_nombre "
			    	
	    		+ " FROM estructura_cobro"
	    		+ "	INNER JOIN `tipo_cambio` ON `tipo_cambio`.`tipo_cambio_id`=`estructura_cobro`.`tipo_cambio_id`" 
	    		+ "	INNER JOIN `tipo_estcobro` ON `tipo_estcobro`.`tipo_estcobro_id`=`estructura_cobro`.`tipo_estcobro_id`" 
		    	
	    		+ "	WHERE estructura_cobro.estrcobro_id="+id_estructura;
		
		
		System.out.println(SQL);
		
		EstructuraCobroVO estructura = new EstructuraCobroVO();
		
		
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				estructura.setAnc_id(RS.getString("anc_id"));
				estructura.setEstrcobro_id(RS.getString("estrcobro_id"));
				estructura.setEstrcobro_observaciones(RS.getString("estrcobro_observaciones"));
				estructura.setTipo_cambio_id(RS.getString("tipo_cambio_id"));
				estructura.setEstrcobro_cxa(RS.getString("estrcobro_cxa"));
				estructura.setEstrcobro_nombre(RS.getString("estrcobro_nombre"));
				
				TipoCambioVO tipocambio = new TipoCambioVO();
				tipocambio.setNombre(RS.getString("tipo_cambio_nombre"));
				estructura.setTipocambio(tipocambio);
				

				TipoEstcobroVO tipoEst = new TipoEstcobroVO();
				tipoEst.setId(RS.getString("tipo_estcobro_id"));
				tipoEst.setNombre(RS.getString("tipo_estcobro_nombre"));
				estructura.setTipo_estructuraC(tipoEst);
				
				//seteamos los rangos
				RangoEstructuraCobroVO rango= new RangoEstructuraCobroVO();
				rango.setEstrcobro_id(RS.getString("estrcobro_id"));
				
				estructura.setRangosEstCobro(RangoEstructuraCobroDAO.getRangosEstructuraDeCobro(rango));
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    return estructura;
	}
	
	public static String insertEstructuraCobro(EstructuraCobroVO estructuraCobro, String anexo_id,String id_usu_crea){
		 getCon();
		
		String id=null;
		
		String SQL= ""
	    		+ " INSERT INTO `estructura_cobro` ("
	    		+ "		anc_id"
	    		+ "		,tipo_cambio_id"
	    		+ "		,estrcobro_observaciones"
	    		+ "		,estrcobro_nombre"
	    	   	+ "		,estrcobro_cxa"
	    	   	+ "		,tipo_estcobro_id"
	    		
	    	   	+ "		,creador"
	    		+ "		,feccreacion"
	    		+ "	) " 
	    		+ " VALUES ("
	    		+ "		'"+anexo_id+"'"
	    		+ "		,'"+estructuraCobro.getTipo_cambio_id()+"'"
	    		+ "		,'"+estructuraCobro.getEstrcobro_observaciones().toUpperCase()+"'"
	    		+ "		,'"+estructuraCobro.getEstrcobro_nombre().toUpperCase()+"'"
		    	+ "		,'"+estructuraCobro.getEstrcobro_cxa()+"'"
		    	+ "		,'"+estructuraCobro.getTipo_estructuraC().getId()+"'"
	    		+ "		,'"+id_usu_crea+"'"
				+ "		,now()"
				+ "	) ";
	     		
		
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

	public static boolean Exist(EstructuraCobroVO estructura) {
		
		getCon();
		
		String SQL= ""
	    		+ " SELECT count(*) AS n"
	    		+ " FROM estructura_cobro"
	    		+ "	WHERE estructura_cobro.estrcobro_id="+estructura.getEstrcobro_id();
		
		
		System.out.println(SQL);
		
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				
				if(RS.getInt("n")>0){
					return true;
				}
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
		
		return false;
	}

	public static void updateEstructuraCobro(EstructuraCobroVO estructura,
			String usuIDSession) {
		String SQL= ""
	    		+ " UPDATE `estructura_cobro` SET "
	    		+ "		tipo_cambio_id='"+estructura.getTipo_cambio_id()+"' "
	    		+ "		,estrcobro_observaciones='"+estructura.getEstrcobro_observaciones().toUpperCase()+"'"
	    		+ "		,estrcobro_nombre='"+estructura.getEstrcobro_nombre().toUpperCase()+"'"
		    	+ "		,estrcobro_cxa='"+estructura.getEstrcobro_cxa()+"' "
	    		+ "		,estados_vig_novig_id='"+estructura.getEstadoVignoVig().getId()+"' "
	    		+ "		,tipo_estcobro_id='"+estructura.getTipo_estructuraC().getId()+"' "
	    		+ "		,modificador='"+usuIDSession+"'"
	    		+ "		,fecmod=now()"
	    		+ "	WHERE  "
	    		+ "		estructura_cobro.estrcobro_id='"+estructura.getEstrcobro_id()+"' "; 
	    	
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	    
	    disconect();
	    
	}

	public static void disableRangos(EstructuraCobroVO estructura,
			String usuIDSession) {
		getCon();
		String SQL= ""
	    		+ " UPDATE `rango_estructura_cobro` SET "
	    		+ "		estados_vig_novig_id='2' "
	    		+ "		,modificador='"+usuIDSession+"'"
	    		+ "		,fecmod=now()"
	    		+ "	WHERE  "
	    		+ "		rango_estructura_cobro.estrcobro_id='"+estructura.getEstrcobro_id()+"' "; 
	    	
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	    
	    disconect();
	    
	}
	

}
