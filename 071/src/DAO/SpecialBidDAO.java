package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.ConstantesBIRT;
import VO.SpecialBidVO;

public class SpecialBidDAO {
	private  Connection conexion=null;
	private  Statement state=null;
	
	public SpecialBidDAO() {
		super();
		
	}

	public  ArrayList<SpecialBidVO> getSpecialBids(SpecialBidVO SB) {
		getCon();
		
		String SQL="SELECT" 
				+ "	SPECIAL_BID.SB_ID"
				+ "	,SPECIAL_BID.SB_NRO "
				+ "	,DETALLE_SB.PROD_ID,DETALLE_SB.DETISB_PRECIO"
				+ "	,DATEDIFF(day,getdate() ,SPECIAL_BID.SB_FECHA_TERMINO_BID) AS saldo"
				+ "	,GRUPO.GRUP_NOMBRE " + 
				" FROM" + 
				"	SPECIAL_BID" + 
				" INNER JOIN DETALLE_SB ON DETALLE_SB.SB_ID=SPECIAL_BID.SB_ID" +
				" INNER JOIN GRUPO ON GRUPO.GRUP_ID=SPECIAL_BID.GRUP_ID" +
				" WHERE SPECIAL_BID.SB_ESTADO='VIGENTE CON SALDO' " + 
				"		 ";
		if(SB.getId_prod()!=null) SQL+=" AND DETALLE_SB.PROD_ID="+SB.getId_prod();
		
		System.out.println(SQL);
		ArrayList<SpecialBidVO> specialbids = new ArrayList<SpecialBidVO>();
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			while(RS.next()){
				
				SpecialBidVO Sb = new SpecialBidVO();
				Sb.setId(RS.getString("SB_ID"));
				Sb.setSB_NRO(RS.getString("SB_NRO"));
				Sb.setId_prod(RS.getString("PROD_ID"));
				Sb.setDETISB_PRECIO(RS.getString("DETISB_PRECIO"));
				Sb.setSaldo(RS.getString("saldo"));
				Sb.setGRUPO_NOM(RS.getString("GRUP_NOMBRE"));
				
				
				specialbids.add(Sb);
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
		return specialbids;
		
	}
	
	public  SpecialBidVO getSpecialBid(SpecialBidVO SB) {
		getCon();
	
		String SQL="SELECT" + 
				"	SPECIAL_BID.SB_ID"
				+ "	,SPECIAL_BID.SB_NRO "
				+ "	,DETALLE_SB.PROD_ID"
				+ "	,DETALLE_SB.DETISB_PRECIO " 
				+ "	,DATEDIFF(day,getdate() ,SPECIAL_BID.SB_FECHA_TERMINO_BID) AS saldo"
				+ "	,GRUPO.GRUP_NOMBRE "
				+" FROM SPECIAL_BID" + 
				" INNER JOIN DETALLE_SB ON DETALLE_SB.SB_ID=SPECIAL_BID.SB_ID" + 
				" INNER JOIN GRUPO ON GRUPO.GRUP_ID=SPECIAL_BID.GRUP_ID" +
				" WHERE SPECIAL_BID.SB_ESTADO='VIGENTE CON SALDO' " + 
				"		 ";
		if(SB.getId_prod()!=null) SQL+=" AND DETALLE_SB.PROD_ID="+SB.getId_prod();
		if(SB.getId()!=null) SQL+=" AND SPECIAL_BID.SB_ID="+SB.getId();
		
		System.out.println(SQL);
		SpecialBidVO Sb = new SpecialBidVO();
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				
				Sb.setId(RS.getString("SB_ID"));
				Sb.setSB_NRO(RS.getString("SB_NRO"));
				Sb.setId_prod(RS.getString("PROD_ID"));
				Sb.setDETISB_PRECIO(RS.getString("DETISB_PRECIO"));
				
				Sb.setSaldo(RS.getString("saldo"));
				Sb.setGRUPO_NOM(RS.getString("GRUP_NOMBRE"));
				
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
		return Sb;
		
	}
	
	private  void getCon() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conexion=(Connection) DriverManager.getConnection("jdbc:sqlserver://"+ConstantesBIRT.SERVER+";databaseName="+ConstantesBIRT.DBNAME+";user="+ConstantesBIRT.USER+";password="+ConstantesBIRT.PASS+";useUnicode=true;characterEncoding=UTF-8");
				state = (Statement) ((java.sql.Connection) conexion).createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		 
	}
 
 public  void disconect(){
		try {
			state.close();
			conexion.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
