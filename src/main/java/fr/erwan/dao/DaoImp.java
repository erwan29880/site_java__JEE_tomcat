package fr.erwan.dao;

import java.util.List;

public interface DaoImp<T> {
	List<T> select();	
	void insert(T utilisateur);
	void update(T utilisateur);
	void delete(T utilisateur);
}
