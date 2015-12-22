<%@page import="VO.ActivoVO"%>
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
ArrayList<GrupoVO> rep=(ArrayList<GrupoVO>)request.getAttribute("rep");
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
<p><strong>INFORME DE CONTROL: COMPARACION UBICACIONES / CONFIGURACIONES</strong></p>
<p>GENERADO POR <%=usuname %></p>

<p><strong>FILTROS UTILIZADOS:</strong></p>

<% for(GrupoVO grupo : rep){
	if(grupo.getEmpresas().size()==0) continue;%>
<ul>
   	<li><b>GRUPO:</b><%=grupo.getId() %> - <%=grupo.getNombre() %> </li> 
   	<li type="none"><ul>
	<% for(EmpresaVO empresa : grupo.getEmpresas()){%>
	<ul>
	   	<li><b>EMPRESA:</b><%=empresa.getId() %> - <%=empresa.getNombre_nof() %> </li> 
	   	<li type="none"><ul>
   		<% for(DireccionVO dir : empresa.getDirecciones()){%>
   		<ul>
	   		<li><b>DIRECCION:</b><%=dir.getId() %> - <%=dir.getDireccion() %> - <%=dir.getComu_nombre() %> - <%=dir.getRegi_nombre() %></li> 
	   		<li type="none"><ul>
   			<% for(UbicacionVO ubi : dir.getUbicaciones()){%>
	   			<ul>
	   				<li><b>UBICACION:</b><%=ubi.getId() %> - <%=ubi.getNom_fisica() %> - <%=ubi.getTipo_nombre() %> - <%=ubi.getConf_equipo()==null?"":ubi.getConf_equipo().getNombre() %>  </li> 
	   				<li type="none"><ul>
	   						<%for(ActivoVO activo : ubi.getActivos()){
	   							String color ="red";
	   							if(activo.getAnalisis_conf()!=null && activo.getAnalisis_conf().equals("OK")) color="green";
	   							if(activo.getAnalisis_conf()!=null && activo.getAnalisis_conf().equals("FALTANTE")) color="blue";
	   						%>
	   							<li><b><%=activo.getProducto().getFuncionalidad().getNombre()%> 
	   									- <%=activo.getProducto().getPn()%> - <%=activo.getId()%> 
	   									- <%=activo.getProducto().getDesc_corto()%> 
	   									- REND:<%=activo.getProducto().getVida_util_imp()%> 
	   									- <span style="color:<%=color%>"><%=activo.getAnalisis_conf()==null?"SOBRANTE":activo.getAnalisis_conf() %></span> 
	   									- <%=activo.getUlt_mov_fec()==null?"": activo.getUlt_mov_fec()%> 
	   									<%=activo.getUlt_mov_tipo()==null?"":activo.getUlt_mov_tipo() %></b></li>
	   						<%} %></ul></li>
	   			</ul>
       		
   			<%} %></ul></li>
   		</ul>
   		<%} %></ul></li>
   	</ul>
   <%} %></ul></li>
</ul>
<%} %>

</body>
</html>