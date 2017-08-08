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
	//启动方法
	public void start() throws IOException{
		//创建服务器
		server = new ServerSocket(8888);
		this.Receive();
	}
	//接收客户端
	private void Receive() throws IOException{
		Socket client = server.accept();
		//假设数据比较少，原本应该是一个字节一个字节的读取，现在我是将其全部读取然后再分析
		byte[] data = new byte[248000];		
		int len = client.getInputStream().read(data);
		//接收客户端的请求信息
		System.out.println(new String(data,0,len).trim());//去除空格

		
		//响应消息
		StringBuilder responsecontent = new StringBuilder();
		responsecontent.append("<html><head><title> HTTP响应示例</title></head><body>Hello Tomcat!</body></html>");
		//responsecontent.append("ahffjpiofusfjk");//文本下面的对应是：plain;而网页是html
		StringBuilder response = new StringBuilder();
		//1,HTTP协议版本，状态代码，描述
				response.append("HTTP/1.1").append(BLACK).append("200").append(BLACK).append("OK").append(CRLF);
				//2,响应头（Response Head）
				response.append("Server:bjsxt Server/0.0.1").append(CRLF);
				response.append("Date:").append(new Date()).append(CRLF);
				response.append("Content-type:text/html;charset = UTF-8").append(CRLF);
				//正文长度;字节长度(特别注意是正文的字节长度)
				response.append("Content-Length:").append(responsecontent.toString().getBytes().length).append(CRLF);
		
		//3,正文之前
		response.append(CRLF);
		
		//4，正文
		response.append(responsecontent);
		
		
		
		//输出流
		/*
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		bw.write(response.toString());
		bw.newLine();
		bw.flush();
		bw.close();*/
		//两种输出流的方式都可以
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(response.toString());
		dos.flush();
		dos.close();
	}
	//停止服务
	public void Stop(){
		
	}
}
