package com.vcc.smarttags.run;

import java.util.List;

import jvntagger.MaxentTagger;
import jvntagger.POSTagger;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vn.hus.nlp.tokenizer.VietTokenizer;
import com.vcc.smarttags.bo.NewsInfo;
import com.vcc.smarttags.dao.SmartTagsNewsDao;
import com.vcc.smarttags.mysql.impl.SmartTagsNewsImpl;

public class CreatePostTagsRun {
	private SmartTagsNewsDao smartTagsNewsDao;
	static Logger logger=Logger.getLogger("Main");
	private VietTokenizer tokenizer;
    private POSTagger vietTagger=null;
	public CreatePostTagsRun() {
		tokenizer=new VietTokenizer();
		smartTagsNewsDao=new SmartTagsNewsImpl();
	    vietTagger=new MaxentTagger("models/maxent");
	}
	public SmartTagsNewsDao getSmartTagsNewsDao() {
		return smartTagsNewsDao;
	}
	public void setSmartTagsNewsDao(SmartTagsNewsDao smartTagsNewsDao) {
		this.smartTagsNewsDao = smartTagsNewsDao;
	}
	public void run()
	{
		List<NewsInfo> newsInfos=smartTagsNewsDao.getListAllNews();
		for(NewsInfo news:newsInfos)
		{
			try {
				Document doc=Jsoup.parse(news.getContent());
			    String postTags=vietTagger.tagging(tokenizer.segment(doc.text()));
			    smartTagsNewsDao.updatePostTags(news.getNewsID(), postTags);
				logger.info(news.getNewsID());
			} catch (Exception e) {
				logger.info("Update Error:"+news.getNewsID());
				smartTagsNewsDao.updateError(news.getNewsID());
				logger.info("Updated Error");
				logger.info("Error",e);
			}
			
		}
	}
	
}
