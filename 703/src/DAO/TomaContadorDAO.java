package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;

public class TomaContadorDAO {
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
	
	
	public static ArrayList<String> setContadorValues(String id_per,ArrayList<String> cont_activo,ArrayList<String> cont_values,ArrayList<String> cont_difs){
		getCon();
		
		String SQL = "select * from `toma_contador` "
					+ "		where `toma_contador`.`estados_vig_novig_id`=1 and `tc_periodo_id` = '"+id_per+"'   ";

	    System.out.println(SQL);
	    ArrayList<String> activos = new ArrayList<String>();
	    
	    try {
			ResultSet RS = state.executeQuery(SQL);
			   
			 while(RS.next()){
				 	cont_activo.add(RS.getString("tc_activo_id")+";;"+RS.getString("tp_tc_id"));
					cont_values.add(RS.getString("tc_valor"));
					cont_difs.add(RS.getString("tc_valor_diferencia"));
			    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return activos;
	}
	
	
	public static String getContadorDif(String id_activo,String id_per){
		getCon();
		
		String SQL = "select * from `toma_contador` "
				+ "		where `toma_contador`.`estados_vig_novig_id`=1 and `tc_periodo_id` = '"+id_per+"' AND  tc_activo_id="+id_activo+"  ";

		System.out.println(SQL);
		String contador_dif="0";
		
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			   
			 if(RS.next()){
				 contador_dif=RS.getString("tc_valor_diferencia");
				 	
			    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		
		disconect();
		  
		return contador_dif;
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
