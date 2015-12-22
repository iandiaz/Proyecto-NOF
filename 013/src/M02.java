

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
import DAO.EstadosVigNoVigDAO;
import DAO.EstructuraCobroDAO;
import DAO.RangoEstructuraCobroDAO;
import DAO.TipoCambioDAO;
import DAO.UbicacionesDAO;
import VO.AnexoContratoVO;
import VO.EstadosVigNoVigVO;
import VO.EstructuraCobroVO;
import VO.MaquinaContadorVO;
import VO.RangoEstructuraCobroVO;

/**
 * Servlet implementation class M02
 */
@WebServlet("/M02")
public class M02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M02() {
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
		
		if(request.getParameter("logout") !=  null){
				Controlador.eraseCookie(request, response);
				response.sendRedirect("/001/");	
				return;
		}
		
		//////////////////////////////////////////////////
		////////VERIFICAMOS PERMISO ASOCIADO/////////////

		String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PMODIFICAR);
		
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
			String estadovignovig=request.getParameter("estadovignovig");
			
			HttpSession session =request.getSession(true);
			AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
    		ArrayList<EstructuraCobroVO> listEstructuras = anexoContrato.getEstructurasCobro();
			
			for(int i =0; i<listEstructuras.size();i++){ 
				EstructuraCobroVO estruct= listEstructuras.get(i);
				
				if(estruct.getEstrcobro_id().equals(id_estructura)){
					listEstructuras.remove(i);
				}
				
			}
			
			anexoContrato.setEstructurasCobro(listEstructuras);
			anexoContrato.setEstadoVignoVig(new EstadosVigNoVigVO(estadovignovig));
			
			
			session.setAttribute("anexoContrato_", anexoContrato);
			
			
			response.sendRedirect("M02?anexo="+anexoContrato.getAnc_id());
			return; 

		}
		if(request.getParameter("addestructura") != null){
			
			String cargoFijoUnico=request.getParameter("cargoFijoUnico");
			String observacion=request.getParameter("observacion");
			String anc_nombre=request.getParameter("anc_nombre");
			
			String tipo_cambio_id=request.getParameter("tipo_cambio_id");
			
			String fecha_hasta=request.getParameter("fecha_hasta");
			String fecha_desde=request.getParameter("fecha_desde");
			String estadovignovig=request.getParameter("estadovignovig");
			
			
			HttpSession session =request.getSession(true);
			
			AnexoContratoVO anexoContrato = (AnexoContratoVO) session.getAttribute("anexoContrato_");
			ArrayList<MaquinaContadorVO> maquinasContratos=new ArrayList<MaquinaContadorVO>();
			
			String[] maqContador_seleccionadas = request.getParameterValues("maqContador[]");
    		if(maqContador_seleccionadas!=null) for (String maqCont: maqContador_seleccionadas) {
    			if(maqCont!=null){
    				
    				String [] maqCont_ar=maqCont.split("_");
    				MaquinaContadorVO maqCont_ = new MaquinaContadorVO(maqCont_ar[0],maqCont_ar[1]);
    				
    				maquinasContratos.add(maqCont_);
    				
    			}	    			
    		}
    		
    		anexoContrato.setMaquinasContadores(maquinasContratos);
    		anexoContrato.setCargoFijoUnico(cargoFijoUnico);
    		anexoContrato.setObservacion(observacion.toUpperCase());
    		anexoContrato.setTipoCambio_id(tipo_cambio_id);
    		
    		anexoContrato.setFecha_desde(fecha_desde);
    		anexoContrato.setFecha_hasta(fecha_hasta);
    		anexoContrato.setNombre(anc_nombre);
    		anexoContrato.setEstadoVignoVig(new EstadosVigNoVigVO(estadovignovig));
			
    		session.setAttribute("anexoContrato", anexoContrato);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("M03");
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
				String estadovignovig=request.getParameter("estadovignovig");
				
				HttpSession session =request.getSession(true);
				
				AnexoContratoVO anexoContrato = (AnexoContratoVO) session.getAttribute("anexoContrato_");
				
				ArrayList<MaquinaContadorVO> maquinasContratos=new ArrayList<MaquinaContadorVO>();
				
				String[] maqContador_seleccionadas = request.getParameterValues("maqContador[]");
	    		if(maqContador_seleccionadas!=null) for (String maqCont: maqContador_seleccionadas) {
	    			if(maqCont!=null){
	    				
	    				String [] maqCont_ar=maqCont.split("_");
	    				MaquinaContadorVO maqCont_ = new MaquinaContadorVO(maqCont_ar[0],maqCont_ar[1]);
	    				
	    				maquinasContratos.add(maqCont_);
	    				
	    			}	    			
	    		}
	    		anexoContrato.setMaquinasContadores(maquinasContratos);
	    		
	    		
	    		
	    		anexoContrato.setCargoFijoUnico(cargoFijoUnico);
	    		anexoContrato.setObservacion(observacion.toUpperCase());
	    		anexoContrato.setTipoCambio_id(tipo_cambio_id);
	    		anexoContrato.setFecha_desde(fecha_desde);
	    		anexoContrato.setFecha_hasta(fecha_hasta);
	    		anexoContrato.setNombre(anc_nombre);
	    		
				anexoContrato.setEstadoVignoVig(new EstadosVigNoVigVO(estadovignovig));
				//update encabezado anexo contrato
	    		AnexoContratoDAO.updateAnexoContrato(anexoContrato,Controlador.getUsuIDSession(request));
				
	    		AnexoContratoDAO.disableEstructurasAnexo(anexoContrato);
	    		
	    		//updateamos estructuras de cobro 
				
				for(EstructuraCobroVO estructura : anexoContrato.getEstructurasCobro()){
					
					//si la estructura existe, la updateamos, de lo contratrio insertamos 
					if(EstructuraCobroDAO.Exist(estructura)){
						//existe updateamos
						EstructuraCobroDAO.updateEstructuraCobro(estructura,Controlador.getUsuIDSession(request));
						
						EstructuraCobroDAO.disableRangos(estructura,Controlador.getUsuIDSession(request));
						
						for(RangoEstructuraCobroVO rango : estructura.getRangosEstCobro()){
							//comprobamos si el rango existe, 
							
							if(RangoEstructuraCobroDAO.Exist(rango)){
								//updateamos
								RangoEstructuraCobroDAO.updateRangoCobro(rango,Controlador.getUsuIDSession(request));
							}
							else{
								//insertamos
								String id_rango=RangoEstructuraCobroDAO.insertRangoCobro(rango, estructura.getEstrcobro_id(),Controlador.getUsuIDSession(request));
								if(id_rango==null) return; 
							}
							
							
								
						}
						
					}
					else{
						//no existe insertamos
						
						String id_estructura=EstructuraCobroDAO.insertEstructuraCobro(estructura, anexoContrato.getAnc_id(),Controlador.getUsuIDSession(request));
						if(id_estructura==null) return; 
						
						int indexSet=anexoContrato.getEstructurasCobro().indexOf(estructura);
						
						estructura.setEstrcobro_id(id_estructura);
						
						anexoContrato.getEstructurasCobro().set(indexSet,estructura);
						
						for(RangoEstructuraCobroVO rango : estructura.getRangosEstCobro()){
							//insertamos los rangos asociados 
							String id_rango=RangoEstructuraCobroDAO.insertRangoCobro(rango, id_estructura,Controlador.getUsuIDSession(request));
							if(id_rango==null) return; 
								
						}
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
			
			String id_anexo=request.getParameter("anexo");
			String nuevamod=request.getParameter("n");
		    
			HttpSession session =request.getSession(true);
    		
    		//------------------------- ANEXO CONTRATO -------------------------//
			AnexoContratoVO anexoContrato = new AnexoContratoVO();
    		
			if(nuevamod!=null && nuevamod.equals("1")){
				anexoContrato.setAnc_id(id_anexo);
	    		
	    		AnexoContratoDAO.getAnexoContrato(anexoContrato);
	    		anexoContrato.setMaquinasContadores(UbicacionesDAO.getUbicacionesConAnexo(id_anexo,null));
	    	
			}
			else{
				anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
	    		
			}
			
			
    		
	    	request.setAttribute("cliente_firma", "");
		    request.setAttribute("CONT_NOMBRE", "");
		    request.setAttribute("contrato_fecha_firma","");
    		
    		//-------------------------NOW-------------------------//
           
            request.setAttribute("fecha", Controlador.getNowFormated());
            
          //ubicaciones 
            request.setAttribute("maquinasContadores", UbicacionesDAO.getUbicacionesConAnexo(anexoContrato.getAnc_id(),null));
             request.setAttribute("anexoContrato", anexoContrato);
            request.setAttribute("tipocambios", TipoCambioDAO.getTipoCambios());
            request.setAttribute("estadosvignovig", EstadosVigNoVigDAO.getEstados());
    		
            session.setAttribute("anexoContrato_", anexoContrato);
            
            //----------------------- FIN ------------------------//
           
		}catch(Exception ex){
		    out.println("Error "+ex);
		    ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("M02.jsp");
        rd.forward(request, response);
		}
	}

}
