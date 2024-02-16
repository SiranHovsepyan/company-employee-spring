package com.example.companyemployee.controller;

import com.example.companyemployee.entity.Company;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.security.SpringUser;
import com.example.companyemployee.service.CategoryService;
import com.example.companyemployee.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompaniesController {
    private final CompanyService companyService;
    private final CategoryService categoryService;
//
//    public CompaniesController(CompanyRepository companyRepository) {
//        this.companyRepository = companyRepository;
//    }

    @GetMapping("/companies")
    public String companiesPage(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        modelMap.put("companies", companies);
        return "companies";
    }

    @GetMapping("/companies/add")
    public String addCompanyPage(ModelMap modelMap) {
        modelMap.addAttribute("categories",categoryService.findAll());
        return "addCompany";
    }

//    @PostMapping("/companies/add")
//    public String addCompany(@RequestParam("name") String name,
//                             @RequestParam("address") String address) {
//        Company company = new Company();
//        company.setName(name);
//        company.setAddress(address);
//        companyRepository.save(company);
//        return "redirect:/companies";
//    }

    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute Company company, @AuthenticationPrincipal SpringUser springUser) {
        company.setUser(springUser.getUser());
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.delete(id);
        return "redirect:/companies";
    }


}
