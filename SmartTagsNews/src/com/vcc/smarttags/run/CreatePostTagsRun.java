package com.vcc.smarttags.run;

import java.util.List;

import jvntagger.MaxentTagger;
import jvntagger.POSTagger;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import vn.hus.nlp.tagger.test.VietnameseTaggerTest;
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
		// TODO Auto-generated constructor stub
		smartTagsNewsDao=new SmartTagsNewsImpl();
		tokenizer=new VietTokenizer();
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
			Document doc=Jsoup.parse(news.getContent());
			System.out.println(doc.text());
			tokenizer.turnOnSentenceDetection();
			String postTags=vietTagger.tagging(tokenizer.tokenize(doc.text()).toString());
			smartTagsNewsDao.updatePostTags(news.getNewsID(), postTags);
			System.out.println(news.getNewsID());
			logger.info(news.getNewsID());
		}
	}
	
}
