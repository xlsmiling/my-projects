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
        //System.out.println(orderQueue.size());
        orderQueue.offer(msg);
    }

//    public String take() throws InterruptedException {
//        return orderQueue.take();
//    }

    public class DataWriter implements Runnable {
        @Override
        public void run() {
        	//try {
	            while (true) {
					//Thread.sleep(290_000);

	                writeData();
	            }
//        	} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
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
            //Date date = new Date();
            //if(Long.toString(time).substring(0,10) == Long.toString(date.getTime()).substring(0,10)){
//            if(date.compareTo(rightNow.getTime()) == 0){
            fileName = "busi_order_" + sf.format(time + 300000);
            System.out.println("*****start****");
            System.out.println(fileName);
            createFile(fileName);
//            }
        }

        private void createFile(String fileName){
            filenameTemp = path+fileName+".txt";//文件路径+名称+文件类型
            File file = new File(filenameTemp);
            try {
                //如果文件不存在，则创建新的文件
                if(!file.exists()){
                    file.createNewFile();
                    //System.out.println("success create file,the file is "+filenameTemp);
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
                //Date beginDate = new Date();
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, true)));
                StringBuffer conent = new StringBuffer();
                int count = 0;
                Date endDate;
                //String head = "staff_id|channel_nbr|sub_time|custId|po_inst_id|action_cd|offer_spec_id|pd_inst_id|pd_spec_id";
                //conent.append(head + "\n");
                while(count < 1000){
                    endDate = new Date();
                    //System.out.println("end="+endDate.getTime()+"**********start="+(date.getTime() + 30000));
                    if(endDate.getTime() < (time + 300000)) {
                    	//System.out.println(orderQueue.take());
                        conent.append(orderQueue.take() + "\n");
                        count++;
                        if(count == 1000){
                            out.write(conent.toString());
                            //System.out.println("!!!!!"+conent.length());
                            conent.setLength(0);// = new StringBuffer();
                            out.flush();
                        	count = 0;
                        }
                    }else{
                    	out.write(conent.toString());
                    	out.flush();
                    	System.out.println("*****end****");
                        out.close();
                        return;
                    }
                }
//                System.out.println("!!!!!"+conent.length());
//                out.write(conent.toString());
//                out.flush();
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
