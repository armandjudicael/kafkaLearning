package mg.kafkalearning;

import java.lang.System;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;

public class ClientExample {
  public static void main(String[] args) {
    try {
      String topic = "topic_0";
      final Properties config = readConfig("client.properties");

      produce(topic, config);
      consume(topic, config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Properties readConfig(String configFileName) throws IOException {
    Properties config = new Properties();

    try (InputStream inputStream = KafkaClient.class.getClassLoader().getResourceAsStream(configFileName)) {
      if (inputStream == null) {
        throw new IOException("Configuration file not found in resources: " + configFileName);
      }
      config.load(inputStream);
    }

    System.out.println("Configuration loaded successfully from resources.");
    return config;
  }

  public static void produce(String topic, Properties config) {
    // sets the message serializers
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    // creates a new producer instance and sends a sample message to the topic
    String key = "key";
    String value = "value";
    Producer<String, String> producer = new KafkaProducer<>(config);
    producer.send(new ProducerRecord<>(topic, key, value));
    System.out.println(
      String.format(
        "Produced message to topic %s: key = %s value = %s", topic, key, value
      )
    );

    // closes the producer connection
    producer.close();
  }

  public static void consume (String topic, Properties config) {
    // sets the group ID, offset and message deserializers
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "java-group-1");
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

    // creates a new consumer instance and subscribes to messages from the topic
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);
    consumer.subscribe(Arrays.asList(topic));

    while (true) {
      // polls the consumer for new messages and prints them
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      for (ConsumerRecord<String, String> record : records) {
        System.out.println(
          String.format(
            "Consumed message from topic %s: key = %s value = %s", topic, record.key(), record.value()
          )
        );
      }
    }
  }
}