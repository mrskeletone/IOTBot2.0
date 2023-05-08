package org.example;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws TelegramApiException, MqttException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot=new Bot();
        botsApi.registerBot(bot);

        String topic        = "base/msg";
        String topic2="base/off";
        int qos             = 1;
        String broker       = "tcp://dev.rightech.io:1883";
        String clientId     = "tel_bot";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setPassword("bot".toCharArray());
            connOpts.setUserName("tel");
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            //System.out.println("Publishing message: "+content);
            //MqttMessage message = new MqttMessage(content.getBytes());
            // message.setQos(qos);
            CountDownLatch receivedSignal = new CountDownLatch(10);
            sampleClient.subscribe(topic, (topic3, msg3) -> {
                byte[] payload = msg3.getPayload();
                System.out.println(new String(msg3.getPayload()));
               bot.sendText(5225475183L,new String(msg3.getPayload()));
                // ... payload handling omitted
                receivedSignal.countDown();
            });
            receivedSignal.await(5, TimeUnit.SECONDS);
            //  sampleClient.publish(topic, message);
            } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}