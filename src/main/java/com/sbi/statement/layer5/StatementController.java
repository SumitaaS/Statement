package com.sbi.statement.layer5;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.statement.layer2.Transactions;
import com.sbi.statement.layer4.StatementService;

@RestController
public class StatementController {

	@Autowired
	StatementService stmtServ;

	@GetMapping("/fetch/monthly")
	public List<Transactions> fetchCustomStatement(@RequestParam(name = "acc_no") int accNo,
			@RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		return stmtServ.getCustomStatement(fromDate, toDate, accNo);
	}

}