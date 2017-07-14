
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;









import com.kedong.parameter.DataParameter;
import com.kedong.parameter.Parameter;
import com.kedong.readxml.TableStruct;
import com.kedong.readxml.XmlRead;

public class MainClass {
	static int PORT = 39999;
	
	public static void main(String[] args){
		
		//String xmlFile = System.getenv("D5000_HOME") + "/conf/monitor_log.conf";
		String xmlFile = "C:\\Users\\WH\\Desktop\\table.xml";
		Parameter.dbUrl = "jdbc:dm://11.11.11.250:12345/FUJIAN_MONITOR";
		Parameter.dbUser = "SYSDBA";
		Parameter.dbPwd = "SYSDBA";
		
		XmlRead xmlReader = new XmlRead(xmlFile);
		List<TableStruct> tableList= xmlReader.read();
		List<DataParameter> list = DataParameter.dataParameterMaker(tableList);
		ServerSocket s = null;
		try {
			s = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("init tcp failed!!"+ e.toString());
		}
		System.out.println("log server start");
		try {
			while (true) {
				try {
					Socket socket = s.accept();
					System.out.println("--------");
					new Receive(socket,list);
				}catch(Exception e) {
					System.out.println("receive error: " + e.toString());
					e.printStackTrace();
				}
			} 
		}finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}		
}
