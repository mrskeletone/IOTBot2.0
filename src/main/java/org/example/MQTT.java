package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTT {
    private MqttClient mqttClient;

    public MQTT(MqttClient mqttClient) {
        this.mqttClient = mqttClient;

    }

    public void Connected_MQTT(String broker, String id ,String username,String password) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        this.mqttClient=new MqttClient(broker,id,persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setPassword(password.toCharArray());
        connOpts.setUserName(username);
        connOpts.setCleanSession(true);
        this.mqttClient.connect(connOpts);
    }
}
