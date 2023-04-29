package org.example.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class RepositoryImpl<T,ID> implements Repository<T,ID> {
    protected final Class<T> domainType;
    private static final Logger logger = Logger.getLogger(RepositoryImpl.class.getName());
    protected final SessionFactory sf ;

    public RepositoryImpl( Class<T> domainType, SessionFactory sf) {
        this.domainType = domainType;
        this.sf = sf;
    }

    public Optional<T> findById(ID id) {
        try (Session se = getSession()) {
            return Optional.ofNullable(se.find(domainType, id));
        } catch (Exception e) {
            logger.severe("Failed to findByID. " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(ID id) {
        try (Session se = getSession()){
            se.beginTransaction();
            Object entity = se.load(domainType, (Serializable) id);
            se.delete(entity);
            se.getTransaction().commit();
            se.close();
        }catch (Exception e){
            logger.severe("Failed delete. " + e.getMessage());
            rollbackCurrent();
        }
    }

    @Override
    public <S extends T> S save(S entity) {
        try(Session se = getSession()){
            se.beginTransaction();
            se.save(entity);
            se.getTransaction().commit();
            se.close();
            return entity;
        }catch (Exception e){
            logger.severe("Failed to save. " + e.getMessage());
            rollbackCurrent();
        }
        return null;
    }

    @Override
    public void update(T entity) {
        try(Session se = getSession()){
            se.beginTransaction();
            se.update(entity);
            se.getTransaction().commit();
            se.close();
        }catch (Exception e){
            logger.severe("Failed to update. " + e.getMessage());
            rollbackCurrent();
        }
    }

    @Override
    public List<T> getAll() {

        try(Session se = getSession();){
            CriteriaBuilder criteriaBuilder = se.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(domainType);
            Root<T> root = criteriaQuery.from(domainType);
            criteriaQuery.select(root);
            List<T> resultList = se.createQuery(criteriaQuery).getResultList();
            se.close();
            return resultList;
        }catch (Exception e){
            logger.severe("Failed to get all book "+e.getMessage());
        }
        return null;
    }

    protected List<T> runCustomQuery(String hql, HashMap<String,Object> para){
        List<T> result = new ArrayList<>();
        try(Session se = getSession()) {
            se.beginTransaction();
            Query query = se.createQuery(hql);
            for (String key : para.keySet()) {
                Object value = para.get(key);
                query.setParameter(key, value);
            }
            result = query.getResultList();
            se.close();
            return result;
        } catch (Exception e) {
            logger.severe("Failed run custom query. " + e.getMessage());
        }
        return null;
    }

    private Session getSession(){
        return this.sf.openSession();
    }

    private Transaction getTransaction(){
        return this.sf.getCurrentSession().getTransaction();
    }

    private void rollbackCurrent(){
        Transaction tx = getTransaction();
        if(tx != null){
            tx.rollback();
        }
    }

    private void sessionClose(){
        Session se = this.sf.getCurrentSession();
        if(se != null){
            se.close();
        }
    }

}
