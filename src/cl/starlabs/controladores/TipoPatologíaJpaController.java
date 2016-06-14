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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Manuel Araya
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
        if (tipoPatología.getPatologiasList() == null) {
            tipoPatología.setPatologiasList(new ArrayList<Patologias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Patologias> attachedPatologiasList = new ArrayList<Patologias>();
            for (Patologias patologiasListPatologiasToAttach : tipoPatología.getPatologiasList()) {
                patologiasListPatologiasToAttach = em.getReference(patologiasListPatologiasToAttach.getClass(), patologiasListPatologiasToAttach.getIdPatologia());
                attachedPatologiasList.add(patologiasListPatologiasToAttach);
            }
            tipoPatología.setPatologiasList(attachedPatologiasList);
            em.persist(tipoPatología);
            for (Patologias patologiasListPatologias : tipoPatología.getPatologiasList()) {
                TipoPatología oldTipoPatologiaOfPatologiasListPatologias = patologiasListPatologias.getTipoPatologia();
                patologiasListPatologias.setTipoPatologia(tipoPatología);
                patologiasListPatologias = em.merge(patologiasListPatologias);
                if (oldTipoPatologiaOfPatologiasListPatologias != null) {
                    oldTipoPatologiaOfPatologiasListPatologias.getPatologiasList().remove(patologiasListPatologias);
                    oldTipoPatologiaOfPatologiasListPatologias = em.merge(oldTipoPatologiaOfPatologiasListPatologias);
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
            List<Patologias> patologiasListOld = persistentTipoPatología.getPatologiasList();
            List<Patologias> patologiasListNew = tipoPatología.getPatologiasList();
            List<String> illegalOrphanMessages = null;
            for (Patologias patologiasListOldPatologias : patologiasListOld) {
                if (!patologiasListNew.contains(patologiasListOldPatologias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Patologias " + patologiasListOldPatologias + " since its tipoPatologia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Patologias> attachedPatologiasListNew = new ArrayList<Patologias>();
            for (Patologias patologiasListNewPatologiasToAttach : patologiasListNew) {
                patologiasListNewPatologiasToAttach = em.getReference(patologiasListNewPatologiasToAttach.getClass(), patologiasListNewPatologiasToAttach.getIdPatologia());
                attachedPatologiasListNew.add(patologiasListNewPatologiasToAttach);
            }
            patologiasListNew = attachedPatologiasListNew;
            tipoPatología.setPatologiasList(patologiasListNew);
            tipoPatología = em.merge(tipoPatología);
            for (Patologias patologiasListNewPatologias : patologiasListNew) {
                if (!patologiasListOld.contains(patologiasListNewPatologias)) {
                    TipoPatología oldTipoPatologiaOfPatologiasListNewPatologias = patologiasListNewPatologias.getTipoPatologia();
                    patologiasListNewPatologias.setTipoPatologia(tipoPatología);
                    patologiasListNewPatologias = em.merge(patologiasListNewPatologias);
                    if (oldTipoPatologiaOfPatologiasListNewPatologias != null && !oldTipoPatologiaOfPatologiasListNewPatologias.equals(tipoPatología)) {
                        oldTipoPatologiaOfPatologiasListNewPatologias.getPatologiasList().remove(patologiasListNewPatologias);
                        oldTipoPatologiaOfPatologiasListNewPatologias = em.merge(oldTipoPatologiaOfPatologiasListNewPatologias);
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
            List<Patologias> patologiasListOrphanCheck = tipoPatología.getPatologiasList();
            for (Patologias patologiasListOrphanCheckPatologias : patologiasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoPatolog\u00eda (" + tipoPatología + ") cannot be destroyed since the Patologias " + patologiasListOrphanCheckPatologias + " in its patologiasList field has a non-nullable tipoPatologia field.");
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
