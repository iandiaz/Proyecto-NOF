
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controlador {
	
	 public static boolean validateSession(HttpServletRequest request, HttpServletResponse response){
	    	
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

}
