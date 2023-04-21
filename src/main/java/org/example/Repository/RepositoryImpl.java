package org.example.Repository;

import com.sun.istack.NotNull;
import org.example.database.DataBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl<T,ID> implements Repository<T,ID> {

    protected final Class<T> domainType;
    protected final SessionFactory sf = DataBase.getSessionFactory();

    public RepositoryImpl(Class<T> domainType) {
        this.domainType = domainType;
    }

    public Optional<T> findById(ID id) {
        Session se = getSession();
        return Optional.ofNullable(se.find(domainType, id));
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public T save(T entity) {
        Session se = getSessionWithBegin();
        se.save(entity);
        sessionCommitAndClose(se);
        return entity;
    }

    @Override
    public List<T> findAll() {
        Session se = getSession();
        CriteriaBuilder criteriaBuilder = se.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(domainType);
        Root<T> root = criteriaQuery.from(domainType);
        criteriaQuery.select(root);
        List<T> resultList = se.createQuery(criteriaQuery).getResultList();
        se.close();
        return resultList;
    }

    protected List<T> runCustomQuery(String hql, HashMap<String,Object> para){
        return queryGenerator(hql, para).getResultList();
    }

    private Query queryGenerator(@NotNull String hql, HashMap<String,Object> para){
        Session se = getSession();
        Query query = se.createQuery(hql);
        for (String key : para.keySet()) {
            Object value = para.get(key);
            query.setParameter(key, value);
        }
        return query;
    }

    private void delete(T entity) {
        Session se = getSessionWithBegin();
        se.delete(entity);
        sessionCommitAndClose(se);
    }

    protected Session getSession(){
        return this.sf.openSession();
    }

    protected Session getSessionWithBegin(){
        Session se = this.sf.openSession();
        se.beginTransaction();
        return se;
    }
    protected void sessionCommitAndClose(Session se){
        Transaction ts = se.getTransaction();
        ts.commit();
        se.close();
    }
}
