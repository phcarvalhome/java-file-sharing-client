package com.phcarvalho.filesharing;


/**
* com/phcarvalho/filesharing/ServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JavaFileSharingIDL.idl
* Domingo, 13 de Outubro de 2019 22h52min48s BRT
*/

public abstract class ServerPOA extends org.omg.PortableServer.Servant
 implements ServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    Integer __method = (Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:com/phcarvalho/filesharing/Server:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Server _this() 
  {
    return ServerHelper.narrow(
    super._this_object());
  }

  public Server _this(org.omg.CORBA.ORB orb) 
  {
    return ServerHelper.narrow(
    super._this_object(orb));
  }


} // class ServerPOA