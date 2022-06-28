package com.sbi.statement.layer5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import com.sbi.statement.layer2.Accounts;
import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer2.User;
import com.sbi.statement.layer4.AccountsNotFoundException;
import com.sbi.statement.layer4.IncorrectPasswordException;
import com.sbi.statement.layer4.StatementPDFView;
import com.sbi.statement.layer4.StatementService;
import com.sbi.statement.layer4.TransactionsNotFoundException;

@CrossOrigin
@RestController
public class StatementController {

	@Autowired
	StatementService stmtServ;
	
	@Autowired
	StatementPDFView pdfView;

	
	@GetMapping("/fetch/custom/{email}/{start_date}/{end_date}")
	public ResponseEntity<List<Transactions>> fetchCustomStatement(@PathVariable(name = "email") String email,
			@PathVariable(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@PathVariable(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) 
					throws AccountsNotFoundException , TransactionsNotFoundException
	{
		ResponseEntity responseRef = null;
		try
		{
			List<Transactions> transList= stmtServ.getCustomStatementEmail(fromDate, toDate, email);
			responseRef = ResponseEntity.ok(transList);
		}
		catch(AccountsNotFoundException e)
		{	
			responseRef =  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		catch(TransactionsNotFoundException e)
		{
			responseRef =  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return responseRef;
	}
	
//	@RequestMapping(value = "/pdfreport/{email}/{start_date}/{end_date}", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
//	public ResponseEntity<InputStreamResource> transactionListController(@PathVariable(name = "email") String email,
//			@PathVariable(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
//			@PathVariable(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) throws DocumentException
//	{
//		ByteArrayInputStream bis = pdfView.transactionList(email,fromDate,toDate);
//		var myHttp = new HttpHeaders();
//        myHttp.add("Content-Disposition", "inline; filename=statement.pdf");
//		
//        return ResponseEntity
//                .ok()
//                .headers(myHttp)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//		
//	}
	
	@RequestMapping(value = "/pdfreport/{email}/{start_date}/{end_date}")//,produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> transactionListController(@PathVariable(name = "email") String email,
			@PathVariable(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@PathVariable(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) throws DocumentException
	{
		ResponseEntity<byte[]> respEnt = null;
		try {
		String byteArr = pdfView.transactionList(email,fromDate,toDate);
		Path filePath = Paths.get(byteArr);
		byte[] fileContent;
		fileContent = Files.readAllBytes(filePath);
		HttpHeaders myHttp = new HttpHeaders();
		myHttp.setContentType(MediaType.APPLICATION_PDF);
		String fileName = "Account-Statement.pdf";
		myHttp.setContentDispositionFormData(fileName, fileName);
		myHttp.setCacheControl("must-revalidate,post-check=0,pre-check = 0");
        respEnt = new ResponseEntity<byte[]>(fileContent,myHttp,HttpStatus.OK);
		
       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return respEnt;
                
	}
	
	
//	@PostMapping("/login")
//	public ResponseEntity<List<Transactions>> checkLogin(@RequestBody User user) 
//			throws AccountsNotFoundException, IncorrectPasswordException, TransactionsNotFoundException  
//	{
//		ResponseEntity responseRef=null;
//		try
//		{	
//			List<Transactions> transList=stmtServ.checkLoginService(user);
//			
//			responseRef = ResponseEntity.ok(transList);
//		}
//		catch(AccountsNotFoundException e)
//		{	
//			responseRef =  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//		catch(IncorrectPasswordException e)
//		{
//			responseRef =  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//		catch(TransactionsNotFoundException e)
//		{
//			responseRef =  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//		return responseRef;
//	}
	
	@PostMapping("/login")
	public ResponseEntity<Accounts> checkLogin(@RequestBody User user) 
			throws AccountsNotFoundException, IncorrectPasswordException, TransactionsNotFoundException  
	{
		ResponseEntity responseRef=null;
		try
		{	
			Accounts accts=stmtServ.checkLoginService(user);
			
			responseRef = ResponseEntity.ok(accts);
		}
		catch(AccountsNotFoundException e)
		{	
			responseRef =  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch(IncorrectPasswordException e)
		{
			responseRef =  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(TransactionsNotFoundException e)
		{
			responseRef =  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return responseRef;
	}
	

	

}
