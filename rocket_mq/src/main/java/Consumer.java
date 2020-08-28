import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Value("${apache.rocketmq.producer.producerGroup")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    public void orderConsumer() throws MQClientException {
        DefaultMQPushConsumer mqPushConsumer = new DefaultMQPushConsumer();
        mqPushConsumer.setNamesrvAddr(namesrvAddr);

        mqPushConsumer.subscribe("Topic1","TagA||TagC||TagD");
        //订阅指定Topic下的所有消息
        mqPushConsumer.subscribe("Topic2","*");
        //Consumer可以订阅多个消息
        mqPushConsumer.subscribe("Topic3","*");

        mqPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        mqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> list,
                    ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                MessageExt messageExt = list.get(0);
                String topic = messageExt.getTopic();
                if(topic.equals("Topic1")){

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        mqPushConsumer.start();

    }
}
