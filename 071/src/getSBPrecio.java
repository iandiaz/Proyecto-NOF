

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SpecialBidDAO;
import VO.SpecialBidVO;

/**
 * Servlet implementation class getSBPrecio
 */
@WebServlet("/getSBPrecio")
public class getSBPrecio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSBPrecio() {
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
		String sb_id=request.getParameter("sb_id");
		
		System.out.println("BUSCANDO SB PRECIO ASYNC ");
		
		//==========TRAEMOS PRODUCTOS =========///
		
		
		SpecialBidVO sb = new SpecialBidVO();
		sb.setId_prod(id_prod);
		sb.setId(sb_id);
		
		SpecialBidDAO cnx = new SpecialBidDAO();
		
		SpecialBidVO specialbid= cnx.getSpecialBid(sb);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(specialbid.getDETISB_PRECIO());
		
		
	}

}
