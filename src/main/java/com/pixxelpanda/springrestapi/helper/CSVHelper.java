package com.pixxelpanda.springrestapi.helper;

import com.pixxelpanda.springrestapi.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Employee> csvToDb(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Employee> list= new ArrayList<Employee>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println(csvParser.getRecordNumber() + "Records are being inserted.");
            for (CSVRecord csvRecord : csvRecords) {
//                Employee e = new Employee();
//                e.setName(csvRecord.get("name"));
//                e.setEmail(csvRecord.get("email"));
//                e.setLocation(csvRecord.get("location"));
//                e.setDepartment(csvRecord.get("department"));
//                e.setAge(Integer.parseInt(csvRecord.get("age")));
//                list.add(e);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: ");
        }
    }

}
