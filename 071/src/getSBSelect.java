

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SpecialBidDAO;
import VO.SpecialBidVO;

/**
 * Servlet implementation class getSBSelect
 */
@WebServlet("/getSBSelect")
public class getSBSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSBSelect() {
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
		
		String id_prod=request.getParameter("id_prod");
		System.out.println("BUSCANDO SB ASYNC");
		
		//==========TRAEMOS PRODUCTOS =========///
		
		
		SpecialBidVO sb = new SpecialBidVO();
		sb.setId_prod(id_prod);
		SpecialBidDAO cnx = new SpecialBidDAO();
		
		ArrayList<SpecialBidVO> specialbids= cnx.getSpecialBids(sb);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<option value=''>SELECCIONAR</option>");
		for(SpecialBidVO sb_:specialbids){
			
			out.println("<option value='"+sb_.getId()+"'>"+sb_.getGRUPO_NOM()+" - "+sb_.getSB_NRO()+" - "+sb_.getDETISB_PRECIO()+" - "+sb_.getSaldo()+"</option>");
			
		}
		
	}

}
