package ua.pr.xslt;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Hashtable;

import oracle.xdo.dataengine.v1.DataProcessor;

public class DataXSLT {
	
	private Connection con;
	private ByteArrayOutputStream baData;
	
	public DataXSLT(Connection con) {
		setCon(con);
	}

	public ByteArrayOutputStream getData(String pathTemplate, Hashtable<String, String> pars) {		
		getData(null, pathTemplate, pars);
		return baData;
	}
	
	public ByteArrayOutputStream getData(String query) {		
		getData(query, null, null);
		return baData;
	}
	
	private void getData(String query, String pathTemplate, Hashtable<String, String> pars) {
		baData = new ByteArrayOutputStream();
		try {
			DataProcessor dp = new DataProcessor();
			dp.setConnection(con);
			if (query != null) {
				dp.setSql(query);
			} else {
				dp.setDataTemplate(pathTemplate);
				dp.setParameters(pars);
			}
			
			dp.setOutput(baData);
			
			dp.processData();
		} catch (Exception e) {
			System.err.println("Error in DataXSLT.getData(String query, "
					+ "String pathTemplate, Hashtable<String, String> pars).");
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
