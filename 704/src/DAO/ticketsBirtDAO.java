package DAO;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.ConstantesBIRT;
import VO.TicketVO;
public class ticketsBirtDAO {
	private static Connection conexion=null;
	private static Statement state=null;
	
	
	
	public static ArrayList<TicketVO> getTickets(TicketVO ticket) throws UnsupportedEncodingException{
		getCon();
		
		String SQL="SELECT" + 
				"		[TICKET].[TICK_DIRECCION]," + 
				"		[TICKET].[TICK_ESTADO]," + 
				"		[TICKET].[TICK_ID]," + 
				"		[TICKET].[TICK_CODIGO_FALLA]," + 
				"		[DETALLE_TECNICO_TICKET].[DIRE_ID]," + 
				"		[DETALLE_TECNICO_TICKET].[DETI_USU_NOF]," + 
				"		ll.USU_ASIG ," + 
				"		DATEDIFF( MINUTE, [TICKET].[TICK_FECHA_ENVIO], GETDATE() ) AS DiffDate ," + 
				"		CONVERT(VARCHAR(10), [TICKET].[TICK_FECHA_ENVIO], 103) as TICK_FECHA_ENVIO, "+
				
				"		[DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET] ," + 
				"		[DETALLE_TECNICO_TICKET].[DETI_EMPRESA] ," + 
				"		[DETALLE_TECNICO_TICKET].[USU_INICIAL] ," + 
				"		[DETALLE_TECNICO_TICKET].[DETI_DIRECCION] ," + 
				"		[DETALLE_TECNICO_TICKET].[DETI_SERIE] , " +
				"		UBICACION.UBI_DESCRIPCION " +
				
				"	FROM " + 
				"		[TICKET]" + 
				"	INNER JOIN [DETALLE_TECNICO_TICKET] ON [DETALLE_TECNICO_TICKET].[TICK_ID] = [TICKET].[TICK_ID]" +
				"	LEFT JOIN ACTIVO ON activo.ALT_SERIE=[DETALLE_TECNICO_TICKET].DETI_SERIE " +
				"	LEFT JOIN UBICACION ON UBICACION.UBI_ID=[ACTIVO].UBI_ID " +
				"	INNER JOIN (" + 
				"		SELECT" + 
				"			[TICKET_ASIGNACION].[USU_ASIG]," + 
				"			[TICKET_ASIGNACION].[TICK_ID]" + 
				"		FROM" + 
				"			[TICKET_ASIGNACION]" + 
				"		INNER JOIN (" + 
				"			SELECT" + 
				"				MAX ([TICKET_ASIGNACION].[TASIG_ID]) AS TASIG_ID" + 
				"			FROM" + 
				"				[TICKET_ASIGNACION]" + 
				"			GROUP BY" + 
				"				[TICKET_ASIGNACION].[TICK_ID]" + 
				"			) t1 ON t1.TASIG_ID = [TICKET_ASIGNACION].[TASIG_ID]" + 
				"		) ll ON ll.TICK_ID = [TICKET].[TICK_ID]" + 
				"	WHERE" + 
				"		NOT (" + 
				"			[TICKET].[TICK_ESTADO] LIKE '%CERRADO%'" + 
				"		)" + 
				"		AND NOT (" + 
				"			[TICKET].[TICK_ESTADO] LIKE '%CIERRE%'" + 
				"		)" + 
				"		AND NOT (" + 
				"			[TICKET].[TICK_ESTADO] LIKE '%ELIMINADO%'" + 
				"		) ";
		
		if(ticket.getDeti_empresa()!=null) SQL+=" AND [DETALLE_TECNICO_TICKET].[DETI_EMPRESA]="+ticket.getDeti_empresa();
		if(ticket.getDeti_tipo_ticket()!=null) SQL+=" AND [DETALLE_TECNICO_TICKET].[DETI_TIPO_TICKET]='"+ticket.getDeti_tipo_ticket()+"' ";
		
		
		SQL+=" ORDER BY [TICKET].[TICK_ID] DESC";
		System.out.println(SQL);
		ArrayList<TicketVO> tickets = new ArrayList<TicketVO>();
		try {
			ResultSet RS = state.executeQuery(SQL);
		
			while(RS.next()){
				
				TicketVO tick = new TicketVO();
				tick.setId(RS.getString("TICK_ID"));
				tick.setDireccion(RS.getString("DETI_DIRECCION"));
				tick.setUbicacion(RS.getString("UBI_DESCRIPCION"));
				if(tick.getUbicacion()==null) tick.setUbicacion("");
				
				tick.setFecha_envio(RS.getString("TICK_FECHA_ENVIO"));
				
				tick.setDeti_empresa(RS.getString("DETI_EMPRESA"));
				
				tickets.add(tick);
			}
			RS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconect();
		
		return tickets;
		
	}
	
	
	public ticketsBirtDAO(){
		 getCon();
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
