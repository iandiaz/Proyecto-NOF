package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.PedidoDetalleVO;
import VO.PedidoVO;
import VO.ProductoVO;
import VO.UbicacionVO;

public class PedidosDetallesDAO {
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
	public static ArrayList<PedidoDetalleVO> getDetallePedidos(String id_pedido){
		getCon();
		
		String SQL= "SELECT "
				+ "		`detalle_pedido`.`id_detalle_pedido`"
				+ "		,detalle_pedido.`detalle_pedido_cantidad`"
				+ "		,detalle_pedido.`id_producto` "
				+ "		,ubicacion.`ubi_fisica` "
				+ "		,producto.`prod_pn_tli_codfab` "
				
				+ "	FROM `detalle_pedido`"
				+ "	INNER JOIN ubicacion ON ubicacion.ubi_id=detalle_pedido.ubi_id "
				+ "	INNER JOIN producto ON producto.prod_id=detalle_pedido.id_producto "
				
				+ " WHERE `detalle_pedido`.`id_pedido`= " +id_pedido  
				+ "";
	    		
		System.out.println(SQL);
		ArrayList<PedidoDetalleVO> detalle_pedidos = new ArrayList<PedidoDetalleVO>();
		try {
			
			ResultSet RS = state.executeQuery(SQL);
			while(RS.next()){
				PedidoDetalleVO pedido_detalle_ = new PedidoDetalleVO();
				
				
				UbicacionVO ubicacion = new UbicacionVO();
				ubicacion.setUbi_fisica(RS.getString("ubi_fisica"));
				
				ProductoVO producto = new ProductoVO();
				producto.setPn(RS.getString("prod_pn_tli_codfab"));
				
				pedido_detalle_.setCantidad(RS.getString("detalle_pedido_cantidad"));
				pedido_detalle_.setId_producto(RS.getString("id_producto"));
				pedido_detalle_.setProducto(producto);
				
				pedido_detalle_.setUbicacion(ubicacion);
				detalle_pedidos.add(pedido_detalle_);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return detalle_pedidos;
	}
	
	public static void insertPedidoDetalle(PedidoVO pedido,String id_creador){
		getCon();
		
		
		for(PedidoDetalleVO detalle : pedido.getDetallePedido()){
			String SQL= ""
		    		+ " INSERT INTO `detalle_pedido` (detalle_pedido_cantidad,id_producto,id_pedido,ubi_id,creador,feccreacion) " + 
		    		"VALUES ('"+detalle.getCantidad()+"','"+detalle.getId_producto()+"','"+detalle.getId_pedido()+"','"+detalle.getId_ubi()+"','"+id_creador+"',now()) ";
			System.out.println(SQL);
			try {
				state.executeUpdate(SQL);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
