package com.homeconstruction.ui.customer;

import com.homeconstruction.home.query.HomeProjection;
import com.homeconstruction.project.query.ProjectProjection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerUIController {

    private final CustomerUIService customerUIService;

    public CustomerUIController(CustomerUIService customerUIService) {
        this.customerUIService = customerUIService;
    }

    @GetMapping("/ui/customer")
    public String customerUI(Model model) {

        String witte_bruggen = "Witte Bruggen";

        ProjectProjection project =
                customerUIService.findProjectByName(witte_bruggen);
        HomeProjection home = customerUIService.findHomeByProjectAndProjectNumber(witte_bruggen, 8);

        model.addAttribute("project", project);
        model.addAttribute("home", home);

        return "customer/customer";
    }
}
