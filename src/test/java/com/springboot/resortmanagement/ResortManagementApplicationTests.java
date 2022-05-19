package com.springboot.resortmanagement;

import com.springboot.resortmanagement.entity.Customer;
import com.springboot.resortmanagement.entity.Resort;
import com.springboot.resortmanagement.entity.Villa;
import com.springboot.resortmanagement.repository.CustomerRepository;
import com.springboot.resortmanagement.repository.ResortRepository;
import com.springboot.resortmanagement.repository.VillaRepository;
import com.springboot.resortmanagement.service.CustomerService;
import com.springboot.resortmanagement.service.ResortService;
import com.springboot.resortmanagement.service.VillaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ResortManagementApplicationTests {

	@Autowired
	private ResortService resortService;

	@MockBean
	private ResortRepository resortRepository;

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private VillaService villaService;

	@MockBean
	private VillaRepository villaRepository;



	// Resort testing

	@Test
	void findAllTestResort() {
		when(resortRepository.findAll()).thenReturn(Stream.
				of(new Resort("Omen","Hyderabad",4.0),
						new Resort("Samsung Park","Mumbai",5.0)).collect(Collectors.toList()));

		assertEquals(1, resortService.getAllResorts().size());

	}

	@Test
	@SuppressWarnings("squid:S2699")
	void saveTestResort(){
		Resort resort = new Resort("Jungle Resort","Delhi",4.1);
		when(resortRepository.save(any(Resort.class))).thenReturn(resort);

		resort.getResortId();
		resort.getResortAddress();
		resort.getResortName();
		resort.getResortRating();
		resort.getVilla();
		resort.getCustomers();

		resort.setResortId(1);
		resort.setResortName("Nova");
		resort.setResortAddress("Hyderabad");
		resort.setResortRating(3.0);
		resort.toString();
	}

	@Test
	void DeleteTestResort(){
		int theId = 1;
		resortService.deleteById(theId);
		verify(resortRepository).deleteById(any());
	}



	// Customer testing
	@Test
	void findAllTestCustomer() {
		when(customerRepository.findAll()).thenReturn(Stream.
				of(new Customer("Olive","Park","olivepark@gmail.com"),
						new Customer("John","Deo","johndeo@gmail.com")).collect(Collectors.toList()));
		assertEquals(1, customerService.getAllCustomers().size());
	}

	@Test
	@SuppressWarnings("squid:S2699")
	void saveTestCustomer(){
		Customer customer = new Customer("Mary","Jain","maryjain@gmail.com");
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		customer.getCustomerId();
		customer.getResort();
		customer.getEmail();
		customer.getFirstName();
		customer.getLastName();

		customer.setCustomerId(1);;
		customer.setFirstName("alpha");
		customer.setLastName("beta");
		customer.setEmail("alphabeta@gmail.com");
		customer.setResort(new Resort("Jungle Resort","Delhi",4.1));
		customer.toString();
	}

	@Test
	void DeleteTestCustomer(){
		int theId = 1;
		customerService.deleteById(theId);
		verify(customerRepository).deleteById(any());
	}

	@Test
	void GetCustomeer(){
		customerService.findAll();
		assertEquals(1, customerService.getAllCustomers().size());
	}
/*
	@Test
	void FindById(){
		int theId = 3;
		customerService.findById(theId);
		verify(customerRepository).deleteById(any());
	}*/

	// Villa testing
	@Test
	void findAllTestVilla() {
		when(villaRepository.findAll()).thenReturn(Stream.
				of(new Villa(2,3), new Villa(4,5)).collect(Collectors.toList()));
		assertEquals(1, villaService.getAllVillas().size());
	}

	@Test
	@SuppressWarnings("squid:S2699")
	void saveTestVilla(){
		Villa villa = new Villa(2,3);
		when(villaRepository.save(villa)).thenReturn(villa);
		villa.getVillaId();
		villa.getVillaNo();
		villa.getResort();
		villa.getStreetNo();

		villa.setVillaId(1);
		villa.setVillaNo(2);
		villa.setStreetNo(34);
		villa.setResort(new Resort("Jungle Resort","Delhi",4.1));
		villa.toString();
	}

	@Test
	void DeleteRestVilla(){
		int theId = 1;
		customerService.deleteById(theId);
		verify(customerRepository).deleteById(any());
	}

}
