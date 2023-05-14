package org.example;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Bot  extends TelegramLongPollingBot{
    @Override
    public String getBotUsername() {
        return "MoveSencorBot";
    }

    @Override
    public String getBotToken() {
        return "6051683156:AAF9P-ugGCGxzQmxbLZ4hwGyYO9vQk20JBE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        String topic        = "base/off";
        String topic2="base/msg";

        int qos             = 1;
        String broker       = "tcp://dev.rightech.io:1883";
        String clientId     = "tel_bot";
        MemoryPersistence persistence = new MemoryPersistence();



      if(Objects.equals(msg.getText(), "off")||Objects.equals(msg.getText(), "Off")){
          String content      = "1";
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setPassword("bot".toCharArray());
            connOpts.setUserName("tel");
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sendText(user.getId(), "deactivate");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
      }else if(Objects.equals(msg.getText(), "on")||Objects.equals(msg.getText(), "On")){
          String content      = "0";
          try {
              MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
              MqttConnectOptions connOpts = new MqttConnectOptions();
              connOpts.setPassword("bot".toCharArray());
              connOpts.setUserName("tel");
              connOpts.setCleanSession(true);
              System.out.println("Connecting to broker: "+broker);
              sampleClient.connect(connOpts);
              System.out.println("Connected");
              System.out.println("Publishing message: "+content);
              MqttMessage message = new MqttMessage(content.getBytes());
              message.setQos(qos);
              sampleClient.publish(topic, message);
              System.out.println("Message published");
              sendText(user.getId(), "activate");
          } catch(MqttException me) {
              System.out.println("reason "+me.getReasonCode());
              System.out.println("msg "+me.getMessage());
              System.out.println("loc "+me.getLocalizedMessage());
              System.out.println("cause "+me.getCause());
              System.out.println("excep "+me);
              me.printStackTrace();
      }

    }if(Objects.equals(msg.getText(), "command")){
          sendText(user.getId(),"on");
          sendText(user.getId(), "off");
        }
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
            sampleClient.subscribe(topic2, (topic3, msg3) -> {
                byte[] payload = msg3.getPayload();
                System.out.println(new String(msg3.getPayload()));
                sendText(5225475183L,new String(msg3.getPayload()));
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
        }}
//5225475183
public void sendText(Long who, String what){
    SendMessage sm = SendMessage.builder()
            .chatId(who.toString()) //Who are we sending a message to
            .text(what).build();    //Message content
    try {
        execute(sm);                        //Actually sending the message
    } catch (TelegramApiException e) {
        throw new RuntimeException(e);      //Any error will be printed here
    }
}
}
