package com.phcarvalho.model.corba;


/**
* com/phcarvalho/model/corba/FileData.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharingIDL.idl
* Quarta-feira, 16 de Outubro de 2019 03h30min06s BRT
*/

public final class FileData implements org.omg.CORBA.portable.IDLEntity
{
  public com.phcarvalho.model.corba.FileMetadata metadata = null;
  public String content = null;

  public FileData ()
  {
  } // ctor

  public FileData (com.phcarvalho.model.corba.FileMetadata _metadata, String _content)
  {
    metadata = _metadata;
    content = _content;
  } // ctor

} // class FileData
