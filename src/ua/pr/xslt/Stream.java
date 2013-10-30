package ua.pr.xslt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

public class Stream {

	public static void ByteArrayOutputStreamToFile(ByteArrayOutputStream baos, String fileName) throws UnsupportedEncodingException 
	{
		FileOutputStream fop = null;
		File file;
		String content = new String(baos.toByteArray());
 
		try 
		{ 
			file = new File(fileName);
			fop = new FileOutputStream(file);
 
			// if file doesnt exists, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}
 
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} 
		catch (Exception e) 
		{
			System.err.println("ByteArrayOutputStreamToFile: " + e);
		} 
		finally 
		{
			try 
			{
				if (fop != null) 
				{
					fop.close();
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
