package com.phcarvalho.model.corba;

/**
* com/phcarvalho/model/corba/SharedFileHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharingIDL.idl
* Segunda-feira, 14 de Outubro de 2019 22h17min22s BRT
*/

public final class SharedFileHolder implements org.omg.CORBA.portable.Streamable
{
  public SharedFile value = null;

  public SharedFileHolder ()
  {
  }

  public SharedFileHolder (SharedFile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SharedFileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SharedFileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SharedFileHelper.type ();
  }

}