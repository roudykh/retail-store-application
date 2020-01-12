package com.roudy.retail;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.roudy.retail.controller.GoodRepository;
import com.roudy.retail.controller.UserRepository;
import com.roudy.retail.model.Good;
import com.roudy.retail.model.User;

@SpringBootTest
class RetailStoreApplicationTests {


    @Autowired
    GoodRepository goodRepository;

    @Autowired
    UserRepository userRepository;
    
	@Test
	void contextLoads() {

		Good mango = new Good(1L, "Mango", "Grocery", 1.5);
	    goodRepository.save(mango);
	 
	    Good found = goodRepository.findByGoodId(mango.getID());
	 
	    assertThat(found.getName())
	      .isEqualTo(mango.getName());
	    
	    assertThat(found.getID())
	      .isEqualTo(mango.getID());
	    
	    assertThat(found.getPrice())
	      .isEqualTo(mango.getPrice());

	    assertThat(found.getType())
	      .isEqualTo(mango.getType());
	    
	    User alex = new User("Alex", "Employee", "01022018");
	    userRepository.save(alex);
	 
	    User userfound = userRepository.findByUserName(alex.getName());
	 
	    assertThat(userfound.getName())
	      .isEqualTo(alex.getName());

	    assertThat(userfound.getType())
	      .isEqualTo(alex.getType());
	}

}
