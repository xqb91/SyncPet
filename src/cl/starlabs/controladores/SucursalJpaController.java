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
import cl.starlabs.modelo.Clinica;
import cl.starlabs.modelo.Comuna;
import cl.starlabs.modelo.Agenda;
import java.util.ArrayList;
import java.util.Collection;
import cl.starlabs.modelo.Propietario;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Veterinario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class SucursalJpaController implements Serializable {

    public SucursalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sucursal sucursal) throws PreexistingEntityException, Exception {
        if (sucursal.getAgendaCollection() == null) {
            sucursal.setAgendaCollection(new ArrayList<Agenda>());
        }
        if (sucursal.getPropietarioCollection() == null) {
            sucursal.setPropietarioCollection(new ArrayList<Propietario>());
        }
        if (sucursal.getVeterinarioCollection() == null) {
            sucursal.setVeterinarioCollection(new ArrayList<Veterinario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clinica clinica = sucursal.getClinica();
            if (clinica != null) {
                clinica = em.getReference(clinica.getClass(), clinica.getIdClinica());
                sucursal.setClinica(clinica);
            }
            Comuna comuna = sucursal.getComuna();
            if (comuna != null) {
                comuna = em.getReference(comuna.getClass(), comuna.getIdComuna());
                sucursal.setComuna(comuna);
            }
            Collection<Agenda> attachedAgendaCollection = new ArrayList<Agenda>();
            for (Agenda agendaCollectionAgendaToAttach : sucursal.getAgendaCollection()) {
                agendaCollectionAgendaToAttach = em.getReference(agendaCollectionAgendaToAttach.getClass(), agendaCollectionAgendaToAttach.getIdEvento());
                attachedAgendaCollection.add(agendaCollectionAgendaToAttach);
            }
            sucursal.setAgendaCollection(attachedAgendaCollection);
            Collection<Propietario> attachedPropietarioCollection = new ArrayList<Propietario>();
            for (Propietario propietarioCollectionPropietarioToAttach : sucursal.getPropietarioCollection()) {
                propietarioCollectionPropietarioToAttach = em.getReference(propietarioCollectionPropietarioToAttach.getClass(), propietarioCollectionPropietarioToAttach.getIdPropietario());
                attachedPropietarioCollection.add(propietarioCollectionPropietarioToAttach);
            }
            sucursal.setPropietarioCollection(attachedPropietarioCollection);
            Collection<Veterinario> attachedVeterinarioCollection = new ArrayList<Veterinario>();
            for (Veterinario veterinarioCollectionVeterinarioToAttach : sucursal.getVeterinarioCollection()) {
                veterinarioCollectionVeterinarioToAttach = em.getReference(veterinarioCollectionVeterinarioToAttach.getClass(), veterinarioCollectionVeterinarioToAttach.getIdVeterinario());
                attachedVeterinarioCollection.add(veterinarioCollectionVeterinarioToAttach);
            }
            sucursal.setVeterinarioCollection(attachedVeterinarioCollection);
            em.persist(sucursal);
            if (clinica != null) {
                clinica.getSucursalCollection().add(sucursal);
                clinica = em.merge(clinica);
            }
            if (comuna != null) {
                comuna.getSucursalCollection().add(sucursal);
                comuna = em.merge(comuna);
            }
            for (Agenda agendaCollectionAgenda : sucursal.getAgendaCollection()) {
                Sucursal oldSucursalOfAgendaCollectionAgenda = agendaCollectionAgenda.getSucursal();
                agendaCollectionAgenda.setSucursal(sucursal);
                agendaCollectionAgenda = em.merge(agendaCollectionAgenda);
                if (oldSucursalOfAgendaCollectionAgenda != null) {
                    oldSucursalOfAgendaCollectionAgenda.getAgendaCollection().remove(agendaCollectionAgenda);
                    oldSucursalOfAgendaCollectionAgenda = em.merge(oldSucursalOfAgendaCollectionAgenda);
                }
            }
            for (Propietario propietarioCollectionPropietario : sucursal.getPropietarioCollection()) {
                Sucursal oldSucursalOfPropietarioCollectionPropietario = propietarioCollectionPropietario.getSucursal();
                propietarioCollectionPropietario.setSucursal(sucursal);
                propietarioCollectionPropietario = em.merge(propietarioCollectionPropietario);
                if (oldSucursalOfPropietarioCollectionPropietario != null) {
                    oldSucursalOfPropietarioCollectionPropietario.getPropietarioCollection().remove(propietarioCollectionPropietario);
                    oldSucursalOfPropietarioCollectionPropietario = em.merge(oldSucursalOfPropietarioCollectionPropietario);
                }
            }
            for (Veterinario veterinarioCollectionVeterinario : sucursal.getVeterinarioCollection()) {
                Sucursal oldSucursalOfVeterinarioCollectionVeterinario = veterinarioCollectionVeterinario.getSucursal();
                veterinarioCollectionVeterinario.setSucursal(sucursal);
                veterinarioCollectionVeterinario = em.merge(veterinarioCollectionVeterinario);
                if (oldSucursalOfVeterinarioCollectionVeterinario != null) {
                    oldSucursalOfVeterinarioCollectionVeterinario.getVeterinarioCollection().remove(veterinarioCollectionVeterinario);
                    oldSucursalOfVeterinarioCollectionVeterinario = em.merge(oldSucursalOfVeterinarioCollectionVeterinario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSucursal(sucursal.getIdSucursal()) != null) {
                throw new PreexistingEntityException("Sucursal " + sucursal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sucursal sucursal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sucursal persistentSucursal = em.find(Sucursal.class, sucursal.getIdSucursal());
            Clinica clinicaOld = persistentSucursal.getClinica();
            Clinica clinicaNew = sucursal.getClinica();
            Comuna comunaOld = persistentSucursal.getComuna();
            Comuna comunaNew = sucursal.getComuna();
            Collection<Agenda> agendaCollectionOld = persistentSucursal.getAgendaCollection();
            Collection<Agenda> agendaCollectionNew = sucursal.getAgendaCollection();
            Collection<Propietario> propietarioCollectionOld = persistentSucursal.getPropietarioCollection();
            Collection<Propietario> propietarioCollectionNew = sucursal.getPropietarioCollection();
            Collection<Veterinario> veterinarioCollectionOld = persistentSucursal.getVeterinarioCollection();
            Collection<Veterinario> veterinarioCollectionNew = sucursal.getVeterinarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Agenda agendaCollectionOldAgenda : agendaCollectionOld) {
                if (!agendaCollectionNew.contains(agendaCollectionOldAgenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agenda " + agendaCollectionOldAgenda + " since its sucursal field is not nullable.");
                }
            }
            for (Propietario propietarioCollectionOldPropietario : propietarioCollectionOld) {
                if (!propietarioCollectionNew.contains(propietarioCollectionOldPropietario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Propietario " + propietarioCollectionOldPropietario + " since its sucursal field is not nullable.");
                }
            }
            for (Veterinario veterinarioCollectionOldVeterinario : veterinarioCollectionOld) {
                if (!veterinarioCollectionNew.contains(veterinarioCollectionOldVeterinario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Veterinario " + veterinarioCollectionOldVeterinario + " since its sucursal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clinicaNew != null) {
                clinicaNew = em.getReference(clinicaNew.getClass(), clinicaNew.getIdClinica());
                sucursal.setClinica(clinicaNew);
            }
            if (comunaNew != null) {
                comunaNew = em.getReference(comunaNew.getClass(), comunaNew.getIdComuna());
                sucursal.setComuna(comunaNew);
            }
            Collection<Agenda> attachedAgendaCollectionNew = new ArrayList<Agenda>();
            for (Agenda agendaCollectionNewAgendaToAttach : agendaCollectionNew) {
                agendaCollectionNewAgendaToAttach = em.getReference(agendaCollectionNewAgendaToAttach.getClass(), agendaCollectionNewAgendaToAttach.getIdEvento());
                attachedAgendaCollectionNew.add(agendaCollectionNewAgendaToAttach);
            }
            agendaCollectionNew = attachedAgendaCollectionNew;
            sucursal.setAgendaCollection(agendaCollectionNew);
            Collection<Propietario> attachedPropietarioCollectionNew = new ArrayList<Propietario>();
            for (Propietario propietarioCollectionNewPropietarioToAttach : propietarioCollectionNew) {
                propietarioCollectionNewPropietarioToAttach = em.getReference(propietarioCollectionNewPropietarioToAttach.getClass(), propietarioCollectionNewPropietarioToAttach.getIdPropietario());
                attachedPropietarioCollectionNew.add(propietarioCollectionNewPropietarioToAttach);
            }
            propietarioCollectionNew = attachedPropietarioCollectionNew;
            sucursal.setPropietarioCollection(propietarioCollectionNew);
            Collection<Veterinario> attachedVeterinarioCollectionNew = new ArrayList<Veterinario>();
            for (Veterinario veterinarioCollectionNewVeterinarioToAttach : veterinarioCollectionNew) {
                veterinarioCollectionNewVeterinarioToAttach = em.getReference(veterinarioCollectionNewVeterinarioToAttach.getClass(), veterinarioCollectionNewVeterinarioToAttach.getIdVeterinario());
                attachedVeterinarioCollectionNew.add(veterinarioCollectionNewVeterinarioToAttach);
            }
            veterinarioCollectionNew = attachedVeterinarioCollectionNew;
            sucursal.setVeterinarioCollection(veterinarioCollectionNew);
            sucursal = em.merge(sucursal);
            if (clinicaOld != null && !clinicaOld.equals(clinicaNew)) {
                clinicaOld.getSucursalCollection().remove(sucursal);
                clinicaOld = em.merge(clinicaOld);
            }
            if (clinicaNew != null && !clinicaNew.equals(clinicaOld)) {
                clinicaNew.getSucursalCollection().add(sucursal);
                clinicaNew = em.merge(clinicaNew);
            }
            if (comunaOld != null && !comunaOld.equals(comunaNew)) {
                comunaOld.getSucursalCollection().remove(sucursal);
                comunaOld = em.merge(comunaOld);
            }
            if (comunaNew != null && !comunaNew.equals(comunaOld)) {
                comunaNew.getSucursalCollection().add(sucursal);
                comunaNew = em.merge(comunaNew);
            }
            for (Agenda agendaCollectionNewAgenda : agendaCollectionNew) {
                if (!agendaCollectionOld.contains(agendaCollectionNewAgenda)) {
                    Sucursal oldSucursalOfAgendaCollectionNewAgenda = agendaCollectionNewAgenda.getSucursal();
                    agendaCollectionNewAgenda.setSucursal(sucursal);
                    agendaCollectionNewAgenda = em.merge(agendaCollectionNewAgenda);
                    if (oldSucursalOfAgendaCollectionNewAgenda != null && !oldSucursalOfAgendaCollectionNewAgenda.equals(sucursal)) {
                        oldSucursalOfAgendaCollectionNewAgenda.getAgendaCollection().remove(agendaCollectionNewAgenda);
                        oldSucursalOfAgendaCollectionNewAgenda = em.merge(oldSucursalOfAgendaCollectionNewAgenda);
                    }
                }
            }
            for (Propietario propietarioCollectionNewPropietario : propietarioCollectionNew) {
                if (!propietarioCollectionOld.contains(propietarioCollectionNewPropietario)) {
                    Sucursal oldSucursalOfPropietarioCollectionNewPropietario = propietarioCollectionNewPropietario.getSucursal();
                    propietarioCollectionNewPropietario.setSucursal(sucursal);
                    propietarioCollectionNewPropietario = em.merge(propietarioCollectionNewPropietario);
                    if (oldSucursalOfPropietarioCollectionNewPropietario != null && !oldSucursalOfPropietarioCollectionNewPropietario.equals(sucursal)) {
                        oldSucursalOfPropietarioCollectionNewPropietario.getPropietarioCollection().remove(propietarioCollectionNewPropietario);
                        oldSucursalOfPropietarioCollectionNewPropietario = em.merge(oldSucursalOfPropietarioCollectionNewPropietario);
                    }
                }
            }
            for (Veterinario veterinarioCollectionNewVeterinario : veterinarioCollectionNew) {
                if (!veterinarioCollectionOld.contains(veterinarioCollectionNewVeterinario)) {
                    Sucursal oldSucursalOfVeterinarioCollectionNewVeterinario = veterinarioCollectionNewVeterinario.getSucursal();
                    veterinarioCollectionNewVeterinario.setSucursal(sucursal);
                    veterinarioCollectionNewVeterinario = em.merge(veterinarioCollectionNewVeterinario);
                    if (oldSucursalOfVeterinarioCollectionNewVeterinario != null && !oldSucursalOfVeterinarioCollectionNewVeterinario.equals(sucursal)) {
                        oldSucursalOfVeterinarioCollectionNewVeterinario.getVeterinarioCollection().remove(veterinarioCollectionNewVeterinario);
                        oldSucursalOfVeterinarioCollectionNewVeterinario = em.merge(oldSucursalOfVeterinarioCollectionNewVeterinario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sucursal.getIdSucursal();
                if (findSucursal(id) == null) {
                    throw new NonexistentEntityException("The sucursal with id " + id + " no longer exists.");
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
            Sucursal sucursal;
            try {
                sucursal = em.getReference(Sucursal.class, id);
                sucursal.getIdSucursal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sucursal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Agenda> agendaCollectionOrphanCheck = sucursal.getAgendaCollection();
            for (Agenda agendaCollectionOrphanCheckAgenda : agendaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sucursal (" + sucursal + ") cannot be destroyed since the Agenda " + agendaCollectionOrphanCheckAgenda + " in its agendaCollection field has a non-nullable sucursal field.");
            }
            Collection<Propietario> propietarioCollectionOrphanCheck = sucursal.getPropietarioCollection();
            for (Propietario propietarioCollectionOrphanCheckPropietario : propietarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sucursal (" + sucursal + ") cannot be destroyed since the Propietario " + propietarioCollectionOrphanCheckPropietario + " in its propietarioCollection field has a non-nullable sucursal field.");
            }
            Collection<Veterinario> veterinarioCollectionOrphanCheck = sucursal.getVeterinarioCollection();
            for (Veterinario veterinarioCollectionOrphanCheckVeterinario : veterinarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sucursal (" + sucursal + ") cannot be destroyed since the Veterinario " + veterinarioCollectionOrphanCheckVeterinario + " in its veterinarioCollection field has a non-nullable sucursal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clinica clinica = sucursal.getClinica();
            if (clinica != null) {
                clinica.getSucursalCollection().remove(sucursal);
                clinica = em.merge(clinica);
            }
            Comuna comuna = sucursal.getComuna();
            if (comuna != null) {
                comuna.getSucursalCollection().remove(sucursal);
                comuna = em.merge(comuna);
            }
            em.remove(sucursal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sucursal> findSucursalEntities() {
        return findSucursalEntities(true, -1, -1);
    }

    public List<Sucursal> findSucursalEntities(int maxResults, int firstResult) {
        return findSucursalEntities(false, maxResults, firstResult);
    }

    private List<Sucursal> findSucursalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sucursal.class));
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

    public Sucursal findSucursal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sucursal.class, id);
        } finally {
            em.close();
        }
    }

    public int getSucursalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sucursal> rt = cq.from(Sucursal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
