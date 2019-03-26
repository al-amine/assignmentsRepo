package dao;

import java.io.IOException;
import java.util.List;



public interface Dao<T> {
	
	
	public abstract void create(T t) throws IOException;
	
	public abstract T delete(int id) throws IOException;
	
	public abstract void update(T t) throws IOException;
	
	public abstract T find(int id) throws IOException;
	
	public abstract List<T> findAll() throws IOException;

	
	
}
