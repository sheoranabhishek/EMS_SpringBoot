package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Department;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVService {
    void save(MultipartFile f);
}
