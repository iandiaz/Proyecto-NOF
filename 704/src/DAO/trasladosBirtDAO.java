package DAO;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.ConstantesBIRT;
import VO.TrasladoVO;

public class trasladosBirtDAO {
	
	private static Connection conexion=null;
	private static Statement state=null;
	
	
	public static ArrayList<TrasladoVO> getTraslados(TrasladoVO traslado) throws UnsupportedEncodingException{
		getCon();
		
		String SQL="SELECT "
				+ "		d_origen.DIRE_DIRECCION AS DIRE_ORIGEN, "
				+ "		d_destino.DIRE_DIRECCION AS DIRE_DESTINO,  "
				+ "		CONVERT(VARCHAR(10), t.[TRAS_FECHA], 103) as TRAS_FECHA,  "
				+ "		PRODUCTO.PROD_PN_TLI_CODFAB ,  "
				+ "		ACTIVO.ALT_SERIE , "
				+ "		ACTIVO.PROD_ID  "
				+ "	FROM"
				+ "		TRASLADO t"
				
				+ "	INNER JOIN ACTIVO ON t.ALT_ID = ACTIVO.ALT_ID"
				+ "	INNER JOIN PRODUCTO ON ACTIVO.PROD_ID = PRODUCTO.PROD_ID"
				+ "	INNER JOIN UBICACION ubi_origen ON t.TRAS_UBI_ID_ORIGEN = ubi_origen.UBI_ID"
				+ "	INNER JOIN DIRECCION d_origen ON d_origen.DIRE_ID = ubi_origen.DIRE_ID"
			
				+ "	INNER JOIN UBICACION u_destino ON t.TRAS_UBI_ID_DESTINO = u_destino.UBI_ID"
				+ "	INNER JOIN DIRECCION d_destino ON d_destino.DIRE_ID = u_destino.DIRE_ID"
				
				+ "	INNER JOIN CLIENTEPROVEEDOR c_origen ON d_origen.CLPR_ID = c_origen.CLPR_ID"
				+ " INNER JOIN CLIENTEPROVEEDOR c_destino ON d_destino.CLPR_ID = c_destino.CLPR_ID"
				+ "	WHERE 1=1 "; 
		
		if(traslado.getUbi_id_both()!=null) SQL+=" AND (ubi_origen.UBI_ID="+traslado.getUbi_id_both()+" OR u_destino.UBI_ID="+traslado.getUbi_id_both()+")";
		
			SQL+= "		"
				+ "	 "
				+ "	ORDER BY"
				+ "		t.TRAS_FECHA "
				+ "		";
		
		
		System.out.println(SQL);
		ArrayList<TrasladoVO> traslados = new ArrayList<TrasladoVO>();
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			while(RS.next()){
				
				TrasladoVO traslado_ = new TrasladoVO();
				
				traslado_.setDire_origen(RS.getString("DIRE_ORIGEN"));
				traslado_.setDire_destino(RS.getString("DIRE_DESTINO"));
				traslado_.setFecha(RS.getString("TRAS_FECHA"));
				traslado_.setSerie(RS.getString("ALT_SERIE"));
				traslado_.setPnumber(RS.getString("PROD_PN_TLI_CODFAB"));
				traslado_.setProd_id(RS.getString("PROD_ID"));
				
				traslados.add(traslado_);
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
		return traslados;
		
	}
	
	 private static void getCon() {
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conexion=(Connection) DriverManager.getConnection("jdbc:sqlserver://"+ConstantesBIRT.SERVER+";databaseName="+ConstantesBIRT.DBNAME+";user="+ConstantesBIRT.USER+";password="+ConstantesBIRT.PASS+";useUnicode=true;characterEncoding=UTF-8");
					state = (Statement) ((java.sql.Connection) conexion).createStatement();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			 
			 
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
