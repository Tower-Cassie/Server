package com.xiaohong.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 面对客户端传来的请求信息，服务器端的回应不可能每次都手写一次，所以在此方法中将有关信息进行封装
 * @author xiaohong
 *
 */
public class Server_Packet {
	//两个常量
		private static final String BLACK = " ";
		private static final String CRLF = "\r\n";
		//响应头
		private StringBuilder headInfo;
		//正文
		private StringBuilder content;
		//输出流
		private BufferedWriter bw;
		private int len = 0;
		//main函数
		public static void main(String[] args) throws IOException{
			//创建服务器
					ServerSocket server = new ServerSocket(8888);
					//接收客户端的请求
					Socket client = server.accept();
					Server_Packet response = new Server_Packet(client);
					//添加正文
					response.createContentln("<html><head><title> HTTP响应示例</title>");
					response.createContentln("</head><body>");
					response.createContentln("Hello Tomcat!");
					response.createContentln("</body></html>");
					response.pushToClient(200);
		}
		/**
		 * 构造器
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
		 * 构建协议头
		 * @param code
		 */
		private void createHead(int code){
			//1,HTTP协议版本，状态代码，描述
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
			//2,响应头（Response Head）
			headInfo.append("Server:bjsxt Server/0.0.1").append(CRLF);
			headInfo.append("Date:").append(new Date()).append(CRLF);
			headInfo.append("Content-type:text/html;charset = GBK").append(CRLF);
			//正文长度;字节长度
			headInfo.append("Content-Length:").append(len).append(CRLF);
			headInfo.append(CRLF);//此句代码一定不能省，如果省了则无法输出
		}
		/**
		 * 构建正文
		 * @param msg
		 */
		public Server_Packet createContent(String msg){
			content.append(msg);
			len += msg.getBytes().length;//正文的长度应该在原来的基础之上不断增加，而不是只记录每次正文的长度
			return this;
		}
		/**
		 * 构建正文 + 回车
		 */
		public Server_Packet createContentln(String msg){
			content.append(msg).append(CRLF);
			len += (msg + CRLF).getBytes().length;
			return this;
		}
		/**
		 * 推送到客户端
		 * @throws IOException 
		 */
		public void pushToClient(int code) throws IOException{
			if(headInfo == null) code = 500;
			createHead(code);
			//头信息 + 分割符
			//bw.append(headInfo.toString());
			bw.write(headInfo.toString());
			//正文
			//bw.append(content.toString());
			bw.write(content.toString());
			bw.newLine();
			bw.flush();
		}
		public void close() throws IOException{
			CloseUtil.CloseAll(bw);
		}
}
