package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.EstadosVigNoVigVO;
import VO.RangoEstructuraCobroVO;

public class RangoEstructuraCobroDAO {
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
	
	public static void disconect(){
		try {
			state.close();
			conexion.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static int getNewId(){
		getCon();
		
		int newID=1;
		
		String SQL= ""
	    		+ " SELECT MAX(rango_estructura_cobro.rango_id) AS rango_id "
	    		+ " FROM rango_estructura_cobro";
	    	
		
		System.out.println(SQL);
		ArrayList<String> ubicaciones = new ArrayList<String>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				newID=(RS.getInt("rango_id")+1);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return newID;
	}
	
	
	public static ArrayList<RangoEstructuraCobroVO> getRangosEstructuraDeCobro(RangoEstructuraCobroVO rango){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		rango_estructura_cobro.rango_id"
	    		+ "		, rango_estructura_cobro.rango_inicial "
	    		+ "		, IF(rango_estructura_cobro.rango_final IS NULL,'',rango_estructura_cobro.rango_final) as rango_final  "
	    		+ "		, rango_estructura_cobro.rango_costo_fijo "
	    		+ "		, rango_estructura_cobro.rango_costo_variable "
	    		+ "		, rango_estructura_cobro.estados_vig_novig_id "
	    		
	    		+ " FROM rango_estructura_cobro"
	    		
	    		+ "	WHERE rango_estructura_cobro.estados_vig_novig_id=1 ";
		
		if(rango.getEstrcobro_id()!=null) SQL+=" AND rango_estructura_cobro.estrcobro_id= "+rango.getEstrcobro_id();
		
	    	
		
		System.out.println(SQL);
		ArrayList<RangoEstructuraCobroVO> rangos = new ArrayList<RangoEstructuraCobroVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				RangoEstructuraCobroVO rango_ = new RangoEstructuraCobroVO();
				rango_.setRango_id(RS.getString("rango_id"));
				rango_.setRango_inicial(RS.getString("rango_inicial"));
				
				rango_.setRango_final(RS.getString("rango_final"));
				
				rango_.setRango_costo_fijo(RS.getString("rango_costo_fijo"));
				rango_.setRango_costo_variable(RS.getString("rango_costo_variable"));
				
				EstadosVigNoVigVO estadosVignoVig = new EstadosVigNoVigVO();
				estadosVignoVig.setId(RS.getString("estados_vig_novig_id"));
				
				rango_.setEstadoVignoVig(estadosVignoVig);
				
				
				rangos.add(rango_);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return rangos;
	}
	
	public static String insertRangoCobro(RangoEstructuraCobroVO rango, String estrcobro_id,String id_usu_crea){
		 getCon();
		
		String id=null;
		String rango_final=rango.getRango_final();
		if(rango_final==null || rango_final.equals(""))rango_final="NULL";
		else rango_final="'"+rango_final+"'";
		String SQL= ""
	    		+ " INSERT INTO `rango_estructura_cobro` ("
	    		+ "		estrcobro_id"
	    		+ "		,rango_inicial"
	    		+ "		,rango_final"
	    		+ "		,rango_costo_fijo"
	    		+ "		,rango_costo_variable"
	    		+ "		,creador"
	    		+ "		,feccreacion"
	    		+ "		) " 
				+ "	VALUES ("
				+ "		'"+estrcobro_id+"'"
				+ "		,'"+rango.getRango_inicial()+"'"
				+ "		,"+rango_final+""
				+ "		,'"+rango.getRango_costo_fijo()+"'"
				+ "		,'"+rango.getRango_costo_variable()+"'"
				+ "		,'"+id_usu_crea+"'"
				+ "		,now()"
			
				+ "	) ";
	    		
		
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL,Statement.RETURN_GENERATED_KEYS);
			
			ResultSet generatedKeys = null;
	    	generatedKeys = state.getGeneratedKeys();
	    	if (generatedKeys.next()) {
	    		id=generatedKeys.getString(1);
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	    
	    disconect();
	    
	    return id;
	}

	public static boolean Exist(RangoEstructuraCobroVO rango) {
		getCon();
		
		String SQL= ""
	    		+ " SELECT count(*) AS n"
	    		+ " FROM rango_estructura_cobro"
	    		+ "	WHERE rango_estructura_cobro.rango_id="+rango.getRango_id();
		
		
		System.out.println(SQL);
		
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				
				if(RS.getInt("n")>0){
					return true;
				}
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
		
		return false;
	}

	public static void updateRangoCobro(RangoEstructuraCobroVO rango,
			String usuIDSession) {
		 	getCon();
			
			String rango_final=rango.getRango_final();
			if(rango_final==null || rango_final.equals(""))rango_final="NULL";
			else rango_final="'"+rango_final+"'";
		
		 	
			String SQL= ""
		    		+ " UPDATE `rango_estructura_cobro` SET "
		    		+ "		rango_inicial='"+rango.getRango_inicial()+"' "
		    		+ "		,rango_final="+rango_final+" "
		    		+ "		,rango_costo_fijo='"+rango.getRango_costo_fijo()+"' "
		    		+ "		,rango_costo_variable='"+rango.getRango_costo_variable()+"' "
		    		+ "		,modificador='"+usuIDSession+"' "
		    		+ "		,fecmod=now() "
		    		+ "		,estados_vig_novig_id= "+rango.getEstadoVignoVig().getId() 
		     		
		    		+ "	WHERE "
					+ "		rango_estructura_cobro.rango_id='"+rango.getRango_id()+"'  ";
		    		
			
			System.out.println(SQL);
			
			try {
				state.executeUpdate(SQL);
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		    
		    disconect();
		
	}
}
