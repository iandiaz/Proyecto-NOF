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

import DAO.ContactosDAO;
import VO.ContactoVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getContactosAsync")
public class getContactosAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getContactosAsync() {
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
		
		System.out.println("BUSCANDO CONTACTOS ASYNC");
		
		String id_cliente=request.getParameter("id_emp");
		String estadovignovig=request.getParameter("estadovignovig");
		
		
		//==========TRAEMOS CONTACTOS =========///
		
		response.setCharacterEncoding("UTF-8");
		
		ContactoVO contacto_filter = new ContactoVO();
		
		if(id_cliente!=null)contacto_filter.setEmpresa(new EmpresaVO(id_cliente));
		if(estadovignovig!=null)contacto_filter.setEstadoVigNoVig(new EstadosVigNoVigVO(estadovignovig));
		
		ArrayList<ContactoVO> arr = ContactosDAO.getContactos(contacto_filter);
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
        
        
	}
}
