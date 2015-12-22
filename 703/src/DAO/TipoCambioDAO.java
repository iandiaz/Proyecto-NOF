package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;

public class TipoCambioDAO {
	static Connection conexion=null;
	static Statement state=null;
	
	public TipoCambioDAO() {
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
	
	public static ArrayList<String> getTipoCambios(){
		getCon();
		
		String SQL= ""
	    		+ " SELECT tipo_cambio.tipo_cambio_id, tipo_cambio.tipo_cambio_nombre "
	    		+ " FROM tipo_cambio";
		
		System.out.println(SQL);
		ArrayList<String> tipocambios = new ArrayList<String>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				tipocambios.add(RS.getString("tipo_cambio_id")+";;"+RS.getString("tipo_cambio_nombre"));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return tipocambios;
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
