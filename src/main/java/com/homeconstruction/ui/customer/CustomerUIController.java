package com.homeconstruction.ui.customer;

import com.homeconstruction.project.query.ProjectProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerUIController {

    private final CustomerUIService customerUIService;

    @Autowired
    public CustomerUIController(CustomerUIService customerUIService) {
        this.customerUIService = customerUIService;
    }

    @GetMapping("/ui/customer")
    public String customerUI(Model model) {

        ProjectProjection project =
                customerUIService.findProjectByNameAndHomeNumber("Witte Bruggen", 8);

        model.addAttribute("project", project);
        return "customer/customer";
    }
}
