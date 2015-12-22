

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

import VO.AnexoContratoVO;
import VO.EstructuraCobroVO;
import VO.MaquinaContadorVO;

/**
 * Servlet implementation class ubiestrM
 */
@WebServlet("/ubiestrM")
public class ubiestrM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ubiestrM() {
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

	
	//////////////////////////////////////////////////
	////////DEFINE PARAMETROS DE USUARIO//////////////
	
	request.setAttribute("modname", Controlador.getNameModulo());
	
	String Perfil_id=Controlador.getPerfilIDSession(request);
	
	request.setAttribute("usuname", Controlador.getUsunameSession(request));
	////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	
	String ubi_id=request.getParameter("ubi_id");
	String activo_tptc_id=request.getParameter("activo_tptc_id");
	
	MaquinaContadorVO maqConSelct =new MaquinaContadorVO(ubi_id, activo_tptc_id);
	
	request.setAttribute("ubi_id", ubi_id);
	request.setAttribute("activo_tptc_id", activo_tptc_id);
	
	HttpSession session =request.getSession(true);
	
	
	if(request.getParameter("grabar") != null){
		
		
		AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
		
		//verificamos si esta maquinaCont está integrada en el objeto anexo, caso contrario la ponemos
		if(!anexoContrato.getMaquinasContadores().contains(maqConSelct)){
			anexoContrato.getMaquinasContadores().add(maqConSelct);
		}
		//scamos de todas las estructuras la maquinaCon en primera instancia
		for(EstructuraCobroVO estructuraEnAnexo : anexoContrato.getEstructurasCobro()){
			estructuraEnAnexo.getMaquinasContador().remove(maqConSelct);
		}
		
		String estr_selecciona="";
		String[] estr_seleccionadas = request.getParameterValues("estructurasSelect[]");
		if(estr_seleccionadas!=null) for (String id_estructura: estr_seleccionadas) {
			if(id_estructura!=null){
				if(estr_selecciona.equals(""))estr_selecciona=id_estructura;
				else estr_selecciona+=","+id_estructura;
				//buscamos la estructura en el anexo 
				
				for(EstructuraCobroVO estructuraEnAnexo : anexoContrato.getEstructurasCobro()){
					if(estructuraEnAnexo.getEstrcobro_id().equals(id_estructura)){
						//removemos la estructura anterior y ponemos la nueva
						
						
						if(!estructuraEnAnexo.getMaquinasContador().contains(maqConSelct)){
							estructuraEnAnexo.getMaquinasContador().add(maqConSelct);
    						
						}
						
					}
				}
				
			}	    			
		}
		
		
		session.setAttribute("anexoContrato_", anexoContrato);
		
		request.setAttribute("estrselect", estr_selecciona);
		request.setAttribute("close", "1");
		
		
		RequestDispatcher rd = request.getRequestDispatcher("ubiestr.jsp");
        rd.forward(request, response);
		return;
	}
	
	//==========SETEAMOS ESTRUCTURAS=========///
	
	AnexoContratoVO anexoContrato = (AnexoContratoVO)session.getAttribute("anexoContrato_");
	
	ArrayList<EstructuraCobroVO> estructurasCobroSeleccionadas= new ArrayList<EstructuraCobroVO>();
	
	if(anexoContrato.getEstructurasCobro()!=null)for(EstructuraCobroVO estructuraEnAnexo : anexoContrato.getEstructurasCobro()){
	
		if(estructuraEnAnexo.getMaquinasContador().contains(maqConSelct))estructurasCobroSeleccionadas.add(estructuraEnAnexo);
		
	}
	
	
	request.setAttribute("estructurasCobro", anexoContrato.getEstructurasCobro());
	
	request.setAttribute("estructurasCobroSeleccionadas", estructurasCobroSeleccionadas);
	
	request.setAttribute("ubi_id", ubi_id);
	
	
	
	RequestDispatcher rd = request.getRequestDispatcher("ubiestrM.jsp");
    rd.forward(request, response);
	
    
    
}
}
