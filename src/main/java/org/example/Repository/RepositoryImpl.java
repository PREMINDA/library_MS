package org.example.Repository;

import org.example.database.DataBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    private void delete(T entity) {
        Session se = getSessionWithBegin();
        se.delete(entity);
        sessionCommitAndClose(se);
    }

    private Session getSession(){
        return this.sf.openSession();
    }

    private Session getSessionWithBegin(){
        Session se = this.sf.openSession();
        se.beginTransaction();
        return se;
    }
    private void sessionCommitAndClose(Session se){
        Transaction ts = se.getTransaction();
        ts.commit();
        se.close();
    }
}
