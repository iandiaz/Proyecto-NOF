package VO;

public class ActivoVO {
	private String id;
	private String serie;
	private String n_imps;
	private String costo_fijo;
	private String costo_variable;
	
	public String getCosto_fijo() {
		return costo_fijo;
	}

	public String getCosto_variable() {
		return costo_variable;
	}

	public void setCosto_fijo(String costo_fijo) {
		this.costo_fijo = costo_fijo;
	}

	public void setCosto_variable(String costo_variable) {
		this.costo_variable = costo_variable;
	}

	public String getN_imps() {
		return n_imps;
	}

	public void setN_imps(String n_imps) {
		this.n_imps = n_imps;
	}

	public String getId() {
		return id;
	}

	public String getSerie() {
		return serie;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public ActivoVO() {
		super();
		
	}
	public ActivoVO(String id) {
		super();
		this.id=id;
	}
	
	@Override
    public boolean equals(Object o){
		ActivoVO activo = (ActivoVO) o;
        if(o == null){
            return false;
        }
        if((this.id.equals(activo.getId()))){
            return true;
        }
        return false;
    }

}
