package dao;

import java.util.List;

public interface GeneralDAO<T> {

	void insert(T entity) throws Exception;

	void update(T entity) throws Exception;

	List<T> selectAll() throws Exception;

	T selectById(long id) throws Exception;

	void delete(T entity) throws Exception;

}
