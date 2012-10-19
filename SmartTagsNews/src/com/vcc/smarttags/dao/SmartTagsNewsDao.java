package com.vcc.smarttags.dao;

import java.util.List;

import com.vcc.smarttags.bo.NewsInfo;

public interface SmartTagsNewsDao {
	List<NewsInfo> getListAllNews();
	void updatePostTags(Long newsId,String postTags);
}
