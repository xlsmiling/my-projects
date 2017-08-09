
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * 解析计费传送过来的文件
 * 
 * @author Administrator
 *
 */

public class getJfFile {
	
	private static Logger logger = Logger.getLogger(getJfFile.class); 
	private static  Producer<String, String> producer ;
	/**
	 * 读取文件
	 * 
	 * @throws IOException
	 */
	public static void getJfFile() throws IOException{
		File dir = new File("/home/rti_ftp/files");
		FileInputStream in = null;
		String fileName = "";
		File file = null;
		//String sendMsg = "";
		//StringBuffer sb = new StringBuffer();
		File[] subs = dir.listFiles();
		for (File sub : subs) {
			fileName = sub.getName();
			file = new File(sub.getParent() + File.separator + fileName);
			in = new FileInputStream(file);
			if(fileName.endsWith(".Z")){
				
			}else if(fileName.endsWith(".C")){
				file.renameTo(new File("/home/rti_ftp/filesHis/"+fileName));
			}else{
				System.out.println(fileName);
				readConfigFileForFTP(in);
				file.renameTo(new File("/home/rti_ftp/filesHis/"+fileName));
			}
		}
	}
	
	/**
	 * 解析
	 * 
	 */
	public static void readConfigFileForFTP(FileInputStream in){
   	 JSONObject jo = new JSONObject();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));  
        String data = null; 
        String[] datas = new String[]{};
        String createDt = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
        int line = 1;
        try {  
            while ((data = br.readLine()) != null) {
            	if(line >1){
            		datas = data.split("\\^");
                	if(datas.length == 5){
                		jo.put("AREA_CODE",datas[0]);
                    	jo.put("SERV_ID",datas[1]);
                    	createDt = datas[2];
                    	date = sdf.parse(createDt);
                    	createDt = sdf1.format(date);
                    	jo.put("CREATED_DATE",createDt);
                    	jo.put("OWE_BUSINESS_TYPE_ID",datas[3]);
                    	jo.put("ruleId", "test_topic1");
                    	//resultBuffer.append(jo + ",");
        				send("EVENT_COLLECTION",jo.toString());
                	}
            	}
                line++;
            }
            System.out.println("数据量："+(line-2));
        } catch (IOException e) {  
            logger.error("文件读取错误。");  
            e.printStackTrace();  
            //return "配置文件读取失败，请联系管理员.";  
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	/**
	 * 测试方法
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		getJfFile jf = new getJfFile();
		initProducer();
		jf.getJfFile();
		producer.close();
		//logger.info(msg);
		///zip.send("test_topic", msg);
//		String createDt = "20170731145509";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse(createDt);
//    	createDt = sdf1.format(date);
//    	System.out.print(createDt);
	}
	
	/**
	 * 初始化producer
	 */
	public static void initProducer(){
		 Properties props = new Properties();
        //props.put("zookeeper.connect", "132.228.27.55:2181,132.228.27.56:2181,132.228.27.57:2181/kafka");
        props.put("metadata.broker.list", "132.228.27.56:9092,132.228.27.57:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("serializer.encoding", "UTF-8");
        props.put("partitioner.class", "SimplePartitioner");
        //props.put("advertised.host.name", "");
        //props.put("producer.type", "sync");
        // key.serializer.class默认为serializer.class
        //props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        //props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<String, String>(config);
	}
	
	/**
	 * 发送消息
	 * 
	 * @throws IOException
	 */
	public static void send(String topic, String data) throws IOException {
       
//        KeyedMessage message = null;
//        message = new KeyedMessage(topic, null, data, data);
        producer.send(new KeyedMessage<String,String>(topic,data));
        //producer.close();
    }

}