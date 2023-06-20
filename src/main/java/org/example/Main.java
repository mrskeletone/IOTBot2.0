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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {
    public static void main(String[] args) throws TelegramApiException, MqttException {
//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        Bot bot=new Bot();
//        botsApi.registerBot(bot);
//        String topic2="base/off";
//
//        String topic        = "base/warning";
//        int qos             = 1;
//        String broker       = "tcp://dev.rightech.io:1883";
//        String clientid = "tel_bot";
//        String username = "tel";
//        String password = "bot";
//        MemoryPersistence persistence = new MemoryPersistence();
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//        while(true){
//            final Runnable runnable = new Runnable() {
//                int countdownStarter = 2;
//
//                public void run() {
//
//                    countdownStarter--;
//
//                    if (countdownStarter < 0) {
//                        try {
//                            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
//                            // connect options
//                            MqttConnectOptions options = new MqttConnectOptions();
//                            options.setUserName(username);
//                            options.setPassword(password.toCharArray());
//                            options.setCleanSession(true);
//                            // setup callback
//                            client.setCallback(new MqttCallback() {
//
//                                public void connectionLost(Throwable cause) {
//                                    System.out.println("connectionLost: " + cause.getMessage());
//                                }
//
//                                public void messageArrived(String topic, MqttMessage message) {
//                                    System.out.println("topic: " + topic);
//                                    System.out.println("Qos: " + message.getQos());
//                                    System.out.println("message content: " + new String(message.getPayload()));
//                                    bot.sendText(5225475183L,new String(message.getPayload()));
//
//                                }
//
//                                public void deliveryComplete(IMqttDeliveryToken token) {
//                                    System.out.println("deliveryComplete---------" + token.isComplete());
//                                }
//
//                            });
//                            client.connect(options);
//                            client.subscribe(topic, qos);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        scheduler.shutdown();
//                    }
//                }
//            };
//            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
//
//        }
        //while (true){
   Chech();//}
    }

    public static void Chech() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot=new Bot();
        botsApi.registerBot(bot);
        String topic        = "base/warning";
        int qos             = 1;
        String broker       = "tcp://dev.rightech.io:1883";
        String clientid = "tel_bot";
        String username = "tel";
        String password = "bot";
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//            final Runnable runnable = new Runnable() {
//                int countdownStarter = 20;
//
//                public void run() {
//                    System.out.println(countdownStarter);
//                    countdownStarter--;
//
//                    if (countdownStarter < 0) {
//                        try {
//                            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
//                            // connect options
//                            MqttConnectOptions options = new MqttConnectOptions();
//                            options.setUserName(username);
//                            options.setPassword(password.toCharArray());
//                            options.setCleanSession(true);
//                            // setup callback
//                            client.setCallback(new MqttCallback() {
//
//                                public void connectionLost(Throwable cause) {
//                                    System.out.println("connectionLost: " + cause.getMessage());
//                                    try {
//                                        Chech();
//                                    } catch (TelegramApiException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                }
//
//                                public void messageArrived(String topic, MqttMessage message) throws MqttException {
//                                    System.out.println("topic: " + topic);
//                                    System.out.println("Qos: " + message.getQos());
//                                    System.out.println("message content: " + new String(message.getPayload()));
//                                    bot.sendText(5225475183L,new String(message.getPayload()));
//                                    client.disconnect();
//
//                                }
//
//                                public void deliveryComplete(IMqttDeliveryToken token) {
//                                    System.out.println("deliveryComplete---------" + token.isComplete());
//                                }
//
//                            });
//                            client.connect(options);
//                            client.subscribe(topic, qos);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        scheduler.shutdown();
//
//                    }
//                }
//            };
//            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);

    }
}