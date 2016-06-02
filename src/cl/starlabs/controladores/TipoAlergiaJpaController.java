/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Alergias;
import cl.starlabs.modelo.TipoAlergia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class TipoAlergiaJpaController implements Serializable {

    public TipoAlergiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAlergia tipoAlergia) throws PreexistingEntityException, Exception {
        if (tipoAlergia.getAlergiasCollection() == null) {
            tipoAlergia.setAlergiasCollection(new ArrayList<Alergias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Alergias> attachedAlergiasCollection = new ArrayList<Alergias>();
            for (Alergias alergiasCollectionAlergiasToAttach : tipoAlergia.getAlergiasCollection()) {
                alergiasCollectionAlergiasToAttach = em.getReference(alergiasCollectionAlergiasToAttach.getClass(), alergiasCollectionAlergiasToAttach.getIdAlergia());
                attachedAlergiasCollection.add(alergiasCollectionAlergiasToAttach);
            }
            tipoAlergia.setAlergiasCollection(attachedAlergiasCollection);
            em.persist(tipoAlergia);
            for (Alergias alergiasCollectionAlergias : tipoAlergia.getAlergiasCollection()) {
                TipoAlergia oldIdTipoAlergiaOfAlergiasCollectionAlergias = alergiasCollectionAlergias.getIdTipoAlergia();
                alergiasCollectionAlergias.setIdTipoAlergia(tipoAlergia);
                alergiasCollectionAlergias = em.merge(alergiasCollectionAlergias);
                if (oldIdTipoAlergiaOfAlergiasCollectionAlergias != null) {
                    oldIdTipoAlergiaOfAlergiasCollectionAlergias.getAlergiasCollection().remove(alergiasCollectionAlergias);
                    oldIdTipoAlergiaOfAlergiasCollectionAlergias = em.merge(oldIdTipoAlergiaOfAlergiasCollectionAlergias);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoAlergia(tipoAlergia.getIdTipoAlergia()) != null) {
                throw new PreexistingEntityException("TipoAlergia " + tipoAlergia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAlergia tipoAlergia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAlergia persistentTipoAlergia = em.find(TipoAlergia.class, tipoAlergia.getIdTipoAlergia());
            Collection<Alergias> alergiasCollectionOld = persistentTipoAlergia.getAlergiasCollection();
            Collection<Alergias> alergiasCollectionNew = tipoAlergia.getAlergiasCollection();
            Collection<Alergias> attachedAlergiasCollectionNew = new ArrayList<Alergias>();
            for (Alergias alergiasCollectionNewAlergiasToAttach : alergiasCollectionNew) {
                alergiasCollectionNewAlergiasToAttach = em.getReference(alergiasCollectionNewAlergiasToAttach.getClass(), alergiasCollectionNewAlergiasToAttach.getIdAlergia());
                attachedAlergiasCollectionNew.add(alergiasCollectionNewAlergiasToAttach);
            }
            alergiasCollectionNew = attachedAlergiasCollectionNew;
            tipoAlergia.setAlergiasCollection(alergiasCollectionNew);
            tipoAlergia = em.merge(tipoAlergia);
            for (Alergias alergiasCollectionOldAlergias : alergiasCollectionOld) {
                if (!alergiasCollectionNew.contains(alergiasCollectionOldAlergias)) {
                    alergiasCollectionOldAlergias.setIdTipoAlergia(null);
                    alergiasCollectionOldAlergias = em.merge(alergiasCollectionOldAlergias);
                }
            }
            for (Alergias alergiasCollectionNewAlergias : alergiasCollectionNew) {
                if (!alergiasCollectionOld.contains(alergiasCollectionNewAlergias)) {
                    TipoAlergia oldIdTipoAlergiaOfAlergiasCollectionNewAlergias = alergiasCollectionNewAlergias.getIdTipoAlergia();
                    alergiasCollectionNewAlergias.setIdTipoAlergia(tipoAlergia);
                    alergiasCollectionNewAlergias = em.merge(alergiasCollectionNewAlergias);
                    if (oldIdTipoAlergiaOfAlergiasCollectionNewAlergias != null && !oldIdTipoAlergiaOfAlergiasCollectionNewAlergias.equals(tipoAlergia)) {
                        oldIdTipoAlergiaOfAlergiasCollectionNewAlergias.getAlergiasCollection().remove(alergiasCollectionNewAlergias);
                        oldIdTipoAlergiaOfAlergiasCollectionNewAlergias = em.merge(oldIdTipoAlergiaOfAlergiasCollectionNewAlergias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoAlergia.getIdTipoAlergia();
                if (findTipoAlergia(id) == null) {
                    throw new NonexistentEntityException("The tipoAlergia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAlergia tipoAlergia;
            try {
                tipoAlergia = em.getReference(TipoAlergia.class, id);
                tipoAlergia.getIdTipoAlergia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAlergia with id " + id + " no longer exists.", enfe);
            }
            Collection<Alergias> alergiasCollection = tipoAlergia.getAlergiasCollection();
            for (Alergias alergiasCollectionAlergias : alergiasCollection) {
                alergiasCollectionAlergias.setIdTipoAlergia(null);
                alergiasCollectionAlergias = em.merge(alergiasCollectionAlergias);
            }
            em.remove(tipoAlergia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAlergia> findTipoAlergiaEntities() {
        return findTipoAlergiaEntities(true, -1, -1);
    }

    public List<TipoAlergia> findTipoAlergiaEntities(int maxResults, int firstResult) {
        return findTipoAlergiaEntities(false, maxResults, firstResult);
    }

    private List<TipoAlergia> findTipoAlergiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoAlergia.class));
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

    public TipoAlergia findTipoAlergia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAlergia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAlergiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoAlergia> rt = cq.from(TipoAlergia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
