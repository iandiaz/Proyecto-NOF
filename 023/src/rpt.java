

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

import DAO.FuncionalidadesDAO;
import DAO.MarcasDAO;
import VO.EstadosVigNoVigVO;
import VO.FuncionalidadVO;
import VO.MarcaVO;

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
			
			if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
			if(request.getParameter("select_marca") !=  null && !request.getParameter("select_marca").equals("") ){
				filtros+="&id_ma="+request.getParameter("select_marca")+"";
				
			}
			
			
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_023_1_xls.php?"+filtros+"';</script>");
		}
		
		/////////////////////////////PDF 
		
		
		
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			
			if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
			if(request.getParameter("select_marca") !=  null && !request.getParameter("select_marca").equals("") ){
				filtros+="&id_ma="+request.getParameter("select_marca")+"";
				
			}
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"generaPDF.php?inf=023-1&usu="+usuario+filtros+"';</script>");
		}
		
		
		
		
   /////////////////////GENERAR POR WEB ////////////////////////
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("1")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			
			if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
			if(request.getParameter("select_marca") !=  null && !request.getParameter("select_marca").equals("") ){
				filtros+="&id_ma="+request.getParameter("select_marca")+"";
				
			}
			
			
			out.write("<script>window.location='"+Constantes.URL_SITIO+"rpt_023_1_pdf.php?inf=023-1&usu="+usuario+filtros+"';</script>");
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
		
		//verificamos permisos 225
		
		String p225=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "225");
		
		request.setAttribute("p225", p225);
		
		
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
		
		
		
		
		  //::::::::::::::::::::::::::::::::::::::::::sql grupos para select option::::::::::::::::::::::::::::::::::::::
		  state = (Statement) ((java.sql.Connection) conexion).createStatement();
		  String SQL_GRUPOS = "SELECT * FROM grupos WHERE estados_vig_novig_id=1 ORDER BY grupos_nombre";
		  System.out.println(SQL_GRUPOS);  	
		    GRUPOS_RS =  state.executeQuery(SQL_GRUPOS);		    
		    //definimos un arreglo para los resultados		    
		    ArrayList<String> grupos = new ArrayList<String>();		   
		    //recorremos los resultados de la consulta
		    while(GRUPOS_RS.next()){        	   
     	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	grupos.add(GRUPOS_RS.getString("grupos_id")+"/"+GRUPOS_RS.getString("grupos_nombre").replace(" ", "&nbsp;"));
     	    }
		  GRUPOS_RS.close();	
		  String[] ar_grupos = new String[grupos.size()];
         for(int x=0; x < grupos.size(); x++){
       	  ar_grupos[x]=grupos.get(x);
         }
               
         request.setAttribute("ar_grupos", ar_grupos);
      //:::::::::::::::::::::::::: sql trae empresas para select option:::::::::::::::::::::::::::::::::::::
         state = (Statement) ((java.sql.Connection) conexion).createStatement();
         String SQL_EMPRESAS = "SELECT "
 	    		+ "		`empresas`.`empresas_id`,"
 	    		+ "		`empresas`.`empresas_nombrenof`,"
 	    		+ "		`empresas`.`grupos_id`"
 	    		+ "	FROM"
 	    		+ "		`empresas`"
 	    		+ "	WHERE"
 	    		+ "		`empresas`.`estados_vig_novig_id` = 1"
 	    		+ "	ORDER BY"
 	    		+ "		`empresas`.`empresas_nombrenof`   "; 
         System.out.println(SQL_EMPRESAS);  	
		  
         EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
	    //definimos un arreglo para los resultados
	    ArrayList<String> empresas = new ArrayList<String>();
	    //recorremos los resultados de la consulta
	    while(EMPRESAS_RS.next()){        	   
    	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
	    	empresas.add(EMPRESAS_RS.getString("empresas_id")+";;"+EMPRESAS_RS.getString("empresas_nombrenof")+";;"+EMPRESAS_RS.getString("grupos_id"));    
	    }
	  EMPRESAS_RS.close();
	  String[] ar_empresas = new String[empresas.size()];
      for(int x=0; x < empresas.size(); x++){
    	 ar_empresas[x]=empresas.get(x);
      }
            
      request.setAttribute("ar_empresas", ar_empresas);
         
	   	//FUNCIONALIDADES
     	request.setAttribute("funcionalidades", FuncionalidadesDAO.getFuncionalidades(new FuncionalidadVO()));
     
     	//MARCAS 
     	MarcaVO marca_filter = new MarcaVO();
   		marca_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
          
    	request.setAttribute("marcas", MarcasDAO.getMarcas(marca_filter));
     
     	state.close();
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


}
