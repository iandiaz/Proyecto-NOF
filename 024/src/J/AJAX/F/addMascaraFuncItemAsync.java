package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VO.FuncMascaraConfVO;
import VO.ItemFuncMascaraConfVO;
import VO.MascaraConfEquipoVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/addMascaraFuncItemAsync")
public class addMascaraFuncItemAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMascaraFuncItemAsync() {
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
		
		
		String func_id=request.getParameter("additemtofunc");
		
		System.out.println("AGREGANDO ITEM A LA FUNC "+func_id+" ASYNC");
		
		
		//==========TRAEMOS FUNCIONALIDAD =========///
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
				if(itemorder!=null)item.setOrden(itemorder);
				item.setIsPadre(itemispadre);
			}
		}
		
		
		FuncMascaraConfVO funcMascara = mascara.getFuncionalidadesMascara().get(mascara.getFuncionalidadesMascara().indexOf(new FuncMascaraConfVO(func_id)));
		
		//comprobamos que id ponerle  
				int id=1;
				while(true){
					if(funcMascara.getItems().contains(new ItemFuncMascaraConfVO(id+""))){
						id++;
					}
					else{
						break;	
					}
				}
		
		funcMascara.getItems().add(new ItemFuncMascaraConfVO(id+"",""));
				
		response.setCharacterEncoding("UTF-8");
		
		
		PrintWriter out = response.getWriter();
			
		out.println("OK");
		
	}
}
