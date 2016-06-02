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
import cl.starlabs.modelo.Examenes;
import cl.starlabs.modelo.TipoExamen;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class TipoExamenJpaController implements Serializable {

    public TipoExamenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoExamen tipoExamen) throws PreexistingEntityException, Exception {
        if (tipoExamen.getExamenesCollection() == null) {
            tipoExamen.setExamenesCollection(new ArrayList<Examenes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Examenes> attachedExamenesCollection = new ArrayList<Examenes>();
            for (Examenes examenesCollectionExamenesToAttach : tipoExamen.getExamenesCollection()) {
                examenesCollectionExamenesToAttach = em.getReference(examenesCollectionExamenesToAttach.getClass(), examenesCollectionExamenesToAttach.getIdExamen());
                attachedExamenesCollection.add(examenesCollectionExamenesToAttach);
            }
            tipoExamen.setExamenesCollection(attachedExamenesCollection);
            em.persist(tipoExamen);
            for (Examenes examenesCollectionExamenes : tipoExamen.getExamenesCollection()) {
                TipoExamen oldTipoExamenOfExamenesCollectionExamenes = examenesCollectionExamenes.getTipoExamen();
                examenesCollectionExamenes.setTipoExamen(tipoExamen);
                examenesCollectionExamenes = em.merge(examenesCollectionExamenes);
                if (oldTipoExamenOfExamenesCollectionExamenes != null) {
                    oldTipoExamenOfExamenesCollectionExamenes.getExamenesCollection().remove(examenesCollectionExamenes);
                    oldTipoExamenOfExamenesCollectionExamenes = em.merge(oldTipoExamenOfExamenesCollectionExamenes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoExamen(tipoExamen.getIdTipoExamen()) != null) {
                throw new PreexistingEntityException("TipoExamen " + tipoExamen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoExamen tipoExamen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen persistentTipoExamen = em.find(TipoExamen.class, tipoExamen.getIdTipoExamen());
            Collection<Examenes> examenesCollectionOld = persistentTipoExamen.getExamenesCollection();
            Collection<Examenes> examenesCollectionNew = tipoExamen.getExamenesCollection();
            List<String> illegalOrphanMessages = null;
            for (Examenes examenesCollectionOldExamenes : examenesCollectionOld) {
                if (!examenesCollectionNew.contains(examenesCollectionOldExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Examenes " + examenesCollectionOldExamenes + " since its tipoExamen field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Examenes> attachedExamenesCollectionNew = new ArrayList<Examenes>();
            for (Examenes examenesCollectionNewExamenesToAttach : examenesCollectionNew) {
                examenesCollectionNewExamenesToAttach = em.getReference(examenesCollectionNewExamenesToAttach.getClass(), examenesCollectionNewExamenesToAttach.getIdExamen());
                attachedExamenesCollectionNew.add(examenesCollectionNewExamenesToAttach);
            }
            examenesCollectionNew = attachedExamenesCollectionNew;
            tipoExamen.setExamenesCollection(examenesCollectionNew);
            tipoExamen = em.merge(tipoExamen);
            for (Examenes examenesCollectionNewExamenes : examenesCollectionNew) {
                if (!examenesCollectionOld.contains(examenesCollectionNewExamenes)) {
                    TipoExamen oldTipoExamenOfExamenesCollectionNewExamenes = examenesCollectionNewExamenes.getTipoExamen();
                    examenesCollectionNewExamenes.setTipoExamen(tipoExamen);
                    examenesCollectionNewExamenes = em.merge(examenesCollectionNewExamenes);
                    if (oldTipoExamenOfExamenesCollectionNewExamenes != null && !oldTipoExamenOfExamenesCollectionNewExamenes.equals(tipoExamen)) {
                        oldTipoExamenOfExamenesCollectionNewExamenes.getExamenesCollection().remove(examenesCollectionNewExamenes);
                        oldTipoExamenOfExamenesCollectionNewExamenes = em.merge(oldTipoExamenOfExamenesCollectionNewExamenes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoExamen.getIdTipoExamen();
                if (findTipoExamen(id) == null) {
                    throw new NonexistentEntityException("The tipoExamen with id " + id + " no longer exists.");
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
            TipoExamen tipoExamen;
            try {
                tipoExamen = em.getReference(TipoExamen.class, id);
                tipoExamen.getIdTipoExamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoExamen with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Examenes> examenesCollectionOrphanCheck = tipoExamen.getExamenesCollection();
            for (Examenes examenesCollectionOrphanCheckExamenes : examenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoExamen (" + tipoExamen + ") cannot be destroyed since the Examenes " + examenesCollectionOrphanCheckExamenes + " in its examenesCollection field has a non-nullable tipoExamen field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoExamen> findTipoExamenEntities() {
        return findTipoExamenEntities(true, -1, -1);
    }

    public List<TipoExamen> findTipoExamenEntities(int maxResults, int firstResult) {
        return findTipoExamenEntities(false, maxResults, firstResult);
    }

    private List<TipoExamen> findTipoExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoExamen.class));
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

    public TipoExamen findTipoExamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoExamenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoExamen> rt = cq.from(TipoExamen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
