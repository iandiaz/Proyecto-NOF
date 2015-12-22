package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.PeriodoTcVO;

public class PeriodosTcDAO {
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
	
	public static ArrayList<PeriodoTcVO> getPeriodosTc(PeriodoTcVO periodo){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		periodos_tc.peri_tc_id"
	    		+ "		, periodos_tc.peri_tc_correlativo"
	    		+ "		, DATE_FORMAT(periodos_tc.peri_tc_fdesde,'%d-%m-%Y') AS peri_tc_fdesdeFormat "
	    		+ "		, DATE_FORMAT(periodos_tc.peri_tc_fhasta,'%d-%m-%Y') AS peri_tc_fhastaFormat "
	    		+ "		, periodos_tc.estados_vig_novig_id "
	    		+ "		, estados_vig_novig.estados_vig_novig_nombre "
	    		+ " FROM periodos_tc"
	    		+ "	INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=periodos_tc.estados_vig_novig_id"
	    		+ "	WHERE 1=1  ";
		
		if(periodo.getEmpresa()!=null) SQL+=" AND periodos_tc.peri_tc_id_emp= "+periodo.getEmpresa().getEmpresas_id();
		if(periodo.getEstadoVigNoVig()!=null) SQL+=" AND periodos_tc.estados_vig_novig_id= "+periodo.getEstadoVigNoVig().getId();
		if(periodo.getEstado_periodo_id()!=null) SQL+=" AND periodos_tc.peri_tc_id_estperiodo= "+periodo.getEstado_periodo_id();
		
			SQL+=" ORDER BY periodos_tc.peri_tc_correlativo ";
		
		System.out.println(SQL);
		ArrayList<PeriodoTcVO> periodos = new ArrayList<PeriodoTcVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				PeriodoTcVO peri = new PeriodoTcVO();
				
				peri.setId(RS.getString("peri_tc_id"));
				peri.setCorrelativo(RS.getString("peri_tc_correlativo"));
				peri.setFdesde(RS.getString("peri_tc_fdesdeFormat"));
				peri.setFhasta(RS.getString("peri_tc_fhastaFormat"));
				
				periodos.add(peri);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return periodos;
	}
	
	public static ArrayList<PeriodoTcVO> getPeriodosTcAnteriores(String id_per,String id_emp){
		getCon();
		
		//buscamos periodos anteriores
			String SQL = "SELECT "
					+ "		`periodos_tc`.`peri_tc_id`,"
					+ "		`periodos_tc`.`peri_tc_correlativo`, "
					+ "		DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y') AS peri_tc_fdesdeFormat,"
					+ "		DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') AS peri_tc_fhastaFormat "
					+ "		FROM"
					+ "			`periodos_tc`"
					+ "		WHERE "
					+ "			`periodos_tc`.`peri_tc_id`< "+id_per
					+ "			AND `periodos_tc`.`peri_tc_id_emp` = "+id_emp
					+ "			AND `periodos_tc`.`estados_vig_novig_id` = 1 "
					+ "			ORDER BY `periodos_tc`.`peri_tc_correlativo` DESC LIMIT 6";
		
		
			
		System.out.println(SQL);
		ArrayList<PeriodoTcVO> periodos = new ArrayList<PeriodoTcVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				PeriodoTcVO peri = new PeriodoTcVO();
				
				peri.setId(RS.getString("peri_tc_id"));
				peri.setCorrelativo(RS.getString("peri_tc_correlativo"));
				peri.setFdesde(RS.getString("peri_tc_fdesdeFormat"));
				peri.setFhasta(RS.getString("peri_tc_fhastaFormat"));
				
				periodos.add(peri);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return periodos;
	}
	
	public static ArrayList<String> getActivosPeriodo(String id_per,String empresas_id){
		getCon();
		
		String SQL = ""
	    		+ " SELECT a.id_activo   "
	    		+ " FROM activos_historia a"
	    		+ " INNER JOIN producto p ON p.PROD_ID = a.PROD_ID "
	    		+ " INNER JOIN funcionalidad f ON f.FUNC_ID = p.FUNC_ID"
	    		+ " INNER JOIN (SELECT 		`periodos_tc`.`peri_tc_fdesde`,`periodos_tc`.`peri_tc_fhasta`	,periodos_tc.peri_tc_correlativo	"
	    		+ "					FROM			`periodos_tc`		"
	    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` ="+id_per
	    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+empresas_id
	    		+ " ) per "
	    		+ " WHERE p.FUNC_ID = 6  "
	    		+ " AND a.empresas_id = "+empresas_id+""
	    		+ " AND a.`fecha_captura`>=per.peri_tc_fdesde AND a.`fecha_captura`<=peri_tc_fhasta"
	    		+ "	GROUP BY a.`id_activo`  "
	    		+ " ORDER BY p.PROD_DESC_CORTO ";
	    System.out.println(SQL);
	    ArrayList<String> activos = new ArrayList<String>();
	    
	    try {
			ResultSet RS = state.executeQuery(SQL);
			   
			 while(RS.next()){
				 activos.add(RS.getString("id_activo"));
			    	
			    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return activos;
	}
	
	
	public static ArrayList<String> getActivosPeriodoHis(String id_per,String empresas_id,String tipo_cambio_id){
		getCon();
		
		String SQL = ""
	    		+ " SELECT a.id_activo as ALT_ID,a.serie, p.PROD_DESC_CORTO, e.empresas_nombrenof,per.peri_tc_correlativo,"
	    		+ "		u.ubi_fisica, d.DIRE_DIRECCION, e.empresas_nombrenof"
	    		+ "		, `estructura_cobro`.`estrcobro_id`"
	    		+ "		,`estructura_cobro`.estrcobro_nombre"
	    		+ "		,a.ubi_id    "
	    		+ "		,`anexo_contrato`.anc_id"
	    		+ "		,`tipo_contador_principal`.tp_tc_id"
	    		+ "		,`tipo_contador_principal`.tp_tc_nombre"
	    		+ "		,`estructura_cobro`.`estrcobro_id` "
	    		
	    		+ " FROM activos_historia a"
	    		+ " INNER JOIN producto p ON p.PROD_ID = a.PROD_ID "
	    		+ " INNER JOIN funcionalidad f ON f.FUNC_ID = p.FUNC_ID"
	    		+ " INNER JOIN ubicacion u ON u.UBI_ID = a.UBI_ID"
	    		
				+ " INNER JOIN `activo_tptc` ON (`activo_tptc`.`estados_vig_novig_id`=1 AND `activo_tptc`.`id_activo`=a.`id_activo`) "
				+ " INNER JOIN `tipo_contador_principal` ON ( `tipo_contador_principal`.`tp_tc_id`=activo_tptc.`tp_tc_id`) "

	    		
	    		+ " INNER JOIN `ubi_estrcobro` ON (`ubi_estrcobro`.`estados_vig_novig_id`=1 AND `ubi_estrcobro`.`ubi_id`=u.`ubi_id` AND ubi_estrcobro.`activo_tptc_id`=activo_tptc.`activo_tptc_id` ) "
	    		+ " INNER JOIN `estructura_cobro` ON (`estructura_cobro`.`estados_vig_novig_id`=1 AND `estructura_cobro`.`estrcobro_id`=ubi_estrcobro.`estrcobro_id`) "
	    		+ " INNER JOIN `anexo_contrato` ON (`anexo_contrato`.`anc_id`=estructura_cobro.`anc_id`) "
		    	
	    		+ " INNER JOIN direccion d ON d.DIRE_ID = u.DIRE_ID"
	    		+ " INNER JOIN empresas e ON e.empresas_id = d.CLPR_ID"
	    		+ " INNER JOIN (SELECT 		`periodos_tc`.`peri_tc_fdesde`,`periodos_tc`.`peri_tc_fhasta`	,periodos_tc.peri_tc_correlativo	"
	    		+ "					FROM			`periodos_tc`		"
	    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` ="+id_per
	    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+empresas_id
	    		+ " ) per "
	    		+ " INNER JOIN (SELECT 		MIN(`periodos_tc`.`peri_tc_fdesde`) as peri_tc_fdesde 	"
	    		+ "					FROM			`periodos_tc`		"
	    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` < "+id_per
	    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+empresas_id+" "
	    		+ "					LIMIT 6 "
	    		+ " ) perFinal "
	    		+ " WHERE p.FUNC_ID = 6 AND estructura_cobro.tipo_cambio_id="+tipo_cambio_id+" "
	    		+ " AND a.empresas_id = "+empresas_id+""
	    		+ " AND a.fecha_captura >=IF(perFinal.peri_tc_fdesde IS NULL,per.peri_tc_fdesde,perFinal.peri_tc_fdesde) AND a.`fecha_captura`<=per.peri_tc_fhasta"
	    		+ "	GROUP BY a.`id_activo`,`estructura_cobro`.`estrcobro_id`   "
	    		+ " ORDER BY d.DIRE_DIRECCION,u.ubi_fisica, p.PROD_DESC_CORTO ";
	    System.out.println(SQL);
	    ArrayList<String> activos = new ArrayList<String>();
	    
	    try {
			ResultSet RS = state.executeQuery(SQL);
			   
			 while(RS.next()){
				 activos.add(RS.getString("ALT_ID")
			    			+";;"+RS.getString("PROD_DESC_CORTO")+" - "+RS.getString("serie")
			    			+";;"+RS.getString("ubi_fisica")
			    			+";;"+RS.getString("DIRE_DIRECCION")
			    			+";;"+RS.getString("empresas_nombrenof")
			    			+";;"+RS.getString("tp_tc_nombre")
			    			+";;"+RS.getString("tp_tc_id")
			    			+";;"+RS.getString("ubi_id")
			    			+";;"+RS.getString("anc_id")
			    			+";;"+RS.getString("estrcobro_id")
			    			
			    			
			    			);
			    	
			    }
			 
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return activos;
	}
	public static ArrayList<String> getActivosPeriodoHis(String id_per,String empresas_id,String tipo_cambio_id,String id_activos){
		getCon();
		
		String SQL = ""
	    		+ " SELECT a.id_activo as ALT_ID,a.serie, p.PROD_DESC_CORTO, e.empresas_nombrenof,per.peri_tc_correlativo,"
	    		+ "		u.ubi_fisica, d.DIRE_DIRECCION, e.empresas_nombrenof"
	    		+ "		, `estructura_cobro`.`estrcobro_id`"
	    		+ "		,`estructura_cobro`.estrcobro_nombre"
	    		+ "		,a.ubi_id    "
	    		+ "		,`anexo_contrato`.anc_id"
	    		+ "		,`tipo_contador_principal`.tp_tc_id"
	    		+ "		,`tipo_contador_principal`.tp_tc_nombre"
	    		+ "		,`estructura_cobro`.`estrcobro_id` "
	    		
	    		+ " FROM activos_historia a"
	    		+ " INNER JOIN producto p ON p.PROD_ID = a.PROD_ID "
	    		+ " INNER JOIN funcionalidad f ON f.FUNC_ID = p.FUNC_ID"
	    		+ " INNER JOIN ubicacion u ON u.UBI_ID = a.UBI_ID"
	    		
				+ " INNER JOIN `activo_tptc` ON (`activo_tptc`.`estados_vig_novig_id`=1 AND `activo_tptc`.`id_activo`=a.`id_activo`) "
				+ " INNER JOIN `tipo_contador_principal` ON ( `tipo_contador_principal`.`tp_tc_id`=activo_tptc.`tp_tc_id`) "

	    		
	    		+ " INNER JOIN `ubi_estrcobro` ON (`ubi_estrcobro`.`estados_vig_novig_id`=1 AND `ubi_estrcobro`.`ubi_id`=u.`ubi_id` AND ubi_estrcobro.`activo_tptc_id`=activo_tptc.`activo_tptc_id` ) "
	    		+ " INNER JOIN `estructura_cobro` ON (`estructura_cobro`.`estados_vig_novig_id`=1 AND `estructura_cobro`.`estrcobro_id`=ubi_estrcobro.`estrcobro_id`) "
	    		+ " INNER JOIN `anexo_contrato` ON (`anexo_contrato`.`anc_id`=estructura_cobro.`anc_id`) "

	    		+ " INNER JOIN direccion d ON d.DIRE_ID = u.DIRE_ID"
	    		+ " INNER JOIN empresas e ON e.empresas_id = d.CLPR_ID"
	    		+ " INNER JOIN (SELECT 		`periodos_tc`.`peri_tc_fdesde`,`periodos_tc`.`peri_tc_fhasta`	,periodos_tc.peri_tc_correlativo	"
	    		+ "					FROM			`periodos_tc`		"
	    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` ="+id_per
	    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+empresas_id
	    		+ " ) per "
	    		+ " INNER JOIN (SELECT 		MIN(`periodos_tc`.`peri_tc_fdesde`) as peri_tc_fdesde 	"
	    		+ "					FROM			`periodos_tc`		"
	    		+ "					WHERE 			`periodos_tc`.`peri_tc_id` < "+id_per
	    		+ "						AND `periodos_tc`.`peri_tc_id_emp` = "+empresas_id+" "
	    		+ "					LIMIT 6 "
	    		+ " ) perFinal "
	    		+ " WHERE p.FUNC_ID = 6 AND estructura_cobro.tipo_cambio_id="+tipo_cambio_id+" "
	    		+ " AND a.empresas_id = "+empresas_id+""
	    		+ " AND a.fecha_captura >=IF(perFinal.peri_tc_fdesde IS NULL,per.peri_tc_fdesde,perFinal.peri_tc_fdesde) AND a.`fecha_captura`<=per.peri_tc_fhasta"
	    		+ " AND a.id_activo IN ("+id_activos+") "
		    	
	    		+ "	GROUP BY a.`id_activo`,`estructura_cobro`.`estrcobro_id`   "
	    		+ " ORDER BY d.DIRE_DIRECCION,u.ubi_fisica, p.PROD_DESC_CORTO ";
	    System.out.println(SQL);
	    ArrayList<String> activos = new ArrayList<String>();
	    
	    try {
			ResultSet RS = state.executeQuery(SQL);
			   
			 while(RS.next()){
				 activos.add(RS.getString("ALT_ID")
			    			+";;"+RS.getString("PROD_DESC_CORTO")+" - "+RS.getString("serie")
			    			+";;"+RS.getString("ubi_fisica")
			    			+";;"+RS.getString("DIRE_DIRECCION")
			    			+";;"+RS.getString("empresas_nombrenof")
			    			+";;"+RS.getString("tp_tc_nombre")
			    			+";;"+RS.getString("tp_tc_id")
			    			+";;"+RS.getString("ubi_id")
			    			+";;"+RS.getString("anc_id")
			    			+";;"+RS.getString("estrcobro_id")
			    			
			    			
			    			);
			    	
			    }
			 
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return activos;
	}
	
	public static void disconect(){
		try {
			state.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
