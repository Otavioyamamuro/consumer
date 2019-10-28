package br.com.fiap.netflix.consumer;

import br.com.fiap.netflix.consumer.entity.Curtida;
import br.com.fiap.netflix.consumer.repository.CurtidaRepository;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Consumer implements CommandLineRunner {

    @Autowired
    private CurtidaRepository curtidaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("fila_do_like", false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");

            Curtida curtida =  curtidaRepository.findByIdMovie(Long.parseLong(message));

            curtida.setCurtida(curtida.getCurtida()+1);

            curtidaRepository.save(curtida);

        };

        channel.basicConsume("fila_do_like", true, deliverCallback, consumerTag -> { });
    }
}
