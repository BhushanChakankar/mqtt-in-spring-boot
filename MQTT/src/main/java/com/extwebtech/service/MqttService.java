package com.extwebtech.service;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

	@Value("${mqtt.server-uri}")
	private String serverUri;

	@Value("${mqtt.default-topic}")
	private String defaultTopic;

	@Value("${mqtt.username}")
	private String username;

	@Value("${mqtt.password}")
	private String password;

	private MqttClient mqttClient;

	public void connect() throws MqttException {
		mqttClient = new MqttClient(serverUri, MqttClient.generateClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName(username);
		options.setPassword(password.toCharArray());
		mqttClient.connect(options);
	}

	public void publish(String message) throws MqttException {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(message.getBytes());
		mqttClient.publish(defaultTopic, mqttMessage);
	}

	public void subscribe(MqttCallback callback) throws MqttException {
		mqttClient.setCallback(callback);
		mqttClient.subscribe(defaultTopic);
	}
}
