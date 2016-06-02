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
import cl.starlabs.modelo.Patologias;
import cl.starlabs.modelo.TipoPatología;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class TipoPatologíaJpaController implements Serializable {

    public TipoPatologíaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPatología tipoPatología) throws PreexistingEntityException, Exception {
        if (tipoPatología.getPatologiasCollection() == null) {
            tipoPatología.setPatologiasCollection(new ArrayList<Patologias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Patologias> attachedPatologiasCollection = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionPatologiasToAttach : tipoPatología.getPatologiasCollection()) {
                patologiasCollectionPatologiasToAttach = em.getReference(patologiasCollectionPatologiasToAttach.getClass(), patologiasCollectionPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollection.add(patologiasCollectionPatologiasToAttach);
            }
            tipoPatología.setPatologiasCollection(attachedPatologiasCollection);
            em.persist(tipoPatología);
            for (Patologias patologiasCollectionPatologias : tipoPatología.getPatologiasCollection()) {
                TipoPatología oldTipoPatologiaOfPatologiasCollectionPatologias = patologiasCollectionPatologias.getTipoPatologia();
                patologiasCollectionPatologias.setTipoPatologia(tipoPatología);
                patologiasCollectionPatologias = em.merge(patologiasCollectionPatologias);
                if (oldTipoPatologiaOfPatologiasCollectionPatologias != null) {
                    oldTipoPatologiaOfPatologiasCollectionPatologias.getPatologiasCollection().remove(patologiasCollectionPatologias);
                    oldTipoPatologiaOfPatologiasCollectionPatologias = em.merge(oldTipoPatologiaOfPatologiasCollectionPatologias);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoPatología(tipoPatología.getIdTipoPatologia()) != null) {
                throw new PreexistingEntityException("TipoPatolog\u00eda " + tipoPatología + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPatología tipoPatología) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPatología persistentTipoPatología = em.find(TipoPatología.class, tipoPatología.getIdTipoPatologia());
            Collection<Patologias> patologiasCollectionOld = persistentTipoPatología.getPatologiasCollection();
            Collection<Patologias> patologiasCollectionNew = tipoPatología.getPatologiasCollection();
            List<String> illegalOrphanMessages = null;
            for (Patologias patologiasCollectionOldPatologias : patologiasCollectionOld) {
                if (!patologiasCollectionNew.contains(patologiasCollectionOldPatologias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Patologias " + patologiasCollectionOldPatologias + " since its tipoPatologia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Patologias> attachedPatologiasCollectionNew = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionNewPatologiasToAttach : patologiasCollectionNew) {
                patologiasCollectionNewPatologiasToAttach = em.getReference(patologiasCollectionNewPatologiasToAttach.getClass(), patologiasCollectionNewPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollectionNew.add(patologiasCollectionNewPatologiasToAttach);
            }
            patologiasCollectionNew = attachedPatologiasCollectionNew;
            tipoPatología.setPatologiasCollection(patologiasCollectionNew);
            tipoPatología = em.merge(tipoPatología);
            for (Patologias patologiasCollectionNewPatologias : patologiasCollectionNew) {
                if (!patologiasCollectionOld.contains(patologiasCollectionNewPatologias)) {
                    TipoPatología oldTipoPatologiaOfPatologiasCollectionNewPatologias = patologiasCollectionNewPatologias.getTipoPatologia();
                    patologiasCollectionNewPatologias.setTipoPatologia(tipoPatología);
                    patologiasCollectionNewPatologias = em.merge(patologiasCollectionNewPatologias);
                    if (oldTipoPatologiaOfPatologiasCollectionNewPatologias != null && !oldTipoPatologiaOfPatologiasCollectionNewPatologias.equals(tipoPatología)) {
                        oldTipoPatologiaOfPatologiasCollectionNewPatologias.getPatologiasCollection().remove(patologiasCollectionNewPatologias);
                        oldTipoPatologiaOfPatologiasCollectionNewPatologias = em.merge(oldTipoPatologiaOfPatologiasCollectionNewPatologias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoPatología.getIdTipoPatologia();
                if (findTipoPatología(id) == null) {
                    throw new NonexistentEntityException("The tipoPatolog\u00eda with id " + id + " no longer exists.");
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
            TipoPatología tipoPatología;
            try {
                tipoPatología = em.getReference(TipoPatología.class, id);
                tipoPatología.getIdTipoPatologia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPatolog\u00eda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Patologias> patologiasCollectionOrphanCheck = tipoPatología.getPatologiasCollection();
            for (Patologias patologiasCollectionOrphanCheckPatologias : patologiasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoPatolog\u00eda (" + tipoPatología + ") cannot be destroyed since the Patologias " + patologiasCollectionOrphanCheckPatologias + " in its patologiasCollection field has a non-nullable tipoPatologia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoPatología);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPatología> findTipoPatologíaEntities() {
        return findTipoPatologíaEntities(true, -1, -1);
    }

    public List<TipoPatología> findTipoPatologíaEntities(int maxResults, int firstResult) {
        return findTipoPatologíaEntities(false, maxResults, firstResult);
    }

    private List<TipoPatología> findTipoPatologíaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPatología.class));
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

    public TipoPatología findTipoPatología(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPatología.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPatologíaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPatología> rt = cq.from(TipoPatología.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
