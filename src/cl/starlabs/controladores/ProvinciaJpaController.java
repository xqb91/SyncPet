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
import cl.starlabs.modelo.Region;
import cl.starlabs.modelo.Comuna;
import cl.starlabs.modelo.Provincia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) throws PreexistingEntityException, Exception {
        if (provincia.getComunaCollection() == null) {
            provincia.setComunaCollection(new ArrayList<Comuna>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Region region = provincia.getRegion();
            if (region != null) {
                region = em.getReference(region.getClass(), region.getIdRegion());
                provincia.setRegion(region);
            }
            Collection<Comuna> attachedComunaCollection = new ArrayList<Comuna>();
            for (Comuna comunaCollectionComunaToAttach : provincia.getComunaCollection()) {
                comunaCollectionComunaToAttach = em.getReference(comunaCollectionComunaToAttach.getClass(), comunaCollectionComunaToAttach.getIdComuna());
                attachedComunaCollection.add(comunaCollectionComunaToAttach);
            }
            provincia.setComunaCollection(attachedComunaCollection);
            em.persist(provincia);
            if (region != null) {
                region.getProvinciaCollection().add(provincia);
                region = em.merge(region);
            }
            for (Comuna comunaCollectionComuna : provincia.getComunaCollection()) {
                Provincia oldProvinciaOfComunaCollectionComuna = comunaCollectionComuna.getProvincia();
                comunaCollectionComuna.setProvincia(provincia);
                comunaCollectionComuna = em.merge(comunaCollectionComuna);
                if (oldProvinciaOfComunaCollectionComuna != null) {
                    oldProvinciaOfComunaCollectionComuna.getComunaCollection().remove(comunaCollectionComuna);
                    oldProvinciaOfComunaCollectionComuna = em.merge(oldProvinciaOfComunaCollectionComuna);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProvincia(provincia.getIdProvincia()) != null) {
                throw new PreexistingEntityException("Provincia " + provincia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdProvincia());
            Region regionOld = persistentProvincia.getRegion();
            Region regionNew = provincia.getRegion();
            Collection<Comuna> comunaCollectionOld = persistentProvincia.getComunaCollection();
            Collection<Comuna> comunaCollectionNew = provincia.getComunaCollection();
            List<String> illegalOrphanMessages = null;
            for (Comuna comunaCollectionOldComuna : comunaCollectionOld) {
                if (!comunaCollectionNew.contains(comunaCollectionOldComuna)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comuna " + comunaCollectionOldComuna + " since its provincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (regionNew != null) {
                regionNew = em.getReference(regionNew.getClass(), regionNew.getIdRegion());
                provincia.setRegion(regionNew);
            }
            Collection<Comuna> attachedComunaCollectionNew = new ArrayList<Comuna>();
            for (Comuna comunaCollectionNewComunaToAttach : comunaCollectionNew) {
                comunaCollectionNewComunaToAttach = em.getReference(comunaCollectionNewComunaToAttach.getClass(), comunaCollectionNewComunaToAttach.getIdComuna());
                attachedComunaCollectionNew.add(comunaCollectionNewComunaToAttach);
            }
            comunaCollectionNew = attachedComunaCollectionNew;
            provincia.setComunaCollection(comunaCollectionNew);
            provincia = em.merge(provincia);
            if (regionOld != null && !regionOld.equals(regionNew)) {
                regionOld.getProvinciaCollection().remove(provincia);
                regionOld = em.merge(regionOld);
            }
            if (regionNew != null && !regionNew.equals(regionOld)) {
                regionNew.getProvinciaCollection().add(provincia);
                regionNew = em.merge(regionNew);
            }
            for (Comuna comunaCollectionNewComuna : comunaCollectionNew) {
                if (!comunaCollectionOld.contains(comunaCollectionNewComuna)) {
                    Provincia oldProvinciaOfComunaCollectionNewComuna = comunaCollectionNewComuna.getProvincia();
                    comunaCollectionNewComuna.setProvincia(provincia);
                    comunaCollectionNewComuna = em.merge(comunaCollectionNewComuna);
                    if (oldProvinciaOfComunaCollectionNewComuna != null && !oldProvinciaOfComunaCollectionNewComuna.equals(provincia)) {
                        oldProvinciaOfComunaCollectionNewComuna.getComunaCollection().remove(comunaCollectionNewComuna);
                        oldProvinciaOfComunaCollectionNewComuna = em.merge(oldProvinciaOfComunaCollectionNewComuna);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provincia.getIdProvincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdProvincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Comuna> comunaCollectionOrphanCheck = provincia.getComunaCollection();
            for (Comuna comunaCollectionOrphanCheckComuna : comunaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Comuna " + comunaCollectionOrphanCheckComuna + " in its comunaCollection field has a non-nullable provincia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Region region = provincia.getRegion();
            if (region != null) {
                region.getProvinciaCollection().remove(provincia);
                region = em.merge(region);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
