package com.phcarvalho.filesharing;


/**
* com/phcarvalho/filesharing/_ClientStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from JavaFileSharingIDL.idl
* Domingo, 13 de Outubro de 2019 22h52min48s BRT
*/

public class _ClientStub extends org.omg.CORBA.portable.ObjectImpl implements Client
{

  public void sendSharedFileList (SharedFileList sharedFileList)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendSharedFileList", true);
                SharedFileListHelper.write ($out, sharedFileList);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendSharedFileList (sharedFileList        );
            } finally {
                _releaseReply ($in);
            }
  } // sendSharedFileList

  public SharedFile downloadSharedFile (String name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("downloadSharedFile", true);
                $out.write_string (name);
                $in = _invoke ($out);
                SharedFile $result = SharedFileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return downloadSharedFile (name        );
            } finally {
                _releaseReply ($in);
            }
  } // downloadSharedFile

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:com/phcarvalho/filesharing/Client:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ClientStub