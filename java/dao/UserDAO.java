package dao;

import model.User;

public interface UserDAO extends GeneralDAO<User> {
	
	User selectByEmail(String email) throws Exception;
	
	User login(String email, String password) throws Exception;
}
