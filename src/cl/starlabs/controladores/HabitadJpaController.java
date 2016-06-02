/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Habitad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Mascota;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class HabitadJpaController implements Serializable {

    public HabitadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitad habitad) throws PreexistingEntityException, Exception {
        if (habitad.getMascotaCollection() == null) {
            habitad.setMascotaCollection(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mascota> attachedMascotaCollection = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionMascotaToAttach : habitad.getMascotaCollection()) {
                mascotaCollectionMascotaToAttach = em.getReference(mascotaCollectionMascotaToAttach.getClass(), mascotaCollectionMascotaToAttach.getIdMascota());
                attachedMascotaCollection.add(mascotaCollectionMascotaToAttach);
            }
            habitad.setMascotaCollection(attachedMascotaCollection);
            em.persist(habitad);
            for (Mascota mascotaCollectionMascota : habitad.getMascotaCollection()) {
                Habitad oldHabitadOfMascotaCollectionMascota = mascotaCollectionMascota.getHabitad();
                mascotaCollectionMascota.setHabitad(habitad);
                mascotaCollectionMascota = em.merge(mascotaCollectionMascota);
                if (oldHabitadOfMascotaCollectionMascota != null) {
                    oldHabitadOfMascotaCollectionMascota.getMascotaCollection().remove(mascotaCollectionMascota);
                    oldHabitadOfMascotaCollectionMascota = em.merge(oldHabitadOfMascotaCollectionMascota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabitad(habitad.getIdHabitad()) != null) {
                throw new PreexistingEntityException("Habitad " + habitad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitad habitad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitad persistentHabitad = em.find(Habitad.class, habitad.getIdHabitad());
            Collection<Mascota> mascotaCollectionOld = persistentHabitad.getMascotaCollection();
            Collection<Mascota> mascotaCollectionNew = habitad.getMascotaCollection();
            List<String> illegalOrphanMessages = null;
            for (Mascota mascotaCollectionOldMascota : mascotaCollectionOld) {
                if (!mascotaCollectionNew.contains(mascotaCollectionOldMascota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mascota " + mascotaCollectionOldMascota + " since its habitad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Mascota> attachedMascotaCollectionNew = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionNewMascotaToAttach : mascotaCollectionNew) {
                mascotaCollectionNewMascotaToAttach = em.getReference(mascotaCollectionNewMascotaToAttach.getClass(), mascotaCollectionNewMascotaToAttach.getIdMascota());
                attachedMascotaCollectionNew.add(mascotaCollectionNewMascotaToAttach);
            }
            mascotaCollectionNew = attachedMascotaCollectionNew;
            habitad.setMascotaCollection(mascotaCollectionNew);
            habitad = em.merge(habitad);
            for (Mascota mascotaCollectionNewMascota : mascotaCollectionNew) {
                if (!mascotaCollectionOld.contains(mascotaCollectionNewMascota)) {
                    Habitad oldHabitadOfMascotaCollectionNewMascota = mascotaCollectionNewMascota.getHabitad();
                    mascotaCollectionNewMascota.setHabitad(habitad);
                    mascotaCollectionNewMascota = em.merge(mascotaCollectionNewMascota);
                    if (oldHabitadOfMascotaCollectionNewMascota != null && !oldHabitadOfMascotaCollectionNewMascota.equals(habitad)) {
                        oldHabitadOfMascotaCollectionNewMascota.getMascotaCollection().remove(mascotaCollectionNewMascota);
                        oldHabitadOfMascotaCollectionNewMascota = em.merge(oldHabitadOfMascotaCollectionNewMascota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = habitad.getIdHabitad();
                if (findHabitad(id) == null) {
                    throw new NonexistentEntityException("The habitad with id " + id + " no longer exists.");
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
            Habitad habitad;
            try {
                habitad = em.getReference(Habitad.class, id);
                habitad.getIdHabitad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mascota> mascotaCollectionOrphanCheck = habitad.getMascotaCollection();
            for (Mascota mascotaCollectionOrphanCheckMascota : mascotaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habitad (" + habitad + ") cannot be destroyed since the Mascota " + mascotaCollectionOrphanCheckMascota + " in its mascotaCollection field has a non-nullable habitad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(habitad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitad> findHabitadEntities() {
        return findHabitadEntities(true, -1, -1);
    }

    public List<Habitad> findHabitadEntities(int maxResults, int firstResult) {
        return findHabitadEntities(false, maxResults, firstResult);
    }

    private List<Habitad> findHabitadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitad.class));
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

    public Habitad findHabitad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitad.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitad> rt = cq.from(Habitad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
