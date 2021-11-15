package inpt.ac.billingservice;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.PagedModel;

import inpt.ac.billingservice.entities.Bill;
import inpt.ac.billingservice.entities.Book;
import inpt.ac.billingservice.entities.BookItem;
import inpt.ac.billingservice.entities.Customer;
import inpt.ac.billingservice.feignClients.BookRestClient;
import inpt.ac.billingservice.feignClients.CustomerRestClient;
import inpt.ac.billingservice.repositories.BillRepo;
import inpt.ac.billingservice.repositories.BookItemRepo;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication implements CommandLineRunner {
	@Autowired
	private BillRepo billrepo;
	@Autowired
	private BookItemRepo bookitemrepo;
	@Autowired
	private BookRestClient bookrestclients;

	@Autowired
	private CustomerRestClient customerRestClient;

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = customerRestClient.getCustomerById(1L);
		Bill bill = billrepo.save(new Bill(null, new Date(), null, customer.getId(), null));
		PagedModel<Book> books = bookrestclients.getBooks(0, 5);
		books.forEach(p -> {
			BookItem bookitem = new BookItem();
			bookitem.setPrice(200);
			bookitem.setQuantity(new Random().nextInt(100));
			bookitem.setBill(bill);
			bookitemrepo.save(bookitem);

		}

		);

	}

}
