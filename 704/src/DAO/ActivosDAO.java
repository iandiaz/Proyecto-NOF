package DAO;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.ConstantesBIRT;
import VO.ActivoVO;

public class ActivosDAO {
	private static Connection conexion=null;
	private static Statement state=null;
	
	public static ArrayList<ActivoVO> getActivos(ActivoVO activo) throws UnsupportedEncodingException{
		getCon();
		
		String SQL="SELECT" + 
				"		ACTIVO.ALT_ID," + 
				"		ACTIVO.ALT_SERIE," + 
				"		ACTIVO.ALT_ESTADO_PRODUCTO, " +
				"		ACTIVO.PROD_ID" +
				" 	FROM" + 
				"		ACTIVO"
				+ " WHERE 1=1 ";
		
		if(activo.getProd_id()!=null) SQL+=" AND ACTIVO.PROD_ID="+activo.getProd_id();
		
		
		System.out.println(SQL);
		ArrayList<ActivoVO> activos = new ArrayList<ActivoVO>();
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			while(RS.next()){
				
				ActivoVO activo_ = new ActivoVO();
				activo_.setId(RS.getString("ALT_ID"));
				activo_.setProd_id(RS.getString("PROD_ID"));
				activo_.setSerie(RS.getString("ALT_SERIE"));
				activo_.setEstado_prod(RS.getString("ALT_ESTADO_PRODUCTO"));
				
				activos.add(activo_);
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
		return activos;
		
	}
	
	public static ActivoVO getActivo(String id_activo) throws UnsupportedEncodingException{
		getCon();
		
		String SQL="SELECT" + 
				"		ACTIVO.ALT_ID," + 
				"		ACTIVO.ALT_SERIE," + 
				"		ACTIVO.ALT_ESTADO_PRODUCTO, " +
				"		ACTIVO.PROD_ID" +
				" 	FROM" + 
				"		ACTIVO"
				+ " WHERE 1=1 AND ACTIVO.ALT_ID="+id_activo;
		
		System.out.println(SQL);
		ActivoVO activo = new ActivoVO();
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				
				activo.setId(RS.getString("ALT_ID"));
				activo.setProd_id(RS.getString("PROD_ID"));
				activo.setSerie(RS.getString("ALT_SERIE"));
				activo.setEstado_prod(RS.getString("ALT_ESTADO_PRODUCTO"));
				
				
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
		return activo;
		
	}
	
	
	 
	private static void getCon() {
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conexion=(Connection) DriverManager.getConnection("jdbc:sqlserver://"+ConstantesBIRT.SERVER+";databaseName="+ConstantesBIRT.DBNAME+";user="+ConstantesBIRT.USER+";password="+ConstantesBIRT.PASS+";useUnicode=true;characterEncoding=UTF-8");
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
}
