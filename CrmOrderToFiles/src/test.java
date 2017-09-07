import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test{
    public static void main(String[] args){

        new Thread(new testThread()).start();
//        Calendar rightNow = Calendar.getInstance();
//        int minute = rightNow.get(Calendar.MINUTE);
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//        minute = Math.round(minute/5*5);//计算5的整数分钟
//
//        rightNow.set(Calendar.MINUTE, minute);
//        rightNow.set(Calendar.SECOND, 0);
//        long time = rightNow.getTimeInMillis();
//        Date date = new Date();
//        Date date2 = new Date();
//        System.out.println(date.compareTo(rightNow.getTime()));

    }

}