package cff.cashflowforecast.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cff.cashflowforecast.entity.AccountDetail;



@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetail, UUID> {

}
