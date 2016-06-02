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
import cl.starlabs.modelo.Contraindicaciones;
import cl.starlabs.modelo.TipoContraindicacion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class TipoContraindicacionJpaController implements Serializable {

    public TipoContraindicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoContraindicacion tipoContraindicacion) throws PreexistingEntityException, Exception {
        if (tipoContraindicacion.getContraindicacionesCollection() == null) {
            tipoContraindicacion.setContraindicacionesCollection(new ArrayList<Contraindicaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Contraindicaciones> attachedContraindicacionesCollection = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionContraindicacionesToAttach : tipoContraindicacion.getContraindicacionesCollection()) {
                contraindicacionesCollectionContraindicacionesToAttach = em.getReference(contraindicacionesCollectionContraindicacionesToAttach.getClass(), contraindicacionesCollectionContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollection.add(contraindicacionesCollectionContraindicacionesToAttach);
            }
            tipoContraindicacion.setContraindicacionesCollection(attachedContraindicacionesCollection);
            em.persist(tipoContraindicacion);
            for (Contraindicaciones contraindicacionesCollectionContraindicaciones : tipoContraindicacion.getContraindicacionesCollection()) {
                TipoContraindicacion oldTipoContraindicicacionOfContraindicacionesCollectionContraindicaciones = contraindicacionesCollectionContraindicaciones.getTipoContraindicicacion();
                contraindicacionesCollectionContraindicaciones.setTipoContraindicicacion(tipoContraindicacion);
                contraindicacionesCollectionContraindicaciones = em.merge(contraindicacionesCollectionContraindicaciones);
                if (oldTipoContraindicicacionOfContraindicacionesCollectionContraindicaciones != null) {
                    oldTipoContraindicicacionOfContraindicacionesCollectionContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionContraindicaciones);
                    oldTipoContraindicicacionOfContraindicacionesCollectionContraindicaciones = em.merge(oldTipoContraindicicacionOfContraindicacionesCollectionContraindicaciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoContraindicacion(tipoContraindicacion.getIdTipoContraindicacion()) != null) {
                throw new PreexistingEntityException("TipoContraindicacion " + tipoContraindicacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoContraindicacion tipoContraindicacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoContraindicacion persistentTipoContraindicacion = em.find(TipoContraindicacion.class, tipoContraindicacion.getIdTipoContraindicacion());
            Collection<Contraindicaciones> contraindicacionesCollectionOld = persistentTipoContraindicacion.getContraindicacionesCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionNew = tipoContraindicacion.getContraindicacionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Contraindicaciones contraindicacionesCollectionOldContraindicaciones : contraindicacionesCollectionOld) {
                if (!contraindicacionesCollectionNew.contains(contraindicacionesCollectionOldContraindicaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contraindicaciones " + contraindicacionesCollectionOldContraindicaciones + " since its tipoContraindicicacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Contraindicaciones> attachedContraindicacionesCollectionNew = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionNewContraindicacionesToAttach : contraindicacionesCollectionNew) {
                contraindicacionesCollectionNewContraindicacionesToAttach = em.getReference(contraindicacionesCollectionNewContraindicacionesToAttach.getClass(), contraindicacionesCollectionNewContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollectionNew.add(contraindicacionesCollectionNewContraindicacionesToAttach);
            }
            contraindicacionesCollectionNew = attachedContraindicacionesCollectionNew;
            tipoContraindicacion.setContraindicacionesCollection(contraindicacionesCollectionNew);
            tipoContraindicacion = em.merge(tipoContraindicacion);
            for (Contraindicaciones contraindicacionesCollectionNewContraindicaciones : contraindicacionesCollectionNew) {
                if (!contraindicacionesCollectionOld.contains(contraindicacionesCollectionNewContraindicaciones)) {
                    TipoContraindicacion oldTipoContraindicicacionOfContraindicacionesCollectionNewContraindicaciones = contraindicacionesCollectionNewContraindicaciones.getTipoContraindicicacion();
                    contraindicacionesCollectionNewContraindicaciones.setTipoContraindicicacion(tipoContraindicacion);
                    contraindicacionesCollectionNewContraindicaciones = em.merge(contraindicacionesCollectionNewContraindicaciones);
                    if (oldTipoContraindicicacionOfContraindicacionesCollectionNewContraindicaciones != null && !oldTipoContraindicicacionOfContraindicacionesCollectionNewContraindicaciones.equals(tipoContraindicacion)) {
                        oldTipoContraindicicacionOfContraindicacionesCollectionNewContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionNewContraindicaciones);
                        oldTipoContraindicicacionOfContraindicacionesCollectionNewContraindicaciones = em.merge(oldTipoContraindicicacionOfContraindicacionesCollectionNewContraindicaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoContraindicacion.getIdTipoContraindicacion();
                if (findTipoContraindicacion(id) == null) {
                    throw new NonexistentEntityException("The tipoContraindicacion with id " + id + " no longer exists.");
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
            TipoContraindicacion tipoContraindicacion;
            try {
                tipoContraindicacion = em.getReference(TipoContraindicacion.class, id);
                tipoContraindicacion.getIdTipoContraindicacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoContraindicacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Contraindicaciones> contraindicacionesCollectionOrphanCheck = tipoContraindicacion.getContraindicacionesCollection();
            for (Contraindicaciones contraindicacionesCollectionOrphanCheckContraindicaciones : contraindicacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoContraindicacion (" + tipoContraindicacion + ") cannot be destroyed since the Contraindicaciones " + contraindicacionesCollectionOrphanCheckContraindicaciones + " in its contraindicacionesCollection field has a non-nullable tipoContraindicicacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoContraindicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoContraindicacion> findTipoContraindicacionEntities() {
        return findTipoContraindicacionEntities(true, -1, -1);
    }

    public List<TipoContraindicacion> findTipoContraindicacionEntities(int maxResults, int firstResult) {
        return findTipoContraindicacionEntities(false, maxResults, firstResult);
    }

    private List<TipoContraindicacion> findTipoContraindicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoContraindicacion.class));
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

    public TipoContraindicacion findTipoContraindicacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoContraindicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoContraindicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoContraindicacion> rt = cq.from(TipoContraindicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
