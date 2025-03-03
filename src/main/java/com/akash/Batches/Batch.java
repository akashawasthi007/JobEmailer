package com.akash.Batches;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.akash.interfaces.MailManager;

@Component
public class Batch implements MailManager {

    
        public String salutation = "<p><span>Hi " ;
        public String content = "</p></span>\r\n <div dir=\"auto\">&nbsp;</div>\r\n" + //
                    "<div dir=\"auto\">I am writing to express my interest in potential job opportunities at your esteemed organization. I have 3 years of experience in building robust backend systems, I bring a strong proficiency in Core Java, Springboot, Struts, Spring JDBC, REST APIs, and microservices architecture.</div>\r\n" + //
                    "<div dir=\"auto\">&nbsp;</div>\r\n" + //
                    "<div dir=\"auto\">For your reference, I have attached my resume to provide further details about my background and achievements. Please feel free to reach out to me at your convenience if there is an opening that matches my skill set.</div>\r\n" + //
                    "<div dir=\"auto\">&nbsp;</div>\r\n" + //
                    "<div dir=\"auto\">Thank you for considering my application. I look forward to the opportunity to contribute to your organization.</div>\r\n" + //
                    "<div dir=\"auto\">&nbsp;</div>\r\n" + //
                    "<div dir=\"auto\">Best regards,</div>\r\n" + //
                    "<div dir=\"auto\">Akash Awasthi</div>\r\n" + //
                    "<div dir=\"auto\">Contact: 7895685215</div>\r\n" + //
                    "<div dir=\"auto\">Email:&nbsp;<a href=\"mailto:akashawasthi076@gmail.com\" rel=\"noreferrer\" target=\"_blank\">akashawasthi076@gmail.com</a></div>" ;
    

    @Autowired
    private JavaMailSender emailSender;
    
    public String singleMail() throws IOException, MessagingException {

        HashMap<String,String> hMap = getAllHRs(); 

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Applying for the role of Java Developer");
        FileSystemResource file = new FileSystemResource("D:\\doc\\Akash_Awasthi_Resume_Updated.pdf");
        helper.addAttachment("Akash_Awasthi_Resume", file, "application/pdf");

        for(Map.Entry<String, String> entry : hMap.entrySet()){
            //System.out.println(entry.getKey() + " " +entry.getValue());
            String htmlContent = salutation + entry.getValue().toString().strip().split("[\\s]")[0] + content;
            helper.setTo(entry.getKey().toString());
            helper.setText("plain text",htmlContent);
            emailSender.send(message);
            System.out.println("mail sent to :"+entry.getKey());

            
        }

        // String str[] = {"akashawasthi076@gmail.com"};
        // for(int i = 0 ; i<1 ;i++)
        // {
        //     System.out.println("i :"+i);
        //     helper.setTo(str[i]);
        //     helper.setText("plain text",salutation+"oye"+content);
        
        //     emailSender.send(message);
        //     System.out.println("i :"+i);
        // }

        return hMap.toString();
    }

    @SuppressWarnings("null")
    @Override
    public HashMap<String, String> getAllHRs() throws IOException {
        System.out.println("Enter getAllHRs()");
        String path = "D:\\doc\\HRContacts.xlsx";
        //String path = "D:\\doc\\testiing.xlsx";
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

