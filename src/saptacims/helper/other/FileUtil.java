package saptacims.helper.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	static final Log log = LogFactory.getLog(FileUtil.class);

	public static boolean writeFile(String fileFullPath,byte[] fileBytes)
	{
		RandomAccessFile rafile = null;
		try {
			File thisfile = new File(fileFullPath);
			if(thisfile.exists())
			{//如果已经存在此文件，先删除，后添加
				thisfile.delete();
			}
			File file = new File(fileFullPath);
			rafile = new RandomAccessFile(file, "rw");
			rafile.write(fileBytes);
			rafile.close();
			return true;
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
			log.error("文件不存在" + ex1.getMessage());
		} catch (IOException ex2) {
			ex2.printStackTrace();
			log.error("读写文件错误" + ex2.getMessage());
		}finally{
			if(rafile != null)
				try
				{
					rafile.close();
				} catch (IOException e)
				{
					e.printStackTrace();
					log.error("关闭文件错误" + e.getMessage());
				}
		}
		return false;
	}
	
	
	public static byte[] readFile(String fileFullPath) {
		RandomAccessFile rafile = null;
		try {
			File file = new File(fileFullPath);
			rafile = new RandomAccessFile(file, "r");
			int len = (int)rafile.length();
			byte[] ret = new byte[len];
			rafile.read(ret);
			rafile.close();
			return ret;
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
			log.error("文件不存在" + ex1.getMessage());
		} catch (IOException ex2) {
			ex2.printStackTrace();
			log.error("读写文件错误" + ex2.getMessage());
		}finally{
			if(rafile != null)
				try
				{
					rafile.close();
				} catch (IOException e)
				{
					e.printStackTrace();
					log.error("关闭文件错误" + e.getMessage());
				}
		}
		return null;
	}
	
	public static String readFileStr(String fileFullPath,String fileEncoding)
	{
		String str;
		try
		{
			byte[] bt = readFile(fileFullPath);
			str = new String(bt,fileEncoding);
			return str;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			log.error("读写文件错误" + e.getMessage());
		}
		return "";
	}
}
