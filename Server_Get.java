package com.xiaohong.server;

import java.io.*;
import java.net.*;


/**
 *创建服务器
 *HTML文件采用get方式获取，能完整的捕捉到我们所需要的数据
 * @author xiaohong
 *
 */
public class Server_Get {
/*
	//对各种功能都没有进行封装
	public static void main(String[] args) throws IOException {
		//创建服务器
		ServerSocket server = new ServerSocket(8888);
		//连接客户端
		Socket client = server.accept();
		StringBuilder sb = new StringBuilder();
		//接收客户端的消息
		String msg = null;
			
		//输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		while((msg = br.readLine()).length() > 0){
			sb.append(msg);
			sb.append("\r\n");//读入数据时，是一行一行的读取的
			if(msg == null) break;
		}
		
		String info = sb.toString().trim();
		System.out.println(info);
		br.close();
		//此方法实现的和上面的是一样的效果，此处我只是回想起另一种出入流的方式
		DataInputStream dis = new DataInputStream(client.getInputStream());
		while((msg = dis.readLine()).length() > 0){
			System.out.println(msg);
			if(msg == null) break;
		}
		dis.close();
		
	}
*/
	//对以上代码进行封装
	private ServerSocket server;
	public static void main(String[] args) throws IOException{
		Server_Get server = new Server_Get();
		server.start();
	}
	//启动方法
	public void start() throws IOException{
		server = new ServerSocket(8888);
		this.receive();
	}
	//接收客户端
	private void receive() throws IOException{
		Socket client = server.accept();
		StringBuilder sb = new StringBuilder();
		String msg = null;//接收客户端的消息
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		while((msg = br.readLine()).length() > 0){
			sb.append(msg);
			sb.append("\r\n");//读入数据时，是一行一行的读取的
			if(msg == null) break;
		}
		//接收客户端的请求信息
		String requestInfo = sb.toString().trim();//去除空格
		System.out.println(requestInfo);
		//System.out.println(sb.toString());//输出很多空格
		br.close();
	}
	//停止服务
	public void stop(){
		
	}

	
}
