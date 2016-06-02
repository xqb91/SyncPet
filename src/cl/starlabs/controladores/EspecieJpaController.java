/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Especie;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Raza;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class EspecieJpaController implements Serializable {

    public EspecieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especie especie) throws PreexistingEntityException, Exception {
        if (especie.getRazaCollection() == null) {
            especie.setRazaCollection(new ArrayList<Raza>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Raza> attachedRazaCollection = new ArrayList<Raza>();
            for (Raza razaCollectionRazaToAttach : especie.getRazaCollection()) {
                razaCollectionRazaToAttach = em.getReference(razaCollectionRazaToAttach.getClass(), razaCollectionRazaToAttach.getIdRaza());
                attachedRazaCollection.add(razaCollectionRazaToAttach);
            }
            especie.setRazaCollection(attachedRazaCollection);
            em.persist(especie);
            for (Raza razaCollectionRaza : especie.getRazaCollection()) {
                Especie oldEspecieOfRazaCollectionRaza = razaCollectionRaza.getEspecie();
                razaCollectionRaza.setEspecie(especie);
                razaCollectionRaza = em.merge(razaCollectionRaza);
                if (oldEspecieOfRazaCollectionRaza != null) {
                    oldEspecieOfRazaCollectionRaza.getRazaCollection().remove(razaCollectionRaza);
                    oldEspecieOfRazaCollectionRaza = em.merge(oldEspecieOfRazaCollectionRaza);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEspecie(especie.getIdEspecie()) != null) {
                throw new PreexistingEntityException("Especie " + especie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especie especie) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especie persistentEspecie = em.find(Especie.class, especie.getIdEspecie());
            Collection<Raza> razaCollectionOld = persistentEspecie.getRazaCollection();
            Collection<Raza> razaCollectionNew = especie.getRazaCollection();
            List<String> illegalOrphanMessages = null;
            for (Raza razaCollectionOldRaza : razaCollectionOld) {
                if (!razaCollectionNew.contains(razaCollectionOldRaza)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Raza " + razaCollectionOldRaza + " since its especie field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Raza> attachedRazaCollectionNew = new ArrayList<Raza>();
            for (Raza razaCollectionNewRazaToAttach : razaCollectionNew) {
                razaCollectionNewRazaToAttach = em.getReference(razaCollectionNewRazaToAttach.getClass(), razaCollectionNewRazaToAttach.getIdRaza());
                attachedRazaCollectionNew.add(razaCollectionNewRazaToAttach);
            }
            razaCollectionNew = attachedRazaCollectionNew;
            especie.setRazaCollection(razaCollectionNew);
            especie = em.merge(especie);
            for (Raza razaCollectionNewRaza : razaCollectionNew) {
                if (!razaCollectionOld.contains(razaCollectionNewRaza)) {
                    Especie oldEspecieOfRazaCollectionNewRaza = razaCollectionNewRaza.getEspecie();
                    razaCollectionNewRaza.setEspecie(especie);
                    razaCollectionNewRaza = em.merge(razaCollectionNewRaza);
                    if (oldEspecieOfRazaCollectionNewRaza != null && !oldEspecieOfRazaCollectionNewRaza.equals(especie)) {
                        oldEspecieOfRazaCollectionNewRaza.getRazaCollection().remove(razaCollectionNewRaza);
                        oldEspecieOfRazaCollectionNewRaza = em.merge(oldEspecieOfRazaCollectionNewRaza);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especie.getIdEspecie();
                if (findEspecie(id) == null) {
                    throw new NonexistentEntityException("The especie with id " + id + " no longer exists.");
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
            Especie especie;
            try {
                especie = em.getReference(Especie.class, id);
                especie.getIdEspecie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especie with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Raza> razaCollectionOrphanCheck = especie.getRazaCollection();
            for (Raza razaCollectionOrphanCheckRaza : razaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Especie (" + especie + ") cannot be destroyed since the Raza " + razaCollectionOrphanCheckRaza + " in its razaCollection field has a non-nullable especie field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(especie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especie> findEspecieEntities() {
        return findEspecieEntities(true, -1, -1);
    }

    public List<Especie> findEspecieEntities(int maxResults, int firstResult) {
        return findEspecieEntities(false, maxResults, firstResult);
    }

    private List<Especie> findEspecieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especie.class));
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

    public Especie findEspecie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especie.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especie> rt = cq.from(Especie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
