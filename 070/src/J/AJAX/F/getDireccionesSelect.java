package J.AJAX.F;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DireccionesDAO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;

/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/getDireccionesSelect")
public class getDireccionesSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDireccionesSelect() {
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
		
		String id_cliente=request.getParameter("id_cliente");
		String direcciones_id=request.getParameter("direcciones_id");
		
		System.out.println("BUSCANDO DIRECCIONES DE CLIENTE "+id_cliente);
		
		//==========TRAEMOS DIRECCIONES DE CLIENTE=========///
		
		EmpresaVO empSeleccionada = new EmpresaVO();
		empSeleccionada.setId(id_cliente);
		
		EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
		estado.setId("1");
		
		DireccionVO dire = new DireccionVO();
		
		
		dire.setEmpresa(empSeleccionada);
		
		dire.setEstadoVigNoVig(estado);
		
		if(direcciones_id!=null)dire.setId(direcciones_id);
		
		ArrayList<DireccionVO> direcciones= DireccionesDAO.getDirecciones(dire);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(direcciones_id==null)out.println("<option value=''>SELECCIONAR</option>");
		for(DireccionVO direccion:direcciones){
			
			out.println("<option value='"+direccion.getId()+"'>"+direccion.getId()+" - "+direccion.getDireccion()+"</option>");
			
		}
		
	
		 
        
        
	}
}
