package com.example.Student_Library_Management.Services;

import com.example.Student_Library_Management.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management.Enums.CardStatus;
import com.example.Student_Library_Management.Enums.TransactionStatus;
import com.example.Student_Library_Management.Models.Book;
import com.example.Student_Library_Management.Models.Card;
import com.example.Student_Library_Management.Models.Transactions;
import com.example.Student_Library_Management.Repositories.BookRepostory;
import com.example.Student_Library_Management.Repositories.CardRepository;
import com.example.Student_Library_Management.Repositories.TransactionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRespository transactionRespository;

    @Autowired
    BookRepostory bookRepostory;

    @Autowired
    CardRepository cardRepository;

    public String issuedBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        int bookId=issueBookRequestDto.getBookId();
        int cardId=issueBookRequestDto.getCardId();

        Book book =bookRepostory.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();


        Transactions transactions=new Transactions();
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        if(book==null || book.isIssued()==true){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRespository.save(transactions);
            throw new Exception("Book is not available");
        }

        if(card==null || card.getCardStatus()!= CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRespository.save(transactions);
            throw new Exception("Card is not activated");
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIssued(true);

        List<Transactions> listOfTransaction=book.getListOfTransactions();
        listOfTransaction.add(transactions);
        book.setListOfTransactions(listOfTransaction);

        List<Book> issuedBooksForCard= card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);


        List<Transactions> transactionListForCard=card.getTransactionsList();
        transactionListForCard.add(transactions);
        card.setTransactionsList(transactionListForCard);

        cardRepository.save(card);
        return "Book Issued Successfully";


    }

    public String getTransactions(int bookId,int cardId){
        List<Transactions> transactionsList=transactionRespository.getTransactionsForBookAndCard(bookId,cardId);
        String transactionId=transactionsList.get(0).getTransactionId();
        return transactionId;
    }






}
