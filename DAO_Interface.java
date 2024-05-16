package CSDL;

import java.util.ArrayList;

public interface DAO_Interface<T> {	
	public int insert(T t);
	public int delete(T t);
	public ArrayList<T> selectAll();
	public boolean nameExists(String name);
	public People findByName(String name);
	public int update(T t);
	public void deletePlayersWithZeroScore();
}
