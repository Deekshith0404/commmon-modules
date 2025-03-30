package com.xworkz.modules.repository;

import com.xworkz.modules.dto.RegFormDto;
import com.xworkz.modules.entity.RegFormEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDateTime;

@Repository
public class RegformRepoImpl implements RegformRepo {
    private static final Logger log = LoggerFactory.getLogger(RegformRepoImpl.class);
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public boolean save(RegFormEntity regFormEntity) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            if (regFormEntity!=null){
                entityManager.getTransaction().begin();
                entityManager.persist(regFormEntity);
                entityManager.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public RegFormEntity getProfileByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        RegFormEntity regFormEntity = new RegFormEntity();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("getUserByemail");
            query.setParameter("email",email);
            regFormEntity=(RegFormEntity) query.getSingleResult();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
        return regFormEntity;
    }

    @Override
    public RegFormEntity login(String email) {
        EntityManager entityManager = null;
        RegFormEntity regFormEntity=new RegFormEntity();
        Query query=null;
        try {
            entityManager=entityManagerFactory.createEntityManager();
            try {
                query = entityManager.createNamedQuery("getUserByemail", RegFormEntity.class);

                    query.setParameter("email",email);
                regFormEntity=(RegFormEntity) query.getSingleResult();
            } catch (Exception e) {
                log.info(e.getMessage());
                return null;
            }


            if (regFormEntity!=null){
                return regFormEntity;
            }else {
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public boolean setpassword(String email,String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("setpassword");
            query.setParameter("email", email);
            query.setParameter("password", password);
            int update=query.executeUpdate();
            entityManager.getTransaction().commit();
            if (update==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean logincount(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("logincountincrement");
            query.setParameter("email", email);
            int update=query.executeUpdate();
            entityManager.getTransaction().commit();
            if (update==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
    }


    @Override
    public boolean loginrest(String email) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("loginreset");
            query.setParameter("email", email);
            int update=query.executeUpdate();
            entityManager.getTransaction().commit();
            if (update==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }

    }

    @Override
    public boolean timelock(String email) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("timelock");
            query.setParameter("email", email);
            query.setParameter("time", LocalDateTime.now());
            int update=query.executeUpdate();
            entityManager.getTransaction().commit();
            if (update==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }

    }

    @Override
    public Long emailcount(String email) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("countEmailOccurrences");
            query.setParameter("email", email);
            Long count=(Long) query.getSingleResult();
            entityManager.getTransaction().commit();
            System.out.println("Repo :"+count);
            return count;

        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return -1l;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }

    }

    @Override
    public boolean deleteByEmail(String email) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("deletebyemail");
            query.setParameter("email", email);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
    }
    }

    @Override
    public Long loginidcount(String loginId) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("idcount");
            query.setParameter("loginId", loginId);
            Long count=(Long) query.getSingleResult();
            entityManager.getTransaction().commit();
            return count;

        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return -1l;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public boolean updateform(RegFormEntity regFormEntity) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
             RegFormEntity ent=entityManager.find(RegFormEntity.class,regFormEntity.getId());
            if (ent!=null){
                entityManager.getTransaction().begin();
                entityManager.merge(ent);
                entityManager.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception e){
            log.error(e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }
}

