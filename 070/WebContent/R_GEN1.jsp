<%@page import="VO.PedidoDetalleVO"%>
<%@page import="VO.PedidoVO"%>
<%@page import="VO.UbicacionVO"%>
<%@page import="VO.DireccionVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="VO.GrupoVO"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<PedidoVO> rep=(ArrayList<PedidoVO>)request.getAttribute("rep");
String usuname=(String)request.getAttribute("usuname");

PedidoVO pedi_filter = (PedidoVO)request.getAttribute("pedi_filter");
PedidoDetalleVO pedidet_filter = (PedidoDetalleVO)request.getAttribute("pedidet_filter");

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
<p><strong>INFORME DE CONTROL: PEDIDOS MAYORISTA</strong></p>
<p>GENERADO POR <%=usuname %></p>

<p><strong>FILTROS UTILIZADOS:</strong></p>
<p><% if(pedi_filter.getGrupoCliente()!=null){out.print("<b>GRUPO CLIENTE:</b> "+pedi_filter.getGrupoCliente().getNombre()+"<br>");} %>
	<% if(pedi_filter.getCliente()!=null){out.print("<b>CLIENTE:</b> "+pedi_filter.getCliente().getNombre_nof()+"<br>");} %>
	<% if(pedi_filter.getDireccion()!=null){out.print("<b>DIRECCION:</b> "+pedi_filter.getDireccion().getDireccion()+"<br>");} %>
	<% if(pedi_filter.getEstadoVigNoVig()!=null){out.print("<b>ESTADO:</b> "+pedi_filter.getEstadoVigNoVig().getNombre()+"<br>");} %></p>
	<% if(pedidet_filter.getProducto()!=null){out.print("<b>PRODUCTO:</b> "+pedidet_filter.getProducto().getId()+"<br>");} %></p>
   
   <%
   		for(PedidoVO pedido : rep){%>
   			<ul>
   				<li><%=pedido.getId() %> - <%=pedido.getCliente().getNombre_nof() %> - <%=pedido.getProveedor().getNombre_nof() %> - <%=pedido.getDireccion().getDireccion() %> - <%=pedido.getEstado_p() %> - <%=pedido.getFec_crea()%> - <%=pedido.getEstadoVigNoVig().getNombre()%> </li> 
   				<li type="none"><ul>
   						<%for(PedidoDetalleVO detPedido : pedido.getDetallePedido()){%>
   							<li>ID PROD: <%=detPedido.getProducto().getId() %> - <%=detPedido.getProducto().getDesc_corto() %> - <%=detPedido.getProducto().getPn() %> - CANT: <%=detPedido.getCantidad() %> - <%=detPedido.getUbicacion().getNom_fisica() %> - TICKET: <%=detPedido.getId_ticket() %></li>
   						<%} %></ul></li>
   			</ul>
       		
   		<%}
   %>

</body>
</html>