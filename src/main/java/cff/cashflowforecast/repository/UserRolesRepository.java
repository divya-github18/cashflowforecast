package cff.cashflowforecast.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cff.cashflowforecast.entity.UserRole;
@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, UUID>{
	UserRole findByRole(String role);	
}
