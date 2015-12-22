package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductosDAO;
import VO.EstadosVigNoVigVO;
import VO.ProductoVO;
import VO.SpecialBidVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/addProductosAsync")
public class addProductosAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProductosAsync() {
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
		
		
		String id_prod= request.getParameter("prod_id");
		String mod= request.getParameter("mod");
		System.out.println("AGREGANDO PRODUCTO "+id_prod);
		
		//==========TRAEMOS PRODUCTOS =========///
		
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session =request.getSession(true);
		SpecialBidVO specialbid=null;
		
		if(mod==null){
			specialbid = (SpecialBidVO)session.getAttribute("specialbid");
		}
		else{
			if(mod.equals("I"))specialbid = (SpecialBidVO)session.getAttribute("specialbid");
			if(mod.equals("M"))specialbid = (SpecialBidVO)session.getAttribute("specialbid_");
		}
		
		PrintWriter out = response.getWriter();
		
		if(specialbid==null){
			out.println("ERROR");
		}
		else if(specialbid.getProductos().contains(new ProductoVO(id_prod))){
			if(mod.equals("I"))out.println("ERRORYAEXISTE");
			if(mod.equals("M")){
				//ya existe pero esta no vigente, lo dejamos vigente 
				ProductoVO prodNoV= specialbid.getProductos().get(specialbid.getProductos().indexOf(new ProductoVO(id_prod)));
				prodNoV.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
				out.println("OK");
			}
			
			
		}
		else{
			saveSBStatus(specialbid,request);
			specialbid.getProductos().add(ProductosDAO.getProducto(id_prod));
			
			out.println("OK");
		}
		
	}
	
	public void saveSBStatus(SpecialBidVO sb_session, HttpServletRequest request){
		//save encabezado 
		
		String sb_valor_tc= request.getParameter("sb_valor_tc").trim();
		String cod= request.getParameter("cod").trim();
		String tipo_cambio_id=request.getParameter("tipo_cambio_id").trim();
		String sb_empresa=request.getParameter("sb_empresa").trim();
		String sb_fecsolicitud=request.getParameter("sb_fecsolicitud").trim();
		String sb_fecaprobacion=request.getParameter("sb_fecaprobacion").trim();
		
		String sb_fecinicio=request.getParameter("sb_fecinicio").trim();
		String sb_fectermino=request.getParameter("sb_fectermino").trim();
		
		sb_session.setValor_tc(Double.parseDouble(sb_valor_tc.trim()));
		sb_session.setCod(cod);
		if(tipo_cambio_id!=null)sb_session.setTipo_moneda_id(Integer.parseInt(tipo_cambio_id));
		sb_session.setEmpresa(sb_empresa);
		sb_session.setFecSolicitud(sb_fecsolicitud);
		sb_session.setFecAprobacion(sb_fecaprobacion);
		sb_session.setFecInicio(sb_fecinicio);
		sb_session.setFecTermino(sb_fectermino);
		
		//save detalle 
		for(ProductoVO prod :sb_session.getProductos()){
			String total_us= request.getParameter("total_us_"+prod.getId());
			String cantidad= request.getParameter("cant_"+prod.getId());
			String total_cl= request.getParameter("total_cl_"+prod.getId());
			
			if(total_us!=null)prod.setPrecio_us(Double.parseDouble(total_us));
			if(cantidad!=null)prod.setCantidad(Integer.parseInt(cantidad));
			if(total_cl!=null)prod.setPrecio_cl(Integer.parseInt(total_cl));
			
		}
		
	}
}