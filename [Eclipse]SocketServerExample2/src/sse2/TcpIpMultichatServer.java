package sse2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultichatServer {

	HashMap<String, DataOutputStream> clients;
	
	TcpIpMultichatServer()
	{
		clients = new HashMap<>();
		Collections.synchronizedMap(clients);
	}
	
	public void start()
	{
		ServerSocket ss = null;
		Socket s = null;
		
		try {
			ss = new ServerSocket(7777);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while(true)
			{
				s = ss.accept();
				System.out.println("["+s.getInetAddress()+":"+s.getPort()+"]"+"���� �����Ͽ����ϴ�.");
				ServerReceiver thread = new ServerReceiver(s);
				thread.start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void sendToAll(String msg)
	{
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext())
		{
			try {
				DataOutputStream out = (DataOutputStream)clients.get((it.next()));
				out.writeUTF(msg);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new TcpIpMultichatServer().start();
	}
	
	class ServerReceiver extends Thread {
		Socket s;
		DataInputStream in;
		DataOutputStream out;
		
		ServerReceiver(Socket s)
		{
			this.s=s;
			try {
				in = new DataInputStream(s.getInputStream());
				out = new DataOutputStream(s.getOutputStream());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run()
		{
			String name = "";
			try {
				name = in.readUTF();
				sendToAll("#"+name+"���� �����̽��ϴ�.");
				sendToAll("CMD_JOIN");
				
				clients.put(name, out);
				System.out.println("���� ���� ������ ���� "+clients.size()+"�Դϴ�.");
				while(in!=null)
				{
					sendToAll(in.readUTF());
				}
			}catch(IOException e) {
				
			}finally {
				clients.remove(name);
				sendToAll("#"+name+"���� �����̽��ϴ�.");
				System.out.println("["+s.getInetAddress()+":"+s.getPort()+"]"+"���� ������ �����Ͽ����ϴ�.");
				System.out.println("���� ���� ������ ���� "+clients.size()+"�Դϴ�.");
			}
		}
	}
}
