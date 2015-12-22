

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class m2
 */
@WebServlet("/m2")
public class m2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m2() {
        super();
        // TODO Auto-generated constructor stub
    }

public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		boolean perf_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) perf_in_session=true;
		    	
		    }
		}
		
		
		if(user_in_session && username_in_session && perf_in_session) user_in_session=true;
		else user_in_session=false;
		//refrescamos la session 
		
		if (user_in_session && cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("Usuarios_nombre")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("Usuarios_login")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("Usuarios_email")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("tipo_usu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	if(cookie.getName().equals("perfilusu_id")) {cookie.setMaxAge(Constantes.T_SESSION);cookie.setPath("/");response.addCookie(cookie);}
		    	
		    	
		    }
		}
		
		
		return user_in_session;
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();		
		
		// logout
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 System.out.println("cookie logout: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");
				return;
		}
		//fin logout
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}

		String Usuarios_nombre="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}		
		request.setAttribute("usuname", Usuarios_nombre);
		request.setAttribute("usuname", Usuarios_nombre);
		//grabar

		if(request.getParameter("grabar") != null){
			//import java.io.IOException;
			
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String FID=request.getParameter("fact_id");
	    		String estados_vig_novig_id=request.getParameter("estados_vig_novig_id");
	    		String datepickerfechafactura[]=request.getParameter("datepickerfechafactura").split("-");
	    		String fecvencimiento[]=request.getParameter("fecvencimiento").split("-");
	    		
	    		
	    		String fecha1 = datepickerfechafactura[2]+"-"+datepickerfechafactura[1]+"-"+datepickerfechafactura[0];
	    		String fecvencimiento1 = fecvencimiento[2]+"-"+fecvencimiento[1]+"-"+fecvencimiento[0];
	    		String tipodte=request.getParameter("tipodte");
				
	    		String FACT_NETO=request.getParameter("FACT_NETO");;
				String FACT_DESCUENTO=request.getParameter("desc");
				String FACT_SUBTOTAL=request.getParameter("FACT_SUBTOTAL");
				String FACT_IVA=request.getParameter("FACT_IVA");
				String FACT_TOTAL=request.getParameter("FACT_TOTAL");
				String FACT_GLOSA_DESC=request.getParameter("glosa_desc");
				String CONT_NOMBRE=request.getParameter("CONT_NOMBRE");
				String CONT_EMAIL=request.getParameter("CONT_EMAIL");
				
				String OBS1=request.getParameter("obs1");
				String OBS2=request.getParameter("obs2");
				String DIRE_ID = request.getParameter("dire_id");
				String gv_ciudad = request.getParameter("gv_ciudad");
				
				
				
				String tipo_dteref=request.getParameter("tipo_dteref");
				String folioref=request.getParameter("folioref");
				String fec_ref=request.getParameter("fec_ref");
				
				if(tipo_dteref==null)tipo_dteref="";
				if(folioref==null)folioref="";
				if(fec_ref==null){fec_ref="NULL";}
				else{ 
					String fecrefar[]=fec_ref.split("-");
					fec_ref ="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
					}
				
	    		
	    		String SQL_DATOS = "update `801` set "
	    				+ "	`estados_vig_novig_id`="+estados_vig_novig_id+" "
	    				+ "	, `801_fecha`='"+fecha1+"'"
	    				+ "	, `801_fecvencimiento`='"+fecvencimiento1+"'"
	    				+ "	, 801_tipodte='"+tipodte+"'"
	    				
			    	  	+ " , 801_NETO='"+FACT_NETO+"'"
			    		+ " , 801_descuento='"+FACT_DESCUENTO+"' "
			    		+ "	, 801_glosa_descuento='"+FACT_GLOSA_DESC+"'"
			    		+ "	, 801_subtotal='"+FACT_SUBTOTAL+"'"
				    	+ "	, 801_IVA='"+FACT_IVA+"'"
				    	+ "	, 801_TOTAL='"+FACT_TOTAL+"'"
				    	
				    	
				    	+ "	, 801_ciudad='"+gv_ciudad.toUpperCase()+"'"
				    	
				    	+ "	, cont_nombre='"+CONT_NOMBRE+"'"
				    	+ "	, cont_email='"+CONT_EMAIL+"'"
				    	
						+ " , tipo_dteref='"+tipo_dteref+"'"
			    		+ " , folioref='"+folioref+"'"
			    		+ " , fec_ref="+fec_ref
			    		
			    		+ "	, 801_obs1='"+OBS1.toUpperCase()+"'"
			    		+ "	, 801_obs2='"+OBS2.toUpperCase()+"'"
			    		+ "	, dire_id='"+DIRE_ID+"'"
			    		
	    				+ "	, `801_fecmod`=now()"
	    				+ "	, `801_modificador`="+id_iusuario+" "
	    				+ "	where `801_id`="+FID;
	    		System.out.println(SQL_DATOS);
	    		int RS_DATOS = state.executeUpdate(SQL_DATOS);
	    		
	    		//insertamos logs
	    		String log_sql=""
	    				+ " INSERT INTO `log` ("
	    				+ "	`log`.`log_fec`, "
	    				+ "	`log`.`log_nombre`, " 
	    				+ "	`log`.`Usuarios_id` "
	    				+ " ) "
	    				+ " VALUES "
	    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+FID+" EN TABLA 801', '"+id_iusuario+"') ";
	    		state.executeUpdate(log_sql);
	    		
				conexion.close();
				
				response.sendRedirect("menufac?Exito=OK");
				
			}
			catch(Exception r ){
				
			}
			
			
		}else{
		
			try {
				Statement state = null;
				ResultSet RS_DATOS = null;
				ResultSet RS_DATOSD = null;
				
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String FID = request.getParameter("id");
	    		String TDTE = request.getParameter("tdte");

	    		//DATOS DE LA TABLA FACTURA
	    		String SQL_DATOS = "SELECT  "
	            		+ " DATE_FORMAT(F.801_FECHA, '%d-%m-%Y') AS FACT_FECHA, "
	            		+ " F.801_CONDICIONES,801_folio_birt, "
						
						+ " C1.empresas_nombrenof as CLPR_NOMBRE_FANTASIA,"
						+ " C2.empresas_razonsocial as CLPR_RAZON_SOCIAL,"
	            		+ " C2.empresas_rut as CLPR_RUT,"
	            		+ " C2.empresas_giro ,"
						
	            		+ " DIRECCION.DIRE_DIRECCION, COMUNA.COMU_NOMBRE, "
			    		+ " DIRECCION.DIRE_ID, "
			    		+ "	DIRECCION.DIRE_CIUDAD, "
			    		+ " REGION.REGI_NOMBRE, "
	            		+ " F.801_TIPO_CAMBIO, "
	            		
	            		+ " F.CONT_NOMBRE, "
	            		+ " F.CONT_TELEFONO, "
	            		+ " F.CONT_EMAIL, "
	            		+ " F.801_OBS, "
	            		+ " F.801_ESTADO, "
	            		+ "	F.estados_vig_novig_id, "
	            		+ "	F.CLPR_ID, "
	            		
	            		
	            		+ " F.801_subtotal, "
	            		+ " F.801_descuento, "
	            		+ " F.801_neto, "
	            		+ " F.801_iva, "
	            		+ " F.801_total, "
	            		+ " F.801_glosa_descuento, "
	            		+ " DATE_FORMAT(F.801_fecvencimiento, '%d-%m-%Y') AS 801_fecvencimiento , "
	            		
	            		+ " DATE_FORMAT(F.801_FECHA_EMISION, '%d-%m-%Y') AS FACT_FECHA_EMISION, "
	            		+ " u1.Usuarios_nombre, u1.Usuarios_ape_p, "
	            		+ " USUARIOS.Usuarios_login,"
	            		+ " F.801_tipodte, "
	            		+ " F.801_obs1, F.801_obs2,F.USU_INICIAL_EMISOR,  "
	            		+ "		IF(F.`tipo_dteref` is null,'',F.`tipo_dteref`) as tipo_dteref, "
						+ "		IF(F.`folioref` is null ,'',F.`folioref`) as  folioref, "
						+ "		IF(F.`fec_ref` is null,'',DATE_FORMAT(F.`fec_ref`,'%d-%m-%Y')) as fec_ref, "
						+ " F.id_dte "
			    		
						
	            		+ " FROM `801` F "
	            		+ " LEFT JOIN empresas as C1 ON F.801_EMPRESA_EMISORA = C1.empresas_id "
	            		+ " LEFT JOIN empresas as C2 ON F.CLPR_ID = C2.empresas_id "
	            		+ " LEFT JOIN DIRECCION ON F.DIRE_ID = DIRECCION.DIRE_ID"
	            		+ " LEFT JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
			    		+ " LEFT JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
	            		+ " LEFT JOIN USUARIOS ON F.801_USU_EMISION = USUARIOS.Usuarios_id "
	            		+ " LEFT JOIN USUARIOS u1 ON F.801_RESPONSABLE = u1.Usuarios_id "
	            		+ " WHERE F.801_ID ='"+FID+"'";
	    		System.out.println(SQL_DATOS);
	    		
	    		String CLPR_ID="";
	    		RS_DATOS =  state.executeQuery(SQL_DATOS);
	            if(RS_DATOS.next()){
		            request.setAttribute("FACT_ID",FID);
		            request.setAttribute("numbirt",RS_DATOS.getString("801_folio_birt"));
		            request.setAttribute("factura_tipodte",RS_DATOS.getString("801_tipodte"));
		            request.setAttribute("factura_obs1",RS_DATOS.getString("801_obs1"));
		            request.setAttribute("factura_obs2",RS_DATOS.getString("801_obs2"));
			 		request.setAttribute("FACT_FECHA",RS_DATOS.getString("FACT_FECHA"));
			 		request.setAttribute("FACT_CONDICIONES",RS_DATOS.getString("801_CONDICIONES"));
			 		request.setAttribute("CLPR_NOMBRE_FANTASIA",RS_DATOS.getString("CLPR_NOMBRE_FANTASIA"));
			 		request.setAttribute("CLPR_RAZON_SOCIAL",RS_DATOS.getString("CLPR_RAZON_SOCIAL"));
			 		request.setAttribute("CLPR_RUT",RS_DATOS.getString("CLPR_RUT"));
			 		request.setAttribute("CLPR_GIRO",RS_DATOS.getString("empresas_giro"));
			 		
			 		request.setAttribute("fec_vencimiento",RS_DATOS.getString("801_fecvencimiento"));
			 		
			 		
			 		//request.setAttribute("CLPR_DV",RS_DATOS.getString("CLPR_DV"));
			 		request.setAttribute("DIRE_DIRECCION",RS_DATOS.getString("DIRE_DIRECCION"));
			 		request.setAttribute("COMU_NOMBRE",RS_DATOS.getString("COMU_NOMBRE"));
			 		request.setAttribute("DIRE_CIUDAD",RS_DATOS.getString("DIRE_CIUDAD"));
			 		request.setAttribute("REGI_NOMBRE",RS_DATOS.getString("REGI_NOMBRE"));
			 		request.setAttribute("FAC_RESPONSABLE",RS_DATOS.getString("Usuarios_nombre")+" "+RS_DATOS.getString("Usuarios_ape_p"));
			 		request.setAttribute("FACT_TIPO_CAMBIO",RS_DATOS.getString("801_TIPO_CAMBIO"));
			 		request.setAttribute("CONT_NOMBRE",RS_DATOS.getString("CONT_NOMBRE"));
			 		request.setAttribute("CONT_TELEFONO",RS_DATOS.getString("CONT_TELEFONO"));
			 		request.setAttribute("CONT_EMAIL",RS_DATOS.getString("CONT_EMAIL"));
			 		
			 		request.setAttribute("FACT_OBS",RS_DATOS.getString("801_OBS"));
			 		request.setAttribute("FACT_ESTADO",RS_DATOS.getString("801_ESTADO"));
			 		request.setAttribute("FACT_FECHA_EMISION",RS_DATOS.getString("FACT_FECHA_EMISION"));
			 		request.setAttribute("USU_INICIAL",RS_DATOS.getString("USU_INICIAL_EMISOR"));
			 		
			 		
			 		request.setAttribute("fec_ref", RS_DATOS.getString("fec_ref"));
			    	request.setAttribute("folioref", RS_DATOS.getString("folioref"));
			    	request.setAttribute("tipo_dteref", RS_DATOS.getString("tipo_dteref"));
			    	
			    	
			    		request.setAttribute("SUBTOTAL",RS_DATOS.getString("801_subtotal"));
			    		request.setAttribute("DESCUENTO",RS_DATOS.getString("801_descuento"));
			 			request.setAttribute("IVA",RS_DATOS.getString("801_iva"));
			 			request.setAttribute("NETO",RS_DATOS.getString("801_neto"));
			 			request.setAttribute("TOTAL",RS_DATOS.getString("801_total"));
			 			request.setAttribute("GLOSADESC",RS_DATOS.getString("801_glosa_descuento"));
			 			request.setAttribute("estados_vig_novig_id",RS_DATOS.getString("estados_vig_novig_id"));
			 			request.setAttribute("DIRE_ID",RS_DATOS.getString("DIRE_ID"));
			 			CLPR_ID=RS_DATOS.getString("CLPR_ID");
			 			
	            }
	            RS_DATOS.close();
	 		   
	 		   Float N=0.0f, I=0.0f;
	 		   String SQL_DATOSD = "SELECT D.ALT_ID, IFNULL(D.PROD_DESC_CORTO, ' ') as PROD_DESC_CORTO,"
	 		   				+ " D.DETI_UNITARIO, IFNULL(D.PROD_PN_TLI_CODFAB, ' ') as PROD_PN_TLI_CODFAB, IFNULL(D.ALT_SERIE, ' ') as ALT_SERIE "
		    		 		+ " FROM detalle_801 D"
		    		 		+ " WHERE D.FACT_ID ='"+FID+"'";
	 		   System.out.println(SQL_DATOSD);
	 		   RS_DATOSD =  state.executeQuery(SQL_DATOSD);
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   while(RS_DATOSD.next()){
	 			   N=N+RS_DATOSD.getFloat("DETI_UNITARIO");
	 			   I=I+((RS_DATOSD.getFloat("DETI_UNITARIO")*19)/100);
	 			   prod_res.add(RS_DATOSD.getString("ALT_ID")+"/"+
	 					  RS_DATOSD.getString("PROD_PN_TLI_CODFAB")+"/"+
	 					  RS_DATOSD.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
	 					  RS_DATOSD.getString("ALT_SERIE").replace("/","")+"/"+
	 					  RS_DATOSD.getInt("DETI_UNITARIO"));
	 		   }
	 		   RS_DATOSD.close();
	 		   
	 		   
	 		   String[] ar_productos = new String[prod_res.size()];
	 		   for(int x=0; x < prod_res.size(); x++){
	 			   ar_productos[x]=prod_res.get(x);
	 		   }
	 		   request.setAttribute("ar_productos", ar_productos);
	 		   request.setAttribute("tdte", TDTE);
	 		   
	 		   
	 		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
			    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
			    ResultSet ESTADOS_RS = state.executeQuery(SQL_ESTADOS);
			    //definimos un arreglo para los resultados
			    ArrayList<String> estados = new ArrayList<String>();
			    //recorremos los resultados de la consulta
			    while(ESTADOS_RS.next()){        	   
	        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
			    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));    
	    	    }
			  ESTADOS_RS.close();
			  
			  String[] ar_estados = new String[estados.size()];
	          for(int x=0; x < estados.size(); x++){
	           	ar_estados[x]=estados.get(x);
	          }
	                
	          request.setAttribute("ar_estados", ar_estados);
			//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
	 		   
	          
	          String SQL_dire = "SELECT"
			    		+ "		direccion.*, `region`.`REGI_NOMBRE`, `comuna`.`COMU_NOMBRE` "
			    		+ "	FROM"
			    		+ "		direccion"
			    		+ "	INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID`"
			    		+ "	INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID`"
			    		+ "	WHERE"
			    		+ "		estados_vig_novig_id = 1"
			    		+ "		AND direccion.CLPR_ID ='"+CLPR_ID+"'	"
			    		+ "	ORDER BY"
			    		+ "		DIRE_DIRECCION";
			    ResultSet DIRE_RS = state.executeQuery(SQL_dire);
			
	       	ArrayList<String> dire_res = new ArrayList<String>();
		 		while(DIRE_RS.next()){
		 			dire_res.add(DIRE_RS.getString("dire_id")+"/"+DIRE_RS.getString("DIRE_DIRECCION").replace("/", " ")+"/"+DIRE_RS.getString("CLPR_ID")+"/"+DIRE_RS.getString("REGI_NOMBRE")+"/"+DIRE_RS.getString("DIRE_CIUDAD")+"/"+DIRE_RS.getString("COMU_NOMBRE"));
		 		}
		 		DIRE_RS.close();
		 		 
		 		String[] ar_dire = new String[dire_res.size()];
		 		for(int x=0; x < dire_res.size(); x++){
		 			ar_dire[x]=dire_res.get(x);
		 		}
		 		request.setAttribute("ar_dire", ar_dire);
		 		
		 		
		 		state.close();
	          conexion.close();
	          
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
			
	
			
		RequestDispatcher rd = request.getRequestDispatcher("m2.jsp");
        rd.forward(request, response);
		
		}
		
	}

}
