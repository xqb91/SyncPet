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
import cl.starlabs.modelo.Farmacos;
import cl.starlabs.modelo.TipoFarmaco;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class TipoFarmacoJpaController implements Serializable {

    public TipoFarmacoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoFarmaco tipoFarmaco) throws PreexistingEntityException, Exception {
        if (tipoFarmaco.getFarmacosCollection() == null) {
            tipoFarmaco.setFarmacosCollection(new ArrayList<Farmacos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Farmacos> attachedFarmacosCollection = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionFarmacosToAttach : tipoFarmaco.getFarmacosCollection()) {
                farmacosCollectionFarmacosToAttach = em.getReference(farmacosCollectionFarmacosToAttach.getClass(), farmacosCollectionFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollection.add(farmacosCollectionFarmacosToAttach);
            }
            tipoFarmaco.setFarmacosCollection(attachedFarmacosCollection);
            em.persist(tipoFarmaco);
            for (Farmacos farmacosCollectionFarmacos : tipoFarmaco.getFarmacosCollection()) {
                TipoFarmaco oldFarmacoOfFarmacosCollectionFarmacos = farmacosCollectionFarmacos.getFarmaco();
                farmacosCollectionFarmacos.setFarmaco(tipoFarmaco);
                farmacosCollectionFarmacos = em.merge(farmacosCollectionFarmacos);
                if (oldFarmacoOfFarmacosCollectionFarmacos != null) {
                    oldFarmacoOfFarmacosCollectionFarmacos.getFarmacosCollection().remove(farmacosCollectionFarmacos);
                    oldFarmacoOfFarmacosCollectionFarmacos = em.merge(oldFarmacoOfFarmacosCollectionFarmacos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoFarmaco(tipoFarmaco.getIdFarmaco()) != null) {
                throw new PreexistingEntityException("TipoFarmaco " + tipoFarmaco + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoFarmaco tipoFarmaco) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoFarmaco persistentTipoFarmaco = em.find(TipoFarmaco.class, tipoFarmaco.getIdFarmaco());
            Collection<Farmacos> farmacosCollectionOld = persistentTipoFarmaco.getFarmacosCollection();
            Collection<Farmacos> farmacosCollectionNew = tipoFarmaco.getFarmacosCollection();
            List<String> illegalOrphanMessages = null;
            for (Farmacos farmacosCollectionOldFarmacos : farmacosCollectionOld) {
                if (!farmacosCollectionNew.contains(farmacosCollectionOldFarmacos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Farmacos " + farmacosCollectionOldFarmacos + " since its farmaco field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Farmacos> attachedFarmacosCollectionNew = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionNewFarmacosToAttach : farmacosCollectionNew) {
                farmacosCollectionNewFarmacosToAttach = em.getReference(farmacosCollectionNewFarmacosToAttach.getClass(), farmacosCollectionNewFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollectionNew.add(farmacosCollectionNewFarmacosToAttach);
            }
            farmacosCollectionNew = attachedFarmacosCollectionNew;
            tipoFarmaco.setFarmacosCollection(farmacosCollectionNew);
            tipoFarmaco = em.merge(tipoFarmaco);
            for (Farmacos farmacosCollectionNewFarmacos : farmacosCollectionNew) {
                if (!farmacosCollectionOld.contains(farmacosCollectionNewFarmacos)) {
                    TipoFarmaco oldFarmacoOfFarmacosCollectionNewFarmacos = farmacosCollectionNewFarmacos.getFarmaco();
                    farmacosCollectionNewFarmacos.setFarmaco(tipoFarmaco);
                    farmacosCollectionNewFarmacos = em.merge(farmacosCollectionNewFarmacos);
                    if (oldFarmacoOfFarmacosCollectionNewFarmacos != null && !oldFarmacoOfFarmacosCollectionNewFarmacos.equals(tipoFarmaco)) {
                        oldFarmacoOfFarmacosCollectionNewFarmacos.getFarmacosCollection().remove(farmacosCollectionNewFarmacos);
                        oldFarmacoOfFarmacosCollectionNewFarmacos = em.merge(oldFarmacoOfFarmacosCollectionNewFarmacos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoFarmaco.getIdFarmaco();
                if (findTipoFarmaco(id) == null) {
                    throw new NonexistentEntityException("The tipoFarmaco with id " + id + " no longer exists.");
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
            TipoFarmaco tipoFarmaco;
            try {
                tipoFarmaco = em.getReference(TipoFarmaco.class, id);
                tipoFarmaco.getIdFarmaco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoFarmaco with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Farmacos> farmacosCollectionOrphanCheck = tipoFarmaco.getFarmacosCollection();
            for (Farmacos farmacosCollectionOrphanCheckFarmacos : farmacosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoFarmaco (" + tipoFarmaco + ") cannot be destroyed since the Farmacos " + farmacosCollectionOrphanCheckFarmacos + " in its farmacosCollection field has a non-nullable farmaco field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoFarmaco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoFarmaco> findTipoFarmacoEntities() {
        return findTipoFarmacoEntities(true, -1, -1);
    }

    public List<TipoFarmaco> findTipoFarmacoEntities(int maxResults, int firstResult) {
        return findTipoFarmacoEntities(false, maxResults, firstResult);
    }

    private List<TipoFarmaco> findTipoFarmacoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoFarmaco.class));
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

    public TipoFarmaco findTipoFarmaco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoFarmaco.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoFarmacoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoFarmaco> rt = cq.from(TipoFarmaco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
