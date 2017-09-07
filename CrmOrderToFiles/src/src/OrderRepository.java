package src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderRepository {
    public static final OrderRepository orderRepository = new OrderRepository();

    private final BlockingQueue<String> orderQueue = new LinkedBlockingQueue<>();
    //生成文件路径
    private static String path = "/home/rti/orderFiles/";
    //private static String path = "/Users/xilang/Documents/test/";

    //文件路径+名称
    private static String filenameTemp;

    private static String fileName;
    
    private static long time;

    public static OrderRepository getInstance() {
        return orderRepository;
    }

    private OrderRepository() {
        new Thread(new DataWriter()).start();
    }

    public void offer(String msg) {
        orderQueue.offer(msg);
    }

    public class DataWriter implements Runnable {
        @Override
        public void run() {
            while (true) {
                writeData();
            }
        }
        private void writeData() {
            // TODO write to hdfs
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
            Calendar rightNow = Calendar.getInstance();
            int minute = rightNow.get(Calendar.MINUTE);
            minute = Math.round(minute/5*5);//计算10的整数分钟
            rightNow.set(Calendar.MINUTE, minute);
            rightNow.set(Calendar.SECOND, 0);
            time = rightNow.getTimeInMillis();
            fileName = "busi_order_" + sf.format(time + 300000);
            System.out.println(fileName);
            createFile(fileName);
        }

        private void createFile(String fileName){
            filenameTemp = path+fileName+".txt";//文件路径+名称+文件类型
            File file = new File(filenameTemp);
            try {
                //如果文件不存在，则创建新的文件
                if(!file.exists()){
                    file.createNewFile();
                }
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void writeFileContent(String filepath) throws IOException {
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, true)));
                StringBuffer conent = new StringBuffer();
                int count = 0;
                Date endDate;
                while(count < 1000){
                    endDate = new Date();
                    if(endDate.getTime() < (time + 300000)) {
                        conent.append(orderQueue.take() + "\n");
                        count++;
                        if(count == 1000){
                            out.write(conent.toString());
                            conent.setLength(0);// = new StringBuffer();
                            out.flush();
                        	count = 0;
                        }
                    }else{
                    	out.write(conent.toString());
                    	out.flush();
                    	System.out.println("*****end!");
                        out.close();
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
