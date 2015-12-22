package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Constantes.Constantes;
import VO.UsuarioVO;

public class UsuariosDAO {
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
	
	
	public static UsuarioVO getUsuario(String id_usu){
		getCon();
		
		String SQL= "SELECT "
				+ "		`usuarios`.`Usuarios_id`"
				+ "		,usuarios.`empresas_id`"
				+ "	FROM `usuarios` " 
				+ "	WHERE `usuarios`.`Usuarios_id`= "+id_usu + 
				"";
	    		
		System.out.println(SQL);
		UsuarioVO usuario = new UsuarioVO();
		try {
			
			ResultSet RS = state.executeQuery(SQL);
			if(RS.next()){
				
				usuario.setEmp_id(RS.getString("empresas_id"));
				usuario.setId(RS.getString("Usuarios_id"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return usuario;
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
