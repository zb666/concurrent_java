import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Test {

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

//    @RequestMapping("/test")
    public String testMQ2() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //开始生产
        producer.orderProducer();
        //开始消费
        consumer.orderConsumer();
        return "success";
    }

}
