package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Customer;
import ra.service.customer.ICustomerService;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/list")
    public ModelAndView showList(){
        List<Customer> customerList=customerService.findAll();
        ModelAndView modelAndView= new ModelAndView("/customer/list");
        modelAndView.addObject("customers",customerList);
        return modelAndView;
    }

    @GetMapping("/createForm")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;

    }
    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message","them thanh cong");
        return modelAndView;
    }
   @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        if (customer!=null){
            ModelAndView modelAndView= new ModelAndView("/customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/customer/error");
            return modelAndView;
        }
   }
  @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
      ModelAndView modelAndView= new ModelAndView("/customer/edit");
      modelAndView.addObject("customer",customer);
      modelAndView.addObject("message","update thanh cong");
      return modelAndView;
  }
  @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        customerService.remove(id);
        ModelAndView modelAndView =new ModelAndView("redirect:/list");
        return modelAndView;
  }

}
