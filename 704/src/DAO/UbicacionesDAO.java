package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
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
	
	public static ArrayList<UbicacionVO> getDirecciones(UbicacionVO ubi){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		ubicacion.ubi_id"
	    		+ "		, ubicacion.ubi_fisica"
	    
	    		+ " FROM ubicacion"
	    		
	    		+ "	WHERE 1=1  ";
		
		if(ubi.getDireccion()!=null) SQL+=" AND ubicacion.dire_id= "+ubi.getDireccion().getId();
		if(ubi.getEstadoVigNoVig()!=null) SQL+=" AND ubicacion.estados_vig_novig_id= "+ubi.getEstadoVigNoVig().getId();
		
			SQL+=" ORDER BY ubicacion.ubi_fisica ";
		
		System.out.println(SQL);
		ArrayList<UbicacionVO> ubicaciones = new ArrayList<UbicacionVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				UbicacionVO ubicacion = new UbicacionVO();
				
				ubicacion.setUbi_id(RS.getString("ubi_id"));
				
				ubicacion.setUbi_fisica(RS.getString("ubi_fisica"));
			
				ubicaciones.add(ubicacion);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return ubicaciones;
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
