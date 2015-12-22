

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
import Constantes.Controlador;
import DAO.FuncionalidadesDAO;
import DAO.ItemsFuncMascarasConfDAO;
import VO.EstadosVigNoVigVO;
import VO.FuncMascaraConfVO;
import VO.FuncionalidadVO;
import VO.ItemFuncMascaraConfVO;
import VO.MascaraConfEquipoVO;

/**
 * Servlet implementation class N024_I_01_001
 */
@WebServlet("/N024_M_01_001")
public class N024_M_01_001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public N024_M_01_001() {
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
		
		
		//==========MASCARA=========///
		
		//TRAEMOS TODOS LOS ITEMS VIGENTES REGISTRADOS
		ItemFuncMascaraConfVO item_filter = new ItemFuncMascaraConfVO();
		item_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		ArrayList<ItemFuncMascaraConfVO> items = ItemsFuncMascarasConfDAO.getItemsMascaraConf(item_filter);
		
		HttpSession session =request.getSession(true);
		
		MascaraConfEquipoVO mascara=null; 
		if(request.getParameter("n") !=null && request.getParameter("n").equals("1")){
			mascara = new MascaraConfEquipoVO();
			FuncionalidadVO func_filter = new FuncionalidadVO();
			func_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
			ArrayList<FuncionalidadVO> funcs= FuncionalidadesDAO.getFuncionalidades(func_filter);
				
			for(FuncionalidadVO funcionalidad :funcs){
				//seteamos funcionalidad
				FuncMascaraConfVO FuncionalidadMascara = new FuncMascaraConfVO();
				FuncionalidadMascara.setNombre(funcionalidad.getNombre());
				FuncionalidadMascara.setId(funcionalidad.getId());
				FuncionalidadMascara.setN("NO");
				
				int id_item=1;
				for(ItemFuncMascaraConfVO item:items){
					if(item.getId_func().equals(funcionalidad.getId())){
						FuncionalidadMascara.setN(item.getOrdenFunc());
						item.setId(id_item+"");
						FuncionalidadMascara.getItems().add(item);
						id_item++;
					}
				}
				
				mascara.getFuncionalidadesMascara().add(FuncionalidadMascara);
				
			}
			
			
			quickSortFuncs(mascara.getFuncionalidadesMascara());
			
		}
		else{
			
			mascara = (MascaraConfEquipoVO)session.getAttribute("mascara");
			
			if(mascara==null){
				System.out.println("NULL SESSION OBJECT ");
				mascara = new MascaraConfEquipoVO();
				FuncionalidadVO func_filter = new FuncionalidadVO();
				func_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
				ArrayList<FuncionalidadVO> funcs= FuncionalidadesDAO.getFuncionalidades(func_filter);
				
				for(FuncionalidadVO funcionalidad :funcs){
					//seteamos funcionalidad
					FuncMascaraConfVO FuncionalidadMascara = new FuncMascaraConfVO();
					FuncionalidadMascara.setNombre(funcionalidad.getNombre());
					FuncionalidadMascara.setId(funcionalidad.getId());
					FuncionalidadMascara.setN("NO");
					int id_item=1;
					
					for(ItemFuncMascaraConfVO item:items){
						if(item.getId_func().equals(funcionalidad.getId())){
							FuncionalidadMascara.setN(item.getOrdenFunc());
							item.setId(id_item+"");
							FuncionalidadMascara.getItems().add(item);
							id_item++;
						}
					}
					
					mascara.getFuncionalidadesMascara().add(FuncionalidadMascara);
					
				}
				quickSortFuncs(mascara.getFuncionalidadesMascara());
				
			}else{
				System.out.println("NO NULL SESSION OBJECT ");
			}
		}
		
        session.setAttribute("mascara", mascara);
		
		request.setAttribute("mascara", mascara);
		
		//System.out.println(System.getProperty("user.dir"));
		
		RequestDispatcher rd = request.getRequestDispatcher("N024_M_01_001.jsp");
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
		for(FuncMascaraConfVO funcionalidad:funcs_nos){
			funcs.add(funcionalidad);
		}
		
	}

}
