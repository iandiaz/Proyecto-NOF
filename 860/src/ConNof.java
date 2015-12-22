import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConNof {
private Connection conexion;
	
	public ConNof(){
		
		
	}
	public void Desconectar(){
		try {
			this.conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Conectar(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		this.conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			
		}catch(Exception e){
			e.printStackTrace();
		
		}
	}
	
	public ResultSet Consulta(String query){
		Conectar();
		try{
			
				Statement state = (Statement) ((java.sql.Connection) this.conexion).createStatement();
			
			  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
			  state = (Statement) ((java.sql.Connection) conexion).createStatement();
			
			    ResultSet Query_RS = state.executeQuery(query);
			    
			    return Query_RS;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public void ConsultaINUP(String query){
		Conectar();
		try{
			
				Statement state = (Statement) ((java.sql.Connection) this.conexion).createStatement();
			
			  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
			  state = (Statement) ((java.sql.Connection) conexion).createStatement();
			
			  state.executeUpdate(query);
			    
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
	}
	
	
}
