<%@page import="VO.UbicacionVO"%>
<%@page import="VO.DireccionVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="VO.GrupoVO"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<GrupoVO> rep=(ArrayList<GrupoVO>)request.getAttribute("rep");
String usuname=(String)request.getAttribute("usuname");

UbicacionVO ubi_filter = (UbicacionVO)request.getAttribute("ubi_filter");
%>
<!DOCTYPE html>
<html lang="en">
<head>
 
    <meta charset="utf-8">
    <title>New Office</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
  <style>

	table{
			font-family: Consolas, "Andale Mono", "Lucida Console", "Lucida Sans Typewriter", Monaco, 			"Courier New", monospace;
		}
	.green{
		color: #090;
		}
		td,th{
			
			text-transform:uppercase;}
		li{
			font-family: sans-serif; 
			font-size:12px;
			margin-left:0px;
			padding-left:0px;
			}
			ul{
				font-family: sans-serif; 
				padding-left:1px;
				margin-left:15px;
				}
	
</style>
  
  </head>
 
  <body class=""> 
  
<table border="0">
  <tr>
    <td width="80" ><img src="http://186.67.10.115:81/NOF/img/logo2.png" height="80px"   alt=""/></td>
    
    
  </tr>
 
</table>
<p><strong>INFORME DE CONTROL: UBICACIONES</strong></p>
<p>GENERADO POR <%=usuname %></p>

<p><strong>FILTROS UTILIZADOS:</strong></p>
<p><% if(ubi_filter.getGrupo()!=null){out.print("<b>GRUPO:</b> "+ubi_filter.getGrupo().getNombre()+"<br>");} %>
	<% if(ubi_filter.getEmpresa()!=null){out.print("<b>EMPRESA:</b> "+ubi_filter.getEmpresa().getNombre_nof()+"<br>");} %>
	<% if(ubi_filter.getDireccion()!=null){out.print("<b>DIRECCION:</b> "+ubi_filter.getDireccion().getDireccion()+"<br>");} %>
	<% if(ubi_filter.getEstadoVigNoVig()!=null){out.print("<b>ESTADO:</b> "+ubi_filter.getEstadoVigNoVig().getNombre()+"<br>");} %></p>
   
   <%
   		for(GrupoVO grupo : rep){%>
   			<ul>
   				<li><strong>GRUPO:</strong> <%=grupo.getId() %> - <%=grupo.getNombre() %></li> 
   				<li type="none"><ul>
   				<%for(EmpresaVO empresa : grupo.getEmpresas()){%>
   					<li><strong>EMPRESA:</strong> <%=empresa.getId() %> - <%=empresa.getNombre_nof() %></li>
   					<li type="none"><ul>
   					<%for(DireccionVO direccion : empresa.getDirecciones()){%>
   						<li><strong>DIRECCION:</strong> <%=direccion.getId() %> - <%=direccion.getDireccion() %></li>
   						<li type="none"><ul>
   						<%for(UbicacionVO ubicacion : direccion.getUbicaciones()){%>
   							<li><strong>UBICACION:</strong> <%=ubicacion.getId() %> - <%=ubicacion.getNom_fisica() %> - <%=ubicacion.getNom_facturacion() %> - <%=ubicacion.getTipo_nombre() %> - <%=ubicacion.getEstadoVigNoVig().getNombre() %></li>
   						<%} %></ul></li>
   					<%} %></ul></li>
   				<%} %>
   				</ul></li>
   			</ul>
       		
   		<%}
   %>

</body>
</html>