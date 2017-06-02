package live.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberFormatUtil {

	public static String format( double d){
		NumberFormat numberFormat = NumberFormat.getInstance();
		//numberFormat.setGroupingUsed(false); // 是否设置千分位
		String str = numberFormat.format( d );
		return str;
	}
	
	public static String format( BigDecimal bigDecimal){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		String str = numberFormat.format( bigDecimal );
		return str;
	}
	 
	public static void main(String[] args) {
		String str = NumberFormatUtil.format(1122.330);
		System.out.println(str);
		
		BigDecimal bigDecimal = new BigDecimal("111111111.3333");
		str = NumberFormatUtil.format(bigDecimal); 
		System.out.println(str);
	}
}
