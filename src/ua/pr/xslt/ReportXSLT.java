package ua.pr.xslt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import oracle.xdo.XDOException;
import oracle.xdo.template.FOProcessor;
import oracle.xdo.template.RTFProcessor;

public class ReportXSLT {
	private String xdoConfPath;
	
	public ByteArrayOutputStream RTF2XML(String fileName) {
		ByteArrayOutputStream baData = new ByteArrayOutputStream();
		RTFProcessor rtf;
		try
		{
			rtf = new RTFProcessor(fileName);
			rtf.setOutput(baData);
	    	try
			{
				rtf.process();
			} 
	    	catch (XDOException e)
			{
				System.err.println("Error in RTF2XML.getXML(String fileName). XDOException!");
			}
		} 
		catch (Exception e)
		{
			System.err.println("Error in RTF2XML.getXML(String fileName). FileNotFoundException!");
		}
		return baData;
	}
	
	/**
	 * Returns a Report. 
	 * Example: rXSLT.getReport(baData, nameShablon, FOProcessor.FORMAT_HTML);
	 * <p>
	 * @param  data is Data from DataXSLT.getData
	 * @param  nameTemplate is fullPath to the file 
	 * @param  fopFormat  =  FOProcessor.FORMAT_...
	 */
	public ByteArrayOutputStream getReport(ByteArrayOutputStream data, String nameTemplate,	byte fopFormat)	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		FOProcessor fo = new FOProcessor();
    	
		try {
			fo.setData(new ByteArrayInputStream(data.toByteArray()));
			fo.setTemplate(new FileInputStream(new File(nameTemplate)));
			fo.setOutputFormat(fopFormat);
			fo.setConfig(xdoConfPath);
			fo.setOutput(baos);
			fo.generate();
		} catch (FileNotFoundException e) {
			System.err.println("Error in RTF2XML.getReport(ByteArrayOutputStream data, "
					+ "String nameTemplate, byte fopFormat, String repPath). FileNotFoundException!");
		} catch (XDOException e) {
			System.err.println("Error in RTF2XML.getReport(ByteArrayOutputStream data, "
					+ "String nameTemplate, byte fopFormat, String repPath). XDOException!");
		}
		return baos;
	}

	public String getXdoConfPath() {
		return xdoConfPath;
	}

	public void setXdoConfPath(String xdoConfPath) {
		this.xdoConfPath = xdoConfPath;
	}
}
