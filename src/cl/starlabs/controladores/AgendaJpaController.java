/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Agenda;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.AgendaDetalle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class AgendaJpaController implements Serializable {

    public AgendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agenda agenda) throws PreexistingEntityException, Exception {
        if (agenda.getAgendaDetalleCollection() == null) {
            agenda.setAgendaDetalleCollection(new ArrayList<AgendaDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sucursal sucursal = agenda.getSucursal();
            if (sucursal != null) {
                sucursal = em.getReference(sucursal.getClass(), sucursal.getIdSucursal());
                agenda.setSucursal(sucursal);
            }
            Collection<AgendaDetalle> attachedAgendaDetalleCollection = new ArrayList<AgendaDetalle>();
            for (AgendaDetalle agendaDetalleCollectionAgendaDetalleToAttach : agenda.getAgendaDetalleCollection()) {
                agendaDetalleCollectionAgendaDetalleToAttach = em.getReference(agendaDetalleCollectionAgendaDetalleToAttach.getClass(), agendaDetalleCollectionAgendaDetalleToAttach.getIdDetalle());
                attachedAgendaDetalleCollection.add(agendaDetalleCollectionAgendaDetalleToAttach);
            }
            agenda.setAgendaDetalleCollection(attachedAgendaDetalleCollection);
            em.persist(agenda);
            if (sucursal != null) {
                sucursal.getAgendaCollection().add(agenda);
                sucursal = em.merge(sucursal);
            }
            for (AgendaDetalle agendaDetalleCollectionAgendaDetalle : agenda.getAgendaDetalleCollection()) {
                Agenda oldEventoAgendaOfAgendaDetalleCollectionAgendaDetalle = agendaDetalleCollectionAgendaDetalle.getEventoAgenda();
                agendaDetalleCollectionAgendaDetalle.setEventoAgenda(agenda);
                agendaDetalleCollectionAgendaDetalle = em.merge(agendaDetalleCollectionAgendaDetalle);
                if (oldEventoAgendaOfAgendaDetalleCollectionAgendaDetalle != null) {
                    oldEventoAgendaOfAgendaDetalleCollectionAgendaDetalle.getAgendaDetalleCollection().remove(agendaDetalleCollectionAgendaDetalle);
                    oldEventoAgendaOfAgendaDetalleCollectionAgendaDetalle = em.merge(oldEventoAgendaOfAgendaDetalleCollectionAgendaDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAgenda(agenda.getIdEvento()) != null) {
                throw new PreexistingEntityException("Agenda " + agenda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agenda agenda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agenda persistentAgenda = em.find(Agenda.class, agenda.getIdEvento());
            Sucursal sucursalOld = persistentAgenda.getSucursal();
            Sucursal sucursalNew = agenda.getSucursal();
            Collection<AgendaDetalle> agendaDetalleCollectionOld = persistentAgenda.getAgendaDetalleCollection();
            Collection<AgendaDetalle> agendaDetalleCollectionNew = agenda.getAgendaDetalleCollection();
            List<String> illegalOrphanMessages = null;
            for (AgendaDetalle agendaDetalleCollectionOldAgendaDetalle : agendaDetalleCollectionOld) {
                if (!agendaDetalleCollectionNew.contains(agendaDetalleCollectionOldAgendaDetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AgendaDetalle " + agendaDetalleCollectionOldAgendaDetalle + " since its eventoAgenda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sucursalNew != null) {
                sucursalNew = em.getReference(sucursalNew.getClass(), sucursalNew.getIdSucursal());
                agenda.setSucursal(sucursalNew);
            }
            Collection<AgendaDetalle> attachedAgendaDetalleCollectionNew = new ArrayList<AgendaDetalle>();
            for (AgendaDetalle agendaDetalleCollectionNewAgendaDetalleToAttach : agendaDetalleCollectionNew) {
                agendaDetalleCollectionNewAgendaDetalleToAttach = em.getReference(agendaDetalleCollectionNewAgendaDetalleToAttach.getClass(), agendaDetalleCollectionNewAgendaDetalleToAttach.getIdDetalle());
                attachedAgendaDetalleCollectionNew.add(agendaDetalleCollectionNewAgendaDetalleToAttach);
            }
            agendaDetalleCollectionNew = attachedAgendaDetalleCollectionNew;
            agenda.setAgendaDetalleCollection(agendaDetalleCollectionNew);
            agenda = em.merge(agenda);
            if (sucursalOld != null && !sucursalOld.equals(sucursalNew)) {
                sucursalOld.getAgendaCollection().remove(agenda);
                sucursalOld = em.merge(sucursalOld);
            }
            if (sucursalNew != null && !sucursalNew.equals(sucursalOld)) {
                sucursalNew.getAgendaCollection().add(agenda);
                sucursalNew = em.merge(sucursalNew);
            }
            for (AgendaDetalle agendaDetalleCollectionNewAgendaDetalle : agendaDetalleCollectionNew) {
                if (!agendaDetalleCollectionOld.contains(agendaDetalleCollectionNewAgendaDetalle)) {
                    Agenda oldEventoAgendaOfAgendaDetalleCollectionNewAgendaDetalle = agendaDetalleCollectionNewAgendaDetalle.getEventoAgenda();
                    agendaDetalleCollectionNewAgendaDetalle.setEventoAgenda(agenda);
                    agendaDetalleCollectionNewAgendaDetalle = em.merge(agendaDetalleCollectionNewAgendaDetalle);
                    if (oldEventoAgendaOfAgendaDetalleCollectionNewAgendaDetalle != null && !oldEventoAgendaOfAgendaDetalleCollectionNewAgendaDetalle.equals(agenda)) {
                        oldEventoAgendaOfAgendaDetalleCollectionNewAgendaDetalle.getAgendaDetalleCollection().remove(agendaDetalleCollectionNewAgendaDetalle);
                        oldEventoAgendaOfAgendaDetalleCollectionNewAgendaDetalle = em.merge(oldEventoAgendaOfAgendaDetalleCollectionNewAgendaDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agenda.getIdEvento();
                if (findAgenda(id) == null) {
                    throw new NonexistentEntityException("The agenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agenda agenda;
            try {
                agenda = em.getReference(Agenda.class, id);
                agenda.getIdEvento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agenda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<AgendaDetalle> agendaDetalleCollectionOrphanCheck = agenda.getAgendaDetalleCollection();
            for (AgendaDetalle agendaDetalleCollectionOrphanCheckAgendaDetalle : agendaDetalleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Agenda (" + agenda + ") cannot be destroyed since the AgendaDetalle " + agendaDetalleCollectionOrphanCheckAgendaDetalle + " in its agendaDetalleCollection field has a non-nullable eventoAgenda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sucursal sucursal = agenda.getSucursal();
            if (sucursal != null) {
                sucursal.getAgendaCollection().remove(agenda);
                sucursal = em.merge(sucursal);
            }
            em.remove(agenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agenda> findAgendaEntities() {
        return findAgendaEntities(true, -1, -1);
    }

    public List<Agenda> findAgendaEntities(int maxResults, int firstResult) {
        return findAgendaEntities(false, maxResults, firstResult);
    }

    private List<Agenda> findAgendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agenda.class));
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

    public Agenda findAgenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agenda> rt = cq.from(Agenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
