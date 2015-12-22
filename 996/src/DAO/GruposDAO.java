package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.GrupoVO;

public class GruposDAO {
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
	
	public static ArrayList<String> getGruposVigentesParaSelect(){
		getCon();
		
		String SQL= "SELECT "
	    		+ "		`grupos`.`grupos_id`,"
	    		+ "		`grupos`.`grupos_nombre` "
	    	
	    		+ "	FROM"
	    		+ "		`grupos`"
	    		+ "	WHERE"
	    		+ "		`grupos`.`estados_vig_novig_id` = 1"
	    		+ "	ORDER BY"
	    		+ "		`grupos`.`grupos_nombre`   ";
		
		System.out.println(SQL);
		ArrayList<String> grupos = new ArrayList<String>();
	    
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			 while(RS.next()){        	   
			   	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
				 grupos.add(RS.getString("grupos_id")+";;"+RS.getString("grupos_nombre"));
				    }
			 RS.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return grupos;
	}
	public static ArrayList<GrupoVO> getGruposVigentesParaSelect(GrupoVO grupo){
		getCon();
		
		String SQL= "SELECT "
	    		+ "		`grupos`.`grupos_id`,"
	    		+ "		`grupos`.`grupos_nombre` "
	    	
	    		+ "	FROM"
	    		+ "		`grupos`"
	    		+ "	WHERE"
	    		+ "		`grupos`.`estados_vig_novig_id` = 1"
	    		+ "	ORDER BY"
	    		+ "		`grupos`.`grupos_nombre`   ";
		ArrayList<GrupoVO> grupos = new ArrayList<GrupoVO>();
	    
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			 while(RS.next()){        	   
			   	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
				 grupos.add( new GrupoVO(RS.getString("grupos_id"),RS.getString("grupos_nombre")));
				    }
			 RS.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return grupos;
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
