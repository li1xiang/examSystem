package saptacims.cst.i18n;

import java.io.Serializable;

/**
 * 常量翻译实体
 * @author qianwei
 *
 */
@SuppressWarnings("serial")
public class KeyCst implements Serializable {
	/**
	 * key
	 */
	private String key;
	
	/**
	 * 需要翻译的常量
	 */
	private String cstValue;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCstValue() {
		return cstValue;
	}

	public void setCstValue(String cstValue) {
		this.cstValue = cstValue;
	}
	
	public static String getValue(String key,String cstValue)
	{
		return key.toUpperCase()+"."+cstValue;
	}
}
