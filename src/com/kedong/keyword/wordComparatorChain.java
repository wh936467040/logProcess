package com.kedong.keyword;

import java.util.LinkedList;
import java.util.List;



public class wordComparatorChain implements wordComparable {
	private static LinkedList<wordComparator> chain = new LinkedList<wordComparator>();
	private List<String> words;

	public wordComparatorChain(List<String> words) {
		this.words=words;
		setChain();
	}
	
	public String doCompare(String str) {
		String result= "";
		for (wordComparator m : chain) {
			result = m.doCompare(str);
			if (result == null){
				continue;
			}else{
				break;
			}		
		}
		return result;
	}
	
	void setChain() {

		for (int i = 0; i < words.size(); i++) {
			String reg = words.get(i);
			wordComparator comparator = new wordComparator(reg);
			chain.add(comparator);
		}
	}
}
