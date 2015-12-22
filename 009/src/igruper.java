

import java.io.FileOutputStream;
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
 * Servlet implementation class Igruper
 */
@WebServlet("/igruper")
public class igruper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public igruper() {
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
		String id_iusuario="";
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_iusuario=cookie.getValue();
		    }
		}
		//grabar
		boolean grupoexiste=false;
		if(request.getParameter("grabar") !=  null){
			Statement stategrabar = null;
			Statement stateval = null;
			ResultSet rs_valgrupos= null;
			try {
				String gruposusu_nombre = request.getParameter("gruposusu_nombre");
		    	String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
				//import java.io.IOException;
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
	    		stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		stateval = (Statement) ((java.sql.Connection) conexion).createStatement();
	    		
	    		String sql_valgrupo="SELECT * FROM gruposusu WHERE gruposusu_nombre='"+gruposusu_nombre.toUpperCase()+"'";
	    		rs_valgrupos=stateval.executeQuery(sql_valgrupo);
	    		//stateval.close();
	    		if(rs_valgrupos.next()){
	    			stateval.close();
	    			rs_valgrupos.close();conexion.close();
	    			grupoexiste=true;
	    			//response.sendRedirect("Igrupo?Exito=NOK");
	    		}else{
			    String SQL_INSERT = "INSERT INTO gruposusu (gruposusu_nombre, estados_vig_novig_id, gruposusu_feccreacion, gruposusu_creador) VALUES ('"+gruposusu_nombre.toUpperCase()+"',"+estados_vig_novig_id+",now(),"+id_iusuario+")";
			    System.out.println("SQL Instert: "+SQL_INSERT);
			    stategrabar.executeUpdate(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
                    
                    ResultSet generatedKeys = null;
                    generatedKeys = stategrabar.getGeneratedKeys();
                    String id_perf_last="";
                    if (generatedKeys.next()) {
                        id_perf_last=generatedKeys.getString(1);
                    }
                    
                    System.out.println("Nuevo id perfil de perfiles: "+id_perf_last);
    
                    
                    
                    String[] seleccionado = request.getParameterValues("perfiles_sel[]");
		    		if(seleccionado!=null) for (String id_per: seleccionado) {
		    			if(id_per!=null && !id_per.equals("-1")){
		    				String insertperper=""
                            + " INSERT INTO `gruposusu_has_perfilusu` ("
                            + " 	`gruposusu_has_perfilusu`.`gruposusu_id`, "
                            + " 	`gruposusu_has_perfilusu`.`perfilusu_id`  "
                            + " ) "
                            + " VALUES"
                            + " 	('"+id_perf_last+"', '"+id_per+"')";
			    			
		    				stategrabar.executeUpdate(insertperper);
			    			System.out.println("Perfilusu insertado: "+id_per+" perfdeperfiles: "+id_perf_last);
		    			}	    			
		    		}
                    

                    
			    stateval.close();
    			rs_valgrupos.close();
			    stategrabar.close();
                
                //insertamos los grupos que inclute el nuevo perfil de perfiles
                    
                    
                    
                    
                    
                    
                    
                    
	            conexion.close(); 
	            
	            //insertamos en el archivo sync
	            
	            /*if(Constantes.SYNCDB==1){
	    			  //creamos un archivo de texto con la instruccion de insertar perfil de usuario 
	    			  String instruccion="005i/"+grupos_nombre.toUpperCase()+"\n";
		    		  byte dataToWrite[] =instruccion.getBytes(); //...
		    				  FileOutputStream outFile = new FileOutputStream(Constantes.SYNCDBDIR+"syncdb.txt",true);
		    				  outFile.write(dataToWrite);
		    				  outFile.close();
		    		    
	    		  }*/
	            
	            
	            
	            RequestDispatcher rd_up = request.getRequestDispatcher("Menugruper?Exito=OK");
	            rd_up.forward(request, response);
	    		}
			}catch(Exception ex){
			    out.println("Unable to connect to database "+ex);
			}
			if(!grupoexiste){return;}
		}
		
		//fin grabar
		
		
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
		Statement statecor = null;		
		ResultSet ESTADOS_RS = null;
		ResultSet CORRELATIVO_RS = null;
		try {
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 statecor = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
		    String SQL_ESTADOS = "SELECT * FROM estados_vig_novig";
		    ESTADOS_RS =  state.executeQuery(SQL_ESTADOS);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> estados = new ArrayList<String>();
		   
		    //recorremos los resultados de la consulta
		    while(ESTADOS_RS.next()){        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	estados.add(ESTADOS_RS.getString("estados_vig_novig_id")+"/"+ESTADOS_RS.getString("estados_vig_novig_nombre"));
        	    
    	    }
		  System.out.println("SIZE LIST: "+estados.size());
		    	
		  ESTADOS_RS.close();
		  state.close();
          
                
          String[] ar_estados = new String[estados.size()];
          for(int x=0; x < estados.size(); x++){
           	ar_estados[x]=estados.get(x);
          }
          request.setAttribute("ar_estados", ar_estados);
            
            
            
            
          //id correlativo
          String last_id_grupos_sql="SELECT"
		    		+ " 	`gruposusu`.`gruposusu_id`"
		    		+ " FROM"
		    		+ " 	`gruposusu`"
		    		+ " ORDER BY"
		    		+ " 	`gruposusu`.`gruposusu_id` DESC"
		    		+ " LIMIT 1";
          System.out.println("Correlativo: "+last_id_grupos_sql);
          CORRELATIVO_RS =  statecor.executeQuery(last_id_grupos_sql);
         
          int correlativo=0;
		  if(CORRELATIVO_RS.next()) correlativo=CORRELATIVO_RS.getInt("gruposusu_id")+1;
          request.setAttribute("correlativo", correlativo+"");
          
          statecor.close();
            
            //:::::::::::::::::::::::::: sql trae perfiles usuario:::::::::::::::::::::::::::::::::::::
            
            Statement state_perf = (Statement) ((java.sql.Connection) conexion).createStatement();
            
            String SQL_PERFILES = " "
            + "             SELECT "
            + "             `perfilusu`.`perfilusu_id`, "
            + "             `perfilusu`.`perfilusu_nombre` "
            + "             FROM "
            + "                 `perfilusu` "
            + "             WHERE "
            + "             `perfilusu`.`estados_vig_novig_id` = 1 ORDER BY `perfilusu`.`perfilusu_nombre` ";
            
            System.out.println(SQL_PERFILES);
            
            ResultSet PERFILES_RS =  state_perf.executeQuery(SQL_PERFILES);
            
            ArrayList<String> perfiles  = new ArrayList<String>();
            
            int numrows=0;
            while(PERFILES_RS.next()){
                perfiles.add(PERFILES_RS.getString("perfilusu_id")+"/"+PERFILES_RS.getString("perfilusu_nombre").replace("/", " "));
                
                numrows++;
            }
            PERFILES_RS.close();
            state_perf.close();
            
            String[] ar_perfiles = new String[perfiles.size()];
            for(int x=0; x < perfiles.size(); x++){
                ar_perfiles[x]=perfiles.get(x);
            }
            
            request.setAttribute("ar_perfiles", ar_perfiles);
            
            
          conexion.close();
          
		}catch(Exception ex){
            ex.printStackTrace();
		    out.println("Error "+ex);
		}
		
		String msg="";
		if(grupoexiste){
			msg="?Exito=NOK";
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
			String grupos_nombre = request.getParameter("gruposusu_nombre");
			request.setAttribute("gruposusu_nombre",grupos_nombre);
			request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
			//System.out.println("grupos_nombre "+grupos_nombre);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("igruper.jsp"+msg);
        rd.forward(request, response);
		
	}

}
