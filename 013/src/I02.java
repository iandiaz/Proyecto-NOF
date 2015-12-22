

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constantes.Constantes;
import DAO.AnexoContratoDAO;
import DAO.ContratoDAO;
import DAO.EstructuraCobroDAO;
import DAO.RangoEstructuraCobroDAO;
import DAO.TipoCambioDAO;
import DAO.UbicacionesDAO;
import VO.AnexoContratoVO;
import VO.ContratoVO;
import VO.EmpresaVO;
import VO.EstructuraCobroVO;
import VO.RangoEstructuraCobroVO;
/**
 * Servlet implementation class ianexo_add
 */
@WebServlet("/I02")
public class I02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I02() {
        super();
      
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(Controlador.validateSession(request, response)){
			
			 mt(request,response);
		}
		else response.sendRedirect("/001/");
	}
	
	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();	
		out.print("asdasad");
		System.out.println("cargando");
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PINGRESAR);
		
		if(p.equals("0")){
			response.sendRedirect("/001/");	
			return;
		}
		
		//////////////////////////////////////////////////
		////////DEFINE PARAMETROS DE USUARIO//////////////
		
		request.setAttribute("modname", Controlador.getNameModulo());
		
		String Perfil_id=Controlador.getPerfilIDSession(request);
		
		request.setAttribute("usuname", Controlador.getUsunameSession(request));
		////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////

		
		if(request.getParameter("deteleEstructura") != null){
			
			String id_estructura = request.getParameter("deteleEstructura");
			
			HttpSession session =request.getSession(true);
			AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato");
    		ArrayList<EstructuraCobroVO> listEstructuras = anexoContrato.getEstructurasCobro();
			
			for(int i =0; i<listEstructuras.size();i++){ 
				EstructuraCobroVO estruct= listEstructuras.get(i);
				
				if(estruct.getEstrcobro_id().equals(id_estructura)){
					listEstructuras.remove(i);
				}
				
			}
			
			anexoContrato.setEstructurasCobro(listEstructuras);
			
			session.setAttribute("anexoContrato", anexoContrato);
			
			String contrato_id=anexoContrato.getContrato().getContrato_id();
			String emp_id = anexoContrato.getEmpresa().getEmpresas_id();
			
			response.sendRedirect("I02?contrato_id="+contrato_id+"&emp_id="+emp_id);
			return; 

		}
		if(request.getParameter("addestructura") != null){
			
			String cargoFijoUnico=request.getParameter("cargoFijoUnico");
			String observacion=request.getParameter("observacion");
			String anc_nombre=request.getParameter("anc_nombre");
			
			String tipo_cambio_id=request.getParameter("tipo_cambio_id");
			
			String fecha_hasta=request.getParameter("fecha_hasta");
			String fecha_desde=request.getParameter("fecha_desde");
			
			
			HttpSession session =request.getSession(true);
			
			AnexoContratoVO anexoContrato = (AnexoContratoVO) session.getAttribute("anexoContrato");
			
			anexoContrato.setCargoFijoUnico(cargoFijoUnico);
    		anexoContrato.setObservacion(observacion.toUpperCase());
    		anexoContrato.setTipoCambio_id(tipo_cambio_id);
    		
    		anexoContrato.setFecha_desde(fecha_desde);
    		anexoContrato.setFecha_hasta(fecha_hasta);
    		anexoContrato.setNombre(anc_nombre);
    		session.setAttribute("anexoContrato", anexoContrato);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("I03");
            rd.forward(request, response);
			return; 
		}
		if(request.getParameter("grabar") != null){
			try {
				
				String cargoFijoUnico=request.getParameter("cargoFijoUnico");
				String observacion=request.getParameter("observacion");
				String tipo_cambio_id=request.getParameter("tipo_cambio_id");
				String fecha_hasta=request.getParameter("fecha_hasta");
				String fecha_desde=request.getParameter("fecha_desde");
				String anc_nombre=request.getParameter("anc_nombre");
				
				HttpSession session =request.getSession(true);
				
				AnexoContratoVO anexoContrato = (AnexoContratoVO) session.getAttribute("anexoContrato");
				
	    		anexoContrato.setCargoFijoUnico(cargoFijoUnico);
	    		anexoContrato.setObservacion(observacion.toUpperCase());
	    		anexoContrato.setTipoCambio_id(tipo_cambio_id);
	    		anexoContrato.setFecha_desde(fecha_desde);
	    		anexoContrato.setFecha_hasta(fecha_hasta);
	    		anexoContrato.setNombre(anc_nombre);
	    		
	    		
				//onsertamos encabezado anexo contrato
	    		anexoContrato.setAnc_id(AnexoContratoDAO.insertAnexoContrato(anexoContrato,Controlador.getUsuIDSession(request)));
				
	    		if(anexoContrato.getAnc_id()==null) return;
	    		
	    		//le decimos a las ubicaciones a que anexo pertenecen
	    		
	    		//UbicacionesDAO.setAnexoCToUbis(anexoContrato);
	    		
	    		
				//insertamos estructuras de cobro 
				
				for(EstructuraCobroVO estructura : anexoContrato.getEstructurasCobro()){
					String id_estructura=EstructuraCobroDAO.insertEstructuraCobro(estructura, anexoContrato.getAnc_id(),Controlador.getUsuIDSession(request));
					if(id_estructura==null) return; 
					
					estructura.setEstrcobro_id(id_estructura);
					//buscamos si alguna ubicacion del anexo 
					
					for(RangoEstructuraCobroVO rango : estructura.getRangosEstCobro()){
						//insertamos los rangos asociados 
						String id_rango=RangoEstructuraCobroDAO.insertRangoCobro(rango, id_estructura,Controlador.getUsuIDSession(request));
						if(id_rango==null) return; 
							
					}
					
				}
				
				//insertamos la relacion ubicacion estructura
	    		AnexoContratoDAO.setAnexoUbisToEstructurasC(anexoContrato);
				
	    		
				session.invalidate();
				response.sendRedirect("menuanexo?Exito=OK");
				return; 
	        	
			}catch(Exception ex){
				ex.printStackTrace();
			    out.println("Error "+ex);
			}
		}else{
		
				
		
		try {
			
			
			
    		String CID=request.getParameter("contrato_id");
    		
    		String emp_id=request.getParameter("emp_id");
    		
    		
    		//------------------------- ANEXO CONTRATO -------------------------//
    		HttpSession session =request.getSession(true);
    		AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato");
    		
    		if(anexoContrato==null){
    			anexoContrato = new AnexoContratoVO();
    			anexoContrato.setMaquinasContadores(UbicacionesDAO.getUbicacionesSinAnexo(emp_id,"1"));
    		}
    		
    		
    		anexoContrato.setAnc_id(AnexoContratoDAO.getNewId()+"");
	    	
	    	ContratoVO contrato = new ContratoVO();
	    	contrato.setContrato_id(CID);
	    	
	    	EmpresaVO empresa = new EmpresaVO();
	    	empresa.setEmpresas_id(emp_id);
	    	
	    	request.setAttribute("cliente_firma", "");
		    request.setAttribute("CONT_NOMBRE", "");
		    request.setAttribute("contrato_fecha_firma","");
    		
    		ContratoDAO.getContrato(CID, emp_id, contrato, empresa);
    		
    		anexoContrato.setContrato(contrato);
    		anexoContrato.setEmpresa(empresa);
	    	 
            //-------------------------NOW-------------------------//
           
            request.setAttribute("fecha", Controlador.getNowFormated());
            
            request.setAttribute("anexoContrato", anexoContrato);
            request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
    		
            
            session.setAttribute("anexoContrato", anexoContrato);
            
            //----------------------- FIN ------------------------//
           
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("I02.jsp");
        rd.forward(request, response);
		}
	}

}
