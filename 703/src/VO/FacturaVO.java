package VO;

public class FacturaVO {
	private String fecha,condpago,id_emisor;
	private String id_cliente,cliente_rz,cliente_rut,dir_direccion,dir_region,dir_ciudad,dir_comuna,peri_tc_id,peri_tc_idNombre;
	private String dire_id;
	private String id;
	private EstadosVigNoVigVO estadoVigNoVig;
	private String subtotal,desc,neto,iva,total,glosa_desc,n_imp,tcambio,observaciones;
	private PeriodoTcVO periodoFac;
	
	public PeriodoTcVO getPeriodoFac() {
		return periodoFac;
	}

	public void setPeriodoFac(PeriodoTcVO periodoFac) {
		this.periodoFac = periodoFac;
	}

	public String getPeri_tc_idNombre() {
		return peri_tc_idNombre;
	}

	public void setPeri_tc_idNombre(String peri_tc_idNombre) {
		this.peri_tc_idNombre = peri_tc_idNombre;
	}

	public String getGlosa_desc() {
		return glosa_desc;
	}

	public void setGlosa_desc(String glosa_desc) {
		this.glosa_desc = glosa_desc;
	}

	private EmpresaVO emisor;
	private PeriodoTcVO periodo;
	
	private TipoCambioVO tipo_cambio;
	
	public TipoCambioVO getTipo_cambio() {
		return tipo_cambio;
	}

	public void setTipo_cambio(TipoCambioVO tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}

	public PeriodoTcVO getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoTcVO periodo) {
		this.periodo = periodo;
	}

	public String getCliente_rut() {
		return cliente_rut;
	}

	public void setCliente_rut(String cliente_rut) {
		this.cliente_rut = cliente_rut;
	}

	public EmpresaVO getEmisor() {
		return emisor;
	}

	public void setEmisor(EmpresaVO emisor) {
		this.emisor = emisor;
	}

	public EstadosVigNoVigVO getEstadoVigNoVig() {
		return estadoVigNoVig;
	}

	public void setEstadoVigNoVig(EstadosVigNoVigVO estadoVigNoVig) {
		this.estadoVigNoVig = estadoVigNoVig;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDire_id() {
		return dire_id;
	}

	public void setDire_id(String dire_id) {
		this.dire_id = dire_id;
	}
	
	public String getFecha() {
		return fecha;
	}


	public String getCondpago() {
		return condpago;
	}

	public String getId_emisor() {
		return id_emisor;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public String getCliente_rz() {
		return cliente_rz;
	}

	public String getDir_direccion() {
		return dir_direccion;
	}

	public String getDir_region() {
		return dir_region;
	}

	public String getDir_ciudad() {
		return dir_ciudad;
	}

	public String getDir_comuna() {
		return dir_comuna;
	}

	public String getPeri_tc_id() {
		return peri_tc_id;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public String getDesc() {
		return desc;
	}

	public String getNeto() {
		return neto;
	}

	public String getIva() {
		return iva;
	}

	public String getTotal() {
		return total;
	}

	public String getN_imp() {
		return n_imp;
	}

	public String getTcambio() {
		return tcambio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setCondpago(String condpago) {
		this.condpago = condpago;
	}

	public void setId_emisor(String id_emisor) {
		this.id_emisor = id_emisor;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public void setCliente_rz(String cliente_rz) {
		this.cliente_rz = cliente_rz;
	}

	public void setDir_direccion(String dir_direccion) {
		this.dir_direccion = dir_direccion;
	}

	public void setDir_region(String dir_region) {
		this.dir_region = dir_region;
	}

	public void setDir_ciudad(String dir_ciudad) {
		this.dir_ciudad = dir_ciudad;
	}

	public void setDir_comuna(String dir_comuna) {
		this.dir_comuna = dir_comuna;
	}

	public void setPeri_tc_id(String peri_tc_id) {
		this.peri_tc_id = peri_tc_id;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setNeto(String neto) {
		this.neto = neto;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setN_imp(String n_imp) {
		this.n_imp = n_imp;
	}

	public void setTcambio(String tcambio) {
		this.tcambio = tcambio;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public FacturaVO() {
		super();
	
	}

}
