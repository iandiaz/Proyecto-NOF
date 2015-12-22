package VO;

import java.util.Calendar;

public class BoletaVO {

	private int id; 
	private String RUTCliente;
	private String RSCliente;
	private String GiroCliente;
	private String DirCliente;
	private String ComCliente;
	private String CiuCliente;
	private String Email;
	private int n_proceso;
	private int monto_precio;
	private Calendar fecha;
	private int id_producto;
	private int id_clpr; 
	private String TipoDTE;
	private String empresa_emisora;
	private int monto_iva;
	private int monto_total;
	private int folio; 
	private String id_dte; 
	private String n_impresiones;
	
	
	public String getId_dte() {
		return id_dte;
	}
	public void setId_dte(String id_dte) {
		this.id_dte = id_dte;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public int getMonto_iva() {
		return monto_iva;
	}
	public void setMonto_iva(int monto_iva) {
		this.monto_iva = monto_iva;
	}
	public int getMonto_total() {
		return monto_total;
	}
	public void setMonto_total(int monto_total) {
		this.monto_total = monto_total;
	}
	public String getEmpresa_emisora() {
		return empresa_emisora;
	}
	public void setEmpresa_emisora(String empresa_emisora) {
		this.empresa_emisora = empresa_emisora;
	}
	public String getTipoDTE() {
		return TipoDTE;
	}
	public void setTipoDTE(String tipoDTE) {
		TipoDTE = tipoDTE;
	}
	public int getId_clpr() {
		return id_clpr;
	}
	public void setId_clpr(int id_clpr) {
		this.id_clpr = id_clpr;
	}
	public BoletaVO() {
		super();
	
	}
	public String getN_impresiones() {
		return n_impresiones;
	}
	public void setN_impresiones(String n_impresiones) {
		this.n_impresiones = n_impresiones;
	}
	public String getRUTCliente() {
		return RUTCliente;
	}
	public void setRUTCliente(String rUTCliente) {
		RUTCliente = rUTCliente;
	}
	public String getRSCliente() {
		return RSCliente;
	}
	public void setRSCliente(String rSCliente) {
		RSCliente = rSCliente;
	}
	public String getGiroCliente() {
		return GiroCliente;
	}
	public void setGiroCliente(String giroCliente) {
		GiroCliente = giroCliente;
	}
	public String getDirCliente() {
		return DirCliente;
	}
	public void setDirCliente(String dirCliente) {
		DirCliente = dirCliente;
	}
	public String getComCliente() {
		return ComCliente;
	}
	public void setComCliente(String comCliente) {
		ComCliente = comCliente;
	}
	public String getCiuCliente() {
		return CiuCliente;
	}
	public void setCiuCliente(String ciuCliente) {
		CiuCliente = ciuCliente;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getN_proceso() {
		return n_proceso;
	}
	public void setN_proceso(int n_proceso) {
		this.n_proceso = n_proceso;
	}
	public int getMonto_precio() {
		return monto_precio;
	}
	public void setMonto_precio(int monto_precio) {
		this.monto_precio = monto_precio;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
	
	
}
