<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String Usu_nom=(String)request.getAttribute("usuname");
    String[] cal_ar=(String[])request.getAttribute("cal_ar");
    String year_actual=(String)request.getAttribute("year_actual");
    String mes_actual=(String)request.getAttribute("mes_actual");
    
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <% 
  if(request.getParameter("mExito")!=null && !request.getParameter("mExito").equals("")){
  		if(request.getParameter("mExito").equals("OK")){
			out.println("<script>alert('OPERACI\u00d3N REALIZADA CON \u00c9XITO')</script>");
			//out.println("<script>window.location = '/001/'</script>");
		} 
	} 
	%>
    <meta charset="utf-8">
    <title>New Office - Calendario</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
   <link rel="stylesheet" type="text/css" href="stylesheets/butons.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/inputs.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->
	<style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
		
  	tbody {
	  
	    height: 600px;
	    overflow-y: scroll;
	}
	thead{
        
    }
    .cal>tbody>tr>td{
        width: 60px;
        height: 60px;
        
    }
	tr{
		 
	}
	tr.hov:hover{
		opacity:0.5;
		cursor: pointer;
	}
    .colorGris{
        background-color: #CCC;
    }
    .colorRojo{
        background-color: #FA5858;
    }
    .color{
		background-color: #9FF781;
	}
    
    .cal{
        float: left;
        margin-left: 5px;
    }
    .hrs{
        vertical-align: bottom;
        font-size: 8px;
        height: 70%;
        border-top: 1px solid #fff;
        
    }
    .hrs p{
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        height: 20px;
        
    }
    .inputCal{
        width: 50px;
        font-size: 14px;
        height: 20px;
    }
    @media screen and (min-width: 700px) {
		.cuadroblanco{
			box-shadow:0px 2px 5px 0px rgba(50, 50, 50, 0.75);
            border: 1px solid black;
			min-height:100%;
			height:500px;
			min-width: 350px;
			max-width:430px;
            position: relative;
            background:#ccc;
            margin: 0 auto;
		}
		.inputMovil{
			width:100%;
		}
        
	}
  
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    
   <script>
   $(document).ready(function() {

       $("#searchInput").keyup(function(){
 	//hide all the rows
           $("#fbody").find("tr").hide();

 	//split the current value of searchInput
           var data = this.value;//.split(" ");
 	//create a jquery object of the rows
           var jo = $("#fbody").find("tr");
           
 	//Recusively filter the jquery object to get results.
          // $.each(data, function(i, v){
               jo = jo.filter("*:contains('"+data.toUpperCase()+"')");
           //});
         //show the rows that match.
           jo.show();
      //Removes the placeholder text  
    
       }).focus(function(){
       //    this.value="";
           $(this).css({"color":"black"});
           $(this).unbind('focus');
       }).css({"color":"#000"});
                     
                     
                     
        $("#calendar td").click(function(){
                                                                var texto = $(this).text();
                                                              
                                                                if(texto.trim() != ""){
                                
                                                                texto=texto.substring(0,2);
                                                                $(this).toggleClass("color colorRojo");
                                                               
                                                                if($('#'+texto.trim()).val().indexOf('novacio')!=-1){
                                                                    var valueText= $('#'+texto.trim()).val().replace('novacio','vacio');
                                                               
                                                                    $('#'+texto.trim()).val(valueText);
                                
                                                                    $("#"+texto.trim()+"_hi").hide();
                                                                    $("#"+texto.trim()+"_hf").hide();
                                                               
                                                                }
                                                                else if($('#'+texto.trim()).val().indexOf('vacio')!=-1){
                                                                    var valueText= $('#'+texto.trim()).val().replace('vacio','novacio');
                                                               
                                                                    $('#'+texto.trim()).val(valueText);
                                                                    //$('#aioConceptName :selected').text();
                                                                    var ar_dataday=valueText.split("/");
                                                                    //alert(''+ar_dataday[3]);
                                
                                                                    //$("#hfin").attr('disabled','disabled');
                                
                                                                    $("#"+texto.trim()+"_hi").show();
                                                                    $("#"+texto.trim()+"_hf").show();
                                
                                
                                
                                

                                                                }
                                                               }
                                                               
                                                              
        });
                     
                     $('#hini').on('change', function() {
                                   //alert('asignando');
                                   var dia = $('#diaselec').text();
                                   var data= $('#'+dia.trim()).val();
                                   var ar_dataday=data.split("/");
                                   ar_dataday[2]=$(this).val();
                                   $('#'+dia.trim()).val(ar_dataday[0]+"/"+ar_dataday[1]+"/"+ar_dataday[2]+"/"+ar_dataday[3]);
                                   
                    });
                     
                     $('#hfin').on('change', function() {
                                   //alert('asignando');
                                   var dia = $('#diaselec').text();
                                   var data= $('#'+dia.trim()).val();
                                   var ar_dataday=data.split("/");
                                   ar_dataday[3]=$(this).val();
                                   $('#'+dia.trim()).val(ar_dataday[0]+"/"+ar_dataday[1]+"/"+ar_dataday[2]+"/"+ar_dataday[3]);
                                   
                    });
                     
                     
                     
                     $('input').click( function() {
                                  //    alert('click');
                                  
                                  // do stuff here when not disabled
                                  return false; // so your '#' href doesn't get used
                                  } );

   });

   </script>
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  
  <body class=""> 
  <!--<![endif]-->
  
  
  
    <div class="navbar">
                        
                     
                     <div class="brand"><img src="lib/bootstrap/img/logonew_big.png"></div>
                     <div class="version" align="right">
                     <p>N998.M.01.001</p>
                     </div>
                    <form method="get" action="Musuario" style="margin: 0px 0px 0px 0px;">                 
                    <button type="submit" name="logout" id="logout" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">CERRAR SESI&Oacute;N</button>
                    </form>
                    <button type="button" onclick="location='/998/'" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:5px;">VOLVER</button>
                   
                    <div align="center" class="title" >
                    <p >MODIFICAR CALENDARIO</p>
                    </div>
                    
               
                             
    </div>
        

  <div class="content">
      <form action="Mcal" method="post" >
         <div class=" cuadroblanco" >
             <div style="margin-left:5px;">
            
            MES:<select name="mes">
                <option value="1" <% if(mes_actual.equals("1")){%> selected <% } %> >ENERO</option>
                <option value="2" <% if(mes_actual.equals("2")){%> selected <% } %>>FEBRERO</option>
                <option value="3" <% if(mes_actual.equals("3")){%> selected <% } %>>MARZO</option>
                <option value="4" <% if(mes_actual.equals("4")){%> selected <% } %>>ABRIL</option>
                <option value="5" <% if(mes_actual.equals("5")){%> selected <% } %>>MAYO</option>
                <option value="6" <% if(mes_actual.equals("6")){%> selected <% } %>>JUNIO</option>
                <option value="7" <% if(mes_actual.equals("7")){%> selected <% } %>>JULIO</option>
                <option value="8" <% if(mes_actual.equals("8")){%> selected <% } %>>AGOSTO</option>
                <option value="9" <% if(mes_actual.equals("9")){%> selected <% } %>>SEPTIEMBRE</option>
                <option value="10" <% if(mes_actual.equals("10")){%> selected <% } %>>OCTUBRE</option>
                <option value="11" <% if(mes_actual.equals("11")){%> selected <% } %>>NOVIEMBRE</option>
                <option value="12" <% if(mes_actual.equals("12")){%> selected <% } %>>DICIEMBRE</option>
            </select>
            A&Ntilde;O:<select name="year">
                <option value="2010" <% if(year_actual.equals("2010")){%> selected <% } %> >2010</option>
                <option value="2011" <% if(year_actual.equals("2011")){%> selected <% } %>>2011</option>
                <option value="2012" <% if(year_actual.equals("2012")){%> selected <% } %>>2012</option>
                <option value="2013" <% if(year_actual.equals("2013")){%> selected <% } %>>2013</option>
                <option value="2014" <% if(year_actual.equals("2014")){%> selected <% } %>>2014</option>
                <option value="2015" <% if(year_actual.equals("2015")){%> selected <% } %>>2015</option>
                <option value="2016" <% if(year_actual.equals("2016")){%> selected <% } %>>2016</option>
                <option value="2017" <% if(year_actual.equals("2017")){%> selected <% } %>>2017</option>
                <option value="2018" <% if(year_actual.equals("2018")){%> selected <% } %>>2018</option>
                <option value="2019" <% if(year_actual.equals("2019")){%> selected <% } %>>2019</option>
                <option value="2020" <% if(year_actual.equals("2020")){%> selected <% } %>>2020</option>
                
            </select>
            <button type="submit" name="buscar" id="buscar"  class="btn btn-success">BUSCAR</button>
            </div>
         <table border="1" class="cal">
         	<thead>
            <tr>
         		<td>LU</td>
         		<td>MA</td>
         		<td>MI</td>
         		<td>JU</td>
         		<td>VI</td>
         		<td>SA</td>
                <td>DO</td>
         	</tr>
            </thead>
            <tbody id="calendar">
            <tr>
                
                <%
                    
                    for(int x=0; x<7;x++){
                    
                        String msg="";
                        String marcado="";
                        String hi="";
                        String hf="";
                        String[] parsecal = cal_ar[x].split("/"); if(parsecal[0].equals("0")){msg="";marcado="class='colorGris'";}else if(parsecal[0].equals("vacio")){msg=parsecal[1];marcado="class='colorRojo'";}else if(parsecal[0].equals("novacio")){marcado="class='color'";msg=parsecal[1];
                            if(parsecal[2].length()>5){hi=parsecal[2].substring(0,5);}
                                else {hi=parsecal[2];} if(parsecal[3].length()>5){hf=parsecal[3].substring(0,5);}else {hf=parsecal[3];}} %>
                
                <td <%=marcado%>><div style="vertical-align: top;height: 30%;" ><%=msg%></div> <% if(!msg.equals("")){ %> <div class="hrs"><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hi" id="<%=msg%>_hi" class="inputCal" value="<%=hi%>"></p><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hf" id="<%=msg%>_hf" class="inputCal" value="<%=hf%>"></p></div> <% } %> <input type="hidden" name="<%=msg%>" id="<%=msg%>" value="<%=cal_ar[x]%>"> </td>
                
                    
                <%    }
                    %>
                
            </tr>
         	<tr>
                
                
                <%
                    
                    for(int x=7; x<14;x++){
                        
                        String msg="";
                        String marcado="";
                        String hi="";
                        String hf="";
                        String[] parsecal = cal_ar[x].split("/"); if(parsecal[0].equals("0")){msg="";marcado="class='colorGris'";}else if(parsecal[0].equals("vacio")){msg=parsecal[1];marcado="class='colorRojo'";}else if(parsecal[0].equals("novacio")){marcado="class='color'";msg=parsecal[1];
                            if(parsecal[2].length()>5){hi=parsecal[2].substring(0,5);}
                                else {hi=parsecal[2];} if(parsecal[3].length()>5){hf=parsecal[3].substring(0,5);}else {hf=parsecal[3];}} %>
                
                <td <%=marcado%>><div style="vertical-align: top;height: 30%;" ><%=msg%></div> <% if(!msg.equals("")){ %> <div class="hrs"><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hi" id="<%=msg%>_hi" class="inputCal" value="<%=hi%>"></p><p><input type="text" <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> name="<%=msg%>_hf" id="<%=msg%>_hf" class="inputCal" value="<%=hf%>"></p></div> <% } %> <input  type="hidden" name="<%=msg%>" id="<%=msg%>" value="<%=cal_ar[x]%>"> </td>
                
                
                <%    }
                    %>

            </tr>
         	<tr>
                
                <%
                    
                    for(int x=14; x<21;x++){
                        
                        String msg="";
                        String marcado="";
                        String hi="";
                        String hf="";
                        String[] parsecal = cal_ar[x].split("/"); if(parsecal[0].equals("0")){msg="";marcado="class='colorGris'";}else if(parsecal[0].equals("vacio")){msg=parsecal[1];marcado="class='colorRojo'";}else if(parsecal[0].equals("novacio")){marcado="class='color'";msg=parsecal[1];
                            if(parsecal[2].length()>5){hi=parsecal[2].substring(0,5);}
                                else {hi=parsecal[2];} if(parsecal[3].length()>5){hf=parsecal[3].substring(0,5);}else {hf=parsecal[3];}} %>
                
                <td <%=marcado%>><div style="vertical-align: top;height: 30%;" ><%=msg%></div><% if(!msg.equals("")){ %> <div class="hrs"><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hi" id="<%=msg%>_hi" class="inputCal" value="<%=hi%>"></p><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hf" id="<%=msg%>_hf" class="inputCal" value="<%=hf%>"></p></div> <% } %> <input type="hidden" name="<%=msg%>" id="<%=msg%>" value="<%=cal_ar[x]%>"> </td>
                
                
                <%    }
                    %>
                
            </tr>
         	<tr>
                <%
                    
                    for(int x=21; x<28;x++){
                        
                        String msg="";
                        String marcado="";
                        String hi="";
                        String hf="";
                        String[] parsecal = cal_ar[x].split("/"); if(parsecal[0].equals("0")){msg="";marcado="class='colorGris'";}else if(parsecal[0].equals("vacio")){msg=parsecal[1];marcado="class='colorRojo'";}else if(parsecal[0].equals("novacio")){marcado="class='color'";msg=parsecal[1];
                            if(parsecal[2].length()>5){hi=parsecal[2].substring(0,5);}
                                else {hi=parsecal[2];} if(parsecal[3].length()>5){hf=parsecal[3].substring(0,5);}else {hf=parsecal[3];}} %>
                
                <td <%=marcado%>><div style="vertical-align: top;height: 30%;" ><%=msg%></div><% if(!msg.equals("")){ %> <div class="hrs"><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hi" id="<%=msg%>_hi" class="inputCal" value="<%=hi%>"></p><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hf" id="<%=msg%>_hf" class="inputCal" value="<%=hf%>"></p></div> <% } %> <input type="hidden" name="<%=msg%>" id="<%=msg%>" value="<%=cal_ar[x]%>"> </td>
                
                
                <%    }
                    %>

            </tr>
         	<tr>
                <%
                    
                    for(int x=28; x<35;x++){
                        
                        String msg="";
                        String marcado="";
                        String hi="";
                        String hf="";
                        String[] parsecal = cal_ar[x].split("/"); if(parsecal[0].equals("0")){msg="";marcado="class='colorGris'";}else if(parsecal[0].equals("vacio")){msg=parsecal[1];marcado="class='colorRojo'";}else if(parsecal[0].equals("novacio")){marcado="class='color'";msg=parsecal[1];
                            if(parsecal[2].length()>5){hi=parsecal[2].substring(0,5);}
                                else {hi=parsecal[2];} if(parsecal[3].length()>5){hf=parsecal[3].substring(0,5);}else {hf=parsecal[3];}} %>
                
                <td <%=marcado%>><div style="vertical-align: top;height: 30%;" ><%=msg%></div><% if(!msg.equals("")){ %> <div class="hrs"><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hi" id="<%=msg%>_hi" class="inputCal" value="<%=hi%>"></p><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hf" id="<%=msg%>_hf" class="inputCal" value="<%=hf%>"></p></div> <% } %> <input type="hidden" name="<%=msg%>" id="<%=msg%>" value="<%=cal_ar[x]%>"> </td>
                
                
                <%    }
                    %>
               
            </tr>
         	<tr>
                <%
                    
                    for(int x=35; x<42;x++){
                        
                        String msg="";
                        String marcado="";
                        String hi="";
                        String hf="";
                        String[] parsecal = cal_ar[x].split("/"); if(parsecal[0].equals("0")){msg="";marcado="class='colorGris'";}else if(parsecal[0].equals("vacio")){msg=parsecal[1];marcado="class='colorRojo'";}else if(parsecal[0].equals("novacio")){marcado="class='color'";msg=parsecal[1];
                            if(parsecal[2].length()>5){hi=parsecal[2].substring(0,5);}
                                else {hi=parsecal[2];} if(parsecal[3].length()>5){hf=parsecal[3].substring(0,5);}else {hf=parsecal[3];}} %>
                
                <td <%=marcado%>><div style="vertical-align: top;height: 30%;" ><%=msg%></div><% if(!msg.equals("")){ %> <div class="hrs"><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hi" id="<%=msg%>_hi" class="inputCal" value="<%=hi%>"></p><p><input <% if(parsecal[0].equals("vacio")){ %> style="display:none" <% } %> type="text" name="<%=msg%>_hf" id="<%=msg%>_hf" class="inputCal" value="<%=hf%>"></p></div> <% } %> <input type="hidden" name="<%=msg%>" id="<%=msg%>" value="<%=cal_ar[x]%>"> </td>
                
                
                <%    }
                    %>
                
                
            </tr>
         
         	</tbody>
         	
         </table>
         
         
         
         <div class="bgrabar">
             
             <button type="submit" name="grabar" id="grabar"  class="btn btn-success">GRABAR</button>
         </div>
         </div>
         
         </form>
        
    </div>
				<div class="footerapp">
               
                  <p style="float: left;"><i class="icon-user"></i>
                  <strong><%=Usu_nom %></strong></p>
                  <button type="button" onclick="window.open('/003/','_blank')" class="btn btn-danger pull-right" style="margin-right: 5px; margin-top:2px;">MENU</button>
                </div>

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
		
    </script>
    
  </body>
</html>