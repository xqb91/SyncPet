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
import cl.starlabs.modelo.Especie;
import cl.starlabs.modelo.Mascota;
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
public class RazaJpaController implements Serializable {

    public RazaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Raza raza) throws PreexistingEntityException, Exception {
        if (raza.getMascotaCollection() == null) {
            raza.setMascotaCollection(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especie especie = raza.getEspecie();
            if (especie != null) {
                especie = em.getReference(especie.getClass(), especie.getIdEspecie());
                raza.setEspecie(especie);
            }
            Collection<Mascota> attachedMascotaCollection = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionMascotaToAttach : raza.getMascotaCollection()) {
                mascotaCollectionMascotaToAttach = em.getReference(mascotaCollectionMascotaToAttach.getClass(), mascotaCollectionMascotaToAttach.getIdMascota());
                attachedMascotaCollection.add(mascotaCollectionMascotaToAttach);
            }
            raza.setMascotaCollection(attachedMascotaCollection);
            em.persist(raza);
            if (especie != null) {
                especie.getRazaCollection().add(raza);
                especie = em.merge(especie);
            }
            for (Mascota mascotaCollectionMascota : raza.getMascotaCollection()) {
                Raza oldRazaOfMascotaCollectionMascota = mascotaCollectionMascota.getRaza();
                mascotaCollectionMascota.setRaza(raza);
                mascotaCollectionMascota = em.merge(mascotaCollectionMascota);
                if (oldRazaOfMascotaCollectionMascota != null) {
                    oldRazaOfMascotaCollectionMascota.getMascotaCollection().remove(mascotaCollectionMascota);
                    oldRazaOfMascotaCollectionMascota = em.merge(oldRazaOfMascotaCollectionMascota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRaza(raza.getIdRaza()) != null) {
                throw new PreexistingEntityException("Raza " + raza + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Raza raza) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Raza persistentRaza = em.find(Raza.class, raza.getIdRaza());
            Especie especieOld = persistentRaza.getEspecie();
            Especie especieNew = raza.getEspecie();
            Collection<Mascota> mascotaCollectionOld = persistentRaza.getMascotaCollection();
            Collection<Mascota> mascotaCollectionNew = raza.getMascotaCollection();
            List<String> illegalOrphanMessages = null;
            for (Mascota mascotaCollectionOldMascota : mascotaCollectionOld) {
                if (!mascotaCollectionNew.contains(mascotaCollectionOldMascota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mascota " + mascotaCollectionOldMascota + " since its raza field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (especieNew != null) {
                especieNew = em.getReference(especieNew.getClass(), especieNew.getIdEspecie());
                raza.setEspecie(especieNew);
            }
            Collection<Mascota> attachedMascotaCollectionNew = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionNewMascotaToAttach : mascotaCollectionNew) {
                mascotaCollectionNewMascotaToAttach = em.getReference(mascotaCollectionNewMascotaToAttach.getClass(), mascotaCollectionNewMascotaToAttach.getIdMascota());
                attachedMascotaCollectionNew.add(mascotaCollectionNewMascotaToAttach);
            }
            mascotaCollectionNew = attachedMascotaCollectionNew;
            raza.setMascotaCollection(mascotaCollectionNew);
            raza = em.merge(raza);
            if (especieOld != null && !especieOld.equals(especieNew)) {
                especieOld.getRazaCollection().remove(raza);
                especieOld = em.merge(especieOld);
            }
            if (especieNew != null && !especieNew.equals(especieOld)) {
                especieNew.getRazaCollection().add(raza);
                especieNew = em.merge(especieNew);
            }
            for (Mascota mascotaCollectionNewMascota : mascotaCollectionNew) {
                if (!mascotaCollectionOld.contains(mascotaCollectionNewMascota)) {
                    Raza oldRazaOfMascotaCollectionNewMascota = mascotaCollectionNewMascota.getRaza();
                    mascotaCollectionNewMascota.setRaza(raza);
                    mascotaCollectionNewMascota = em.merge(mascotaCollectionNewMascota);
                    if (oldRazaOfMascotaCollectionNewMascota != null && !oldRazaOfMascotaCollectionNewMascota.equals(raza)) {
                        oldRazaOfMascotaCollectionNewMascota.getMascotaCollection().remove(mascotaCollectionNewMascota);
                        oldRazaOfMascotaCollectionNewMascota = em.merge(oldRazaOfMascotaCollectionNewMascota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = raza.getIdRaza();
                if (findRaza(id) == null) {
                    throw new NonexistentEntityException("The raza with id " + id + " no longer exists.");
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
            Raza raza;
            try {
                raza = em.getReference(Raza.class, id);
                raza.getIdRaza();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The raza with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mascota> mascotaCollectionOrphanCheck = raza.getMascotaCollection();
            for (Mascota mascotaCollectionOrphanCheckMascota : mascotaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Raza (" + raza + ") cannot be destroyed since the Mascota " + mascotaCollectionOrphanCheckMascota + " in its mascotaCollection field has a non-nullable raza field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Especie especie = raza.getEspecie();
            if (especie != null) {
                especie.getRazaCollection().remove(raza);
                especie = em.merge(especie);
            }
            em.remove(raza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Raza> findRazaEntities() {
        return findRazaEntities(true, -1, -1);
    }

    public List<Raza> findRazaEntities(int maxResults, int firstResult) {
        return findRazaEntities(false, maxResults, firstResult);
    }

    private List<Raza> findRazaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Raza.class));
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

    public Raza findRaza(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Raza.class, id);
        } finally {
            em.close();
        }
    }

    public int getRazaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Raza> rt = cq.from(Raza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
