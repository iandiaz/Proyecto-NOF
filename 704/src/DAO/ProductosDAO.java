package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.ProductoVO;

public class ProductosDAO {
	
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
	
	
	public static ArrayList<ProductoVO> getProductos(ProductoVO prod){
		getCon();
		
	
		String SQL= ""
	    		+ " SELECT "
	    		+ "		producto.prod_id"
	    		+ "		, producto.prod_pn_tli_codfab"
	    		+ "		, producto.prod_desc_corto"
	    		
	    		+ " FROM producto"
	    		+ "	WHERE 1=1  ";
	    if(prod.getFunc_id()!=null)SQL+= "	AND producto.func_id = "+prod.getFunc_id()+" ";
	    if(prod.getTipo()!=null)SQL+= "	AND producto.prod_tipo = "+prod.getTipo()+" ";
		
			SQL+=" ORDER BY producto.prod_desc_corto ";
		
		System.out.println(SQL);
		ArrayList<ProductoVO> productos = new ArrayList<ProductoVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				ProductoVO producto = new ProductoVO();
				
				producto.setId(RS.getString("prod_id"));
				producto.setPn(RS.getString("prod_pn_tli_codfab"));
				producto.setDesc_corto(RS.getString("prod_desc_corto"));
				
				productos.add(producto);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return productos;
	}
	public static ProductoVO getProducto(String id_prod){
		getCon();
		
		String SQL= ""
	    		+ " SELECT "
	    		+ "		producto.prod_id"
	    		+ "		, producto.prod_pn_tli_codfab"
	    		+ "		, producto.prod_desc_corto"
	    		+ " FROM producto"
	    		+ "	WHERE producto.prod_id="+id_prod;
	 
		
		System.out.println(SQL);
		ProductoVO producto = new ProductoVO();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				
				producto.setId(RS.getString("prod_id"));
				producto.setPn(RS.getString("prod_pn_tli_codfab"));
				producto.setDesc_corto(RS.getString("prod_desc_corto"));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    disconect();
	    
	    return producto;
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
