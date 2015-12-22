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
import com.google.gson.Gson;

import Constantes.Controlador;
import DAO.DtesDAO;
import VO.DteVO;
import VO.EmpresaVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getDteEncAsync")
public class getDteEncAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDteEncAsync() {
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
		
		System.out.println("BUSCANDO DTE ENC ASYNC");
		
		String modulo=request.getParameter("modulo");
		String id_emisor=request.getParameter("id_emisor");
		String f1=request.getParameter("f1");
		String f2=request.getParameter("f2");
		
		//==========TRAEMOS DTE ENC =========///
		
		response.setCharacterEncoding("UTF-8");
		
		
		DteVO dte_enc_filter= new DteVO();
		dte_enc_filter.setModulo(modulo);
		if(id_emisor!=null)dte_enc_filter.setEmisor(new EmpresaVO(id_emisor));
		dte_enc_filter.setFechaEmision1(f1);
		dte_enc_filter.setFechaEmision2(f2);
		
		ArrayList<DteVO> arr = DtesDAO.getDteEcabezados(dte_enc_filter,Controlador.getUsuIDSession(request));
		Gson gson= new Gson();
		
		String json_=gson.toJson(arr);
		PrintWriter out = response.getWriter();
		
		out.println(json_);
		 
		
		
		
        
        
	}
}
