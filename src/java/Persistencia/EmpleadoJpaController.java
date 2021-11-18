/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Empleado;
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
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("tpofinalPU");
    }
    public void create(Empleado empleado) {
        if (empleado.getListRes() == null) {
            empleado.setListRes(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListRes = new ArrayList<Reserva>();
            for (Reserva listResReservaToAttach : empleado.getListRes()) {
                listResReservaToAttach = em.getReference(listResReservaToAttach.getClass(), listResReservaToAttach.getId_reserva());
                attachedListRes.add(listResReservaToAttach);
            }
            empleado.setListRes(attachedListRes);
            em.persist(empleado);
            for (Reserva listResReserva : empleado.getListRes()) {
                Empleado oldEmpleOfListResReserva = listResReserva.getEmple();
                listResReserva.setEmple(empleado);
                listResReserva = em.merge(listResReserva);
                if (oldEmpleOfListResReserva != null) {
                    oldEmpleOfListResReserva.getListRes().remove(listResReserva);
                    oldEmpleOfListResReserva = em.merge(oldEmpleOfListResReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId_persona());
            List<Reserva> listResOld = persistentEmpleado.getListRes();
            List<Reserva> listResNew = empleado.getListRes();
            List<Reserva> attachedListResNew = new ArrayList<Reserva>();
            for (Reserva listResNewReservaToAttach : listResNew) {
                listResNewReservaToAttach = em.getReference(listResNewReservaToAttach.getClass(), listResNewReservaToAttach.getId_reserva());
                attachedListResNew.add(listResNewReservaToAttach);
            }
            listResNew = attachedListResNew;
            empleado.setListRes(listResNew);
            empleado = em.merge(empleado);
            for (Reserva listResOldReserva : listResOld) {
                if (!listResNew.contains(listResOldReserva)) {
                    listResOldReserva.setEmple(null);
                    listResOldReserva = em.merge(listResOldReserva);
                }
            }
            for (Reserva listResNewReserva : listResNew) {
                if (!listResOld.contains(listResNewReserva)) {
                    Empleado oldEmpleOfListResNewReserva = listResNewReserva.getEmple();
                    listResNewReserva.setEmple(empleado);
                    listResNewReserva = em.merge(listResNewReserva);
                    if (oldEmpleOfListResNewReserva != null && !oldEmpleOfListResNewReserva.equals(empleado)) {
                        oldEmpleOfListResNewReserva.getListRes().remove(listResNewReserva);
                        oldEmpleOfListResNewReserva = em.merge(oldEmpleOfListResNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = empleado.getId_persona();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId_persona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listRes = empleado.getListRes();
            for (Reserva listResReserva : listRes) {
                listResReserva.setEmple(null);
                listResReserva = em.merge(listResReserva);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
