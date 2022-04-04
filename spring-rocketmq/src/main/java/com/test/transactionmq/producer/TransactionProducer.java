package com.test.transactionmq.producer;

import com.test.transactionmq.service.listener.MyTransactionListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wuxiangdong
 */
@Component
public class TransactionProducer {
    private TransactionMQProducer transactionMQProducer;
    private final MyTransactionListener myTransactionListener;
    private ExecutorService executorService = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));


    @Autowired
    public TransactionProducer(MyTransactionListener myTransactionListener) {
        this.myTransactionListener = myTransactionListener;
    }


    @PostConstruct
    public void init() {
        transactionMQProducer = new TransactionMQProducer("transaction-producer");
        transactionMQProducer.setNamesrvAddr("127.0.0.1:9876");
        transactionMQProducer.setSendMsgTimeout(Integer.MAX_VALUE);
        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(myTransactionListener);
        this.start();
    }

    public void start() {
        try {
            this.transactionMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void send(String topic, String data) throws MQClientException {
        Message message = new Message(topic, data.getBytes(StandardCharsets.UTF_8));
        this.transactionMQProducer.sendMessageInTransaction(message, "this is arg");
    }
}
