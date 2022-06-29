package com.sbi.statement.layer4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer3.AccountsRepository;
@Component
public class StatementPDFView {
	
	@Autowired
	AccountsRepository acctRepo;
	
	@Autowired
	StatementService stmtServ;
	
	String USER_PASSWORD = "Password";
	String OWNER_PASSWORD = "vijayPass";
	
	Document doc= null;
	PdfWriter writer = null;

	
	public String transactionList(String email, LocalDate fromDate, LocalDate toDate) throws DocumentException {
		
		try {
			
			Document doc= new Document();
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\Pdfs\\Statement-1111.pdf"));
			
			writer.setEncryption(USER_PASSWORD.getBytes(),
	                OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING|PdfWriter.ALLOW_COPY,
	                PdfWriter.ENCRYPTION_AES_128);
			
			Accounts a = new Accounts();
			a.setEmail(email);
			a=acctRepo.findByEmail(email);
			List<Transactions> transList = a.getTrans();
			
			double currBal = a.getCurrentBalance();
			
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);
            
            Font contentFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 11);
            
            Paragraph para=new Paragraph(new Phrase("Statement",titleFont));
			
			Paragraph empty=new Paragraph(new Phrase("       ",contentFont));
			
			Paragraph accHolderName=new Paragraph(new Phrase(a.getAccountHolderName(),contentFont));
			Paragraph accHolderAddress=new Paragraph(new Phrase("Address :\t"+a.getAccountHolderAddress(),contentFont));
			Paragraph accNumber=new Paragraph(new Phrase("Account Number :\t"+a.getAccountNumber(),contentFont));
			Paragraph tranFromDate=new Paragraph(new Phrase("From Date :\t"+String.valueOf(fromDate),contentFont));
			Paragraph tranToDate=new Paragraph(new Phrase("To Date :\t"+String.valueOf(toDate),contentFont));
			Paragraph email1=new Paragraph(new Phrase("Email :\t"+a.getEmail(),contentFont));
			Paragraph currBalance=new Paragraph(new Phrase("Current Balance :\t"+String.valueOf(currBal),contentFont));
			
            int chunkSize = 34;

            List<List<Transactions>> partitionedList = ListUtils.partition(transList, chunkSize);

            doc.open();
            
            for (List<Transactions> tl: partitionedList)
            {
						
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(95);
            table.setWidths(new int[]{1, 1, 1,1,1,1});
            table.setHorizontalAlignment(0);
            table.setSpacingBefore(6f);
            table.setSpacingAfter(6f);
            table.setHeaderRows(1);
           
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Transaction Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBorderColor(BaseColor.BLUE);
            hcell.setBackgroundColor(new BaseColor(52,167,244));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Payment Type", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBorderColor(BaseColor.BLUE);
            hcell.setBackgroundColor(new BaseColor(52,167,244));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Sent To", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBorderColor(BaseColor.BLUE);
            hcell.setBackgroundColor(new BaseColor(52,167,244));
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Withdrawal (in INR)", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBorderColor(BaseColor.BLUE);
            hcell.setBackgroundColor(new BaseColor(52,167,244));
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Deposit (in INR)", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBorderColor(BaseColor.BLUE);
            hcell.setBackgroundColor(new BaseColor(52,167,244));
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Balance (in INR)", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBorderColor(BaseColor.BLUE);
            hcell.setBackgroundColor(new BaseColor(52,167,244));
            table.addCell(hcell);
                       
            for (Transactions t : tl) 
            	                
            	{          	

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(t.getTransTime().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.BLUE);
                table.addCell(cell);
                
                String transType = String.valueOf(t.getTransType());
                if (transType.equals("C"))
                {
                	transType = "Credit";
                }
                else
                {
                	transType = "Debit";
                }

                cell = new PdfPCell(new Phrase(transType));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.BLUE);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(t.getRefAccount()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.BLUE);
                table.addCell(cell);

                String transType1 = String.valueOf(t.getTransType());
                String debit = null;
                String credit = null;
                if(transType1.equals("D"))
                {
                	debit = String.valueOf(t.getTransAmount());
                	credit = "-";
                
                }
                else
                {
                	debit = "-";
                	credit = String.valueOf(t.getTransAmount());
                }
                cell = new PdfPCell(new Phrase(debit));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.BLUE);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(credit));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.BLUE);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(t.getRemainingBalance())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.BLUE);
                cell.setBorderWidthRight(1);
                table.addCell(cell); 
                currBal = t.getRemainingBalance();      
            }
			
			
			
			para.setAlignment(Element.ALIGN_CENTER);
			doc.add(para);
			doc.add(empty);
			doc.add(empty);
			doc.add(accHolderName);
			doc.add(accHolderAddress);
			doc.add(accNumber);
			doc.add(tranFromDate);
			doc.add(tranToDate);
			doc.add(email1);
			doc.add(currBalance);
			doc.add(empty);
			doc.add(table);
			
            }
            
            doc.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "D:\\Pdfs\\Statement-1111.pdf";
		
			
		
	}

}

