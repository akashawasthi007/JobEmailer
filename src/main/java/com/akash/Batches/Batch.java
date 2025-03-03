package com.akash.Batches;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.akash.interfaces.MailManager;

@Component
public class Batch implements MailManager {

    private MailSender mailSender;

    private SimpleMailMessage simpleMailMessage;



    public String singleMail() throws IOException {

        HashMap<String,String> hMap = getAllHRs(); 

        for(Map.Entry<String, String> entry : hMap.entrySet()){
            System.out.println(entry.getKey() + " " +entry.getValue());
            
            

        }

        return hMap.toString();
    }







    @SuppressWarnings("null")
    @Override
    public HashMap<String, String> getAllHRs() throws IOException {
        System.out.println("Enter getAllHRs()");
        String path = "D:\\doc\\HRContacts.xlsx";
        HashMap<String,String> mp = new HashMap<>();
         XSSFWorkbook workbook = null;
        try{
        
            FileInputStream file = new FileInputStream(new File(path));
            System.out.println(file.getClass());
            workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet)
            {
                mp.put(row.getCell(2).toString(), row.getCell(1).toString());
            }
            System.out.println("workbook number of rows "+sheet.getLastRowNum());
            
    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            workbook.close();
        }
        System.out.println("Exit getAllHRs()");
        return mp;
        //throw new UnsupportedOperationException("Unimplemented method 'getAllHRs'");
    }

}
