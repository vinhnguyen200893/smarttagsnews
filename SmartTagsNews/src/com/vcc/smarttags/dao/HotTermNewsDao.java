package com.vcc.smarttags.dao;

import java.util.List;
import java.util.Map;

import com.vcc.smarttags.bo.NewsInfo;

public interface HotTermNewsDao {
	Map<Long,String> getListDocs();
	void updateHotTerm();
	List<Long> listNewsIdUpdated();
}
