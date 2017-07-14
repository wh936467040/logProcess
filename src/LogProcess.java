
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.kedong.parameter.DataParameter;
import com.kedong.regexPattern.regexComparatorChain;
import com.kedong.dmconnection.*;
import com.kedong.keyword.*;


public class LogProcess {
	public  static int insertData(String buffer,DataParameter para,DbConnection conn) throws IOException {
		
		para.refreshSql();
		regexComparatorChain regexChain = para.getRegexChain();
		wordComparatorChain keyWordChain = para.getKeyWordChain();
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buffer.getBytes())));
		try {
			PreparedInsert preInsert1 = new PreparedInsert(para.getSql1(),conn);
			PreparedInsert preInsert2 = new PreparedInsert(para.getSql2(),conn);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] words = regexChain.doCompare(line);
				String tag = null;
				if (null != words) {
					preInsert1.setString(words);
					tag = keyWordChain.doCompare(line);
					if (tag != null) {						
						preInsert2.setString(words);
						preInsert2.setString(words.length+1, tag);
					}
				}
			}
			preInsert1.execute();
			preInsert2.execute();
			System.out.println("Prase and Insert " + para.getTableName() +"Success!!!");
			preInsert1.close();
			preInsert2.close();
		}catch (Exception e) {
			System.out.println("error: failed to insert database ");
			e.printStackTrace();
			return 0;
		}
		br.close();
		return 1;
	}
}
