package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GruposDAO;
import VO.GrupoVO;

/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getGruposSelect")
public class getGruposSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getGruposSelect() {
        super();
        // TODO Auto-generated constructor stub
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
		
		//String id_cliente=request.getParameter("id_cliente");
		//String direcciones_id=request.getParameter("direcciones_id");
		
		System.out.println("BUSCANDO GRUPOS ");
		
		//==========TRAEMOS GRUPOS =========///
		
		ArrayList<GrupoVO> grupos= GruposDAO.getGruposVigentesParaSelect(new GrupoVO());
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<option value='' selected >TODOS</option>");
		for(GrupoVO grupo:grupos){
			
			out.println("<option value='"+grupo.getId()+"'>"+grupo.getId()+" - "+grupo.getNombre()+"</option>");
			
		}
		
	
		 
        
        
	}
}
