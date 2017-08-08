package com.xiaohong.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server_Response {
	private ServerSocket server;
	private static final String BLACK = "";
	private static final String CRLF = "\r\n";
	public static void main(String[] args) throws IOException{
		Server_Response server = new Server_Response();
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

		
		//��Ӧ��Ϣ
		StringBuilder responsecontent = new StringBuilder();
		responsecontent.append("<html><head><title> HTTP��Ӧʾ��</title></head><body>Hello Tomcat!</body></html>");
		//responsecontent.append("ahffjpiofusfjk");//�ı�����Ķ�Ӧ�ǣ�plain;����ҳ��html
		StringBuilder response = new StringBuilder();
		//1,HTTPЭ��汾��״̬���룬����
				response.append("HTTP/1.1").append(BLACK).append("200").append(BLACK).append("OK").append(CRLF);
				//2,��Ӧͷ��Response Head��
				response.append("Server:bjsxt Server/0.0.1").append(CRLF);
				response.append("Date:").append(new Date()).append(CRLF);
				response.append("Content-type:text/html;charset = UTF-8").append(CRLF);
				//���ĳ���;�ֽڳ���(�ر�ע�������ĵ��ֽڳ���)
				response.append("Content-Length:").append(responsecontent.toString().getBytes().length).append(CRLF);
		
		//3,����֮ǰ
		response.append(CRLF);
		
		//4������
		response.append(responsecontent);
		
		
		
		//�����
		/*
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		bw.write(response.toString());
		bw.newLine();
		bw.flush();
		bw.close();*/
		//����������ķ�ʽ������
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(response.toString());
		dos.flush();
		dos.close();
	}
	//ֹͣ����
	public void Stop(){
		
	}
}
