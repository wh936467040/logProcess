package com.kedong.dmconnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedInsert {
	PreparedStatement pstmt = null;
	String sql;

	DbConnection conn;
	
	public PreparedInsert(String sql,DbConnection conn) {
		this.sql = sql;
		this.conn = conn;
		try {
			pstmt = (PreparedStatement) conn.connection.prepareStatement(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void setString(String[] words) throws SQLException {
		if (null != words) {
			int i = 0;
			for (i = 1; i <= words.length; i++) {
				// System.out.println(words[i]);
				pstmt.setString(i, words[i - 1]);
			}
			pstmt.addBatch();
		}
	}
	
	public void setString(int i ,String word) throws SQLException {
		if (null != word) {
			pstmt.setString(i, word);
			pstmt.addBatch();
		}
	}
	
	public int execute() {
		try {
			pstmt.executeBatch();
			conn.connection.commit();
			pstmt.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
		
	}
	
	public int close(){
		try {
			pstmt.close();
			conn.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
