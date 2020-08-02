package com.gj.repository;

import java.util.List;

import com.gj.exception.RecordDuplicateException;
import com.gj.exception.RecordNotFoundException;

public interface ICommonDao<T> {

	public T findById( long id) throws RecordNotFoundException;
	public List<T> findAll(String query);
	public T delete( long id) throws RecordNotFoundException;
	public List<T> executeQuery(String query);
	public void executeUpdate(String query);
	public T save(T obj) throws RecordDuplicateException;
	public T update(T obj) throws RecordNotFoundException;
	public void batchUpdate(List<T> obj) throws RecordNotFoundException;
}
