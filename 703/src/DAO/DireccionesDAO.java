package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.DireccionVO;

public class DireccionesDAO {
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
	
	
	public static ArrayList<DireccionVO> getDirecciones(DireccionVO dire){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		direccion.dire_id"
	    		+ "		, direccion.dire_direccion"
	    		+ "		, direccion.estados_vig_novig_id "
	    		+ "		, direccion.dire_ciudad "
	    		+ "		, estados_vig_novig.estados_vig_novig_nombre "
	    		+ "		, comuna.comu_nombre "
	    		+ "		, region.regi_nombre "
	    		+ " FROM direccion"
	    		+ "	INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=direccion.estados_vig_novig_id"
	    		+ "	INNER JOIN comuna ON comuna.comu_id=direccion.comu_id "
	    		+ "	INNER JOIN region ON region.REGI_ID=comuna.REGI_ID "
	    		
	    		+ "	WHERE 1=1  ";
		
		if(dire.getEmpresa()!=null) SQL+=" AND direccion.clpr_id= "+dire.getEmpresa().getEmpresas_id();
		if(dire.getEstadoVigNoVig()!=null) SQL+=" AND direccion.estados_vig_novig_id= "+dire.getEstadoVigNoVig().getId();
		
			SQL+=" ORDER BY direccion.dire_direccion ";
		
		System.out.println(SQL);
		ArrayList<DireccionVO> direcciones = new ArrayList<DireccionVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				DireccionVO direccion = new DireccionVO();
				
				direccion.setId(RS.getString("dire_id"));
				direccion.setDireccion(RS.getString("dire_direccion"));
				direccion.setCiudad(RS.getString("dire_ciudad"));
				direccion.setRegi_nombre(RS.getString("regi_nombre"));
				direccion.setComu_nombre(RS.getString("comu_nombre"));
				direcciones.add(direccion);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return direcciones;
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
