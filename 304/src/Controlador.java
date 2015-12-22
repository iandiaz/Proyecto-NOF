import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controlador {
	
	 public static boolean validateSession(HttpServletRequest request, HttpServletResponse response){
	    	
	    	boolean user_in_session=false;
	    	boolean username_in_session=false;
	    	boolean perf_in_session=false;
	    	
	    	boolean login_in_session=false;
	    	boolean email_in_session=false;
	    	boolean tipousu_in_session=false;
	    	
	    	Cookie [] cookies=request.getCookies();
	    	
	    	if (cookies != null) {
	    	    for (Cookie cookie : cookies) {
	    	        //work with cookies
	    	    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
	    	    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
	    	    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
	    	    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) perf_in_session=true;
	    	    	
	    	    	if(cookie.getName().equals("Usuarios_login") && cookie.getValue()!=null && !cookie.getValue().equals("")) login_in_session=true;
	    	    	if(cookie.getName().equals("Usuarios_email") && cookie.getValue()!=null && !cookie.getValue().equals("")) email_in_session=true;
	    	    	if(cookie.getName().equals("tipo_usu_id") && cookie.getValue()!=null && !cookie.getValue().equals("")) tipousu_in_session=true;
	    	    	
	    	    }
	    	}
	    	
	    	
	    	if(user_in_session && username_in_session && perf_in_session && login_in_session && email_in_session && tipousu_in_session ) user_in_session=true;
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
	 
	 public static String getUsunameSession(HttpServletRequest request){
		 Calendar ahoraCal = Calendar.getInstance();
			String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
			String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
			
		 Cookie [] cookies=request.getCookies();
			String Usuarios_nombre=null;
		 if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 //System.out.println("cookie: "+cookie.getName());
			    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
			    	
			    }
			}		
		 return Usuarios_nombre;
	 }
	 public static String getUsuIDSession(HttpServletRequest request){
			
		 Cookie [] cookies=request.getCookies();
			String id_Usuario=null;
		 if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 //System.out.println("cookie: "+cookie.getName());
			    	
			    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) id_Usuario=cookie.getValue();
			    }
			}		
		 return id_Usuario;
	 }
	 
	 public static String getPerfilIDSession(HttpServletRequest request){
			
		 Cookie [] cookies=request.getCookies();
		 String Perfil_id=null;
		 if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        //work with cookies
			    	 //System.out.println("cookie: "+cookie.getName());
			    	
			    	if(cookie.getName().equals("perfilusu_id") && cookie.getValue()!=null) Perfil_id=cookie.getValue();
			    }
			}		
		 return Perfil_id;
	 }
	 
	
	 public static String verificaPermisoSession(String perfilusu_id,String permiso) {
		 String validate = "0"; 
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
	    		Connection conexion=(Connection) DriverManager.getConnection
	    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
						
				Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
				
				String permisos_sql="SELECT perfilusu_has_permisos.permisos_id "
						+ "	FROM perfilusu_has_permisos "
						+ "	WHERE perfilusu_has_permisos.estados_vig_novig_id=1 "
						+ "	AND perfilusu_has_permisos.perfilusu_id="+perfilusu_id+""
						+ " AND perfilusu_has_permisos.permisos_id="+permiso;
				System.out.println("SQL: "+permisos_sql);
				ResultSet rs_permisos = state.executeQuery(permisos_sql);
				
				
				while(rs_permisos.next()) {
					if(rs_permisos.getString("permisos_id").equals(permiso)){
						 validate="1";
						 break;
					 }
				}
			
				
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
			
		 
		 
		
		 return validate;
	 }
	 
	 public static void eraseCookie(HttpServletRequest request, HttpServletResponse response) {
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null)
		        for (int i = 0; i < cookies.length; i++) {
		            cookies[i].setValue("");
		            cookies[i].setPath("/");
		            cookies[i].setMaxAge(0);
		            response.addCookie(cookies[i]);
		        }
		}
	 
	 public static String getNameModulo(String n_modulo)  {
		 try{
		 Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		
    		
		 String SQL_PERFILES = "SELECT "
		    		+ " 	`modulos`.`Modulos_codigo`,"
		    		+ " 	`modulos`.`estados_vig_novig_id`,"
		    		+ " 	`modulos`.`Modulos_nombre` "
		    		+ " FROM"
		    		+ " 	`modulos`"
		    		+ " WHERE"
		    		+ " 	`modulos`.`Modulos_codigo` = "+n_modulo;
		    		
		    	
		    
		    System.out.println(SQL_PERFILES);
		    ResultSet MODULO_RS = state.executeQuery(SQL_PERFILES);
		    
		    if(MODULO_RS.next()) return MODULO_RS.getString("Modulos_nombre");
		    else return null;
		    
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
		}
	 
	 public static double distFrom(double lat1, double lng1, double lat2, double lng2) {  
		    //double earthRadius = 3958.75;//miles  
		    double earthRadius = 6371;//kilometers  
		    double dLat = Math.toRadians(lat2 - lat1);  
		    double dLng = Math.toRadians(lng2 - lng1);  
		    double sindLat = Math.sin(dLat / 2);  
		    double sindLng = Math.sin(dLng / 2);  
		    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
		            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));  
		    double dist = ( earthRadius * c); 
		    
		    long factor = (long) Math.pow(10, 2);
		    dist = dist * factor;
		    long tmp = Math.round(dist);
		    
		    return (double) tmp / factor;  
		}  
	 public static String getHrsMinutes(int minutes) {
		 	
		 int horas_ = minutes/60;
		 int minutos_= 	minutes-(horas_*60);
		 
		 String horas="";
		 String minutos="";
		 
		 
		 if(horas_<10) horas="0"+horas_;
		 else horas=horas_+"";
		 
		 if(minutos_<10) minutos="0"+minutos_;
		 else minutos=minutos_+"";
		 
		 
		   	return horas+":"+minutos;
		}  


}
