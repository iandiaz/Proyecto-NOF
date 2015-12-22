package J.AJAX.F;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VO.ContactoVO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.OcDetalleVO;
import VO.OcVO;
import VO.ProductoVO;
import VO.TipoMonedaVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/removeProductosAsync")
public class removeProductosAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeProductosAsync() {
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
		
		
		String id_prod= request.getParameter("id_prod_remove");
		String mod= request.getParameter("mod");
		System.out.println("REMOVIENDO PRODUCTO "+id_prod);
		
		//==========TRAEMOS PRODUCTOS =========///
		
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session =request.getSession(true);
		OcVO oc=null;
		
		if(mod==null){
			oc = (OcVO)session.getAttribute("oc");
		}
		else{
			if(mod.equals("I"))oc = (OcVO)session.getAttribute("oc");
			if(mod.equals("M"))oc = (OcVO)session.getAttribute("oc_");
		}
		PrintWriter out = response.getWriter();
		
		if(oc==null){
			out.println("ERROR");
		}
		else{
			saveOCStatus(oc,request);
			
			
			if(mod==null){
				int indexDelete=-1;
				int count=0;
				for(OcDetalleVO det :oc.getDetalle_oc()){
					if(det.getProducto().getId().equals(id_prod)){
						indexDelete=count;
						break;
					}
					count++;
				}
				//buscamos el producto a eliminar
				if(indexDelete!=-1)oc.getDetalle_oc().remove(indexDelete);
			}
			else{
				if(mod.equals("I")){
					int indexDelete=-1;
					int count=0;
					for(OcDetalleVO det :oc.getDetalle_oc()){
						if(det.getProducto().getId().equals(id_prod)){
							indexDelete=count;
							break;
						}
						count++;
					}
					//buscamos el producto a eliminar
					if(indexDelete!=-1)oc.getDetalle_oc().remove(indexDelete);
					
				}
				if(mod.equals("M")){
					
					for(OcDetalleVO det :oc.getDetalle_oc()){
						if(det.getProducto().getId().equals(id_prod)){
							det.setEstadoVigNoVig(new EstadosVigNoVigVO("2"));
							break;
						}
						
					}
					
				}
			}
			
			
			
			out.println("OK");
		}
		
	}
	
	public void saveOCStatus(OcVO oc, HttpServletRequest request){
		//save encabezado 
		String estados_vig_novig_id= request.getParameter("estados_vig_novig_id").trim();
		String oc_nro= request.getParameter("oc_nro").trim();
		String oc_notapedido= request.getParameter("oc_notapedido").trim();
		String oc_tcambio_ref=request.getParameter("oc_tcambio_ref").trim();
		String oc_forma_pago=request.getParameter("oc_forma_pago").trim();
		
		String oc_plazo_pago=request.getParameter("oc_plazo_pago").trim();
		String oc_fec_emision=request.getParameter("oc_fec_emision").trim();
		String oc_fec_recepcion=request.getParameter("oc_fec_recepcion").trim();
		String id_proveedor=request.getParameter("id_proveedor").trim();
		String id_dire_prov=request.getParameter("id_dire_prov").trim();
		String id_contacto_proveedor=request.getParameter("id_contacto_proveedor").trim();
		
		String id_comprador=request.getParameter("id_comprador").trim();
		String id_dire_comprador=request.getParameter("id_dire_comprador").trim();
		String id_contacto_comprador=request.getParameter("id_contacto_comprador").trim();
		String tipo_cambio_id=request.getParameter("tipo_cambio_id").trim();
		
		if(id_proveedor!=null) oc.setProveedor(new EmpresaVO(id_proveedor));
		if(id_dire_prov!=null) oc.setDireccion_proveedor(new DireccionVO(id_dire_prov));
		if(id_contacto_proveedor!=null) oc.setContacto_proveedor(new ContactoVO(id_contacto_proveedor));
		
		if(id_comprador!=null) oc.setComprador(new EmpresaVO(id_comprador));
		if(id_dire_comprador!=null) oc.setDireccion_comprador(new DireccionVO(id_dire_comprador));
		if(id_contacto_comprador!=null) oc.setContacto_comprador(new ContactoVO(id_contacto_comprador));
		
		if(tipo_cambio_id!=null && !tipo_cambio_id.equals("")) oc.setTipomoneda(new TipoMonedaVO(tipo_cambio_id));
		
		
		oc.setNro(oc_nro);
		oc.setNotapedido(oc_notapedido);
		oc.setTcambio_ref(oc_tcambio_ref);
		oc.setFormadepago(oc_forma_pago);
		oc.setPlazodepago(oc_plazo_pago);
		oc.setFec_emision(oc_fec_emision);
		oc.setFec_recepcion(oc_fec_recepcion);
		if(estados_vig_novig_id!=null)oc.setEstadoVigNoVig(new EstadosVigNoVigVO(estados_vig_novig_id));
		
		//save detalle 
		for(OcDetalleVO detalle_oc :oc.getDetalle_oc()){
			
			ProductoVO prod= detalle_oc.getProducto();
			
			String total_us= request.getParameter("total_us_"+prod.getId());
			String cantidad= request.getParameter("cant_"+prod.getId());
			String total_cl= request.getParameter("total_cl_"+prod.getId());
			
			if(total_us!=null)detalle_oc.setPrecio(total_us);
			if(cantidad!=null)detalle_oc.setCantidad(cantidad);
			if(total_cl!=null)detalle_oc.setPrecio_pesos(total_cl);
			
		}
		
	}
}
