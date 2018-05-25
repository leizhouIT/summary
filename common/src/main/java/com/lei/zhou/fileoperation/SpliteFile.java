package com.lei.zhou.fileoperation;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class SpliteFile {
	private static final long BLOCK_G=1024*1024*1024;//1G

	private static final long BLOCK_M=1024*1024;//1M
	public static void main(String[] args) throws Exception {


		spliteFile("/Users/zhoulei8/remote1609.xml");
		/*File file = new File("D:\\gaoqing\\bb.txt");
		long partLength = file.length() / 5 + 1;
		File dir = new File(file.getParent(), ".temp");		
		dir.mkdir();
		
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		for (int i = 0; i < 5; i++) {	
			OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(dir, i + "")));
			for (int j = 0, b = 0; j < partLength && (b = in.read()) != -1; j++) {
				out.write(b);
			}
			out.close();
		}
		in.close();
		
		file.delete();
		dir.renameTo(file);	
		System.out.println("分割完成");*/
	}

	public static void spliteFile(String filePathName) throws Exception {
		RandomAccessFile file = new RandomAccessFile(filePathName,"rw");
		long length = file.length();
		//按照每个文件1m进行切分，算出切分的个数
		long countFile=0;
		if (length%BLOCK_M==0){
			countFile=length / BLOCK_M;
		}else {
			countFile = length / BLOCK_M + 1;
		}
		RandomAccessFile newFile = null;
		File dir = null;
		for (int i = 1;i<=countFile;i++){
			dir=new File("/Users/zhoulei8/newFile/",i+".txt");
			dir.getParentFile().mkdir();
			dir.createNewFile();
			newFile = new RandomAccessFile(dir,"rw");
			byte[] bytes = new byte[(int) BLOCK_M];
			file.read(bytes);
			newFile.write(bytes);
		}

	}

}
