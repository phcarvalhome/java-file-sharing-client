package com.phcarvalho.filesharing;


/**
* com/phcarvalho/filesharing/ClientOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JavaFileSharingIDL.idl
* Domingo, 13 de Outubro de 2019 22h52min48s BRT
*/

public interface ClientOperations 
{
  void sendSharedFileList(SharedFileList sharedFileList);
  SharedFile downloadSharedFile(String name);
} // interface ClientOperations