

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
 * Servlet implementation class cfac
 */
@WebServlet("/cfac")
public class cfac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cfac() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
  		boolean user_in_session=false;
  		Cookie [] cookies=request.getCookies();
  		
  		if (cookies != null) {
  		    for (Cookie cookie : cookies) {
  		        //work with cookies
  		    	 //System.out.println("cookie: "+cookie.getName());
  		    	if(cookie.getName().equals("Usuarios_id")) user_in_session=true;
  		    }
  		}
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
  		    }
  		}
  		
  		
  		return user_in_session;
  		
      }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
			try {
				Statement state = null;
				Statement state_fac = null;
				ResultSet RS_FACTURAS = null;
				ResultSet GUIAS_RS = null;
				ResultSet FAC_RS = null;
				String FID = request.getParameter("fact_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		//DATOS DE LA TABLA FACTURA
	    		ConBirt birtBDF= new ConBirt();
	            ResultSet QueryBirtF= birtBDF.Consulta("SELECT  "
	            		+ " CONVERT(varchar, FACTURA.FACT_FECHA, 105) AS FACT_FECHA, "
	            		+ " FACTURA.FACT_CONDICIONES, "
	            		+ " C1.CLPR_NOMBRE_FANTASIA, "
	            		+ " C2.CLPR_RAZON_SOCIAL, C2.CLPR_RUT, C2.CLPR_DV, C2.CLPR_GIRO,"	
	            		+ " DIRECCION.DIRE_DIRECCION, COMUNA.COMU_NOMBRE, "
			    		+ " DIRECCION.DIRE_ID, DIRECCION.DIRE_CIUDAD, "
			    		+ " REGION.REGI_NOMBRE, "
	            		+ " FACTURA.FACT_TIPO_CAMBIO, "
	            		+ " CONTACTO.CONT_NOMBRE, "
	            		+ " CONTACTO.CONT_TELEFONO, "
	            		+ "	CONTACTO.CONT_EMAIL, "
	            		+ " FACTURA.FACT_TOTAL_TEXTO, "
	            		+ " FACTURA.FACT_OBS, "
	            		+ " FACTURA.FACT_ESTADO, "
	            		+ " CONVERT(varchar, FACTURA.FACT_FECHA_EMISION, 105) AS FACT_FECHA_EMISION, "
	            		+ " USUARIO.USU_INICIAL "
	            		+ " FROM FACTURA "
	            		+ " INNER JOIN CLIENTEPROVEEDOR as C1 ON FACTURA.FACT_EMPRESA_EMISORA = C1.CLPR_ID "
	            		+ " INNER JOIN CLIENTEPROVEEDOR as C2 ON FACTURA.CLPR_ID = C2.CLPR_ID "
	            		+ " INNER JOIN DIRECCION ON FACTURA.DIRE_ID = DIRECCION.DIRE_ID"
	            		+ " INNER JOIN COMUNA ON DIRECCION.COMU_ID = COMUNA.COMU_ID"
			    		+ " INNER JOIN REGION ON COMUNA.REGI_ID = REGION.REGI_ID"
	            		+ " INNER JOIN CONTACTO ON FACTURA.CONT_ID = CONTACTO.CONT_ID "
	            		+ " INNER JOIN USUARIO ON FACTURA.FACT_USU_EMISION = USUARIO.USU_ID "
	            		+ " WHERE FACTURA.FACT_ID ='"+FID+"'");
	            QueryBirtF.next();
	            request.setAttribute("FACT_ID",FID);
		 		request.setAttribute("FACT_FECHA",QueryBirtF.getString("FACT_FECHA"));
		 		request.setAttribute("FACT_CONDICIONES",QueryBirtF.getString("FACT_CONDICIONES"));
		 		request.setAttribute("CLPR_NOMBRE_FANTASIA",QueryBirtF.getString("CLPR_NOMBRE_FANTASIA"));
		 		request.setAttribute("CLPR_RAZON_SOCIAL",QueryBirtF.getString("CLPR_RAZON_SOCIAL"));
		 		
		 		request.setAttribute("CLPR_RUT",QueryBirtF.getString("CLPR_RUT"));
		 		request.setAttribute("CLPR_DV",QueryBirtF.getString("CLPR_DV"));
		 		request.setAttribute("CLPR_GIRO",QueryBirtF.getString("CLPR_GIRO"));
		 		request.setAttribute("DIRE_DIRECCION",QueryBirtF.getString("DIRE_DIRECCION"));
		 		request.setAttribute("COMU_NOMBRE",QueryBirtF.getString("COMU_NOMBRE"));
		 		request.setAttribute("DIRE_CIUDAD",QueryBirtF.getString("DIRE_CIUDAD"));
		 		request.setAttribute("REGI_NOMBRE",QueryBirtF.getString("REGI_NOMBRE"));
		 		Float cambio = QueryBirtF.getFloat("FACT_TIPO_CAMBIO");
		 		request.setAttribute("FACT_TIPO_CAMBIO",cambio.intValue()+"");
		 		//request.setAttribute("FACT_TIPO_CAMBIO",QueryBirtF.getString("FACT_TIPO_CAMBIO"));
		 		request.setAttribute("CONT_NOMBRE",QueryBirtF.getString("CONT_NOMBRE"));
		 		request.setAttribute("CONT_EMAIL",QueryBirtF.getString("CONT_EMAIL"));
		 		
		 		request.setAttribute("CONT_TELEFONO",QueryBirtF.getString("CONT_TELEFONO"));
		 		request.setAttribute("FACT_TOTAL_TEXTO",QueryBirtF.getString("FACT_TOTAL_TEXTO"));
		 		request.setAttribute("FACT_OBS",QueryBirtF.getString("FACT_OBS"));
		 		request.setAttribute("FACT_ESTADO",QueryBirtF.getString("FACT_ESTADO"));
		 		request.setAttribute("FACT_FECHA_EMISION",QueryBirtF.getString("FACT_FECHA_EMISION"));
		 		request.setAttribute("USU_INICIAL",QueryBirtF.getString("USU_INICIAL"));
		 		request.setAttribute("estadovignovig","1");
		 		
	            QueryBirtF.close();
	    		
	            String descuento="0";
	            //SI SE PASO A NOF
	            String SQL_FAC = "SELECT 801_obs1"
	            		+ "	,801_obs2"
	            		+ "	,801_tipodte"
	            		+ "	,801_FOLIO"
	            		+ "	,DATE_FORMAT(fec_ref,'%d-%m-%Y') as fec_ref "
	            		+ "	,folioref"
	            		+ "	,tipo_dteref"
	            		+ "	,DATE_FORMAT(801_fecvencimiento,'%d-%m-%Y') as 801_fecvencimiento "
	            		+ "	,801_glosa_descuento"
	            		+ " ,801_subtotal,801_neto,801_iva,801_total,estados_vig_novig_id  "
				    	
	            		+ "	,801_descuento FROM `801` WHERE 801_folio_birt='"+FID+"'"; 
	    		System.out.println(SQL_FAC);
	 		    FAC_RS =  state.executeQuery(SQL_FAC);
	 		    
	 			String SUBTOTAL="";
		    	String desc="";
		    	String NETO="";
		    	String IVA="";
		    	String TOTAL="";
		    	
	 		    
	            if(FAC_RS.next()){
	            	request.setAttribute("factura_obs1",FAC_RS.getString("801_obs1"));
	            	request.setAttribute("factura_obs2",FAC_RS.getString("801_obs2"));
	            	request.setAttribute("factura_tipodte",FAC_RS.getString("801_tipodte"));
	            	
	            	
	            	request.setAttribute("FACT_FOLIO", FAC_RS.getString("801_FOLIO"));
	            	request.setAttribute("fec_ref", FAC_RS.getString("fec_ref"));
			    	request.setAttribute("folioref", FAC_RS.getString("folioref"));
			    	request.setAttribute("tipo_dteref", FAC_RS.getString("tipo_dteref"));
			    	request.setAttribute("fecvencimiento", FAC_RS.getString("801_fecvencimiento"));
			    	request.setAttribute("glosadesc", FAC_RS.getString("801_glosa_descuento"));
			    	request.setAttribute("estadovignovig",FAC_RS.getString("estados_vig_novig_id"));
			    	
			    	if(FAC_RS.getString("801_descuento")!=null && !FAC_RS.getString("801_descuento").equals("") && !FAC_RS.getString("801_descuento").equals("null")){
			    		descuento=FAC_RS.getString("801_descuento");
			    	}
			    	
			    	
			    	 SUBTOTAL=FAC_RS.getString("801_subtotal");
			    	 desc=FAC_RS.getString("801_descuento");
			    	 NETO=FAC_RS.getString("801_neto");
			    	 IVA=FAC_RS.getString("801_iva");
			    	 TOTAL=FAC_RS.getString("801_total");
			    	
			    	
			    	
	            }
	            
	    		ConBirt birtBD0= new ConBirt();
	            ResultSet QueryBirt0= birtBD0.Consulta("SELECT TOP 1 DETALLE_VENTA.VTA_ID "
	            		+ " FROM DETALLE_FACTURA INNER JOIN DETALLE_VENTA ON DETALLE_FACTURA.DETI_TRAS_ID = DETALLE_VENTA.DETIVTA_ID "
	            		+ " WHERE DETALLE_FACTURA.FACT_ID ='"+FID+"'");
	            QueryBirt0.next();
	            String VID = QueryBirt0.getString("VTA_ID");
	            QueryBirt0.close();
	            
	            ConBirt birtBD1= new ConBirt();
			    ResultSet QueryBirt1= birtBD1.Consulta("SELECT USUARIO.USU_NOMBRE FROM VENTA "
			    		+ " INNER JOIN USUARIO ON VENTA.VTA_RESPONSABLE = USUARIO.USU_ID"
			    		+ " WHERE VTA_ID ='"+VID+"'");
	            QueryBirt1.next();
		 		request.setAttribute("USU_NOMBRE",QueryBirt1.getString("USU_NOMBRE"));
	 		    QueryBirt1.close();
	            
	 		    //SELECT IF(id_dte is null,'1','0') as dte FROM factura_venta WHERE FACT_ID= "+QueryBirt.getString("fact_id")
	    		String SQL_Guias = "SELECT IF(id_dte is null,'1','0') as dte FROM `801` WHERE 801_ID='"+FID+"'"; 
	 		    GUIAS_RS =  state.executeQuery(SQL_Guias);
	 		    String id_fac_estado = "";
				if(GUIAS_RS.next()){
					if(GUIAS_RS.getString("dte").equals("0")){id_fac_estado = "ENVIADA";}
					if(GUIAS_RS.getString("dte").equals("1")){id_fac_estado = "PARA ENVIAR";}
				}else{
					id_fac_estado = "NO ENVIADA";
				}
				request.setAttribute("id_fac_estado",id_fac_estado);
	 		    GUIAS_RS.close();
	 		    state.close();
	 		   
	 		   Float SUBTOTALINT=0.0f, I=0.0f;
	 		   ConBirt birtBD2= new ConBirt();
	 		   ResultSet QueryBirt2= birtBD2.Consulta("SELECT A.ALT_ID, p.PROD_DESC_CORTO, D.DETIVTA_PV, p.PROD_PN_TLI_CODFAB, A.ALT_SERIE,D.UBI_ID,VENTA.VTA_TICK_ID,VENTA.VTA_OC "
		    		 		+ " FROM DETALLE_VENTA D"
		    		 		+ "	LEFT JOIN VENTA ON VENTA.VTA_ID = D.VTA_ID "
		    		 		+ " INNER JOIN activo a ON D.alt_id = a.alt_id"
		    		 		+ " INNER JOIN producto p ON a.prod_id = p.prod_id"
		    		 		+ " WHERE D.VTA_ID ='"+VID+"'");
	 		   ArrayList<String> prod_res = new ArrayList<String>();
	 		   while(QueryBirt2.next()){
	 			  SUBTOTALINT+=QueryBirt2.getFloat("DETIVTA_PV");
	 			   prod_res.add(QueryBirt2.getString("ALT_ID")+"/"+
	 					  QueryBirt2.getString("PROD_PN_TLI_CODFAB")+"/"+
	 					  QueryBirt2.getString("PROD_DESC_CORTO").replace("/", " ")+"/"+
	 					  QueryBirt2.getString("ALT_SERIE").replace("/","")+"/"+
	 					  QueryBirt2.getInt("DETIVTA_PV")+"/"+
	 					 QueryBirt2.getInt("UBI_ID")+"/"+
	 					QueryBirt2.getInt("VTA_TICK_ID"));
	 			   
	 			  request.setAttribute("VTA_OC",QueryBirt2.getString("VTA_OC"));
	 			   
	 		   }
	 		   QueryBirt2.close();
	 		   String n2 = (SUBTOTALINT.intValue())+"";
	 		   
	 		   
	 		   
	 	
		    	 
		    	 if(SUBTOTAL.equals("")){
		    		  request.setAttribute("subtotal",n2.toString());
			 		     int neto = SUBTOTALINT.intValue()-(Integer.parseInt(descuento));
			 		   int total=(((int)(neto*0.19))+neto);
			 		   String total2 = (total)+"";
			 		  request.setAttribute("TOTAL",total2.toString());
				 	  request.setAttribute("NETO",neto+"");
			 		 request.setAttribute("IVA",((int)(neto*0.19))+"");
			 		 request.setAttribute("descuento",descuento);
		    	 }else{
		    		  request.setAttribute("subtotal",SUBTOTAL);
		    		  request.setAttribute("TOTAL",TOTAL);
				 	  request.setAttribute("NETO",NETO+"");
			 		 request.setAttribute("IVA",IVA);
			 		 request.setAttribute("descuento",desc);
		    	 }
		    	 
	 		 
	 		 
	 		 
	 		 
	 		   
	 		   String[] ar_productos = new String[prod_res.size()];
	 		   for(int x=0; x < prod_res.size(); x++){
	 			   ar_productos[x]=prod_res.get(x);
	 		   }
	 		   request.setAttribute("ar_productos", ar_productos);
	           birtBD1.Desconectar();
	           birtBD2.Desconectar();
	           birtBDF.Desconectar();
	           
			}catch(Exception ex){
			    out.println("ERROR: "+ex);
			    ex.printStackTrace();
			}
			
		RequestDispatcher rd = request.getRequestDispatcher("cfac.jsp");
        rd.forward(request, response);
		
		}

}
