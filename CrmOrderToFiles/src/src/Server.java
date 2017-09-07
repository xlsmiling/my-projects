package src;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.stream.Collectors;

public class Server {

    private final MessageRepository messageRepository = MessageRepository.getInstance();

    public void start() {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("MySelf");
            consumer.setNamesrvAddr("132.228.27.63:9876");
            consumer.setInstanceName("MY");
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
            consumer.subscribe("CRM_ORDER", "*");
            //consumer.subscribe("TopicTest3", "*");
            /**
             * 注册消费的监听
             */
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                try{
                    List<String> messageList = msgs.stream()
                            .map(MessageExt::getBody)
                            .map(String::new)
                            .collect(Collectors.toList());
                    messageRepository.offerBatch(messageList);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 重试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        }catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new MessageHandler()).start();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }

}
