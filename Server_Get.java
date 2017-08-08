package com.xiaohong.server;

import java.io.*;
import java.net.*;


/**
 *����������
 *HTML�ļ�����get��ʽ��ȡ���������Ĳ�׽����������Ҫ������
 * @author xiaohong
 *
 */
public class Server_Get {
/*
	//�Ը��ֹ��ܶ�û�н��з�װ
	public static void main(String[] args) throws IOException {
		//����������
		ServerSocket server = new ServerSocket(8888);
		//���ӿͻ���
		Socket client = server.accept();
		StringBuilder sb = new StringBuilder();
		//���տͻ��˵���Ϣ
		String msg = null;
			
		//������
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		while((msg = br.readLine()).length() > 0){
			sb.append(msg);
			sb.append("\r\n");//��������ʱ����һ��һ�еĶ�ȡ��
			if(msg == null) break;
		}
		
		String info = sb.toString().trim();
		System.out.println(info);
		br.close();
		//�˷���ʵ�ֵĺ��������һ����Ч�����˴���ֻ�ǻ�������һ�ֳ������ķ�ʽ
		DataInputStream dis = new DataInputStream(client.getInputStream());
		while((msg = dis.readLine()).length() > 0){
			System.out.println(msg);
			if(msg == null) break;
		}
		dis.close();
		
	}
*/
	//�����ϴ�����з�װ
	private ServerSocket server;
	public static void main(String[] args) throws IOException{
		Server_Get server = new Server_Get();
		server.start();
	}
	//��������
	public void start() throws IOException{
		server = new ServerSocket(8888);
		this.receive();
	}
	//���տͻ���
	private void receive() throws IOException{
		Socket client = server.accept();
		StringBuilder sb = new StringBuilder();
		String msg = null;//���տͻ��˵���Ϣ
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		while((msg = br.readLine()).length() > 0){
			sb.append(msg);
			sb.append("\r\n");//��������ʱ����һ��һ�еĶ�ȡ��
			if(msg == null) break;
		}
		//���տͻ��˵�������Ϣ
		String requestInfo = sb.toString().trim();//ȥ���ո�
		System.out.println(requestInfo);
		//System.out.println(sb.toString());//����ܶ�ո�
		br.close();
	}
	//ֹͣ����
	public void stop(){
		
	}

	
}
