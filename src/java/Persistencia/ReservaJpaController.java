/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Huesped;
import Logica.Empleado;
import Logica.Habitacion;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wilbe
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public ReservaJpaController() {
        emf = Persistence.createEntityManagerFactory("tpofinalPU");
    }
    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped hues = reserva.getHues();
            if (hues != null) {
                hues = em.getReference(hues.getClass(), hues.getId_persona());
                reserva.setHues(hues);
            }
            Empleado emple = reserva.getEmple();
            if (emple != null) {
                emple = em.getReference(emple.getClass(), emple.getId_persona());
                reserva.setEmple(emple);
            }
            Habitacion habi = reserva.getHabi();
            if (habi != null) {
                habi = em.getReference(habi.getClass(), habi.getId_habitacion());
                reserva.setHabi(habi);
            }
            em.persist(reserva);
            if (hues != null) {
                hues.getListRes().add(reserva);
                hues = em.merge(hues);
            }
            if (emple != null) {
                emple.getListRes().add(reserva);
                emple = em.merge(emple);
            }
            if (habi != null) {
                habi.getListRes().add(reserva);
                habi = em.merge(habi);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getId_reserva());
            Huesped huesOld = persistentReserva.getHues();
            Huesped huesNew = reserva.getHues();
            Empleado empleOld = persistentReserva.getEmple();
            Empleado empleNew = reserva.getEmple();
            Habitacion habiOld = persistentReserva.getHabi();
            Habitacion habiNew = reserva.getHabi();
            if (huesNew != null) {
                huesNew = em.getReference(huesNew.getClass(), huesNew.getId_persona());
                reserva.setHues(huesNew);
            }
            if (empleNew != null) {
                empleNew = em.getReference(empleNew.getClass(), empleNew.getId_persona());
                reserva.setEmple(empleNew);
            }
            if (habiNew != null) {
                habiNew = em.getReference(habiNew.getClass(), habiNew.getId_habitacion());
                reserva.setHabi(habiNew);
            }
            reserva = em.merge(reserva);
            if (huesOld != null && !huesOld.equals(huesNew)) {
                huesOld.getListRes().remove(reserva);
                huesOld = em.merge(huesOld);
            }
            if (huesNew != null && !huesNew.equals(huesOld)) {
                huesNew.getListRes().add(reserva);
                huesNew = em.merge(huesNew);
            }
            if (empleOld != null && !empleOld.equals(empleNew)) {
                empleOld.getListRes().remove(reserva);
                empleOld = em.merge(empleOld);
            }
            if (empleNew != null && !empleNew.equals(empleOld)) {
                empleNew.getListRes().add(reserva);
                empleNew = em.merge(empleNew);
            }
            if (habiOld != null && !habiOld.equals(habiNew)) {
                habiOld.getListRes().remove(reserva);
                habiOld = em.merge(habiOld);
            }
            if (habiNew != null && !habiNew.equals(habiOld)) {
                habiNew.getListRes().add(reserva);
                habiNew = em.merge(habiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = reserva.getId_reserva();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
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
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getId_reserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Huesped hues = reserva.getHues();
            if (hues != null) {
                hues.getListRes().remove(reserva);
                hues = em.merge(hues);
            }
            Empleado emple = reserva.getEmple();
            if (emple != null) {
                emple.getListRes().remove(reserva);
                emple = em.merge(emple);
            }
            Habitacion habi = reserva.getHabi();
            if (habi != null) {
                habi.getListRes().remove(reserva);
                habi = em.merge(habi);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
