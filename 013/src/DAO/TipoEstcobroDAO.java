package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;

public class TipoEstcobroDAO {
	static Connection conexion=null;
	static Statement state=null;
	
	public TipoEstcobroDAO() {
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
	
	public static ArrayList<String> getTipoEstcobro(){
		getCon();
		
		String SQL= ""
	    		+ " SELECT tipo_estcobro.tipo_estcobro_id, tipo_estcobro.tipo_estcobro_nombre "
	    		+ " FROM tipo_estcobro";
		
		System.out.println(SQL);
		ArrayList<String> tipoestr = new ArrayList<String>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				tipoestr.add(RS.getString("tipo_estcobro_id")+";;"+RS.getString("tipo_estcobro_nombre"));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return tipoestr;
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
