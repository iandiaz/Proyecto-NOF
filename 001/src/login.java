

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void validateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	PrintWriter out = response.getWriter(); 
		//validamos las credenciales de usuario
    	String login= request.getParameter("login");
    	String pass= request.getParameter("pass");
    	
    	HttpSession sesion=request.getSession(true);
    	sesion.setMaxInactiveInterval(3600);
    	
    	//System.out.println(sesion.getId()+" :ID 1");
    	// System.out.println(sesion.getServletContext().getContextPath());
    	
    	boolean existe_user = false; 
    	boolean pass_correct = false; 
    	
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		//out.println("Conexi�n realizada con �xito a: "+conexion.getCatalog());
    	
			Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
			
			String query="SELECT "
							+ " `usuarios`.`Usuarios_id`, "
							+ "	`usuarios`.`Usuarios_nombre`, "
							+ " `usuarios`.`Usuarios_ape_p`, "
							+ " `usuarios`.`Usuarios_ape_m`, "
							+ " `usuarios`.`Usuarios_login`, "
							+ " `usuarios`.`Usuarios_pass`, "
							+ " `usuarios`.`Usuarios_email`, "
							+ " `usuarios`.`tipo_usu_id`, "
							+ " `usuarios`.`perfilusu_id` "
							+ " FROM"
							+ "	`usuarios` "
							+ " WHERE "
							+ "	`usuarios`.`Usuarios_login` = '"+login+"' "
							+ " AND `usuarios`.`estados_vig_novig_id` = 1";
			System.out.println("SQL: "+query);
    		ResultSet rs = state.executeQuery(query);
			
    		UsuarioOB userSession = new UsuarioOB();
    		
    		while(rs.next()) {
    			
    			String nombre_usu=rs.getString("Usuarios_nombre");
    			
    			if(rs.getString("Usuarios_ape_p")!=null)nombre_usu+=" "+rs.getString("Usuarios_ape_p");
    			if(rs.getString("Usuarios_ape_m")!=null)nombre_usu+=" "+rs.getString("Usuarios_ape_m");
    			
    			userSession.setUsuarios_id(rs.getString("Usuarios_id"));
    			userSession.setUsuarios_nombre(nombre_usu);
    			userSession.setUsuarios_login(rs.getString("Usuarios_login"));
    			userSession.setUsuarios_email(rs.getString("Usuarios_email"));
    			userSession.setTipo_usu_id(rs.getString("tipo_usu_id"));
    			userSession.setPerfilusu_id(rs.getString("perfilusu_id"));
    			//rs.getString(1);
    			existe_user=true;
    			//System.out.println(rs.getString("Usuarios_id"));
    			if(rs.getString("Usuarios_pass").toUpperCase().equals(pass.toUpperCase())) pass_correct=true;
    		}
    		if(existe_user && pass_correct ) {
    			sesion.setAttribute("userSession", userSession);
    			ServletContext context1 = sesion.getServletContext();
    			context1.setAttribute("userSession", userSession);
    			//pasamos los parametros a una cookie
    			
    			Cookie Usuarios_id = new Cookie("Usuarios_id", userSession.getUsuarios_id());
    			Cookie Usuarios_nombre = new Cookie("Usuarios_nombre", userSession.getUsuarios_nombre());
    			Cookie Usuarios_login = new Cookie("Usuarios_login", userSession.getUsuarios_login());
    			Cookie Usuarios_email = new Cookie("Usuarios_email", userSession.getUsuarios_email());
    			Cookie tipo_usu_id = new Cookie("tipo_usu_id", userSession.getTipo_usu_id());
    			Cookie perfilusu_id = new Cookie("perfilusu_id", userSession.getPerfilusu_id());
    			
    			Usuarios_id.setPath("/");
    			Usuarios_id.setMaxAge(Constantes.T_SESSION);
    			
    			Usuarios_nombre.setPath("/");
    			Usuarios_nombre.setMaxAge(Constantes.T_SESSION);
    			
    			Usuarios_login.setPath("/");
    			Usuarios_login.setMaxAge(Constantes.T_SESSION);
    			
    			Usuarios_email.setPath("/");
    			Usuarios_email.setMaxAge(Constantes.T_SESSION);
    			
    			tipo_usu_id.setPath("/");
    			tipo_usu_id.setMaxAge(Constantes.T_SESSION);
    			
    			perfilusu_id.setPath("/");
    			perfilusu_id.setMaxAge(Constantes.T_SESSION);
    			
    	    	response.addCookie(Usuarios_id);
    	    	response.addCookie(Usuarios_nombre);
    	    	response.addCookie(Usuarios_login);
    	    	response.addCookie(Usuarios_email);
    	    	response.addCookie(tipo_usu_id);
    	    	response.addCookie(perfilusu_id);
    		}

    			//insertamos logs
    		String log_sql=""
    				+ " INSERT INTO `nof`.`log` ("
    				+ "	`nof`.`log`.`log_fec`, "
    				+ "	`nof`.`log`.`log_nombre`, " 
    				+ "	`nof`.`log`.`Usuarios_id` "
    				+ " ) "
    				+ " VALUES "
    				+ " (now(), 'Ingreso a sistema', '"+userSession.getUsuarios_id()+"') ";
//    		state.executeUpdate(log_sql);
    		
    		
    			
    			// cerrando resultset

    			rs.close(); state.close();
			
    		conexion.close();
    		
    		
    		} catch(SQLException ex){ 
    			ex.printStackTrace();
    		} catch(Exception ex){ 
    			ex.printStackTrace();
    		}
    	//enviamos respuesta al jsp login
    	String msg="";
    	if(existe_user && pass_correct) {
    		//msg="Usuario existe";
    		response.sendRedirect("/003/");
    	}
    	else {
	    	if(!existe_user)msg="USUARIO NO EXISTE";
	    	else if(!pass_correct)msg="LA CONTRASE�A INGRESADA NO ES CORRECTA";
	    	RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg="+msg);
	    	a.forward(request, response);
	    }
    }

    public void rememberPass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	//validamos que el usuario ingresado exista
    	String login= request.getParameter("login");
    	
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		//out.println("Conexi�n realizada con �xito a: "+conexion.getCatalog());
    	
			Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
			
			String query="SELECT "
							+ " `usuarios`.`Usuarios_id`, "
							+ "	`usuarios`.`Usuarios_nombre`, "
							+ " `usuarios`.`Usuarios_login`, "
							+ " `usuarios`.`Usuarios_pass`, "
							+ " `usuarios`.`Usuarios_email`, "
							+ " `usuarios`.`tipo_usu_id` "
							+ " FROM"
							+ "	`usuarios` "
							+ " WHERE "
							+ "	`usuarios`.`Usuarios_login` = '"+login+"' "
							+ " AND `usuarios`.`estados_vig_novig_id` = 1";
			
    		ResultSet rs = state.executeQuery(query);
			
    		boolean existe_user=false;
    		String usu_email="";
    		while(rs.next()) {
    			usu_email=rs.getString("Usuarios_email");
    			existe_user=true;
    			
    		}
    		if(existe_user){
    			
    			
    			//generamos una nueva contrase�a 
    			String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    			Random rnd = new Random();


 			   int len =6;
    			   StringBuilder sb = new StringBuilder( len );
    			   
    			   for( int i = 0; i < len; i++ )  sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
    			   
    			
    			
    			String new_pass = sb.toString();
    			System.out.println("Nuevo password asignado: "+new_pass);
    			
    			
    			//actualizamos contrase�a en sistema
    			
    			String update_pass_sql=""
    					+ " UPDATE `usuarios` "
    					+ " SET `usuarios`.`Usuarios_pass` = '"+new_pass+"' "
    					+ " WHERE"
    					+ " 	`usuarios`.`Usuarios_login` = '"+login+"' "
    					+ " ";
    			
    			
    			state.executeUpdate(update_pass_sql);
    			
    			//enviamos correo con contrase�a aleatoria de 6 digitos
    			
    			final String username = "sistema@newoffice.cl";
    			final String password = "sistema";
    			
    			Properties props = new Properties();
    			props.put("mail.smtp.auth", "true");
    			//props.put("mail.smtp.starttls.enable", "true");
    			props.put("mail.smtp.host", "mail.newoffice.cl");
    			props.put("mail.smtp.port", "25");
    			Session session = Session.getInstance(props,
    					  new javax.mail.Authenticator() {
    						protected PasswordAuthentication getPasswordAuthentication() {
    							return new PasswordAuthentication(username, password);
    						}
    					  });
    			
    	        String msgBody = "Estimado usuario, se ha generado una nueva contrase�a de acceso a la plataforma New Office. \nRecuerde cambiar la conse�a la pr�xima vez que acceda."
    	        		+ "\nUsuario: " + login.toUpperCase()
    	        		+ "\nContrase�a: " + new_pass ;

    	            Message msg = new MimeMessage(session);
    	            msg.setFrom(new InternetAddress("sistema@newoffice.cl", "NEW OFFICE"));
    	            msg.addRecipient(Message.RecipientType.TO,
    	                             new InternetAddress(""+usu_email, "Sr. Usuario"));
    	            msg.setSubject("NUEVA CONTRASE�A DE ACCESO A NEW OFFICE");
    	            msg.setText(msgBody.toUpperCase());
    	            Transport.send(msg);

    	            // ...
    	        
    			    String msge="SE HA ENVIADO UN CORREO ELECTR\u00d3NICO CON UNA NUEVA CONTRASE�A.";
        			RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg="+msge);
        	    	a.forward(request, response);
    			
    		}else{
    			
    			System.out.println("Usuario no registrado en sistema ");
    			String msg="USUARIO NO REGISTRADO EN SISTEMA";
    			RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg="+msg);
    	    	a.forward(request, response);
    		}

    		
    		
    		
    			// cerrando resultset
    			System.out.println("Cerrando consulta");
    			rs.close(); state.close();
			
    		conexion.close();
    		
    		
    		} catch(SQLException ex){ 
    			ex.printStackTrace();
    		} catch(Exception ex){ 
    			ex.printStackTrace();
    		}

    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("ingresar")!=null) validateUser(request,response);
		if(request.getParameter("rempass")!=null) rememberPass(request, response);
		
	}

}
