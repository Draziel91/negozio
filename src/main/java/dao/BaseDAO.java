package dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface BaseDAO <T, ID>{

    T findById(ID id);

    T findById(ID id, Connection conn);

    ArrayList<T> findAll();

    int insert(T entity);
}
