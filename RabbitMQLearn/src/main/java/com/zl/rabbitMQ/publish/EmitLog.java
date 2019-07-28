package com.zl.rabbitMQ.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //交换器  名称  类型
        //direct （直连）
        //topic （主题）
        //headers （标题）
        //fanout （分发）
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        for(int i = 0 ; i < 5; i++){
            String message = "Hello World! " + i;
             channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
             System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}

