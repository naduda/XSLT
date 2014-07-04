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
	
	public ByteArrayOutputStream getData(String query, String pathTemplate, Hashtable<String, String> pars,
											String outputFileName) {
		
		baData = new ByteArrayOutputStream();
		
		if (query != null) {
			if (query.trim().length() == 0) {
				query = null;
			}
		}
		
		try {
			DataProcessor dp = new DataProcessor();
			dp.setConnection(con);
			if (query != null) {
				dp.setSql(query);
			} else {
				dp.setDataTemplate(pathTemplate);
				if (pars != null) {
					dp.setParameters(pars);
				}
			}
			
			if (outputFileName != null) {
				dp.setOutput(outputFileName);
			} else {
				dp.setOutput(baData);
			}
			
			dp.processData();
		} catch (Exception e) {
			System.err.println("Error in DataXSLT.getData(String query, "
					+ "String pathTemplate, Hashtable<String, String> pars)." + e);
		}
		return baData;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
