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
import cl.starlabs.modelo.Historialvacunas;
import cl.starlabs.modelo.Vacunas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class VacunasJpaController implements Serializable {

    public VacunasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacunas vacunas) throws PreexistingEntityException, Exception {
        if (vacunas.getHistorialvacunasCollection() == null) {
            vacunas.setHistorialvacunasCollection(new ArrayList<Historialvacunas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Historialvacunas> attachedHistorialvacunasCollection = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionHistorialvacunasToAttach : vacunas.getHistorialvacunasCollection()) {
                historialvacunasCollectionHistorialvacunasToAttach = em.getReference(historialvacunasCollectionHistorialvacunasToAttach.getClass(), historialvacunasCollectionHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollection.add(historialvacunasCollectionHistorialvacunasToAttach);
            }
            vacunas.setHistorialvacunasCollection(attachedHistorialvacunasCollection);
            em.persist(vacunas);
            for (Historialvacunas historialvacunasCollectionHistorialvacunas : vacunas.getHistorialvacunasCollection()) {
                Vacunas oldVacunaOfHistorialvacunasCollectionHistorialvacunas = historialvacunasCollectionHistorialvacunas.getVacuna();
                historialvacunasCollectionHistorialvacunas.setVacuna(vacunas);
                historialvacunasCollectionHistorialvacunas = em.merge(historialvacunasCollectionHistorialvacunas);
                if (oldVacunaOfHistorialvacunasCollectionHistorialvacunas != null) {
                    oldVacunaOfHistorialvacunasCollectionHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionHistorialvacunas);
                    oldVacunaOfHistorialvacunasCollectionHistorialvacunas = em.merge(oldVacunaOfHistorialvacunasCollectionHistorialvacunas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVacunas(vacunas.getIdVacuna()) != null) {
                throw new PreexistingEntityException("Vacunas " + vacunas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vacunas vacunas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacunas persistentVacunas = em.find(Vacunas.class, vacunas.getIdVacuna());
            Collection<Historialvacunas> historialvacunasCollectionOld = persistentVacunas.getHistorialvacunasCollection();
            Collection<Historialvacunas> historialvacunasCollectionNew = vacunas.getHistorialvacunasCollection();
            List<String> illegalOrphanMessages = null;
            for (Historialvacunas historialvacunasCollectionOldHistorialvacunas : historialvacunasCollectionOld) {
                if (!historialvacunasCollectionNew.contains(historialvacunasCollectionOldHistorialvacunas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialvacunas " + historialvacunasCollectionOldHistorialvacunas + " since its vacuna field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Historialvacunas> attachedHistorialvacunasCollectionNew = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunasToAttach : historialvacunasCollectionNew) {
                historialvacunasCollectionNewHistorialvacunasToAttach = em.getReference(historialvacunasCollectionNewHistorialvacunasToAttach.getClass(), historialvacunasCollectionNewHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollectionNew.add(historialvacunasCollectionNewHistorialvacunasToAttach);
            }
            historialvacunasCollectionNew = attachedHistorialvacunasCollectionNew;
            vacunas.setHistorialvacunasCollection(historialvacunasCollectionNew);
            vacunas = em.merge(vacunas);
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunas : historialvacunasCollectionNew) {
                if (!historialvacunasCollectionOld.contains(historialvacunasCollectionNewHistorialvacunas)) {
                    Vacunas oldVacunaOfHistorialvacunasCollectionNewHistorialvacunas = historialvacunasCollectionNewHistorialvacunas.getVacuna();
                    historialvacunasCollectionNewHistorialvacunas.setVacuna(vacunas);
                    historialvacunasCollectionNewHistorialvacunas = em.merge(historialvacunasCollectionNewHistorialvacunas);
                    if (oldVacunaOfHistorialvacunasCollectionNewHistorialvacunas != null && !oldVacunaOfHistorialvacunasCollectionNewHistorialvacunas.equals(vacunas)) {
                        oldVacunaOfHistorialvacunasCollectionNewHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionNewHistorialvacunas);
                        oldVacunaOfHistorialvacunasCollectionNewHistorialvacunas = em.merge(oldVacunaOfHistorialvacunasCollectionNewHistorialvacunas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vacunas.getIdVacuna();
                if (findVacunas(id) == null) {
                    throw new NonexistentEntityException("The vacunas with id " + id + " no longer exists.");
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
            Vacunas vacunas;
            try {
                vacunas = em.getReference(Vacunas.class, id);
                vacunas.getIdVacuna();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacunas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historialvacunas> historialvacunasCollectionOrphanCheck = vacunas.getHistorialvacunasCollection();
            for (Historialvacunas historialvacunasCollectionOrphanCheckHistorialvacunas : historialvacunasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vacunas (" + vacunas + ") cannot be destroyed since the Historialvacunas " + historialvacunasCollectionOrphanCheckHistorialvacunas + " in its historialvacunasCollection field has a non-nullable vacuna field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vacunas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vacunas> findVacunasEntities() {
        return findVacunasEntities(true, -1, -1);
    }

    public List<Vacunas> findVacunasEntities(int maxResults, int firstResult) {
        return findVacunasEntities(false, maxResults, firstResult);
    }

    private List<Vacunas> findVacunasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vacunas.class));
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

    public Vacunas findVacunas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacunas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacunasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vacunas> rt = cq.from(Vacunas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
