package com.lms.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface Dao <T> {
	
	
	public abstract void update(T t) throws SQLException, IOException;
	
	public abstract void delete(T t) throws SQLException, IOException;
	
	public abstract T get(int id) throws SQLException, IOException;
	
	public abstract List<T> getAll() throws SQLException, IOException;

}
