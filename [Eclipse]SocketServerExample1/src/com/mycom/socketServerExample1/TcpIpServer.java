package com.mycom.socketServerExample1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(7777);
			System.out.println(getTime()+"������ �غ�Ǿ����ϴ�.");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		while(true)
		{
			try {
				System.out.println(getTime()+"�����û�� ��ٸ��ϴ�.");
				Socket s = ss.accept();
				System.out.println(getTime()+s.getInetAddress()+"�κ��� �����û�� ���Խ��ϴ�.");
				
				OutputStream out = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message1 from Server.");
				System.out.println(getTime()+"�����͸� �����߽��ϴ�.");
				
				dos.close();
				s.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static String getTime()
	{
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}

}
