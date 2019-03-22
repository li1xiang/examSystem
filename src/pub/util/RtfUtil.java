package pub.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * itext 进行rtf导出工具类
 * @author Administrator
 *
 */
public class RtfUtil {
	/**
	 * 若图片的长或宽超过page的长或宽，对超出更多的一边进行等比例缩放
	 * @param document rtf文档
	 * @param image 图片
	 */
	public void scaleImg(Document document, Image image){
		float imgWidth = image.getWidth();
		float imgHeight = image.getHeight();
		if(imgWidth == 0 || imgHeight == 0)
			return;
		float pWidth = document.getPageSize().getWidth()-document.leftMargin()
				- document.rightMargin();
		float pHeight = document.getPageSize().getHeight() - document.topMargin()
				- document.bottomMargin();
		if(imgWidth > pWidth || imgHeight > pHeight){
			float wRate = pWidth / imgWidth * 100;
			System.out.println(wRate);
			float hRate = pHeight / imgHeight * 100;
			System.out.println(hRate);
			image.scalePercent(wRate > hRate? hRate : wRate);
		}
	}
	
	/**
	 * 创建document
	 * @param pageSize PageSize中的尺寸常量
	 * @param path 文件存放路径
	 * @throws FileNotFoundException
	 */
	public Document createDocument(Rectangle pageSize, String path) throws FileNotFoundException{
		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document, new FileOutputStream(path));
		return document;
	}
}
