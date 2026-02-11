package com.culturals.registration.controller;

import com.culturals.registration.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    
    @Autowired
    private WinnerService winnerService;
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/department-points")
    public String viewDepartmentPoints(Model model) {
        Map<String, Integer> departmentPoints = winnerService.getDepartmentPoints();
        model.addAttribute("departmentPoints", departmentPoints);
        return "department-points";
    }
}
