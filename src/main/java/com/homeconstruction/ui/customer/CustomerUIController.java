package com.homeconstruction.ui.customer;

import com.homeconstruction.home.api.ProjectNumber;
import com.homeconstruction.home.query.HomeProjection;
import com.homeconstruction.project.api.ProjectName;
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

        ProjectName witte_bruggen = new ProjectName("Witte Bruggen");
        ProjectNumber projectNumber = new ProjectNumber(8);

        ProjectProjection project =
                customerUIService.findProjectByName(witte_bruggen);
        HomeProjection home = customerUIService.findHomeByProjectAndProjectNumber(witte_bruggen, projectNumber);

        model.addAttribute("project", project);
        model.addAttribute("home", home);

        return "customer/customer";
    }
}
