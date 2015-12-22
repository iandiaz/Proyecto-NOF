package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public static ArrayList<PedidoSerieVO> getPedidosSeries(String id_pedido){
		getCon();
		
		String SQL= "SELECT "
				+ "		`pedido_serie`.`id_ps`"
				+ "		,`pedido_serie`.`prod_id`"
				+ "		,pedido_serie.`id_pedido`"
				+ "		,pedido_serie.`id_detalle_pedido` "
				+ "		,IF(pedido_serie.`ps_serie` IS NULL ,'', pedido_serie.`ps_serie`) AS ps_serie "
				+ "		,producto.`prod_pn_tli_codfab` "
				+ "		,producto.`prod_desc_corto` "
				+ "		,ubicacion.`ubi_fisica` "
				+ "	FROM `pedido_serie`"
				+ "	INNER JOIN producto ON producto.prod_id=pedido_serie.prod_id"
				+ "	INNER JOIN detalle_pedido ON detalle_pedido.id_detalle_pedido=pedido_serie.id_detalle_pedido"
				+ "	INNER JOIN ubicacion ON ubicacion.ubi_id=detalle_pedido.ubi_id"
				
				+ " WHERE `pedido_serie`.`id_pedido`= " +id_pedido  
				+ "";
	    		
		System.out.println(SQL);
		ArrayList<PedidoSerieVO> detalle_pedidos = new ArrayList<PedidoSerieVO>();
		try {
			
			ResultSet RS = state.executeQuery(SQL);
			while(RS.next()){
				PedidoSerieVO pedido_detalle_ = new PedidoSerieVO();
				
				pedido_detalle_.setId_detalle_pedido(RS.getString("id_detalle_pedido"));
				pedido_detalle_.setId_pedido(RS.getString("id_pedido"));
				pedido_detalle_.setProd_id(RS.getString("prod_id"));
				pedido_detalle_.setPn(RS.getString("prod_pn_tli_codfab"));
				pedido_detalle_.setId(RS.getString("id_ps"));
				pedido_detalle_.setSerie(RS.getString("ps_serie"));
				pedido_detalle_.setUbi_fisica(RS.getString("ubi_fisica"));
				pedido_detalle_.setDesc_corto(RS.getString("prod_desc_corto"));
				
				detalle_pedidos.add(pedido_detalle_);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return detalle_pedidos;
	}
	
	
	public static void setSerie(String id_ps,String serie){
		getCon();
		
		String SQL= "UPDATE pedido_serie SET pedido_serie.`ps_serie`='"+serie+"' WHERE `pedido_serie`.`id_ps`="+id_ps;
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
