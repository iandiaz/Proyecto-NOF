package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Constantes.Constantes;


public class rep9DAO {
	private Connection conexion=null;
	private Statement state=null;
	
	
	String periodos_para_tc[]=null;
	String periodosfechas_para_tc[]=null;
	String periodos_para_tc_ids[]=null;
	String periodos_para_tc_nimps[]=null;
	String periodos_para_tc_nmaquinas[]=null;
	String periodos_para_tc_totalventasimps[]=null;
	String periodos_para_tc_totaldolaresimps[]=null;
	String periodos_para_tc_valorUnitarioimps[]=null;
	String periodos_para_tc_valorUnitarioimpsCL[]=null;
	String periodos_para_tc_mediaTC[]=null; 
	
	String periodos_para_tc_nsme[]=null;
	String periodos_para_tc_rendimiento[]=null;
	String periodos_para_tc_cuc[]=null;
	
	String periodos_para_tc_nkite[]=null;
	String periodos_para_tc_kiterendimiento[]=null;
	String periodos_para_tc_kitecuc[]=null;
	
	
	String periodos_para_tc_ntickets[]=null;
	String periodos_para_tc_nticketsLog[]=null;
	String periodos_para_tc_nticketsServTec[]=null;
	String periodos_para_tc_ids12[]=null;
	
	String periodos_para_tc_totales[]=null;
	
	
	String periodos_para_tc_totalventasnoimps[]=null;
	String periodos_para_tc_totalIngresos[]=null;
	String periodos_para_tc_ndires[]=null;
	
	String periodos_para_tc_n_env_fotoconductor[]=null;
	String periodos_para_tc_n_env_repuestos[]=null;
	String periodos_para_tc_n_env_consumibles[]=null;
	
	String periodos_para_tc_rendimiento_fotocond[]=null;
	String periodos_para_tc_rendimiento_consumibles[]=null;
	String periodos_para_tc_rendimiento_repuestos[]=null;
	
	String periodos_para_tc_cuc_fotocond[]=null;
	String periodos_para_tc_cuc_consumibles[]=null;
	String periodos_para_tc_cuc_repuestos[]=null;
	
	public String[] getPeriodos_para_tc_n_env_repuestos() {
		return periodos_para_tc_n_env_repuestos;
	}

	public String[] getPeriodos_para_tc_n_env_consumibles() {
		return periodos_para_tc_n_env_consumibles;
	}

	public String[] getPeriodos_para_tc_n_env_fotoconductor() {
		return periodos_para_tc_n_env_fotoconductor;
	}

	public String[] getPeriodos_para_tc_rendimiento_fotocond() {
		return periodos_para_tc_rendimiento_fotocond;
	}

	public String[] getPeriodos_para_tc_rendimiento_consumibles() {
		return periodos_para_tc_rendimiento_consumibles;
	}

	public String[] getPeriodos_para_tc_rendimiento_repuestos() {
		return periodos_para_tc_rendimiento_repuestos;
	}

	public String[] getPeriodos_para_tc_cuc_fotocond() {
		return periodos_para_tc_cuc_fotocond;
	}

	public String[] getPeriodos_para_tc_cuc_consumibles() {
		return periodos_para_tc_cuc_consumibles;
	}

	public String[] getPeriodos_para_tc_cuc_repuestos() {
		return periodos_para_tc_cuc_repuestos;
	}

	public String[] getPeriodos_para_tc_ndires() {
		return periodos_para_tc_ndires;
	}

	public String[] getPeriodos_para_tc_totalIngresos() {
		return periodos_para_tc_totalIngresos;
	}

	public String[] getPeriodos_para_tc_nticketsServTec() {
		return periodos_para_tc_nticketsServTec;
	}
	
	public String[] getPeriodos_para_tc_nticketsLog() {
		return periodos_para_tc_nticketsLog;
	}
	
	public String[] getPeriodos_para_tc_nkite() {
		return periodos_para_tc_nkite;
	}
	
	public String[] getPeriodos_para_tc_kiterendimiento() {
		return periodos_para_tc_kiterendimiento;
	}
	
	public String[] getPeriodos_para_tc_kitecuc() {
		return periodos_para_tc_kitecuc;
	}
	
	public String[] getPeriodos_para_tc_cuc() {
		return periodos_para_tc_cuc;
	}
	
	public String[] getPeriodos_para_tc_rendimiento() {
		return periodos_para_tc_rendimiento;
	}
	
	public String[] getPeriodos_para_tc_mediaTC() {
		return periodos_para_tc_mediaTC;
	}
	
	public String[] getPeriodos_para_tc() {
		return periodos_para_tc;
	}

	public String[] getPeriodosfechas_para_tc() {
		return periodosfechas_para_tc;
	}
	
	public String[] getPeriodos_para_tc_ntickets() {
		return periodos_para_tc_ntickets;
	}

	public String[] getPeriodos_para_tc_nsme() {
		return periodos_para_tc_nsme;
	}

	public String[] getPeriodos_para_tc_valorUnitarioimps() {
		return periodos_para_tc_valorUnitarioimps;
	}

	public String[] getPeriodos_para_tc_totaldolaresimps() {
		return periodos_para_tc_totaldolaresimps;
	}

	
	public String[] getPeriodos_para_tc_totalventasimps() {
		return periodos_para_tc_totalventasimps;
	}

	
	public String[] getPeriodos_para_tc_nmaquinas() {
		return periodos_para_tc_nmaquinas;
	}

	
	public String[] getPeriodos_para_tc_nimps() {
		return periodos_para_tc_nimps;
	}
	
	public String[] getPeriodos_para_tc_valorUnitarioimpsCL() {
		return periodos_para_tc_valorUnitarioimpsCL;
	}
	
	public String[] getPeriodos_para_tc_totales() {
		return periodos_para_tc_totales;
	}
	public String[] getPeriodos_para_tc_totalventasnoimps() {
		return periodos_para_tc_totalventasnoimps;
	}
	
	public void generatePeriodos_para_tc(String id_per,String id_emp) {
		
		//buscamos periodos anteriores
		String SQL_PERIODO_PREV = "SELECT "
				+ "		`periodos_tc`.`peri_tc_id`,"
				+ "		`periodos_tc`.`peri_tc_correlativo`, "
             + "		DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y') AS peri_tc_fdesde,"
		        + "		DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') AS peri_tc_fhasta "
             + "		FROM"
				+ "			`periodos_tc`"
				+ "		WHERE "
				+ "			`periodos_tc`.`peri_tc_id`<= "+id_per
				+ "			AND `periodos_tc`.`peri_tc_id_emp` = "+id_emp
				+ "			AND `periodos_tc`.`estados_vig_novig_id` = 1 "
				+ "			ORDER BY `periodos_tc`.`peri_tc_correlativo` DESC LIMIT 7 ";
	
		System.out.println(SQL_PERIODO_PREV);
		
		try {
			ResultSet RS_PERIODO_PREV = state.executeQuery(SQL_PERIODO_PREV);
			
			this.periodos_para_tc = new String[7];
			this.periodosfechas_para_tc = new String[7];
			this.periodos_para_tc_ids = new String[7];
			
			int cont=6;
				while(RS_PERIODO_PREV.next() && cont>=0){
					periodos_para_tc[cont]=RS_PERIODO_PREV.getString("peri_tc_correlativo");
					periodosfechas_para_tc[cont]=RS_PERIODO_PREV.getString("peri_tc_fdesde")+" "+RS_PERIODO_PREV.getString("peri_tc_fhasta");
					periodos_para_tc_ids[cont]=RS_PERIODO_PREV.getString("peri_tc_id");
					
					//sacamos para este periodo la toma de contadores 
					
					
					
					cont--;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	public void generate12Periodos_para_tc(String id_per,String id_emp) {
		
		//buscamos periodos anteriores
		String SQL_PERIODO_PREV = "SELECT "
				+ "		`periodos_tc`.`peri_tc_id`,"
				+ "		`periodos_tc`.`peri_tc_correlativo`, "
             + "		DATE_FORMAT(`periodos_tc`.`peri_tc_fdesde`,'%d-%m-%Y') AS peri_tc_fdesde,"
		        + "		DATE_FORMAT(`periodos_tc`.`peri_tc_fhasta`,'%d-%m-%Y') AS peri_tc_fhasta "
             + "		FROM"
				+ "			`periodos_tc`"
				+ "		WHERE "
				+ "			`periodos_tc`.`peri_tc_id`<= "+id_per
				+ "			AND `periodos_tc`.`peri_tc_id_emp` = "+id_emp
				+ "			AND `periodos_tc`.`estados_vig_novig_id` = 1 "
				+ "			ORDER BY `periodos_tc`.`peri_tc_correlativo` DESC LIMIT 12 ";
	
		System.out.println(SQL_PERIODO_PREV);
		
		try {
			ResultSet RS_PERIODO_PREV = state.executeQuery(SQL_PERIODO_PREV);
			
			this.periodos_para_tc_ids12 = new String[12];
			
			int cont=11;
				while(RS_PERIODO_PREV.next() && cont>=0){
					periodos_para_tc_ids12[cont]=RS_PERIODO_PREV.getString("peri_tc_id");
					
					//sacamos para este periodo la toma de contadores 
					
					
					
					cont--;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}				
	public void generateNMaquinas_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		//buscamos periodos anteriores
		String SQL_NMAQ = "SELECT count(a.id_activo) AS nMquinas, sum(valorMaquina) AS valorMaquinas, a.peri_tc_id,sum(a.prod_vida_util_imp ) as prod_vida_util_imp " + 
				"FROM  (" + 
				"SELECT MAX( DISTINCT `activos_historia`.`id_activo`) AS id_activo ,producto.`prod_cuc` AS valorMaquina ,periodos_tc.`peri_tc_id` , producto.`prod_vida_util_imp` " + 
				"FROM `activos_historia` " + 
				"INNER JOIN producto ON producto.prod_id=activos_historia.prod_id " + 
				"INNER JOIN `periodos_tc` ON ( `activos_historia`.`fecha_captura`>=periodos_tc.`peri_tc_fdesde` AND `activos_historia`.`fecha_captura`<=periodos_tc.`peri_tc_fhasta` ) " + 
				"WHERE `activos_historia`.`empresas_id` = "+id_emp+" " + 
				"	AND periodos_tc.`peri_tc_id` IN ("+per_ids+") " + 
				"	AND producto.func_id=6 " + 
				"GROUP BY periodos_tc.`peri_tc_id`,`activos_historia`.`id_activo` " + 
				") a " + 
				"GROUP BY a.peri_tc_id   ";
	
		System.out.println(SQL_NMAQ);
		
		try {
			ResultSet RS_NMAQ = state.executeQuery(SQL_NMAQ);
			
			this.periodos_para_tc_nmaquinas = new String[7];
			
			
				while(RS_NMAQ.next()){
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
					
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_NMAQ.getString("peri_tc_id"))){
							periodos_para_tc_nmaquinas[i]=RS_NMAQ.getString("nMquinas");
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	
	public void generateNDirecciones_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		//buscamos periodos anteriores
		String SQL_NMAQ = "SELECT count(a.DIRE_ID) AS nDires , a.peri_tc_id " + 
				"FROM  (" + 
				"SELECT MAX( DISTINCT `ubicacion`.`DIRE_ID`) AS DIRE_ID ,producto.`prod_cuc` AS valorMaquina ,periodos_tc.`peri_tc_id` , producto.`prod_vida_util_imp` " + 
				"FROM `activos_historia`"
				+ "	INNER JOIN `ubicacion` ON ubicacion.`UBI_ID`=`activos_historia`.`ubi_id` " 
				+ "	INNER JOIN producto ON producto.prod_id=activos_historia.prod_id " 
				+ "	INNER JOIN `periodos_tc` ON ( `activos_historia`.`fecha_captura`>=periodos_tc.`peri_tc_fdesde` AND `activos_historia`.`fecha_captura`<=periodos_tc.`peri_tc_fhasta` ) " 
				+ "	WHERE `activos_historia`.`empresas_id` = "+id_emp+" " + 
				"	AND periodos_tc.`peri_tc_id` IN ("+per_ids+") " + 
				"	AND producto.func_id=6 " 
				+ "	GROUP BY periodos_tc.`peri_tc_id`,`ubicacion`.`DIRE_ID` " + 
				") a " + 
				"GROUP BY a.peri_tc_id   ";
	
		System.out.println(SQL_NMAQ);
		
		try {
			ResultSet RS_NMAQ = state.executeQuery(SQL_NMAQ);
			
			this.periodos_para_tc_ndires = new String[7];
			
			
				while(RS_NMAQ.next()){
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
					
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_NMAQ.getString("peri_tc_id"))){
							periodos_para_tc_ndires[i]=RS_NMAQ.getString("nDires");
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	public void generateNImp_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_NIMPS = "SELECT sum(`803`.`803_n_impresiones`) AS nIMPS "
				+ "		,periodos_tc.`peri_tc_id` "
				+ "	FROM `803` "
				+ " INNER JOIN `periodos_tc` ON ( `803`.`peri_tc_id`=periodos_tc.`peri_tc_id` ) " + 
				"	WHERE `803`.`clpr_id` = "+id_emp+" AND `803`.`estados_vig_novig_id`=1 " + 
				"		AND periodos_tc.`peri_tc_id` IN ("+per_ids+") " + 
				"	GROUP BY periodos_tc.`peri_tc_id`  ";
	
		System.out.println(SQL_NIMPS);
		
		try {
			ResultSet RS_NIMPS = state.executeQuery(SQL_NIMPS);
			
			
			this.periodos_para_tc_nimps = new String[7];
			
			
				while(RS_NIMPS.next()){
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
					
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_NIMPS.getString("peri_tc_id"))){
							periodos_para_tc_nimps[i]=RS_NIMPS.getString("nIMPS");
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}

	public void generateTotalVentasImp_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_TVIMP = "SELECT sum(`803`.`803_neto`) AS netoTotal"
				+ "		, sum(`803`.`803_neto`/`803`.`803_tipo_cambio`)  AS totalDolaresIMP "
				+ "		, (sum(`803`.`803_tipo_cambio`*`803`.`803_neto`)/sum(`803`.`803_neto`)) AS mediaTipoCambio "
				
				
				+ "		, periodos_tc.`peri_tc_id` "
				+ "	FROM `803` "
				+ "	INNER JOIN `periodos_tc` ON (  `803`.`peri_tc_id`=periodos_tc.`peri_tc_id`) " + 
				"	WHERE `803`.`clpr_id` = "+id_emp+" AND `803`.`estados_vig_novig_id`=1 " + 
				"		AND periodos_tc.`peri_tc_id` IN ("+per_ids+") " + 
				"	GROUP BY periodos_tc.`peri_tc_id`  ";
	
		System.out.println(SQL_TVIMP);
		
		try {
			ResultSet RS_TVIMP = state.executeQuery(SQL_TVIMP);
			
			this.periodos_para_tc_totalventasimps = new String[7];
			this.periodos_para_tc_totaldolaresimps = new String[7];
			this.periodos_para_tc_valorUnitarioimps = new String[7];
			this.periodos_para_tc_valorUnitarioimpsCL = new String[7];
			this.periodos_para_tc_mediaTC = new String[7];
			
				while(RS_TVIMP.next()){
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
					
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_TVIMP.getString("peri_tc_id"))){
							
							
							periodos_para_tc_totalventasimps[i]=RS_TVIMP.getString("netoTotal");
							
							periodos_para_tc_totaldolaresimps[i]=RS_TVIMP.getString("totalDolaresIMP")+"";
							
							if(periodos_para_tc_nimps[i]!=null && Integer.parseInt(periodos_para_tc_nimps[i])>0){
								
								
								periodos_para_tc_valorUnitarioimps[i]=(RS_TVIMP.getFloat("totalDolaresIMP")/Integer.parseInt(periodos_para_tc_nimps[i]))+"";
								periodos_para_tc_valorUnitarioimpsCL[i]=(RS_TVIMP.getFloat("netoTotal")/Integer.parseInt(periodos_para_tc_nimps[i]))+"";
								
							}
							
							periodos_para_tc_mediaTC[i]=(RS_TVIMP.getString("mediaTipoCambio"));
							
							
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
public void generateTotalIngresos_para_tc() {
		
		this.periodos_para_tc_totalIngresos = new String[7];
	
		for(int i =0; i<7;i++){
			
				
			Float valorImp=(float) 0;
			Float valorOtros=(float) 0;
			if(periodos_para_tc_totalventasnoimps[i]!=null) valorImp=Float.parseFloat(periodos_para_tc_totalventasnoimps[i]);
			if(periodos_para_tc_totalventasimps[i]!=null) valorOtros=Float.parseFloat(periodos_para_tc_totalventasimps[i]);
			
			
			periodos_para_tc_totalIngresos[i]=(valorImp+valorOtros)+"";
		}
			
	}
public void generateTotalVentasNOImp_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL = "SELECT sum(`dte_totales`.`Neto`) as netoTotal,`periodos_tc`.`peri_tc_id`" + 
				"	FROM `dte_encabezado`" + 
				"	INNER JOIN `dte_totales` ON `dte_totales`.`id_enc`=`dte_encabezado`.`id_dte`" + 
				"	LEFT JOIN `periodos_tc` ON ( dte_encabezado.`FechaEmision`>=periodos_tc.`peri_tc_fdesde` AND dte_encabezado.`FechaEmision`<=periodos_tc.`peri_tc_fhasta` )" 
				+ " WHERE `Modulo` IN ('801','802','804') "
				+ "		AND periodos_tc.`peri_tc_id` IN ("+per_ids+") "
				+ " 	AND `dte_encabezado`.`cliente_id` = "+id_emp+"" 
				+ " GROUP BY `periodos_tc`.`peri_tc_id` "
				
				+"	 ";
	
	
		System.out.println(SQL);
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			this.periodos_para_tc_totalventasnoimps = new String[7];
			
				while(RS.next()){
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
					
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS.getString("peri_tc_id"))){
							
							periodos_para_tc_totalventasnoimps[i]=RS.getString("netoTotal");
							
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	

	public void generateNSuministrosMaquinasEnviatos_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_nsme = new String[7];
			this.periodos_para_tc_rendimiento = new String[7];
			this.periodos_para_tc_cuc = new String[7];
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvSuministrosMaqPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
						
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
							periodos_para_tc_nsme[i]=sumi[0]+"";
							periodos_para_tc_cuc[i]=sumi[1];
							periodos_para_tc_rendimiento[i]=sumi[2];
						}
					}
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
			
	}
	
	public void generateNFotoCondEnviatos_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_n_env_fotoconductor = new String[7];
			this.periodos_para_tc_rendimiento_fotocond = new String[7];
			this.periodos_para_tc_cuc_fotocond = new String[7];
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvFotoCondPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
						
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
							periodos_para_tc_n_env_fotoconductor[i]=sumi[0]+"";
							periodos_para_tc_cuc_fotocond[i]=sumi[1];
							periodos_para_tc_rendimiento_fotocond[i]=sumi[2];
						}
					}
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
			
	}
	
	
	public void generateNConsumiblesEnviatos_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_n_env_consumibles = new String[7];
			this.periodos_para_tc_rendimiento_consumibles = new String[7];
			this.periodos_para_tc_cuc_consumibles = new String[7];
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvConsumiblesPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
						
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
							periodos_para_tc_n_env_consumibles[i]=sumi[0]+"";
							periodos_para_tc_cuc_consumibles[i]=sumi[1];
							periodos_para_tc_rendimiento_consumibles[i]=sumi[2];
						}
					}
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
			
	}
	
public void generateNRepuestosEnviatos_para_tc(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_n_env_repuestos = new String[7];
			this.periodos_para_tc_rendimiento_repuestos = new String[7];
			this.periodos_para_tc_cuc_repuestos = new String[7];
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvRepuestosPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
						
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
							periodos_para_tc_n_env_repuestos[i]=sumi[0]+"";
							periodos_para_tc_cuc_repuestos[i]=sumi[1];
							periodos_para_tc_rendimiento_repuestos[i]=sumi[2];
							
						}
					}
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
			
	}
	
	public void generateNKitEnviatos_para_tc(String id_emp) {
			
		String per_ids="'0'";
		
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_nkite = new String[7];
			this.periodos_para_tc_kiterendimiento = new String[7];
			this.periodos_para_tc_kitecuc = new String[7];
			
			
			while(RS_PERS.next()){
				
				trasladosBirtDAO tb= new trasladosBirtDAO();
				String[] sumi=tb.getNumeroKitPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
				tb.disconect();
				
				for(int i =0; i<periodos_para_tc_ids.length;i++){
					
					if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
						periodos_para_tc_nkite[i]=sumi[0]+"";
						periodos_para_tc_kitecuc[i]=sumi[1];
						periodos_para_tc_kiterendimiento[i]=sumi[2];
					
					}
				}
				
			}
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
				
	}
	public void generateNTickets_para_tc() {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
		
		//primero traemos un listado de los periodos con sus fechas a consultar 
		
		
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_ntickets = new String[7];
			
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					int ntickets=tb.getNumeroTicketsPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
						
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
							periodos_para_tc_ntickets[i]=ntickets+"";
						}
					}
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
		}

	public void generateNTicketsLogistica_para_tc() {
	
	String per_ids="'0'";

	for(int i =0; i<periodos_para_tc_ids.length;i++){
		per_ids+=",'"+periodos_para_tc_ids[i]+"'";
	}
	
	//primero traemos un listado de los periodos con sus fechas a consultar 
	
	 
	String SQL_PERS = "SELECT "
			+ "	`periodos_tc`.`peri_tc_id`"
			+ "	,`periodos_tc`.`peri_tc_fdesde`"
			+ "	, `periodos_tc`.`peri_tc_fhasta`"
			+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
			"FROM `periodos_tc` " + 
			"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";

	System.out.println(SQL_PERS);
	
	try {
		ResultSet RS_PERS = state.executeQuery(SQL_PERS);
		
		
		this.periodos_para_tc_nticketsLog = new String[7];
		
		
			while(RS_PERS.next()){
				
				//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
				
				ticketsBirtDAO tb= new ticketsBirtDAO();
				int ntickets=tb.getNumeroTicketsPorEmpresaConRangoFechaLogistica(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
				tb.disconect();
				
				for(int i =0; i<periodos_para_tc_ids.length;i++){
					
					if(periodos_para_tc_ids[i]!= null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
						periodos_para_tc_nticketsLog[i]=ntickets+"";
						
					}
				}
					
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
		
}
	public void generateNTicketsServTecnico_para_tc() {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids.length;i++){
			per_ids+=",'"+periodos_para_tc_ids[i]+"'";
		}
		
		//primero traemos un listado de los periodos con sus fechas a consultar 
		
			String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
			this.periodos_para_tc_nticketsServTec = new String[7];
			
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					int ntickets=tb.getNumeroTicketsPorEmpresaConRangoFechaServTecnico(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					for(int i =0; i<periodos_para_tc_ids.length;i++){
						
						if(periodos_para_tc_ids[i]!=null && periodos_para_tc_ids[i].equals(RS_PERS.getString("peri_tc_id"))){
							periodos_para_tc_nticketsServTec[i]=ntickets+"";
						}
					}
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}

	public void generateTotales(String id_emp) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			per_ids+=",'"+periodos_para_tc_ids12[i]+"'";
		}
		
		this.periodos_para_tc_totales = new String[26];
		
		
		String ventasImp12[]=getTotalVentasImp12Periodos(per_ids,id_emp);
		
		String envioSum[]=getNSuministrosMaquinasEnviatos12Periodos(per_ids);
		String envioKit[]=getNKitEnviatos12Periodos(per_ids);
		
		periodos_para_tc_totales[0]=ventasImp12[0];
		
		periodos_para_tc_totales[1]=getNImp_12periodos(per_ids,id_emp);
		
		periodos_para_tc_totales[2]=ventasImp12[1];
		
		periodos_para_tc_totales[3]=getTicketsLogistica12Periodos(per_ids);
		periodos_para_tc_totales[4]=generateNTicketsServTecnico12Periodos(per_ids);
		
		if(periodos_para_tc_totales[0]!=null && periodos_para_tc_totales[2]!=null && Float.parseFloat(periodos_para_tc_totales[2])>0){
			periodos_para_tc_totales[5]=(Float.parseFloat(periodos_para_tc_totales[0])/Float.parseFloat(periodos_para_tc_totales[2]))+"";	
		}
		
		periodos_para_tc_totales[6]=envioSum[0];
		periodos_para_tc_totales[7]=envioSum[1];
		periodos_para_tc_totales[8]=envioSum[2];
		
		periodos_para_tc_totales[9]=getTotalVentasSinImp12Periodos(per_ids,id_emp);
		
		periodos_para_tc_totales[10]=envioKit[0];
		periodos_para_tc_totales[11]=envioKit[1];
		periodos_para_tc_totales[12]=envioKit[2];
		
		/*
		if(periodos_para_tc_valorUnitarioimps[6]!=null){
			periodos_para_tc_totales[13]=periodos_para_tc_valorUnitarioimps[6];	
		}
		
		*/
		
		if(periodos_para_tc_totales[2]!=null && periodos_para_tc_totales[1]!=null && Float.parseFloat(periodos_para_tc_totales[1])>0){
			periodos_para_tc_totales[13]=(Float.parseFloat(periodos_para_tc_totales[2])/Float.parseFloat(periodos_para_tc_totales[1]))+"";	
		}
		
		
		
		if(periodos_para_tc_totales[13]!=null && periodos_para_tc_totales[5]!=null){
			periodos_para_tc_totales[16]=(Float.parseFloat(periodos_para_tc_totales[13])*Float.parseFloat(periodos_para_tc_totales[5]))+"";
				
		}
		
		String envioFotoCond[]=getNEnviatos12Periodos_fotocond(per_ids);
		String envioConsumibles[]=getNEnviatos12Periodos_consumibles(per_ids);
		String envioRepuestos[]=getNEnviatos12Periodos_repuestos(per_ids);
		
		periodos_para_tc_totales[17]=envioFotoCond[0];
		periodos_para_tc_totales[18]=envioFotoCond[1];
		periodos_para_tc_totales[19]=envioFotoCond[2];
		
		periodos_para_tc_totales[20]=envioConsumibles[0];
		periodos_para_tc_totales[21]=envioConsumibles[1];
		periodos_para_tc_totales[22]=envioConsumibles[2];
		
		periodos_para_tc_totales[23]=envioRepuestos[0];
		periodos_para_tc_totales[24]=envioRepuestos[1];
		periodos_para_tc_totales[25]=envioRepuestos[2];
		
		
		Float valorImp=(float) 0;
		Float valorOtros=(float) 0;
		if(periodos_para_tc_totales[0]!=null) valorImp=Float.parseFloat(periodos_para_tc_totales[0]);
		if(periodos_para_tc_totales[14]!=null) valorOtros=Float.parseFloat(periodos_para_tc_totales[14]);
		periodos_para_tc_totales[15]=(valorImp+valorOtros)+"";
			
	}
	

	private String getNImp_12periodos(String per_ids,String id_emp) {
		
		String nImp="0";
	 
		String SQL_NIMPS = "SELECT sum(`803`.`803_n_impresiones`) AS nIMPS "
				+ "	FROM `803` "
				+ " INNER JOIN `periodos_tc` ON ( `803`.`803_fecha`>=periodos_tc.`peri_tc_fdesde` AND `803`.`803_fecha`<=periodos_tc.`peri_tc_fhasta` ) " + 
				"	WHERE `803`.`clpr_id` = "+id_emp+" AND `803`.`estados_vig_novig_id`=1 " + 
				"		AND periodos_tc.`peri_tc_id` IN ("+per_ids+") " + 
				"	 ";
	
		System.out.println(SQL_NIMPS);
		
		try {
			ResultSet RS_NIMPS = state.executeQuery(SQL_NIMPS);
			
				if(RS_NIMPS.next()){
					
					nImp=RS_NIMPS.getString("nIMPS");
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nImp;
			
	}
		
	public String[] getTotalVentasImp12Periodos(String per_ids,String id_emp) {
		
		
		String totalNeto="0";
		String totalNetoDolares="0";
		
		String SQL_TVIMP = "SELECT sum(`803`.`803_neto`) AS netoTotal"
				+ "		, sum(`803`.`803_neto`/`803`.`803_tipo_cambio`)  AS totalDolaresIMP "
				+ "		, (sum(`803`.`803_tipo_cambio`*`803`.`803_neto`)/sum(`803`.`803_neto`)) AS mediaTipoCambio "
				+ "	FROM `803` "
				+ "	INNER JOIN `periodos_tc` ON (  `803`.`peri_tc_id`=periodos_tc.`peri_tc_id`) " + 
				"	WHERE `803`.`clpr_id` = "+id_emp+" AND `803`.`estados_vig_novig_id`=1 " + 
				"		AND periodos_tc.`peri_tc_id` IN ("+per_ids+") " + 
				"	 ";
	
		System.out.println(SQL_TVIMP);
		
		try {
			ResultSet RS_TVIMP = state.executeQuery(SQL_TVIMP);
			
			if(RS_TVIMP.next()){
				
				totalNeto=RS_TVIMP.getString("netoTotal");
				totalNetoDolares=RS_TVIMP.getString("totalDolaresIMP");
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new String[]{totalNeto,totalNetoDolares};
		
			
	}
	
public String getTotalVentasSinImp12Periodos(String per_ids,String id_emp) {
		
		
		String totalNeto="0";
		
		String SQL_VENTASSINIMP = "SELECT sum(`dte_totales`.`Neto`) as neto  " + 
				"	FROM `dte_encabezado`" + 
				"	INNER JOIN `dte_totales` ON `dte_totales`.`id_enc`=`dte_encabezado`.`id_dte`" + 
				"	LEFT JOIN `periodos_tc` ON ( dte_encabezado.`FechaEmision`>=periodos_tc.`peri_tc_fdesde` AND dte_encabezado.`FechaEmision`<=periodos_tc.`peri_tc_fhasta` )" 
				+ " WHERE `Modulo` IN ('801','802','804') "
				+ "		AND periodos_tc.`peri_tc_id` IN ("+per_ids+") "
				+ " 	AND `dte_encabezado`.`cliente_id` = "+id_emp+"" 
				+ "  "
				
				+"	 ";
	
		System.out.println(SQL_VENTASSINIMP);
		
		try {
			ResultSet RS_VENTASSINIMP = state.executeQuery(SQL_VENTASSINIMP);
			
			if(RS_VENTASSINIMP.next()){
				
				totalNeto=RS_VENTASSINIMP.getString("neto");
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalNeto;
		
			
	}
	
	public String getTicketsLogistica12Periodos(String per_ids) {
		
		//primero traemos un listado de los periodos con sus fechas a consultar 
		
		int ntickets=0;
	 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";

		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					ntickets+=tb.getNumeroTicketsPorEmpresaConRangoFechaLogistica(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
						
				}
		} catch (SQLException e) {
			//out.println("La conexion a BIRT ha fallado, porfavor refrescar la pagina.");
			e.printStackTrace();
		}
		return ntickets+"";
			
	}
	
	public String generateNTicketsServTecnico12Periodos(String per_ids) {
			
		//primero traemos un listado de los periodos con sus fechas a consultar 
		int ntickets=0;
		 
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";

		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					ntickets+=tb.getNumeroTicketsPorEmpresaConRangoFechaServTecnico(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ntickets+"";
	}
	
	public String[] getNSuministrosMaquinasEnviatos12Periodos(String per_ids) {
		
		float nSME=0;
		float nSMECUC=0;
		float nSMEREND=0;
	
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
		
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvSuministrosMaqPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					nSME+=Float.parseFloat(sumi[0]);
					nSMECUC+=Float.parseFloat(sumi[1]);
					nSMEREND+=Float.parseFloat(sumi[2]);
					
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nSME+"",nSMECUC+"",nSMEREND+""};
		
			
	}
	
	public String[] getNKitEnviatos12Periodos(String per_ids) {
		
		float nKIT=0;
		float nKITCUC=0;
		float nKITREND=0;
	
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
		
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroKitPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					nKIT+=Float.parseFloat(sumi[0]);
					nKITCUC+=Float.parseFloat(sumi[1]);
					nKITREND+=Float.parseFloat(sumi[2]);
					
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{nKIT+"",nKITCUC+"",nKITREND+""};
		
			
	}
	
	
	public String[] getNEnviatos12Periodos_fotocond(String per_ids) {
		
		float n=0;
		float nCUC=0;
		float nREND=0;
	
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
		
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvFotoCondPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					n+=Float.parseFloat(sumi[0]);
					nCUC+=Float.parseFloat(sumi[1]);
					nREND+=Float.parseFloat(sumi[2]);
					
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{n+"",nCUC+"",nREND+""};
		
			
	}
	
public String[] getNEnviatos12Periodos_consumibles(String per_ids) {
		
		float n=0;
		float nCUC=0;
		float nREND=0;
	
		String SQL_PERS = "SELECT "
				+ "	`periodos_tc`.`peri_tc_id`"
				+ "	,`periodos_tc`.`peri_tc_fdesde`"
				+ "	, `periodos_tc`.`peri_tc_fhasta`"
				+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
				"FROM `periodos_tc` " + 
				"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";
	
		System.out.println(SQL_PERS);
		
		try {
			ResultSet RS_PERS = state.executeQuery(SQL_PERS);
			
		
				while(RS_PERS.next()){
					
					//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvConsumiblesPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
					tb.disconect();
					
					n+=Float.parseFloat(sumi[0]);
					nCUC+=Float.parseFloat(sumi[1]);
					nREND+=Float.parseFloat(sumi[2]);
					
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String[]{n+"",nCUC+"",nREND+""};
		
			
	}
public String[] getNEnviatos12Periodos_repuestos(String per_ids) {
	
	float n=0;
	float nCUC=0;
	float nREND=0;

	String SQL_PERS = "SELECT "
			+ "	`periodos_tc`.`peri_tc_id`"
			+ "	,`periodos_tc`.`peri_tc_fdesde`"
			+ "	, `periodos_tc`.`peri_tc_fhasta`"
			+ "	,`periodos_tc`.`peri_tc_id_emp` " + 
			"FROM `periodos_tc` " + 
			"WHERE `periodos_tc`.`estados_vig_novig_id`=1 AND `periodos_tc`.`peri_tc_id` IN ("+per_ids+")";

	System.out.println(SQL_PERS);
	
	try {
		ResultSet RS_PERS = state.executeQuery(SQL_PERS);
		
			while(RS_PERS.next()){
				
				//conusltamos con el rango de fecha, la empresa el numero de tickets que tuvo 
				
				trasladosBirtDAO tb= new trasladosBirtDAO();
				String[] sumi=tb.getNumeroEnvRepuestosPorEmpresaConRangoFecha(RS_PERS.getString("peri_tc_fdesde"), RS_PERS.getString("peri_tc_fhasta"),RS_PERS.getString("peri_tc_id_emp"));
				tb.disconect();
				
				n+=Float.parseFloat(sumi[0]);
				nCUC+=Float.parseFloat(sumi[1]);
				nREND+=Float.parseFloat(sumi[2]);
					
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return new String[]{n+"",nCUC+"",nREND+""};
	
		
}
	
	public rep9DAO(){
		 getCon();
	 }
	 
	private void getCon() {
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
	 
	public void disconect(){
			try {
				state.close();
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
