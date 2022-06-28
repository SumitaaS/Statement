package com.sbi.statement.layer4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

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

	
	public ByteArrayInputStream transactionList(String email, LocalDate fromDate, LocalDate toDate) throws DocumentException {

		Document doc= new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Accounts a = new Accounts();
		a.setEmail(email);
		a=acctRepo.findByEmail(email);
		List<Transactions> tl = a.getTrans();
		
		try {
			
			PdfWriter.getInstance(doc, out);
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 1, 1,1,1,1});
            table.setHorizontalAlignment(0);
            
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            Font paraFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 20);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Transaction Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Payment Type", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Particulars", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Withdrawal (in INR)", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Deposit (in INR)", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Balance (in INR)", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            for (Transactions t : tl) 
            	                
            	{          	

                PdfPCell cell;
                
                

                cell = new PdfPCell(new Phrase(t.getTransTime().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                
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
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(20);
                cell.setBorderColorBottom(BaseColor.WHITE);
                cell.setBorderWidthBottom(20);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase(t.getRefAccount()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(20);
                cell.setBorderColorBottom(BaseColor.WHITE);
                cell.setBorderWidthBottom(20);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(t.getTransAmount())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                cell.setBorder(20);
                cell.setBorderColorBottom(BaseColor.WHITE);
                cell.setBorderWidthBottom(20);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                
                cell = new PdfPCell(new Phrase(String.valueOf(t.getTransAmount())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidthBottom(20);
                cell.setBorderColorBottom(BaseColor.WHITE);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(t.getRemainingBalance())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                cell.setBorder(20);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidthBottom(20);
                cell.setBorderColorBottom(BaseColor.WHITE);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);    
            }
			doc.open();
			Paragraph para=new Paragraph(new Phrase("Statement",paraFont));
			para.setAlignment(Element.ALIGN_CENTER);
			doc.add(para);
			doc.add(table);
			doc.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return new ByteArrayInputStream(out.toByteArray());
		
			
		
	}

}
