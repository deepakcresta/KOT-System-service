/*************************************************************************
 * 
 * AADIM INNOVATION CONFIDENTIAL
 * __________________  
                    _ _             _____                             _   _             
     /\            | (_)           |_   _|                           | | (_)            
    /  \   __ _  __| |_ _ __ ___     | |  _ __  _ __   _____   ____ _| |_ _  ___  _ __  
   / /\ \ / _` |/ _` | | '_ ` _ \    | | | '_ \| '_ \ / _ \ \ / / _` | __| |/ _ \| '_ \ 
  / ____ \ (_| | (_| | | | | | | |  _| |_| | | | | | | (_) \ V / (_| | |_| | (_) | | | |
 /_/    \_\__,_|\__,_|_|_| |_| |_| |_____|_| |_|_| |_|\___/ \_/ \__,_|\__|_|\___/|_| |_|
                                                                                        
 *
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained here in is, and remains
 * the property of Aadim Innovation and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here in are proprietary to Aadim Innovation. Dissemination of this
 * information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Aadim Innovation.
 * 
 */
package com.kotservice.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;
import com.kotservice.contact.Contact;
import com.kotservice.contact.ContactCreateDto;
import com.kotservice.contact.ContactReponseListDto;
import com.kotservice.contact.ContactResponseDto;
import com.kotservice.order.OrderRepository;

/**
 * <<Description Here>>
 * @author vs
 * @version 
 * @since , Nov 4, 2022
 */
@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;
  
  //  for posting  the data
  public CustomerResponseDto addCustomer(CustomerCreateDto customerCreateDto) {
    Customer customer = new Customer();
    customer.setAddress(customerCreateDto.getAddress());
    customer.setCustomerName(customerCreateDto.getCustomerName());
    customer.setEmail(customerCreateDto.getEmail());
    customer.setPhoneNumber(customerCreateDto.getPhoneNumber());
    
    Customer saveCustomer = customerRepository.save(customer);
   return getCustomerResponseDto(saveCustomer);
   

}
  // for getting the data 
  private CustomerResponseDto getCustomerResponseDto(Customer customer) {
    CustomerResponseDto  response = new CustomerResponseDto();
    response.setAddress(customer.getAddress());
    response.setCustomerName(customer.getCustomerName());
    response.setEmail(customer.getEmail());
    response.setPhoneNumber(customer.getPhoneNumber());
    return response;
}
  
  public CustomerResponseListDto getAll() {
    List<CustomerResponseDto>customerResponseList =new ArrayList<>();
    List<Customer> customer =(List<Customer>)customerRepository.findAll();
    for (Customer customers :customer) {
      customerResponseList.add(getCustomerResponseDto(customers));
    }
    CustomerResponseListDto response = new CustomerResponseListDto();
    response.setCustomers(customerResponseList);
//    response.setTotal(long)customer.size();
    return response;   
}
  public CustomerResponseDto getById( Long id) {
    Optional<Customer> optionalCustomer= customerRepository.findById(id);
        if(optionalCustomer.isPresent()) {
          return getCustomerResponseDto(optionalCustomer.get());
        }
        else {
          return null;
        }
  }
}