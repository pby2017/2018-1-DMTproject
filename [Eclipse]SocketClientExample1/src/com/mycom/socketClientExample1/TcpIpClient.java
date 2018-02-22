package com.mycom.socketClientExample1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TcpIpClient {

	static Scanner sc;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		sb.delete(0, sb.length());
		
		while(true)
		{
			try {
				sb.append(sc.next());
				break;
			} catch(InputMismatchException ime) {
				sc = new Scanner(System.in);
			}
		}
		
		try {
			System.out.println("������ �������Դϴ�. ����IP :"+sb.toString());
			Socket s = new Socket(sb.toString(),7777);
			
			InputStream in = s.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			System.out.println("�����κ��� ���� �޽��� :"+dis.readUTF());
			System.out.println("������ �����մϴ�.");
		}catch(ConnectException ce) {
			ce.printStackTrace();
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("good");
	}

}

