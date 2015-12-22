

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class rpt
 */
@WebServlet("/rpt")
public class rpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rpt() {
        super();
    
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

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	boolean sesion_valida=validateSession(request,response);
	
	if(!sesion_valida) response.sendRedirect("/001/");
	else {
		 mt(request,response);
	}
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

    
    //guardams el usuario qeu esta haciendo el reporte
    String usuario="";
    Cookie [] cookies=request.getCookies();
	
    if (cookies != null) {
	    for (Cookie cookie : cookies) {
	        if(cookie.getName().equals("Usuarios_nombre")) usuario=cookie.getValue();
	    }
	}
    
    
    
	if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1") ){
		PrintWriter out = response.getWriter();
		String filtros="a=1";
		
        if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
			
		}
        if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			filtros+="&id_p="+request.getParameter("select_prod")+"";
			
		}
		
		
		//out.write("<script>window.open(\"www.google.com\");</script>");
		
		out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_860_1_xls.php?"+filtros+"';setInterval(\"close()\",3600000);</script>");
	}
	if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("2")){
        PrintWriter out = response.getWriter();
		String filtros="";
        
        
        if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
			
		}
        
        if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			filtros+="&id_p="+request.getParameter("select_prod")+"";
			
		}
        
        out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_860_2_xls.php?inf=996-2&usu="+usuario+filtros+"';</script>");
    }
    
	     
   
	
	 
	 
	 
    
    /////////////////////GENERAR POR PDF ////////////////////////
    
	if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
		PrintWriter out = response.getWriter();
		String filtros="";
		
        if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
			
		}
        
		if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			filtros+="&id_p="+request.getParameter("select_prod")+"";
			
		}
		
		out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=860-1&usu="+usuario+filtros+"';</script>");
	}
    
    if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("2")){
        PrintWriter out = response.getWriter();
		String filtros="";
        
        
        if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
			
		}
        
        if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			filtros+="&id_p="+request.getParameter("select_prod")+"";
			
		}
        out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=860-2&usu="+usuario+filtros+"';</script>");
    }
    
    
    
   
    
    /////////////////////GENERAR POR WEB ////////////////////////
    
    if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
		PrintWriter out = response.getWriter();
		String filtros="";
		
        if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
		}
        
		if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			filtros+="&id_p="+request.getParameter("select_prod")+"";
			
		}
		
		out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_860_1_pdf.php?inf=860-1&usu="+usuario+filtros+"';</script>");
	}
    
    if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("2")){
        PrintWriter out = response.getWriter();
		String filtros="";
        
       
        if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
			filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
			
		}
        
        
        if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			filtros+="&id_p="+request.getParameter("select_prod")+"";
			
		}
        out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_860_2_pdf.php?inf=996-2&usu="+usuario+filtros+"';</script>");
    }
    
   
    
	
}
public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	
	PrintWriter out = response.getWriter();
	
	///////////////////////////////////////////
	//////DEFINE DESLOGEO//////////////////////
	
	Cookie [] cookies=request.getCookies();
	if(request.getParameter("logout") !=  null){
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		    	//System.out.println("cookie logout: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) {cookie.setMaxAge(0);cookie.setPath("/");response.addCookie(cookie);}
		    }
		}
			response.sendRedirect("/001/");
			return;
		
	}
	
	
	//////////////////////////////////////////////////
	////////DEFINE PARAMETROS DE USUARIO//////////////
	
	String Usuarios_nombre="";
	
	
	
	Calendar ahoraCal = Calendar.getInstance();
	String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
	String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
	if (cookies != null) {
	    for (Cookie cookie : cookies) {
	        if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
	    }
	}
	request.setAttribute("usuname", Usuarios_nombre);
	
	//////////////////////////////////////////////////
	
	
	Statement state = null;
	
	ResultSet GRUPOS_RS=null;
	ResultSet EMPRESAS_RS=null;
	ResultSet DIRECCION_RS=null;
	ResultSet UBI_RS=null;
	ResultSet FUNCIONALIDAD_RS=null;
	
	
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	//Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO2,Constantes.USER,Constantes.PASS);
		Connection conexion=(Connection) DriverManager.getConnection("jdbc:sqlserver://www.birt.cl;databaseName=newoffice;user=nof;password=Newoffice2014;");
		
		state = (Statement) ((java.sql.Connection) conexion).createStatement();
	
	
	
	
	  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
	  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_GRUPOS = "SELECT * FROM grupo";
	    GRUPOS_RS =  state.executeQuery(SQL_GRUPOS);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> grupos = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(GRUPOS_RS.next()){        	   
 	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	grupos.add(GRUPOS_RS.getString("grup_id")+"/"+GRUPOS_RS.getString("grup_nombre"));
 	    }
	  System.out.println("SIZE LIST: "+grupos.size());  	
	  GRUPOS_RS.close();	
	  state.close();
	  String[] ar_grupos = new String[grupos.size()];
     for(int x=0; x < grupos.size(); x++){
   	  ar_grupos[x]=grupos.get(x);
     }
           
     request.setAttribute("ar_grupos", ar_grupos);
  //:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
     
     
     //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     String url = "jdbc:sqlserver://www.birt.cl;databaseName=newoffice;user=nof;password=Newoffice2014;";
     Connection conexionBIRT = (Connection) DriverManager.getConnection(url);
     
     
     state = (Statement) ((Connection) conexionBIRT).createStatement();
    String SQL_EMPRESAS = "SELECT "
    		+ "		`empresas`.`empresas_id`,"
    		+ "		`empresas`.`empresas_nombrenof`"
    		+ "	FROM"
    		+ "		`empresas`"
    		+ "	WHERE"
    		+ "		`empresas`.`estados_vig_novig_id` = 1"
    		+ "	ORDER BY"
    		+ "		`empresas`.`empresas_nombrenof`   ";
    System.out.println(SQL_EMPRESAS);
    ConNof conqueryy= new ConNof();
    EMPRESAS_RS=conqueryy.Consulta(SQL_EMPRESAS);
    
    
    
    //definimos un arreglo para los resultados
    ArrayList<String> empresas = new ArrayList<String>();
    //recorremos los resultados de la consulta
    while(EMPRESAS_RS.next()){        	   
	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof"));
    }
  EMPRESAS_RS.close();
  state.close();
  conqueryy.Desconectar();
  String[] ar_empresas = new String[empresas.size()];
  for(int x=0; x < empresas.size(); x++){
	 ar_empresas[x]=empresas.get(x);
  }
        
  request.setAttribute("ar_empresas", ar_empresas);
  //::::::::::::::::::::::::::::::::::::::::::sql direcciones para select option::::::::::::::::::::::::::::::::::::::
  state = (Statement) ((java.sql.Connection) conexion).createStatement();
  String SQL_DIRECCION = "SELECT * FROM direccion  ORDER BY direccion.DIRE_DIRECCION ";
    DIRECCION_RS =  state.executeQuery(SQL_DIRECCION);		    
    //definimos un arreglo para los resultados		    
    ArrayList<String> direcciones = new ArrayList<String>();		   
    //recorremos los resultados de la consulta
    while(DIRECCION_RS.next()){        	   
	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
    	direcciones.add(DIRECCION_RS.getString("DIRE_ID")+"/"+DIRECCION_RS.getString("DIRE_DIRECCION")+"/"+DIRECCION_RS.getString("clpr_id"));
	    }
  DIRECCION_RS.close();	
  state.close();
  String[] ar_direcciones = new String[direcciones.size()];
 for(int x=0; x < direcciones.size(); x++){
	 ar_direcciones[x]=direcciones.get(x);
 }
       
 request.setAttribute("ar_direcciones", ar_direcciones);
 
 //::::::::::::::::::::::::::::::::::::::::::sql ubicaciones para select option::::::::::::::::::::::::::::::::::::::
  state = (Statement) ((java.sql.Connection) conexion).createStatement();
  String SQL_UBI = "SELECT * FROM ubicacion where UBICACION.UBI_ESTADO='VIGENTE' ";
    UBI_RS =  state.executeQuery(SQL_UBI);		    
    //definimos un arreglo para los resultados		    
    ArrayList<String> ubicaciones = new ArrayList<String>();
    ArrayList<String> tubicaciones = new ArrayList<String>();	
    //recorremos los resultados de la consulta
    while(UBI_RS.next()){        	   
    //SI HAY ubicaciones, ENTONCES GUARDAMOS LAS ubicaciones
    	ubicaciones.add(UBI_RS.getString("UBI_ID")+"/"+UBI_RS.getString("UBI_DESCRIPCION").replace("/","")+"/"+UBI_RS.getString("DIRE_ID")+"/"+UBI_RS.getString("UBI_TIPO"));
    	//llenamos arreglo de los tipos de ubicacion
    	tubicaciones.add(UBI_RS.getString("UBI_TIPO"));
    	
    }
  UBI_RS.close();	
  state.close();
  
  String[] ar_ubi = new String[ubicaciones.size()];
for(int x=0; x < ubicaciones.size(); x++){
	
	ar_ubi[x]=ubicaciones.get(x);
}
request.setAttribute("ar_ubi", ar_ubi);
String[] ar_tubi = new String[tubicaciones.size()];
for(int x=0; x < tubicaciones.size(); x++){
	ar_tubi[x]=tubicaciones.get(x);
}

request.setAttribute("ar_tubi", ar_tubi);

//::::::::::::::::::::::::::::::::::::::::::sql funcionalidad para select option::::::::::::::::::::::::::::::::::::::
  state = (Statement) ((java.sql.Connection) conexion).createStatement();
  String SQL_FUNCIONALIDAD = "SELECT * FROM funcionalidad WHERE funcionalidad.FUNC_ESTADO='VIGENTE' ";
  FUNCIONALIDAD_RS =  state.executeQuery(SQL_FUNCIONALIDAD);		    
    //definimos un arreglo para los resultados		    
    ArrayList<String> funcionalidades = new ArrayList<String>();		   
    //recorremos los resultados de la consulta
    while(FUNCIONALIDAD_RS.next()){        	   
    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
    	funcionalidades.add(FUNCIONALIDAD_RS.getString("FUNC_ID")+"/"+FUNCIONALIDAD_RS.getString("FUNC_NOMBRE"));
    }
  FUNCIONALIDAD_RS.close();	
  state.close();
  String[] ar_funcionalidades = new String[funcionalidades.size()];
for(int x=0; x < funcionalidades.size(); x++){
   ar_funcionalidades[x]=funcionalidades.get(x);
}
     
request.setAttribute("ar_funcionalidades", ar_funcionalidades);

        //::::::::::::::::::::::::::::::::::::::::::sql productos para select option::::::::::::::::::::::::::::::::::::::
        state = (Statement) ((java.sql.Connection) conexion).createStatement();
        String SQL_PROD = "SELECT [PRODUCTO].[PROD_ID],[PRODUCTO].[PROD_DESC_LARGO],[PRODUCTO].[PROD_COD_BAR_FAB],[PRODUCTO].[FUNC_ID] FROM [PRODUCTO] WHERE [PRODUCTO].[PROD_ESTADO]='VIGENTE' ORDER BY [PRODUCTO].[PROD_COD_BAR_FAB] ";
        ResultSet PRODUCTOS_RS =  state.executeQuery(SQL_PROD);
        //definimos un arreglo para los resultados
        ArrayList<String> productos  = new ArrayList<String>();
        //recorremos los resultados de la consulta
        while(PRODUCTOS_RS.next()){
            //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
            productos.add(PRODUCTOS_RS.getString("PROD_ID")+"/"+PRODUCTOS_RS.getString("PROD_COD_BAR_FAB")+" - "+PRODUCTOS_RS.getString("PROD_DESC_LARGO")+"/"+PRODUCTOS_RS.getString("FUNC_ID"));
        }
        PRODUCTOS_RS.close();
        state.close();
        String[] ar_productos = new String[productos.size()];
        for(int x=0; x < productos.size(); x++){
            ar_productos[x]=productos.get(x);
        }
        
        request.setAttribute("ar_productos", ar_productos);

        //vemos si tiene permiso para todos los reportes

        ConNof conquery= new ConNof();
        String query_permisos_reportes="SELECT "
        		+ "		`perfilusu_has_permisos`.`permisos_id` "
        		+ "	FROM"
        		+ "		`perfilusu_has_permisos`"
        		+ "	WHERE"
        		+ "		`perfilusu_has_permisos`.`estados_vig_novig_id` = 1 "
        		+ "	AND `perfilusu_has_permisos`.`perfilusu_id` = 1"
        		+ "	AND `perfilusu_has_permisos`.`permisos_id` IN (36, 60, 61)";
        
        conquery.Consulta(query_permisos_reportes);
        String tiene36="";
        String tiene60="";
        String tiene61="";
        
 
 
	conexion.close();
    RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
	a.forward(request, response);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	
}

}
