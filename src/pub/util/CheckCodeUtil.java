package pub.util;

import java.util.LinkedList;
import java.util.Random;

public class CheckCodeUtil {

	public static String getContent(int size) {
		  String content = "";   
		  for(int i=0;i <size;i++){   
			  content+=getChar();   
			  try { 
				Thread.sleep(new   Random().nextInt(10)+10);//休眠以控制字符的重复问题  
			  } catch (InterruptedException e) { 
				e.printStackTrace(); 
			  } 
		  }   
		  return content; 
	} 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static char getChar(){   
		Random random=new Random();   
		char   ch= '0';  
		LinkedList ls=new LinkedList();   
		for(int i=0;i <10;i++){//0-9   
			ls.add(String.valueOf(48+i));   
		}   
		for(int i=0;i <26;i++){//A-Z   
			ls.add(String.valueOf(65+i));   
		}   
		for(int i=0;i <26;i++){//a-z   
			ls.add(String.valueOf(97+i));   
		}   
		int index=random.nextInt(ls.size());  
		System.out.println("index"+index); 
		if(index> (ls.size()-1)){   
			index=ls.size()-1;   
		}   
		ch=(char)Integer.parseInt(String.valueOf(ls.get(index)));   
		return ch;   
	}
	
}


