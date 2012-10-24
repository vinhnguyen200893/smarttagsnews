package com.vcc.smarttags.dao;

import java.util.List;

public interface HotTermExtractionDao {
	List<String> getTraceableList();
	Double getEnergy(String term);
	Double energyFunction(String term);
	Double energyDecay(String term);
	Double getVariation(String term);
}
