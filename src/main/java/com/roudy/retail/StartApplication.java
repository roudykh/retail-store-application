package com.roudy.retail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.roudy.retail.controller.BasketRepository;
import com.roudy.retail.controller.GoodRepository;
import com.roudy.retail.controller.PurchaseRepository;
import com.roudy.retail.controller.UserRepository;
import com.roudy.retail.model.Basket;
import com.roudy.retail.model.Good;
import com.roudy.retail.model.Purchase;
import com.roudy.retail.model.User;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    GoodRepository goodRepository;
    
    @Autowired
    PurchaseRepository purchaseRepository;
    
    @Autowired
    BasketRepository basketRepository;

    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{

        log.info("Starting Application...");
        startTestCases();
        
    }

    // Using H2 database
    void startTestCases() throws Exception{

        jdbcTemplate.execute("DROP TABLE user IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE user(" +
                "id SERIAL, name VARCHAR(100), type VARCHAR(10), created_date date)");

        List<User> list = Arrays.asList(
                new User("User A", "Employee", "01022018"),
                new User("User B", "Affliate", "01042019"),
                new User("User C", "Customer", "01032019"),
                new User("User D", "Customer", "01012017")
        );

        list.forEach(x -> {
            log.info("Saving...{}", x.getName());
            userRepository.save(x);
        });
        
        jdbcTemplate.execute("DROP TABLE good IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE good(" +
                             "id SERIAL, name VARCHAR(100), type VARCHAR(10), price number)");
        
        List<Good> goodList = Arrays.asList(
                new Good(1L, "Mango", "Grocery", 1.5),
                new Good(2L, "Apple", "Grocery", 3),
                new Good(3L, "Knife", "Supply", 5),
                new Good(4L, "Plate", "Supply", 6)
        );
        
        HashMap<Good, Integer> basket = new HashMap<Good, Integer>();
        
        goodList.forEach(u -> {
            log.info("Saving...{}", u.getName());
            goodRepository.save(u);
            basket.put(u, 1);
        });

        jdbcTemplate.execute("DROP TABLE basket IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE basket(" +
                             "id long, goodid long, quantity int)");
        
        long basketId = 1L;
        log.info("Saving basket...");
        basketRepository.save(new Basket(basket), basketId);
        
        List<Purchase> purchaseList = Arrays.asList(
        		new Purchase(basketRepository.findBasketById(basketId), userRepository.findByUserId(1L)),
                new Purchase(basketRepository.findBasketById(basketId), userRepository.findByUserId(2L)),
                new Purchase(basketRepository.findBasketById(basketId), userRepository.findByUserId(3L)),
                new Purchase(basketRepository.findBasketById(basketId), userRepository.findByUserId(4L))
        );
        


        jdbcTemplate.execute("DROP TABLE purchase IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE purchase(" +
                             "id SERIAL, userid int, basketid int, price number)");
        
        purchaseList.forEach(u -> {
            log.info("Saving new purchase...");
            purchaseRepository.save(u, basketId);
            u.checkout();
        });

    }


}
