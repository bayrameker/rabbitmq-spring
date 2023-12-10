package com.backend.server.service;

import com.backend.server.model.Product;
import com.backend.server.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createProductAsync(Product product) {

        try {
            rabbitTemplate.convertAndSend("crudQueue", product);
        } catch (AmqpException e) {
            // Loglama yapabilir veya özel bir işlem gerçekleştirebilirsiniz.
            throw e; // Hata, GlobalExceptionHandler tarafından ele alınacaktır.
        }
    }

    @RabbitListener(queues = "crudQueue")
    public void handleProductMessage(String productJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productJson, Product.class);
        createProduct(product);
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            productRepository.save(product);
        }
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // RabbitMQ mesaj işleme metodları...
}
