package DAO;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.TipoTcVO;

public class TiposTcDAO {
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
	
	
	public static ArrayList<TipoTcVO> getTiposTc(String id_Activo){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		activo_tptc.activo_tptc_id"
	    		+ "		, activo_tptc.tp_tc_id"
	    		+ "		, activo_tptc.id_activo "
	    		+ "		, tipo_toma_contador.tp_tc_nombre "
	    		+ " FROM activo_tptc "
	    		+ "	INNER JOIN tipo_toma_contador ON tipo_toma_contador.tp_tc_id=activo_tptc.tp_tc_id "
	    		+ "	WHERE activo_tptc.estados_vig_novig_id=1 AND activo_tptc.id_activo="+id_Activo;
		
		System.out.println(SQL);
		ArrayList<TipoTcVO> tipostc = new ArrayList<TipoTcVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				TipoTcVO tipoTC = new TipoTcVO();
				tipoTC.setId(RS.getString("tp_tc_id"));
				tipoTC.setNombre(RS.getString("tp_tc_nombre"));
				
				tipostc.add(tipoTC);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return tipostc;
	}
	
	
	public static ArrayList<TipoTcVO> getTiposTc(){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		tipo_toma_contador.tp_tc_id"
	    		+ "		, tipo_toma_contador.tp_tc_nombre"
	    		+ " FROM tipo_toma_contador ";
	    	
		System.out.println(SQL);
		ArrayList<TipoTcVO> tipostc = new ArrayList<TipoTcVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				TipoTcVO tipoTC = new TipoTcVO();
				tipoTC.setId(RS.getString("tp_tc_id"));
				tipoTC.setNombre(RS.getString("tp_tc_nombre"));
				
				tipostc.add(tipoTC);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return tipostc;
	}
	public static void insertTipoTcActivo(String id_activo,String tp_tc_id) throws UnsupportedEncodingException{
		getCon();
		
		String SQL="INSERT INTO activo_tptc (tp_tc_id,id_activo) " + 
				"		VALUES ('"+tp_tc_id+"','"+id_activo+"')"; 
				
		System.out.println(SQL);
		try {
			 state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
	
		
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
