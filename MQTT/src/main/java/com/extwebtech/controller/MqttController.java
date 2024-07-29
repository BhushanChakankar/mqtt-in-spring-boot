package com.extwebtech.controller;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extwebtech.service.MqttService;
import com.extwebtech.service.SimpleMqttCallback;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

	@Autowired
	private MqttService mqttService;

	@GetMapping("/connect")
	public String connect() throws MqttException {
		mqttService.connect();
		return "Connected to MQTT broker";
	}

	@PostMapping("/publish")
	public String publishMessage(@RequestBody String message) throws MqttException {
		mqttService.publish(message);
		return "Message published: " + message;
	}

	@GetMapping("/subscribe")
	public String subscribe() throws MqttException {
		MqttCallback callback = new SimpleMqttCallback();
		mqttService.subscribe(callback);
		return "Subscribed to topic";
	}
}
