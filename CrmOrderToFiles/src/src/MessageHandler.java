package src;

import crmOrder.crmOrderInterface;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MessageHandler implements Runnable {

//    private final OrderBuffer orderBuffer = OrderBuffer.getInstance();
	private final MessageRepository messageRepository = MessageRepository.getInstance();


    private volatile boolean flag;


//	private int threadNum;
//    
//    public MessageHandler(int threadNum){
//		this.threadNum = threadNum;
//	}

    @Override
    public void run() {
        flag = true;
        while (flag) {
            String msg;
			try {
                msg = messageRepository.take();
                //msg = "<notice version=\"0.1\"><id>jsbss19_1504155292500_100118462296</id><type>orderlist</type><channelId>-2110035</channelId><from>CRM</from><areacode>025</areacode><dtime>2017-08-23 11:37:31.494</dtime><desc>orderlist</desc></notice>";
	            //
	            Document document;
                document = DocumentHelper.parseText(msg);
                Element rootElement = document.getRootElement(); //得到根结点
                String id = rootElement.elementTextTrim("id");
                //System.out.println(id);
                handleMessage(id);
            }catch (DocumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void interrupt() {
        flag = false;
    }

    private void handleMessage(String msg) {
//        String order = transferMsg(msg);
//        orderBuffer.push(order);
        transferMsg(msg);
    }

    private void transferMsg(String msg) {
        crmOrderInterface crmOrderInterface = new crmOrderInterface();
        crmOrderInterface.callInterface(msg);
        //System.out.println(returnMsg);
        //return returnMsg;
    }

    public static void main(String[] args){
        new Thread(new MessageHandler()).start();
    }

}
