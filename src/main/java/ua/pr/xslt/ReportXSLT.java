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

	/**
	 * @author pr
	 * Code:
	 *  if (outputFileName != null) {
	 *		...
	 *	}
	 */
	public ByteArrayOutputStream RTF2XML(String fileName, String outputFileName) {
		ByteArrayOutputStream baData = new ByteArrayOutputStream();
		RTFProcessor rtf;
		try
		{
			rtf = new RTFProcessor(fileName);
			
			if (outputFileName != null) {
				rtf.setOutput(outputFileName);
			} else {
				rtf.setOutput(baData);
			}
			
	    	try
			{
				rtf.process();
			} 
	    	catch (XDOException e)
			{
				System.err.println("Error in RTF2XML.getXML(String fileName). XDOException! " + e);
			}
		} 
		catch (Exception e)
		{
			System.err.println("Error in RTF2XML.getXML(String fileName). FileNotFoundException! " + e);
		}
		return baData;
	}
	
	/**
	 * 
	 * if (outputFileName != null) {
	 *		fo.setOutput(outputFileName);
	 * } else {
	 *		fo.setOutput(baos);
	 * }
	 * 
	 */
	public ByteArrayOutputStream getReport(ByteArrayOutputStream data, String nameTemplate,	
											byte fopFormat, String outputFileName) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		FOProcessor fo = new FOProcessor();
    	
		try {
			fo.setData(new ByteArrayInputStream(data.toByteArray()));
			fo.setTemplate(new FileInputStream(new File(nameTemplate)));
			fo.setOutputFormat(fopFormat);
			fo.setConfig(xdoConfPath);
			
			if (outputFileName != null) {
				fo.setOutput(outputFileName);
			} else {
				fo.setOutput(baos);
			}

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
