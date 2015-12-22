package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.ConstantesBIRT;
import VO.EmpresaVO;
public class ticketsBirtDAO {
	private Connection conexion=null;
	private Statement state=null;
	
	public int getNumeroTicketsPorEmpresaConRangoFecha(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL_TICKETS = "SELECT" + 
				"	count(*) AS n" + 
				" FROM" + 
				"	TICKET"
				+ " INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID] "  
				+ " WHERE" 
				+ "	ticket.TICK_FECHA_ENVIO >= '"+fechadesde+"'" 
				+ " AND ticket.TICK_FECHA_ENVIO <= '"+fechahasta+"' "
				+ " AND DETALLE_TECNICO_TICKET.DETI_EMPRESA = "+id_emp+""
				+ " AND NOT [TICKET].[TICK_ESTADO] LIKE '%CERRADO%' "
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%CIERRE%' "
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%' ";
			
		System.out.println(SQL_TICKETS);
		int n=0;
		
		try {
			ResultSet RS = state.executeQuery(SQL_TICKETS);
		
			if(RS.next()){
				n= RS.getInt("n");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
			
	}
	
public int getNumeroTicketsConRangoFecha(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
		
		String SQL_TICKETS = "SELECT" + 
				"	count(*) AS n" + 
				" FROM" + 
				"	TICKET"
				+ " INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID] "  
				+ "	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DETALLE_TECNICO_TICKET.DETI_EMPRESA" 
				
				+ " WHERE" 
				+ "	ticket.TICK_FECHA_ENVIO >= '"+fechadesde+"'" 
				+ " AND ticket.TICK_FECHA_ENVIO <= '"+fechahasta+"' "
				+ " AND NOT [TICKET].[TICK_ESTADO] LIKE '%CERRADO%' "
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%CIERRE%' "
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%' ";
		
		if(id_emp!=null && !id_emp.equals("")) SQL_TICKETS+= "		AND DETALLE_TECNICO_TICKET.DETI_EMPRESA ="+id_emp ;
		if(idgrupo!=null && !idgrupo.equals("")) SQL_TICKETS+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";
		if(select_vendedor!=null && select_vendedor.length>0){
			EmpresaVO emp = new EmpresaVO();
			emp.setResponsables_ar_ids(select_vendedor);
			
			ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

			String vendedores ="0";
			
			for(EmpresaVO empresa:emps){
				vendedores+=","+empresa.getId();
			}
			
			SQL_TICKETS+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

			}
			
		//System.out.println(SQL_TICKETS);
		int n=0;
		
		try {
			ResultSet RS = state.executeQuery(SQL_TICKETS);
		
			if(RS.next()){
				n= RS.getInt("n");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
			
	}
public int getNumeroTicketsConRangoFechaLogistica(String fechadesde,String fechahasta,String id_emp, String idgrupo, String[] select_vendedor) {
	
	String SQL_TICKETS = "SELECT" + 
			"	count(*) AS n" + 
			" FROM" + 
			"	TICKET"
			+ " INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID] " 
			+ "	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DETALLE_TECNICO_TICKET.DETI_EMPRESA" 
			
			+ " WHERE" 
			+ "	ticket.TICK_FECHA_ENVIO >= '"+fechadesde+"'" 
			+ " AND ticket.TICK_FECHA_ENVIO <= '"+fechahasta+"' "
			+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%' "
			+ "	AND [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET] IN ('INSTALACION USUARIO','RETIRO','TXT','ENVIO','SOLICITUD TONER CRITICO','VENTA','SOLICITUD TONER NORMAL')";

	
	if(id_emp!=null && !id_emp.equals("")) SQL_TICKETS+= "		AND DETALLE_TECNICO_TICKET.DETI_EMPRESA ="+id_emp ;
	if(idgrupo!=null && !idgrupo.equals("")) SQL_TICKETS+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";

	if(select_vendedor!=null && select_vendedor.length>0){
		EmpresaVO emp = new EmpresaVO();
		emp.setResponsables_ar_ids(select_vendedor);
		
		ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

		String vendedores ="0";
		
		for(EmpresaVO empresa:emps){
			vendedores+=","+empresa.getId();
		}
		
		SQL_TICKETS+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

		}
	System.out.println(SQL_TICKETS);
	int n=0;
	
	try {
		ResultSet RS = state.executeQuery(SQL_TICKETS);
	
		if(RS.next()){
			n= RS.getInt("n");
		}
		RS.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return n;
		
}
	public int getNumeroTicketsPorEmpresaConRangoFechaLogistica(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL_TICKETS = "SELECT" + 
				"	count(*) AS n" + 
				" FROM" + 
				"	TICKET"
				+ " INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID] "  
				+ " WHERE" 
				+ "	ticket.TICK_FECHA_ENVIO >= '"+fechadesde+"'" 
				+ " AND ticket.TICK_FECHA_ENVIO <= '"+fechahasta+"' "
				+ " AND DETALLE_TECNICO_TICKET.DETI_EMPRESA = "+id_emp+""
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%' "
				+ "	AND [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET] IN ('INSTALACION USUARIO','RETIRO','TXT','ENVIO','SOLICITUD TONER CRITICO','VENTA','SOLICITUD TONER NORMAL')";
	
		System.out.println(SQL_TICKETS);
		int n=0;
		
		try {
			ResultSet RS = state.executeQuery(SQL_TICKETS);
		
			if(RS.next()){
				n= RS.getInt("n");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
			
	}
	
	public int getNumeroTicketsPorEmpresaConRangoFechaServTecnico(String fechadesde,String fechahasta,String id_emp) {
		
		String SQL_TICKETS = "SELECT" + 
				"	count(*) AS n" + 
				" FROM" + 
				"	TICKET"
				+ " INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID] "  
				+ " WHERE" 
				+ "	ticket.TICK_FECHA_ENVIO >= '"+fechadesde+"'" 
				+ " AND ticket.TICK_FECHA_ENVIO <= '"+fechahasta+"' "
				+ " AND DETALLE_TECNICO_TICKET.DETI_EMPRESA = "+id_emp+""
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%' "
				+ "	AND [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET] IN ('MxM AUTORIZADA','INSTALACION TECNICO','CONFIGURACION','DIAGNOSTICO','REPARACION','MxM POR AUTORIZAR','SINIESTRO','MXM','INSTALACION.')";
	
	
		System.out.println(SQL_TICKETS);
		int n=0;
		
		try {
			ResultSet RS = state.executeQuery(SQL_TICKETS);
		
			if(RS.next()){
				n= RS.getInt("n");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
			
	}
	
	public int getNumeroTicketsConRangoFechaServTecnico(String fechadesde,String fechahasta,String id_emp,String idgrupo, String[] select_vendedor) {
		
		String SQL_TICKETS = "SELECT" + 
				"	count(*) AS n" + 
				" FROM" + 
				"	TICKET"
				+ " INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID] "  
				+ "	INNER JOIN CLIENTEPROVEEDOR ON CLIENTEPROVEEDOR.CLPR_ID = DETALLE_TECNICO_TICKET.DETI_EMPRESA" 
				+ " WHERE" 
				+ "	ticket.TICK_FECHA_ENVIO >= '"+fechadesde+"'" 
				+ " AND ticket.TICK_FECHA_ENVIO <= '"+fechahasta+"' "
				+ " AND NOT	[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%' "
				+ "	AND [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET] IN ('MxM AUTORIZADA','INSTALACION TECNICO','CONFIGURACION','DIAGNOSTICO','REPARACION','MxM POR AUTORIZAR','SINIESTRO','MXM','INSTALACION.')";
	
		if(id_emp!=null && !id_emp.equals("")) SQL_TICKETS+= "		AND DETALLE_TECNICO_TICKET.DETI_EMPRESA ="+id_emp ;
		if(idgrupo!=null && !idgrupo.equals("")) SQL_TICKETS+= "		AND CLIENTEPROVEEDOR.GRUP_ID = "+idgrupo+" ";

		if(select_vendedor!=null && select_vendedor.length>0){
			EmpresaVO emp = new EmpresaVO();
			emp.setResponsables_ar_ids(select_vendedor);
			
			ArrayList<EmpresaVO> emps= EmpresasDAO.getEmpresas(emp);

			String vendedores ="0";
			
			for(EmpresaVO empresa:emps){
				vendedores+=","+empresa.getId();
			}
			
			SQL_TICKETS+= "	AND CLIENTEPROVEEDOR.CLPR_ID IN ("+vendedores+") ";

			}
		//System.out.println(SQL_TICKETS);
		int n=0;
		
		try {
			ResultSet RS = state.executeQuery(SQL_TICKETS);
		
			if(RS.next()){
				n= RS.getInt("n");
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
			
	}
	
	public ticketsBirtDAO(){
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
