<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleTecnoglobalProxyid" scope="session" class="webserver.TecnoglobalProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleTecnoglobalProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleTecnoglobalProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleTecnoglobalProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        webserver.Tecnoglobal getTecnoglobal10mtemp = sampleTecnoglobalProxyid.getTecnoglobal();
if(getTecnoglobal10mtemp == null){
%>
<%=getTecnoglobal10mtemp %>
<%
}else{
        if(getTecnoglobal10mtemp!= null){
        String tempreturnp11 = getTecnoglobal10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String fechadoc_1id=  request.getParameter("fechadoc16");
            java.lang.String fechadoc_1idTemp = null;
        if(!fechadoc_1id.equals("")){
         fechadoc_1idTemp  = fechadoc_1id;
        }
        String rut_2id=  request.getParameter("rut18");
            java.lang.String rut_2idTemp = null;
        if(!rut_2id.equals("")){
         rut_2idTemp  = rut_2id;
        }
        String sucursal_3id=  request.getParameter("sucursal20");
        int sucursal_3idTemp  = Integer.parseInt(sucursal_3id);
        String tipodoc_4id=  request.getParameter("tipodoc22");
        int tipodoc_4idTemp  = Integer.parseInt(tipodoc_4id);
        String estdoc_5id=  request.getParameter("estdoc24");
            java.lang.String estdoc_5idTemp = null;
        if(!estdoc_5id.equals("")){
         estdoc_5idTemp  = estdoc_5id;
        }
        String numdoc_6id=  request.getParameter("numdoc26");
        int numdoc_6idTemp  = Integer.parseInt(numdoc_6id);
        String linea_7id=  request.getParameter("linea28");
            java.lang.String linea_7idTemp = null;
        if(!linea_7id.equals("")){
         linea_7idTemp  = linea_7id;
        }
        String codigoarticulo_8id=  request.getParameter("codigoarticulo30");
            java.lang.String codigoarticulo_8idTemp = null;
        if(!codigoarticulo_8id.equals("")){
         codigoarticulo_8idTemp  = codigoarticulo_8id;
        }
        String partnumber_9id=  request.getParameter("partnumber32");
            java.lang.String partnumber_9idTemp = null;
        if(!partnumber_9id.equals("")){
         partnumber_9idTemp  = partnumber_9id;
        }
        String serie_10id=  request.getParameter("serie34");
            java.lang.String serie_10idTemp = null;
        if(!serie_10id.equals("")){
         serie_10idTemp  = serie_10id;
        }
        String descripcionarticulo_11id=  request.getParameter("descripcionarticulo36");
            java.lang.String descripcionarticulo_11idTemp = null;
        if(!descripcionarticulo_11id.equals("")){
         descripcionarticulo_11idTemp  = descripcionarticulo_11id;
        }
        String cantidadlinea_12id=  request.getParameter("cantidadlinea38");
        float cantidadlinea_12idTemp  = Float.parseFloat(cantidadlinea_12id);
        String preciounitario_13id=  request.getParameter("preciounitario40");
        float preciounitario_13idTemp  = Float.parseFloat(preciounitario_13id);
        String ordencompra_14id=  request.getParameter("ordencompra42");
            java.lang.String ordencompra_14idTemp = null;
        if(!ordencompra_14id.equals("")){
         ordencompra_14idTemp  = ordencompra_14id;
        }
        String notaventa_15id=  request.getParameter("notaventa44");
        int notaventa_15idTemp  = Integer.parseInt(notaventa_15id);
        String observacion_16id=  request.getParameter("observacion46");
            java.lang.String observacion_16idTemp = null;
        if(!observacion_16id.equals("")){
         observacion_16idTemp  = observacion_16id;
        }
        String codigomoneda_17id=  request.getParameter("codigomoneda48");
        int codigomoneda_17idTemp  = Integer.parseInt(codigomoneda_17id);
        String valormoneda_18id=  request.getParameter("valormoneda50");
        float valormoneda_18idTemp  = Float.parseFloat(valormoneda_18id);
        String glosamoneda_19id=  request.getParameter("glosamoneda52");
            java.lang.String glosamoneda_19idTemp = null;
        if(!glosamoneda_19id.equals("")){
         glosamoneda_19idTemp  = glosamoneda_19id;
        }
        java.lang.String inputFactOC13mtemp = sampleTecnoglobalProxyid.inputFactOC(fechadoc_1idTemp,rut_2idTemp,sucursal_3idTemp,tipodoc_4idTemp,estdoc_5idTemp,numdoc_6idTemp,linea_7idTemp,codigoarticulo_8idTemp,partnumber_9idTemp,serie_10idTemp,descripcionarticulo_11idTemp,cantidadlinea_12idTemp,preciounitario_13idTemp,ordencompra_14idTemp,notaventa_15idTemp,observacion_16idTemp,codigomoneda_17idTemp,valormoneda_18idTemp,glosamoneda_19idTemp);
if(inputFactOC13mtemp == null){
%>
<%=inputFactOC13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(inputFactOC13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>