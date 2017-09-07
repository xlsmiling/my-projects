package crmOrder;

public class GeneralPortTypeProxy implements crmOrder.GeneralPortType {
  private String _endpoint = null;
  private crmOrder.GeneralPortType generalPortType = null;
  
  public GeneralPortTypeProxy() {
    _initGeneralPortTypeProxy();
  }
  
  public GeneralPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initGeneralPortTypeProxy();
  }
  
  private void _initGeneralPortTypeProxy() {
    try {
      generalPortType = (new crmOrder.GeneralLocator()).getgeneralHttpSoap11Endpoint();
      if (generalPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)generalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)generalPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (generalPortType != null)
      ((javax.xml.rpc.Stub)generalPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public crmOrder.GeneralPortType getGeneralPortType() {
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType;
  }
  
  public java.lang.String updateData(java.lang.String sysSource, java.lang.String updateString) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.updateData(sysSource, updateString);
  }
  
  public java.lang.String deleteDataV(java.lang.String sysSource, java.lang.String deleteString, java.lang.String tableName) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.deleteDataV(sysSource, deleteString, tableName);
  }
  
  public java.lang.String deleteData(java.lang.String sysSource, java.lang.String deleteString) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.deleteData(sysSource, deleteString);
  }
  
  public java.lang.String getData(java.lang.String sysSource, java.lang.String queryString) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.getData(sysSource, queryString);
  }
  
  public java.lang.String getDataV(java.lang.String sysSource, java.lang.String queryString, java.lang.String tableName) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.getDataV(sysSource, queryString, tableName);
  }
  
  public java.lang.String updateDataV(java.lang.String sysSource, java.lang.String updateString, java.lang.String tableName) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.updateDataV(sysSource, updateString, tableName);
  }
  
  public java.lang.String storeData(java.lang.String sysSource, java.lang.String storeString) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.storeData(sysSource, storeString);
  }
  
  public java.lang.String storeDataV(java.lang.String sysSource, java.lang.String storeString, java.lang.String tableName) throws java.rmi.RemoteException{
    if (generalPortType == null)
      _initGeneralPortTypeProxy();
    return generalPortType.storeDataV(sysSource, storeString, tableName);
  }
  
  
}