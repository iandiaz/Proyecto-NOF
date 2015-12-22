

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Mmanual2
 */
@WebServlet("/Mmanual2")
public class Mmanual2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mmanual2() {
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
		
		System.out.println("RECORRIENDO JAVA");
		String idmodulo=request.getParameter("id_m");
		
			
			try {
				 
				 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				 
			       
					if (isMultipart) {
						System.out.println("intentando subir archivo");
			        	try {
							
							 List<FileItem> multiparts = new ServletFileUpload(
	                                 new DiskFileItemFactory()).parseRequest(request);
							
			            
							 for(FileItem item : multiparts){
								 //System.out.println(item.getString()+" "+item.getFieldName());
								 if(idmodulo==null && item.getFieldName().equals("id_m")) {
									 System.out.println("id_modulo nulo");
									 idmodulo=item.getString();
								 }
									 
									 
				                    if(!item.isFormField()){
				                        String name = new File(item.getName()).getName();
				                        item.write( new File(Constantes.DIR_MANUALES + File.separator + name));
				                        
				                        
				                        Class.forName("com.mysql.jdbc.Driver");
				           			 Connection conexion = (Connection) DriverManager.getConnection
				           		    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
				           			//import java.io.IOException;
				           			
				               		 Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
				           		    
				           		    String SQL_up = "UPDATE `modulos` SET `modulos`.`modulos_manualnombre` = '"+name+"'  WHERE 	`modulos`.`Modulos_id` = "+idmodulo;
				           		    System.out.println(SQL_up);
				           		    state.executeUpdate(SQL_up);
				                        //RequestDispatcher rd_up = request.getRequestDispatcher("Mmanual?mExito=OK");
				        	            //rd_up.forward(request, response);
				                    }
				                }


							 
							 
							 
			                
			            } catch (FileUploadException e) {
			                e.printStackTrace();
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
					}
				
				
	    		 
	    		
			  
			
	          
	           
	           
	            
	    		
			}catch(Exception ex){
			    ex.printStackTrace();
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
		ResultSet modulos_RS = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 Connection conexion = (Connection) DriverManager.getConnection
		    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			//import java.io.IOException;
			
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		    
		    String SQL_modulos = "SELECT modulos.Modulos_id, modulos.Modulos_nombre, modulos.Modulos_codigo, modulos.estados_vig_novig_id, "
 					+ "modulos.Menus_id, modulos.modulos_manualnombre, e.estados_vig_novig_nombre, modulos_descripcion FROM modulos "
 					+ "INNER JOIN estados_vig_novig e ON e.estados_vig_novig_id=modulos.estados_vig_novig_id "
 					+ "WHERE Modulos_id="+idmodulo;
		    System.out.println("SQL_modulos : "+SQL_modulos);
		    modulos_RS =  state.executeQuery(SQL_modulos);
		    if(modulos_RS.next()){
		    	request.setAttribute("Modulos_id",modulos_RS.getString("Modulos_id"));
		    	request.setAttribute("Modulos_nombre",modulos_RS.getString("Modulos_nombre"));
		    	request.setAttribute("modulos_manualnombre",modulos_RS.getString("modulos_manualnombre"));
		    	
		    	
		   }
		    modulos_RS.close();
		    state.close();	    
		    
          conexion.close();
                
		}catch(Exception ex){
			out.println("Error "+ex.getMessage());
		   ex.printStackTrace();
		}
	
		String msg="";
		
		System.out.println("msg: "+msg);
		RequestDispatcher rd = request.getRequestDispatcher("mmanual2.jsp"+msg);
        rd.forward(request, response);
		
	}

}
