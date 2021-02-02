package cff.cashflowforecast.entity;

import java.util.UUID;
import javax.persistence.*;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CFUser {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private UUID userId;
	@NotNull
	@Column(length = 20, name = "user_name")
	private String userName;
	@Column(name = "mail_id")
	private String email;
	/*
	 * one role can be multiple users
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private UserRole roles;

}
