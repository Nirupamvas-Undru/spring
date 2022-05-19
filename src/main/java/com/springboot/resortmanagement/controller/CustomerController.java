package com.springboot.resortmanagement.controller;


import com.springboot.resortmanagement.dto.CustomerDto;
import com.springboot.resortmanagement.entity.Customer;
import com.springboot.resortmanagement.entity.Resort;
import com.springboot.resortmanagement.exception.ResortNotFoundException;
import com.springboot.resortmanagement.service.CustomerService;
import com.springboot.resortmanagement.service.ResortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

// to get db data in JSON format
//@RestController


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ResortService resortService;

    String customerForm = "customers/customer-form";

    String resortIdno = "resortId";

    @GetMapping("/showAll")
    public String findALl(Model theModel){
        List<Customer> customers = customerService.findAll();

        theModel.addAttribute("customers", customers);

        return "customers/showCustomers";
    }

    @GetMapping("/showFormForAdd/{Id}")
    public String addCustomer(Model theModel, @PathVariable("Id") int theId){
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        theModel.addAttribute(resortIdno,theId);
        return customerForm;
    }

    @PostMapping("/save/{Id}")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult result, @PathVariable("Id") int theId){

        if(result.hasErrors()) {
            return customerForm;
        }else {
            Resort theResort = resortService.findById(theId);
            theCustomer.setResort(theResort);
            customerService.save(theCustomer);
            return "redirect:/customers/findCustomers?resortId=" + theId;
        }

    }

    @GetMapping("/showFormForUpdate")
    public String updateCustomer(@RequestParam("customerId") int theId,@RequestParam("resortId") int resortId, Model theModel){
        Customer theCustomer= customerService.findById(theId);
        theModel.addAttribute(theCustomer);
        theModel.addAttribute(resortIdno,resortId);
        return customerForm;
    }

    @GetMapping("/deleteById")
    public String deleteCustomer(@RequestParam("customerId") int customerId,@RequestParam("resortId") int theId){
        customerService.deleteById(customerId);
        return "redirect:/customers/findCustomers?resortId="+theId;
    }

    @GetMapping("/findCustomers")
    public String findCustomers(@RequestParam("resortId") int theId, Model theModel){
        List<Customer> customers= customerService.findCustomers(theId);
        Resort resorts = resortService.findById(theId);
          if (resorts!=null) {
            theModel.addAttribute("resortCustomers", customers);
            theModel.addAttribute(resortIdno, theId);
            return "customers/showCustomers";
        } else {
            throw new ResortNotFoundException("Resort id not found - " + theId);
        }

    }


    //for dto testing
    @GetMapping("/customer-list")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
