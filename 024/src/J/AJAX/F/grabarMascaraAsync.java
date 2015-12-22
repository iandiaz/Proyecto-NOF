package J.AJAX.F;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.AlertEventTriggeredDAO;
import DAO.ItemsFuncMascarasConfDAO;
import VO.EstadosVigNoVigVO;
import VO.FuncMascaraConfVO;
import VO.ItemFuncMascaraConfVO;
import VO.MascaraConfEquipoVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/grabarMascaraAsync")
public class grabarMascaraAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public grabarMascaraAsync() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 mt(request,response);
	}

	public void mt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		
		System.out.println("GRABANDO LA MASCARA");
		
		//==========TRAEMOS MASCARA =========///
		
		HttpSession session =request.getSession(true);
		MascaraConfEquipoVO mascara = (MascaraConfEquipoVO)session.getAttribute("mascara");
		
		//==========GRABAMOS LOS NOMBRES DE ITEMS DE SER NECESARIO =========///
		
			for(FuncMascaraConfVO func :mascara.getFuncionalidadesMascara()){
				String order_func=request.getParameter("orderfunc_"+func.getId());
				if(order_func!=null)func.setN(order_func);
				for(ItemFuncMascaraConfVO item:func.getItems()){
					String itemname=request.getParameter("item_"+func.getId()+"_"+item.getId());
					String itemorder=request.getParameter("itemorder_"+func.getId()+"_"+item.getId());
					String itemispadre=request.getParameter("item_ispadre_"+func.getId()+"_"+item.getId());
					if(itemispadre==null)itemispadre="0";
					if(itemname!=null)item.setNombre(itemname.toUpperCase());
					if(itemorder!=null)item.setOrden(itemorder.toUpperCase());
					item.setIsPadre(itemispadre);
					
				}
			}
			
		//GRABAMOS EN BD 
				
		//grabamos las funcionalidades de la mascara
		
		for(FuncMascaraConfVO func:mascara.getFuncionalidadesMascara()){
			//grabamos los items de las funcionalidades 
			for(ItemFuncMascaraConfVO item:func.getItems()){
				 item.setId_func(func.getId());
				 item.setOrdenFunc(func.getN());
				 
				 
				 
				 //validamos, si el item no existe insertamos, de lo contrario hacemos update.
				 //buscamos item por orden y funcionalidad 
				 
				 	ItemFuncMascaraConfVO item_filter = new ItemFuncMascaraConfVO();
					item_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
					item_filter.setId_func(func.getId());
					item_filter.setOrden(item.getOrden());
					
					if(item.getNombre()!=null){
						if(item.getCorr()!=null){
							//existe asi que hacemos update 
							item.setId(item.getCorr());
							ItemsFuncMascarasConfDAO.updatetItemFMascara(item, Controlador.getUsuIDSession(request), Constantes.PMODIFICAR);
						}else{
							//no existe, insertamos
							ItemsFuncMascarasConfDAO.insertItemFMascara(item, Controlador.getUsuIDSession(request), Constantes.PMODIFICAR);
						}
						
					}
					
				 
			}
		}
		
		
		//registramos evento 
		
		AlertEventTriggeredDAO.insert(Constantes.MODULOID, Constantes.PMODIFICAR, Controlador.getUsuIDSession(request), "", "0");
				
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
			
		out.println("OK");
		
	}
}
