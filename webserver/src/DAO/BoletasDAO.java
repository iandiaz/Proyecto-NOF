package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Constantes.Constantes;
import VO.BoletaDetalleVO;
import VO.BoletaVO;

public class BoletasDAO {
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
	
	
	public static boolean updateBoleta(BoletaVO boleta,String id_usu_mod){
		getCon();
		
		/**
		 * UPDATE ENCABEZADO DE BOLETA 
		 */
		
		String SQL= "UPDATE `831` SET "
				+ "		fecmod=now()	"
				+ "		,modificador= "+id_usu_mod; 
			if(boleta.getId_dte()!=null) SQL+= "	,	id_dte = "+boleta.getId_dte()+" ";
			if(boleta.getFolio()+""!=null) SQL+= "	,	`831_folio` = '"+boleta.getFolio()+"' ";
		
			SQL+= " WHERE `id_831` = "+boleta.getId();
				
		
		System.out.println(SQL);
		
		try {
			
			state.executeUpdate(SQL);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false; 
		}
	    
	    disconect();
	    
	    return true;
	}
	
	public static String insertBoleta(BoletaVO boleta){
		getCon();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		
		/**
		 * INSERTAMOS ENCABEZADO DE BOLETA 
		 */
		String SQL= ""
	    		+ " INSERT INTO `831` ("
	    		+ "			`831_RUTCliente` "
	    		+ "			,`831_RSCliente` "
	    		+ "			,`831_GiroCliente` "
	    		+ "			,`831_DirCliente` "
	    		+ "			,`831_ComCliente` "
	    		+ "			,`831_CiuCliente` "
	    		+ "			,`831_Email` "
	    		+ "			,`831_n_proceso` "
	    		+ "			,`831_Neto` "
	    		+ "			,`831_fecha` "
	    		+ "			,`831_webservice` "
	    		+ "			,`id_clpr` "
	    		+ "			,`831_empresa_emisora` "
	    		
	    		+ "		) "
	    		+ "		VALUES ("
	    		+ "			'"+boleta.getRUTCliente()+"'"
	    		+ "			,'"+boleta.getRSCliente()+"'"
	    		+ "			,'"+boleta.getGiroCliente()+"'"
	    		+ "			,'"+boleta.getDirCliente()+"'"
	    		+ "			,'"+boleta.getComCliente()+"'"
	    		+ "			,'"+boleta.getCiuCliente()+"'"
	    		+ "			,'"+boleta.getEmail()+"'"
	    		+ "			,'"+boleta.getN_proceso()+"'"
	    		+ "			,'"+boleta.getMonto_precio()+"'"
	    		+ "			,'"+formatDate.format(boleta.getFecha().getTime()) +"'"
	    		+ "			,'1' "
	    		+ "			,'"+boleta.getId_clpr()+"'"
	    		+ "			,'"+boleta.getEmpresa_emisora()+"'"
	    		
	    		+ "		) "
	    		+ "	  ";
		
		System.out.println(SQL);
		
    	String id_boleta_last="";
		try {
			
			state.executeUpdate(SQL,Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = null;
   		  	generatedKeys = state.getGeneratedKeys();
   		 	  if (generatedKeys.next()) {
	   			  id_boleta_last=generatedKeys.getString(1);
	   			  
	   		  }
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
	    
	    disconect();
	    
	    return id_boleta_last;
	}
	
	
	public static boolean insertBoletaDetalle(BoletaDetalleVO boletaDetalle){
		getCon();
		
		/**
		 * INSERTAMOS ENCABEZADO DE BOLETA 
		 */
		String SQL= ""
	    		+ " INSERT INTO `detalle_831` ("
	    		+ "			`id_831` "
	    		+ "			,`det_831_Precio` "
	    		+ "			,`prod_id` "
	    		+ "		) "
	    		+ "		VALUES ("
	    		+ "			'"+boletaDetalle.getId_boleta()+"'"
	    		+ "			,'"+boletaDetalle.getPrecio()+"'"
	    		+ "			,'"+boletaDetalle.getId_producto()+"'"
	    		
	    		+ "		) "
	    		+ "	  ";
		
		System.out.println(SQL);
		
    	try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false; 
		}
	    
	    disconect();
	    
	    return true;
	}
	
	public static String findNextFolio(BoletaVO boleta){
		getCon();
		
		/**
		 * BUSCAMOS FOLIOS DISPONIBLES
		 */
		String SQL = "select `dte_encabezado`.`Folio` from `dte_encabezado` where `dte_encabezado`.`TipoDTE`='"+boleta.getTipoDTE()+"' and  `dte_encabezado`.`empresa_emisora_id`='"+boleta.getEmpresa_emisora()+"' ORDER BY `dte_encabezado`.`Folio` DESC limit 1 "; 
		String folio="0";
		   
		System.out.println(SQL);
		
    	try {
    		ResultSet RS_FINDFOLIO = state.executeQuery(SQL);
    		if(RS_FINDFOLIO.next()){
		    	folio=(RS_FINDFOLIO.getInt("Folio")+1)+"";
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
	    
	    disconect();
	    
	    return folio;
	}
	
	
	public static String getFirstFolio(BoletaVO boleta){
		getCon();
		
		/**
		 * BUSCAMOS FOLIOS DISPONIBLES
		 */
		String SQL = "select admfolios_desde from `adm_folios` where `adm_folios`.`empresas_id`="+boleta.getEmpresa_emisora()+" and `adm_folios`.`tipodte`='"+boleta.getTipoDTE()+"'" ; 
		String folio=null;
		   
		System.out.println(SQL);
		
    	try {
    		ResultSet RS_FINDFOLIOADM = state.executeQuery(SQL);
 		    if(RS_FINDFOLIOADM.next()){folio=RS_FINDFOLIOADM.getString("admfolios_desde");}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
	    
	    disconect();
	    
	    return folio;
	}
	
	
	
	
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
