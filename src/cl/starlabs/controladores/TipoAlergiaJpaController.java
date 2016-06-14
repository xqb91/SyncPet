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
import cl.starlabs.modelo.Alergias;
import cl.starlabs.modelo.TipoAlergia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Manuel Araya
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
        if (tipoAlergia.getAlergiasList() == null) {
            tipoAlergia.setAlergiasList(new ArrayList<Alergias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Alergias> attachedAlergiasList = new ArrayList<Alergias>();
            for (Alergias alergiasListAlergiasToAttach : tipoAlergia.getAlergiasList()) {
                alergiasListAlergiasToAttach = em.getReference(alergiasListAlergiasToAttach.getClass(), alergiasListAlergiasToAttach.getIdAlergia());
                attachedAlergiasList.add(alergiasListAlergiasToAttach);
            }
            tipoAlergia.setAlergiasList(attachedAlergiasList);
            em.persist(tipoAlergia);
            for (Alergias alergiasListAlergias : tipoAlergia.getAlergiasList()) {
                TipoAlergia oldIdTipoAlergiaOfAlergiasListAlergias = alergiasListAlergias.getIdTipoAlergia();
                alergiasListAlergias.setIdTipoAlergia(tipoAlergia);
                alergiasListAlergias = em.merge(alergiasListAlergias);
                if (oldIdTipoAlergiaOfAlergiasListAlergias != null) {
                    oldIdTipoAlergiaOfAlergiasListAlergias.getAlergiasList().remove(alergiasListAlergias);
                    oldIdTipoAlergiaOfAlergiasListAlergias = em.merge(oldIdTipoAlergiaOfAlergiasListAlergias);
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

    public void edit(TipoAlergia tipoAlergia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAlergia persistentTipoAlergia = em.find(TipoAlergia.class, tipoAlergia.getIdTipoAlergia());
            List<Alergias> alergiasListOld = persistentTipoAlergia.getAlergiasList();
            List<Alergias> alergiasListNew = tipoAlergia.getAlergiasList();
            List<String> illegalOrphanMessages = null;
            for (Alergias alergiasListOldAlergias : alergiasListOld) {
                if (!alergiasListNew.contains(alergiasListOldAlergias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alergias " + alergiasListOldAlergias + " since its idTipoAlergia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Alergias> attachedAlergiasListNew = new ArrayList<Alergias>();
            for (Alergias alergiasListNewAlergiasToAttach : alergiasListNew) {
                alergiasListNewAlergiasToAttach = em.getReference(alergiasListNewAlergiasToAttach.getClass(), alergiasListNewAlergiasToAttach.getIdAlergia());
                attachedAlergiasListNew.add(alergiasListNewAlergiasToAttach);
            }
            alergiasListNew = attachedAlergiasListNew;
            tipoAlergia.setAlergiasList(alergiasListNew);
            tipoAlergia = em.merge(tipoAlergia);
            for (Alergias alergiasListNewAlergias : alergiasListNew) {
                if (!alergiasListOld.contains(alergiasListNewAlergias)) {
                    TipoAlergia oldIdTipoAlergiaOfAlergiasListNewAlergias = alergiasListNewAlergias.getIdTipoAlergia();
                    alergiasListNewAlergias.setIdTipoAlergia(tipoAlergia);
                    alergiasListNewAlergias = em.merge(alergiasListNewAlergias);
                    if (oldIdTipoAlergiaOfAlergiasListNewAlergias != null && !oldIdTipoAlergiaOfAlergiasListNewAlergias.equals(tipoAlergia)) {
                        oldIdTipoAlergiaOfAlergiasListNewAlergias.getAlergiasList().remove(alergiasListNewAlergias);
                        oldIdTipoAlergiaOfAlergiasListNewAlergias = em.merge(oldIdTipoAlergiaOfAlergiasListNewAlergias);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Alergias> alergiasListOrphanCheck = tipoAlergia.getAlergiasList();
            for (Alergias alergiasListOrphanCheckAlergias : alergiasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoAlergia (" + tipoAlergia + ") cannot be destroyed since the Alergias " + alergiasListOrphanCheckAlergias + " in its alergiasList field has a non-nullable idTipoAlergia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
