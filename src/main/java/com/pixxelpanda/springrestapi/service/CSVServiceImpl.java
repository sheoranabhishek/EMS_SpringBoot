package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.helper.CSVHelper;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVServiceImpl implements CSVService{

        @Autowired
        EmployeeRepository eRepository;

        public void save(MultipartFile file) {
            try {
                CSVHelper.csvToDb(file.getInputStream());
//                eRepository.saveAll(list);
            } catch (Exception e) {
                throw new RuntimeException("fail to store csv data");
            }
        }

}
