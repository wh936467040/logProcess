package com.kedong.keyword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class wordComparator implements wordComparable {
	private String regex;
	
	public wordComparator() {
		super();
	}

	public wordComparator(String regex) {
		super();
		this.regex=new String(regex);
	}

	public	String doCompare(String str) {
		String c = "" ;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);

			if (m.find()) {
				try{
					c = m.group();
					return c;
				}catch(PatternSyntaxException e){
					System.out.println("关键字含有正则表达式特殊字符,无法匹配");
					//System.out.println("PatternSyntaxException");
					//System.out.println(str);
					//System.out.println(c);
					return null;
				}
			}else{	
				//System.out.println(regex);
				//System.out.println(str);
				return null;
			}
	}


	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String  getRegex() {
		return this.regex;
	}
	
}
