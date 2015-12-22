

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
 * Servlet implementation class cfac2
 */
@WebServlet("/cfac2")
public class cfac2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cfac2() {
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
		    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null) perf_in_session=true;
		    	
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
		// TODO Auto-generated method stub
		
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

		String Usuarios_nombre="",id_iusuario="";
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}	
		
		request.setAttribute("usuname", Usuarios_nombre);
		Statement state = null;
		Statement statecor = null;		
	
	
		
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		String GID=request.getParameter("gd_id");
		    String SQL_CAB = "SELECT "
		    		+ "		`804`.`804_folio` as fac_servim_folio, "
		    		+ "		IF(`804`.`id_dte` is null , '1','0') as dte , "
		    		+ "		DATE_FORMAT(`804`.`804_fecha`, '%d-%m-%Y') AS FAC_FECHA ,"
		    		+ "		DATE_FORMAT(`804`.`804_fecvencimiento`, '%d-%m-%Y') AS fac_servim_fecvencimiento ,"
		    		+ "		emisor.empresas_nombrenof as empresas_nombrenofemisor  ,"
		    		+ "		`804`.`804_tipodte` as fac_servim_tipodte  ,"
		    		+ "		cliente.empresas_razonsocial as empresas_razonsocialcliente, "
		    		+ "		cliente.empresas_rut as empresas_rutcliente,"
		    		+ "		cliente.empresas_id as empresas_idcliente,"
		    		+ "		cliente.empresas_nombrenof as empresas_nombrenofcliente,"
		    		+ "		cliente.empresas_giro ,"
		    		+ "		`804`.`804_obs` as fac_servim_obs,"
		    		 
		    		+ "		`804`.`804_condiciones` as fac_servim_condiciones, "
		    		+ "		`804`.`804_tipo_cambio` AS fac_servim_tcambio, "
		    		
		    		+ "		IF(`804`.`tipo_dteref` is null,'NINGUNA',`804`.`tipo_dteref`) as tipo_dteref, "
		    		+ "		IF(`804`.`folioref` is null ,'',`804`.`folioref`) as  folioref, "
		    		+ "		IF(`804`.`fec_ref` is null,'',DATE_FORMAT(`804`.`fec_ref`,'%d-%m-%Y')) as fec_ref, "

		    		
					+ "		`contacto`.`CONT_EMAIL`, "
					+ "		CONCAT_WS(' ',`contacto`.CONT_NOMBRE,`contacto`.CONT_APEP,`contacto`.CONT_APEM) AS CONT_NOMBRE, "
 					+ "		`contacto`.`CONT_TELEFONO`,  "
					+ "		`contacto`.`CONT_TELEFONOC`,  "
					
					+ "		`direccion`.`DIRE_DIRECCION` , "
					+ "		`direccion`.`DIRE_CIUDAD`,  "
					+ "		`comuna`.`COMU_NOMBRE`,  "
					+ "		`region`.`REGI_NOMBRE`, "
					+ "		`804`.`804_subtotal` as fac_servim_total , "
					+ "		`804`.`804_total`, "
					+ "		`804`.`804_descuento`, "
		    		+ "		`804`.`804_glosa_descuento`, "
		    		+ "		CONCAT_WS(' ',`usuarios`.`Usuarios_nombre`,`usuarios`.`Usuarios_ape_p`,`usuarios`.`Usuarios_ape_m`) AS Usuarios_nombre_com,"
		    		+ " `804`.`804_neto`,`804`.`804_iva` "					
					+ "	FROM"
		    		+ "		`804`"
		    		+ "	INNER JOIN `empresas` emisor ON `emisor`.`empresas_id` = `804`.`804_empresa_emisora` "
		    		+ " INNER JOIN `empresas` cliente ON cliente.empresas_id = `804`.`clpr_id` "
		    		+ " INNER JOIN `contacto` ON `contacto`.`CONT_ID` = `804`.`CONT_ID` "
		    		+ " INNER JOIN `direccion` ON `direccion`.`DIRE_ID` = `804`.`dire_id` "
		    		+ " INNER JOIN `comuna` ON `comuna`.`COMU_ID` = `direccion`.`COMU_ID` "
		    		+ " INNER JOIN `region` ON `region`.`REGI_ID` = `comuna`.`REGI_ID` "
		    		+ " INNER JOIN `usuarios` ON `usuarios`.`Usuarios_id` = cliente.`responsable_id` "
		    		+ "	WHERE"
		    		+ "		`804`.`804_id`= "+GID;
		    //System.out.println(SQL_CAB);
		    ResultSet CAB_RS = state.executeQuery(SQL_CAB);
		    if(CAB_RS.next()){
		    	request.setAttribute("gd_id", GID);
		    	String estado_sii="";
		    	if(CAB_RS.getString("dte").equals("0")){estado_sii = "ENVIADA";}
				if(CAB_RS.getString("dte").equals("1")){estado_sii = "PARA ENVIAR";}
				
				request.setAttribute("empresas_giro",CAB_RS.getString("empresas_giro"));
		    	request.setAttribute("estado_sii", estado_sii);
		    	request.setAttribute("folio", CAB_RS.getString("fac_servim_folio"));
		    	request.setAttribute("FAC_FECHA", CAB_RS.getString("FAC_FECHA"));
		    	request.setAttribute("fac_servim_condiciones", CAB_RS.getString("fac_servim_condiciones"));
		    	request.setAttribute("fac_servim_fecvencimiento", CAB_RS.getString("fac_servim_fecvencimiento"));
		    	request.setAttribute("empresas_nombrenofemisor", CAB_RS.getString("empresas_nombrenofemisor"));
		    	request.setAttribute("fac_servim_tipodte", CAB_RS.getString("fac_servim_tipodte"));
		    	request.setAttribute("empresas_razonsocialcliente", CAB_RS.getString("empresas_razonsocialcliente"));
		    	request.setAttribute("empresas_idcliente", CAB_RS.getString("empresas_idcliente"));
		    	request.setAttribute("empresas_rutcliente", CAB_RS.getString("empresas_rutcliente"));
		    	request.setAttribute("empresas_nombrenofcliente", CAB_RS.getString("empresas_nombrenofcliente"));
		    	
		    	request.setAttribute("fac_servim_obs", CAB_RS.getString("fac_servim_obs"));
		    	
		    	request.setAttribute("CONT_EMAIL", CAB_RS.getString("CONT_EMAIL"));
		    	request.setAttribute("CONT_NOMBRE", CAB_RS.getString("CONT_NOMBRE"));
		    	request.setAttribute("CONT_TELEFONO", CAB_RS.getString("CONT_TELEFONO"));
		    	request.setAttribute("CONT_TELEFONOC", CAB_RS.getString("CONT_TELEFONOC"));
		    	
		    	
		    	request.setAttribute("PERS_NOMBRE", CAB_RS.getString("Usuarios_nombre_com"));
		    	request.setAttribute("DIRE_DIRECCION", CAB_RS.getString("DIRE_DIRECCION"));
		    	request.setAttribute("DIRE_CIUDAD", CAB_RS.getString("DIRE_CIUDAD"));
		    	request.setAttribute("COMU_NOMBRE", CAB_RS.getString("COMU_NOMBRE"));
		    	request.setAttribute("REGI_NOMBRE", CAB_RS.getString("REGI_NOMBRE"));
		    	request.setAttribute("fac_servim_total", CAB_RS.getString("fac_servim_total"));
		    	
		    	request.setAttribute("fac_servim_glosa_desc", CAB_RS.getString("804_glosa_descuento"));
		    	request.setAttribute("DESC", CAB_RS.getString("804_descuento"));
		    	
		    	request.setAttribute("fac_servim_tcambio", CAB_RS.getString("fac_servim_tcambio"));
		    	request.setAttribute("fac_servotros_neto", CAB_RS.getString("804_neto"));
		    	request.setAttribute("fac_servotros_iva", CAB_RS.getString("804_iva"));
		    	request.setAttribute("fac_servotros_totalfinal", CAB_RS.getString("804_total"));
		    	
		    	
		    	request.setAttribute("fec_ref", CAB_RS.getString("fec_ref"));
		    	request.setAttribute("folioref", CAB_RS.getString("folioref"));
		    	request.setAttribute("tipo_dteref", CAB_RS.getString("tipo_dteref"));
		    	
		    }
		    CAB_RS.close();
		    
		    String SQL_DET = "SELECT * FROM detalle_804 WHERE 804_id = "+GID+" ORDER BY d804_id";
		    System.out.println(SQL_DET);
		    ResultSet DET_RS = state.executeQuery(SQL_DET);
		    ArrayList<String> prod = new ArrayList<String>();
		    Integer c=0;
		    while(DET_RS.next()){ prod.add(c+"/"+DET_RS.getString("d804_descripcion")+"/"+DET_RS.getString("d804_valor")); c=c+1;}
		    DET_RS.close();	
		    String[] ar_prod = new String[5];
		    for(int x=0; x < prod.size(); x++){ 
		    	
		    		ar_prod[x]=prod.get(x);	
		    	
		    	 
		    
		    }
		    request.setAttribute("ar_prod", ar_prod);
//		   
            statecor.close();
            conexion.close();
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("cfac2.jsp");
        rd.forward(request, response);
	}
}
