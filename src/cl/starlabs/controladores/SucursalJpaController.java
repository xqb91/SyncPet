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
import java.util.List;
import cl.starlabs.modelo.Propietario;
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Veterinario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Manuel Araya
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
        if (sucursal.getAgendaList() == null) {
            sucursal.setAgendaList(new ArrayList<Agenda>());
        }
        if (sucursal.getPropietarioList() == null) {
            sucursal.setPropietarioList(new ArrayList<Propietario>());
        }
        if (sucursal.getVeterinarioList() == null) {
            sucursal.setVeterinarioList(new ArrayList<Veterinario>());
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
            List<Agenda> attachedAgendaList = new ArrayList<Agenda>();
            for (Agenda agendaListAgendaToAttach : sucursal.getAgendaList()) {
                agendaListAgendaToAttach = em.getReference(agendaListAgendaToAttach.getClass(), agendaListAgendaToAttach.getIdEvento());
                attachedAgendaList.add(agendaListAgendaToAttach);
            }
            sucursal.setAgendaList(attachedAgendaList);
            List<Propietario> attachedPropietarioList = new ArrayList<Propietario>();
            for (Propietario propietarioListPropietarioToAttach : sucursal.getPropietarioList()) {
                propietarioListPropietarioToAttach = em.getReference(propietarioListPropietarioToAttach.getClass(), propietarioListPropietarioToAttach.getIdPropietario());
                attachedPropietarioList.add(propietarioListPropietarioToAttach);
            }
            sucursal.setPropietarioList(attachedPropietarioList);
            List<Veterinario> attachedVeterinarioList = new ArrayList<Veterinario>();
            for (Veterinario veterinarioListVeterinarioToAttach : sucursal.getVeterinarioList()) {
                veterinarioListVeterinarioToAttach = em.getReference(veterinarioListVeterinarioToAttach.getClass(), veterinarioListVeterinarioToAttach.getIdVeterinario());
                attachedVeterinarioList.add(veterinarioListVeterinarioToAttach);
            }
            sucursal.setVeterinarioList(attachedVeterinarioList);
            em.persist(sucursal);
            if (clinica != null) {
                clinica.getSucursalList().add(sucursal);
                clinica = em.merge(clinica);
            }
            if (comuna != null) {
                comuna.getSucursalList().add(sucursal);
                comuna = em.merge(comuna);
            }
            for (Agenda agendaListAgenda : sucursal.getAgendaList()) {
                Sucursal oldSucursalOfAgendaListAgenda = agendaListAgenda.getSucursal();
                agendaListAgenda.setSucursal(sucursal);
                agendaListAgenda = em.merge(agendaListAgenda);
                if (oldSucursalOfAgendaListAgenda != null) {
                    oldSucursalOfAgendaListAgenda.getAgendaList().remove(agendaListAgenda);
                    oldSucursalOfAgendaListAgenda = em.merge(oldSucursalOfAgendaListAgenda);
                }
            }
            for (Propietario propietarioListPropietario : sucursal.getPropietarioList()) {
                Sucursal oldSucursalOfPropietarioListPropietario = propietarioListPropietario.getSucursal();
                propietarioListPropietario.setSucursal(sucursal);
                propietarioListPropietario = em.merge(propietarioListPropietario);
                if (oldSucursalOfPropietarioListPropietario != null) {
                    oldSucursalOfPropietarioListPropietario.getPropietarioList().remove(propietarioListPropietario);
                    oldSucursalOfPropietarioListPropietario = em.merge(oldSucursalOfPropietarioListPropietario);
                }
            }
            for (Veterinario veterinarioListVeterinario : sucursal.getVeterinarioList()) {
                Sucursal oldSucursalOfVeterinarioListVeterinario = veterinarioListVeterinario.getSucursal();
                veterinarioListVeterinario.setSucursal(sucursal);
                veterinarioListVeterinario = em.merge(veterinarioListVeterinario);
                if (oldSucursalOfVeterinarioListVeterinario != null) {
                    oldSucursalOfVeterinarioListVeterinario.getVeterinarioList().remove(veterinarioListVeterinario);
                    oldSucursalOfVeterinarioListVeterinario = em.merge(oldSucursalOfVeterinarioListVeterinario);
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
            List<Agenda> agendaListOld = persistentSucursal.getAgendaList();
            List<Agenda> agendaListNew = sucursal.getAgendaList();
            List<Propietario> propietarioListOld = persistentSucursal.getPropietarioList();
            List<Propietario> propietarioListNew = sucursal.getPropietarioList();
            List<Veterinario> veterinarioListOld = persistentSucursal.getVeterinarioList();
            List<Veterinario> veterinarioListNew = sucursal.getVeterinarioList();
            List<String> illegalOrphanMessages = null;
            for (Agenda agendaListOldAgenda : agendaListOld) {
                if (!agendaListNew.contains(agendaListOldAgenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agenda " + agendaListOldAgenda + " since its sucursal field is not nullable.");
                }
            }
            for (Propietario propietarioListOldPropietario : propietarioListOld) {
                if (!propietarioListNew.contains(propietarioListOldPropietario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Propietario " + propietarioListOldPropietario + " since its sucursal field is not nullable.");
                }
            }
            for (Veterinario veterinarioListOldVeterinario : veterinarioListOld) {
                if (!veterinarioListNew.contains(veterinarioListOldVeterinario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Veterinario " + veterinarioListOldVeterinario + " since its sucursal field is not nullable.");
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
            List<Agenda> attachedAgendaListNew = new ArrayList<Agenda>();
            for (Agenda agendaListNewAgendaToAttach : agendaListNew) {
                agendaListNewAgendaToAttach = em.getReference(agendaListNewAgendaToAttach.getClass(), agendaListNewAgendaToAttach.getIdEvento());
                attachedAgendaListNew.add(agendaListNewAgendaToAttach);
            }
            agendaListNew = attachedAgendaListNew;
            sucursal.setAgendaList(agendaListNew);
            List<Propietario> attachedPropietarioListNew = new ArrayList<Propietario>();
            for (Propietario propietarioListNewPropietarioToAttach : propietarioListNew) {
                propietarioListNewPropietarioToAttach = em.getReference(propietarioListNewPropietarioToAttach.getClass(), propietarioListNewPropietarioToAttach.getIdPropietario());
                attachedPropietarioListNew.add(propietarioListNewPropietarioToAttach);
            }
            propietarioListNew = attachedPropietarioListNew;
            sucursal.setPropietarioList(propietarioListNew);
            List<Veterinario> attachedVeterinarioListNew = new ArrayList<Veterinario>();
            for (Veterinario veterinarioListNewVeterinarioToAttach : veterinarioListNew) {
                veterinarioListNewVeterinarioToAttach = em.getReference(veterinarioListNewVeterinarioToAttach.getClass(), veterinarioListNewVeterinarioToAttach.getIdVeterinario());
                attachedVeterinarioListNew.add(veterinarioListNewVeterinarioToAttach);
            }
            veterinarioListNew = attachedVeterinarioListNew;
            sucursal.setVeterinarioList(veterinarioListNew);
            sucursal = em.merge(sucursal);
            if (clinicaOld != null && !clinicaOld.equals(clinicaNew)) {
                clinicaOld.getSucursalList().remove(sucursal);
                clinicaOld = em.merge(clinicaOld);
            }
            if (clinicaNew != null && !clinicaNew.equals(clinicaOld)) {
                clinicaNew.getSucursalList().add(sucursal);
                clinicaNew = em.merge(clinicaNew);
            }
            if (comunaOld != null && !comunaOld.equals(comunaNew)) {
                comunaOld.getSucursalList().remove(sucursal);
                comunaOld = em.merge(comunaOld);
            }
            if (comunaNew != null && !comunaNew.equals(comunaOld)) {
                comunaNew.getSucursalList().add(sucursal);
                comunaNew = em.merge(comunaNew);
            }
            for (Agenda agendaListNewAgenda : agendaListNew) {
                if (!agendaListOld.contains(agendaListNewAgenda)) {
                    Sucursal oldSucursalOfAgendaListNewAgenda = agendaListNewAgenda.getSucursal();
                    agendaListNewAgenda.setSucursal(sucursal);
                    agendaListNewAgenda = em.merge(agendaListNewAgenda);
                    if (oldSucursalOfAgendaListNewAgenda != null && !oldSucursalOfAgendaListNewAgenda.equals(sucursal)) {
                        oldSucursalOfAgendaListNewAgenda.getAgendaList().remove(agendaListNewAgenda);
                        oldSucursalOfAgendaListNewAgenda = em.merge(oldSucursalOfAgendaListNewAgenda);
                    }
                }
            }
            for (Propietario propietarioListNewPropietario : propietarioListNew) {
                if (!propietarioListOld.contains(propietarioListNewPropietario)) {
                    Sucursal oldSucursalOfPropietarioListNewPropietario = propietarioListNewPropietario.getSucursal();
                    propietarioListNewPropietario.setSucursal(sucursal);
                    propietarioListNewPropietario = em.merge(propietarioListNewPropietario);
                    if (oldSucursalOfPropietarioListNewPropietario != null && !oldSucursalOfPropietarioListNewPropietario.equals(sucursal)) {
                        oldSucursalOfPropietarioListNewPropietario.getPropietarioList().remove(propietarioListNewPropietario);
                        oldSucursalOfPropietarioListNewPropietario = em.merge(oldSucursalOfPropietarioListNewPropietario);
                    }
                }
            }
            for (Veterinario veterinarioListNewVeterinario : veterinarioListNew) {
                if (!veterinarioListOld.contains(veterinarioListNewVeterinario)) {
                    Sucursal oldSucursalOfVeterinarioListNewVeterinario = veterinarioListNewVeterinario.getSucursal();
                    veterinarioListNewVeterinario.setSucursal(sucursal);
                    veterinarioListNewVeterinario = em.merge(veterinarioListNewVeterinario);
                    if (oldSucursalOfVeterinarioListNewVeterinario != null && !oldSucursalOfVeterinarioListNewVeterinario.equals(sucursal)) {
                        oldSucursalOfVeterinarioListNewVeterinario.getVeterinarioList().remove(veterinarioListNewVeterinario);
                        oldSucursalOfVeterinarioListNewVeterinario = em.merge(oldSucursalOfVeterinarioListNewVeterinario);
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
            List<Agenda> agendaListOrphanCheck = sucursal.getAgendaList();
            for (Agenda agendaListOrphanCheckAgenda : agendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sucursal (" + sucursal + ") cannot be destroyed since the Agenda " + agendaListOrphanCheckAgenda + " in its agendaList field has a non-nullable sucursal field.");
            }
            List<Propietario> propietarioListOrphanCheck = sucursal.getPropietarioList();
            for (Propietario propietarioListOrphanCheckPropietario : propietarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sucursal (" + sucursal + ") cannot be destroyed since the Propietario " + propietarioListOrphanCheckPropietario + " in its propietarioList field has a non-nullable sucursal field.");
            }
            List<Veterinario> veterinarioListOrphanCheck = sucursal.getVeterinarioList();
            for (Veterinario veterinarioListOrphanCheckVeterinario : veterinarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sucursal (" + sucursal + ") cannot be destroyed since the Veterinario " + veterinarioListOrphanCheckVeterinario + " in its veterinarioList field has a non-nullable sucursal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clinica clinica = sucursal.getClinica();
            if (clinica != null) {
                clinica.getSucursalList().remove(sucursal);
                clinica = em.merge(clinica);
            }
            Comuna comuna = sucursal.getComuna();
            if (comuna != null) {
                comuna.getSucursalList().remove(sucursal);
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
