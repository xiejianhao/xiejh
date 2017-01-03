package httpsmock;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ChangePhp {

	public static void main(String[] args) {
		String filePath = "F:\\workspace2\\BuyPlat_V2.2";
		getFiles(filePath);
	}

	static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath());
			} else {
				if (file.getName().contains(".jsp") || file.getName().contains(".java")
						|| file.getName().contains(".js")||file.getName().contains(".xml")||file.getName().contains(".properties"))
					changeFile(file);
			}
		}
	}

	static void changeFile(File file) {
		String s = null;
		StringBuilder all = new StringBuilder(500);
		BufferedReader readerseg;
		File newFile = new File(file.getAbsolutePath() + "1");
		try {
			InputStreamReader fileReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
			readerseg = new BufferedReader(fileReader);
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			while ((s = readerseg.readLine()) != null) {
				String str = s;
				str = str.replaceAll(".php", ".liangxiaoyu");
				all.append(str).append("\r\n");
			}
			fileOutputStream.write(all.toString().getBytes(Charset.forName("UTF-8")));
			fileOutputStream.flush();
			fileOutputStream.close();
			readerseg.close();
			fileReader.close();
			file.delete();
			String path = newFile.getAbsolutePath();
			path = path.substring(0,path.length()-1);
			System.out.println(path);
			newFile.renameTo(new File(path));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String codeString(File file){  
	    BufferedInputStream bin=null;
		try {
			bin = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    int p=0;
		try {
			p = (bin.read() << 8) + bin.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    String code = null;  
	      
	    switch (p) {  
	        case 0xefbb:  
	            code = "UTF-8";  
	            break;  
	        case 0xfffe:  
	            code = "Unicode";  
	            break;  
	        case 0xfeff:  
	            code = "UTF-16BE";  
	            break;  
	        default:  
	            code = "GBK";  
	    }  
	      
	    return code;  
	}  
}
