import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Constantes.Constantes;
import Constantes.Controlador;
import DAO.BirtActivosDAO;
import DAO.ConfEquipoDetallesDAO;
import DAO.ConfEquiposDAO;
import DAO.DireccionesDAO;
import DAO.EmpresasDAO;
import DAO.GruposDAO;
import DAO.ItemsFuncMascarasConfDAO;
import DAO.UbicacionesDAO;
import VO.ActivoVO;
import VO.ConfEquipoDetalleVO;
import VO.ConfEquipoVO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.GrupoVO;
import VO.ItemFuncMascaraConfVO;
import VO.PedidoDetalleVO;
import VO.PedidoVO;
import VO.UbicacionVO;

/**
 * Servlet implementation class R_GEN1
 */
@WebServlet("/R_GEN3")
public class R_GEN3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final boolean ConfEquipoVO = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_GEN3() {
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
		
		PedidoVO pedido_ = new PedidoVO();
		
		PedidoDetalleVO detallepedido_ = new PedidoDetalleVO();
		detallepedido_.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		EmpresaVO empresa_filter = new EmpresaVO();
		ActivoVO active_filter = new ActivoVO();
		active_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
		
		DireccionVO direccion_filter = new DireccionVO();
		
		if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
			
		}
		if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
			empresa_filter.setId(request.getParameter("select_empresa"));
		}
		if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
			direccion_filter.setId(request.getParameter("select_direccion"));
		}
		if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
			
		}
		if(request.getParameter("select_prod") !=  null && !request.getParameter("select_prod").equals("") ){
			
		}
		
		
		
		ArrayList<ActivoVO> activos = BirtActivosDAO.getActivos(active_filter);
		ArrayList<UbicacionVO> ubicaciones = UbicacionesDAO.getUbicaciones(new UbicacionVO(), Controlador.getUsuIDSession(request));
		ArrayList<DireccionVO> direcciones = DireccionesDAO.getDirecciones(direccion_filter);
		ArrayList<EmpresaVO> empresas = EmpresasDAO.getEmpresas(empresa_filter,Controlador.getUsuIDSession(request));
		ArrayList<GrupoVO> grupos = GruposDAO.getGrupos(new GrupoVO(), Controlador.getUsuIDSession(request));
		
		
		for(ActivoVO activo : activos){
			int indexof=ubicaciones.indexOf(new UbicacionVO(activo.getUbicacion().getId()));
			if(indexof!=-1){
				UbicacionVO ubiInList= ubicaciones.get(indexof);
				if(!ubiInList.getActivos().contains(activo)){
					ubiInList.getActivos().add(activo);
				}	
			}
			
		}
		
		//TRAEMOS TODOS LOS ITEMS MASCARA VIGENTES REGISTRADOS
		ItemFuncMascaraConfVO item_filter = new ItemFuncMascaraConfVO();
		item_filter.setEstadoVigNoVig(new EstadosVigNoVigVO("1"));
				
		ArrayList<ItemFuncMascaraConfVO> itemsMascara = ItemsFuncMascarasConfDAO.getItemsMascaraConf(item_filter);
		//traemos configuraciones  
		ArrayList<ConfEquipoVO> configuraciones = ConfEquiposDAO.getConfiguraciones(new ConfEquipoVO());
		
		ConfEquipoDetalleVO conf_detalle_filter = new ConfEquipoDetalleVO();
	   	ArrayList<ConfEquipoDetalleVO> conf_detalles= ConfEquipoDetallesDAO.getConfiguracionDetalle(conf_detalle_filter);
		
	   	for(ConfEquipoVO configuracion : configuraciones){
	   		
			for(ConfEquipoDetalleVO detalle_conf : conf_detalles){
		   		
				if(detalle_conf.getId_confe().equals(configuracion.getId())){
					configuracion.getDetalleConf().add(detalle_conf);
				}
		   		
		   	}
	   		
	   	}
		
		//validamos si corresponde o no corresponde el activo en la ubicacion según su configuracion 
		
		for(UbicacionVO ubi : ubicaciones){
			if(ubi.getConf_equipo()!=null && !ubi.getConf_equipo().getId().equals("0")){
				//tiene conf seteada, evaluamos productos. 
				ConfEquipoVO confubi=configuraciones.get(configuraciones.indexOf(new ConfEquipoVO(ubi.getConf_equipo().getId())));
				for(ConfEquipoDetalleVO confdetalle :confubi.getDetalleConf()){
					boolean existeEsteProdEnUbi=false; 
					int cantProductos=0;
					
					for(ActivoVO activo:ubi.getActivos()){
						if(activo.getAnalisis_conf()!=null) continue; // si ya lo analizamos pasamos al siguiente. 
						if(confdetalle.getProducto().getId().equals(activo.getProducto().getId()) && !existeEsteProdEnUbi){
							cantProductos++;
							if(cantProductos==Integer.parseInt(confdetalle.getCantidad()))existeEsteProdEnUbi=true;
							activo.setAnalisis_conf("OK");
							// vemos si el producto tiene un padre. de tenerlo dejamos todos los demas productos/activos de la familia como sobrante
							String cod_padre_potencial=confdetalle.getItem().getCod_interno().substring(0,3)+"00"; // 59920
							System.out.println("COD INTERNO:"+confdetalle.getItem().getCod_interno()+" POT PADRE:"+cod_padre_potencial);
							
							//verificamos si efectivamente tiene un padre.
							boolean tienePadre=false; 
							for(ItemFuncMascaraConfVO item_:itemsMascara){
								if(item_.getCod_interno().equals(cod_padre_potencial)){
									tienePadre=item_.getIsPadre().equals("1");
									break;
								}
							}
							
							// si es padre dejamos como sobrante todos los productos activos q no sean el que estoy evaluando 
							
							if(tienePadre){
								for(ConfEquipoDetalleVO confdetalle_p :confubi.getDetalleConf()){
									//comparamos si la conf iterada corresponde al padre encontrado 
									if(!confdetalle_p.getItem().getCod_interno().equals(confdetalle.getItem().getCod_interno()) 
											&& Integer.parseInt(confdetalle_p.getItem().getCod_interno()) >Integer.parseInt(cod_padre_potencial)
											&& Integer.parseInt(confdetalle_p.getItem().getCod_interno())<=Integer.parseInt(confdetalle.getItem().getCod_interno().substring(0,3)+"99")){
										System.out.println("------COD INTERNO "+confdetalle_p.getItem().getCod_interno()+" ES HIJO ENCONTRADO");
										//evaluamos si existen activos con este codigo, de existir dejamos todos ellos como sobrantes 
										for(ActivoVO activo_p:ubi.getActivos()){
											if(activo_p.getProducto().getId().equals(confdetalle_p.getProducto().getId()) && !activo_p.equals(activo)){
												activo_p.setAnalisis_conf("SOBRANTE");
											}
										}
									}
								}
							}
							
							
						}
						else if(confdetalle.getProducto().getId().equals(activo.getProducto().getId()) && existeEsteProdEnUbi){
							// en caso que existan mas activos de la cantidad especificada lo dejamos como sobrante 
							existeEsteProdEnUbi=true;
							activo.setAnalisis_conf("SOBRANTE");
						}
					}
					
					//si existeEsteProdEnUbi es igual a false, entonces agregamos los productos como FALTANTES
					
					if(!existeEsteProdEnUbi){
						//si este producto deberia existir lo dejamos como faltante 
						ActivoVO activoFaltante = new ActivoVO();
						activoFaltante.setId("NO EXISTE");
						activoFaltante.setProducto(confdetalle.getProducto());
						
						
							if(confdetalle.getMostrar().equals("0") && confdetalle.getInicial().equals("1")){
								// si no esta como etiquetado y es inicial colocamos OK
								activoFaltante.setAnalisis_conf("OK");
							}
							else{
								activoFaltante.setAnalisis_conf("FALTANTE");	
							}
							
						
						
						
						activoFaltante.getProducto().setFuncionalidad(confdetalle.getFuncionalidad());
						
						ubi.getActivos().add(activoFaltante);
					}
				
				}
			}
		}
		
		for(DireccionVO direccion:direcciones){
			for(UbicacionVO ubicacion : ubicaciones){
				if(ubicacion.getDireccion().getId().equals(direccion.getId())){
					direccion.getUbicaciones().add(ubicacion);
				}
			}
		}
		
		
		for(EmpresaVO empresa : empresas){
			for(DireccionVO direccion: direcciones){
				if(direccion.getEmpresa().getId().equals(empresa.getId())){
					empresa.getDirecciones().add(direccion);
				}
			}
		}
		
		for(GrupoVO grupo:grupos){
			for(EmpresaVO empresa: empresas){
				if(empresa.getGrupo().getId().equals(grupo.getId())){
					grupo.getEmpresas().add(empresa);
				}
			}
			
		}
		
		ArrayList<GrupoVO> rpt = grupos;
		
		/**
		 * PREGUNTAMOS PORQUE VIA LO GENERO
		 */
		
		if(request.getParameter("grabar_web") !=  null){
			request.setAttribute("usuname", Controlador.getUsunameSession(request));
	 	    request.setAttribute("rep", rpt);
	        
	        RequestDispatcher a = request.getRequestDispatcher("R_GEN3.jsp");
	      	a.forward(request, response);
	      	return;
			
		}
		if(request.getParameter("grabar_pdf") !=  null){
			try{
				System.out.println("GENERANDO PDF");
				 // step 1
		        Document document = new Document();
		        
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        // step 2
		        PdfWriter.getInstance(document, baos);
		        // step 3
		        document.open();
		        // step 4
		        
		        Font fontNormal = FontFactory.getFont("sans-serif",10);
		        Font fontBolt = FontFactory.getFont("sans-serif",10,Font.BOLD);
		        Font fontRED = FontFactory.getFont("sans-serif",10);
		        fontRED.setColor(BaseColor.RED);
		        
		        Font fontGREEN = FontFactory.getFont("sans-serif",10);
		        fontGREEN.setColor(BaseColor.GREEN);
		        
		        Font fontBLUE = FontFactory.getFont("sans-serif",10);
		        fontBLUE.setColor(BaseColor.BLUE);
		        
		        
		        Paragraph titulo=new Paragraph("INFORME DE CONTROL: COMPARACION UBICACIONES / CONFIGURACIONES",fontBolt); 
		        Paragraph usu=new Paragraph("GENERADO POR "+Controlador.getUsunameSession(request),fontNormal); 
		        
		        String imageUrl = "http://127.0.0.1:"+request.getServerPort()+"/"+Constantes.MODULO+"/images/logo2.png";

		        Image imageLogo = Image.getInstance(new URL(imageUrl));
		        
		        document.add(imageLogo);
		                
		        document.add(titulo);
		        
		        document.add(Chunk.NEWLINE);
		        
		        document.add(usu);
		        
		        document.add(Chunk.NEWLINE);
		        
	            List List_ = new List(List.UNORDERED);
		        
		        for(GrupoVO grupo:rpt){
		        	if(grupo.getEmpresas().size()==0)continue;
		        	
		            Chunk chunk_p1=new Chunk("GRUPO:", fontBolt); //This gonna be normal font
		            Chunk chunk2_p1=new Chunk(grupo.getId()+" - "+grupo.getNombre(), fontNormal); //This gonna be normal font
		            Paragraph p1=new Paragraph(); 
		            p1.add(new Chunk(chunk_p1));
		            p1.add(new Chunk(chunk2_p1));
		        	
		        	ListItem p1Item =new ListItem(p1);
		        	List_.add(p1Item);
		        	List List_2 = new List(List.UNORDERED);
		        	for(EmpresaVO empresa : grupo.getEmpresas()){
		        		Chunk chunk_p2=new Chunk("EMPRESA:", fontBolt);
		        		Chunk chunk2_p2=new Chunk(empresa.getId()+" - "+empresa.getNombre_nof(), fontNormal);
			            Paragraph p2=new Paragraph(); 
			            p2.add(new Chunk(chunk_p2)); 
			            p2.add(new Chunk(chunk2_p2)); 
		        		
		        		ListItem p2Item =new ListItem(p2);
		        		List_2.add(p2Item);
		        		
		        		List List_3 = new List(List.UNORDERED);
		        		for(DireccionVO direccion:empresa.getDirecciones()){
		        			Chunk chunk_p3=new Chunk("DIRECCION:", fontBolt);
			        		Chunk chunk2_p3=new Chunk(direccion.getId()+" - "+direccion.getDireccion()+" - "+direccion.getComu_nombre()+" - "+direccion.getRegi_nombre(), fontNormal);
				            Paragraph p3=new Paragraph(); 
				            p3.add(new Chunk(chunk_p3)); 
				            p3.add(new Chunk(chunk2_p3));
				            ListItem p3Item =new ListItem(p3);
				            List_3.add(p3Item);
				            
				            List List_4 = new List(List.UNORDERED);
				            
				            for(UbicacionVO ubicacion:direccion.getUbicaciones()){
				            	Chunk chunk_p4=new Chunk("UBICACION:", fontBolt);
				            	
				        		Chunk chunk2_p4=new Chunk(ubicacion.getId()
				        								+" - "+ubicacion.getNom_fisica()
				        								+" - "+ubicacion.getTipo_nombre()
				        								+" - "+(ubicacion.getConf_equipo()==null?"":
				        									ubicacion.getConf_equipo()
				        									.getNombre()), 
				        												fontNormal);
					            Paragraph p4=new Paragraph(); 
					            p4.add(new Chunk(chunk_p4)); 
					            p4.add(new Chunk(chunk2_p4));
					            ListItem p4Item =new ListItem(p4);
					            List_4.add(p4Item);
					            
					            List List_5 = new List(List.UNORDERED);
					            for(ActivoVO activo:ubicacion.getActivos()){
					            	Chunk chunk1_p5=new Chunk(activo.getProducto().getFuncionalidad().getNombre()+" - "+activo.getProducto().getPn()+" - "+activo.getId()+" - "+activo.getProducto().getDesc_corto()+" - REND: "+activo.getProducto().getVida_util_imp(), fontBolt);
					            	Chunk chunk2_p5=null;
					            	
					            	if(activo.getAnalisis_conf()==null)activo.setAnalisis_conf("SOBRANTE");
					            	
					            	if(activo.getAnalisis_conf().equals("OK")){
					            		chunk2_p5=new Chunk(" - "+(activo.getAnalisis_conf()), fontGREEN);
					            	}else if(activo.getAnalisis_conf().equals("SOBRANTE")){
					            		chunk2_p5=new Chunk(" - "+(activo.getAnalisis_conf()), fontRED);
						        			
					            	}if(activo.getAnalisis_conf().equals("FALTANTE")){
					            		chunk2_p5=new Chunk(" - "+(activo.getAnalisis_conf()), fontBLUE);
					            	}
					        		Chunk chunk3_p5=new Chunk(" - "+(activo.getUlt_mov_fec()==null?"":activo.getUlt_mov_fec())+" "+(activo.getUlt_mov_tipo()==null?"":activo.getUlt_mov_tipo()), fontBolt);
					        		
						            Paragraph p5=new Paragraph(); 
						            p5.add(new Chunk(chunk1_p5)); 
						            p5.add(new Chunk(chunk2_p5)); 
						            p5.add(new Chunk(chunk3_p5)); 
						            ListItem p5Item =new ListItem(p5);
						            List_5.add(p5Item);
						            
					            }
					            p4Item.add(List_5);
				            }
				            p3Item.add(List_4);
		        		}
		        		p2Item.add(List_3);
			    	}
		        	p1Item.add(List_2);
		        }
	            document.add(List_);
		        // step 5
		        document.close();
		        
		        response.setHeader("Expires", "0");
	            response.setHeader("Cache-Control",
	                "must-revalidate, post-check=0, pre-check=0");
	            response.setHeader("Pragma", "public");
	            // setting the content type
	            response.setContentType("application/pdf");
	            // the contentlength
	            response.setContentLength(baos.size());
	            // write ByteArrayOutputStream to the ServletOutputStream
	            ServletOutputStream os = response.getOutputStream();
	            baos.writeTo(os);
	            os.flush();
	            os.close();
		      	return;
	        
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
     
		if(request.getParameter("grabar_excel") !=  null){
			try{
				System.out.println("GENERANDO EXCEL");
				 // step 1
				// this could be file stream too or any other stream where you want to write your output
			
				// create a work book
				HSSFWorkbook workbook = new HSSFWorkbook();

				// create a sheet under the work book above
				HSSFSheet sheet = workbook.createSheet("sheet 1");

				// create a row in above sheet
				HSSFRow row = sheet.createRow(0); // 0,1,2..

				// create a cell or column in the above row
				HSSFCell cellTitulo = row.createCell(0); // 0,1,2...
				// set the actual data or cell contents
				cellTitulo.setCellValue("NEWOFFICE");
				
				HSSFRow row1 = sheet.createRow(1); // 0,1,2..
				(row1.createCell(0)).setCellValue("INFORME COMPARACION UBICACIONES / CONFIGURACIONES"); //A3
				/**
				 * DATA ENCABEZADO
				 */
				
				
				HSSFRow row3 = sheet.createRow(3); // 0,1,2..
				//HSSFCell cell_A3 = row3.createCell(0); // 0,1,2...
				
				(row3.createCell(0)).setCellValue("ID GRUPO"); //A3
				(row3.createCell(1)).setCellValue("GRUPO"); 
				(row3.createCell(2)).setCellValue("ID EMPRESA");
				(row3.createCell(3)).setCellValue("EMPRESA"); 
				(row3.createCell(4)).setCellValue("ID DIRECCION"); 
				(row3.createCell(5)).setCellValue("DIRECCION"); 
				(row3.createCell(6)).setCellValue("ID UBICACION");
				(row3.createCell(7)).setCellValue("UBICACION");
				(row3.createCell(8)).setCellValue("TIPO UBICACION");
				(row3.createCell(9)).setCellValue("CONFIGURACION UBICACION");
				
				(row3.createCell(10)).setCellValue("FUNCIONALIDAD");
				(row3.createCell(11)).setCellValue("PART NUMBER");
				(row3.createCell(12)).setCellValue("ID ACTIVO");
				(row3.createCell(13)).setCellValue("DESCRIPCION");
				(row3.createCell(14)).setCellValue("RENDIMIENTO");
				(row3.createCell(15)).setCellValue("ESTADO");
				(row3.createCell(16)).setCellValue("FECHA ULTIMO MOV");
				(row3.createCell(17)).setCellValue("ULTIMO MOV");
				
				
				/**
				 * DATA DETALLE
				 */
				int countRow=4;
				
				for(GrupoVO grupo:rpt){
					for(EmpresaVO empresa : grupo.getEmpresas()){
						for(DireccionVO direccion:empresa.getDirecciones()){
							for(UbicacionVO ubicacion:direccion.getUbicaciones()){
								for(ActivoVO activo:ubicacion.getActivos()){
									HSSFRow rowData = sheet.createRow(countRow); 
					        		(rowData.createCell(0)).setCellValue(grupo.getId());
					        		(rowData.createCell(1)).setCellValue(grupo.getNombre());
					        		(rowData.createCell(2)).setCellValue(empresa.getId());
					        		(rowData.createCell(3)).setCellValue(empresa.getNombre_nof());
					        		(rowData.createCell(4)).setCellValue(direccion.getId());
					        		(rowData.createCell(5)).setCellValue(direccion.getDireccion());
					        		(rowData.createCell(6)).setCellValue(ubicacion.getId());
					        		(rowData.createCell(7)).setCellValue(ubicacion.getNom_fisica());
					        		(rowData.createCell(8)).setCellValue(ubicacion.getTipo_nombre());
					        		(rowData.createCell(9)).setCellValue(ubicacion.getConf_equipo()==null?"":ubicacion.getConf_equipo().getNombre());
					        		
					        		(rowData.createCell(10)).setCellValue(activo.getProducto().getFuncionalidad().getNombre());
					        		(rowData.createCell(11)).setCellValue(activo.getProducto().getPn());
					        		(rowData.createCell(12)).setCellValue(activo.getId());
					        		
					        		(rowData.createCell(13)).setCellValue(activo.getProducto().getDesc_corto());
					        		(rowData.createCell(14)).setCellValue(activo.getProducto().getVida_util_imp());
					        		(rowData.createCell(15)).setCellValue(activo.getAnalisis_conf()==null?"SOBRANTE":activo.getAnalisis_conf());
					        		(rowData.createCell(16)).setCellValue(activo.getUlt_mov_fec());
					        		(rowData.createCell(17)).setCellValue(activo.getUlt_mov_tipo());
					        		
					        		countRow++;
								}
							}
						}
			        }
			    }
				
				
				// More about cell styles can be found on the Developer Guide page above.
				// create font
				//HSSFFont font = workbook.createFont();
				// create cell style that can be applied to cell or entire row
				//HSSFCellStyle style = workbook.createCellStyle();
				//style.setFont(font);
				//cell.setCellStyle(style);

				// write-out your workbook to the output stream
					
				
				response.setContentType("application/vnd.ms-excel");
			       workbook.write(response.getOutputStream());
			       response.getOutputStream().close();
				
			  	return;
	        
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	
	
	
}

}
