import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PublicKey;

/**
 * Created by xilang on 2018/4/10.
 */
public class Producer {
    private static final Logger _LOG = LoggerFactory.getLogger(Producer.class);
    private static DefaultMQProducer producer;

    public static void initProducer(){
        try {
            producer = new DefaultMQProducer("cssProducer");
            producer.setNamesrvAddr("132.228.27.63:9876");
            producer.setInstanceName("cssProducerInstance");
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static void send(String data){
        try {
            Message msg = new Message("crmMsgTopic", "*", data.getBytes());
            Producer.getInstance().send(msg);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static DefaultMQProducer getInstance() {
        if (producer == null ) {
            try {
                if (producer == null) {
                    initProducer();
                }
            } catch (Exception e) {
                _LOG.error(e.getMessage(), e);
            }
        }
        return producer;
    }
    
    public static void main(String[] args){
        String a = "aaaaa";
        Producer.send(a);
    }
}
