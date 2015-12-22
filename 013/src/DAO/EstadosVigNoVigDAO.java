package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.EstadosVigNoVigVO;

public class EstadosVigNoVigDAO {
	static Connection conexion=null;
	static Statement state=null;
	
	public EstadosVigNoVigDAO() {
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
	
	public static ArrayList<EstadosVigNoVigVO> getEstados(){
		getCon();
		
		int newID=1;
		
		String SQL = "	SELECT "
		 		+ "				estados_vig_novig.estados_vig_novig_id "
		 		+ "				,estados_vig_novig.estados_vig_novig_nombre "
			      
	    		+ "				FROM estados_vig_novig  ";
	    	
	    System.out.println(SQL);
	    	
	    ArrayList<EstadosVigNoVigVO> estados = new ArrayList<EstadosVigNoVigVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
		    	EstadosVigNoVigVO estado = new EstadosVigNoVigVO(RS.getString("estados_vig_novig_id"));
		    	estado.setNombre(RS.getString("estados_vig_novig_nombre"));
		    	
		    	estados.add(estado);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    return estados;
	   
	}
}
