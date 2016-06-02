/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.IllegalOrphanException;
import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Comuna;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Provincia;
import cl.starlabs.modelo.Sucursal;
import java.util.ArrayList;
import java.util.Collection;
import cl.starlabs.modelo.Propietario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class ComunaJpaController implements Serializable {

    public ComunaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comuna comuna) throws PreexistingEntityException, Exception {
        if (comuna.getSucursalCollection() == null) {
            comuna.setSucursalCollection(new ArrayList<Sucursal>());
        }
        if (comuna.getPropietarioCollection() == null) {
            comuna.setPropietarioCollection(new ArrayList<Propietario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia provincia = comuna.getProvincia();
            if (provincia != null) {
                provincia = em.getReference(provincia.getClass(), provincia.getIdProvincia());
                comuna.setProvincia(provincia);
            }
            Collection<Sucursal> attachedSucursalCollection = new ArrayList<Sucursal>();
            for (Sucursal sucursalCollectionSucursalToAttach : comuna.getSucursalCollection()) {
                sucursalCollectionSucursalToAttach = em.getReference(sucursalCollectionSucursalToAttach.getClass(), sucursalCollectionSucursalToAttach.getIdSucursal());
                attachedSucursalCollection.add(sucursalCollectionSucursalToAttach);
            }
            comuna.setSucursalCollection(attachedSucursalCollection);
            Collection<Propietario> attachedPropietarioCollection = new ArrayList<Propietario>();
            for (Propietario propietarioCollectionPropietarioToAttach : comuna.getPropietarioCollection()) {
                propietarioCollectionPropietarioToAttach = em.getReference(propietarioCollectionPropietarioToAttach.getClass(), propietarioCollectionPropietarioToAttach.getIdPropietario());
                attachedPropietarioCollection.add(propietarioCollectionPropietarioToAttach);
            }
            comuna.setPropietarioCollection(attachedPropietarioCollection);
            em.persist(comuna);
            if (provincia != null) {
                provincia.getComunaCollection().add(comuna);
                provincia = em.merge(provincia);
            }
            for (Sucursal sucursalCollectionSucursal : comuna.getSucursalCollection()) {
                Comuna oldComunaOfSucursalCollectionSucursal = sucursalCollectionSucursal.getComuna();
                sucursalCollectionSucursal.setComuna(comuna);
                sucursalCollectionSucursal = em.merge(sucursalCollectionSucursal);
                if (oldComunaOfSucursalCollectionSucursal != null) {
                    oldComunaOfSucursalCollectionSucursal.getSucursalCollection().remove(sucursalCollectionSucursal);
                    oldComunaOfSucursalCollectionSucursal = em.merge(oldComunaOfSucursalCollectionSucursal);
                }
            }
            for (Propietario propietarioCollectionPropietario : comuna.getPropietarioCollection()) {
                Comuna oldComunaOfPropietarioCollectionPropietario = propietarioCollectionPropietario.getComuna();
                propietarioCollectionPropietario.setComuna(comuna);
                propietarioCollectionPropietario = em.merge(propietarioCollectionPropietario);
                if (oldComunaOfPropietarioCollectionPropietario != null) {
                    oldComunaOfPropietarioCollectionPropietario.getPropietarioCollection().remove(propietarioCollectionPropietario);
                    oldComunaOfPropietarioCollectionPropietario = em.merge(oldComunaOfPropietarioCollectionPropietario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComuna(comuna.getIdComuna()) != null) {
                throw new PreexistingEntityException("Comuna " + comuna + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comuna comuna) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comuna persistentComuna = em.find(Comuna.class, comuna.getIdComuna());
            Provincia provinciaOld = persistentComuna.getProvincia();
            Provincia provinciaNew = comuna.getProvincia();
            Collection<Sucursal> sucursalCollectionOld = persistentComuna.getSucursalCollection();
            Collection<Sucursal> sucursalCollectionNew = comuna.getSucursalCollection();
            Collection<Propietario> propietarioCollectionOld = persistentComuna.getPropietarioCollection();
            Collection<Propietario> propietarioCollectionNew = comuna.getPropietarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Sucursal sucursalCollectionOldSucursal : sucursalCollectionOld) {
                if (!sucursalCollectionNew.contains(sucursalCollectionOldSucursal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sucursal " + sucursalCollectionOldSucursal + " since its comuna field is not nullable.");
                }
            }
            for (Propietario propietarioCollectionOldPropietario : propietarioCollectionOld) {
                if (!propietarioCollectionNew.contains(propietarioCollectionOldPropietario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Propietario " + propietarioCollectionOldPropietario + " since its comuna field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (provinciaNew != null) {
                provinciaNew = em.getReference(provinciaNew.getClass(), provinciaNew.getIdProvincia());
                comuna.setProvincia(provinciaNew);
            }
            Collection<Sucursal> attachedSucursalCollectionNew = new ArrayList<Sucursal>();
            for (Sucursal sucursalCollectionNewSucursalToAttach : sucursalCollectionNew) {
                sucursalCollectionNewSucursalToAttach = em.getReference(sucursalCollectionNewSucursalToAttach.getClass(), sucursalCollectionNewSucursalToAttach.getIdSucursal());
                attachedSucursalCollectionNew.add(sucursalCollectionNewSucursalToAttach);
            }
            sucursalCollectionNew = attachedSucursalCollectionNew;
            comuna.setSucursalCollection(sucursalCollectionNew);
            Collection<Propietario> attachedPropietarioCollectionNew = new ArrayList<Propietario>();
            for (Propietario propietarioCollectionNewPropietarioToAttach : propietarioCollectionNew) {
                propietarioCollectionNewPropietarioToAttach = em.getReference(propietarioCollectionNewPropietarioToAttach.getClass(), propietarioCollectionNewPropietarioToAttach.getIdPropietario());
                attachedPropietarioCollectionNew.add(propietarioCollectionNewPropietarioToAttach);
            }
            propietarioCollectionNew = attachedPropietarioCollectionNew;
            comuna.setPropietarioCollection(propietarioCollectionNew);
            comuna = em.merge(comuna);
            if (provinciaOld != null && !provinciaOld.equals(provinciaNew)) {
                provinciaOld.getComunaCollection().remove(comuna);
                provinciaOld = em.merge(provinciaOld);
            }
            if (provinciaNew != null && !provinciaNew.equals(provinciaOld)) {
                provinciaNew.getComunaCollection().add(comuna);
                provinciaNew = em.merge(provinciaNew);
            }
            for (Sucursal sucursalCollectionNewSucursal : sucursalCollectionNew) {
                if (!sucursalCollectionOld.contains(sucursalCollectionNewSucursal)) {
                    Comuna oldComunaOfSucursalCollectionNewSucursal = sucursalCollectionNewSucursal.getComuna();
                    sucursalCollectionNewSucursal.setComuna(comuna);
                    sucursalCollectionNewSucursal = em.merge(sucursalCollectionNewSucursal);
                    if (oldComunaOfSucursalCollectionNewSucursal != null && !oldComunaOfSucursalCollectionNewSucursal.equals(comuna)) {
                        oldComunaOfSucursalCollectionNewSucursal.getSucursalCollection().remove(sucursalCollectionNewSucursal);
                        oldComunaOfSucursalCollectionNewSucursal = em.merge(oldComunaOfSucursalCollectionNewSucursal);
                    }
                }
            }
            for (Propietario propietarioCollectionNewPropietario : propietarioCollectionNew) {
                if (!propietarioCollectionOld.contains(propietarioCollectionNewPropietario)) {
                    Comuna oldComunaOfPropietarioCollectionNewPropietario = propietarioCollectionNewPropietario.getComuna();
                    propietarioCollectionNewPropietario.setComuna(comuna);
                    propietarioCollectionNewPropietario = em.merge(propietarioCollectionNewPropietario);
                    if (oldComunaOfPropietarioCollectionNewPropietario != null && !oldComunaOfPropietarioCollectionNewPropietario.equals(comuna)) {
                        oldComunaOfPropietarioCollectionNewPropietario.getPropietarioCollection().remove(propietarioCollectionNewPropietario);
                        oldComunaOfPropietarioCollectionNewPropietario = em.merge(oldComunaOfPropietarioCollectionNewPropietario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comuna.getIdComuna();
                if (findComuna(id) == null) {
                    throw new NonexistentEntityException("The comuna with id " + id + " no longer exists.");
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
            Comuna comuna;
            try {
                comuna = em.getReference(Comuna.class, id);
                comuna.getIdComuna();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comuna with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Sucursal> sucursalCollectionOrphanCheck = comuna.getSucursalCollection();
            for (Sucursal sucursalCollectionOrphanCheckSucursal : sucursalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comuna (" + comuna + ") cannot be destroyed since the Sucursal " + sucursalCollectionOrphanCheckSucursal + " in its sucursalCollection field has a non-nullable comuna field.");
            }
            Collection<Propietario> propietarioCollectionOrphanCheck = comuna.getPropietarioCollection();
            for (Propietario propietarioCollectionOrphanCheckPropietario : propietarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comuna (" + comuna + ") cannot be destroyed since the Propietario " + propietarioCollectionOrphanCheckPropietario + " in its propietarioCollection field has a non-nullable comuna field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provincia provincia = comuna.getProvincia();
            if (provincia != null) {
                provincia.getComunaCollection().remove(comuna);
                provincia = em.merge(provincia);
            }
            em.remove(comuna);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comuna> findComunaEntities() {
        return findComunaEntities(true, -1, -1);
    }

    public List<Comuna> findComunaEntities(int maxResults, int firstResult) {
        return findComunaEntities(false, maxResults, firstResult);
    }

    private List<Comuna> findComunaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comuna.class));
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

    public Comuna findComuna(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comuna.class, id);
        } finally {
            em.close();
        }
    }

    public int getComunaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comuna> rt = cq.from(Comuna.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
