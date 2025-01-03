# Kafka and Java Learning Project

## Overview
This project is a hands-on learning initiative for integrating Apache Kafka with Java. It covers essential concepts such as producing and consuming messages, managing Kafka configurations, and working with Kafka topics programmatically. By exploring this project, you will gain foundational knowledge to build robust distributed systems using Kafka and Java.

---

## Features
- **Producer and Consumer Implementation**: Learn how to produce and consume messages using Kafka.
- **Configuration Management**: Dynamic loading of Kafka configurations from property files.
- **Error Handling**: Comprehensive error handling for better debugging and resilience.
- **Classpath Resource Access**: Demonstrates the use of Java's class loader to access resource files.

---

## Prerequisites

### Software Requirements
- **Java Development Kit (JDK)**: Version 11 or higher.
- **Gradle**: Version 7.0 or higher.
- **Kafka**: Kafka Server setup locally or on a cloud platform.
- **IntelliJ IDEA** (Optional): For easier project management and development.

### Kafka Setup
1. Download and install Kafka from [Apache Kafka Downloads](https://kafka.apache.org/downloads).
2. Start Zookeeper:
   ```sh
   bin/zookeeper-server-start.sh config/zookeeper.properties
   ```
3. Start Kafka Broker:
   ```sh
   bin/kafka-server-start.sh config/server.properties
   ```
4. Create a topic:
   ```sh
   bin/kafka-topics.sh --create --topic topic_0 --bootstrap-server localhost:9092
   ```

---

## Project Structure

```
kafka-java-learning/
├── src/
│   └── main/
│       ├── java/
│       │   └── mg/kafkalearning/
│       │       └── KafkaClient.java
│       └── resources/
│           └── client.properties
├── build.gradle
└── README.md
```

- **`KafkaClient.java`**: The main application file that demonstrates Kafka producer and consumer logic.
- **`client.properties`**: Contains Kafka configurations such as bootstrap servers, client ID, and group ID.
- **`build.gradle`**: Gradle configuration for dependency and task management.

---

## Build and Run

### Step 1: Clone the Repository
```sh
git clone https://github.com/your-username/kafka-java-learning.git
cd kafka-java-learning
```

### Step 2: Build the Project
```sh
gradle build
```

### Step 3: Run the Application
```sh
java -cp build/libs/kafka-java-learning-1.0-SNAPSHOT.jar mg.kafkalearning.KafkaClient
```

---

## Configuration

### Kafka Configuration File (`client.properties`)
Example content of the `client.properties` file:
```properties
bootstrap.servers=localhost:9092
key.serializer=org.apache.kafka.common.serialization.StringSerializer
value.serializer=org.apache.kafka.common.serialization.StringSerializer
key.deserializer=org.apache.kafka.common.serialization.StringDeserializer
value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
group.id=my-kafka-group
```
Make sure this file is placed in the `src/main/resources` directory.

---

## Dependencies
This project uses the following dependencies:

- **Apache Kafka Clients**:
  ```gradle
  implementation group: 'org.apache.kafka', name: 'kafka-clients', version: '3.3.1'
  ```
- **SLF4J Logging**:
  ```gradle
  implementation group: 'org.slf4j', name: 'slf4j-nop', version: '1.7.36'
  ```
- **JUnit 5 for Testing**:
  ```gradle
  testImplementation platform('org.junit:junit-bom:5.10.0')
  testImplementation 'org.junit.jupiter:junit-jupiter'
  ```

---

## Learning Goals
- Understand the basics of Kafka topics, producers, and consumers.
- Learn how to integrate Kafka with Java applications.
- Manage configurations dynamically using property files.
- Improve debugging skills with SLF4J logging.

---

## Troubleshooting

### Common Issues
1. **`ArtifactResolveException` during dependency download**:
   - Ensure a stable internet connection.
   - Check if `mavenCentral()` is accessible.

2. **`IOException` for `client.properties` file**:
   - Verify that the file exists in the `resources` directory.
   - Use the `readConfig` method provided in the `KafkaClient` class.

3. **Kafka broker unreachable**:
   - Confirm Kafka is running on the specified `bootstrap.servers`.
   - Check your firewall settings.

---

## Next Steps
- Implement advanced Kafka features such as partitions and replication.
- Explore Kafka Streams for real-time processing.
- Integrate Kafka with Spring Boot for enhanced productivity.

---

## Author
**Judicael Ratombo**  
Java Developer | Spring Enthusiast  
Feel free to connect with me on [LinkedIn](#) or check out my GitHub [@your-username](https://github.com/your-username).

---

## License
This project is licensed under the MIT License. See `LICENSE` for details.

