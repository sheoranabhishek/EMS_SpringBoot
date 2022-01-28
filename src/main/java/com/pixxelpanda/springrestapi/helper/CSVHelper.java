package com.pixxelpanda.springrestapi.helper;

import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.service.DepartmentService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public String TYPE = "text/csv";
    
    @Autowired
    private DepartmentService dService;


    public boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<Employee> csvToDb(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Employee> list= new ArrayList<Employee>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println(csvParser.getRecordNumber() + "Records are being inserted.");
            for (CSVRecord csvRecord : csvRecords) {
                String deptName = csvRecord.get("department");
                Department d = dService.getDepartmentByName(deptName);
                if( d == null)
                {
                    throw new RuntimeException("The department name inside CSV , doesn't match any.");
                }
                
                Employee e = new Employee();
                e.setName(csvRecord.get("name"));
                e.setEmail(csvRecord.get("email"));
                e.setLocation(csvRecord.get("location"));
                e.setDept(d);
                e.setAge(Integer.parseInt(csvRecord.get("age")));
                System.out.println(e.toString());
                list.add(e);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: ");
        }
    }

}
