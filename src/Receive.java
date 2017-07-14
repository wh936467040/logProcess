import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

import com.kedong.dmconnection.DbConnection;
import com.kedong.parameter.DataParameter;
import com.kedong.parameter.Parameter;

public class Receive {
	private Socket socket;
	private BufferedReader in;
	private List<DataParameter> paraList;
	private DbConnection conn;

	public Receive(Socket s,List<DataParameter> list) throws IOException {
		paraList = list;
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		conn = new DbConnection(Parameter.dbUrl,Parameter.dbUser,Parameter.dbPwd);
		conn.initJdbcConnection();
		run();
		in.close();
		conn.destoryConnection();
	}

	public void run() {
		String buffer = "";
		int type  = 0;
		try {
			while (true) {
				String str = in.readLine();
				if(str.equals("END"))break;
				if(str.length() < 5) {
					type = Integer.parseInt(str);
				}else {
					buffer = buffer+str+"\n";
				}
			}
			System.out.println(buffer);
		} catch (IOException e) {
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		if(type == 0 ) return;
		DataParameter para = DataParameter.findById(type, paraList);
		try {
			LogProcess.insertData(buffer,para,conn);
		} catch (IOException e) {
			System.out.println("process log error: " + e.toString());
			e.printStackTrace();
		}
	}
}
