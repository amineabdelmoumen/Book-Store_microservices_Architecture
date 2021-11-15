package inpt.ac.billingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import inpt.ac.billingservice.entities.Bill;

public interface BillRepo extends JpaRepository<Bill, Long> {

    @Query("select b from Bill b where b.id= :x")
    public Bill findByid(@Param("x") Long id);

}
