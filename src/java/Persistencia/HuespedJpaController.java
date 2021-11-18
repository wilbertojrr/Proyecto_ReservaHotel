/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Huesped;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wilbe
 */
public class HuespedJpaController implements Serializable {

    public HuespedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public HuespedJpaController() {
        emf = Persistence.createEntityManagerFactory("tpofinalPU");
    }
    public void create(Huesped huesped) {
        if (huesped.getListRes() == null) {
            huesped.setListRes(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListRes = new ArrayList<Reserva>();
            for (Reserva listResReservaToAttach : huesped.getListRes()) {
                listResReservaToAttach = em.getReference(listResReservaToAttach.getClass(), listResReservaToAttach.getId_reserva());
                attachedListRes.add(listResReservaToAttach);
            }
            huesped.setListRes(attachedListRes);
            em.persist(huesped);
            for (Reserva listResReserva : huesped.getListRes()) {
                Huesped oldHuesOfListResReserva = listResReserva.getHues();
                listResReserva.setHues(huesped);
                listResReserva = em.merge(listResReserva);
                if (oldHuesOfListResReserva != null) {
                    oldHuesOfListResReserva.getListRes().remove(listResReserva);
                    oldHuesOfListResReserva = em.merge(oldHuesOfListResReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huesped huesped) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped persistentHuesped = em.find(Huesped.class, huesped.getId_persona());
            List<Reserva> listResOld = persistentHuesped.getListRes();
            List<Reserva> listResNew = huesped.getListRes();
            List<Reserva> attachedListResNew = new ArrayList<Reserva>();
            for (Reserva listResNewReservaToAttach : listResNew) {
                listResNewReservaToAttach = em.getReference(listResNewReservaToAttach.getClass(), listResNewReservaToAttach.getId_reserva());
                attachedListResNew.add(listResNewReservaToAttach);
            }
            listResNew = attachedListResNew;
            huesped.setListRes(listResNew);
            huesped = em.merge(huesped);
            for (Reserva listResOldReserva : listResOld) {
                if (!listResNew.contains(listResOldReserva)) {
                    listResOldReserva.setHues(null);
                    listResOldReserva = em.merge(listResOldReserva);
                }
            }
            for (Reserva listResNewReserva : listResNew) {
                if (!listResOld.contains(listResNewReserva)) {
                    Huesped oldHuesOfListResNewReserva = listResNewReserva.getHues();
                    listResNewReserva.setHues(huesped);
                    listResNewReserva = em.merge(listResNewReserva);
                    if (oldHuesOfListResNewReserva != null && !oldHuesOfListResNewReserva.equals(huesped)) {
                        oldHuesOfListResNewReserva.getListRes().remove(listResNewReserva);
                        oldHuesOfListResNewReserva = em.merge(oldHuesOfListResNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = huesped.getId_persona();
                if (findHuesped(id) == null) {
                    throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped huesped;
            try {
                huesped = em.getReference(Huesped.class, id);
                huesped.getId_persona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listRes = huesped.getListRes();
            for (Reserva listResReserva : listRes) {
                listResReserva.setHues(null);
                listResReserva = em.merge(listResReserva);
            }
            em.remove(huesped);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huesped> findHuespedEntities() {
        return findHuespedEntities(true, -1, -1);
    }

    public List<Huesped> findHuespedEntities(int maxResults, int firstResult) {
        return findHuespedEntities(false, maxResults, firstResult);
    }

    private List<Huesped> findHuespedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huesped.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Huesped findHuesped(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huesped.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuespedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huesped> rt = cq.from(Huesped.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
