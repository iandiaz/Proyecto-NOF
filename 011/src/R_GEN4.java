

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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Constantes.Controlador;
import DAO.DireccionesDAO;
import DAO.EmpresasDAO;
import DAO.EstadosVigNoVigDAO;
import DAO.GruposDAO;
import DAO.ReportesDAO;
import VO.DireccionVO;
import VO.EmpresaVO;
import VO.GrupoVO;
import VO.UbicacionVO;

/**
 * Servlet implementation class R_GEN
 */
@WebServlet("/R_GEN4")
public class R_GEN4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_GEN4() {
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
		
			UbicacionVO ubi_ = new UbicacionVO();
			
			if(request.getParameter("select_grupo") !=  null && !request.getParameter("select_grupo").equals("") ){
				ubi_.setGrupo(GruposDAO.getGrupo(request.getParameter("select_grupo")));
			}
			if(request.getParameter("select_empresa") !=  null && !request.getParameter("select_empresa").equals("") ){
				ubi_.setEmpresa(EmpresasDAO.getEmpresa(request.getParameter("select_empresa")));
			}
			if(request.getParameter("select_direccion") !=  null && !request.getParameter("select_direccion").equals("") ){
				ubi_.setDireccion(DireccionesDAO.getDireccion(request.getParameter("select_direccion")));
			}
			if(request.getParameter("select_estado") !=  null && !request.getParameter("select_estado").equals("") ){
				ubi_.setEstadoVigNoVig(EstadosVigNoVigDAO.getEstadoVigNoVig(request.getParameter("select_estado")));
			}
			
			ArrayList<GrupoVO> rpt = ReportesDAO.getUbicacionesStruc(ubi_, Controlador.getUsuIDSession(request));
			
			/**
			 * PREGUNTAMOS PORQUE VIA LO GENERO
			 */
			
			
			if(request.getParameter("grabar_web") !=  null){
				request.setAttribute("usuname", Controlador.getUsunameSession(request));
		 	    request.setAttribute("rep", rpt);
		        request.setAttribute("ubi_filter", ubi_);
		        
		        RequestDispatcher a = request.getRequestDispatcher("R_GEN4.jsp");
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
				    
			        Paragraph titulo=new Paragraph("INFORME DE CONTROL: UBICACIONES",fontBolt); 
			        Paragraph usu=new Paragraph("GENERADO POR "+Controlador.getUsunameSession(request),fontNormal); 
			        
			        
			        String imageUrl = "http://186.67.10.115:81/NOF/img/logo2.png";

			        Image imageLogo = Image.getInstance(new URL(imageUrl));
			        
			        document.add(imageLogo);
			                
			        document.add(titulo);
			        
			        document.add(Chunk.NEWLINE);
			        
			        document.add(usu);
			        
			        document.add(Chunk.NEWLINE);
			        
		            List GRUPOS_List = new List(List.UNORDERED);
		            		        
			        for(GrupoVO grupo:rpt){
			        	Chunk titulo_grupo=new Chunk("GRUPO: ",fontBolt);//This gonna be bold font
			            Chunk data_grupo=new Chunk(grupo.getId()+" - "+grupo.getNombre(), fontNormal); //This gonna be normal font
			            Paragraph p_grupo=new Paragraph(); 
			            p_grupo.add(new Chunk(titulo_grupo));
			            p_grupo.add(new Chunk(data_grupo)); 
			        	
			        	ListItem grupoItem =new ListItem(p_grupo);
			        	GRUPOS_List.add(grupoItem);
			        
			        	List EMPRESAS_List = new List(List.UNORDERED);
			        	for(EmpresaVO empresa :grupo.getEmpresas()){
			        		
			        		Chunk titulo_empresa=new Chunk("EMPRESA: ",fontBolt);
				            Chunk data_empresa=new Chunk(empresa.getId()+" - "+empresa.getNombre_nof(), fontNormal);
				            Paragraph p_empresa=new Paragraph(); 
				            p_empresa.add(new Chunk(titulo_empresa));
				            p_empresa.add(new Chunk(data_empresa)); 
			        		
			        		ListItem empresaItem =new ListItem(p_empresa);
			        		EMPRESAS_List.add(empresaItem);
				        	
				        	List DIRECCION_List = new List(List.UNORDERED);
				        	for(DireccionVO direccion : empresa.getDirecciones()){
				        		
				        		Chunk titulo_direccion=new Chunk("DIRECCION: ",fontBolt);
					            Chunk data_direccion=new Chunk(direccion.getId()+" - "+direccion.getDireccion(), fontNormal);
					            Paragraph p_direccion=new Paragraph(); 
					            p_direccion.add(new Chunk(titulo_direccion));
					            p_direccion.add(new Chunk(data_direccion)); 
				        		
				        		ListItem direccionItem =new ListItem(p_direccion);
				        		DIRECCION_List.add(direccionItem);
				        		
				        		List UBICACION_List = new List(List.UNORDERED);
				        		for(UbicacionVO ubicacion: direccion.getUbicaciones()){
				        			
				        			Chunk titulo_ubicacion=new Chunk("UBICACION: ",fontBolt);
						            Chunk data_ubicacion=new Chunk(ubicacion.getId()+" - "+ubicacion.getNom_fisica()+" - "+ubicacion.getNom_facturacion()+" - "+ubicacion.getTipo_nombre()+" - "+ubicacion.getEstadoVigNoVig().getNombre(), fontNormal);
						            Paragraph p_ubicacion=new Paragraph(); 
						            p_ubicacion.add(new Chunk(titulo_ubicacion));
						            p_ubicacion.add(new Chunk(data_ubicacion)); 
					        		
				        			ListItem ubicacionItem =new ListItem(p_ubicacion);
				        			UBICACION_List.add(ubicacionItem);
					        		
				        		}
				        		direccionItem.add(UBICACION_List);
				        		
				        	}
				        	empresaItem.add(DIRECCION_List);
			        	}
			        	
			        	grupoItem.add(EMPRESAS_List);
			        	
			        }
			        
			        document.add(GRUPOS_List);
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
					(row1.createCell(0)).setCellValue("INFORME UBICACIONES"); //A3
					/**
					 * DATA ENCABEZADO
					 */
					
					
					HSSFRow row3 = sheet.createRow(3); // 0,1,2..
					//HSSFCell cell_A3 = row3.createCell(0); // 0,1,2...
					
					(row3.createCell(0)).setCellValue("GRUPO"); //A3
					(row3.createCell(1)).setCellValue("ID GRUPO"); 
					(row3.createCell(2)).setCellValue("EMPRESA");
					(row3.createCell(3)).setCellValue("EMPRESA ID"); 
					(row3.createCell(4)).setCellValue("DIRECCION"); 
					(row3.createCell(5)).setCellValue("DIRECCION ID"); 
					(row3.createCell(6)).setCellValue("UBICACION ID");
					(row3.createCell(7)).setCellValue("UBICACION FISICA");
					(row3.createCell(8)).setCellValue("UBICACION CONTABILIDAD");
					(row3.createCell(9)).setCellValue("TIPO");
					(row3.createCell(10)).setCellValue("ESTADO");
					
					
					/**
					 * DATA DETALLE
					 */
					int countRow=4;
					 for(GrupoVO grupo:rpt){
						 	
				         	for(EmpresaVO empresa : grupo.getEmpresas()){
				         		for(DireccionVO direccion : empresa.getDirecciones()){
				         			for(UbicacionVO ubicacion : direccion.getUbicaciones()){
				         				
				         				HSSFRow rowData = sheet.createRow(countRow); 
						        		(rowData.createCell(0)).setCellValue(grupo.getNombre());
						        		(rowData.createCell(1)).setCellValue(grupo.getId());
						        		(rowData.createCell(2)).setCellValue(empresa.getNombre_nof());
						        		(rowData.createCell(3)).setCellValue(empresa.getId());
						        		
						        		(rowData.createCell(4)).setCellValue(direccion.getDireccion());
						        		(rowData.createCell(5)).setCellValue(direccion.getId());
						        		(rowData.createCell(6)).setCellValue(ubicacion.getId());
						        		
						        		(rowData.createCell(7)).setCellValue(ubicacion.getNom_fisica());
						        		(rowData.createCell(8)).setCellValue(ubicacion.getNom_facturacion());
						        		
						        		(rowData.createCell(9)).setCellValue(ubicacion.getTipo_nombre());
						        		(rowData.createCell(10)).setCellValue(ubicacion.getEstadoVigNoVig().getNombre());
						        		
						        		countRow++;
				         			}
				         			
				         		}
				        		
				         		
				        	}
				        	
				        }
					
					
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
