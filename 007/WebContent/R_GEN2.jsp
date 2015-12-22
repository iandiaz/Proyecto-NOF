<%@page import="VO.UsuarioVO"%>
<%@page import="VO.UbicacionVO"%>
<%@page import="VO.DireccionVO"%>
<%@page import="VO.EmpresaVO"%>
<%@page import="VO.GrupoVO"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<UsuarioVO> rep=(ArrayList<UsuarioVO>)request.getAttribute("rep");
String usuname=(String)request.getAttribute("usuname");

EmpresaVO empresa_filter = (EmpresaVO)request.getAttribute("empresa_filter");
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
<p><strong>INFORME DE CONTROL: REPORTE CLIENTE-PROVEEDOR</strong></p>
<p>GENERADO POR <%=usuname %></p>

<p><strong>FILTROS UTILIZADOS:</strong></p>
<p><% if(empresa_filter.getGrupo()!=null){out.print("<b>GRUPO:</b> "+empresa_filter.getGrupo().getNombre()+"<br>");} %>
	<% if(empresa_filter.getNombre_nof()!=null){out.print("<b>EMPRESA:</b> "+empresa_filter.getNombre_nof()+"<br>");} %>
	
	<% if(empresa_filter.getEstadoVigNoVig()!=null){out.print("<b>ESTADO:</b> "+empresa_filter.getEstadoVigNoVig().getNombre()+"<br>");} %>
	<% if(empresa_filter.getEscliente()!=null){out.print("<b>ES CLIENTE:</b> SI<br>");} %>
	<% if(empresa_filter.getEsproveedor()!=null){out.print("<b>ES PROVEEDOR:</b> SI<br>");} %>
	<% if(empresa_filter.getEsprospeccion()!=null){out.print("<b>ES PROSPECCION:</b> SI<br>");} %>
	</p>
   
   <%
   		for(UsuarioVO usuario : rep){%>
   			<ul>
   				<li><strong>RESPONSABLE CUENTA:</strong> <%=usuario.getId() %> - <%=usuario.getNombre() %> <%=usuario.getApe_p() %> <%=usuario.getApe_m() %></li> 
   				<li type="none"><ul>
   				<%for(EmpresaVO empresa : usuario.getEmpresas()){%>
   					<li><%=empresa.getId() %> - <%=empresa.getNombre_nof() %> 
   						ES CLI:<%=empresa.getEscliente().equals("1")?"SI":"NO" %> 
   							ES PRO:<%=empresa.getEsproveedor().equals("1")?"SI":"NO" %> 
   								ES PROS:<%=empresa.getEsprospeccion().equals("1")?"SI":"NO" %> 
   										- <%=empresa.getEstadoVigNoVig().getNombre() %></li>
   				<%} %>
   				</ul></li>
   			</ul>
       		
   		<%}
   %>

</body>
</html>