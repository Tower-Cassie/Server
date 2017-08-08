package com.xiaohong.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * ��Կͻ��˴�����������Ϣ���������˵Ļ�Ӧ������ÿ�ζ���дһ�Σ������ڴ˷����н��й���Ϣ���з�װ
 * @author xiaohong
 *
 */
public class Server_Packet {
	//��������
		private static final String BLACK = " ";
		private static final String CRLF = "\r\n";
		//��Ӧͷ
		private StringBuilder headInfo;
		//����
		private StringBuilder content;
		//�����
		private BufferedWriter bw;
		private int len = 0;
		//main����
		public static void main(String[] args) throws IOException{
			//����������
					ServerSocket server = new ServerSocket(8888);
					//���տͻ��˵�����
					Socket client = server.accept();
					Server_Packet response = new Server_Packet(client);
					//�������
					response.createContentln("<html><head><title> HTTP��Ӧʾ��</title>");
					response.createContentln("</head><body>");
					response.createContentln("Hello Tomcat!");
					response.createContentln("</body></html>");
					response.pushToClient(200);
		}
		/**
		 * ������
		 */
		public Server_Packet(){
			headInfo = new StringBuilder();
			content = new StringBuilder();
			len = 0;
		}
		public Server_Packet(Socket client){
			this();
			try {
				bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			} catch (IOException e) {
				headInfo = null;
			}
		}
		/**
		 * ����Э��ͷ
		 * @param code
		 */
		private void createHead(int code){
			//1,HTTPЭ��汾��״̬���룬����
			headInfo.append("HTTP/1.1").append(BLACK).append(code).append(BLACK);
			switch(code){
				case 200:
					headInfo.append("OK").append(CRLF);
					break;
				case 404:
					headInfo.append("Not Found").append(CRLF);
					break;
				case 505:
					headInfo.append("Server Error").append(CRLF);
					break;
			}
			//2,��Ӧͷ��Response Head��
			headInfo.append("Server:bjsxt Server/0.0.1").append(CRLF);
			headInfo.append("Date:").append(new Date()).append(CRLF);
			headInfo.append("Content-type:text/html;charset = GBK").append(CRLF);
			//���ĳ���;�ֽڳ���
			headInfo.append("Content-Length:").append(len).append(CRLF);
			headInfo.append(CRLF);//�˾����һ������ʡ�����ʡ�����޷����
		}
		/**
		 * ��������
		 * @param msg
		 */
		public Server_Packet createContent(String msg){
			content.append(msg);
			len += msg.getBytes().length;//���ĵĳ���Ӧ����ԭ���Ļ���֮�ϲ������ӣ�������ֻ��¼ÿ�����ĵĳ���
			return this;
		}
		/**
		 * �������� + �س�
		 */
		public Server_Packet createContentln(String msg){
			content.append(msg).append(CRLF);
			len += (msg + CRLF).getBytes().length;
			return this;
		}
		/**
		 * ���͵��ͻ���
		 * @throws IOException 
		 */
		public void pushToClient(int code) throws IOException{
			if(headInfo == null) code = 500;
			createHead(code);
			//ͷ��Ϣ + �ָ��
			//bw.append(headInfo.toString());
			bw.write(headInfo.toString());
			//����
			//bw.append(content.toString());
			bw.write(content.toString());
			bw.newLine();
			bw.flush();
		}
		public void close() throws IOException{
			CloseUtil.CloseAll(bw);
		}
}
