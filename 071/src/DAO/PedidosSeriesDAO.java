package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Constantes.Constantes;
import VO.PedidoSerieVO;

public class PedidosSeriesDAO {
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
	
	 
	public static void insert(PedidoSerieVO pedidoSerie){
		getCon();
		
			String SQL= ""
		    		+ " INSERT INTO `pedido_serie` (prod_id,id_pedido,id_detalle_pedido,ps_precio,sb_id) " + 
		    		"VALUES ('"+pedidoSerie.getProd_id()+"','"+pedidoSerie.getId_pedido()+"','"+pedidoSerie.getId_detalle_pedido()+"','"+pedidoSerie.getPrecio()+"','"+pedidoSerie.getSb_id()+"') ";
			System.out.println(SQL);
			try {
				state.executeUpdate(SQL);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	    disconect();
	    
	    
	}
	
	
	
	public static int getNewId(){
		getCon();
		
		int newID=1;
		
		String SQL= ""
	    		+ " SELECT MAX(pedido.id_pedido) AS id_pedido "
	    		+ " FROM pedido";
	    
		System.out.println(SQL);
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				newID=(RS.getInt("id_pedido")+1);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return newID;
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
