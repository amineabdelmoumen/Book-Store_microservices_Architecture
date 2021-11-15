package inpt.ac.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private Date billingdate;
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private Collection<BookItem> booktItems;
    private Long customerID;
    @Transient
    private Customer customer;

    public Bill() {
    }

    public Bill(Long id, Date billingdate, Collection<BookItem> booktItems, Long customerID, Customer customer) {
        this.id = id;
        this.billingdate = billingdate;
        this.booktItems = booktItems;
        this.customerID = customerID;
        this.customer = customer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillingdate() {
        return this.billingdate;
    }

    public void setBillingdate(Date billingdate) {
        this.billingdate = billingdate;
    }

    public Collection<BookItem> getBooktItems() {
        return this.booktItems;
    }

    public void setBooktItems(Collection<BookItem> booktItems) {
        this.booktItems = booktItems;
    }

    public Long getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bill id(Long id) {
        setId(id);
        return this;
    }

    public Bill billingdate(Date billingdate) {
        setBillingdate(billingdate);
        return this;
    }

    public Bill booktItems(Collection<BookItem> booktItems) {
        setBooktItems(booktItems);
        return this;
    }

    public Bill customerID(Long customerID) {
        setCustomerID(customerID);
        return this;
    }

    public Bill customer(Customer customer) {
        setCustomer(customer);
        return this;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", billingdate='" + getBillingdate() + "'" + ", booktItems='"
                + getBooktItems() + "'" + ", customerID='" + getCustomerID() + "'" + ", customer='" + getCustomer()
                + "'" + "}";
    }

}
