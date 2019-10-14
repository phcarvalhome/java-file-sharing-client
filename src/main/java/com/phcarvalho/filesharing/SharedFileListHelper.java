package com.phcarvalho.filesharing;


/**
* com/phcarvalho/filesharing/SharedFileListHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JavaFileSharingIDL.idl
* Domingo, 13 de Outubro de 2019 22h52min48s BRT
*/

abstract public class SharedFileListHelper
{
  private static String  _id = "IDL:com/phcarvalho/filesharing/SharedFileList:1.0";

  public static void insert (org.omg.CORBA.Any a, SharedFileList that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static SharedFileList extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = SharedFileHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "sharedFileList",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (SharedFileListHelper.id (), "SharedFileList", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static SharedFileList read (org.omg.CORBA.portable.InputStream istream)
  {
    SharedFileList value = new SharedFileList ();
    int _len0 = istream.read_long ();
    value.sharedFileList = new SharedFile[_len0];
    for (int _o1 = 0;_o1 < value.sharedFileList.length; ++_o1)
      value.sharedFileList[_o1] = SharedFileHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, SharedFileList value)
  {
    ostream.write_long (value.sharedFileList.length);
    for (int _i0 = 0;_i0 < value.sharedFileList.length; ++_i0)
      SharedFileHelper.write (ostream, value.sharedFileList[_i0]);
  }

}