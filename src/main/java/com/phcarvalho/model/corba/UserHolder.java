package com.phcarvalho.model.corba;

/**
* com/phcarvalho/model/corba/UserHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharingIDL.idl
* Quarta-feira, 16 de Outubro de 2019 03h30min06s BRT
*/

public final class UserHolder implements org.omg.CORBA.portable.Streamable
{
  public User value = null;

  public UserHolder ()
  {
  }

  public UserHolder (User initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = UserHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    UserHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return UserHelper.type ();
  }

}
