package com.pixxelpanda.springrestapi.helper;

import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.service.DepartmentService;
import com.pixxelpanda.springrestapi.service.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {
    public static String TYPE = "text/csv";
    
    @Autowired
    private static DepartmentService dService;

    @Autowired
    private static EmployeeService eService;

    private CSVHelper(DepartmentService dService , EmployeeService eService)
    {
        CSVHelper.dService = dService;
        CSVHelper.eService = eService;
    }

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static void csvToDb(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println(csvParser.getRecordNumber() + "Records are being inserted.");
            eService.saveCSVtoDB(csvRecords);
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: ");
        }
    }


}
