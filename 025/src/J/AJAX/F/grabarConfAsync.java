package J.AJAX.F;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.AlertEventTriggeredDAO;
import DAO.ConfEquipoDetallesDAO;
import DAO.ConfEquiposDAO;
import DAO.FuncionalidadesDAO;
import DAO.ItemsFuncMascarasConfDAO;
import VO.ConfEquipoDetalleVO;
import VO.ConfEquipoVO;
import VO.EstadosVigNoVigVO;
import VO.FuncMascaraConfVO;
import VO.FuncionalidadVO;
import VO.ItemFuncMascaraConfVO;
import VO.MascaraConfEquipoVO;


/**
 * Servlet implementation class getDireccionesSelect
 */
@WebServlet("/grabarConfAsync")
public class grabarConfAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public grabarConfAsync() {
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
		
		
		System.out.println("GRABANDO LA CONFIGURACION");
		
		//==========TRAEMOS MASCARA =========///
		ItemFuncMascaraConfVO item_filter = new ItemFuncMascaraConfVO();
		item_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		ArrayList<ItemFuncMascaraConfVO> items = ItemsFuncMascarasConfDAO.getItemsMascaraConf(item_filter);
		MascaraConfEquipoVO mascara=new MascaraConfEquipoVO();
		FuncionalidadVO func_filter = new FuncionalidadVO();
		func_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		ArrayList<FuncionalidadVO> funcs= FuncionalidadesDAO.getFuncionalidades(func_filter);
		
		for(FuncionalidadVO funcionalidad :funcs){
			//seteamos funcionalidad
			FuncMascaraConfVO FuncionalidadMascara = new FuncMascaraConfVO();
			FuncionalidadMascara.setNombre(funcionalidad.getNombre());
			FuncionalidadMascara.setId(funcionalidad.getId());
			FuncionalidadMascara.setN("NO");
			for(ItemFuncMascaraConfVO item:items){
				if(item.getId_func().equals(funcionalidad.getId())){
					FuncionalidadMascara.setN(item.getOrdenFunc());
					
					if(!FuncionalidadMascara.getN().equals("NO"))FuncionalidadMascara.getItems().add(item);
				}
			}
			
			mascara.getFuncionalidadesMascara().add(FuncionalidadMascara);
			
		}
		//==========ESTABLECIENDO CONF =========///
		String confname=request.getParameter("confe_nombre");
		ConfEquipoVO conf = new ConfEquipoVO();
		conf.setNombre(confname.toUpperCase());
		
		//==========GRABAMOS LOS PRODUCTOS DE ITEMS SELECCIONADOS =========///
		
			for(FuncMascaraConfVO func :mascara.getFuncionalidadesMascara()){
				String order_func=request.getParameter("orderfunc_"+func.getId());
				if(order_func!=null)func.setN(order_func);
				for(ItemFuncMascaraConfVO item:func.getItems()){
					
					//aqui debemos capturar producto y cantidad para grabar 
					String id_producto=request.getParameter("prod_item_"+item.getId());
					String cantidad=request.getParameter("cantidad_"+item.getId());
					
					String inicial=request.getParameter("detconfe_inicial_"+item.getId());
					String recambio=request.getParameter("detconfe_recambio_"+item.getId());
					String mostrar=request.getParameter("detconfe_mostrar_"+item.getId());
					
					if(inicial==null)inicial="0";
					if(recambio==null)recambio="0";
					if(mostrar==null)mostrar="0";
					
					
					if(id_producto!=null && !id_producto.equals("")){
						ConfEquipoDetalleVO conf_detalle= new ConfEquipoDetalleVO();
						conf_detalle.setId_producto(id_producto);
						conf_detalle.setCantidad(cantidad);
						conf_detalle.setInicial(inicial);
						conf_detalle.setRecambio(recambio);
						conf_detalle.setMostrar(mostrar);
						conf_detalle.setId_mci(item.getId());
						
						conf.getDetalleConf().add(conf_detalle);
					}
					
				
				}
			}
			
		//GRABAMOS EN BD 
				
		//grabamos las funcionalidades de la mascara
			
		String id_conf=	ConfEquiposDAO.insertConfEquipo(conf, Controlador.getUsuIDSession(request));
		conf.setId(id_conf);	
		
		for(ConfEquipoDetalleVO detalle:conf.getDetalleConf()){
			detalle.setId_confe(conf.getId());
			ConfEquipoDetallesDAO.insertConfEquipoDetalle(detalle, Controlador.getUsuIDSession(request));
		}
		
		
		//registramos evento 
		
		AlertEventTriggeredDAO.insert(Constantes.MODULOID, Constantes.PINGRESAR, Controlador.getUsuIDSession(request), conf.getNombre(), conf.getId());
				
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
			
		out.println("OK");
		
	}
}
