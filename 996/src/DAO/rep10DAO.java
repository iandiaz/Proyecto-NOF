package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Constantes.Constantes;
import Constantes.UtilFunctions;
import VO.PeriodoVO;


public class rep10DAO {
	private Connection conexion=null;
	private Statement state=null;
	
	
	String periodos_para_tc[]=null;
	String periodosfechas_para_tc[]=null;
	
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
	String periodos_para_tc_totalventasimpsNC[]=null;
	String periodos_para_tc_totaldolaresimpsNC[]=null;
	String periodos_para_tc_totalventasnoimpsNC[]=null;
	
	
	String periodos_para_tc_n_fotocond[]=null;
	String periodos_para_tc_n_consumibles[]=null;
	String periodos_para_tc_n_repuestos[]=null;
	
	public String[] getPeriodos_para_tc_n_fotocond() {
		return periodos_para_tc_n_fotocond;
	}

	public String[] getPeriodos_para_tc_n_consumibles() {
		return periodos_para_tc_n_consumibles;
	}

	public String[] getPeriodos_para_tc_n_repuestos() {
		return periodos_para_tc_n_repuestos;
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

	String periodos_para_tc_rendimiento_fotocond[]=null;
	String periodos_para_tc_rendimiento_consumibles[]=null;
	String periodos_para_tc_rendimiento_repuestos[]=null;
	
	String periodos_para_tc_cuc_fotocond[]=null;
	String periodos_para_tc_cuc_consumibles[]=null;
	String periodos_para_tc_cuc_repuestos[]=null;
	
	
	public String[] getPeriodos_para_tc_totalventasimpsNC() {
		return periodos_para_tc_totalventasimpsNC;
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
	public void generatePeriodos_para_tc(String year,String month) {

		this.periodosfechas_para_tc = new String[7];
		
		this.periodos_para_tc = new String[7];
		
		for(int i=6; i>=0;i--){
			//buscamos periodos anteriores
			String SQL_PERIODO_PREV = "SELECT DATE_FORMAT(DATE_ADD('"+year+"-"+month+"-01',INTERVAL -"+(6-i)+" MONTH),'%Y-%m') AS fec ";
			System.out.println(SQL_PERIODO_PREV);
			try {
				ResultSet RS_PERIODO_PREV = state.executeQuery(SQL_PERIODO_PREV);
				
				if(RS_PERIODO_PREV.next()){
					periodosfechas_para_tc[i]=RS_PERIODO_PREV.getString("fec");
					periodos_para_tc[i]=""+i;
				}
				
					
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
	}
	
	public ArrayList<PeriodoVO> generate6Periodos(String year,String month) {

		ArrayList<PeriodoVO> periodos = new ArrayList<PeriodoVO>();
		
		for(int i=6; i>=0;i--){
			//buscamos periodos anteriores
			String SQL_PERIODO_PREV = "SELECT DATE_FORMAT(DATE_ADD('"+year+"-"+month+"-01',INTERVAL -"+(6-i)+" MONTH),'%Y-%m') AS fec ";
			System.out.println(SQL_PERIODO_PREV);
			try {
				ResultSet RS_PERIODO_PREV = state.executeQuery(SQL_PERIODO_PREV);
				
				if(RS_PERIODO_PREV.next()){
					PeriodoVO per = new PeriodoVO();
					per.setNombre(RS_PERIODO_PREV.getString("fec"));
					periodos.add(per);
					//periodosfechas_para_tc[i]=RS_PERIODO_PREV.getString("fec");
					//periodos_para_tc[i]=""+i;
				}
				
					
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
	return periodos;
		
			
	}
	
public void generate12Periodos_para_tc(String year,String month) {
		
	this.periodos_para_tc_ids12 = new String[12];
	
	for(int i=11; i>=0;i--){
		//buscamos periodos anteriores
		String SQL_PERIODO_PREV = "SELECT DATE_FORMAT(DATE_ADD('"+year+"-"+month+"-01',INTERVAL -"+i+" MONTH),'%Y-%m') AS fec ";
		System.out.println(SQL_PERIODO_PREV);
		try {
			ResultSet RS_PERIODO_PREV = state.executeQuery(SQL_PERIODO_PREV);
			
			if(RS_PERIODO_PREV.next())periodos_para_tc_ids12[i]=RS_PERIODO_PREV.getString("fec");
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
			
	}	
	
	public void generateNImp_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	 
		String SQL_NIMPS = "SELECT (sum(`803`.`803_n_impresiones`) - IF(sum(`841`.`841_n_impresiones`) IS NULL ,0,sum(`841`.`841_n_impresiones`))) AS nIMPS "
				+ "		,DATE_FORMAT(`803`.`803_fecha`,'%Y-%m') dateformat "
				+ "	FROM `803`"
				+ "	INNER JOIN empresas on empresas.empresas_id=`803`.`clpr_id`"
				+ "	LEFT JOIN `841` ON (`841`.`estados_vig_novig_id`=1 AND `841`.`folioref`=`803`.`803_folio` AND `841`.`841_est_aprobacion`=2)  "
				+ "	WHERE "
				+ "		`803`.`estados_vig_novig_id`=1 " 
				+ "		AND DATE_FORMAT(`803`.`803_fecha`,'%Y-%m') IN ("+per_ids+") " ;
		
		if(id_emp!=null && !id_emp.equals("")) SQL_NIMPS+= "		AND `803`.`clpr_id` = "+id_emp+" ";
		if(idgrupo!=null && !idgrupo.equals("")) SQL_NIMPS+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
		if(select_vendedor!=null && select_vendedor.length>0){
			SQL_NIMPS+= "		AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";
		}
				
				SQL_NIMPS+="	GROUP BY DATE_FORMAT(`803`.`803_fecha`,'%Y-%m')   ";
	
		System.out.println(SQL_NIMPS);
		
		try {
			ResultSet RS_NIMPS = state.executeQuery(SQL_NIMPS);
			
			
			this.periodos_para_tc_nimps = new String[7];
			
			
				while(RS_NIMPS.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS_NIMPS.getString("dateformat"))){
							periodos_para_tc_nimps[i]=RS_NIMPS.getString("nIMPS");
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	public void generateNMaquinas_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	 
		//buscamos periodos anteriores
		String SQL_NMAQ = "SELECT count(a.id_activo) AS nMquinas, sum(valorMaquina) AS valorMaquinas,a.fec ,sum(a.prod_vida_util_imp ) as prod_vida_util_imp " + 
				"FROM  (" + 
				"SELECT MAX( DISTINCT `activos_historia`.`id_activo`) AS id_activo ,producto.`prod_cuc` AS valorMaquina ,DATE_FORMAT(`activos_historia`.`fecha_captura`,'%Y-%m') as fec , producto.`prod_vida_util_imp` "  
				+ "	FROM `activos_historia` " 
				+ "	INNER JOIN empresas ON empresas.empresas_id=`activos_historia`.`empresas_id` "
				+ "	INNER JOIN producto ON producto.prod_id=activos_historia.prod_id " + 
				"WHERE " + 
				"		 DATE_FORMAT(`activos_historia`.`fecha_captura`,'%Y-%m') IN ("+per_ids+") " + 
				"	AND producto.func_id=6 ";
		
		if(id_emp!=null && !id_emp.equals("")) SQL_NMAQ+= "		AND `activos_historia`.`empresas_id` = "+id_emp+" ";
		if(idgrupo!=null && !idgrupo.equals("")) SQL_NMAQ+= "	AND `empresas`.`grupos_id` = "+idgrupo+" ";
		if(select_vendedor!=null && select_vendedor.length>0){
			SQL_NMAQ+= "		AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";
		}
			
		
		SQL_NMAQ+="GROUP BY DATE_FORMAT(`activos_historia`.`fecha_captura`,'%Y-%m'),`activos_historia`.`id_activo` " + 
				") a " + 
				"GROUP BY a.fec   ";
	
		System.out.println(SQL_NMAQ);
		
		try {
			ResultSet RS_NMAQ = state.executeQuery(SQL_NMAQ);
			
			this.periodos_para_tc_nmaquinas = new String[7];
			
			
				while(RS_NMAQ.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS_NMAQ.getString("fec"))){
							periodos_para_tc_nmaquinas[i]=RS_NMAQ.getString("nMquinas");
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	public void generateTotalVentasImp_para_tc(String id_emp,String idgrupo,String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	
		String SQL_TVIMP = "SELECT sum(`803`.`803_neto`) AS netoTotal"
				+ "		, sum(`803`.`803_neto`/`803`.`803_tipo_cambio`)  AS totalDolaresIMP "
				+ "		, (sum(`803`.`803_tipo_cambio`*`803`.`803_neto`)/sum(`803`.`803_neto`)) AS mediaTipoCambio "
				+ "		, DATE_FORMAT(`803`.`803_fecha`,'%Y-%m') as fec  "
				+ "	FROM `803` "
				+ "	INNER JOIN empresas on empresas.empresas_id=`803`.`clpr_id` "
				+ "	WHERE 	"
				+ "			 `803`.`estados_vig_novig_id`=1 " + 
				"		AND DATE_FORMAT(`803`.`803_fecha`,'%Y-%m') IN ("+per_ids+") "; 
				
	if(id_emp!=null && !id_emp.equals("")) SQL_TVIMP+= "		AND `803`.`clpr_id` = "+id_emp+" ";
	if(idgrupo!=null && !idgrupo.equals("")) SQL_TVIMP+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
	
	if(select_vendedor!=null && select_vendedor.length>0){
		SQL_TVIMP+= "		AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";
	}
		
				
	SQL_TVIMP+="	GROUP BY DATE_FORMAT(`803`.`803_fecha`,'%Y-%m')   ";
	
		System.out.println(SQL_TVIMP);
		
		try {
			ResultSet RS_TVIMP = state.executeQuery(SQL_TVIMP);
			
			this.periodos_para_tc_totalventasimps = new String[7];
			this.periodos_para_tc_totaldolaresimps = new String[7];
			this.periodos_para_tc_valorUnitarioimps = new String[7];
			this.periodos_para_tc_valorUnitarioimpsCL = new String[7];
			this.periodos_para_tc_mediaTC = new String[7];
			
				while(RS_TVIMP.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS_TVIMP.getString("fec"))){
							
							
							int netototal = RS_TVIMP.getInt("netoTotal");
							double dolaresTotal=RS_TVIMP.getDouble("totalDolaresIMP");
							
							
							
							
							if(periodos_para_tc_totalventasimpsNC[i]!=null){
								System.out.println("Descontando :"+Integer.parseInt(periodos_para_tc_totalventasimpsNC[i]));
								netototal-=Integer.parseInt(periodos_para_tc_totalventasimpsNC[i]);
							}
							
							
							if(periodos_para_tc_totaldolaresimpsNC[i]!=null){
								System.out.println("Descontando :"+Double.parseDouble(periodos_para_tc_totaldolaresimpsNC[i]));
								dolaresTotal-=Double.parseDouble(periodos_para_tc_totaldolaresimpsNC[i]);
							}
							
							periodos_para_tc_totalventasimps[i]=netototal+"";
							
							periodos_para_tc_totaldolaresimps[i]=dolaresTotal+"";
							
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


	public void generateTotalNCImp_para_tc(String id_emp,String idgrupo, String select_vendedor[]) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	
		String SQL = "SELECT sum(`841`.`841_neto`) as netoTotal"
				+ "		,DATE_FORMAT(`841`.`841_fecha`,'%Y-%m') as fec" 
				+ "		, sum(`841`.`841_neto`/`841`.`841_tipo_cambio`)  AS totalDolaresIMP "
				
				+ "	FROM `841`" 
				+ "	INNER JOIN empresas on empresas.empresas_id=`841`.`clpr_id` "
				+ " WHERE `841_modulo_ref` IN ('803') AND `841`.estados_vig_novig_id=1 AND `841`.`841_folio` IS NOT NULL  "
				+ "		AND DATE_FORMAT(`841`.`841_fecha`,'%Y-%m') IN ("+per_ids+") " ;
				
	if(id_emp!=null && !id_emp.equals(""))  SQL+= "		AND `841`.`clpr_id` = "+id_emp+" ";
	if(idgrupo!=null && !idgrupo.equals(""))  SQL+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
			
	if(select_vendedor!=null && select_vendedor.length>0){

		SQL+= "	AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";

		}
				
	SQL+="	GROUP BY DATE_FORMAT(`841`.`841_fecha`,'%Y-%m')    ";
	
		System.out.println(SQL);
		
		try {
			ResultSet RS_TVIMP = state.executeQuery(SQL);
			
			this.periodos_para_tc_totalventasimpsNC = new String[7];
			this.periodos_para_tc_totaldolaresimpsNC = new String[7];
			
				while(RS_TVIMP.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS_TVIMP.getString("fec"))){
							
							periodos_para_tc_totalventasimpsNC[i]=RS_TVIMP.getString("netoTotal");
							periodos_para_tc_totaldolaresimpsNC[i]=RS_TVIMP.getString("totalDolaresIMP")+"";
							
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}

	public void generateTotalVentasNOImp_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	
		String SQL = "SELECT sum(`dte_totales`.`Neto`) as netoTotal,DATE_FORMAT(dte_encabezado.`FechaEmision`,'%Y-%m') as fec" + 
				"	FROM `dte_encabezado`" + 
				"	INNER JOIN `dte_totales` ON `dte_totales`.`id_enc`=`dte_encabezado`.`id_dte`" 
				+ "	INNER JOIN empresas on empresas.empresas_id=`dte_encabezado`.`cliente_id` "
				+ " WHERE `Modulo` IN ('801','802','804') "
				+ "		AND DATE_FORMAT(dte_encabezado.`FechaEmision`,'%Y-%m') IN ("+per_ids+") " ;
				
			if(id_emp!=null && !id_emp.equals("")) SQL+= "		AND `dte_encabezado`.`cliente_id` = "+id_emp+" ";
			if(idgrupo!=null && !idgrupo.equals("")) SQL+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
				
			SQL+= " GROUP BY DATE_FORMAT(dte_encabezado.`FechaEmision`,'%Y-%m') "
				
				+"	 ";
			
			if(select_vendedor!=null && select_vendedor.length>0){

				SQL+= "	AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";

				}
	
	
		System.out.println(SQL);
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			this.periodos_para_tc_totalventasnoimps = new String[7];
			
				while(RS.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS.getString("fec"))){
							
							int neto=RS.getInt("netoTotal");
							if(periodos_para_tc_totalventasnoimpsNC[i]!=null)	neto-=Integer.parseInt(periodos_para_tc_totalventasnoimpsNC[i]);
									
							periodos_para_tc_totalventasnoimps[i]=neto+"";
							
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	
	public void generateTotalVentasNOImpNC_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	
		String SQL = "SELECT sum(`841`.`841_neto`) as netoTotal"
				+ "		,DATE_FORMAT(`841`.`841_fecha`,'%Y-%m') as fec" 
				+ "		, sum(`841`.`841_neto`/`841`.`841_tipo_cambio`)  AS totalDolaresIMP "
				
				+ "	FROM `841`" 
				+ "	INNER JOIN empresas on empresas.empresas_id=`841`.`clpr_id` "
				+ " WHERE `841_modulo_ref` NOT IN ('803') AND `841`.estados_vig_novig_id=1 AND `841`.`841_folio` IS NOT NULL  "
				+ "		AND DATE_FORMAT(`841`.`841_fecha`,'%Y-%m') IN ("+per_ids+") " ;
				
	if(id_emp!=null && !id_emp.equals(""))  SQL+= "		AND `841`.`clpr_id` = "+id_emp+" ";
	if(idgrupo!=null && !idgrupo.equals(""))  SQL+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
			
				
	SQL+="	GROUP BY DATE_FORMAT(`841`.`841_fecha`,'%Y-%m')    ";
	
	if(select_vendedor!=null && select_vendedor.length>0){

		SQL+= "	AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";

		}
	
	
		System.out.println(SQL);
		
		try {
			ResultSet RS = state.executeQuery(SQL);
			
			this.periodos_para_tc_totalventasnoimpsNC = new String[7];
			
				while(RS.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS.getString("fec"))){
							
							periodos_para_tc_totalventasnoimpsNC[i]=RS.getString("netoTotal");
							
						}
					}
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}

	
	public void generateNDirecciones_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			per_ids+=",'"+periodosfechas_para_tc[i]+"'";
		}
	
		String SQL_NMAQ = "SELECT count(a.DIRE_ID) AS nDires , a.fec " + 
				"FROM  (" + 
				"SELECT MAX( DISTINCT `ubicacion`.`DIRE_ID`) AS DIRE_ID ,producto.`prod_cuc` AS valorMaquina ,DATE_FORMAT(`activos_historia`.`fecha_captura`,'%Y-%m') as fec , producto.`prod_vida_util_imp` " + 
				"FROM `activos_historia`"
				+ "	INNER JOIN `ubicacion` ON ubicacion.`UBI_ID`=`activos_historia`.`ubi_id` " 
				+ "	INNER JOIN producto ON producto.prod_id=activos_historia.prod_id " 
				+ "	INNER JOIN empresas ON empresas.empresas_id=`activos_historia`.`empresas_id` " 
				+ "	INNER JOIN grupos ON grupos.grupos_id=empresas.grupos_id " 
				
				+ "	WHERE "
				+ "		 " + 
				"		DATE_FORMAT(`activos_historia`.`fecha_captura`,'%Y-%m') IN ("+per_ids+") " + 
				"		AND producto.func_id=6 ";
		
		if(id_emp!=null && !id_emp.equals("")) SQL_NMAQ+= "		AND `activos_historia`.`empresas_id` = "+id_emp+" ";
		if(idgrupo!=null && !idgrupo.equals("")) SQL_NMAQ+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
		if(select_vendedor!=null && select_vendedor.length>0){

			SQL_NMAQ+= "	AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";

			}
		
				SQL_NMAQ+= "	GROUP BY DATE_FORMAT(`activos_historia`.`fecha_captura`,'%Y-%m'),`ubicacion`.`DIRE_ID` " + 
				") a " + 
				"GROUP BY a.fec   ";
	
		System.out.println(SQL_NMAQ);
		
		try {
			ResultSet RS_NMAQ = state.executeQuery(SQL_NMAQ);
			
			this.periodos_para_tc_ndires = new String[7];
			
			
				while(RS_NMAQ.next()){
					
					for(int i =0; i<periodosfechas_para_tc.length;i++){
					
						if(periodosfechas_para_tc[i].equals(RS_NMAQ.getString("fec"))){
							periodos_para_tc_ndires[i]=RS_NMAQ.getString("nDires");
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
	

	public void generateNSuministrosMaquinasEnviatos_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		
		this.periodos_para_tc_nsme = new String[7];
		this.periodos_para_tc_rendimiento = new String[7];
		this.periodos_para_tc_cuc = new String[7]; 
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
				
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=null;
					
					sumi=tb.getNumeroEnvSuministrosMaqConRangoFecha(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					
					
					
					tb.disconect();
					
					periodos_para_tc_nsme[i]=sumi[0]+"";
					periodos_para_tc_cuc[i]=sumi[1];
					periodos_para_tc_rendimiento[i]=sumi[2];
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			
			
		}
		
			
	}
	
	
	public void generateNEnviatos_para_tc_fotocond(String id_emp,String idgrupo, String[] select_vendedor) {
		
		
		this.periodos_para_tc_n_fotocond = new String[7];
		this.periodos_para_tc_rendimiento_fotocond = new String[7];
		this.periodos_para_tc_cuc_fotocond = new String[7]; 
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
				
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=null;
					
					sumi=tb.getNumeroEnvConRangoFecha_fotocond(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					
					
					
					tb.disconect();
					
					periodos_para_tc_n_fotocond[i]=sumi[0]+"";
					periodos_para_tc_cuc_fotocond[i]=sumi[1];
					periodos_para_tc_rendimiento_fotocond[i]=sumi[2];
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
		
			
	}
	
	public void generateNEnviatos_para_tc_consumibles(String id_emp,String idgrupo, String[] select_vendedor) {
		
		
		this.periodos_para_tc_n_consumibles = new String[7];
		this.periodos_para_tc_rendimiento_consumibles = new String[7];
		this.periodos_para_tc_cuc_consumibles = new String[7]; 
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
				
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=null;
					
					sumi=tb.getNumeroEnvConRangoFecha_consumibles(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					
					
					
					tb.disconect();
					
					periodos_para_tc_n_consumibles[i]=sumi[0]+"";
					periodos_para_tc_cuc_consumibles[i]=sumi[1];
					periodos_para_tc_rendimiento_consumibles[i]=sumi[2];
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			
		}
		
			
	}

public void generateNEnviatos_para_tc_repuestos(String id_emp,String idgrupo, String[] select_vendedor) {
		
		
		this.periodos_para_tc_n_repuestos = new String[7];
		this.periodos_para_tc_rendimiento_repuestos = new String[7];
		this.periodos_para_tc_cuc_repuestos = new String[7]; 
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
				
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=null;
					
					sumi=tb.getNumeroEnvConRangoFecha_repuestos(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					
					tb.disconect();
					
					periodos_para_tc_n_repuestos[i]=sumi[0]+"";
					periodos_para_tc_cuc_repuestos[i]=sumi[1];
					periodos_para_tc_rendimiento_repuestos[i]=sumi[2];
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
		}
			
	}
	public void generateNKitEnviatos_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
	
		this.periodos_para_tc_nkite = new String[7];
		this.periodos_para_tc_kiterendimiento = new String[7];
		this.periodos_para_tc_kitecuc = new String[7];
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroKitConRangoFecha(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					periodos_para_tc_nkite[i]=sumi[0]+"";
					periodos_para_tc_kitecuc[i]=sumi[1];
					periodos_para_tc_kiterendimiento[i]=sumi[2];
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
			
			
		}
	
				
	}

	public void generateNTickets_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
		this.periodos_para_tc_ntickets = new String[7];
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					int ntickets=tb.getNumeroTicketsConRangoFecha(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					periodos_para_tc_ntickets[i]=ntickets+"";
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
			
			
		}
		
			
		}



	public void generateNTicketsLogistica_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
	this.periodos_para_tc_nticketsLog = new String[7];
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					int ntickets=tb.getNumeroTicketsConRangoFechaLogistica(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					periodos_para_tc_nticketsLog[i]=ntickets+"";
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			
		}
		
	}


	public void generateNTicketsServTecnico_para_tc(String id_emp,String idgrupo, String[] select_vendedor) {
		
	this.periodos_para_tc_nticketsServTec = new String[7];
		
		for(int i =0; i<periodosfechas_para_tc.length;i++){
			
			if(periodosfechas_para_tc[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodosfechas_para_tc[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					int ntickets=tb.getNumeroTicketsConRangoFechaServTecnico(periodosfechas_para_tc[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					periodos_para_tc_nticketsServTec[i]=ntickets+"";
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			
		}
			
	}
	

	public void generateTotales(String id_emp,String idgrupo, String[] select_vendedor) {
		
		String per_ids="'0'";
	
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			per_ids+=",'"+periodos_para_tc_ids12[i]+"'";
		}
		
		this.periodos_para_tc_totales = new String[26];
		
		
		String ventasImpNC12[]=getTotalVentasImpNC12Periodos(per_ids,id_emp,idgrupo,select_vendedor);
		
		
		String ventasImp12[]=getTotalVentasImp12Periodos(per_ids,id_emp,idgrupo,select_vendedor);
		String envioSum[]=getNSuministrosMaquinasEnviatos12Periodos(id_emp,idgrupo,select_vendedor);
		String envioKit[]=getNKitEnviatos12Periodos(id_emp,idgrupo,select_vendedor);
		
		int ventasImp=0;
		double ventasImpDolares=0;
		if(ventasImp12[0]!=null)ventasImp=Integer.parseInt(ventasImp12[0]);
		
		if(ventasImpNC12[0]!=null)ventasImp-=Integer.parseInt(ventasImpNC12[0]);
		
		periodos_para_tc_totales[0]=ventasImp+"";
		
		if(ventasImp12[1]!=null)ventasImpDolares=Double.parseDouble(ventasImp12[1]);
		
		if(ventasImpNC12[1]!=null)ventasImpDolares-=Double.parseDouble(ventasImpNC12[1]);
		
		periodos_para_tc_totales[1]=getNImp_12periodos(per_ids,id_emp,idgrupo,select_vendedor);
		
		periodos_para_tc_totales[2]=ventasImpDolares+"";
		periodos_para_tc_totales[3]=getTicketsLogistica12Periodos(id_emp,idgrupo,select_vendedor);
		periodos_para_tc_totales[4]=generateNTicketsServTecnico12Periodos(id_emp,idgrupo,select_vendedor);
		
		
		if(periodos_para_tc_totales[0]!=null && periodos_para_tc_totales[2]!=null && Float.parseFloat(periodos_para_tc_totales[2])>0){
			periodos_para_tc_totales[5]=(Float.parseFloat(periodos_para_tc_totales[0])/Float.parseFloat(periodos_para_tc_totales[2]))+"";	
		}
		
		periodos_para_tc_totales[6]=envioSum[0];
		periodos_para_tc_totales[7]=envioSum[1];
		periodos_para_tc_totales[8]=envioSum[2];
		
		
		
		
		periodos_para_tc_totales[9]=getTotalVentasSinImp12Periodos(per_ids,id_emp,idgrupo);
		
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
		
		String envioFotoCond[]=getNEnviatos12Periodos_fotocond(id_emp,idgrupo,select_vendedor);
		String envioConsumibles[]=getNEnviatos12Periodos_consumibles(id_emp,idgrupo,select_vendedor);
		String envioRepuestos[]=getNEnviatos12Periodos_repuestos(id_emp,idgrupo,select_vendedor);
		
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
		if(periodos_para_tc_totales[9]!=null) valorOtros=Float.parseFloat(periodos_para_tc_totales[9]);
		periodos_para_tc_totales[15]=(valorImp+valorOtros)+"";
			
	}
	

	private String getNImp_12periodos(String per_ids,String id_emp,String idgrupo, String[] select_vendedor) {
		String nImp="0";
	 
		String SQL_NIMPS = "SELECT (sum(`803`.`803_n_impresiones`) - IF(sum(`841`.`841_n_impresiones`) IS NULL ,0,sum(`841`.`841_n_impresiones`))) AS nIMPS "
				+ "	FROM `803` "
				+ "	INNER JOIN empresas on empresas.empresas_id=`803`.`clpr_id`"
				+ "	LEFT JOIN `841` ON (`841`.`estados_vig_novig_id`=1 AND `841`.`folioref`=`803`.`803_folio` AND `841`.`841_est_aprobacion`=2)   "
			    
				+ "	WHERE "
				+ "		 `803`.`estados_vig_novig_id`=1 " 
				+ "		AND DATE_FORMAT(`803`.`803_fecha`,'%Y-%m') IN ("+per_ids+") "; 
				
		if(id_emp!=null && !id_emp.equals("")) SQL_NIMPS+= "		AND `803`.`clpr_id` = "+id_emp+" ";
		if(idgrupo!=null && !idgrupo.equals("")) SQL_NIMPS+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
		
		
		if(select_vendedor!=null && select_vendedor.length>0){
			SQL_NIMPS+= "		AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";
		}
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
	public String[] getTotalVentasImpNC12Periodos(String per_ids,String id_emp,String idgrupo, String[] select_vendedor) {
		
		String totalNeto="0";
		String totalNetoDolares="0";
		
		String SQL = "SELECT sum(`841`.`841_neto`) as netoTotal"
				+ "		,DATE_FORMAT(`841`.`841_fecha`,'%Y-%m') as fec" 
				+ "		, sum(`841`.`841_neto`/`841`.`841_tipo_cambio`)  AS totalDolaresIMP "
				
				+ "	FROM `841`" 
				+ "	INNER JOIN empresas on empresas.empresas_id=`841`.`clpr_id` "
				+ " WHERE `841_modulo_ref` IN ('803') AND `841`.estados_vig_novig_id=1 AND `841`.`841_folio` IS NOT NULL  "
				+ "		AND DATE_FORMAT(`841`.`841_fecha`,'%Y-%m') IN ("+per_ids+") " ;
				
	if(id_emp!=null && !id_emp.equals(""))  SQL+= "		AND `841`.`clpr_id` = "+id_emp+" ";
	if(idgrupo!=null && !idgrupo.equals(""))  SQL+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
			
				
	SQL+="	GROUP BY DATE_FORMAT(`841`.`841_fecha`,'%Y-%m')    ";
	
	if(select_vendedor!=null && select_vendedor.length>0){

		SQL+= "	AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";

		}
	
		System.out.println(SQL);
		
		try {
			ResultSet RS_TVIMP = state.executeQuery(SQL);
			
			if(RS_TVIMP.next()){
				
				totalNeto=RS_TVIMP.getString("netoTotal");
				totalNetoDolares=RS_TVIMP.getString("totalDolaresIMP");
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new String[]{totalNeto,totalNetoDolares};
		
			
	}	
	public String[] getTotalVentasImp12Periodos(String per_ids,String id_emp,String idgrupo,String[] select_vendedor) {
		String totalNeto="0";
		String totalNetoDolares="0";
		
		String SQL_TVIMP = "SELECT sum(`803`.`803_neto`) AS netoTotal"
				+ "		, sum(`803`.`803_neto`/`803`.`803_tipo_cambio`)  AS totalDolaresIMP "
				+ "		, (sum(`803`.`803_tipo_cambio`*`803`.`803_neto`)/sum(`803`.`803_neto`)) AS mediaTipoCambio "
				+ "	FROM `803` "
				+ "	INNER JOIN empresas on empresas.empresas_id=`803`.`clpr_id` "
			    + "	WHERE 	"
			    + "		 `803`.`estados_vig_novig_id`=1 " + 
			    "		AND DATE_FORMAT(`803`.`803_fecha`,'%Y-%m') IN ("+per_ids+") " +
				"		 " + 
				"	 ";
	
			if(id_emp!=null && !id_emp.equals("")) SQL_TVIMP+= "		AND `803`.`clpr_id` = "+id_emp+" ";
		if(idgrupo!=null && !idgrupo.equals("")) SQL_TVIMP+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
		
		if(select_vendedor!=null && select_vendedor.length>0){
			SQL_TVIMP+= "		AND `empresas`.`responsable_id` IN ("+UtilFunctions.implode(",", select_vendedor)+") ";
		}
		
		
		
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
	
public String getTotalVentasSinImp12Periodos(String per_ids,String id_emp,String idgrupo) {
		String totalNeto="0";
		
		String SQL_VENTASSINIMP = "SELECT sum(`dte_totales`.`Neto`) as neto  " + 
				"	FROM `dte_encabezado`" 
				+ "	INNER JOIN empresas on empresas.empresas_id=`dte_encabezado`.`cliente_id` "
				+ "	INNER JOIN `dte_totales` ON `dte_totales`.`id_enc`=`dte_encabezado`.`id_dte`"  
				+ " WHERE `Modulo` IN ('801','802','804') "
				+ "		AND DATE_FORMAT(dte_encabezado.`FechaEmision`,'%Y-%m') IN ("+per_ids+") " 
				+"	 ";
		
		
		
	if(id_emp!=null && !id_emp.equals("")) SQL_VENTASSINIMP+= "		AND `dte_encabezado`.`cliente_id` = "+id_emp+" ";
	if(idgrupo!=null && !idgrupo.equals("")) SQL_VENTASSINIMP+= "		AND `empresas`.`grupos_id` = "+idgrupo+" ";
	
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
	
	public String getTicketsLogistica12Periodos(String id_emp,String idgrupo, String[] select_vendedor) {
		
		//primero traemos un listado de los periodos con sus fechas a consultar 
		
		int ntickets=0;
	 	
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			if(periodos_para_tc_ids12[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
					
					
					System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					ntickets+=tb.getNumeroTicketsConRangoFechaLogistica(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
			
		}	
		
		return ntickets+"";
			
	}
	
	public String generateNTicketsServTecnico12Periodos(String id_emp,String idgrupo, String[] select_vendedor) {
			
		//primero traemos un listado de los periodos con sus fechas a consultar 
		int ntickets=0;
		 
		
		
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			if(periodos_para_tc_ids12[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					ticketsBirtDAO tb= new ticketsBirtDAO();
					ntickets+=tb.getNumeroTicketsConRangoFechaServTecnico(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
			
		}	
		
		return ntickets+"";
	}
	
	public String[] getNSuministrosMaquinasEnviatos12Periodos(String id_emp,String idgrupo, String[] select_vendedor) {
		
		float nSME=0;
		float nSMECUC=0;
		float nSMEREND=0;
		
		
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			if(periodos_para_tc_ids12[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvSuministrosMaqConRangoFecha(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					nSME+=Float.parseFloat(sumi[0]);
					nSMECUC+=Float.parseFloat(sumi[1]);
					nSMEREND+=Float.parseFloat(sumi[2]);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
				
		}	
		
		return new String[]{nSME+"",nSMECUC+"",nSMEREND+""};
			
	}
	
	public String[] getNKitEnviatos12Periodos(String id_emp,String idgrupo, String[] select_vendedor) {
		
		float nKIT=0;
		float nKITCUC=0;
		float nKITREND=0;
		
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			if(periodos_para_tc_ids12[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroKitConRangoFecha(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					nKIT+=Float.parseFloat(sumi[0]);
					nKITCUC+=Float.parseFloat(sumi[1]);
					nKITREND+=Float.parseFloat(sumi[2]);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
				
		}	
		
		return new String[]{nKIT+"",nKITCUC+"",nKITREND+""};
		
			
	}
	public String[] getNEnviatos12Periodos_fotocond(String id_emp,String idgrupo, String[] select_vendedor) {
		
		float nKIT=0;
		float nKITCUC=0;
		float nKITREND=0;
		
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			if(periodos_para_tc_ids12[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvConRangoFecha_fotocond(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					nKIT+=Float.parseFloat(sumi[0]);
					nKITCUC+=Float.parseFloat(sumi[1]);
					nKITREND+=Float.parseFloat(sumi[2]);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
			
		}	
		
		return new String[]{nKIT+"",nKITCUC+"",nKITREND+""};
		
			
	}
	
public String[] getNEnviatos12Periodos_consumibles(String id_emp,String idgrupo, String[] select_vendedor) {
		
		float nKIT=0;
		float nKITCUC=0;
		float nKITREND=0;
		
		for(int i =0; i<periodos_para_tc_ids12.length;i++){
			
			if(periodos_para_tc_ids12[i]!=null){
				try{
					String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
					
					
					//System.out.println(SQL);
					ResultSet RS = state.executeQuery(SQL);
					RS.next();
					
					trasladosBirtDAO tb= new trasladosBirtDAO();
					String[] sumi=tb.getNumeroEnvConRangoFecha_consumibles(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
					tb.disconect();
					
					nKIT+=Float.parseFloat(sumi[0]);
					nKITCUC+=Float.parseFloat(sumi[1]);
					nKITREND+=Float.parseFloat(sumi[2]);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
		}	
		
		return new String[]{nKIT+"",nKITCUC+"",nKITREND+""};
		
			
	}
	
	public String[] getNEnviatos12Periodos_repuestos(String id_emp,String idgrupo, String[] select_vendedor) {
	
	float n=0;
	float nCUC=0;
	float nREND=0;
	
	for(int i =0; i<periodos_para_tc_ids12.length;i++){
		if(periodos_para_tc_ids12[i]!=null){
			try{
				String SQL = "SELECT LAST_DAY('"+periodos_para_tc_ids12[i]+"-01') AS LASTDAY ";
				
				
				System.out.println(SQL);
				ResultSet RS = state.executeQuery(SQL);
				RS.next();
				
				trasladosBirtDAO tb= new trasladosBirtDAO();
				String[] sumi=tb.getNumeroEnvConRangoFecha_repuestos(periodos_para_tc_ids12[i]+"-01 00:00:00", RS.getString("LASTDAY")+" 23:59:59",id_emp,idgrupo,select_vendedor);
				tb.disconect();
				
				n+=Float.parseFloat(sumi[0]);
				nCUC+=Float.parseFloat(sumi[1]);
				nREND+=Float.parseFloat(sumi[2]);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
				
		}
		
	}	
	
	return new String[]{n+"",nCUC+"",nREND+""};
	
		
}
	public rep10DAO(){
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
				
				e.printStackTrace();
			}
		}
}
