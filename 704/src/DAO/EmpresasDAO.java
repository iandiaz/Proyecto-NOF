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

public class EmpresasDAO {
	
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
	
	
	public static ArrayList<EmpresaVO> getEmpresas(EmpresaVO emp){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		empresas.empresas_id"
	    		+ "		, empresas.empresas_nombrenof"
	    		+ "		, empresas.estados_vig_novig_id "
	    		+ "		, empresas.empresas_rut "
	    		+ "		, estados_vig_novig.estados_vig_novig_nombre "
	    		+ " FROM empresas"
	    		+ "	INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=empresas.estados_vig_novig_id"
	    		+ "	WHERE 1=1  ";
		
		if(emp.getEmpresas_id()!=null) SQL+=" AND empresas.empresas_id= "+emp.getEmpresas_id();
		if(emp.getEmpresas_escliente()!=null) SQL+=" AND empresas.empresas_escliente= "+emp.getEmpresas_escliente();
		if(emp.getEmpresas_esproveedor()!=null) SQL+=" AND empresas.empresas_esproveedor= "+emp.getEmpresas_esproveedor();
		if(emp.getEstadoVigNoVig()!=null) SQL+=" AND empresas.estados_vig_novig_id= "+emp.getEstadoVigNoVig().getId();
		if(emp.getEmpresas_relacionada()!=null) SQL+=" AND empresas.empresas_relacionada= "+emp.getEmpresas_relacionada();
		
		
		
		
			SQL+=" ORDER BY empresas.empresas_nombrenof ";
		
		System.out.println(SQL);
		ArrayList<EmpresaVO> empresas = new ArrayList<EmpresaVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				EmpresaVO empresa = new EmpresaVO();
				empresa.setEmpresas_id(RS.getString("empresas_id"));
				empresa.setEmpresas_nombrenof(RS.getString("empresas_nombrenof"));
				
				EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
				estado.setId(RS.getString("estados_vig_novig_id"));
				estado.setNombre(RS.getString("estados_vig_novig_nombre"));
				
				empresa.setEstadoVigNoVig(estado);
				
				empresa.setEmpresas_rut(RS.getString("empresas_rut"));
				
				empresas.add(empresa);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return empresas;
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
