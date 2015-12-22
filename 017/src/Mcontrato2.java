

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
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
 * Servlet implementation class Mcontrato2
 */
@WebServlet("/Mcontrato2")
public class Mcontrato2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mcontrato2() {
        super();
        // TODO Auto-generated constructor stub
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
		
		String Usuarios_nombre="";
		
		//fecha 
		Calendar ahoraCal = Calendar.getInstance();
		String fecha=ahoraCal.get(Calendar.DATE)+"-"+(ahoraCal.get(Calendar.MONTH)+1)+"-"+ahoraCal.get(Calendar.YEAR);
		String hora=ahoraCal.get(Calendar.HOUR_OF_DAY)+":"+ahoraCal.get(Calendar.MINUTE)+":"+ahoraCal.get(Calendar.SECOND);
		String Usuarios_ID="";
		//System.out.print(Datenow);
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        //work with cookies
		    	 //System.out.println("cookie: "+cookie.getName());
		    	if(cookie.getName().equals("Usuarios_id")) { Usuarios_ID=cookie.getValue();}
		    	if(cookie.getName().equals("Usuarios_nombre")) Usuarios_nombre=cookie.getValue()+" "+fecha+" "+hora;
		    }
		}
		request.setAttribute("usuname", Usuarios_nombre);
		
		
		
		//grabar
		
		boolean existeclpr2 = false;
		
		
		

		
		try {
			 
			 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			 
		       
				if (isMultipart) {
					
					Statement state = null;
					Statement state_fac = null;
					Statement state_facdet = null;
					Statement state_ins = null;
					Statement state_des = null;
					
					//import java.io.IOException;
					Class.forName("com.mysql.jdbc.Driver");
		    		Connection conexion=(Connection) DriverManager.getConnection
		    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
		    		state = (Statement) ((java.sql.Connection) conexion).createStatement();
					Statement stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
					state_fac = (Statement) ((java.sql.Connection) conexion).createStatement();
					state_facdet = (Statement) ((java.sql.Connection) conexion).createStatement();
					state_ins = (Statement) ((java.sql.Connection) conexion).createStatement();
					state_des = (Statement) ((java.sql.Connection) conexion).createStatement();
					
					   //DATOS DE LA TABLA CONTRATO
					
					String contrato_nombre = "";
					String estado_contrato_id = "";
			    	String estados_vig_novig_id = "";
			    	String contrato_id="";
			    	String fech = null;
					String fecha_inicio =null;
					String fecha_termino = null;
					
					
						String fecrefar[]=null;
						
					
					String durac_inic=null;
			    	String plazo_renov=null;
			    	
					ArrayList<String> detalleseleccionado_emp= new ArrayList<String>();
					ArrayList<String> detalleseleccionado_tpcontrato= new ArrayList<String>();
					ArrayList<String> detalleseleccionado_secobra= new ArrayList<String>();
					
		        	try {
						
						 List<FileItem> multiparts = new ServletFileUpload(
                                new DiskFileItemFactory()).parseRequest( request);
						
		            	 for(FileItem item : multiparts){
							 
								 
			                    if(!item.isFormField()){
			                       
			                        
			                    }
			                    else{
			                    	
			                    	String key=item.getFieldName();
			                    	String valor =item.getString();
			                    	
			                    	
			                    	 if(key.equals("empresa[]"))detalleseleccionado_emp.add(valor);
			                    	 if(key.equals("tipocontrato[]"))detalleseleccionado_tpcontrato.add(valor);
			                    	 if(key.equals("se_cobra[]"))detalleseleccionado_secobra.add(valor);
					                    
			                    	  
			                    	
			                    	if(key.equals("contrato_nombre"))contrato_nombre = valor;
			  				      
			  				      	if(key.equals("estado_contrato_id"))estado_contrato_id = valor;
			  				      	
			  				      if(key.equals("estados_vig_novig_id"))estados_vig_novig_id = valor;
			  				    if(key.equals("contrato_id"))contrato_id = valor;
			  				    
			  				      
			  				      	if(key.equals("fecha")){
			  				    	  	fecrefar=valor.split("-");
			  				    	  	fech="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
			  				      	}
			  				      
			  				      

				  				      if(key.equals("fecha_inicio")){
				  				    	  	fecrefar=valor.split("-");
				  				    	  fecha_inicio="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
				  				      }
			  				      
			  				      

				  				      if(key.equals("fecha_termino")){
				  				    	  	fecrefar=valor.split("-");
				  				    	  fecha_termino="'"+fecrefar[2]+"-"+fecrefar[1]+"-"+fecrefar[0]+"'";
				  				      }
			  				      
			  				      
				  				      if(key.equals("durac_inic"))durac_inic = valor;
				  				      if(key.equals("plazo_renov"))plazo_renov = valor;
				  				      
			  				    }
			                }
						 
						 
		            	 
		            	 
		            	 
		            	 String sql_valgrupo2="SELECT * FROM contrato WHERE contrato_nombre='"+contrato_nombre.toUpperCase()+"' AND  contrato_id<>'"+contrato_id+"'";
		 	    		
		 	    		System.out.println("sql_valgrupo2: "+sql_valgrupo2);
		 	    		
		 	    		ResultSet rs_valclpr2 = state.executeQuery(sql_valgrupo2);
		 	    		
		 	    		System.out.println("OUT : "+sql_valgrupo2);
		 	    		
		 	    		if(rs_valclpr2.next())
		 	    		{
		 	    			
		 	    			state.close();
		 	    			rs_valclpr2.close();
		 	    			existeclpr2=true;   		
		 	    		}
		 	    		if(existeclpr2)
		 	    		{
		 	    			
		 	    			conexion.close();
		 	    			response.sendRedirect("Menucontratos?Exito=NOOK1");
	 			    		return;
		 	    		} 
		 	    		
		 	    		if(!existeclpr2)
		 	    		{
		 	    				
		 	    		   String SQL_UPDATE = ""
		 	  			   		+ "UPDATE contrato "
		 	  			   		+ "SET contrato_nombre='"+contrato_nombre.toUpperCase()+"', "
		 	  			   		+ "contrato_accion_alertada=0,"
		 	  			   		+ "contrato_ult_idper_exec=22,"
		 	  			   		+ "estados_vig_novig_id="+estados_vig_novig_id+", "
		 	  			   		+ "estadocontrato_id="+estado_contrato_id+", "
		 	  			   		+ "fecha="+fech+","
		 	  			   		+ "fecha_inicio="+fecha_inicio+","
		 	  			   		+ "fecha_termino="+fecha_termino+","
		 	  			   		+ "durac_inic="+durac_inic+","
		 	  			   		+ "plazo_renov="+plazo_renov+","
		 	  			   		
		 	  			   		+ "contrato_fecmod=now(), "
		 	  			   		+ "contrato_modificador="+Usuarios_ID+"  "
		 	  			   		
		 	  			   		+ "WHERE contrato_id="+contrato_id;
		 	  				    
		 	  				    System.out.println("UP: "+SQL_UPDATE); 
		 	  				    
		 	  					stategrabar.executeUpdate(SQL_UPDATE);
		 	  					
		 	  					stategrabar.close();
		 	  					
		 	  					
		 	  					
		 	  					stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement();
		 	  					
		 	  					String up_delete=""
		 	  	    					+ " UPDATE contrato_empresa SET "
		 	  	    					+ " estados_vig_novig_id=2 "
		 	  	    					+ " WHERE contrato_id="+contrato_id;
		 	  					
		 	  					System.out.println("up_delete: "+up_delete);
		 	  					
		 	      				stategrabar.executeUpdate(up_delete);
		 	      				
		 	      				stategrabar.close(); 
		 	      				
		 	      				stategrabar = (Statement) ((java.sql.Connection) conexion).createStatement(); 
		 	    		  

		 						String up_delet=""
		 		    					+ " UPDATE contrato_tipocontrato SET "
		 		    					+ " estados_vig_novig_id=2 "
		 		    					+ " WHERE contrato_id="+contrato_id;
		 						
		 						System.out.println("up_delete: "+up_delet);
		 						
		 	    				stategrabar.executeUpdate(up_delet);
		 	    			
		 			    		
		 	      				
		 	    		  
		 	    		  String[] seleccionado_emp_ar = new String[detalleseleccionado_emp.size()];
						    for(int x=0; x < detalleseleccionado_emp.size(); x++){ seleccionado_emp_ar[x]=detalleseleccionado_emp.get(x);}
		 	    			if(seleccionado_emp_ar!=null) 
		 		    		for (String id_per: seleccionado_emp_ar) 
		 		    		{
		 		    			if(id_per!=null && !id_per.equals("-1"))
		 		    			{
		 		    				String insertusuemp=""
		 			    					+ " INSERT INTO `contrato_empresa` ("
		 			    					+ " 	`contrato_empresa`.`contrato_id`,"
		 			    					+ " 	`contrato_empresa`.`empresas_id` "
		 			    					+ " ) "
		 			    					+ " VALUES"
		 			    					+ " 	('"+contrato_id+"', '"+id_per+"')";
		 			    			
		 		    				stategrabar.executeUpdate(insertusuemp);
		 			    			
		 		    			}	    			
		 		    		}
		 		    		
		 		    		//insertar contrato- tipocontrato
		 		    		
		 		    		
		 		    		
		 		    		  String[] seleccionado_tipo_contrato_ar = new String[detalleseleccionado_tpcontrato.size()];
							    for(int x=0; x < detalleseleccionado_tpcontrato.size(); x++){ seleccionado_tipo_contrato_ar[x]=detalleseleccionado_tpcontrato.get(x);}
			 	    		
		 			    		if(seleccionado_tipo_contrato_ar!=null) 
		 			    			for (String id_pe: seleccionado_tipo_contrato_ar) 
		 			    		{
		 			    			if(id_pe!=null && !id_pe.equals("-1"))
		 			    			{
		 			    				String insertusuem=""
		 				    					+ " INSERT INTO `contrato_tipocontrato` ("
		 				    					+ " 	`contrato_tipocontrato`.`contrato_id`,"
		 				    					+ " 	`contrato_tipocontrato`.`tipo_contrato_id` "
		 				    					+ " ) "
		 				    					+ " VALUES"
		 				    					+ " 	('"+contrato_id+"', "+id_pe+")";
		 				    			
		 			    				stategrabar.executeUpdate(insertusuem);
		 				    			
		 			    			}	    			
		 			    		}
		 			    		
		 			    		  String[] seleccionado_secobra_ar = new String[detalleseleccionado_secobra.size()];
								    for(int x=0; x < detalleseleccionado_secobra.size(); x++){ seleccionado_secobra_ar[x]=detalleseleccionado_secobra.get(x);}
				 	    		
		 			    		
		 			    			if(seleccionado_secobra_ar!=null) 
		 				    		for (String id_per: seleccionado_secobra_ar) 
		 				    		{
		 				    			if(id_per!=null && !id_per.equals("-1"))
		 				    			{
		 				    				String insersecobra=""
		 					    					+ " UPDATE `contrato_tipocontrato` SET"
		 					    					+ " 	se_cobra=1"
		 					    					+ "	WHERE "
		 					    					+ "		contrato_id="+contrato_id+" AND tipo_contrato_id="+id_per;
		 				    				
		 				    				stategrabar.executeUpdate(insersecobra);
		 					    			System.out.println(""+insersecobra);
		 				    			}	    			
		 				    		}
		 				    
		 			    		
								 
								   
								    
								    String file1="";
								    
								    for(FileItem item : multiparts){
										
									
											 
						                    if(!item.isFormField()){
						                        String name = new File(item.getName()).getName();
						                     
												
						                        String[] name_part=name.split("\\.");
						                        
						                        
						                        if(name!=null && !name.equals("")){
						                        	item.write( new File(Constantes.DIR_DOCS + File.separator + contrato_id+"_"+item.getFieldName()+"."+name_part[name_part.length-1]));
								                       
							                        if(item.getFieldName().equals("file1"))file1=contrato_id+"_"+item.getFieldName()+"."+name_part[name_part.length-1];
							                      
							                    }	
						                        }
						              }
								    
								    if(!file1.equals("")){
								    	 String SQL_FACf = "update `contrato` set "
													+ "	contrato_file='"+file1+"'"
													
													+ " WHERE contrato_id="+contrato_id;
											
											System.out.println(SQL_FACf);
											state.executeUpdate(SQL_FACf);
								    }
								   
									
									
									
									  //insertamos logs
						    		String log_sql=""
						    				+ " INSERT INTO `log` ("
						    				+ "	`log`.`log_fec`, "
						    				+ "	`log`.`log_nombre`, " 
						    				+ "	`log`.`Usuarios_id` "
						    				+ " ) "
						    				+ " VALUES "
						    				+ " (now(), 'USUARIO MODIFICA REGISTRO "+contrato_id+" EN TABLA contrato', '"+Usuarios_ID+"') ";
						    		state.executeUpdate(log_sql);
						    		
					        	stategrabar.close(); 
		 			    		conexion.close();
		 			    		
		 			    		response.sendRedirect("Menucontratos?Exito=OK");
		 			    		return;
		 	    		}
		 	    		 
		            	 	return ;
						 
		                ///FIN GRABAR 
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
		
		Statement state2 = null;
		ResultSet USUARIOS_RS = null;
		Statement state = null;
		ResultSet EMPRESAS_RS = null;
		ResultSet EMPRESAS_R = null;
		ResultSet TIPO_USU_RS = null;
		ResultSet PERFILUSU_RS = null;
		ResultSet PERUSUEMP_RS = null;
		ResultSet PERUSUEMP_R = null;
		
		try 
		{
			//import java.io.IOException;
			Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion=(Connection) DriverManager.getConnection
    	    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
    		 state2 = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 state = (Statement) ((java.sql.Connection) conexion).createStatement();
    		 
    		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		 
    		 String SQL_PERUSUEMP = "SELECT * FROM contrato_empresa WHERE estados_vig_novig_id=1 AND contrato_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
    		
  		    System.out.println("SQL_PERUSUEMP: "+SQL_PERUSUEMP);
  		  
  		    PERUSUEMP_RS =  state.executeQuery(SQL_PERUSUEMP);
  		    
  		    //definimos un arreglo para los resultados
  		    
  		    ArrayList<String> usuariosempresas = new ArrayList<String>();
  		    
  		    //recorremos los resultados de la consulta
  		  
  		    while(PERUSUEMP_RS.next())
  		    {        	   
          	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
  		    	
  		    	usuariosempresas.add(PERUSUEMP_RS.getString("empresas_id"));
  		    	
      	    }
  		    
  		  //System.out.println("SIZE LIST: "+usuariosempresas.size());	
  		    
  		  PERUSUEMP_RS.close();
  		  
  		  state.close();
  		  
  		  String[] ar_usuariosempresas = new String[usuariosempresas.size()];
  		  
  		  
            for(int x=0; x < usuariosempresas.size(); x++)
            {
            	ar_usuariosempresas[x]=usuariosempresas.get(x);
            }
                  
            request.setAttribute("ar_usuariosempresas", ar_usuariosempresas);
            
  		//::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
    		 
    		//:::::::::::::::::::::::::: sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
            
            
            state = (Statement) ((java.sql.Connection) conexion).createStatement();
            
 		    String SQL_EMPRESAS = "SELECT * FROM empresas ORDER BY empresas_id";
 		    
 		    System.out.println(SQL_EMPRESAS);
 		    
 		    EMPRESAS_RS =  state.executeQuery(SQL_EMPRESAS);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> empresas = new ArrayList<String>();
 		    
 		    
 		    //recorremos los resultados de la consulta
 		    
 		    while(EMPRESAS_RS.next())
 		    {        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	empresas.add(EMPRESAS_RS.getString("empresas_id")+"/"+EMPRESAS_RS.getString("empresas_nombrenof"));
 		    	
 		    	
     	    }
 		  
 		    //System.out.println("SIZE LIST: "+empresas.size());	
 		  
 		    EMPRESAS_RS.close();
 		     		 
 		    state.close();
 		    
 		    String[] ar_empresas = new String[empresas.size()];
 		    
 		    
           for(int x=0; x < empresas.size(); x++)
           {
         	 ar_empresas[x]=empresas.get(x);
           }
                 
           request.setAttribute("ar_empresas", ar_empresas);
           
 		//::::::::::::::::::::::::::fin sql trae estados vig no vig para select option:::::::::::::::::::::::::::::::::::::
 		  
           // tipo contrato
           
           
           state = (Statement) ((java.sql.Connection) conexion).createStatement();
  		 
   		//:::::::::::::::::::::::::: SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
   		 String SQL_PERUSUEM = "SELECT * FROM contrato_tipocontrato WHERE estados_vig_novig_id=1 AND contrato_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
   		
 		    System.out.println("SQL_PERUSUEMP: "+SQL_PERUSUEM);
 		  
 		    PERUSUEMP_R =  state.executeQuery(SQL_PERUSUEM);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> usuariosempresa = new ArrayList<String>();
 		    
 		    //recorremos los resultados de la consulta
 		  
 		    while(PERUSUEMP_R.next())
 		    {        	   
         	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	
 		    	usuariosempresa.add(PERUSUEMP_R.getString("tipo_contrato_id")+";"+PERUSUEMP_R.getString("se_cobra"));
 		    	
     	    }
 		    
 		  //System.out.println("SIZE LIST: "+usuariosempresas.size());	
 		    
 		  PERUSUEMP_R.close();
 		  
 		  state.close();
 		  
 		  String[] ar_usuariosempresa = new String[usuariosempresa.size()];
 		  
 		  
           for(int x=0; x < usuariosempresa.size(); x++)
           {
           	ar_usuariosempresa[x]=usuariosempresa.get(x);
          
           }
                 
           request.setAttribute("ar_usuariosempresa", ar_usuariosempresa);
           
 		//::::::::::::::::::::::::::SQL TRAE PERMISOS QUE TIENE EL USU EMP:::::::::::::::::::::::::::::::::::::
           
           
           state = (Statement) ((java.sql.Connection) conexion).createStatement();
           
		    String SQL_EMPRESA = "SELECT * FROM tipo_contrato ORDER BY tipo_contrato_id";
		    
		    System.out.println(SQL_EMPRESA);
		    
		    EMPRESAS_R =  state.executeQuery(SQL_EMPRESA);
		    
		    //definimos un arreglo para los resultados
		    
		    ArrayList<String> empresa = new ArrayList<String>();
		    
		    
		    //recorremos los resultados de la consulta
		    
		    while(EMPRESAS_R.next())
		    {        	   
        	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
		    	empresa.add(EMPRESAS_R.getString("tipo_contrato_id")+"/"+EMPRESAS_R.getString("tipo_contrato_nombre"));
		    	
		    	
		    	
    	    }
		  
		   
		    EMPRESAS_R.close();
		     		 
		    state.close();
		    
		    String[] ar_empresa = new String[empresa.size()];
		    
		   
		    
          for(int x=0; x < empresa.size(); x++)
          {
        	 ar_empresa[x]=empresa.get(x);
        	 
        	
        	 
          }
                
          request.setAttribute("ar_empresa", ar_empresa);
           
           // fin tipo contrato
           
           
 		  //::::::::::::::::::::::::::::::::::::::::::sql tipo_usu para select option::::::::::::::::::::::::::::::::::::::
 		  
           state = (Statement) ((java.sql.Connection) conexion).createStatement();
           
 		  String SQL_tipo_usu = "SELECT estadocontrato_id,estadocontrato_nombre FROM estadocontrato";
 		  
 		 System.out.println(SQL_tipo_usu);
 		 
 		    TIPO_USU_RS =  state.executeQuery(SQL_tipo_usu);
 		    
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> tipo_usu = new ArrayList<String>();
 		    
 		    //recorremos los resultados de la consulta
 		    
 		    while(TIPO_USU_RS.next())
 		    {        	   
       	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	
 		    	tipo_usu.add(TIPO_USU_RS.getString("estadocontrato_id")+"/"+TIPO_USU_RS.getString("estadocontrato_nombre"));
 		    	
       	    }
 		    
 		    TIPO_USU_RS.close();
 		  state.close();
 		  
 		  String[] ar_tipo_usu = new String[tipo_usu.size()];
 		  
           for(int x=0; x < tipo_usu.size(); x++)
           {
        	   
         	  ar_tipo_usu[x]=tipo_usu.get(x);
           }
                 
           request.setAttribute("ar_tipo_usu", ar_tipo_usu);
           
 		//::::::::::::::::::::::::::::::::::::::::fin sql tipo_usu para select option::::::::::::::::::::::::::::::::::::::
 		  
 		//::::::::::::::::::::::::::::::::::::::::::sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
 		  
           state = (Statement) ((java.sql.Connection) conexion).createStatement();
           
 		  String SQL_perfilusu = "SELECT perfilusu_id,perfilusu_nombre FROM perfilusu WHERE estados_vig_novig_id=1";
 		  
 		  System.out.println(SQL_perfilusu);
 		  
 		    PERFILUSU_RS =  state.executeQuery(SQL_perfilusu);
 		    
 		    //definimos un arreglo para los resultados
 		    
 		    ArrayList<String> perfilusu = new ArrayList<String>();
 		    //recorremos los resultados de la consulta
 		    
 		    while(PERFILUSU_RS.next())
 		    {
 		    	
     	    //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
 		    	perfilusu.add(PERFILUSU_RS.getString("perfilusu_id")+"/"+PERFILUSU_RS.getString("perfilusu_nombre").replace("/", " "));
     	    }
 		    
 		  //System.out.println("SIZE LIST: "+perfilusu.size());
 		    
 		  PERFILUSU_RS.close();
 		  
 		  	String[] ar_perfilusu = new String[perfilusu.size()];
 		  	
 		   for(int x=0; x < perfilusu.size(); x++)
           {
         	  ar_perfilusu[x]=perfilusu.get(x);
           }
            
           request.setAttribute("ar_perfilusu", ar_perfilusu);
           
 		//::::::::::::::::::::::::::::::::::::::::::fin sql estado clpr para select option::::::::::::::::::::::::::::::::::::::  
 		  
           state.close();

           String SQL_Usuarios = "SELECT "
        		   	+ "u.contrato_id, " 		    		
		    		+ " u.contrato_nombre, "
		    		+ " es.estadocontrato_id, "
		    		+ " e.estados_vig_novig_id, "
		    		+ " DATE_FORMAT(u.fecha,'%d-%m-%Y') as fecha,"
		    		+ " DATE_FORMAT(u.fecha_inicio,'%d-%m-%Y') as fecha_inicio,"
		    		+ " DATE_FORMAT(u.fecha_termino,'%d-%m-%Y') as fecha_termino,"
		    		+ " u.durac_inic,"
		    		+ " u.plazo_renov,"
		    		+ " CONCAT_WS(' ',us.Usuarios_nombre,us.Usuarios_ape_p) as nombre,"
		    		+ " DATE_FORMAT(u.contrato_feccreacion,'%d-%m-%Y %H:%i:%s') AS fec_crea,"
		    		+ " IF ( u.contrato_fecmod IS NULL,' ',DATE_FORMAT(u.contrato_fecmod,'%d-%m-%Y %H:%i:%s')) AS contrato_fecmod,"
		    		+ " IF (u.contrato_modificador IS NULL,' ',CONCAT_WS(' ', u2.Usuarios_nombre,u2.Usuarios_ape_p)) AS contrato_modificador"
		    		+ " FROM contrato u"
		    		+ " INNER JOIN estadocontrato es ON u.estadocontrato_id = es.estadocontrato_id"
		    		+ " INNER JOIN estados_vig_novig e ON u.estados_vig_novig_id = e.estados_vig_novig_id"
		    		+ " INNER JOIN usuarios us ON u.contrato_creador = us.Usuarios_id"
		    		+ " LEFT JOIN usuarios u2 ON u.contrato_modificador = u2.Usuarios_id"
		    		+ " WHERE u.contrato_id="+Integer.parseInt(request.getParameter("Usuarios_id"));
		    
		    System.out.println("SQL_Usuarios: "+SQL_Usuarios);
		    
		    USUARIOS_RS =  state2.executeQuery(SQL_Usuarios);
		    if(USUARIOS_RS.next())
		    {   	
		    	request.setAttribute("correlativo",USUARIOS_RS.getString("contrato_id")+"");
		    	request.setAttribute("contrato_nombre",USUARIOS_RS.getString("contrato_nombre"));
		    	request.setAttribute("estados_vig_novig_id1",USUARIOS_RS.getString("estados_vig_novig_id"));
		    	request.setAttribute("fecha",USUARIOS_RS.getString("fecha"));
		    	request.setAttribute("fecha_inicio",USUARIOS_RS.getString("fecha_inicio"));
		    	request.setAttribute("fecha_termino",USUARIOS_RS.getString("fecha_termino"));
		    	request.setAttribute("durac_inic",USUARIOS_RS.getString("durac_inic"));
		    	request.setAttribute("plazo_renov",USUARIOS_RS.getString("plazo_renov"));
		    	request.setAttribute("Usuarios_email",USUARIOS_RS.getString("nombre"));
		    	request.setAttribute("tipo_usu_id1",USUARIOS_RS.getString("estadocontrato_id"));
		    	String fec_crea=USUARIOS_RS.getString("fec_crea");
		    	String user_crea=USUARIOS_RS.getString("contrato_fecmod");
		    	String fec_mod=USUARIOS_RS.getString("contrato_modificador");
		    	request.setAttribute("fec_crea", (fec_crea)+"");
		        request.setAttribute("fec_mod", (fec_mod)+"");
	            request.setAttribute("user_crea", (user_crea)+"");
	        
    	    }
		    
		    USUARIOS_RS.close();
		    
		    state2.close();	    
		    
		   
		}
		catch(Exception ex)
		{
            ex.printStackTrace();
        }
		
		
		
		String msg="";
		
		if(existeclpr2)
		{
			msg="?Exito=NOK2";
			System.out.println(msg);
		}
		
		if(existeclpr2)
		{
			String Usuarios_name = request.getParameter("Usuarios_nombre");
			String Usuarios_ape_p = request.getParameter("Usuarios_ape_p");
			String Usuarios_ape_m = request.getParameter("Usuarios_ape_m");
			String Usuarios_login = request.getParameter("Usuarios_login");
			String Usuarios_pass = request.getParameter("Usuarios_pass");
			String Usuarios_email = request.getParameter("Usuarios_email");
			String Usuarios_telefono = request.getParameter("Usuarios_telefono");
			String tipo_usu_id = request.getParameter("tipo_usu_id");
			String perfilusu_id = request.getParameter("perfilusu_id");
			String estados_vig_novig_id = request.getParameter("estados_vig_novig_id");
	    	String empresas_id = request.getParameter("empresas_id");
	    	
	    	ArrayList<String> miusuemp = new ArrayList<String>();
	    	
	    	 String[] seleccionado = request.getParameterValues("permisos[]");
	    		if(seleccionado!=null)for (String id_per: seleccionado)
	    		{
	    			if(id_per!=null && !id_per.equals("-1"))
	    			{
	    				miusuemp.add(id_per);
	    			}
	    		}
	    	String[] ar_miusuemp = new String[miusuemp.size()];
	   		
	        for(int x=0; x < miusuemp.size(); x++)
	        {
	        	ar_miusuemp[x]=miusuemp.get(x);
	        }
	                   
	        request.setAttribute("ar_usuariosempresas", ar_miusuemp);
	        request.setAttribute("Usuarios_nombre",Usuarios_name);
	    	request.setAttribute("Usuarios_ape_p",Usuarios_ape_p);
	    	request.setAttribute("Usuarios_ape_m",Usuarios_ape_m);
	    	request.setAttribute("Usuarios_login",Usuarios_login);
	    	request.setAttribute("Usuarios_pass",Usuarios_pass);
	    	request.setAttribute("Usuarios_email",Usuarios_email);
	    	request.setAttribute("Usuarios_telefono",Usuarios_telefono);
	    	request.setAttribute("tipo_usu_id",tipo_usu_id);
	    	request.setAttribute("perfilusu_id",perfilusu_id);
	    	request.setAttribute("estados_vig_novig_id",estados_vig_novig_id);
	    	request.setAttribute("empresas_id",empresas_id);
	    	
		}
		
		System.out.println("msg: "+msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("Mcontrato2.jsp"+msg);
		
		
        rd.forward(request, response);
		
	}

}
