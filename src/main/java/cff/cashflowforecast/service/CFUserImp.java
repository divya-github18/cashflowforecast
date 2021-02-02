package cff.cashflowforecast.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.interfaces.Icfuser;
import cff.cashflowforecast.repository.UserRepository;

@Transactional(rollbackFor = Exception.class)
@Service
public class CFUserImp implements Icfuser {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * get all users
	 */
	public List<CFUser> getAllUsers() {
		return (List<CFUser>) userRepository.findAll();
	}

	/**
	 * create user
	 */
	public CFUser createCfUser(CFUser cfuser) {
		return userRepository.save(cfuser);
	}

	/**
	 * get the user based on userid
	 */
	public CFUser getUserById(UUID userId) {
		return userRepository.findById(userId).get();

	}

	/**
	 * delete the user based on userid
	 */
	public void deleteUser(UUID userId) {
		CFUser user = userRepository.findById(userId).orElseThrow();
		userRepository.delete(user);
	}

	/**
	 * update the user based on userid
	 */
	public void updateUser(UUID userId, CFUser userDetails) {
		CFUser user = userRepository.findById(userId).orElseThrow();
		user.setUserName(userDetails.getUserName());
		user.setRoles(userDetails.getRoles());
		user.setEmail(userDetails.getEmail());
		userRepository.save(user);

	}

}
