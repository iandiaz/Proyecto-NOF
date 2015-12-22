package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.GrupoVO;

public class GruposDAO {
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
	
	
	public static ArrayList<GrupoVO> getGrupos(GrupoVO grup){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		grupos.grupos_id"
	    		+ "		, grupos.grupos_nombre"
	    		+ " FROM grupos"
	    		+ "	WHERE 1=1  ";
		
		
		
		
		
			SQL+=" ORDER BY grupos.grupos_nombre ";
		
		System.out.println(SQL);
		ArrayList<GrupoVO> grupos = new ArrayList<GrupoVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				GrupoVO grupo = new GrupoVO();
				grupo.setId(RS.getString("grupos_id"));
				grupo.setNombre(RS.getString("grupos_nombre"));
			
				grupos.add(grupo);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return grupos;
	}
	
	
	public static EmpresaVO getEmpresa(String id_emp){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		empresas.empresas_id"
	    		+ "		, empresas.empresas_nombrenof"
	    		+ "		, empresas.estados_vig_novig_id "
	    		+ "		, empresas.empresas_rut "
	    		+ "		, empresas.empresas_razonsocial "
	    		+ "		, estados_vig_novig.estados_vig_novig_nombre "
	    		+ " FROM empresas"
	    		+ "	INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=empresas.estados_vig_novig_id"
	    		+ "	WHERE 1=1  "
				+ " 	AND empresas.empresas_id= "+id_emp;
		
				SQL+=" ORDER BY empresas.empresas_nombrenof ";
		
		System.out.println(SQL);
		EmpresaVO empresa = new EmpresaVO();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				empresa.setEmpresas_id(RS.getString("empresas_id"));
				empresa.setEmpresas_nombrenof(RS.getString("empresas_nombrenof"));
				
				EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
				estado.setId(RS.getString("estados_vig_novig_id"));
				estado.setNombre(RS.getString("estados_vig_novig_nombre"));
				
				empresa.setEstadoVigNoVig(estado);
				
				empresa.setEmpresas_rut(RS.getString("empresas_rut"));
				empresa.setEmpresas_razonsocial(RS.getString("empresas_razonsocial"));
				
				
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return empresa;
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
