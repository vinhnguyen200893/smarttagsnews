package com.vcc.smarttags.hotterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.vcc.smarttags.dao.HotTermNewsDao;
import com.vcc.smarttags.mysql.pool.ConnectionPool;

public class FrequencyTermImpl implements HotTermNewsDao {

	private static Logger logger=Logger.getLogger("Main");
	private Map<String,Integer> listWord;
	private Map<Long,String> listDocs;
	private List<Long> listKeyUpdated;
	public String getLastDate(){
		long currenttime = new java.util.Date().getTime() - 24 * 60 * 60 * 1000;
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(new Date(currenttime));
	}
	public String getCurrentDate()
	{
		long currenttime = new java.util.Date().getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(new Date(currenttime));
	}
	public Map<String, Integer> GetWordFrequency(String docs) {

		Map<String, Integer> termFrequencyMap = new HashMap<String, Integer>();
        	StringTokenizer tknr = new StringTokenizer(docs, " ");
    		String term = "";
    		while (tknr.hasMoreTokens()) {
    			term = tknr.nextToken();
    			if(term.contains("N")||term.contains("Np")||term.contains("Nc")||term.contains("V"))
    			{
    			if(term.contains("_"))
    			{
    			Integer frequency = termFrequencyMap.get(term);
    			if (frequency == null) {
    				termFrequencyMap.put(term, 1);
    			} else {
    				termFrequencyMap.put(term, frequency + 1);
    		    }
    			}
    			}
    			else
    			{
    				if(term.contains("Ny"))
    				{
    					Integer frequency = termFrequencyMap.get(term);
    	    			if (frequency == null) {
    	    				termFrequencyMap.put(term, 1);
    	    			} else {
    	    				termFrequencyMap.put(term, frequency + 1);
    	    		    }
    				}
    			}
    		}
		return termFrequencyMap;
	}
	@Override
	public Map<Long,String> getListDocs() {
		// TODO Auto-generated method stub
		 Map<Long,String> listDoc=new HashMap<Long, String>();
		 String querySelect="select NewsId,postTags from ViewForAdmicro where CreateDate >='"+getCurrentDate()+"' and postTags is not NULL" ;
		 try {
			Connection connection=ConnectionPool.getConnection();
			PreparedStatement statement=connection.prepareStatement(querySelect);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				listDoc.put(rs.getLong("NewsId"),rs.getString("postTags"));
			}
			connection.close();
		} catch (SQLException e) {
			logger.info("Error",e);
		}
		 return listDoc;
	}
	@Override
	public void updateHotTerm() {
		// TODO Auto-generated method stub
	    listWord=new HashMap<String, Integer>();
		listDocs=new HashMap<Long,String>();
		listKeyUpdated=new ArrayList<Long>();
		listKeyUpdated=listNewsIdUpdated();
		listDocs=getListDocs();
		try {
			Connection connection=ConnectionPool.getConnection();
			for(Long newsId:listDocs.keySet())
			{
				if(listKeyUpdated.contains(newsId)==false)
				{
				listWord=GetWordFrequency(listDocs.get(newsId));
				for(String term:listWord.keySet())
				{
					String[] split=term.split("/");
					String queryInsert="Insert ignore into TermFrequency(NewsID,Term,Frequency,Tags) values(?,?,?,?)";
				    PreparedStatement statement=connection.prepareStatement(queryInsert);
				    statement.setLong(1,newsId);
				    statement.setString(2,split[0]);
				    statement.setInt(3,listWord.get(term));
				    statement.setString(4,split[1]);
				    statement.executeUpdate();
				    statement.close();
				}
				}
			}
			connection.close();
		} catch (SQLException e) {
			logger.info("Error"+e);
		}
		
	}
	@Override
	public List<Long> listNewsIdUpdated() {
		// TODO Auto-generated method stub
		List<Long> listNewsId=new ArrayList<Long>();
		String querySelectDistinct="SELECT DISTINCT NewsID FROM TermFrequency";
		try {
			Connection connection=ConnectionPool.getConnection();
			PreparedStatement statement=connection.prepareStatement(querySelectDistinct);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				listNewsId.add(rs.getLong("NewsID"));
			}
			connection.close();
		} catch (SQLException e) {
			logger.info("Error",e);
		}
		return listNewsId;
	}
	@Override
	public List<String> getListNounPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

}
