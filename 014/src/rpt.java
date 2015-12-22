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

import Constantes.Constantes;
import Constantes.Controlador;

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
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean sesion_valida=Controlador.validateSession(request,response);
		
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
		
        
        
        //////////////////////////EXCEL 
        
        
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
		
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_014_1_xls.php?"+filtros+"';</script>");
		}
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
		
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_014_2_xls.php?"+filtros+"';</script>");
		}
		/////////////////////////////PDF 
		
		
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=014-1&usu="+usuario+filtros+"';</script>");
		}
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("2")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
		
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=014-2&usu="+usuario+filtros+"';</script>");
		}
		
		
		
		
		
		
   /////////////////////GENERAR POR WEB ////////////////////////
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			if(request.getParameter("select_contacto") !=  null && !request.getParameter("select_contacto").equals("") ){
				filtros+="&id_cont="+request.getParameter("select_contacto")+"";
				
			}
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
		
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_014_1_pdf.php?inf=996-1&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("2")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			if(request.getParameter("select_eclpr") !=  null && !request.getParameter("select_eclpr").equals("") ){
				filtros+="&id_eclpr="+request.getParameter("select_eclpr")+"";
				
			}
			if(request.getParameter("select_responsable") !=  null && !request.getParameter("select_responsable").equals("") ){
				filtros+="&id_re="+request.getParameter("select_responsable")+"";
				
			}
			if(request.getParameter("cli") !=  null && request.getParameter("cli").equals("1") ){
				filtros+="&cli="+("1")+"";
				
			}
			if(request.getParameter("pro") !=  null && request.getParameter("pro").equals("1") ){
				filtros+="&pro="+("1")+"";
				
			}
			if(request.getParameter("pros") !=  null && request.getParameter("pros").equals("1") ){
				filtros+="&pros="+("1")+"";
				
			}
		
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_014_2_pdf.php?inf=996-1&usu="+usuario+filtros+"';</script>");
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
		ResultSet ESTCLPR_RS=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=(Connection) DriverManager.getConnection
	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_estclpr = "SELECT * FROM estado_clpr WHERE estados_vig_novig_id=1 ORDER BY Estado_Clpr_nombre";
		    ESTCLPR_RS =  state.executeQuery(SQL_estclpr);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> estclpr = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(ESTCLPR_RS.next()){        	   
  	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estclpr.add(ESTCLPR_RS.getString("Estado_Clpr_id")+"/"+ESTCLPR_RS.getString("Estado_Clpr_nombre"));
  	    }
		  ESTCLPR_RS.close();
		  String[] ar_estclpr = new String[estclpr.size()];
        for(int x=0; x < estclpr.size(); x++){
      	  ar_estclpr[x]=estclpr.get(x);
        }
              
        request.setAttribute("ar_estclpr", ar_estclpr);
		
	  //::::::::::::::::::::::::::::::::::::::::::sql usuarios internos para responsable select option::::::::::::::::::::::::::::::::::::::  
	  state = (Statement) ((java.sql.Connection) conexion).createStatement();
	  String SQL_resp = "SELECT `contacto`.`CONT_ID`, CONCAT_WS(' ',`contacto`.`CONT_NOMBRE`,`contacto`.`CONT_APEP`,`contacto`.`CONT_APEM`) as Usuarios_nombre "
	  		+ "	FROM"
	  		+ "		`contacto`"
	  		+ "	ORDER BY `contacto`.`CONT_NOMBRE` ";
	  
	    ResultSet resp_RS = state.executeQuery(SQL_resp);		    
	    //definimos un arreglo para los resultados		    
	    ArrayList<String> responsables = new ArrayList<String>();		   
	    //recorremos los resultados de la consulta
	    while(resp_RS.next()){        	   
	    	responsables.add(resp_RS.getString("CONT_ID")+"/"+resp_RS.getString("Usuarios_nombre"));
	    }
	  String[] ar_responsables = new String[responsables.size()];
      for(int x=0; x < responsables.size(); x++){
    	  ar_responsables[x]=responsables.get(x);
      }
            
      request.setAttribute("ar_responsables", ar_responsables);
	        
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
