package pub.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 压缩解压缩工具类
 * 
 * @author hajime
 * 
 */
public class CompressUtil {

	/**
	 * zip压缩
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] zip(byte[] data) throws Exception {
		if(data==null|| data.length==0)
			return null;
		byte[] b = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(bos);
		ZipEntry entry = new ZipEntry("zip");
		entry.setSize(data.length);
		zip.putNextEntry(entry);
		zip.write(data);
		zip.closeEntry();
		zip.close();
		b = bos.toByteArray();
		bos.close();
		return b;
	}

	/***
	 * zip解压
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unzip(byte[] data) throws Exception {
		if(data==null|| data.length==0)
			return null;
		byte[] b = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ZipInputStream zip = new ZipInputStream(bis);
		while (zip.getNextEntry() != null) {
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = zip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
		}
		zip.close();
		bis.close();
		return b;
	}

	public static byte[] compress(byte[] data) {
		if(data==null|| data.length==0)
			return null;
		byte[] output = new byte[0];

		Deflater compresser = new Deflater();

		compresser.reset();
		compresser.setInput(data);
		compresser.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!compresser.finished()) {
				int i = compresser.deflate(buf);
				bos.write(buf, 0, i);
			}
			output = bos.toByteArray();
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		compresser.end();
		return output;
	}

	public static byte[] decompress(byte[] data) {
		if(data==null|| data.length==0)
			return null;
		byte[] output = new byte[0];

		Inflater decompresser = new Inflater();
		decompresser.reset();
		decompresser.setInput(data);

		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!decompresser.finished()) {
				int i = decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		decompresser.end();
		return output;
	}

	public static byte[] gzip(byte[] buff) throws IOException {
		if(buff==null|| buff.length==0)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(buff);
		gzip.close();
		byte[] bytes = out.toByteArray();
		return bytes;
	}

	public static byte[] ungzip(byte[] buff) throws IOException {
		if(buff==null|| buff.length==0)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(buff);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			RandomAccessFile file = new RandomAccessFile(
					"D:\\test_zip\\message_zip", "r");
			byte[] b = new byte[(int) file.length()];
			file.readFully(b);
			byte[] db = CompressUtil.ungzip(b);
			String s = new String(db);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
