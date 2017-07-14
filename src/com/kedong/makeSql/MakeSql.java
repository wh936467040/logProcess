package com.kedong.makeSql;

import java.util.List;

import com.kedong.Util.MyUtil;

public class MakeSql {
	public  static String makeSql1(String tableName,List<String> propertyNames) {
		String sql="insert into "+tableName.toUpperCase()+"_"+MyUtil.getToday() +"(";
		for (int i=0;i<propertyNames.size()-1;i++){
			sql=sql+propertyNames.get(i).toUpperCase()+",";
		}
		sql=sql+propertyNames.get(propertyNames.size()-1).toUpperCase();
		sql=sql+") "+"values(";
		for (int i=0;i<propertyNames.size()-1;i++){
			sql=sql+"?,";
		}
		sql=sql+"?";
		sql=sql+");";
		
		return sql;
	}
	
	public static String makeSql2(String tableName,List<String> propertyNames) {

		String sql="insert into "+tableName.toUpperCase()+"2_"+MyUtil.getToday()+"(";
		for (int i=0;i<propertyNames.size();i++){
			sql=sql+propertyNames.get(i).toUpperCase()+",";
		}
		sql=sql+"TAG)";
		sql=sql+"values(";
		for (int i=0;i<propertyNames.size();i++){
			sql=sql+"?,";
		}
		sql=sql+"?);";
		
		return sql;
	}
	
	
	
}
