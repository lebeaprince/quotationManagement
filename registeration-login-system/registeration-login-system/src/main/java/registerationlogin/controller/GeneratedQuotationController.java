package registerationlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import registerationlogin.entity.Customer;
import registerationlogin.entity.GeneratedQuotation;
import registerationlogin.service.CustomerService;
import registerationlogin.service.GeneratedQuotationService;

@Controller
public class GeneratedQuotationController {

    private final GeneratedQuotationService generatedQuotationService;
    private final CustomerService customerService;

    public GeneratedQuotationController(GeneratedQuotationService generatedQuotationService,
                                        CustomerService customerService) {
        this.generatedQuotationService = generatedQuotationService;
        this.customerService = customerService;
    }

    @GetMapping("/letters/{id}")
    public String viewLetter(@PathVariable("id") Long id, Model model) {
        GeneratedQuotation letter = generatedQuotationService.findById(id);
        if (letter == null) {
            return "redirect:/index?error=Letter%20not%20found";
        }
        Customer customer = customerService.findById(letter.getCustomerId());
        model.addAttribute("letter", letter);
        model.addAttribute("customer", customer);
        return "quotation/letter";
    }
}
