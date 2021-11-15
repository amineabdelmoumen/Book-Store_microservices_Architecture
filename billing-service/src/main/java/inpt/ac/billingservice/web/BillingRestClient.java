package inpt.ac.billingservice.web;

import java.util.Optional;

import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import inpt.ac.billingservice.entities.Bill;
import inpt.ac.billingservice.entities.Book;
import inpt.ac.billingservice.entities.Customer;
import inpt.ac.billingservice.feignClients.BookRestClient;
import inpt.ac.billingservice.feignClients.CustomerRestClient;
import inpt.ac.billingservice.repositories.BillRepo;
import inpt.ac.billingservice.repositories.BookItemRepo;

@RestController
public class BillingRestClient {
    private BillRepo billrepo;

    public BillingRestClient(BillRepo billrepo, CustomerRestClient customerRestClient, BookItemRepo bookItemRepo,
            BookRestClient bookRestClient) {
        this.billrepo = billrepo;
        this.customerRestClient = customerRestClient;
        this.bookItemRepo = bookItemRepo;
        this.bookRestClient = bookRestClient;
    }

    private CustomerRestClient customerRestClient;
    private BookItemRepo bookItemRepo;

    BookRestClient bookRestClient;

    @GetMapping(path = "/fullbill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billrepo.findByid(id);
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);

        bill.getBooktItems().forEach(bi -> {
            Book b = bookRestClient.getBook(bi.getBookID());
            bi.setBook(b);
        });
        return bill;

    }

}
