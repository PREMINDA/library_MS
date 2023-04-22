package org.example.Repository;
import com.sun.istack.NotNull;
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
import java.util.logging.Logger;

public class RepositoryImpl<T,ID> implements Repository<T,ID> {

    protected final Class<T> domainType;
    private static final Logger logger = Logger.getLogger(RepositoryImpl.class.getName());
    protected final SessionFactory sf ;

    public RepositoryImpl(Class<T> domainType, SessionFactory sf) {
        this.domainType = domainType;
        this.sf = sf;
    }


    public Optional<T> findById(ID id) {
        try {
            Session se = getSession();
            return Optional.ofNullable(se.find(domainType, id));
        }catch (Exception e){
            logger.severe("Failed to findByID. " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public T save(T entity) {
        try{
            Session se = getSessionWithBegin();
            se.save(entity);
            sessionCommitAndClose(se);
            return entity;
        }catch (Exception e){
            logger.severe("Failed to save. " + e.getMessage());
        }
        return null;
    }

    @Override
    public T update(T entity) {
        try{
            Session se = getSessionWithBegin();
            se.update(entity);
            sessionCommitAndClose(se);
            return entity;
        }catch (Exception e){
            logger.severe("Failed to update. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        try{
            Session se = getSession();
            CriteriaBuilder criteriaBuilder = se.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(domainType);
            Root<T> root = criteriaQuery.from(domainType);
            criteriaQuery.select(root);
            List<T> resultList = se.createQuery(criteriaQuery).getResultList();
            se.close();
            return resultList;
        }catch (Exception e){
            logger.severe("Failed get all. " + e.getMessage());
        }
        return null;
    }

    protected List<T> runCustomQuery(String hql, HashMap<String,Object> para){
        Query result = queryGenerator(hql, para);
        if(result != null) return result.getResultList();
        return null;
    }

    private Query queryGenerator(@NotNull String hql, HashMap<String,Object> para){

        try{
            Session se = getSession();
            Query query = se.createQuery(hql);
            for (String key : para.keySet()) {
                Object value = para.get(key);
                query.setParameter(key, value);
            }
            return query;
        }catch (Exception e){
            logger.severe("Failed run custom query. " + e.getMessage());
        }
        return null;
    }

    private void delete(T entity) {
        try {
            Session se = getSessionWithBegin();
            se.delete(entity);
            sessionCommitAndClose(se);
        }catch (Exception e){
            logger.severe("Failed delete. " + e.getMessage());
        }
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
