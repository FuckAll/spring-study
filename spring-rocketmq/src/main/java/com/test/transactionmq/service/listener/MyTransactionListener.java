package com.test.transactionmq.service.listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * @author wuxiangdong
 */
@Component
public class MyTransactionListener implements TransactionListener {
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("executeLocal Transaction msg = " + msg);
        System.out.println("executeLocal Transaction arg = " + arg);
        // 并没有直接提交，所以会过短时间重试下面的方法
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("check local transaction msg = " + msg);
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
