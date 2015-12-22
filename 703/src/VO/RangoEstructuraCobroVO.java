package VO;

public class RangoEstructuraCobroVO {
	private String rango_id;
	private String rango_inicial;
	private String rango_final;
	
	private String rango_costo_variable;
	private String rango_costo_fijo;
	private String estrcobro_id;
	
	private EstadosVigNoVigVO estadoVignoVig;
	
	public EstadosVigNoVigVO getEstadoVignoVig() {
		return estadoVignoVig;
	}

	public void setEstadoVignoVig(EstadosVigNoVigVO estadoVignoVig) {
		this.estadoVignoVig = estadoVignoVig;
	}

	public String getEstrcobro_id() {
		return estrcobro_id;
	}

	public void setEstrcobro_id(String estrcobro_id) {
		this.estrcobro_id = estrcobro_id;
	}

	public String getRango_costo_variable() {
		return rango_costo_variable;
	}

	public void setRango_costo_variable(String rango_costo_variable) {
		this.rango_costo_variable = rango_costo_variable;
	}

	public String getRango_costo_fijo() {
		return rango_costo_fijo;
	}

	public void setRango_costo_fijo(String rango_costo_fijo) {
		this.rango_costo_fijo = rango_costo_fijo;
	}

	public String getRango_final() {
		return rango_final;
	}

	public void setRango_final(String rango_final) {
		this.rango_final = rango_final;
	}

	public String getRango_inicial() {
		return rango_inicial;
	}

	public void setRango_inicial(String rango_inicial) {
		this.rango_inicial = rango_inicial;
	}

	public RangoEstructuraCobroVO() {
		super();
		
	}

	public String getRango_id() {
		return rango_id;
	}

	public void setRango_id(String rango_id) {
		this.rango_id = rango_id;
	}
	

}
