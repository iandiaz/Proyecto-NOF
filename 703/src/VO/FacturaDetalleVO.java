package VO;

public class FacturaDetalleVO {
	String ubi_id,id_activo,estrcobro_id,anc_id,nimp,id_fact,descripcion;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	String es_activo,es_anexo,es_estructuracobro;
	public String getEs_activo() {
		return es_activo;
	}
	public String getEs_anexo() {
		return es_anexo;
	}
	public String getEs_estructuracobro() {
		return es_estructuracobro;
	}
	public void setEs_activo(String es_activo) {
		this.es_activo = es_activo;
	}
	public void setEs_anexo(String es_anexo) {
		this.es_anexo = es_anexo;
	}
	public void setEs_estructuracobro(String es_estructuracobro) {
		this.es_estructuracobro = es_estructuracobro;
	}
	public String getAnc_id() {
		return anc_id;
	}
	public void setAnc_id(String anc_id) {
		this.anc_id = anc_id;
	}
	String precioCF;
	String precioCV;
	
	public String getPrecioCF() {
		return precioCF;
	}
	public String getPrecioCV() {
		return precioCV;
	}
	public void setPrecioCF(String precioCF) {
		this.precioCF = precioCF;
	}
	public void setPrecioCV(String precioCV) {
		this.precioCV = precioCV;
	}
	public FacturaDetalleVO() {
		super();
	
	}
	public String getUbi_id() {
		return ubi_id;
	}
	public String getId_fact() {
		return id_fact;
	}
	public void setId_fact(String id_fact) {
		this.id_fact = id_fact;
	}
	public String getId_activo() {
		return id_activo;
	}
	public String getEstrcobro_id() {
		return estrcobro_id;
	}
	public String getNimp() {
		return nimp;
	}
	public void setUbi_id(String ubi_id) {
		this.ubi_id = ubi_id;
	}
	public void setId_activo(String id_activo) {
		this.id_activo = id_activo;
	}
	public void setEstrcobro_id(String estrcobro_id) {
		this.estrcobro_id = estrcobro_id;
	}
	public void setNimp(String nimp) {
		this.nimp = nimp;
	}
	
}
