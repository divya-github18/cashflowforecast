package cff.cashflowforecast.interfaces;

import java.util.List;
import java.util.UUID;

import cff.cashflowforecast.entity.CFUser;

public interface Icfuser {
	public List<CFUser> getAllUsers();
	public CFUser createCfUser(CFUser cfuser);
	public CFUser getUserById( UUID userId);
	public void deleteUser(UUID userId);
	public void updateUser(UUID userId,CFUser userDetails);

}
