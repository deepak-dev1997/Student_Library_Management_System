package com.example.Student_Library_Management.Controllers;

import com.example.Student_Library_Management.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        try{
            return transactionService.issuedBook(issueBookRequestDto);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getTxnInfo")
    public String getTransactionEntry(@RequestParam("bookId")Integer bookId,@RequestParam("cardId") Integer cardId){
        return transactionService.getTransactions(bookId,cardId);
    }

}
