package com.kedong.regexPattern;


import java.util.LinkedList;
import java.util.List;

public class regexComparatorChain implements regexComparable {
	private LinkedList<regexComparator> chain = new LinkedList<regexComparator>();
	private List<String> rule;

	public regexComparatorChain(List<String> regexRule) {
		this.rule=regexRule;
		setChain();
	}
	
	public String[] doCompare(String str) {
		String result[] = { "", str };
		String [] returnValue = new String[rule.size()];
		int i=0;
		for (regexComparator m : chain) {
			str = result[1];
			//System.out.println(str);
			result = m.doCompare(str);
			if (result==null){
				return null;
			}else{
				returnValue[i++]=result[0];
			}
		}
		return returnValue;
	}
	
	public void print() {
		System.out.println("******************************");
		for(int i=0;i<rule.size();i++) {
			System.out.println(rule.get(i));
		}
		System.out.println("******************************");
	}
	
	protected void setChain() {
		for (int i = 0; i < rule.size(); i++) {
			String reg = rule.get(i);
			regexComparator comparator = new regexComparator(reg);
			chain.add(comparator);
		}

	}
}
