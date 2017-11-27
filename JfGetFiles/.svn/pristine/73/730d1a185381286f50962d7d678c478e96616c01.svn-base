import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class InitProducer {
    private static DefaultMQProducer producer ;

    /**
     * 初始化producer
     */
    public static void initProducer(){
        try {
            /**
             * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
             * 注意：ProducerGroupName需要由应用来保证唯一<br>
             * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
             * 因为服务器会回查这个Group下的任意一个Producer
             */
            producer = new DefaultMQProducer("jfMsgGroups");

            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.setNamesrvAddr("132.252.3.39:9876;132.252.3.40:9876");
            producer.setInstanceName("jfMsg");
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     *
     * @param
     * @throws java.io.IOException
     */
    public static void send(String data){
        try {
            Message msg = new Message("AdapterTopic", data.getBytes());
            producer.send(msg);
//            SendResult sendResult = producer.send(msg);
//            System.out.println(sendResult);
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

    public static void destory() {
        if (producer != null) {
            producer.shutdown();
        }
    }
}
