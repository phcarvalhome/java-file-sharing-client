package com.phcarvalho.filesharing;


/**
* com/phcarvalho/filesharing/SharedFile.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JavaFileSharingIDL.idl
* Domingo, 13 de Outubro de 2019 22h52min48s BRT
*/

public final class SharedFile implements org.omg.CORBA.portable.IDLEntity
{
  public User user = null;
  public String name = null;
  public String extension = null;
  public String content = null;

  public SharedFile ()
  {
  } // ctor

  public SharedFile (User _user, String _name, String _extension, String _content)
  {
    user = _user;
    name = _name;
    extension = _extension;
    content = _content;
  } // ctor

} // class SharedFile
