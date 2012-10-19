/*
    Copyright (C) 2006, Xuan-Hieu Phan
    
    Email:	hieuxuan@ecei.tohoku.ac.jp
		pxhieu@gmail.com
    URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
    
    Graduate School of Information Sciences,
    Tohoku University
*/

package jflexcrf;

import java.io.*;
import java.util.*;

public class Observation {
    public String originalData = "";	// original data (before generating context predicates)
    public int[] cps = null;		// array of context predicates
    public int modelLabel = -1;		// label predicted by model

    public Observation() {
	// do nothing currently
    }
    
    public String toString(Map lbInt2Str) {
	String res = originalData;
	
	String labelStr = (String)lbInt2Str.get(new Integer(modelLabel));
	if (labelStr != null) {
	    res += Option.outputSeparator + labelStr.toUpperCase();
	}
	
	return res;
    }
    
} // end of class Observation

