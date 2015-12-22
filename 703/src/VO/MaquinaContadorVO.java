package VO;

public class MaquinaContadorVO {
	//-----ID OBJETO-----//
	private String ubi_id;
	private String activo_tptc_id;
	///////
	
	private String ubi_fisica;
	private String anc_id;
	private String descripcion;
	private String id_activo;
	private String tp_tc_id;
	private String tp_tc_nombre;
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

	public MaquinaContadorVO(String ubi_id, String activo_tptc_id) {
		super();
		this.ubi_id = ubi_id;
		this.activo_tptc_id = activo_tptc_id;
	}

	public MaquinaContadorVO() {
		super();
		
	}

	public String getUbi_id() {
		return ubi_id;
	}


	public String getUbi_fisica() {
		return ubi_fisica;
	}


	public String getAnc_id() {
		return anc_id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public String getId_activo() {
		return id_activo;
	}


	public String getTp_tc_id() {
		return tp_tc_id;
	}


	public String getTp_tc_nombre() {
		return tp_tc_nombre;
	}


	public String getActivo_tptc_id() {
		return activo_tptc_id;
	}


	public void setUbi_id(String ubi_id) {
		this.ubi_id = ubi_id;
	}


	public void setUbi_fisica(String ubi_fisica) {
		this.ubi_fisica = ubi_fisica;
	}


	public void setAnc_id(String anc_id) {
		this.anc_id = anc_id;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setId_activo(String id_activo) {
		this.id_activo = id_activo;
	}


	public void setTp_tc_id(String tp_tc_id) {
		this.tp_tc_id = tp_tc_id;
	}


	public void setTp_tc_nombre(String tp_tc_nombre) {
		this.tp_tc_nombre = tp_tc_nombre;
	}


	public void setActivo_tptc_id(String activo_tptc_id) {
		this.activo_tptc_id = activo_tptc_id;
	}


	@Override
    public boolean equals(Object o){
		MaquinaContadorVO maqCon = (MaquinaContadorVO) o;
        if(o == null){
            return false;
        }
        if((this.ubi_id.equals(maqCon.getUbi_id())) && (this.activo_tptc_id.equals(maqCon.getActivo_tptc_id()))){
            return true;
        }
        return false;
    }
}
