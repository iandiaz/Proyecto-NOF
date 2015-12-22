package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import VO.AnexoContratoVO;
import VO.EmpresaVO;
import VO.EstadosVigNoVigVO;
import VO.EstructuraCobroVO;
import VO.FacturaDetalleVO;
import VO.FacturaVO;
import VO.MaquinaContadorVO;
import VO.PeriodoTcVO;
import VO.RangoEstructuraCobroVO;
import VO.TipoCambioVO;
import VO.TipoEstcobroVO;

public class FacturacionDAO {
	static Connection conexion=null;
	static Statement state=null;
	
	private static void getCon() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			 conexion=(Connection) DriverManager.getConnection
		    		("jdbc:mysql://"+Constantes.SERVER+"/"+Constantes.CATALOGO,Constantes.USER,Constantes.PASS);
			 state = (Statement) ((java.sql.Connection) conexion).createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
	}
	
	public static String insertFactura(FacturaVO factura){
		getCon();
		
		String id=null;
		
		String SQL="INSERT INTO `factura` ("
				+ "			fact_fecha"
				+ "			,fact_condpago"
				+ "			,fact_id_emisor"
				+ "			,fact_id_cliente"
				+ "			,fact_cliente_rz"
				+ "			,fact_cliente_rut"
				+ "			,dire_id"
				+ "			,fact_dir_direccion"
				+ "			,fact_dir_region"
				+ "			,fact_dir_ciudad"
				+ "			,fact_dir_comuna"
				+ "			,peri_tc_id"
				+ "			,fact_subtotal"
				+ "			,fact_desc"
				+ "			,fact_neto"
				+ "			,fact_iva"
				+ "			,fact_total"
				+ "			,fact_n_imp"
				+ "			,fact_tcambio"
				+ "			,fact_observaciones"
				+ "			,tipo_cambio_id"
				+ "			,fact_glosa_desc"
				+ "	) " + 
				"VALUES ( "
				+ "'"+factura.getFecha()+"'	"
				+ ",'"+factura.getCondpago()+"'	"
				+ ",'"+factura.getId_emisor()+"'"
				+ ",'"+factura.getId_cliente()+"'"
				+ ",'"+factura.getCliente_rz()+"'"
				+ ",'"+factura.getCliente_rut()+"'"
				
				+ ",'"+factura.getDire_id()+"'"
				+ ",'"+factura.getDir_direccion()+"'"
				+ ",'"+factura.getDir_region()+"'"
				+ ",'"+factura.getDir_ciudad()+"'"
				+ ",'"+factura.getDir_comuna()+"'"
				+ ",'"+factura.getPeri_tc_id()+"'"
				
				+ ",'"+factura.getSubtotal()+"'"
				+ ",'"+factura.getDesc()+"'"
				+ ",'"+factura.getNeto()+"'"
				+ ",'"+factura.getIva()+"'"
				+ ",'"+factura.getTotal()+"'"
				
				+ ",'"+factura.getN_imp()+"'"
				+ ",'"+factura.getTcambio()+"'"
				+ ",'"+factura.getObservaciones()+"'"
				+ ",'"+factura.getTipo_cambio().getId()+"'"
				+ ",'"+factura.getGlosa_desc()+"'"
				+ ")";
		
		System.out.println(SQL);
		try {
			state.executeUpdate(SQL,Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = null;
	    	generatedKeys = state.getGeneratedKeys();
	    	if (generatedKeys.next()) {
	    		id=generatedKeys.getString(1);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    disconect();
	    
	    return id;
	}
	
	public static void updateFactura(FacturaVO factura){
		getCon();
		
		String SQL="UPDATE `factura`  SET "
				+ "			fact_fecha='"+factura.getFecha()+"'"
				+ "			,fact_condpago='"+factura.getCondpago()+"' "
				+ "			,fact_id_emisor='"+factura.getId_emisor()+"' "
				+ "			,fact_id_cliente='"+factura.getId_cliente()+"' "
				+ "			,fact_cliente_rz='"+factura.getCliente_rz()+"' "
				+ "			,fact_cliente_rut='"+factura.getCliente_rut()+"' "
				+ "			,dire_id='"+factura.getDire_id()+"' "
				+ "			,fact_dir_direccion='"+factura.getDir_direccion()+"' "
				+ "			,fact_dir_region='"+factura.getDir_region()+"' "
				+ "			,fact_dir_ciudad='"+factura.getDir_ciudad()+"' "
				+ "			,fact_dir_comuna='"+factura.getDir_comuna()+"' "
				+ "			,peri_tc_id='"+factura.getPeri_tc_id()+"' "
				+ "			,fact_subtotal='"+factura.getSubtotal()+"' "
				+ "			,fact_desc='"+factura.getDesc()+"' "
				+ "			,fact_neto='"+factura.getNeto()+"' "
				+ "			,fact_iva='"+factura.getIva()+"' "
				+ "			,fact_total='"+factura.getTotal()+"' "
				+ "			,fact_n_imp='"+factura.getN_imp()+"' "
				+ "			,fact_tcambio='"+factura.getTcambio()+"' "
				+ "			,fact_observaciones='"+factura.getObservaciones()+"' "
				+ "			,tipo_cambio_id='"+factura.getTipo_cambio().getId()+"' "
				+ "			,fact_glosa_desc='"+factura.getGlosa_desc()+"' "
				+ "	WHERE id_fact='"+factura.getId()+"'  "; 
		 
		System.out.println(SQL);
		try {
			state.executeUpdate(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    disconect();
	    
	}
	
	public static void toggleFactura(String id_factura,String estado){
		getCon();
		
		
		String SQL="UPDATE `factura` SET estados_vig_novig_id="+estado+" WHERE id_fact="+id_factura;
			
		
		System.out.println(SQL);
		try {
			state.executeUpdate(SQL);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    disconect();
	 
	}
	
	
	public static void deleteDetalleFactura(FacturaVO factura){
		getCon();
		
		//antes de insertar detalle, borramos los detalles anteriores que puedan existir 
		
		String SQL_DELETE="UPDATE detalle_factura SET estados_vig_novig_id=2 WHERE id_fact="+factura.getId();
		System.out.println(SQL_DELETE);
		
		try {
			state.executeUpdate(SQL_DELETE);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	    disconect();
	    
	    
	}
	public static void insertDetalleFactura(FacturaDetalleVO facturaDetalle){
		getCon();
		
		String precioCV="0";
		if(facturaDetalle.getPrecioCV()!=null) precioCV=facturaDetalle.getPrecioCV();
		
		String SQL="INSERT INTO `detalle_factura` ("
				+ "			ubi_id"
				+ "			,id_activo"
				+ "			,detallefact_nimp"
				+ "			,detallefact_precioCF"
				+ "			,detallefact_precioCV"
				+ "			,id_fact"
				+ "			,estrcobro_id"
				+ "			,anc_id"
				+ "			,detallefact_descripcion"

				
				+ "			,es_activo"
				+ "			,es_anexo"
				+ "			,es_estructuracobro"
				
				+ "	) " + 
				"VALUES ( "
				+ ""+facturaDetalle.getUbi_id()+"	"
				+ ","+facturaDetalle.getId_activo()+"	"
				+ ","+facturaDetalle.getNimp()+""
				+ ",'"+facturaDetalle.getPrecioCF()+"'"
				+ ",'"+precioCV+"'"
				+ ",'"+facturaDetalle.getId_fact()+"'"
				+ ","+facturaDetalle.getEstrcobro_id()+""
				+ ",'"+facturaDetalle.getAnc_id()+"'"
				
				+ ",'"+facturaDetalle.getDescripcion()+"'"
				
				+ ",'"+facturaDetalle.getEs_activo()+"'"
				+ ",'"+facturaDetalle.getEs_anexo()+"'"
				+ ",'"+facturaDetalle.getEs_estructuracobro()+"'"
					+ ")";
		System.out.println(SQL);
		
		facturaDetalle.setEs_activo("0");
		facturaDetalle.setEs_anexo("1");
		facturaDetalle.setEs_estructuracobro("0");
		try {
			state.executeUpdate(SQL);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    disconect();
	    
	    
	}
	
	
	public static void getPrecioContador(ArrayList<String> cont_activo,ArrayList<String> cont_difs,ArrayList<String> cont_precios){
		getCon();
		
		
		//primero debemos buscar la estructura que le pertenece
		for(int i =0; i< cont_activo.size();i++){
			
			String act_ar[]=cont_activo.get(i).split(";;");
			
			String id_activo=act_ar[0]; // id del activo a cobrar en la ubicacion
			String id_estructura =act_ar[1]; // id de la estructura de cobro 
			
			int cantidad_imp=Integer.parseInt(cont_difs.get(i));
			int impPorCobrar=Integer.parseInt(cont_difs.get(i));
			//buscamos la estructura y sus rangos 
			
			String SQL_RANGOS="SELECT "
					+ "				rango_estructura_cobro.`rango_inicial`"
					+ "				,rango_estructura_cobro.`rango_final` "
					+ "				,rango_estructura_cobro.`rango_costo_variable` "
					+ "				,rango_estructura_cobro.`rango_costo_fijo` "
					
					+ "			FROM `rango_estructura_cobro` WHERE `rango_estructura_cobro`.`estrcobro_id`="+id_estructura;
			try {
				ResultSet RANGOS_RS = state.executeQuery(SQL_RANGOS);
				double precio =0;
				
				while(RANGOS_RS.next()){
					if(cantidad_imp==0) break;
					if(RANGOS_RS.getString("rango_final")==null){
						//solo cobramos esta linea 
						precio+= (impPorCobrar*RANGOS_RS.getDouble("rango_costo_variable"))+(RANGOS_RS.getDouble("rango_costo_fijo"));
						
						
						break;
					}
					//evaluamos si cae en esta rango, si es así, sera el ultimo rango que cobraremos
					if(cantidad_imp>=RANGOS_RS.getInt("rango_inicial") && cantidad_imp<=RANGOS_RS.getInt("rango_final")){
						precio+= (impPorCobrar*RANGOS_RS.getDouble("rango_costo_variable"))+(RANGOS_RS.getDouble("rango_costo_fijo"));
						
						break;
					}
					else{
						//cobramos este rango y continuamos evaluando
						
						precio+= (RANGOS_RS.getInt("rango_final")*RANGOS_RS.getDouble("rango_costo_variable"))+(RANGOS_RS.getDouble("rango_costo_fijo"));
								
						impPorCobrar-=RANGOS_RS.getInt("rango_final");
					}
					
				
			    }
				//System.out.println("ACTIVO:"+id_activo+" ESTRUCTURA:"+id_estructura+" NIMP:"+cantidad_imp+" PRECIO:"+precio);
				
				cont_precios.add(precio+"");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	    disconect();
	    
	    
	}
	
	
	
	public static ArrayList<AnexoContratoVO> getAnexosFactura(String id_per,String id_emp,String tipo_cambio_id){
		getCon();
		
		String SQL=" SELECT "
				+ "		a.id_activo as ALT_ID"
				+ "		,a.serie"
				+ "		, p.PROD_DESC_CORTO"
				+ "		, e.empresas_nombrenof"
				+ "		,per.peri_tc_correlativo"
				+ "		,u.ubi_fisica"
				+ "		, d.DIRE_DIRECCION"
				+ "		, e.empresas_nombrenof		"
				+ "		, `estructura_cobro`.`estrcobro_id`		"
				+ "		,`estructura_cobro`.estrcobro_nombre		"
				+ "		,a.ubi_id     "
				+ "		,estructura_cobro.tipo_cambio_id " 
				+ "		,estructura_cobro.tipo_estcobro_id " 
				+ "		,anexo_contrato.anc_id "
				+ "		,anexo_contrato.anc_cargo_fijo_unico " 
				+ "		,anexo_contrato.anc_observaciones "
				+ "		,ubi_estrcobro.activo_tptc_id "
				+ "		,tipo_contador_principal.tp_tc_nombre "
				
				+ " FROM activos_historia a " + 
				" INNER JOIN producto p ON p.PROD_ID = a.PROD_ID  " + 
				" INNER JOIN funcionalidad f ON f.FUNC_ID = p.FUNC_ID " + 
				" INNER JOIN ubicacion u ON u.UBI_ID = a.UBI_ID " +
				" INNER JOIN `activo_tptc` ON (`activo_tptc`.`estados_vig_novig_id`=1 AND `activo_tptc`.`id_activo`=a.`id_activo`) " +
				" INNER JOIN `tipo_contador_principal` ON ( `tipo_contador_principal`.`tp_tc_id`=activo_tptc.`tp_tc_id`)  " +
				
				" INNER JOIN `ubi_estrcobro` ON (`ubi_estrcobro`.`estados_vig_novig_id`=1 AND `ubi_estrcobro`.`ubi_id`=u.`ubi_id`  AND ubi_estrcobro.`activo_tptc_id`=activo_tptc.`activo_tptc_id` )  " + 
				" INNER JOIN `estructura_cobro` ON (`estructura_cobro`.`estados_vig_novig_id`=1 AND `estructura_cobro`.`estrcobro_id`=ubi_estrcobro.`estrcobro_id`)  " + 
				" INNER JOIN `anexo_contrato` ON (`anexo_contrato`.`estados_vig_novig_id`=1 AND `anexo_contrato`.`anc_id`=estructura_cobro.`anc_id`)  " + 
				
				" INNER JOIN direccion d ON d.DIRE_ID = u.DIRE_ID " + 
				" INNER JOIN empresas e ON e.empresas_id = d.CLPR_ID " + 
				" INNER JOIN (	SELECT 		`periodos_tc`.`peri_tc_fdesde`,`periodos_tc`.`peri_tc_fhasta`	,periodos_tc.peri_tc_correlativo						"
				+ "				FROM			`periodos_tc`							"
				+ "				WHERE 			`periodos_tc`.`peri_tc_id` ="+id_per+"						"
				+ "									AND `periodos_tc`.`peri_tc_id_emp` = "+id_emp+" ) per  "
				+ "	WHERE p.FUNC_ID = 6 AND  estructura_cobro.tipo_cambio_id="+tipo_cambio_id+" "
				+ "		AND a.empresas_id = "+id_emp+" AND a.fecha_captura >=per.peri_tc_fdesde AND a.`fecha_captura`<=per.peri_tc_fhasta	"
				+ "	GROUP BY a.`id_activo`,`estructura_cobro`.`estrcobro_id`    "
				+ "	ORDER BY d.DIRE_DIRECCION,u.ubi_fisica, p.PROD_DESC_CORTO " + 
				"";
		System.out.println(SQL);
		ArrayList<AnexoContratoVO> anexos = new ArrayList<AnexoContratoVO>();
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			
			while(RS.next()){
				
				
				if(!anexos.contains(new AnexoContratoVO(RS.getString("anc_id")))){
					
					
					
					MaquinaContadorVO maq = new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id"));
					maq.setId_activo(RS.getString("ALT_ID"));
					
					EstructuraCobroVO estructura = new EstructuraCobroVO(RS.getString("estrcobro_id"));
					estructura.setEstrcobro_nombre(RS.getString("estrcobro_nombre"));
					estructura.setTipocambio(new TipoCambioVO(RS.getString("tipo_cambio_id")));
					estructura.setTipo_estructuraC(new TipoEstcobroVO(RS.getString("tipo_estcobro_id")));
					estructura.setAnc_id(RS.getString("anc_id"));
				
					estructura.getMaquinasContador().add(maq);
					
					AnexoContratoVO anexo = new AnexoContratoVO(RS.getString("anc_id"));
					anexo.setCargoFijoUnico(RS.getString("anc_cargo_fijo_unico"));
					anexo.setObservacion(RS.getString("anc_observaciones"));
					anexo.getEstructurasCobro().add(estructura);
					
					anexos.add(anexo);
					
				}
				else{
					//anexo ya existe.. por ende verificamos si ya existe la estructura
					AnexoContratoVO anexo = anexos.get(anexos.indexOf(new AnexoContratoVO(RS.getString("anc_id"))));
					
					if(anexo.getEstructurasCobro().contains(new EstructuraCobroVO(RS.getString("estrcobro_id")))){
						// si tiene la estructura
						
						EstructuraCobroVO estructura = anexo.getEstructurasCobro().get(anexo.getEstructurasCobro().indexOf(new EstructuraCobroVO(RS.getString("estrcobro_id"))));
						//verificamos si existe la ubicacion
						if(estructura.getMaquinasContador().contains(new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id")))){
							//existe ubicacion, verificamos si existe activo
							
						}
						else{
							//no existe maq contador, insertamos 
							MaquinaContadorVO maq = new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id"));
							maq.setId_activo(RS.getString("ALT_ID"));
							
							estructura.getMaquinasContador().add(maq);
							
						}
						
						
					}
					else{
						//no tiene la estructura. insertamos  
						MaquinaContadorVO maq = new MaquinaContadorVO(RS.getString("ubi_id"),RS.getString("activo_tptc_id"));
						maq.setId_activo(RS.getString("ALT_ID"));
						
						EstructuraCobroVO estructura = new EstructuraCobroVO(RS.getString("estrcobro_id"));
						estructura.setEstrcobro_nombre(RS.getString("estrcobro_nombre"));
						estructura.setTipocambio(new TipoCambioVO(RS.getString("tipo_cambio_id")));
						estructura.setTipo_estructuraC(new TipoEstcobroVO(RS.getString("tipo_estcobro_id")));
						estructura.setAnc_id(RS.getString("anc_id"));
						
						estructura.getMaquinasContador().add(maq);
						
						anexo.getEstructurasCobro().add(estructura);
						
					}
					
					
				}
				
				
				
				
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    disconect();
	    return anexos;
	    
	}
	
	public static String setPreciosTomaC(ArrayList<AnexoContratoVO> anexos,String id_per){
		
		for(AnexoContratoVO anexo: anexos){
			for(EstructuraCobroVO estructura : anexo.getEstructurasCobro()){
				
				RangoEstructuraCobroVO rango = new RangoEstructuraCobroVO();
				rango.setEstrcobro_id(estructura.getEstrcobro_id());
				//seteamos rangos de estructuras de cobro
				estructura.setRangosEstCobro(RangoEstructuraCobroDAO.getRangosEstructuraDeCobro(rango));
				
				if(estructura.getTipo_estructuraC().getId().equals("1")){
					//individual
					//recorremos ubicaciones 
					for(MaquinaContadorVO maq : estructura.getMaquinasContador()){
						//recorremos activos 
							//obtenemos n de impresiones
							maq.setN_imps(TomaContadorDAO.getContadorDif(maq.getId_activo(), id_per));
							
							//calculamos valor para tipo individual. 
							String [] precios=getPrecioIndividualC(estructura.getRangosEstCobro(), Integer.parseInt(maq.getN_imps()));
							
							maq.setCosto_fijo(precios[0]);
							maq.setCosto_variable(precios[1]);
							
						
					}
					
				}
				if(estructura.getTipo_estructuraC().getId().equals("2")){
					//grupal 
					
					//seteamos n de impresiones
					for(MaquinaContadorVO maq : estructura.getMaquinasContador()){
						//recorremos activos 
							//obtenemos n de impresiones
							maq.setN_imps(TomaContadorDAO.getContadorDif(maq.getId_activo(), id_per));
							
					}
					
					
					double CF=0;
					//recorremos rangos para aplicar el costo fijo a la estructura completa
					
					for(RangoEstructuraCobroVO rango_ : estructura.getRangosEstCobro()){
						CF+=Double.parseDouble(rango_.getRango_costo_fijo());
					}
					
					estructura.setCostoFijo(CF+"");
					
					//costo varialbe debe ser por la suma de n imps
					
					int n_imptotalesEstructura =0;
					
					for(MaquinaContadorVO maq:estructura.getMaquinasContador()){
						if(maq.getN_imps()!=null)n_imptotalesEstructura+=Integer.parseInt(maq.getN_imps());
					}
					//ahora que tenemos la suma total de n de impresiones aplicamos rangos. 
					String [] precios=getPrecioIndividualC(estructura.getRangosEstCobro(), n_imptotalesEstructura);
					estructura.setCostoVariable(precios[1]);
					
					//recorremos ubicaciones 
					/*
					for(UbicacionVO ubicacion : estructura.getUbicaciones()){
						//recorremos activos 
						for(ActivoVO activo : ubicacion.getActivos()){
							//obtenemos n de impresiones
							activo.setN_imps(TomaContadorDAO.getContadorDif(activo.getId(), id_per));
							
							//calculamos valor para tipo individual. 
							String [] precios=getPrecioIndividualC(estructura.getRangosEstCobro(), Integer.parseInt(activo.getN_imps()));
							activo.setCosto_fijo("0");
							activo.setCosto_variable(precios[1]);
							
						}
					}
					*/
					
				
					
				}
			}
		}
		
		for(AnexoContratoVO anexo: anexos){
			System.out.println("ANEXO:"+anexo.getAnc_id()+" CF:"+anexo.getCargoFijoUnico());
			for(EstructuraCobroVO estructura : anexo.getEstructurasCobro()){
				System.out.println("	ESTRUCTURA:"+estructura.getEstrcobro_id()+" "+estructura.getEstrcobro_nombre()+" CF:"+estructura.getCostoFijo()+" CV:"+estructura.getCostoVariable());
				
				for(RangoEstructuraCobroVO rango : estructura.getRangosEstCobro()){
					System.out.println("		RANGO:FIJO:"+rango.getRango_costo_fijo()+" VARIABLE:"+rango.getRango_costo_variable());
				}
				for(MaquinaContadorVO maquinaC : estructura.getMaquinasContador()){
					System.out.println("		UBI:"+maquinaC.getUbi_id()+" ACTIVO:"+maquinaC.getId_activo()+" NIMP:"+maquinaC.getN_imps()+" CFIJO:"+maquinaC.getCosto_fijo()+" CVARIABLE:"+maquinaC.getCosto_variable());
					//recorremos activos 
					
				}
				
			}
		}
		
		
		return null;
	}
	
	public static String[] getPrecioIndividualC(ArrayList<RangoEstructuraCobroVO> rangos,int n_imp_){
		
		int n_imp=n_imp_;
		double precioCostoFijo=0;
		double precioCostoVariable=0;
		for(RangoEstructuraCobroVO rango :rangos){
			if(rango.getRango_final()==null || rango.getRango_final().equals("")){
				//solo cobramos esta linea 
				precioCostoVariable+= (n_imp*Double.parseDouble(rango.getRango_costo_variable()));
				precioCostoFijo+=Double.parseDouble(rango.getRango_costo_fijo());
				
				break;
			}
			//evaluamos si cae en esta rango, si es así, sera el ultimo rango que cobraremos
			if(n_imp>=Integer.parseInt(rango.getRango_inicial()) && n_imp<=Integer.parseInt(rango.getRango_final())){
				
				precioCostoVariable+= (n_imp*Double.parseDouble(rango.getRango_costo_variable()));
				precioCostoFijo+=Double.parseDouble(rango.getRango_costo_fijo());
				
				break;
			}
			else{
				//cobramos este rango y continuamos evaluando
				precioCostoFijo+=Double.parseDouble(rango.getRango_costo_fijo());
				precioCostoVariable+= (Integer.parseInt(rango.getRango_final())*Double.parseDouble(rango.getRango_costo_variable()));
						
				n_imp-=Integer.parseInt(rango.getRango_final());
			}
		}
		
		return new String[]{precioCostoFijo+"",precioCostoVariable+""};
	}
	
	
	public static ArrayList<FacturaVO> getFacturas(){
		getCon();
		
		String SQL=" 	SELECT "
				+ "			DATE_FORMAT(fact_fecha,'%d-%m-%Y') AS fact_fecha "
			
				+ "			,id_fact"
				+ "			,fact_cliente_rz"
				+ "			,fact_total"
				+ "			,estados_vig_novig.estados_vig_novig_id "
				+ "			,estados_vig_novig.estados_vig_novig_nombre "
				+ "		FROM "
				+ "			`factura`"
				+ "		INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=factura.estados_vig_novig_id ";
		System.out.println(SQL);
		ArrayList<FacturaVO> facturas = new ArrayList<FacturaVO>();
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				
				FacturaVO factura = new FacturaVO();
				factura.setCliente_rz(RS.getString("fact_cliente_rz"));
				factura.setFecha(RS.getString("fact_fecha"));
				factura.setId(RS.getString("id_fact"));
				factura.setTotal(RS.getString("fact_total"));
				
				EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
				estado.setId(RS.getString("estados_vig_novig_id"));
				estado.setNombre(RS.getString("estados_vig_novig_nombre"));
				factura.setEstadoVigNoVig(estado);
				facturas.add(factura);
				
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 disconect();
		
		 return facturas;
	}
	public static FacturaVO getFactura(String id){
		getCon();
		
		String SQL=" 	SELECT "
				+ "			DATE_FORMAT(fact_fecha,'%d-%m-%Y') AS fact_fecha "
				
				+ "			,id_fact"
				+ "			,fact_id_cliente"
				+ "			,fact_observaciones"
				
				+ "			,fact_cliente_rz"
				+ "			,fact_cliente_rut"
				+ "			,factura.peri_tc_id"
				+ "			,fact_condpago"
				+ "			,fact_total"
				+ "			,fact_subtotal"
				+ "			,fact_desc"
				+ "			,fact_neto"
				+ "			,fact_iva"
				+ "			,fact_glosa_desc"
				+ "			,fact_n_imp"
				+ "			,fact_tcambio"
				
				+ "			,fact_dir_direccion"
				
				+ "			,fact_dir_region"
				+ "			,fact_dir_ciudad"
				+ "			,fact_dir_comuna"

				
				+ "			,estados_vig_novig.estados_vig_novig_id "
				+ "			,estados_vig_novig.estados_vig_novig_nombre "
				
				+ "			,emisor.empresas_id AS empresas_idEmisor "
				+ "			,emisor.empresas_nombrenof AS empresas_nombrenofEmisor "
				
				+ "			,tipo_cambio.tipo_cambio_id  "
				+ "			,tipo_cambio.tipo_cambio_nombre  "
				+ "			,CONCAT_WS(' ',periodos_tc.peri_tc_correlativo,DATE_FORMAT(periodos_tc.peri_tc_fdesde,'%d-%m-%Y'),DATE_FORMAT(periodos_tc.peri_tc_fhasta,'%d-%m-%Y') ) AS periodoNombre  "
				+ "			,periodos_tc.peri_tc_correlativo"
				+ "			,DATE_FORMAT(periodos_tc.peri_tc_fdesde,'%d-%m-%Y') AS peri_tc_fdesde"
				+ "			,DATE_FORMAT(periodos_tc.peri_tc_fhasta,'%d-%m-%Y') AS peri_tc_fhasta   "
				
				
				
				+ "		FROM "
				+ "			`factura`"
				+ "		INNER JOIN estados_vig_novig ON estados_vig_novig.estados_vig_novig_id=factura.estados_vig_novig_id"
				+ "		INNER JOIN empresas emisor ON emisor.empresas_id=factura.fact_id_emisor"
				+ "		INNER JOIN tipo_cambio ON tipo_cambio.tipo_cambio_id=factura.tipo_cambio_id"
				+ "		INNER JOIN periodos_tc ON periodos_tc.peri_tc_id=factura.peri_tc_id"
				+ "		WHERE factura.id_fact= "+id;
		System.out.println(SQL);
		FacturaVO factura = new FacturaVO();
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			if(RS.next()){
				PeriodoTcVO periodoFac= new PeriodoTcVO();
				periodoFac.setCorrelativo(RS.getString("peri_tc_correlativo"));
				
				periodoFac.setFdesde(RS.getString("peri_tc_fdesde"));
				periodoFac.setFhasta(RS.getString("peri_tc_fhasta"));
				factura.setPeriodoFac(periodoFac);
				
				factura.setCliente_rz(RS.getString("fact_cliente_rz"));
				factura.setFecha(RS.getString("fact_fecha"));
				factura.setId(RS.getString("id_fact"));
				factura.setN_imp(RS.getString("fact_n_imp"));
				factura.setObservaciones(RS.getString("fact_observaciones"));
				factura.setTcambio(RS.getString("fact_tcambio"));
				factura.setPeri_tc_idNombre(RS.getString("periodoNombre"));
				
				
				factura.setDir_direccion(RS.getString("fact_dir_direccion"));
				factura.setDir_region(RS.getString("fact_dir_region"));
				factura.setDir_ciudad(RS.getString("fact_dir_ciudad"));
				factura.setDir_comuna(RS.getString("fact_dir_comuna"));
				
				factura.setSubtotal(RS.getString("fact_subtotal"));
				factura.setDesc(RS.getString("fact_desc"));
				factura.setNeto(RS.getString("fact_neto"));
				
				factura.setTotal(RS.getString("fact_total"));
				
				factura.setIva(RS.getString("fact_iva"));
				
				factura.setCondpago(RS.getString("fact_condpago"));
				EstadosVigNoVigVO estado = new EstadosVigNoVigVO();
				estado.setId(RS.getString("estados_vig_novig_id"));
				estado.setNombre(RS.getString("estados_vig_novig_nombre"));
				factura.setEstadoVigNoVig(estado);
				
				EmpresaVO emisor= new EmpresaVO();
				emisor.setEmpresas_id(RS.getString("empresas_idEmisor"));
				emisor.setEmpresas_nombrenof(RS.getString("empresas_nombrenofEmisor"));
				
				factura.setEmisor(emisor);
				
				factura.setCliente_rut(RS.getString("fact_cliente_rut"));
				factura.setPeri_tc_id(RS.getString("peri_tc_id"));
				factura.setId_cliente(RS.getString("fact_id_cliente"));
				factura.setGlosa_desc(RS.getString("fact_glosa_desc"));
				
				TipoCambioVO tipo_cambio = new TipoCambioVO();
				tipo_cambio.setId(RS.getString("tipo_cambio_id"));
				tipo_cambio.setNombre(RS.getString("tipo_cambio_nombre"));
				
				factura.setTipo_cambio(tipo_cambio);
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 disconect();
		
		 return factura;
	}
	
	public static ArrayList<FacturaDetalleVO> getFacturaDetalle(String id_fact){
		getCon();
		
		String SQL=" 	SELECT "
				+ "			id_fact"
				+ "			,ubi_id"
				+ "			,id_activo"
				
				+ "			,estrcobro_id"
				+ "			,anc_id"
				+ "			,detallefact_descripcion"
				
				+ "			,detallefact_nimp"
				+ "			,detallefact_precioCF"
				+ "			,detallefact_precioCV "
				
				+ "			,id_fact "
				
				+ "			,es_activo"
				+ "			,es_anexo"
				+ "			,es_estructuracobro"
			
				+ "		FROM "
				+ "			`detalle_factura`"
				+ "		WHERE "
				+ "			estados_vig_novig_id=1 "
				+ "			AND detalle_factura.id_fact= "+id_fact;
		System.out.println(SQL);
		ArrayList<FacturaDetalleVO> detalles = new ArrayList<FacturaDetalleVO>();
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			while(RS.next()){
				
				FacturaDetalleVO detalle_factura = new FacturaDetalleVO();
				detalle_factura.setId_fact(RS.getString("id_fact"));
				detalle_factura.setUbi_id(RS.getString("ubi_id"));
				detalle_factura.setId_activo(RS.getString("id_activo"));
				detalle_factura.setEstrcobro_id(RS.getString("estrcobro_id"));
				
				detalle_factura.setAnc_id(RS.getString("anc_id"));
				detalle_factura.setDescripcion(RS.getString("detallefact_descripcion"));
				
				detalle_factura.setPrecioCF(RS.getString("detallefact_precioCF"));
				detalle_factura.setPrecioCV(RS.getString("detallefact_precioCV"));
				
				detalle_factura.setEs_activo(RS.getString("es_activo"));
				
				detalle_factura.setEs_anexo(RS.getString("es_anexo"));
				detalle_factura.setEs_estructuracobro(RS.getString("es_estructuracobro"));
				
				
				
				detalles.add(detalle_factura);
					
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 disconect();
		
		 return detalles;
	}
	public static void disconect(){
		try {
			state.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
