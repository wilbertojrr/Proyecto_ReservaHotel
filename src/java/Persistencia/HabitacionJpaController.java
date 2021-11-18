/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Habitacion;
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
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("tpofinalPU");
    }
    public void create(Habitacion habitacion) {
        if (habitacion.getListRes() == null) {
            habitacion.setListRes(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListRes = new ArrayList<Reserva>();
            for (Reserva listResReservaToAttach : habitacion.getListRes()) {
                listResReservaToAttach = em.getReference(listResReservaToAttach.getClass(), listResReservaToAttach.getId_reserva());
                attachedListRes.add(listResReservaToAttach);
            }
            habitacion.setListRes(attachedListRes);
            em.persist(habitacion);
            for (Reserva listResReserva : habitacion.getListRes()) {
                Habitacion oldHabiOfListResReserva = listResReserva.getHabi();
                listResReserva.setHabi(habitacion);
                listResReserva = em.merge(listResReserva);
                if (oldHabiOfListResReserva != null) {
                    oldHabiOfListResReserva.getListRes().remove(listResReserva);
                    oldHabiOfListResReserva = em.merge(oldHabiOfListResReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getId_habitacion());
            List<Reserva> listResOld = persistentHabitacion.getListRes();
            List<Reserva> listResNew = habitacion.getListRes();
            List<Reserva> attachedListResNew = new ArrayList<Reserva>();
            for (Reserva listResNewReservaToAttach : listResNew) {
                listResNewReservaToAttach = em.getReference(listResNewReservaToAttach.getClass(), listResNewReservaToAttach.getId_reserva());
                attachedListResNew.add(listResNewReservaToAttach);
            }
            listResNew = attachedListResNew;
            habitacion.setListRes(listResNew);
            habitacion = em.merge(habitacion);
            for (Reserva listResOldReserva : listResOld) {
                if (!listResNew.contains(listResOldReserva)) {
                    listResOldReserva.setHabi(null);
                    listResOldReserva = em.merge(listResOldReserva);
                }
            }
            for (Reserva listResNewReserva : listResNew) {
                if (!listResOld.contains(listResNewReserva)) {
                    Habitacion oldHabiOfListResNewReserva = listResNewReserva.getHabi();
                    listResNewReserva.setHabi(habitacion);
                    listResNewReserva = em.merge(listResNewReserva);
                    if (oldHabiOfListResNewReserva != null && !oldHabiOfListResNewReserva.equals(habitacion)) {
                        oldHabiOfListResNewReserva.getListRes().remove(listResNewReserva);
                        oldHabiOfListResNewReserva = em.merge(oldHabiOfListResNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = habitacion.getId_habitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
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
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId_habitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listRes = habitacion.getListRes();
            for (Reserva listResReserva : listRes) {
                listResReserva.setHabi(null);
                listResReserva = em.merge(listResReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
