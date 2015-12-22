package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.PedidoVO;

public class PedidosDAO {
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
	
	
	public static String insertPedido(PedidoVO pedido,String id_creador){
		getCon();
		
		String id_pedido=null;
		
		String SQL= ""
	    		+ " INSERT INTO `pedido` (id_cliente,id_prov,creador,feccreacion) " + 
	    		"VALUES ('"+pedido.getCliente().getEmpresas_id()+"','"+pedido.getProveedor().getEmpresas_id()+"','"+id_creador+"',now()) ";
	    
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL,Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = null;
	    	generatedKeys = state.getGeneratedKeys();
	    	if (generatedKeys.next()) {
	    		id_pedido=generatedKeys.getString(1);
	    	}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return id_pedido;
	}
	public static void setEnviado(String id_pedido){
		getCon();
		
		
		String SQL= ""
	    		+ " UPDATE `pedido` SET pedido_enviado=1 WHERE  id_pedido="+id_pedido ;
	    
		System.out.println(SQL);
		
		try {
			state.executeUpdate(SQL);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	
	}
	
	public static ArrayList<PedidoVO> getPedidos(PedidoVO pedido){
		getCon();
		
		String SQL= "SELECT "
				+ "		`pedido`.`id_pedido`"
				+ "		,pedido.`id_cliente`"
				+ "		,pedido.`id_prov`"
				+ "		,DATE_FORMAT(pedido.`feccreacion`,'%d-%m-%Y') AS feccreacion"
				+ "		,cliente.`empresas_nombrenof` as nombrenofCliente " 
				+ "		,proveedor.`empresas_nombrenof` as nombrenofProveedor " +
				"FROM `pedido` " + 
				"INNER JOIN empresas cliente ON cliente.`empresas_id` = `pedido`.`id_cliente` " + 
				"INNER JOIN empresas proveedor ON proveedor.`empresas_id` = `pedido`.`id_prov` " + 
				" WHERE 1=1 ";
		
		if(pedido.getEnviado()!=null)SQL+=" AND pedido.pedido_enviado="+pedido.getEnviado();
		if(pedido.getProveedor()!=null)SQL+=" AND proveedor.empresas_id="+pedido.getProveedor().getEmpresas_id();
	    		
		System.out.println(SQL);
		ArrayList<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		try {
			
			ResultSet RS = state.executeQuery(SQL);
			while(RS.next()){
				PedidoVO pedido_ = new PedidoVO();
				
				EmpresaVO cliente = new EmpresaVO();
				cliente.setEmpresas_id(RS.getString("id_cliente"));
				cliente.setEmpresas_nombrenof(RS.getString("nombrenofCliente"));
				
				pedido_.setCliente(cliente);
				
				
				EmpresaVO proveedor = new EmpresaVO();
				proveedor.setEmpresas_id(RS.getString("id_prov"));
				proveedor.setEmpresas_nombrenof(RS.getString("nombrenofProveedor"));
				
				pedido_.setProveedor(proveedor);
				
				pedido_.setId(RS.getString("id_pedido"));
				pedido_.setFec_crea(RS.getString("feccreacion"));
				pedidos.add(pedido_);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return pedidos;
	}
	
	public static PedidoVO getPedido(String id_pedido){
		getCon();
		
		String SQL= "SELECT "
				+ "		`pedido`.`id_pedido`"
				+ "		,pedido.`id_cliente`"
				+ "		,pedido.`id_prov`"
				+ "		,DATE_FORMAT(pedido.`feccreacion`,'%d-%m-%Y') AS feccreacion"
				+ "		,cliente.`empresas_nombrenof` as nombrenofCliente " 
				+ "		,proveedor.`empresas_nombrenof` as nombrenofProveedor " 
				+ "		,direccion.`dire_direccion` "
				+ "		,`estado_pedido`.`estado_pedido_nombre` "
				+ "FROM `pedido` "  
				+ "	INNER JOIN direccion ON direccion.dire_id=pedido.dire_id "
				
				+ "INNER JOIN empresas cliente ON cliente.`empresas_id` = `pedido`.`id_cliente`"
				+ "INNER JOIN empresas proveedor ON proveedor.`empresas_id` = `pedido`.`id_prov` "
				+ "INNER JOIN estado_pedido ON estado_pedido.`id_estado_pedido` = `pedido`.`id_estado_pedido` "
				+ "	WHERE `pedido`.`id_pedido`= "+id_pedido + 
				"";
	    		
		System.out.println(SQL);
		PedidoVO pedido = new PedidoVO();
		try {
			
			ResultSet RS = state.executeQuery(SQL);
			while(RS.next()){
				
				EmpresaVO cliente = new EmpresaVO();
				cliente.setEmpresas_id(RS.getString("id_cliente"));
				cliente.setEmpresas_nombrenof(RS.getString("nombrenofCliente"));
				
				pedido.setCliente(cliente);
				
				EmpresaVO proveedor = new EmpresaVO();
				proveedor.setEmpresas_id(RS.getString("id_prov"));
				proveedor.setEmpresas_nombrenof(RS.getString("nombrenofProveedor"));
				
				pedido.setProveedor(proveedor);
				
				pedido.setId(RS.getString("id_pedido"));
				pedido.setFec_crea(RS.getString("feccreacion"));
				
				DireccionVO dire = new DireccionVO();
				dire.setDireccion(RS.getString("dire_direccion"));
				pedido.setDireccion(dire);
				pedido.setEstado_p(RS.getString("estado_pedido_nombre"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return pedido;
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
