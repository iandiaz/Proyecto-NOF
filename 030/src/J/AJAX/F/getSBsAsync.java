package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.SpecialBidsDAO;
import VO.SpecialBidVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getSBsAsync")
public class getSBsAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSBsAsync() {
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
		
		System.out.println("BUSCANDO SBS  ");
		
		//==========TRAEMOS PRODUCTOS =========///
		
		response.setCharacterEncoding("UTF-8");
		
		
		
		ArrayList<SpecialBidVO> arr = SpecialBidsDAO.getSpecialBids(new SpecialBidVO());
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
