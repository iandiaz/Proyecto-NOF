<%@page import="VO.ProductoVO"%>
<%@page import="VO.ConfEquipoDetalleVO"%>
<%@page import="VO.ConfEquipoVO"%>
<%@page import="VO.PedidoDetalleVO"%>
<%@page import="VO.PedidoVO"%>
<%@page import="VO.UbicacionVO"%>
<%@page import="VO.DireccionVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="VO.GrupoVO"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<ProductoVO> rep=(ArrayList<ProductoVO>)request.getAttribute("rep");
String usuname=(String)request.getAttribute("usuname");

ConfEquipoDetalleVO confdetalle_filter = (ConfEquipoDetalleVO)request.getAttribute("confdetalle_filter");
ConfEquipoVO conf_filter = (ConfEquipoVO)request.getAttribute("conf_filter");
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
<p><strong>INFORME DE CONTROL: CONFIGURACIONES POR PRODUCTO</strong></p>
<p>GENERADO POR <%=usuname %></p>

<p><strong>FILTROS UTILIZADOS:</strong></p>
<%if(confdetalle_filter.getFuncionalidad()!=null){%><p><b>FUNCIONALIDAD:</b> <%=confdetalle_filter.getFuncionalidad().getNombre() %></p> <%} %>
<%if(confdetalle_filter.getProducto()!=null){%><p><b>PRODUCTO:</b> <%=confdetalle_filter.getProducto().getPn() %></p> <%} %>
<%if(conf_filter.getId()!=null){%><p><b>CONFIGURACION:</b> <%=conf_filter.getNombre() %></p> <%} %>
   
   <% for(ProductoVO prod : rep){%>
   			<ul>
   				<li><b><%=prod.getId() %> - <%=prod.getPn() %> - <%=prod.getDesc_corto() %> - REND: <%=prod.getVida_util_imp() %></b> </li> 
   				<li type="none"><ul>
   						<%for(ConfEquipoVO conf : prod.getConfiguracionesEquipo()){%>
   							<li><%=conf.getNombre()%></li>
   						<%} %></ul></li>
   			</ul>
       		
   		<%}
   %>

</body>
</html>