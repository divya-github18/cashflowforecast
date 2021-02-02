package cff.cashflowforecast.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cff.cashflowforecast.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID >{
}
