package javax.core.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 */
public class DateUtils {
    /**
     * 两个日期之间的日期列表，包含开始和结束时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDays(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = null;
        Date eDate = null;
        try {
            sdate = df.parse(startTime);
            eDate = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list = new ArrayList<String>();
        while (sdate.getTime() <= eDate.getTime()) {
            list.add(df.format(sdate));
            c.setTime(sdate);
            c.add(Calendar.DATE, 1); // 日期加1天
            sdate = c.getTime();
        }
        return list;
    }
}
