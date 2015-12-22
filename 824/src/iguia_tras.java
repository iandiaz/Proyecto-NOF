

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
 * Servlet implementation class iguia_tras
 */
@WebServlet("/iguia_tras")
public class iguia_tras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iguia_tras() {
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
		//System.out.println("1");
		
		Cookie [] cookies=request.getCookies();
		if(request.getParameter("logout") !=  null){
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
			    }
			}
				response.sendRedirect("/001/");	
				return;
		}
		String Usuarios_nombre="";
		
		//fecha
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
		
		Statement state = null;
		Statement statefac = null;
		ResultSet Alertas_RS = null;
		ResultSet RS_FAC = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		statefac = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		String EID=request.getParameter("clientes_id");
    		String DID=request.getParameter("did");
    		String condiciones=request.getParameter("condiciones");
    		String obs=request.getParameter("obs");
    		String ref=request.getParameter("ref");

    		request.setAttribute("clientes_id",EID);
    		request.setAttribute("condiciones",condiciones);
    		request.setAttribute("ref",ref);
    		request.setAttribute("obs",obs);
    		request.setAttribute("did",DID);
	    				  		
    		
    		//:::::::::::::::::::::::::: :::::::::::::::::::::::::::::::
  		    String SQL_PRECIOBDNOF = "SELECT pgt_prod_id,pgt_precio_guia_traslado FROM precio_guia_traslado";
  		    ResultSet PRECIOBDNOF_RS = state.executeQuery(SQL_PRECIOBDNOF);
  		    //definimos un arreglo para los resultados
  		    ArrayList<String> preciosbdnof = new ArrayList<String>();
  		    //recorremos los resultados de la consulta
  		    while(PRECIOBDNOF_RS.next()){        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	preciosbdnof.add(PRECIOBDNOF_RS.getString("pgt_prod_id")+"/"+PRECIOBDNOF_RS.getString("pgt_precio_guia_traslado"));    
      	    }
    		
    		
    		
    		String ids="0";
    		
    		String[] seleccionado_detguias = request.getParameterValues("detguias[]");
    		if(seleccionado_detguias!=null)
    			for (String id_gi: seleccionado_detguias) {
	    			if(id_gi!=null && !id_gi.equals("-1")){
	    				ids+=","+id_gi;
	    			}
    			}
    		
    		
    		//traemos los id que ya estén en la base de datos 
    		


    		ArrayList<String> id_trasladosins= new ArrayList<String>();
 		    String SQL_trasyaingresados = "select `detalle_824`.`tras_id` from `detalle_824` where `detalle_824`.`estados_vig_novig_id`=1 ";
 		    System.out.println(SQL_trasyaingresados);
 		    ResultSet trasin_RS = state.executeQuery(SQL_trasyaingresados);
 		    while(trasin_RS.next()){
 		    	
 		    	id_trasladosins.add(trasin_RS.getString("tras_id").trim());
 		    	
 		   }
    		
    		
    		
    		ConBirt birtBD2= new ConBirt();
    		String queru="SELECT "
    				+ "		t.ALT_ID,"
    				+ "		t.tras_id,"
    				+ "		p.PROD_ID,"
    				+ "		p.PROD_PN_TLI_CODFAB,"
    				+ "		p.PROD_DESC_CORTO,"
    				+ "		p.PROD_COD_BAR_FAB,"
    				+ "		a.ALT_SERIE,"
    				+ "		CONVERT (VARCHAR(10), t.TRAS_FECHA, 105) AS fecha,"
    				+ "		u_destino.UBI_DESCRIPCION,"
    				+ "		t.TRAS_FECHA"
    				+ "	FROM"
    				+ "		traslado t"
    				+ "	INNER JOIN activo a ON t.ALT_ID = a.ALT_ID"
    				+ "	INNER JOIN producto p ON a.PROD_ID = p.PROD_ID"
    				+ "	INNER JOIN UBICACION u_destino ON t.TRAS_UBI_ID_DESTINO = u_destino.UBI_ID"
    				+ "	INNER JOIN UBICACION u_origen ON t.TRAS_UBI_ID_ORIGEN = u_origen.UBI_ID"
    				+ "	WHERE"
    				+ "		u_destino.DIRE_ID = "+request.getParameter("d_id")
    				+ "	AND u_origen.DIRE_ID = "+request.getParameter("o_id")
    				+ "	AND t.TRAS_GD='0'"
    				+ "	ORDER BY"
    				+ "		p.PROD_DESC_CORTO";
    		System.out.println(queru);
    		ResultSet QueryBirt2= birtBD2.Consulta(queru);
		    
		    if(request.getAttribute("filtro")!=null){
		    	request.setAttribute("filtro",request.getAttribute("filtro"));
		    }		    
		    ArrayList<String> alertas = new ArrayList<String>();
		    
		    
		    for(int i = 0; i < id_trasladosins.size(); i++){
		        System.out.println(id_trasladosins.get(i));
		    }
		    while(QueryBirt2.next()){
		    	String marcado="0";		 
		    	
		    
		    	if(id_trasladosins.indexOf(QueryBirt2.getString("tras_id")) !=-1){
		    		//si existe no hacemos nada
		    	}
		    	else{
		    		if(ids.contains(QueryBirt2.getString("tras_id"))) marcado=QueryBirt2.getString("tras_id");
		    		
		    		String precio2="NO EXISTE";
			    	
			    	for(int i =0; i<preciosbdnof.size();i++){
			    		String dat = preciosbdnof.get(i);
			    		String[] dat_ar = dat.split("/");
			    		
			    		if(dat_ar[0].equals(QueryBirt2.getString("PROD_ID"))){
			    			precio2=dat_ar[1];
			    		}
			    		
			    	}
		    		
		    		alertas.add(QueryBirt2.getString("ALT_ID")+"/"+QueryBirt2.getString("PROD_DESC_CORTO").replace("/", " ")
		    			+"/"+QueryBirt2.getString("PROD_PN_TLI_CODFAB").replace("/", " ")+"/"+QueryBirt2.getString("PROD_COD_BAR_FAB").replace("/", " ")+
		    			"/"+QueryBirt2.getString("ALT_SERIE").replace("/", " ")
		    			+"/"+marcado+"/"+QueryBirt2.getString("tras_id")+"/"+QueryBirt2.getString("fecha")+"/"+QueryBirt2.getString("UBI_DESCRIPCION").replace("/", " ")
		    			+"/"+QueryBirt2.getString("TRAS_FECHA")+"/"+QueryBirt2.getString("tras_id")+"///"+precio2);
		    	}
		    	
		    	
    	    }
		    QueryBirt2.close();
		    state.close();
            conexion.close();
                
            String[] ar_alertas = new String[alertas.size()];
            for(int x=0; x < alertas.size(); x++){
            	ar_alertas[x]=alertas.get(x);
            }
             
            request.setAttribute("ar_alertas", ar_alertas);
            
            request.setAttribute("d_id", request.getParameter("d_id"));
		    request.setAttribute("o_id", request.getParameter("o_id"));
		    
		    
		    request.setAttribute("empresas_id", request.getParameter("empresas_id"));
		    request.setAttribute("gv_fecha", request.getParameter("gv_fecha"));
		    
		    request.setAttribute("numtick", request.getParameter("numtick"));
		    request.setAttribute("obs", request.getParameter("obs"));
		    request.setAttribute("desc", request.getParameter("desc"));
		    request.setAttribute("glosa_desc", request.getParameter("glosa_desc"));
		    
		    
		    
		    
		    
		}catch(Exception ex){
			ex.printStackTrace();
		    out.println("ERROR "+ex);
		}
			
		RequestDispatcher rd = request.getRequestDispatcher("iguia_tras.jsp");
        rd.forward(request, response);
		
		}

}
