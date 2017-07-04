package site.chronos.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utils {
	/**
	 * 获取id
	 * 
	 * @return
	 */
	public static String getNewId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
    /**
     * yyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static  String getNewTime(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String newTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
        return newTime;
    }

}
