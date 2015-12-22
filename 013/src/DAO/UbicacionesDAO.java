package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.MaquinaContadorVO;
import VO.UbicacionVO;

public class UbicacionesDAO {
	
	static Connection conexion=null;
	static Statement state=null;
	
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
	
	public static ArrayList<String> getUbicaciones(String id_emp, String estadoVignoVig){
		getCon();
		
		String empresa=null;
		
		String SQL= ""
	    		+ " SELECT ubicacion.ubi_id, ubicacion.ubi_fisica "
	    		+ " FROM ubicacion"
	    		+ "	INNER JOIN direccion on direccion.dire_id=ubicacion.dire_id  "
	    		+ " WHERE direccion.clpr_id = "+id_emp 
				+ "		AND  ubicacion.estados_vig_novig_id='"+estadoVignoVig+"'"; 
    	
		
		System.out.println(SQL);
		ArrayList<String> ubicaciones = new ArrayList<String>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				ubicaciones.add(RS.getString("ubi_id")+";;"+RS.getString("ubi_fisica"));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
	}
	
	public static ArrayList<MaquinaContadorVO> getUbicacionesSinAnexo(String id_emp, String estadoVignoVig){
		getCon();
		
		String SQL= ""
	    		+ " SELECT 	ubicacion.ubi_id"
	    		+ "			, ubicacion.ubi_fisica "
	    		+ "			, ubicacion.ubi_descripcion "
	    		+ "			, tipo_contador_principal.tp_tc_id "
	    		+ "			, tipo_contador_principal.tp_tc_nombre "
	    		+ "			, activos_historia.id_activo "
	    		+ "			, activo_tptc.activo_tptc_id "
	    		
	    		+ " FROM ubicacion"
	    		+ "	INNER JOIN direccion on direccion.dire_id=ubicacion.dire_id  "
	    		+ "	INNER JOIN activos_historia on (activos_historia.empresas_id="+id_emp+" AND DATE_FORMAT(activos_historia.fecha_captura,'%m-%Y')=DATE_FORMAT(now(),'%m-%Y') AND activos_historia.ubi_id=ubicacion.ubi_id)  "
	    		+ "	INNER JOIN producto on (producto.prod_tipo=1 AND producto.func_id=6 AND producto.prod_id=activos_historia.prod_id ) "
	    		+ "	INNER JOIN activo_tptc on activo_tptc.id_activo=activos_historia.id_activo  "
	    		+ "	INNER JOIN tipo_contador_principal on tipo_contador_principal.tp_tc_id=activo_tptc.tp_tc_id  "
	    		+ "	LEFT JOIN ubi_estrcobro ON (ubi_estrcobro.`ubi_id`=ubicacion.`ubi_id` AND ubi_estrcobro.`activo_tptc_id`=activo_tptc.`activo_tptc_id`)   "
	    		  
	    		+ " WHERE direccion.clpr_id = "+id_emp 
	    		+ "		AND  ubicacion.estados_vig_novig_id='"+estadoVignoVig+"'"
	    		+ " 	AND ubi_estrcobro.`ubiestr_id` IS NULL  "
	    		+ "	GROUP BY ubicacion.ubi_id,activos_historia.id_activo,tipo_contador_principal.tp_tc_id ";	
		
		System.out.println(SQL);
		ArrayList<MaquinaContadorVO> ubicaciones = new ArrayList<MaquinaContadorVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				MaquinaContadorVO ubicacion = new MaquinaContadorVO();
				ubicacion.setUbi_id(RS.getString("ubi_id"));
				ubicacion.setUbi_fisica(RS.getString("ubi_fisica"));
				ubicacion.setDescripcion(RS.getString("ubi_descripcion"));
				ubicacion.setTp_tc_id(RS.getString("tp_tc_id"));
				ubicacion.setTp_tc_nombre(RS.getString("tp_tc_nombre"));
				ubicacion.setId_activo(RS.getString("id_activo"));
				ubicacion.setActivo_tptc_id(RS.getString("activo_tptc_id"));
				ubicaciones.add(ubicacion);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
	}
	
	public static ArrayList<MaquinaContadorVO> getUbicacionesConAnexo(String id_anexo,String id_estructura){
		getCon();
		
		String SQL= ""
	    		+ " SELECT 	ubicacion.ubi_id"
	    		+ "			, ubicacion.ubi_fisica "
	    		+ "			, ubicacion.ubi_descripcion "
	    		+ "			, tipo_contador_principal.tp_tc_id "
	    		+ "			, tipo_contador_principal.tp_tc_nombre "
	    		+ "			, activos_historia.id_activo "
	    		+ "			, activo_tptc.activo_tptc_id "
	    		+ "			, ubi_estrcobro.estrcobro_id "
	    		+ " FROM ubi_estrcobro"
	    		+ " INNER JOIN activo_tptc ON (activo_tptc.activo_tptc_id=ubi_estrcobro.activo_tptc_id)"
	    		+ " INNER JOIN ubicacion ON (ubicacion.ubi_id=ubi_estrcobro.ubi_id)"
	    		+ "	INNER JOIN activos_historia ON (activos_historia.id_activo= activo_tptc.id_activo)  "
	    		
	    		+ "	INNER JOIN tipo_contador_principal on tipo_contador_principal.tp_tc_id=activo_tptc.tp_tc_id  "
	    		
	    		+ " WHERE  " 
	    		+ "		  ubi_estrcobro.estados_vig_novig_id='1'"
	    		+ " 	AND ubi_estrcobro.`anc_id`="+id_anexo;
		if(id_estructura!=null) SQL+= " 	AND ubi_estrcobro.`estrcobro_id`="+id_estructura;
		
	    		SQL+= " GROUP BY ubicacion.ubi_id,activos_historia.id_activo,tipo_contador_principal.tp_tc_id,ubi_estrcobro.estrcobro_id";	
		
		System.out.println(SQL);
		ArrayList<MaquinaContadorVO> ubicaciones = new ArrayList<MaquinaContadorVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				MaquinaContadorVO ubicacion=new MaquinaContadorVO();
				if(ubicaciones.contains(new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id")))){
					ubicacion=ubicaciones.get(ubicaciones.indexOf(new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id"))));
				}
				else{
					ubicacion = new MaquinaContadorVO();
				}
				
				ubicacion.setUbi_id(RS.getString("ubi_id"));
				ubicacion.setUbi_fisica(RS.getString("ubi_fisica"));
				ubicacion.setDescripcion(RS.getString("ubi_descripcion"));
				ubicacion.setTp_tc_id(RS.getString("tp_tc_id"));
				ubicacion.setTp_tc_nombre(RS.getString("tp_tc_nombre"));
				ubicacion.setId_activo(RS.getString("id_activo"));
				ubicacion.setActivo_tptc_id(RS.getString("activo_tptc_id"));
				
				String idsEstructuras="";
				if(ubicacion.getEstructurasids()==null)idsEstructuras=RS.getString("estrcobro_id");
				else idsEstructuras=ubicacion.getEstructurasids()+","+RS.getString("estrcobro_id");
				
				ubicacion.setEstructurasids(idsEstructuras);
				
				if(ubicaciones.contains(new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id")))){
					
				}
				else{
					ubicaciones.add(ubicacion);
				}
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
	}
	public static ArrayList<UbicacionVO> getUbicacionesSinAnexo(String id_emp, String estadoVignoVig,String id_anexoIncluido){
		getCon();
		
		String empresa=null;
		
		String SQL= ""
	    		+ " SELECT 	ubicacion.ubi_id"
	    		+ "			, ubicacion.ubi_fisica "
	    		+ "			, ubicacion.ubi_descripcion "
	    		+ " FROM ubicacion"
	    		+ "	INNER JOIN direccion on direccion.dire_id=ubicacion.dire_id  "
	    		+ " WHERE direccion.clpr_id = "+id_emp
	    		+ "		AND (ubicacion.anc_id IS NULL OR ubicacion.anc_id='"+id_anexoIncluido+"') "
	    		+ "		AND  ubicacion.estados_vig_novig_id='"+estadoVignoVig+"' ";
		
		System.out.println(SQL);
		ArrayList<UbicacionVO> ubicaciones = new ArrayList<UbicacionVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				UbicacionVO ubicacion = new UbicacionVO();
				ubicacion.setUbi_id(RS.getString("ubi_id"));
				ubicacion.setUbi_fisica(RS.getString("ubi_fisica"));
				ubicacion.setDescripcion(RS.getString("ubi_descripcion"));
				
				ubicaciones.add(ubicacion);
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
	}
	public static ArrayList<UbicacionVO> getUbicaciones(UbicacionVO ubi){
		getCon();
		
		String SQL= ""
	    		+ " SELECT ubicacion.ubi_id"
	    		+ "		, ubicacion.ubi_fisica "
	    		+ "		, ubicacion.ubi_descripcion "
	    		+ " FROM ubicacion"
	    		+ "	INNER JOIN direccion on direccion.dire_id=ubicacion.dire_id "
	    		+ "	WHERE 1=1  ";
		
		if(ubi.getAnc_id()!=null) SQL+=" AND ubicacion.anc_id= "+ubi.getAnc_id();
		
	    	
		
		System.out.println(SQL);
		ArrayList<UbicacionVO> ubicaciones = new ArrayList<UbicacionVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				UbicacionVO ubicacion = new UbicacionVO();
				ubicacion.setUbi_id(RS.getString("ubi_id"));
				ubicacion.setUbi_fisica(RS.getString("ubi_fisica"));
				ubicacion.setDescripcion(RS.getString("ubi_descripcion"));
				
				ubicaciones.add(ubicacion);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
	}
	
	public static ArrayList<UbicacionVO> getUbicacionesEstructuraVig(String id_estr){
		getCon();
		
		String SQL= ""
	    		+ " SELECT ubi_estrcobro.`estrcobro_id`,ubi_estrcobro.`ubi_id` "
	    		+ "	FROM `ubi_estrcobro` "
	    		+ "	WHERE "
	    		+ "		ubi_estrcobro.`estados_vig_novig_id`=1"
	    		+ "		AND ubi_estrcobro.`estrcobro_id`= "+id_estr;
		
		
	    	
		
		System.out.println(SQL);
		ArrayList<UbicacionVO> ubicaciones = new ArrayList<UbicacionVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				UbicacionVO ubicacion = new UbicacionVO();
				ubicacion.setUbi_id(RS.getString("ubi_id"));
				
				ubicaciones.add(ubicacion);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
	}
	
	
	/*public static void setAnexoCToUbis(AnexoContratoVO anexoContrato){
		getCon();
		
		String idanexo=anexoContrato.getAnc_id();
		String ids_ubis="'0'";
		for(UbicacionVO ubicacion : anexoContrato.getUbicaciones()){
			ids_ubis+=",'"+ubicacion.getUbi_id()+"'";
		}
		
		
		String SQL= ""
	    		+ " UPDATE `ubicacion` SET ubicacion.`anc_id`='"+idanexo+"' WHERE ubicacion.`UBI_ID` IN ("+ids_ubis+") ";
		
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	   
	}*/
	
	
	
	
	
	public static void disconect(){
		try {
			state.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
