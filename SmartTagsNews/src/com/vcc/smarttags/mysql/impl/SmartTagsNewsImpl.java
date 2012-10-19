package com.vcc.smarttags.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vcc.smarttags.bo.NewsInfo;
import com.vcc.smarttags.config.SystemInfo;
import com.vcc.smarttags.dao.SmartTagsNewsDao;
import com.vcc.smarttags.mysql.pool.ConnectionPool;

public class SmartTagsNewsImpl implements SmartTagsNewsDao {
	private static Logger logger=Logger.getLogger("Main");
	@Override
	public List<NewsInfo> getListAllNews() {
		// TODO Auto-generated method stub
		List<NewsInfo> newsInfos=new ArrayList<NewsInfo>();
		String query = "select min(newsid),min(publishdate),newsurl,min(cat_id),id,Content,GeneralCatID from ViewForAdmicro a where createDate >=DATE_ADD(CURDATE(),INTERVAL " + (-1) * SystemInfo.NO_OF_DAY_TO_GET_CONTENT + " DAY) group by newsurl";
		try {
			Connection connection=ConnectionPool.getConnection();
			PreparedStatement statement=connection.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				NewsInfo item=new NewsInfo();
				item.setNewsID(rs.getLong("newsid"));
				item.setId(rs.getInt("id"));
				item.setContent(rs.getString("Content"));
				newsInfos.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("Error",e);
		}
		return newsInfos;
	}

	@Override
	public void updatePostTags(Long newsId) {
		// TODO Auto-generated method stub
		
	}

}
