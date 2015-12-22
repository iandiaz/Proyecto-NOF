

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.ConfEquipoDetallesDAO;
import DAO.ConfEquiposDAO;
import DAO.FuncionalidadesDAO;
import DAO.ItemsFuncMascarasConfDAO;
import VO.ConfEquipoDetalleVO;
import VO.ConfEquipoVO;
import VO.EstadosVigNoVigVO;
import VO.FuncMascaraConfVO;
import VO.FuncionalidadVO;
import VO.ItemFuncMascaraConfVO;
import VO.MascaraConfEquipoVO;

/**
 * Servlet implementation class N025_C_02_001
 */
@WebServlet("/N025_C_02_001")
public class N025_C_02_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N025_C_02_001() {
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
		
	}
	
	private void mt(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		PrintWriter out = response.getWriter();		
	   	
	   	if(request.getParameter("logout") !=  null){
	   			Controlador.eraseCookie(request, response);
	   			response.sendRedirect("/001/");	
	   			return;
	   	}

	   	//////////////////////////////////////////////////
	   	////////VERIFICAMOS PERMISO ASOCIADO/////////////
	   	
	   	String p=Controlador.verificaPermisoSession(Controlador.getPerfilIDSession(request), Constantes.PCONSULTAR);
	   	
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
		
	   	String id_conf=request.getParameter("id");
	   	ConfEquipoVO configuracion= ConfEquiposDAO.getConfiguracion(id_conf);
	   	
	   	ConfEquipoDetalleVO conf_detalle_filter = new ConfEquipoDetalleVO();
	   	conf_detalle_filter.setId_confe(configuracion.getId());
	   	
	   	ArrayList<ConfEquipoDetalleVO> conf_detalle= ConfEquipoDetallesDAO.getConfiguracionDetalle(conf_detalle_filter);
	   	
	   	configuracion.setDetalleConf(conf_detalle);
	   	
	//==========MASCARA=========///
		
		//TRAEMOS TODOS LOS ITEMS VIGENTES REGISTRADOS
		ItemFuncMascaraConfVO item_filter = new ItemFuncMascaraConfVO();
		item_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		ArrayList<ItemFuncMascaraConfVO> items = ItemsFuncMascarasConfDAO.getItemsMascaraConf(item_filter);
		MascaraConfEquipoVO mascara=new MascaraConfEquipoVO();
			FuncionalidadVO func_filter = new FuncionalidadVO();
			func_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			ArrayList<FuncionalidadVO> funcs= FuncionalidadesDAO.getFuncionalidades(func_filter);
			
			for(FuncionalidadVO funcionalidad :funcs){
				//seteamos funcionalidad
				FuncMascaraConfVO FuncionalidadMascara = new FuncMascaraConfVO();
				FuncionalidadMascara.setNombre(funcionalidad.getNombre());
				FuncionalidadMascara.setId(funcionalidad.getId());
				FuncionalidadMascara.setN("NO");
				for(ItemFuncMascaraConfVO item:items){
					if(item.getId_func().equals(funcionalidad.getId())){
						FuncionalidadMascara.setN(item.getOrdenFunc());
						if(item.getIsPadre().equals("1")){
							//evaluamos si el padre tiene hijos, de no tenerlos lo borramos 
							boolean tieneHijos=false; 
							for(ConfEquipoDetalleVO confdetalle:conf_detalle){
								if(Integer.parseInt(confdetalle.getItem().getCod_interno())>Integer.parseInt(item.getCod_interno()) 
										&& (Integer.parseInt(confdetalle.getItem().getCod_interno())<=Integer.parseInt(item.getCod_interno())+99)){
									// es hijo
									
									tieneHijos=true;
									break; 
								}
							}
							if(tieneHijos)FuncionalidadMascara.getItems().add(item);
						}
						else{
							for(ConfEquipoDetalleVO confdetalle:conf_detalle){
								if(confdetalle.getItem().getId().equals(item.getId())){
									FuncionalidadMascara.getItems().add(item);
									break; 
								}
							}
						}
					}
				}
				
				if(FuncionalidadMascara.getItems().size()>0)mascara.getFuncionalidadesMascara().add(FuncionalidadMascara);
				
			}
			
			
		quickSortFuncs(mascara.getFuncionalidadesMascara());
			
        
		request.setAttribute("mascara", mascara);
		request.setAttribute("configuracion", configuracion);
	   	
	   	RequestDispatcher rd = request.getRequestDispatcher("N"+Constantes.MODULO+"_C_02_001.jsp");
		rd.forward(request, response);
		
	}
	
	public void sortFuncs(ArrayList<FuncMascaraConfVO> funcs){
		boolean ordenado=true; 
		
		FuncMascaraConfVO funcAnt=null;
		int i=0;
		for(FuncMascaraConfVO funcionalidad:funcs){
			if(funcAnt==null)funcAnt=funcionalidad;
			else{
				//comparamos 
				
				if( Integer.parseInt(funcAnt.getN())>Integer.parseInt(funcionalidad.getN())  ){
					ordenado=false;
					
					funcs.set(i, funcAnt);
					funcs.set(i-1, funcionalidad);
					
				}else{
					funcAnt=funcionalidad;
				}
				
				
			}
		i++;
		}
		
		
		if(!ordenado)sortFuncs(funcs);
		
	} 
	
	public void quickSortFuncs(ArrayList<FuncMascaraConfVO> funcs){
		ArrayList<FuncMascaraConfVO> funcs_nos=new ArrayList<FuncMascaraConfVO>();
		ArrayList<FuncMascaraConfVO> funcs_number=new ArrayList<FuncMascaraConfVO>();
		
		for(FuncMascaraConfVO funcionalidad:funcs){
			if(funcionalidad.getN().equals("NO"))funcs_nos.add(funcionalidad);
			else funcs_number.add(funcionalidad);
		}
		
		sortFuncs(funcs_number);
		
		funcs.clear();
		
		for(FuncMascaraConfVO funcionalidad:funcs_number){
			funcs.add(funcionalidad);
		}
		/*for(FuncMascaraConfVO funcionalidad:funcs_nos){
			funcs.add(funcionalidad);
		}*/
		
	}

}
