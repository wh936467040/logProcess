package com.kedong.readxml;
import java.util.LinkedList;
import java.util.List;
public class TableStruct {
	
	private int id;
	private String tableName;
	
	public List<String> keywords=new LinkedList<String>();
	public List<String>  names=new LinkedList<String>();
	public List<String>  rules=new LinkedList<String>();
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void print() {
		System.out.println("**************************");
		System.out.println("id: " + this.id);
		System.out.println("tableName: " + this.tableName);
		System.out.print("keywords: ");
		
		for(int i=0;i<keywords.size();i++) {
			System.out.print(keywords.get(i)+ " ");
		}
		System.out.print("\n");
		
		for(int i=0;i<rules.size();i++) {
			System.out.println(names.get(i) + "     " +rules.get(i));
		}
		System.out.println("**************************");
	}
	
	public List<String> parse(String str){
		List<String> keyword= new LinkedList<String>();
		while(true){
			int index = str.indexOf(";");
			if(index < str.length()-1) {
				String word = str.substring(0, index);
				str = str.substring(index+1,str.length());
				keyword.add(word);
			} else {
				break;
			}
		}
		return keyword;
	}
}
