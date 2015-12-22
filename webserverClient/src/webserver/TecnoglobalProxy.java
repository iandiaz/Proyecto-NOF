package webserver;

public class TecnoglobalProxy implements webserver.Tecnoglobal {
  private String _endpoint = null;
  private webserver.Tecnoglobal tecnoglobal = null;
  
  public TecnoglobalProxy() {
    _initTecnoglobalProxy();
  }
  
  public TecnoglobalProxy(String endpoint) {
    _endpoint = endpoint;
    _initTecnoglobalProxy();
  }
  
  private void _initTecnoglobalProxy() {
    try {
      tecnoglobal = (new webserver.TecnoglobalServiceLocator()).gettecnoglobal();
      if (tecnoglobal != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tecnoglobal)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tecnoglobal)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tecnoglobal != null)
      ((javax.xml.rpc.Stub)tecnoglobal)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webserver.Tecnoglobal getTecnoglobal() {
    if (tecnoglobal == null)
      _initTecnoglobalProxy();
    return tecnoglobal;
  }
  
  public java.lang.String inputFactOC(java.lang.String fechadoc, java.lang.String rut, int sucursal, int tipodoc, java.lang.String estdoc, int numdoc, java.lang.String linea, java.lang.String codigoarticulo, java.lang.String partnumber, java.lang.String serie, java.lang.String descripcionarticulo, float cantidadlinea, float preciounitario, java.lang.String ordencompra, int notaventa, java.lang.String observacion, int codigomoneda, float valormoneda, java.lang.String glosamoneda) throws java.rmi.RemoteException{
    if (tecnoglobal == null)
      _initTecnoglobalProxy();
    return tecnoglobal.inputFactOC(fechadoc, rut, sucursal, tipodoc, estdoc, numdoc, linea, codigoarticulo, partnumber, serie, descripcionarticulo, cantidadlinea, preciounitario, ordencompra, notaventa, observacion, codigomoneda, valormoneda, glosamoneda);
  }
  
  
}