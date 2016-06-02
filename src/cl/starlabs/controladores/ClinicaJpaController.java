/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Clinica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Sucursal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class ClinicaJpaController implements Serializable {

    public ClinicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clinica clinica) throws PreexistingEntityException, Exception {
        if (clinica.getSucursalCollection() == null) {
            clinica.setSucursalCollection(new ArrayList<Sucursal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Sucursal> attachedSucursalCollection = new ArrayList<Sucursal>();
            for (Sucursal sucursalCollectionSucursalToAttach : clinica.getSucursalCollection()) {
                sucursalCollectionSucursalToAttach = em.getReference(sucursalCollectionSucursalToAttach.getClass(), sucursalCollectionSucursalToAttach.getIdSucursal());
                attachedSucursalCollection.add(sucursalCollectionSucursalToAttach);
            }
            clinica.setSucursalCollection(attachedSucursalCollection);
            em.persist(clinica);
            for (Sucursal sucursalCollectionSucursal : clinica.getSucursalCollection()) {
                Clinica oldClinicaOfSucursalCollectionSucursal = sucursalCollectionSucursal.getClinica();
                sucursalCollectionSucursal.setClinica(clinica);
                sucursalCollectionSucursal = em.merge(sucursalCollectionSucursal);
                if (oldClinicaOfSucursalCollectionSucursal != null) {
                    oldClinicaOfSucursalCollectionSucursal.getSucursalCollection().remove(sucursalCollectionSucursal);
                    oldClinicaOfSucursalCollectionSucursal = em.merge(oldClinicaOfSucursalCollectionSucursal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClinica(clinica.getIdClinica()) != null) {
                throw new PreexistingEntityException("Clinica " + clinica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clinica clinica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clinica persistentClinica = em.find(Clinica.class, clinica.getIdClinica());
            Collection<Sucursal> sucursalCollectionOld = persistentClinica.getSucursalCollection();
            Collection<Sucursal> sucursalCollectionNew = clinica.getSucursalCollection();
            List<String> illegalOrphanMessages = null;
            for (Sucursal sucursalCollectionOldSucursal : sucursalCollectionOld) {
                if (!sucursalCollectionNew.contains(sucursalCollectionOldSucursal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sucursal " + sucursalCollectionOldSucursal + " since its clinica field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Sucursal> attachedSucursalCollectionNew = new ArrayList<Sucursal>();
            for (Sucursal sucursalCollectionNewSucursalToAttach : sucursalCollectionNew) {
                sucursalCollectionNewSucursalToAttach = em.getReference(sucursalCollectionNewSucursalToAttach.getClass(), sucursalCollectionNewSucursalToAttach.getIdSucursal());
                attachedSucursalCollectionNew.add(sucursalCollectionNewSucursalToAttach);
            }
            sucursalCollectionNew = attachedSucursalCollectionNew;
            clinica.setSucursalCollection(sucursalCollectionNew);
            clinica = em.merge(clinica);
            for (Sucursal sucursalCollectionNewSucursal : sucursalCollectionNew) {
                if (!sucursalCollectionOld.contains(sucursalCollectionNewSucursal)) {
                    Clinica oldClinicaOfSucursalCollectionNewSucursal = sucursalCollectionNewSucursal.getClinica();
                    sucursalCollectionNewSucursal.setClinica(clinica);
                    sucursalCollectionNewSucursal = em.merge(sucursalCollectionNewSucursal);
                    if (oldClinicaOfSucursalCollectionNewSucursal != null && !oldClinicaOfSucursalCollectionNewSucursal.equals(clinica)) {
                        oldClinicaOfSucursalCollectionNewSucursal.getSucursalCollection().remove(sucursalCollectionNewSucursal);
                        oldClinicaOfSucursalCollectionNewSucursal = em.merge(oldClinicaOfSucursalCollectionNewSucursal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clinica.getIdClinica();
                if (findClinica(id) == null) {
                    throw new NonexistentEntityException("The clinica with id " + id + " no longer exists.");
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
            Clinica clinica;
            try {
                clinica = em.getReference(Clinica.class, id);
                clinica.getIdClinica();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clinica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Sucursal> sucursalCollectionOrphanCheck = clinica.getSucursalCollection();
            for (Sucursal sucursalCollectionOrphanCheckSucursal : sucursalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clinica (" + clinica + ") cannot be destroyed since the Sucursal " + sucursalCollectionOrphanCheckSucursal + " in its sucursalCollection field has a non-nullable clinica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(clinica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clinica> findClinicaEntities() {
        return findClinicaEntities(true, -1, -1);
    }

    public List<Clinica> findClinicaEntities(int maxResults, int firstResult) {
        return findClinicaEntities(false, maxResults, firstResult);
    }

    private List<Clinica> findClinicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clinica.class));
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

    public Clinica findClinica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clinica.class, id);
        } finally {
            em.close();
        }
    }

    public int getClinicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clinica> rt = cq.from(Clinica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
