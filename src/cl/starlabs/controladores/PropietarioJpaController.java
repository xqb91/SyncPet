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
import cl.starlabs.modelo.Comuna;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.Propietario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class PropietarioJpaController implements Serializable {

    public PropietarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propietario propietario) throws PreexistingEntityException, Exception {
        if (propietario.getMascotaCollection() == null) {
            propietario.setMascotaCollection(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comuna comuna = propietario.getComuna();
            if (comuna != null) {
                comuna = em.getReference(comuna.getClass(), comuna.getIdComuna());
                propietario.setComuna(comuna);
            }
            Sucursal sucursal = propietario.getSucursal();
            if (sucursal != null) {
                sucursal = em.getReference(sucursal.getClass(), sucursal.getIdSucursal());
                propietario.setSucursal(sucursal);
            }
            Collection<Mascota> attachedMascotaCollection = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionMascotaToAttach : propietario.getMascotaCollection()) {
                mascotaCollectionMascotaToAttach = em.getReference(mascotaCollectionMascotaToAttach.getClass(), mascotaCollectionMascotaToAttach.getIdMascota());
                attachedMascotaCollection.add(mascotaCollectionMascotaToAttach);
            }
            propietario.setMascotaCollection(attachedMascotaCollection);
            em.persist(propietario);
            if (comuna != null) {
                comuna.getPropietarioCollection().add(propietario);
                comuna = em.merge(comuna);
            }
            if (sucursal != null) {
                sucursal.getPropietarioCollection().add(propietario);
                sucursal = em.merge(sucursal);
            }
            for (Mascota mascotaCollectionMascota : propietario.getMascotaCollection()) {
                Propietario oldPropietarioOfMascotaCollectionMascota = mascotaCollectionMascota.getPropietario();
                mascotaCollectionMascota.setPropietario(propietario);
                mascotaCollectionMascota = em.merge(mascotaCollectionMascota);
                if (oldPropietarioOfMascotaCollectionMascota != null) {
                    oldPropietarioOfMascotaCollectionMascota.getMascotaCollection().remove(mascotaCollectionMascota);
                    oldPropietarioOfMascotaCollectionMascota = em.merge(oldPropietarioOfMascotaCollectionMascota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPropietario(propietario.getIdPropietario()) != null) {
                throw new PreexistingEntityException("Propietario " + propietario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propietario propietario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propietario persistentPropietario = em.find(Propietario.class, propietario.getIdPropietario());
            Comuna comunaOld = persistentPropietario.getComuna();
            Comuna comunaNew = propietario.getComuna();
            Sucursal sucursalOld = persistentPropietario.getSucursal();
            Sucursal sucursalNew = propietario.getSucursal();
            Collection<Mascota> mascotaCollectionOld = persistentPropietario.getMascotaCollection();
            Collection<Mascota> mascotaCollectionNew = propietario.getMascotaCollection();
            List<String> illegalOrphanMessages = null;
            for (Mascota mascotaCollectionOldMascota : mascotaCollectionOld) {
                if (!mascotaCollectionNew.contains(mascotaCollectionOldMascota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mascota " + mascotaCollectionOldMascota + " since its propietario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (comunaNew != null) {
                comunaNew = em.getReference(comunaNew.getClass(), comunaNew.getIdComuna());
                propietario.setComuna(comunaNew);
            }
            if (sucursalNew != null) {
                sucursalNew = em.getReference(sucursalNew.getClass(), sucursalNew.getIdSucursal());
                propietario.setSucursal(sucursalNew);
            }
            Collection<Mascota> attachedMascotaCollectionNew = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionNewMascotaToAttach : mascotaCollectionNew) {
                mascotaCollectionNewMascotaToAttach = em.getReference(mascotaCollectionNewMascotaToAttach.getClass(), mascotaCollectionNewMascotaToAttach.getIdMascota());
                attachedMascotaCollectionNew.add(mascotaCollectionNewMascotaToAttach);
            }
            mascotaCollectionNew = attachedMascotaCollectionNew;
            propietario.setMascotaCollection(mascotaCollectionNew);
            propietario = em.merge(propietario);
            if (comunaOld != null && !comunaOld.equals(comunaNew)) {
                comunaOld.getPropietarioCollection().remove(propietario);
                comunaOld = em.merge(comunaOld);
            }
            if (comunaNew != null && !comunaNew.equals(comunaOld)) {
                comunaNew.getPropietarioCollection().add(propietario);
                comunaNew = em.merge(comunaNew);
            }
            if (sucursalOld != null && !sucursalOld.equals(sucursalNew)) {
                sucursalOld.getPropietarioCollection().remove(propietario);
                sucursalOld = em.merge(sucursalOld);
            }
            if (sucursalNew != null && !sucursalNew.equals(sucursalOld)) {
                sucursalNew.getPropietarioCollection().add(propietario);
                sucursalNew = em.merge(sucursalNew);
            }
            for (Mascota mascotaCollectionNewMascota : mascotaCollectionNew) {
                if (!mascotaCollectionOld.contains(mascotaCollectionNewMascota)) {
                    Propietario oldPropietarioOfMascotaCollectionNewMascota = mascotaCollectionNewMascota.getPropietario();
                    mascotaCollectionNewMascota.setPropietario(propietario);
                    mascotaCollectionNewMascota = em.merge(mascotaCollectionNewMascota);
                    if (oldPropietarioOfMascotaCollectionNewMascota != null && !oldPropietarioOfMascotaCollectionNewMascota.equals(propietario)) {
                        oldPropietarioOfMascotaCollectionNewMascota.getMascotaCollection().remove(mascotaCollectionNewMascota);
                        oldPropietarioOfMascotaCollectionNewMascota = em.merge(oldPropietarioOfMascotaCollectionNewMascota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = propietario.getIdPropietario();
                if (findPropietario(id) == null) {
                    throw new NonexistentEntityException("The propietario with id " + id + " no longer exists.");
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
            Propietario propietario;
            try {
                propietario = em.getReference(Propietario.class, id);
                propietario.getIdPropietario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propietario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mascota> mascotaCollectionOrphanCheck = propietario.getMascotaCollection();
            for (Mascota mascotaCollectionOrphanCheckMascota : mascotaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propietario (" + propietario + ") cannot be destroyed since the Mascota " + mascotaCollectionOrphanCheckMascota + " in its mascotaCollection field has a non-nullable propietario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Comuna comuna = propietario.getComuna();
            if (comuna != null) {
                comuna.getPropietarioCollection().remove(propietario);
                comuna = em.merge(comuna);
            }
            Sucursal sucursal = propietario.getSucursal();
            if (sucursal != null) {
                sucursal.getPropietarioCollection().remove(propietario);
                sucursal = em.merge(sucursal);
            }
            em.remove(propietario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propietario> findPropietarioEntities() {
        return findPropietarioEntities(true, -1, -1);
    }

    public List<Propietario> findPropietarioEntities(int maxResults, int firstResult) {
        return findPropietarioEntities(false, maxResults, firstResult);
    }

    private List<Propietario> findPropietarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propietario.class));
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

    public Propietario findPropietario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propietario.class, id);
        } finally {
            em.close();
        }
    }

    public int getPropietarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propietario> rt = cq.from(Propietario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
