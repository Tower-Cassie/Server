package com.xiaohong.server;

import java.io.*;
import java.net.*;

/**
 * ����Get��ʽ��ȡPOST,����Ϣ��ʾ�Ĳ���������Ϊpost��get��ʽ�洢��Դ�ķ�ʽ��ͬ
 * @author xiaohong
 *
 */
public class Server_Post {
	//δ��װģʽ
/*
 *
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(8888);
		Socket client = server.accept();
		//�������ݱȽ��٣�ԭ��Ӧ����һ���ֽ�һ���ֽڵĶ�ȡ���������ǽ���ȫ����ȡȻ���ٷ���
		byte[] data = new byte[248000];		
		int len = client.getInputStream().read(data);
		//���տͻ��˵�������Ϣ
		System.out.println(new String(data,0,len).trim());//ȥ���ո�
		
	}
*/
	private ServerSocket server;
	public static void main(String[] args) throws IOException{
		Server_Post server = new Server_Post();
		server.start();
	}
	//��������
	public void start() throws IOException{
		//����������
		server = new ServerSocket(8888);
		this.Receive();
	}
	//���տͻ���
	private void Receive() throws IOException{
		Socket client = server.accept();
		//�������ݱȽ��٣�ԭ��Ӧ����һ���ֽ�һ���ֽڵĶ�ȡ���������ǽ���ȫ����ȡȻ���ٷ���
		byte[] data = new byte[248000];		
		int len = client.getInputStream().read(data);
		//���տͻ��˵�������Ϣ
		System.out.println(new String(data,0,len).trim());//ȥ���ո�

	}
	//ֹͣ����
	public void Stop(){
		
	}
}
