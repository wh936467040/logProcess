package com.kedong.parameter;
import java.util.LinkedList;
import java.util.List;

import com.kedong.keyword.wordComparatorChain;
import com.kedong.makeSql.MakeSql;
import com.kedong.readxml.TableStruct;
import com.kedong.regexPattern.regexComparatorChain;


public class DataParameter {
	private int id;
	private String tableName;
	private String sql1;
	private String sql2;
	private List<String> colName;
	private List<String> regexRule;
	private List<String> keywords;
	private regexComparatorChain regexChain;
	private wordComparatorChain keyWordChain;
		
	public DataParameter(TableStruct table) {
		this.id = table.getId();
		this.tableName = table.getTableName();
		this.sql1 = MakeSql.makeSql1(table.getTableName(), table.names);
		this.sql2 = MakeSql.makeSql2(table.getTableName(), table.names);
		this.colName = table.names;
		this.keywords = table.keywords;
		this.keyWordChain = new wordComparatorChain(table.keywords);
		this.regexRule = table.rules;
		this.regexChain = new regexComparatorChain(table.rules);
	}
	
	public int refreshSql(){
		this.sql1 = MakeSql.makeSql1(this.tableName, this.colName);
		this.sql2 = MakeSql.makeSql2(this.tableName, this.colName);
		return 1;
	}
	
	public static List<DataParameter>  dataParameterMaker(List<TableStruct> tableList) {
		List<DataParameter> list = new LinkedList<DataParameter>();
		for(int i=0;i<tableList.size();i++) {
			DataParameter para = new DataParameter(tableList.get(i));
			list.add(para);
		}
		return list;
	}
	
	public static DataParameter findById(int id,List<DataParameter> list) {
		for(int i=0;i<list.size();i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	
	public void print() {
		System.out.println("**************************");
		System.out.println("id: " + this.id);
		System.out.println("tableName: " + this.tableName);
		System.out.println("sql1: " + this.sql1);
		System.out.println("sql2: " + this.sql2);
		
		System.out.print("keywords: ");
		for(int i=0;i<keywords.size();i++) {
			System.out.print(keywords.get(i)+ " ");
		}
		System.out.print("\n");
		
		for(int i=0;i<regexRule.size();i++) {
			System.out.println(colName.get(i) + "     " +regexRule.get(i));
		}
		System.out.println("**************************");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSql1() {
		return sql1;
	}
	public void setSql1(String sql1) {
		this.sql1 = sql1;
	}
	public String getSql2() {
		return sql2;
	}
	public void setSql2(String sql2) {
		this.sql2 = sql2;
	}
	public List<String> getRegexRule() {
		return regexRule;
	}
	public void setRegexRule(List<String> regexRule) {
		this.regexRule = regexRule;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keyWords) {
		this.keywords = keyWords;
	}
	public regexComparatorChain getRegexChain() {
		return regexChain;
	}
	public void setRegexChain(regexComparatorChain regexChain) {
		this.regexChain = regexChain;
	}
	public wordComparatorChain getKeyWordChain() {
		return keyWordChain;
	}
	public void setKeyWordChain(wordComparatorChain keyWordChain) {
		this.keyWordChain = keyWordChain;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
