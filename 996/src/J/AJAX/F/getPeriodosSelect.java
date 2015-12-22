package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PeriodosDAO;
import VO.PeriodoVO;

/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getPeriodosSelect")
public class getPeriodosSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPeriodosSelect() {
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
		
		String id_cliente=request.getParameter("id_cliente");
		
		System.out.println("BUSCANDO PERIODOS ASYNC ");
		
		//==========TRAEMOS PERIODOS =========///
		
		PeriodoVO per_filter = new PeriodoVO();
		
		per_filter.setId_empresa(id_cliente);
		
		ArrayList<PeriodoVO> periodos= PeriodosDAO.getPeriodosVigentesParaSelect(per_filter);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<option value='' selected >TODOS</option>");
		for(PeriodoVO periodo:periodos){
			
			out.println("<option value='"+periodo.getId()+"'>"+periodo.getNombre()+"</option>");
			
		}
		
	
		 
        
        
	}
}
