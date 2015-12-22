

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * Servlet implementation class Mcal
 */
@WebServlet("/Mcal")
public class Mcal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mcal() {
        super();
        // TODO Auto-generated constructor stub
    }

    
public boolean validateSession(HttpServletRequest request, HttpServletResponse response){
		
		boolean user_in_session=false;
		boolean username_in_session=false;
		Cookie [] cookies=request.getCookies();
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 System.out.println("cookie: "+cookie.getName()+"-"+cookie.getValue());
		    	if(cookie.getName().equals("Usuarios_id") && cookie.getValue()!=null) user_in_session=true;
		    	if(cookie.getName().equals("Usuarios_nombre") && cookie.getValue()!=null) username_in_session=true;
		    }
		}
		
		
		if(user_in_session && username_in_session) user_in_session=true;
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
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		String Usuarios_nombre="";
		String Perfil_id="";
		
		
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
				if(cookie.getName().equals("perfilusu_id")) Perfil_id=cookie.getValue();
			}
		}
		request.setAttribute("usuname", Usuarios_nombre);
		////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////
		
		//Aqui.. codigo del modulo
    
        //creamos un nuevo array con el calendario del mes
    
        //tama–o = 7 dias de semana * 6 semanas maximo = 42
    
        String[] cal = new String[42];
        //llenamos con un numero 0 para no llevarlo vacio
        for(int i =0; i<42; i++){
            cal[i]="0";
        }
    
        //por default traemos el mes y a–o actual para armar el calendario
    
    
    try {
        //import java.io.IOException;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion=(Connection) DriverManager.getConnection
        ("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
 
        
        String fec_form="NOW()";
        
        if(request.getParameter("mes") !=  null && request.getParameter("year")!=null){
            fec_form="'"+request.getParameter("year")+"-"+request.getParameter("mes")+"-01'";
        }
        
        
        
        
        
        
        //:::::::::::::::::::::::::: sql trae parametros :::::::::::::::::::::::::::::::::::::
        
        Statement state = (Statement) ((java.sql.Connection) conexion).createStatement();
        
        String SQL_FECNOW = "SELECT YEAR ("+fec_form+") AS YEAR, MONTH ("+fec_form+") AS MES, weekday(DATE_FORMAT("+fec_form+",'%Y-%m-01')) as weekday_first, DAY (LAST_DAY("+fec_form+")) AS lastd ";
        System.out.println(SQL_FECNOW);
    
        ResultSet FECNOW_RS =  state.executeQuery(SQL_FECNOW);
  
        String year_actual="";
        String mes_actual="";
        int ultimo_dia=0;
        int weekdar_first=0;
        while(FECNOW_RS.next()){
            year_actual=FECNOW_RS.getString("YEAR");
            mes_actual=FECNOW_RS.getString("MES");
            weekdar_first=FECNOW_RS.getInt("weekday_first");
            ultimo_dia=FECNOW_RS.getInt("lastd");
        }
        FECNOW_RS.close();
        state.close();
       
        request.setAttribute("year_actual", year_actual);
        request.setAttribute("mes_actual", mes_actual);
        
        int count=weekdar_first;
        for(int i =1; i<=ultimo_dia; i++){
            cal[count]="vacio/"+i;
            count++;
        }
        
        
        
        
        //:::::::::::::::::::::::::: ACCION PARA GRABAR:::::::::::::::::::::::::::::::::::::
        
        if(request.getParameter("grabar")!=  null){
            //primero borramos si es que ya tenia registros
            Statement state_up = (Statement) ((java.sql.Connection) conexion).createStatement();
            String SQL_UP = "UPDATE `cal` SET `cal`.`cal_estado` = 2, cal_accion_alertada=0, cal_ult_idper_exec=40 WHERE YEAR (`cal`.`cal_dia`) ="+year_actual+" AND MONTH (`cal`.`cal_dia`) ="+mes_actual+" AND `cal`.`cal_estado` = 1";
            System.out.println(SQL_UP);
            state_up.executeUpdate(SQL_UP);
            state_up.close();
          
            for(int i =1; i<42; i++){
                if(request.getParameter(""+i)!=null){
                    String data_dia=request.getParameter(""+i);
                    System.out.println(data_dia+" hora seleccionada real: "+request.getParameter(i+"_hi")+" - "+request.getParameter(i+"_hf"));
                    String[] data_dia_ar=data_dia.split("/");
                    if(data_dia_ar[0]!=null && data_dia_ar[0].equals("novacio")){
                        //insertamos registro
                        Statement state_in = (Statement) ((java.sql.Connection) conexion).createStatement();
                        
                        
                        String hora1=request.getParameter(i+"_hi");
                        String hora2=request.getParameter(i+"_hf");
                        
                        try{
                        if(hora1.length()==5)hora1=hora1+":00";
                        if(hora1.length()==5)hora2=hora2+":00";
                        }
                        catch(Exception e){}
                        if(hora1.length()==0)hora1="09:00:00";
                        if(hora2.length()==0)hora2="18:00:00";
                        String SQL_IN = "INSERT INTO `cal` (`cal`.`cal_dia`, `cal`.`cal_estado`, `cal`.`cal_horadesde`, `cal`.`cal_horahasta` )"
                        + " VALUES('"+year_actual+"-"+mes_actual+"-"+data_dia_ar[1]+"',1,'"+hora1+"','"+hora2+"')";
                        
                        System.out.println(SQL_IN);
                        
                        state_in.executeUpdate(SQL_IN);
                        
                        state_in.close();

                    }
                    
                }
                
                
            }
            
        }

        
        //buscamos en la tabla la data del mes en curso
    
        //:::::::::::::::::::::::::: sql trae calendario de dia actual:::::::::::::::::::::::::::::::::::::
        
        Statement state_cal = (Statement) ((java.sql.Connection) conexion).createStatement();
        
        String SQL_CAL = " "
        + "     SELECT "
        + "         day(`cal`.`cal_dia`) as cal_dia, "
        + "         `cal`.`cal_horadesde`, "
        + "         `cal`.`cal_horahasta` "
        + "     FROM "
        + "         `cal` "
        + "     WHERE "
        + "        YEAR (`cal`.`cal_dia`) = "+year_actual
        + "        AND MONTH (`cal`.`cal_dia`) =  "+mes_actual
        + "        AND `cal`.`cal_estado` = 1";
        
        System.out.println(SQL_CAL);
        
        ResultSet CALNOW_RS =  state_cal.executeQuery(SQL_CAL);
        
        int numrows=0;
        while(CALNOW_RS.next()){
            cal[(weekdar_first+CALNOW_RS.getInt("cal_dia"))-1]="novacio/"+CALNOW_RS.getString("cal_dia")+"/"+CALNOW_RS.getString("cal_horadesde")+"/"+CALNOW_RS.getString("cal_horahasta");
            
            numrows++;
        }
        CALNOW_RS.close();
        state_cal.close();

        if(numrows==0){
            //poblamos el mes y preguntamos de nuevo
        }
        
        request.setAttribute("cal_ar", cal);
    
		///////////////////////////////////////////////////////////
		RequestDispatcher rd = request.getRequestDispatcher("Mcal.jsp");
        rd.forward(request, response);
        
		}catch(Exception ex){
		    out.println("Error: "+ex);
		    ex.printStackTrace();
		}
		
	}

}
