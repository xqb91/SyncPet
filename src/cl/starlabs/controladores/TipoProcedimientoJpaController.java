/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Procedimientos;
import cl.starlabs.modelo.TipoProcedimiento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class TipoProcedimientoJpaController implements Serializable {

    public TipoProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoProcedimiento tipoProcedimiento) throws PreexistingEntityException, Exception {
        if (tipoProcedimiento.getProcedimientosCollection() == null) {
            tipoProcedimiento.setProcedimientosCollection(new ArrayList<Procedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Procedimientos> attachedProcedimientosCollection = new ArrayList<Procedimientos>();
            for (Procedimientos procedimientosCollectionProcedimientosToAttach : tipoProcedimiento.getProcedimientosCollection()) {
                procedimientosCollectionProcedimientosToAttach = em.getReference(procedimientosCollectionProcedimientosToAttach.getClass(), procedimientosCollectionProcedimientosToAttach.getIdProcedimiento());
                attachedProcedimientosCollection.add(procedimientosCollectionProcedimientosToAttach);
            }
            tipoProcedimiento.setProcedimientosCollection(attachedProcedimientosCollection);
            em.persist(tipoProcedimiento);
            for (Procedimientos procedimientosCollectionProcedimientos : tipoProcedimiento.getProcedimientosCollection()) {
                TipoProcedimiento oldTipoProcedimientoOfProcedimientosCollectionProcedimientos = procedimientosCollectionProcedimientos.getTipoProcedimiento();
                procedimientosCollectionProcedimientos.setTipoProcedimiento(tipoProcedimiento);
                procedimientosCollectionProcedimientos = em.merge(procedimientosCollectionProcedimientos);
                if (oldTipoProcedimientoOfProcedimientosCollectionProcedimientos != null) {
                    oldTipoProcedimientoOfProcedimientosCollectionProcedimientos.getProcedimientosCollection().remove(procedimientosCollectionProcedimientos);
                    oldTipoProcedimientoOfProcedimientosCollectionProcedimientos = em.merge(oldTipoProcedimientoOfProcedimientosCollectionProcedimientos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoProcedimiento(tipoProcedimiento.getIdTipoProcedimiento()) != null) {
                throw new PreexistingEntityException("TipoProcedimiento " + tipoProcedimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoProcedimiento tipoProcedimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoProcedimiento persistentTipoProcedimiento = em.find(TipoProcedimiento.class, tipoProcedimiento.getIdTipoProcedimiento());
            Collection<Procedimientos> procedimientosCollectionOld = persistentTipoProcedimiento.getProcedimientosCollection();
            Collection<Procedimientos> procedimientosCollectionNew = tipoProcedimiento.getProcedimientosCollection();
            List<String> illegalOrphanMessages = null;
            for (Procedimientos procedimientosCollectionOldProcedimientos : procedimientosCollectionOld) {
                if (!procedimientosCollectionNew.contains(procedimientosCollectionOldProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimientos " + procedimientosCollectionOldProcedimientos + " since its tipoProcedimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Procedimientos> attachedProcedimientosCollectionNew = new ArrayList<Procedimientos>();
            for (Procedimientos procedimientosCollectionNewProcedimientosToAttach : procedimientosCollectionNew) {
                procedimientosCollectionNewProcedimientosToAttach = em.getReference(procedimientosCollectionNewProcedimientosToAttach.getClass(), procedimientosCollectionNewProcedimientosToAttach.getIdProcedimiento());
                attachedProcedimientosCollectionNew.add(procedimientosCollectionNewProcedimientosToAttach);
            }
            procedimientosCollectionNew = attachedProcedimientosCollectionNew;
            tipoProcedimiento.setProcedimientosCollection(procedimientosCollectionNew);
            tipoProcedimiento = em.merge(tipoProcedimiento);
            for (Procedimientos procedimientosCollectionNewProcedimientos : procedimientosCollectionNew) {
                if (!procedimientosCollectionOld.contains(procedimientosCollectionNewProcedimientos)) {
                    TipoProcedimiento oldTipoProcedimientoOfProcedimientosCollectionNewProcedimientos = procedimientosCollectionNewProcedimientos.getTipoProcedimiento();
                    procedimientosCollectionNewProcedimientos.setTipoProcedimiento(tipoProcedimiento);
                    procedimientosCollectionNewProcedimientos = em.merge(procedimientosCollectionNewProcedimientos);
                    if (oldTipoProcedimientoOfProcedimientosCollectionNewProcedimientos != null && !oldTipoProcedimientoOfProcedimientosCollectionNewProcedimientos.equals(tipoProcedimiento)) {
                        oldTipoProcedimientoOfProcedimientosCollectionNewProcedimientos.getProcedimientosCollection().remove(procedimientosCollectionNewProcedimientos);
                        oldTipoProcedimientoOfProcedimientosCollectionNewProcedimientos = em.merge(oldTipoProcedimientoOfProcedimientosCollectionNewProcedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoProcedimiento.getIdTipoProcedimiento();
                if (findTipoProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The tipoProcedimiento with id " + id + " no longer exists.");
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
            TipoProcedimiento tipoProcedimiento;
            try {
                tipoProcedimiento = em.getReference(TipoProcedimiento.class, id);
                tipoProcedimiento.getIdTipoProcedimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoProcedimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Procedimientos> procedimientosCollectionOrphanCheck = tipoProcedimiento.getProcedimientosCollection();
            for (Procedimientos procedimientosCollectionOrphanCheckProcedimientos : procedimientosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoProcedimiento (" + tipoProcedimiento + ") cannot be destroyed since the Procedimientos " + procedimientosCollectionOrphanCheckProcedimientos + " in its procedimientosCollection field has a non-nullable tipoProcedimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoProcedimiento> findTipoProcedimientoEntities() {
        return findTipoProcedimientoEntities(true, -1, -1);
    }

    public List<TipoProcedimiento> findTipoProcedimientoEntities(int maxResults, int firstResult) {
        return findTipoProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<TipoProcedimiento> findTipoProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoProcedimiento.class));
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

    public TipoProcedimiento findTipoProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoProcedimiento> rt = cq.from(TipoProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
