import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testThread implements Runnable {

    @Override
    public void run() {
        //while(true){
            Calendar rightNow = Calendar.getInstance();
            int minute = rightNow.get(Calendar.MINUTE);
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
            minute = Math.round(minute/5*5);//计算5的整数分钟

            rightNow.set(Calendar.MINUTE, minute);
            rightNow.set(Calendar.SECOND, 0);
            long time = rightNow.getTimeInMillis();
            Date date = new Date();
            System.out.println(sf.format(rightNow.getTime()));
            while(Long.toString(time).substring(0,10) == Long.toString(date.getTime()).substring(0,10)){
                System.out.println(rightNow.getTimeInMillis()+300000);
                System.out.println((new Date()).getTime());
            }
            //System.out.println((new Date()).getTime());
        //}
    }
}