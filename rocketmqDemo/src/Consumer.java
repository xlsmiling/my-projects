import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;


public class Consumer {
    public static void main(String[] args) throws MQClientException {
    	
    	/**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("zhiHengCompany");
        consumer.setNamesrvAddr("132.228.27.63:9876");
        consumer.setInstanceName("zhiHeng");
        /**
         * 可以通过设置consumeMessageBatchMaxSize参数来批量接收消息，默认一条
         */
        //consumer.setConsumeMessageBatchMaxSize(10);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 订阅指定topic下tags分别等于TagA或TagC或TagD
         */
        // consumer.subscribe("TopicTest1", "TagA || TagC || TagD");
        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个topic
         */
        consumer.subscribe("C3Output2OthersTopic-02cc95f8ff1f408e984958361da84f0f", "6");
        //consumer.subscribe("TopicTest3", "*");
        /**
         * 注册消费的监听
         */
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            	try {  
            		  
//                    for (MessageExt msg : msgs) {  
//                        System.out.println(" Receive New Messages: " + msg);  
//                    } 
            		MessageExt msg = msgs.get(0);
                    System.out.println(new String(msg.getBody()));
  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 重试  
                }  
  
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;// 成功  
            }
        });

        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        consumer.start();

        System.out.println("Consumer Started.");
      
    }
}