package com.pixxelpanda.springrestapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface CSVService {
    public void save(MultipartFile f);
}
