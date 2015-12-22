
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

import Constantes.ConstantesBIRT;
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
        String usuario=Controlador.getUsunameSession(request);
        
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("1") ){
			PrintWriter out = response.getWriter();
			String filtros="a=1";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_5_xls.php?"+filtros+"';setInterval(\"close()\",3600000);</script>");
		}
		if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("2")){
            PrintWriter out = response.getWriter();
			String filtros="";
            
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            
            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_2_xls.php?inf=996-2&usu="+usuario+filtros+"';</script>");
        }
        
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("3")){
				PrintWriter out = response.getWriter();
				String filtros="";
				if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
	            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
					filtros+="&id_d="+request.getParameter("select_direccion")+"";
					
				}
	            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
					filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
					
				}
	            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
					filtros+="&id_tub="+request.getParameter("select_tubi")+"";
					
				}
	            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
					filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
					
				}
	            
	            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
					filtros+="&id_e="+request.getParameter("select_estado")+"";
					
				}
	            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
					filtros+="&id_p="+request.getParameter("select_prod")+"";
					
				}
				
				out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_7_xls.php?inf=996-3&usu="+usuario+filtros+"';</script>");
			}
	        
	   
		
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("4")){
			 PrintWriter out = response.getWriter();
				String filtros="";
	            
	            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
	            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
					filtros+="&id_d="+request.getParameter("select_direccion")+"";
					
				}
	            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
					filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
					
				}
	            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
					filtros+="&id_tub="+request.getParameter("select_tubi")+"";
					
				}
	            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
					filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
					
				}
	            
	            
				if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
					filtros+="&id_e="+request.getParameter("select_estado")+"";
					
				}
	            
	            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
					filtros+="&id_f1="+request.getParameter("f1")+"";
					
				}
	            
	            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
					filtros+="&id_f2="+request.getParameter("f2")+"";
					
				}
	            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
					filtros+="&id_p="+request.getParameter("select_prod")+"";
					
				}
	            
	            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_5f2_xls.php?inf=996-4&usu="+usuario+filtros+"';</script>");
			 
		 }
		 
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("5") ){
				PrintWriter out = response.getWriter();
				String filtros="a=1";
				if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
	            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
					filtros+="&id_d="+request.getParameter("select_direccion")+"";
					
				}
	            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
					filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
					
				}
	            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
					filtros+="&id_tub="+request.getParameter("select_tubi")+"";
					
				}
	            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
					filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
					
				}
	            
	            
	            
				if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
					filtros+="&id_e="+request.getParameter("select_estado")+"";
					
				}
				
				
				//out.write("<script>window.open(\"www.google.com\");</script>");
				
				out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_5_xls.php?"+filtros+"';setInterval(\"close()\",3600000);</script>");
			}
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("6") ){
				PrintWriter out = response.getWriter();
				String filtros="a=1";
				
				if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
	            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
					filtros+="&id_d="+request.getParameter("select_direccion")+"";
					
				}
	            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
					filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
					
				}
	            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
					filtros+="&id_tub="+request.getParameter("select_tubi")+"";
					
				}
	            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
					filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
					
				}
	            
	            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
					filtros+="&id_e="+request.getParameter("select_estado")+"";
					
				}
	            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
					filtros+="&id_p="+request.getParameter("select_prod")+"";
					
				}
	            
	            if(request.getParameter("select_unou") !=  null && !request.getParameter("select_unou").equals("") ){
					filtros+="&id_unou="+request.getParameter("select_unou")+"";
					
				}
	            
				
				//out.write("<script>window.open(\"www.google.com\");</script>");
				
				out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_6_xls.php?"+filtros+"';</script>");
			}
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("7") ){
				PrintWriter out = response.getWriter();
				String filtros="a=1&id_u="+Controlador.getUsuIDSession(request)+"";
				if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
	            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
					filtros+="&id_d="+request.getParameter("select_direccion")+"";
					
				}
	            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
					filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
					
				}
	            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
					filtros+="&id_tub="+request.getParameter("select_tubi")+"";
					
				}
	            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
					filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
					
				}
	            
	            
	            
				if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
					filtros+="&id_e="+request.getParameter("select_estado")+"";
					
				}
				
				
				out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_7_xls.php?"+filtros+"';</script>");
			}
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("8")){
	            PrintWriter out = response.getWriter();
	            String filtros="a=1&id_u="+Controlador.getUsuIDSession(request)+"";
				
	            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
	            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
					filtros+="&id_d="+request.getParameter("select_direccion")+"";
					
				}
	            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
					filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
					
				}
	            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
					filtros+="&id_tub="+request.getParameter("select_tubi")+"";
					
				}
	            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
					filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
					
				}
	            
	            
				if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
					filtros+="&id_e="+request.getParameter("select_estado")+"";
					
				}
	            
	            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
					filtros+="&id_f1="+request.getParameter("f1")+"";
					
				}
	            
	            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
					filtros+="&id_f2="+request.getParameter("f2")+"";
					
				}
	            
	            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_8_xls.php?inf=996-8&usu="+usuario+filtros+"';</script>");
	        }
		 if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("9")){
	            PrintWriter out = response.getWriter();
	            String filtros="a=1&id_u="+Controlador.getUsuIDSession(request)+"";
				
	            if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
				if(request.getParameter("select_periodo") !=  null && !request.getParameter("select_periodo").equals("") ){
					filtros+="&id_per="+request.getParameter("select_periodo")+"";
					
				}
				
	           
	            
	            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_9_xls.php?inf=996-9&usu="+usuario+filtros+"';</script>");
	        }
            if(request.getParameter("grabar_excel") !=  null && request.getParameter("informe").equals("11")){
	            PrintWriter out = response.getWriter();
	            String filtros="a=1&id_u="+Controlador.getUsuIDSession(request)+"";
				
	            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
					filtros+="&id_g="+request.getParameter("select_grupo")+"";
					
				}
				if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
					filtros+="&id_em="+request.getParameter("select_empresa")+"";
					
				}
                if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				    filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
                }
            
                if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				    filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
                }
                if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
                    filtros+="&id_p="+request.getParameter("select_prod")+"";

                }
                if(request.getParameter("select_unou") !=  null && !request.getParameter("select_unou").equals("") ){
				    filtros+="&id_unou="+request.getParameter("select_unou")+"";
				
                }
            
	            
	           
	            
	            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_11_xls.php?inf=996-11&usu="+usuario+filtros+"';</script>");
	        }
	        
        
        /////////////////////GENERAR POR PDF ////////////////////////
        
		if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("1")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-1&usu="+usuario+filtros+"';</script>");
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
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            
            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-2&usu="+usuario+filtros+"';</script>");
        }
        
        
        
        if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("3")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
				filtros+="&id_p="+request.getParameter("select_prod")+"";
				
			}
			
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-3&usu="+usuario+filtros+"';</script>");
		}
        
        if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("5")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-5&usu="+usuario+filtros+"';</script>");
		}
        
        
        if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("6")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
				filtros+="&id_p="+request.getParameter("select_prod")+"";
				
			}
            
            if(request.getParameter("select_unou") !=  null && !request.getParameter("select_unou").equals("") ){
				filtros+="&id_unou="+request.getParameter("select_unou")+"";
				
			}
            
        	
			
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-6&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("7")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-7&id_u="+Controlador.getUsuIDSession(request)+"&usu="+usuario+filtros+"';</script>");
		}
        
        if(request.getParameter("grabar_pdf") !=  null && request.getParameter("informe").equals("8")){
            PrintWriter out = response.getWriter();
			String filtros="";
            
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            
            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/generaPDF.php?inf=996-8&id_u="+Controlador.getUsuIDSession(request)+"&usu="+usuario+filtros+"';</script>");
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
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_5_pdf.php?inf=996-1&usu="+usuario+filtros+"';</script>");
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
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            
            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_2_pdf.php?inf=996-2&usu="+usuario+filtros+"';</script>");
        }
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("3")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
				filtros+="&id_p="+request.getParameter("select_prod")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_7_pdf.php?inf=996-3&usu="+usuario+filtros+"';</script>");
		}
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("4")){
            PrintWriter out = response.getWriter();
			String filtros="";
            
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
				filtros+="&id_p="+request.getParameter("select_prod")+"";
				
			}
            
            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_5f2_pdf.php?inf=996-4&usu="+usuario+filtros+"';</script>");
        }
        
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("5")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_5_pdf.php?inf=996-5&usu="+usuario+filtros+"';</script>");
		}
        
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("6")){
        	PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
				filtros+="&id_p="+request.getParameter("select_prod")+"";
				
			}
            
            if(request.getParameter("select_unou") !=  null && !request.getParameter("select_unou").equals("") ){
				filtros+="&id_unou="+request.getParameter("select_unou")+"";
				
			}
            
        	
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_6_pdf.php?inf=996-6&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("7")){
			PrintWriter out = response.getWriter();
			String filtros="";
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
			
			out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_7_pdf.php?inf=996-7&id_u="+Controlador.getUsuIDSession(request)+"&usu="+usuario+filtros+"';</script>");
		}
        if(request.getParameter("grabar_web") !=  null && request.getParameter("informe").equals("8")){
            PrintWriter out = response.getWriter();
			String filtros="";
            
            if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				filtros+="&id_g="+request.getParameter("select_grupo")+"";
				
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				filtros+="&id_em="+request.getParameter("select_empresa")+"";
				
			}
            if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				filtros+="&id_d="+request.getParameter("select_direccion")+"";
				
			}
            if(request.getParameter("select_ubicacion") !=  null && !request.getParameter("select_ubicacion").equals("") ){
				filtros+="&id_ub="+request.getParameter("select_ubicacion")+"";
				
			}
            if(request.getParameter("select_tubi") !=  null && !request.getParameter("select_tubi").equals("") ){
				filtros+="&id_tub="+request.getParameter("select_tubi")+"";
				
			}
            if(request.getParameter("select_funcionalidad") !=  null && !request.getParameter("select_funcionalidad").equals("") ){
				filtros+="&id_fu="+request.getParameter("select_funcionalidad")+"";
				
			}
            
            
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				filtros+="&id_e="+request.getParameter("select_estado")+"";
				
			}
            
            if(request.getParameter("f1") !=  null && !request.getParameter("f1").equals("") ){
				filtros+="&id_f1="+request.getParameter("f1")+"";
				
			}
            
            if(request.getParameter("f2") !=  null && !request.getParameter("f2").equals("") ){
				filtros+="&id_f2="+request.getParameter("f2")+"";
				
			}
            
            out.write("<script>window.location='http://"+request.getServerName()+":81/NOF/rpt_996_8_pdf.php?inf=996-8&id_u="+Controlador.getUsuIDSession(request)+"&usu="+usuario+filtros+"';</script>");
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
		
		//verificamos permisos 36 60 61 87
		
		String p36=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "36");
		String p60=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "60");
		String p61=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "61");
		String p87=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "87");
		String p185=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "185");
		String p186=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "186");
		String p191=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "191");
		String p192=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "192");
		
		String p229=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "229");
		
		String p242=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "242");
		String p243=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "243");
		String p247=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), "247");
		
		
		
		request.setAttribute("p36", p36);
		request.setAttribute("p60", p60);
		request.setAttribute("p61", p61);
		request.setAttribute("p87", p87);
		request.setAttribute("p185", p185);
		request.setAttribute("p186", p186);
		request.setAttribute("p191", p191);
		request.setAttribute("p192", p192);
		
		request.setAttribute("p229", p229);
		
		request.setAttribute("p242", p242);
		request.setAttribute("p243", p243);
		request.setAttribute("p247", p247);
		
		
		
		
		//////////////////////////////////////////////////
		
		Statement state = null;
		ResultSet DIRECCION_RS=null;
		ResultSet UBI_RS=null;
		ResultSet FUNCIONALIDAD_RS=null;
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO2,Constantes.USER,Constantes.PASS);
			Connection conexion=(Connection) DriverManager.getConnection("jdbc:sqlserver://"+ConstantesBIRT.SERVER+";databaseName="+ConstantesBIRT.DBNAME+";user="+ConstantesBIRT.USER+";password="+ConstantesBIRT.PASS+";");
			
			state = (Statement) ((java.sql.Connection) conexion).createStatement();
		
				 //::::::::::::::::::::::::::::::::::::::::::sql ubicaciones para select option::::::::::::::::::::::::::::::::::::::
				  state = (Statement) ((java.sql.Connection) conexion).createStatement();
				  String SQL_UBI = "SELECT * FROM ubicacion where UBICACION.UBI_ESTADO='VIGENTE' ORDER BY UBI_DESCRIPCION ";
				  System.out.println(SQL_UBI);
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
			  System.out.println(SQL_FUNCIONALIDAD);
			    
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
            String SQL_PROD = "SELECT [PRODUCTO].[PROD_ID],[PRODUCTO].[PROD_DESC_CORTO],[PRODUCTO].[PROD_PN_TLI_CODFAB],[PRODUCTO].[FUNC_ID] FROM [PRODUCTO] WHERE [PRODUCTO].[PROD_ESTADO]='VIGENTE' ORDER BY [PRODUCTO].[PROD_COD_BAR_FAB] ";
            System.out.println(SQL_PROD);
            ResultSet PRODUCTOS_RS =  state.executeQuery(SQL_PROD);
            //definimos un arreglo para los resultados
            ArrayList<String> productos  = new ArrayList<String>();
            //recorremos los resultados de la consulta
            while(PRODUCTOS_RS.next()){
                //SI HAY ESTADOS, ENTONCES GUARDAMOS LOS ESTADOS
                productos.add(PRODUCTOS_RS.getString("PROD_ID")+";;"+PRODUCTOS_RS.getString("PROD_PN_TLI_CODFAB")+" - "+PRODUCTOS_RS.getString("PROD_DESC_CORTO").replace(";", "")+";;"+PRODUCTOS_RS.getString("FUNC_ID"));
            }
            PRODUCTOS_RS.close();
            state.close();
            String[] ar_productos = new String[productos.size()];
            for(int x=0; x < productos.size(); x++){
                ar_productos[x]=productos.get(x);
            }
            
            request.setAttribute("ar_productos", ar_productos);

            //vemos si tiene permiso para todos los reportes
    
           
		conexion.close();
        RequestDispatcher a = request.getRequestDispatcher("rpt.jsp");
    	a.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	

}

