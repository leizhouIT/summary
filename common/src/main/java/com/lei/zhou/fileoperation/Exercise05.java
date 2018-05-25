package com.lei.zhou.fileoperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) throws Exception {
		System.out.println("请输入要合并的文件路径:");
		File dir = new File("D:\\gaoqing\\高清视频.mp4");
		
		
		File file = new File(dir.getParent(), ".temp");
		File[] arr = dir.listFiles();						
		
		OutputStream out = new FileOutputStream(file);		
		for (File subfile : arr) {
			InputStream in = new FileInputStream(subfile);	
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1)		
				out.write(buffer, 0, length);				
			in.close();
			subfile.delete();								
		}
		out.close();
		
		dir.delete();			
		file.renameTo(dir);
		System.out.println("合并完成");
	}

}
