package com.xiaohong.server;

import java.io.*;
import java.net.*;

/**
 * 利用Get方式获取POST,则信息显示的不完整，因为post与get方式存储资源的方式不同
 * @author xiaohong
 *
 */
public class Server_Post {
	//未封装模式
/*
 *
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(8888);
		Socket client = server.accept();
		//假设数据比较少，原本应该是一个字节一个字节的读取，现在我是将其全部读取然后再分析
		byte[] data = new byte[248000];		
		int len = client.getInputStream().read(data);
		//接收客户端的请求信息
		System.out.println(new String(data,0,len).trim());//去除空格
		
	}
*/
	private ServerSocket server;
	public static void main(String[] args) throws IOException{
		Server_Post server = new Server_Post();
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

	}
	//停止服务
	public void Stop(){
		
	}
}
