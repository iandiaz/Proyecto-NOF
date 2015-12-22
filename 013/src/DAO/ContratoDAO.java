package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Constantes.Constantes;
import VO.ContratoVO;
import VO.EmpresaVO;

public class ContratoDAO {
	
	static Connection conexion=null;
	static Statement state=null;
	
	public ContratoDAO() {
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
	
	public static void getContrato(String contrato_id,String emp_id,ContratoVO contrato,EmpresaVO empresa){
		getCon();
		
		int newID=1;
		
		String SQL = "	SELECT "
				 + "			contrato.contrato_nombre"
				 + "			,contrato.contrato_id  "
		 		+ "				,estados_vig_novig.estados_vig_novig_id "
		 		+ "				,es.estadocontrato_nombre"
		 		+ "				,estados_vig_novig.estados_vig_novig_nombre "
		 		+ "				,contrato.contrato_id "
		 		+ "				, DATE_FORMAT(contrato.fecha_inicio,'%d-%m-%Y') as fecha_inicio1 "
		 	    + "  			,`empresas`.`empresas_nombrenof` "
		 	    + "				,`empresas`.`empresas_id`"
		 	    + "				,empresas.empresas_razonsocial"
		 	    + "				,empresas.empresas_rut"
		 	    + "				,grupos.grupos_nombre  "
			      
	    		+ "				FROM contrato  "
	    		+ "				INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=contrato.estados_vig_novig_id "
	    		+ "				INNER JOIN estadocontrato es ON contrato.estadocontrato_id = es.estadocontrato_id"
	    		+ " 			INNER JOIN `contrato_empresa` ON `contrato_empresa`.`contrato_id` = `contrato`.`contrato_id` "
	            + " 			INNER JOIN `empresas` ON `empresas`.`empresas_id` = `contrato_empresa`.`empresas_id` "
	            + " 			INNER JOIN `grupos` ON `grupos`.`grupos_id` = `empresas`.`grupos_id` "
	            + "				WHERE contrato.contrato_id="+contrato_id+" AND `empresas`.`empresas_id`="+emp_id; 
		 
	    System.out.println(SQL);
	    	
		
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
		    	
		    	contrato.setContrato_nombre(RS.getString("contrato_nombre"));
		    	empresa.setEmpresas_id(emp_id);
		    	empresa.setGrupos_nombre(RS.getString("grupos_nombre"));
		    	empresa.setEmpresas_nombrenof(RS.getString("empresas_nombrenof"));
		    	empresa.setEmpresas_razonsocial(RS.getString("empresas_razonsocial"));
		    	empresa.setEmpresas_rut(RS.getString("empresas_rut"));
		    	
		    	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	   
	}

}
