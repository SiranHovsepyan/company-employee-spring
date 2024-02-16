package com.example.companyemployee.service;

import com.example.companyemployee.entity.Company;

import java.util.List;

public interface CompanyService {
    Company save(Company company);
    List<Company> findAll();
    void delete(int companyId);

}
