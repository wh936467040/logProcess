package com.kedong.keyword;

import java.util.LinkedList;
import java.util.List;

public class KeywordParse {
	public static List<String> parse(String str){
		List<String> keyword= new LinkedList<String>();
		while(true){
			int index = str.indexOf(";");
			if(index <str.length()) {
				String word = str.substring(0, index);
				str = str.substring(index+1,str.length());
				keyword.add(word);
			} 
			if(str.length() <= 1){
				break;
			}
		}
		return keyword;
	}
}
