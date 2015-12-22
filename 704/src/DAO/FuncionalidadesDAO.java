package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.FuncionalidadVO;

public class FuncionalidadesDAO {
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
	
	
	public static ArrayList<FuncionalidadVO> getFuncionalidades(FuncionalidadVO func){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		funcionalidad.func_id"
	    		+ "		, funcionalidad.func_nombre"
	    		+ " FROM funcionalidad"
	    		
	    		+ "	WHERE 1=1  ";
		
		
			SQL+=" ORDER BY funcionalidad.func_nombre ";
		
		System.out.println(SQL);
		ArrayList<FuncionalidadVO> funcionalidades = new ArrayList<FuncionalidadVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				FuncionalidadVO funcionalidad = new FuncionalidadVO();
				
				funcionalidad.setId(RS.getString("func_id"));
				funcionalidad.setNombre(RS.getString("func_nombre"));
			
				funcionalidades.add(funcionalidad);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return funcionalidades;
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
