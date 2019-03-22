package pub.util;

import java.text.ParseException;
import java.util.Date;

public interface DateTimeAdpter {

	public Date parse(String source) throws ParseException;
	
	public String format(Date date);
}
