package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.ContactosDAO;
import VO.ContactoVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getContactoAsync")
public class getContactoAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getContactoAsync() {
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
		
		String id_contacto= request.getParameter("id_contacto");
		System.out.println("BUSCANDO CONTACTO ");
		
		//==========TRAEMOS CONTACTO =========///
		
		response.setCharacterEncoding("UTF-8");
		
		ContactoVO contacto = ContactosDAO.getContacto(id_contacto);
		
		Gson gson= new Gson();
		
		String json_=gson.toJson(contacto);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
