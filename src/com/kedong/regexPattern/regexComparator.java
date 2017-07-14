package com.kedong.regexPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class regexComparator implements regexComparable {
	public static int count=0;
	private String regex;
	
	public regexComparator() {
		super();
		count++;
	}

	public regexComparator(String regex) {
		super();
		count++;
		this.regex=new String(regex);
	}

	public	String[] doCompare(String str) {
		String c = null ;
		String[] result = {"",str};
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);

			if (m.find()) {
				try{
					c = m.group();

					str=str.substring(c.length(),str.length());
					str=str.replaceFirst("^ ","");
					str=str.trim();
					result[0]= c ;
					result[1]= str; 
					return result;
				}catch(PatternSyntaxException e){
					
					//System.out.println("PatternSyntaxException");
					//System.out.println(str);
					//System.out.println(c);
					result[0]= str;
					result[1]=null; 
					return result;
				}
			}else{	
				System.out.println(regex +" " +str);
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
