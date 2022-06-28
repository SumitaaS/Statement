package com.sbi.statement.layer5;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer4.StatementPDFView;
import com.sbi.statement.layer4.StatementService;

@CrossOrigin
@RestController
public class StatementController {

	@Autowired
	StatementService stmtServ;
	
	@Autowired
	StatementPDFView pdfView;

	
	@GetMapping("/fetch/custom/{email}/{start_date}/{end_date}")
	public List<Transactions> fetchCustomStatement(@PathVariable(name = "email") String email,
			@PathVariable(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@PathVariable(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		return stmtServ.getCustomStatementEmail(fromDate, toDate, email);
	}
	
	@RequestMapping(value = "/pdfreport/{email}/{start_date}/{end_date}", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> transactionListController(@PathVariable(name = "email") String email,
			@PathVariable(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@PathVariable(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) throws DocumentException
	{
		ByteArrayInputStream bis = pdfView.transactionList(email,fromDate,toDate);
		var myHttp = new HttpHeaders();
        myHttp.add("Content-Disposition", "inline; filename=statement.pdf");
		
        return ResponseEntity
                .ok()
                .headers(myHttp)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
		
	}
	

}

