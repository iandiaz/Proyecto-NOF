package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.ConstantesBIRT;
import VO.EmpresaVO;

public class trasladosBirtDAO {
	private Connection conexion=null;
	private Statement state=null;
	
	public String[] getNumeroEnvSuministrosMaqPorEmpresaConRangoFecha(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL= "SELECT" + 
				"	COUNT(*) as nSUM"
				+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
				+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
				"	FROM" + 
				"		TRASLADO" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
				"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
				+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
				"	WHERE" 
				+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
				+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
				+"		AND DIRECCION.CLPR_ID ="+id_emp 
				+"		AND PRODUCTO.FUNC_ID = 2"  
				+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
			
				
			
		System.out.println(SQL);
		String nSUM="0";
		String cucTotal="0";
		String rendimientoTotal="0";
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
				if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
				if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSUM,cucTotal,rendimientoTotal};
			
	}
public String[] getNumeroEnvFotoCondPorEmpresaConRangoFecha(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL= "SELECT" + 
				"	COUNT(*) as nSUM"
				+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
				+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
				"	FROM" + 
				"		TRASLADO" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
				"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
				+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
				"	WHERE" 
				+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
				+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
				+"		AND DIRECCION.CLPR_ID ="+id_emp 
				+"		AND PRODUCTO.FUNC_ID = 18"  
				+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
			
				
			
		System.out.println(SQL);
		String nSUM="0";
		String cucTotal="0";
		String rendimientoTotal="0";
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
				if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
				if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSUM,cucTotal,rendimientoTotal};
			
	}

	public String[] getNumeroEnvConsumiblesPorEmpresaConRangoFecha(String fechadesde,String fechahasta,String id_emp) {
	
	String SQL= "SELECT" + 
			"	COUNT(*) as nSUM"
			+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
			+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
			"	FROM" + 
			"		TRASLADO" + 
			"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
			"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
			"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
			"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
			+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
			"	WHERE" 
			+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
			+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
			+"		AND DIRECCION.CLPR_ID ="+id_emp 
			+"		AND PRODUCTO.FUNC_ID = 19"  
			+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
		
			
		
	System.out.println(SQL);
	String nSUM="0";
	String cucTotal="0";
	String rendimientoTotal="0";
	try {
		ResultSet RS = state.executeQuery(SQL);
	
		if(RS.next()){
			if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
			if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
			if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
		}
		RS.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return new String[]{nSUM,cucTotal,rendimientoTotal};
		
}
	
	public String[] getNumeroEnvRepuestosPorEmpresaConRangoFecha(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL= "SELECT" + 
				"	COUNT(*) as nSUM"
				+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
				+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
				"	FROM" + 
				"		TRASLADO" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
				"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
				+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
				"	WHERE" 
				+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
				+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
				+"		AND DIRECCION.CLPR_ID ="+id_emp 
				+"		AND PRODUCTO.FUNC_ID = 10"  
				+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
			
				
			
		System.out.println(SQL);
		String nSUM="0";
		String cucTotal="0";
		String rendimientoTotal="0";
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
				if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
				if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSUM,cucTotal,rendimientoTotal};
			
	}
	
public String[] getNumeroEnvSuministrosMaqConRangoFecha(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
		
		String SQL= "SELECT" + 
				"	COUNT(*) as nSUM"
				+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
				+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
				"	FROM" + 
				"		TRASLADO" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DIRECCION.CLPR_ID" + 
				
				"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
				"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
				+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
				"	WHERE" 
				+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
				+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
			
				+"		AND PRODUCTO.FUNC_ID = 2"  
				+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
		
		
		
		if(id_emp!=null && !id_emp.equals("")) SQL+= "		AND DIRECCION.CLPR_ID ="+id_emp ;
		if(idgrupo!=null && !idgrupo.equals("")) SQL+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";
	
		if(select_vendedor!=null && select_vendedor.length>0){
			EmpresaVO emp = new EmpresaVO();
			emp.setResponsables_ar_ids(select_vendedor);
			
			ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

			String vendedores ="0";
			
			for(EmpresaVO empresa:emps){
				vendedores+=","+empresa.getId();
			}
			
			SQL+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

			}
			
		System.out.println(SQL);
		String nSUM="0";
		String cucTotal="0";
		String rendimientoTotal="0";
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
				if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
				if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSUM,cucTotal,rendimientoTotal};
			
	}

public String[] getNumeroEnvConRangoFecha_fotocond(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
	
	String SQL= "SELECT" + 
			"	COUNT(*) as nSUM"
			+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
			+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
			"	FROM" + 
			"		TRASLADO" + 
			"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
			"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
			"	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DIRECCION.CLPR_ID" + 
			
			"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
			"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
			+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
			"	WHERE" 
			+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
			+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
		
			+"		AND PRODUCTO.FUNC_ID = 18 "  
			+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
	
	
	
	if(id_emp!=null && !id_emp.equals("")) SQL+= "		AND DIRECCION.CLPR_ID ="+id_emp ;
	if(idgrupo!=null && !idgrupo.equals("")) SQL+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";

			
	if(select_vendedor!=null && select_vendedor.length>0){
		EmpresaVO emp = new EmpresaVO();
		emp.setResponsables_ar_ids(select_vendedor);
		
		ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

		String vendedores ="0";
		
		for(EmpresaVO empresa:emps){
			vendedores+=","+empresa.getId();
		}
		
		SQL+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

		}
		
	System.out.println(SQL);
	String nSUM="0";
	String cucTotal="0";
	String rendimientoTotal="0";
	try {
		ResultSet RS = state.executeQuery(SQL);
	
		if(RS.next()){
			if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
			if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
			if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
		}
		RS.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return new String[]{nSUM,cucTotal,rendimientoTotal};
		
}

public String[] getNumeroEnvConRangoFecha_consumibles(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
	
	String SQL= "SELECT" + 
			"	COUNT(*) as nSUM"
			+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
			+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
			"	FROM" + 
			"		TRASLADO" + 
			"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
			"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
			"	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DIRECCION.CLPR_ID" + 
			
			"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
			"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
			+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
			"	WHERE" 
			+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
			+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
		
			+"		AND PRODUCTO.FUNC_ID = 19 "  
			+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
	
	
	
	if(id_emp!=null && !id_emp.equals("")) SQL+= "		AND DIRECCION.CLPR_ID ="+id_emp ;
	if(idgrupo!=null && !idgrupo.equals("")) SQL+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";

	if(select_vendedor!=null && select_vendedor.length>0){
		EmpresaVO emp = new EmpresaVO();
		emp.setResponsables_ar_ids(select_vendedor);
		
		ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

		String vendedores ="0";
		
		for(EmpresaVO empresa:emps){
			vendedores+=","+empresa.getId();
		}
		
		SQL+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

		}
		
	
	
	System.out.println(SQL);
	String nSUM="0";
	String cucTotal="0";
	String rendimientoTotal="0";
	try {
		ResultSet RS = state.executeQuery(SQL);
	
		if(RS.next()){
			if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
			if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
			if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
		}
		RS.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return new String[]{nSUM,cucTotal,rendimientoTotal};
		
}


public String[] getNumeroEnvConRangoFecha_repuestos(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
	
	String SQL= "SELECT" + 
			"	COUNT(*) as nSUM"
			+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
			+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
			"	FROM" + 
			"		TRASLADO" + 
			"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
			"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
			"	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DIRECCION.CLPR_ID" + 
			
			"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
			"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
			+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
			"	WHERE" 
			+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
			+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
		
			+"		AND PRODUCTO.FUNC_ID = 10 "  
			+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
	
	
	
	if(id_emp!=null && !id_emp.equals("")) SQL+= "		AND DIRECCION.CLPR_ID ="+id_emp ;
	if(idgrupo!=null && !idgrupo.equals("")) SQL+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";

	if(select_vendedor!=null && select_vendedor.length>0){
		EmpresaVO emp = new EmpresaVO();
		emp.setResponsables_ar_ids(select_vendedor);
		
		ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

		String vendedores ="0";
		
		for(EmpresaVO empresa:emps){
			vendedores+=","+empresa.getId();
		}
		
		SQL+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

		}
		
		
	System.out.println(SQL);
	String nSUM="0";
	String cucTotal="0";
	String rendimientoTotal="0";
	try {
		ResultSet RS = state.executeQuery(SQL);
	
		if(RS.next()){
			if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
			if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
			if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
		}
		RS.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return new String[]{nSUM,cucTotal,rendimientoTotal};
		
}
	
	public String[] getNumeroKitPorEmpresaConRangoFecha(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL= "SELECT" + 
				"	COUNT(*) as nSUM"
				+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
				+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
				"	FROM" + 
				"		TRASLADO" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
				"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
				+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
				"	WHERE" 
				+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
				+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
				+"		AND DIRECCION.CLPR_ID ="+id_emp 
				+"		AND PRODUCTO.FUNC_ID = 17"  
				+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
			
				
			
		System.out.println(SQL);
		String nSUM="0";
		String cucTotal="0";
		String rendimientoTotal="0";
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
				if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
				if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSUM,cucTotal,rendimientoTotal};
			
	}
	
public String[] getNumeroKitConRangoFecha(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
		
		String SQL= "SELECT" + 
				"	COUNT(*) as nSUM"
				+ "	,SUM(PRODUCTO_CUC.CUC_VALOR) as cucTotal"
				+ "	,SUM(PRODUCTO.PROD_VIDA_UTIL_IMP) AS rendimientoTotal " + 
				"	FROM" + 
				"		TRASLADO" + 
				"	INNER JOIN UBICACION ON UBICACION.UBI_ID = TRASLADO.TRAS_UBI_ID_DESTINO" + 
				"	INNER JOIN DIRECCION ON DIRECCION.DIRE_ID = UBICACION.DIRE_ID" + 
				"	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DIRECCION.CLPR_ID" + 
				
				"	INNER JOIN ACTIVO ON ACTIVO.ALT_ID = TRASLADO.ALT_ID" + 
				"	INNER JOIN PRODUCTO ON PRODUCTO.PROD_ID = ACTIVO.PROD_ID"
				+ "	LEFT JOIN PRODUCTO_CUC ON (PRODUCTO_CUC.PROD_ID = PRODUCTO.PROD_ID	AND PRODUCTO_CUC.CUC_ID IN (SELECT MAX (PRODUCTO_CUC.CUC_ID) AS CUC_ID FROM	PRODUCTO_CUC GROUP BY PRODUCTO_CUC.PROD_ID))" + 
				"	WHERE" 
				+"		TRASLADO.TRAS_FECHA >= '"+fechadesde+"'"  
				+"		AND TRASLADO.TRAS_FECHA <= '"+fechahasta+"'"  
				+"		AND PRODUCTO.FUNC_ID = 17"  
				+"		AND TRASLADO.TRAS_ESTADO <> 'ELIMINADO' ";
			
		if(id_emp!=null && !id_emp.equals("")) SQL+= "		AND DIRECCION.CLPR_ID ="+id_emp ;
		if(idgrupo!=null && !idgrupo.equals("")) SQL+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";
	
		if(select_vendedor!=null && select_vendedor.length>0){
			EmpresaVO emp = new EmpresaVO();
			emp.setResponsables_ar_ids(select_vendedor);
			
			ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

			String vendedores ="0";
			
			for(EmpresaVO empresa:emps){
				vendedores+=","+empresa.getId();
			}
			
			SQL+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

			}
			
		//System.out.println(SQL);
		String nSUM="0";
		String cucTotal="0";
		String rendimientoTotal="0";
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			if(RS.next()){
				if(RS.getString("nSUM")!=null && !RS.getString("nSUM").equals(""))nSUM= RS.getString("nSUM");
				if(RS.getString("cucTotal")!=null && !RS.getString("cucTotal").equals(""))cucTotal= RS.getString("cucTotal");
				if(RS.getString("rendimientoTotal")!=null && !RS.getString("rendimientoTotal").equals(""))rendimientoTotal=RS.getString("rendimientoTotal");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSUM,cucTotal,rendimientoTotal};
			
	}
	
	
	public trasladosBirtDAO(){
		 getCon();
	 }
	 
	 private void getCon() {
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conexion=(Connection) DriverManager.getConnection("jdbc:sqlserver://"+ConstantesBIRT.SERVER+";databaseName="+ConstantesBIRT.DBNAME+";user="+ConstantesBIRT.USER+";password="+ConstantesBIRT.PASS+";");
					state = (Statement) ((java.sql.Connection) conexion).createStatement();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			 
			 
		}
	 
	 public void disconect(){
			try {
				state.close();
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
