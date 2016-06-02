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
import cl.starlabs.modelo.Sucursal;
import cl.starlabs.modelo.Examenes;
import java.util.ArrayList;
import java.util.Collection;
import cl.starlabs.modelo.Historialvacunas;
import cl.starlabs.modelo.Contraindicaciones;
import cl.starlabs.modelo.Desparacitaciones;
import cl.starlabs.modelo.Farmacos;
import cl.starlabs.modelo.Procedimientos;
import cl.starlabs.modelo.Hospitalizacion;
import cl.starlabs.modelo.AgendaDetalle;
import cl.starlabs.modelo.Anamnesis;
import cl.starlabs.modelo.Patologias;
import cl.starlabs.modelo.Veterinario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class VeterinarioJpaController implements Serializable {

    public VeterinarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Veterinario veterinario) throws PreexistingEntityException, Exception {
        if (veterinario.getExamenesCollection() == null) {
            veterinario.setExamenesCollection(new ArrayList<Examenes>());
        }
        if (veterinario.getHistorialvacunasCollection() == null) {
            veterinario.setHistorialvacunasCollection(new ArrayList<Historialvacunas>());
        }
        if (veterinario.getContraindicacionesCollection() == null) {
            veterinario.setContraindicacionesCollection(new ArrayList<Contraindicaciones>());
        }
        if (veterinario.getDesparacitacionesCollection() == null) {
            veterinario.setDesparacitacionesCollection(new ArrayList<Desparacitaciones>());
        }
        if (veterinario.getFarmacosCollection() == null) {
            veterinario.setFarmacosCollection(new ArrayList<Farmacos>());
        }
        if (veterinario.getProcedimientosCollection() == null) {
            veterinario.setProcedimientosCollection(new ArrayList<Procedimientos>());
        }
        if (veterinario.getHospitalizacionCollection() == null) {
            veterinario.setHospitalizacionCollection(new ArrayList<Hospitalizacion>());
        }
        if (veterinario.getAgendaDetalleCollection() == null) {
            veterinario.setAgendaDetalleCollection(new ArrayList<AgendaDetalle>());
        }
        if (veterinario.getAnamnesisCollection() == null) {
            veterinario.setAnamnesisCollection(new ArrayList<Anamnesis>());
        }
        if (veterinario.getPatologiasCollection() == null) {
            veterinario.setPatologiasCollection(new ArrayList<Patologias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sucursal sucursal = veterinario.getSucursal();
            if (sucursal != null) {
                sucursal = em.getReference(sucursal.getClass(), sucursal.getIdSucursal());
                veterinario.setSucursal(sucursal);
            }
            Collection<Examenes> attachedExamenesCollection = new ArrayList<Examenes>();
            for (Examenes examenesCollectionExamenesToAttach : veterinario.getExamenesCollection()) {
                examenesCollectionExamenesToAttach = em.getReference(examenesCollectionExamenesToAttach.getClass(), examenesCollectionExamenesToAttach.getIdExamen());
                attachedExamenesCollection.add(examenesCollectionExamenesToAttach);
            }
            veterinario.setExamenesCollection(attachedExamenesCollection);
            Collection<Historialvacunas> attachedHistorialvacunasCollection = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionHistorialvacunasToAttach : veterinario.getHistorialvacunasCollection()) {
                historialvacunasCollectionHistorialvacunasToAttach = em.getReference(historialvacunasCollectionHistorialvacunasToAttach.getClass(), historialvacunasCollectionHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollection.add(historialvacunasCollectionHistorialvacunasToAttach);
            }
            veterinario.setHistorialvacunasCollection(attachedHistorialvacunasCollection);
            Collection<Contraindicaciones> attachedContraindicacionesCollection = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionContraindicacionesToAttach : veterinario.getContraindicacionesCollection()) {
                contraindicacionesCollectionContraindicacionesToAttach = em.getReference(contraindicacionesCollectionContraindicacionesToAttach.getClass(), contraindicacionesCollectionContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollection.add(contraindicacionesCollectionContraindicacionesToAttach);
            }
            veterinario.setContraindicacionesCollection(attachedContraindicacionesCollection);
            Collection<Desparacitaciones> attachedDesparacitacionesCollection = new ArrayList<Desparacitaciones>();
            for (Desparacitaciones desparacitacionesCollectionDesparacitacionesToAttach : veterinario.getDesparacitacionesCollection()) {
                desparacitacionesCollectionDesparacitacionesToAttach = em.getReference(desparacitacionesCollectionDesparacitacionesToAttach.getClass(), desparacitacionesCollectionDesparacitacionesToAttach.getIdDesparacitacion());
                attachedDesparacitacionesCollection.add(desparacitacionesCollectionDesparacitacionesToAttach);
            }
            veterinario.setDesparacitacionesCollection(attachedDesparacitacionesCollection);
            Collection<Farmacos> attachedFarmacosCollection = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionFarmacosToAttach : veterinario.getFarmacosCollection()) {
                farmacosCollectionFarmacosToAttach = em.getReference(farmacosCollectionFarmacosToAttach.getClass(), farmacosCollectionFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollection.add(farmacosCollectionFarmacosToAttach);
            }
            veterinario.setFarmacosCollection(attachedFarmacosCollection);
            Collection<Procedimientos> attachedProcedimientosCollection = new ArrayList<Procedimientos>();
            for (Procedimientos procedimientosCollectionProcedimientosToAttach : veterinario.getProcedimientosCollection()) {
                procedimientosCollectionProcedimientosToAttach = em.getReference(procedimientosCollectionProcedimientosToAttach.getClass(), procedimientosCollectionProcedimientosToAttach.getIdProcedimiento());
                attachedProcedimientosCollection.add(procedimientosCollectionProcedimientosToAttach);
            }
            veterinario.setProcedimientosCollection(attachedProcedimientosCollection);
            Collection<Hospitalizacion> attachedHospitalizacionCollection = new ArrayList<Hospitalizacion>();
            for (Hospitalizacion hospitalizacionCollectionHospitalizacionToAttach : veterinario.getHospitalizacionCollection()) {
                hospitalizacionCollectionHospitalizacionToAttach = em.getReference(hospitalizacionCollectionHospitalizacionToAttach.getClass(), hospitalizacionCollectionHospitalizacionToAttach.getIdHospitalizacion());
                attachedHospitalizacionCollection.add(hospitalizacionCollectionHospitalizacionToAttach);
            }
            veterinario.setHospitalizacionCollection(attachedHospitalizacionCollection);
            Collection<AgendaDetalle> attachedAgendaDetalleCollection = new ArrayList<AgendaDetalle>();
            for (AgendaDetalle agendaDetalleCollectionAgendaDetalleToAttach : veterinario.getAgendaDetalleCollection()) {
                agendaDetalleCollectionAgendaDetalleToAttach = em.getReference(agendaDetalleCollectionAgendaDetalleToAttach.getClass(), agendaDetalleCollectionAgendaDetalleToAttach.getIdDetalle());
                attachedAgendaDetalleCollection.add(agendaDetalleCollectionAgendaDetalleToAttach);
            }
            veterinario.setAgendaDetalleCollection(attachedAgendaDetalleCollection);
            Collection<Anamnesis> attachedAnamnesisCollection = new ArrayList<Anamnesis>();
            for (Anamnesis anamnesisCollectionAnamnesisToAttach : veterinario.getAnamnesisCollection()) {
                anamnesisCollectionAnamnesisToAttach = em.getReference(anamnesisCollectionAnamnesisToAttach.getClass(), anamnesisCollectionAnamnesisToAttach.getIdAnamnesis());
                attachedAnamnesisCollection.add(anamnesisCollectionAnamnesisToAttach);
            }
            veterinario.setAnamnesisCollection(attachedAnamnesisCollection);
            Collection<Patologias> attachedPatologiasCollection = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionPatologiasToAttach : veterinario.getPatologiasCollection()) {
                patologiasCollectionPatologiasToAttach = em.getReference(patologiasCollectionPatologiasToAttach.getClass(), patologiasCollectionPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollection.add(patologiasCollectionPatologiasToAttach);
            }
            veterinario.setPatologiasCollection(attachedPatologiasCollection);
            em.persist(veterinario);
            if (sucursal != null) {
                sucursal.getVeterinarioCollection().add(veterinario);
                sucursal = em.merge(sucursal);
            }
            for (Examenes examenesCollectionExamenes : veterinario.getExamenesCollection()) {
                Veterinario oldVeterinarioOfExamenesCollectionExamenes = examenesCollectionExamenes.getVeterinario();
                examenesCollectionExamenes.setVeterinario(veterinario);
                examenesCollectionExamenes = em.merge(examenesCollectionExamenes);
                if (oldVeterinarioOfExamenesCollectionExamenes != null) {
                    oldVeterinarioOfExamenesCollectionExamenes.getExamenesCollection().remove(examenesCollectionExamenes);
                    oldVeterinarioOfExamenesCollectionExamenes = em.merge(oldVeterinarioOfExamenesCollectionExamenes);
                }
            }
            for (Historialvacunas historialvacunasCollectionHistorialvacunas : veterinario.getHistorialvacunasCollection()) {
                Veterinario oldVeterinarioOfHistorialvacunasCollectionHistorialvacunas = historialvacunasCollectionHistorialvacunas.getVeterinario();
                historialvacunasCollectionHistorialvacunas.setVeterinario(veterinario);
                historialvacunasCollectionHistorialvacunas = em.merge(historialvacunasCollectionHistorialvacunas);
                if (oldVeterinarioOfHistorialvacunasCollectionHistorialvacunas != null) {
                    oldVeterinarioOfHistorialvacunasCollectionHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionHistorialvacunas);
                    oldVeterinarioOfHistorialvacunasCollectionHistorialvacunas = em.merge(oldVeterinarioOfHistorialvacunasCollectionHistorialvacunas);
                }
            }
            for (Contraindicaciones contraindicacionesCollectionContraindicaciones : veterinario.getContraindicacionesCollection()) {
                Veterinario oldVeterinarioOfContraindicacionesCollectionContraindicaciones = contraindicacionesCollectionContraindicaciones.getVeterinario();
                contraindicacionesCollectionContraindicaciones.setVeterinario(veterinario);
                contraindicacionesCollectionContraindicaciones = em.merge(contraindicacionesCollectionContraindicaciones);
                if (oldVeterinarioOfContraindicacionesCollectionContraindicaciones != null) {
                    oldVeterinarioOfContraindicacionesCollectionContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionContraindicaciones);
                    oldVeterinarioOfContraindicacionesCollectionContraindicaciones = em.merge(oldVeterinarioOfContraindicacionesCollectionContraindicaciones);
                }
            }
            for (Desparacitaciones desparacitacionesCollectionDesparacitaciones : veterinario.getDesparacitacionesCollection()) {
                Veterinario oldEspecialistaOfDesparacitacionesCollectionDesparacitaciones = desparacitacionesCollectionDesparacitaciones.getEspecialista();
                desparacitacionesCollectionDesparacitaciones.setEspecialista(veterinario);
                desparacitacionesCollectionDesparacitaciones = em.merge(desparacitacionesCollectionDesparacitaciones);
                if (oldEspecialistaOfDesparacitacionesCollectionDesparacitaciones != null) {
                    oldEspecialistaOfDesparacitacionesCollectionDesparacitaciones.getDesparacitacionesCollection().remove(desparacitacionesCollectionDesparacitaciones);
                    oldEspecialistaOfDesparacitacionesCollectionDesparacitaciones = em.merge(oldEspecialistaOfDesparacitacionesCollectionDesparacitaciones);
                }
            }
            for (Farmacos farmacosCollectionFarmacos : veterinario.getFarmacosCollection()) {
                Veterinario oldVeterinarioOfFarmacosCollectionFarmacos = farmacosCollectionFarmacos.getVeterinario();
                farmacosCollectionFarmacos.setVeterinario(veterinario);
                farmacosCollectionFarmacos = em.merge(farmacosCollectionFarmacos);
                if (oldVeterinarioOfFarmacosCollectionFarmacos != null) {
                    oldVeterinarioOfFarmacosCollectionFarmacos.getFarmacosCollection().remove(farmacosCollectionFarmacos);
                    oldVeterinarioOfFarmacosCollectionFarmacos = em.merge(oldVeterinarioOfFarmacosCollectionFarmacos);
                }
            }
            for (Procedimientos procedimientosCollectionProcedimientos : veterinario.getProcedimientosCollection()) {
                Veterinario oldVeterinarioOfProcedimientosCollectionProcedimientos = procedimientosCollectionProcedimientos.getVeterinario();
                procedimientosCollectionProcedimientos.setVeterinario(veterinario);
                procedimientosCollectionProcedimientos = em.merge(procedimientosCollectionProcedimientos);
                if (oldVeterinarioOfProcedimientosCollectionProcedimientos != null) {
                    oldVeterinarioOfProcedimientosCollectionProcedimientos.getProcedimientosCollection().remove(procedimientosCollectionProcedimientos);
                    oldVeterinarioOfProcedimientosCollectionProcedimientos = em.merge(oldVeterinarioOfProcedimientosCollectionProcedimientos);
                }
            }
            for (Hospitalizacion hospitalizacionCollectionHospitalizacion : veterinario.getHospitalizacionCollection()) {
                Veterinario oldVeterinarioOfHospitalizacionCollectionHospitalizacion = hospitalizacionCollectionHospitalizacion.getVeterinario();
                hospitalizacionCollectionHospitalizacion.setVeterinario(veterinario);
                hospitalizacionCollectionHospitalizacion = em.merge(hospitalizacionCollectionHospitalizacion);
                if (oldVeterinarioOfHospitalizacionCollectionHospitalizacion != null) {
                    oldVeterinarioOfHospitalizacionCollectionHospitalizacion.getHospitalizacionCollection().remove(hospitalizacionCollectionHospitalizacion);
                    oldVeterinarioOfHospitalizacionCollectionHospitalizacion = em.merge(oldVeterinarioOfHospitalizacionCollectionHospitalizacion);
                }
            }
            for (AgendaDetalle agendaDetalleCollectionAgendaDetalle : veterinario.getAgendaDetalleCollection()) {
                Veterinario oldVeterinarioOfAgendaDetalleCollectionAgendaDetalle = agendaDetalleCollectionAgendaDetalle.getVeterinario();
                agendaDetalleCollectionAgendaDetalle.setVeterinario(veterinario);
                agendaDetalleCollectionAgendaDetalle = em.merge(agendaDetalleCollectionAgendaDetalle);
                if (oldVeterinarioOfAgendaDetalleCollectionAgendaDetalle != null) {
                    oldVeterinarioOfAgendaDetalleCollectionAgendaDetalle.getAgendaDetalleCollection().remove(agendaDetalleCollectionAgendaDetalle);
                    oldVeterinarioOfAgendaDetalleCollectionAgendaDetalle = em.merge(oldVeterinarioOfAgendaDetalleCollectionAgendaDetalle);
                }
            }
            for (Anamnesis anamnesisCollectionAnamnesis : veterinario.getAnamnesisCollection()) {
                Veterinario oldVeterinarioOfAnamnesisCollectionAnamnesis = anamnesisCollectionAnamnesis.getVeterinario();
                anamnesisCollectionAnamnesis.setVeterinario(veterinario);
                anamnesisCollectionAnamnesis = em.merge(anamnesisCollectionAnamnesis);
                if (oldVeterinarioOfAnamnesisCollectionAnamnesis != null) {
                    oldVeterinarioOfAnamnesisCollectionAnamnesis.getAnamnesisCollection().remove(anamnesisCollectionAnamnesis);
                    oldVeterinarioOfAnamnesisCollectionAnamnesis = em.merge(oldVeterinarioOfAnamnesisCollectionAnamnesis);
                }
            }
            for (Patologias patologiasCollectionPatologias : veterinario.getPatologiasCollection()) {
                Veterinario oldVeterinarioOfPatologiasCollectionPatologias = patologiasCollectionPatologias.getVeterinario();
                patologiasCollectionPatologias.setVeterinario(veterinario);
                patologiasCollectionPatologias = em.merge(patologiasCollectionPatologias);
                if (oldVeterinarioOfPatologiasCollectionPatologias != null) {
                    oldVeterinarioOfPatologiasCollectionPatologias.getPatologiasCollection().remove(patologiasCollectionPatologias);
                    oldVeterinarioOfPatologiasCollectionPatologias = em.merge(oldVeterinarioOfPatologiasCollectionPatologias);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVeterinario(veterinario.getIdVeterinario()) != null) {
                throw new PreexistingEntityException("Veterinario " + veterinario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Veterinario veterinario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Veterinario persistentVeterinario = em.find(Veterinario.class, veterinario.getIdVeterinario());
            Sucursal sucursalOld = persistentVeterinario.getSucursal();
            Sucursal sucursalNew = veterinario.getSucursal();
            Collection<Examenes> examenesCollectionOld = persistentVeterinario.getExamenesCollection();
            Collection<Examenes> examenesCollectionNew = veterinario.getExamenesCollection();
            Collection<Historialvacunas> historialvacunasCollectionOld = persistentVeterinario.getHistorialvacunasCollection();
            Collection<Historialvacunas> historialvacunasCollectionNew = veterinario.getHistorialvacunasCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionOld = persistentVeterinario.getContraindicacionesCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionNew = veterinario.getContraindicacionesCollection();
            Collection<Desparacitaciones> desparacitacionesCollectionOld = persistentVeterinario.getDesparacitacionesCollection();
            Collection<Desparacitaciones> desparacitacionesCollectionNew = veterinario.getDesparacitacionesCollection();
            Collection<Farmacos> farmacosCollectionOld = persistentVeterinario.getFarmacosCollection();
            Collection<Farmacos> farmacosCollectionNew = veterinario.getFarmacosCollection();
            Collection<Procedimientos> procedimientosCollectionOld = persistentVeterinario.getProcedimientosCollection();
            Collection<Procedimientos> procedimientosCollectionNew = veterinario.getProcedimientosCollection();
            Collection<Hospitalizacion> hospitalizacionCollectionOld = persistentVeterinario.getHospitalizacionCollection();
            Collection<Hospitalizacion> hospitalizacionCollectionNew = veterinario.getHospitalizacionCollection();
            Collection<AgendaDetalle> agendaDetalleCollectionOld = persistentVeterinario.getAgendaDetalleCollection();
            Collection<AgendaDetalle> agendaDetalleCollectionNew = veterinario.getAgendaDetalleCollection();
            Collection<Anamnesis> anamnesisCollectionOld = persistentVeterinario.getAnamnesisCollection();
            Collection<Anamnesis> anamnesisCollectionNew = veterinario.getAnamnesisCollection();
            Collection<Patologias> patologiasCollectionOld = persistentVeterinario.getPatologiasCollection();
            Collection<Patologias> patologiasCollectionNew = veterinario.getPatologiasCollection();
            List<String> illegalOrphanMessages = null;
            for (Examenes examenesCollectionOldExamenes : examenesCollectionOld) {
                if (!examenesCollectionNew.contains(examenesCollectionOldExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Examenes " + examenesCollectionOldExamenes + " since its veterinario field is not nullable.");
                }
            }
            for (Historialvacunas historialvacunasCollectionOldHistorialvacunas : historialvacunasCollectionOld) {
                if (!historialvacunasCollectionNew.contains(historialvacunasCollectionOldHistorialvacunas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialvacunas " + historialvacunasCollectionOldHistorialvacunas + " since its veterinario field is not nullable.");
                }
            }
            for (Contraindicaciones contraindicacionesCollectionOldContraindicaciones : contraindicacionesCollectionOld) {
                if (!contraindicacionesCollectionNew.contains(contraindicacionesCollectionOldContraindicaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contraindicaciones " + contraindicacionesCollectionOldContraindicaciones + " since its veterinario field is not nullable.");
                }
            }
            for (Desparacitaciones desparacitacionesCollectionOldDesparacitaciones : desparacitacionesCollectionOld) {
                if (!desparacitacionesCollectionNew.contains(desparacitacionesCollectionOldDesparacitaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Desparacitaciones " + desparacitacionesCollectionOldDesparacitaciones + " since its especialista field is not nullable.");
                }
            }
            for (Farmacos farmacosCollectionOldFarmacos : farmacosCollectionOld) {
                if (!farmacosCollectionNew.contains(farmacosCollectionOldFarmacos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Farmacos " + farmacosCollectionOldFarmacos + " since its veterinario field is not nullable.");
                }
            }
            for (Procedimientos procedimientosCollectionOldProcedimientos : procedimientosCollectionOld) {
                if (!procedimientosCollectionNew.contains(procedimientosCollectionOldProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimientos " + procedimientosCollectionOldProcedimientos + " since its veterinario field is not nullable.");
                }
            }
            for (Hospitalizacion hospitalizacionCollectionOldHospitalizacion : hospitalizacionCollectionOld) {
                if (!hospitalizacionCollectionNew.contains(hospitalizacionCollectionOldHospitalizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Hospitalizacion " + hospitalizacionCollectionOldHospitalizacion + " since its veterinario field is not nullable.");
                }
            }
            for (AgendaDetalle agendaDetalleCollectionOldAgendaDetalle : agendaDetalleCollectionOld) {
                if (!agendaDetalleCollectionNew.contains(agendaDetalleCollectionOldAgendaDetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AgendaDetalle " + agendaDetalleCollectionOldAgendaDetalle + " since its veterinario field is not nullable.");
                }
            }
            for (Anamnesis anamnesisCollectionOldAnamnesis : anamnesisCollectionOld) {
                if (!anamnesisCollectionNew.contains(anamnesisCollectionOldAnamnesis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Anamnesis " + anamnesisCollectionOldAnamnesis + " since its veterinario field is not nullable.");
                }
            }
            for (Patologias patologiasCollectionOldPatologias : patologiasCollectionOld) {
                if (!patologiasCollectionNew.contains(patologiasCollectionOldPatologias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Patologias " + patologiasCollectionOldPatologias + " since its veterinario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sucursalNew != null) {
                sucursalNew = em.getReference(sucursalNew.getClass(), sucursalNew.getIdSucursal());
                veterinario.setSucursal(sucursalNew);
            }
            Collection<Examenes> attachedExamenesCollectionNew = new ArrayList<Examenes>();
            for (Examenes examenesCollectionNewExamenesToAttach : examenesCollectionNew) {
                examenesCollectionNewExamenesToAttach = em.getReference(examenesCollectionNewExamenesToAttach.getClass(), examenesCollectionNewExamenesToAttach.getIdExamen());
                attachedExamenesCollectionNew.add(examenesCollectionNewExamenesToAttach);
            }
            examenesCollectionNew = attachedExamenesCollectionNew;
            veterinario.setExamenesCollection(examenesCollectionNew);
            Collection<Historialvacunas> attachedHistorialvacunasCollectionNew = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunasToAttach : historialvacunasCollectionNew) {
                historialvacunasCollectionNewHistorialvacunasToAttach = em.getReference(historialvacunasCollectionNewHistorialvacunasToAttach.getClass(), historialvacunasCollectionNewHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollectionNew.add(historialvacunasCollectionNewHistorialvacunasToAttach);
            }
            historialvacunasCollectionNew = attachedHistorialvacunasCollectionNew;
            veterinario.setHistorialvacunasCollection(historialvacunasCollectionNew);
            Collection<Contraindicaciones> attachedContraindicacionesCollectionNew = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionNewContraindicacionesToAttach : contraindicacionesCollectionNew) {
                contraindicacionesCollectionNewContraindicacionesToAttach = em.getReference(contraindicacionesCollectionNewContraindicacionesToAttach.getClass(), contraindicacionesCollectionNewContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollectionNew.add(contraindicacionesCollectionNewContraindicacionesToAttach);
            }
            contraindicacionesCollectionNew = attachedContraindicacionesCollectionNew;
            veterinario.setContraindicacionesCollection(contraindicacionesCollectionNew);
            Collection<Desparacitaciones> attachedDesparacitacionesCollectionNew = new ArrayList<Desparacitaciones>();
            for (Desparacitaciones desparacitacionesCollectionNewDesparacitacionesToAttach : desparacitacionesCollectionNew) {
                desparacitacionesCollectionNewDesparacitacionesToAttach = em.getReference(desparacitacionesCollectionNewDesparacitacionesToAttach.getClass(), desparacitacionesCollectionNewDesparacitacionesToAttach.getIdDesparacitacion());
                attachedDesparacitacionesCollectionNew.add(desparacitacionesCollectionNewDesparacitacionesToAttach);
            }
            desparacitacionesCollectionNew = attachedDesparacitacionesCollectionNew;
            veterinario.setDesparacitacionesCollection(desparacitacionesCollectionNew);
            Collection<Farmacos> attachedFarmacosCollectionNew = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionNewFarmacosToAttach : farmacosCollectionNew) {
                farmacosCollectionNewFarmacosToAttach = em.getReference(farmacosCollectionNewFarmacosToAttach.getClass(), farmacosCollectionNewFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollectionNew.add(farmacosCollectionNewFarmacosToAttach);
            }
            farmacosCollectionNew = attachedFarmacosCollectionNew;
            veterinario.setFarmacosCollection(farmacosCollectionNew);
            Collection<Procedimientos> attachedProcedimientosCollectionNew = new ArrayList<Procedimientos>();
            for (Procedimientos procedimientosCollectionNewProcedimientosToAttach : procedimientosCollectionNew) {
                procedimientosCollectionNewProcedimientosToAttach = em.getReference(procedimientosCollectionNewProcedimientosToAttach.getClass(), procedimientosCollectionNewProcedimientosToAttach.getIdProcedimiento());
                attachedProcedimientosCollectionNew.add(procedimientosCollectionNewProcedimientosToAttach);
            }
            procedimientosCollectionNew = attachedProcedimientosCollectionNew;
            veterinario.setProcedimientosCollection(procedimientosCollectionNew);
            Collection<Hospitalizacion> attachedHospitalizacionCollectionNew = new ArrayList<Hospitalizacion>();
            for (Hospitalizacion hospitalizacionCollectionNewHospitalizacionToAttach : hospitalizacionCollectionNew) {
                hospitalizacionCollectionNewHospitalizacionToAttach = em.getReference(hospitalizacionCollectionNewHospitalizacionToAttach.getClass(), hospitalizacionCollectionNewHospitalizacionToAttach.getIdHospitalizacion());
                attachedHospitalizacionCollectionNew.add(hospitalizacionCollectionNewHospitalizacionToAttach);
            }
            hospitalizacionCollectionNew = attachedHospitalizacionCollectionNew;
            veterinario.setHospitalizacionCollection(hospitalizacionCollectionNew);
            Collection<AgendaDetalle> attachedAgendaDetalleCollectionNew = new ArrayList<AgendaDetalle>();
            for (AgendaDetalle agendaDetalleCollectionNewAgendaDetalleToAttach : agendaDetalleCollectionNew) {
                agendaDetalleCollectionNewAgendaDetalleToAttach = em.getReference(agendaDetalleCollectionNewAgendaDetalleToAttach.getClass(), agendaDetalleCollectionNewAgendaDetalleToAttach.getIdDetalle());
                attachedAgendaDetalleCollectionNew.add(agendaDetalleCollectionNewAgendaDetalleToAttach);
            }
            agendaDetalleCollectionNew = attachedAgendaDetalleCollectionNew;
            veterinario.setAgendaDetalleCollection(agendaDetalleCollectionNew);
            Collection<Anamnesis> attachedAnamnesisCollectionNew = new ArrayList<Anamnesis>();
            for (Anamnesis anamnesisCollectionNewAnamnesisToAttach : anamnesisCollectionNew) {
                anamnesisCollectionNewAnamnesisToAttach = em.getReference(anamnesisCollectionNewAnamnesisToAttach.getClass(), anamnesisCollectionNewAnamnesisToAttach.getIdAnamnesis());
                attachedAnamnesisCollectionNew.add(anamnesisCollectionNewAnamnesisToAttach);
            }
            anamnesisCollectionNew = attachedAnamnesisCollectionNew;
            veterinario.setAnamnesisCollection(anamnesisCollectionNew);
            Collection<Patologias> attachedPatologiasCollectionNew = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionNewPatologiasToAttach : patologiasCollectionNew) {
                patologiasCollectionNewPatologiasToAttach = em.getReference(patologiasCollectionNewPatologiasToAttach.getClass(), patologiasCollectionNewPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollectionNew.add(patologiasCollectionNewPatologiasToAttach);
            }
            patologiasCollectionNew = attachedPatologiasCollectionNew;
            veterinario.setPatologiasCollection(patologiasCollectionNew);
            veterinario = em.merge(veterinario);
            if (sucursalOld != null && !sucursalOld.equals(sucursalNew)) {
                sucursalOld.getVeterinarioCollection().remove(veterinario);
                sucursalOld = em.merge(sucursalOld);
            }
            if (sucursalNew != null && !sucursalNew.equals(sucursalOld)) {
                sucursalNew.getVeterinarioCollection().add(veterinario);
                sucursalNew = em.merge(sucursalNew);
            }
            for (Examenes examenesCollectionNewExamenes : examenesCollectionNew) {
                if (!examenesCollectionOld.contains(examenesCollectionNewExamenes)) {
                    Veterinario oldVeterinarioOfExamenesCollectionNewExamenes = examenesCollectionNewExamenes.getVeterinario();
                    examenesCollectionNewExamenes.setVeterinario(veterinario);
                    examenesCollectionNewExamenes = em.merge(examenesCollectionNewExamenes);
                    if (oldVeterinarioOfExamenesCollectionNewExamenes != null && !oldVeterinarioOfExamenesCollectionNewExamenes.equals(veterinario)) {
                        oldVeterinarioOfExamenesCollectionNewExamenes.getExamenesCollection().remove(examenesCollectionNewExamenes);
                        oldVeterinarioOfExamenesCollectionNewExamenes = em.merge(oldVeterinarioOfExamenesCollectionNewExamenes);
                    }
                }
            }
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunas : historialvacunasCollectionNew) {
                if (!historialvacunasCollectionOld.contains(historialvacunasCollectionNewHistorialvacunas)) {
                    Veterinario oldVeterinarioOfHistorialvacunasCollectionNewHistorialvacunas = historialvacunasCollectionNewHistorialvacunas.getVeterinario();
                    historialvacunasCollectionNewHistorialvacunas.setVeterinario(veterinario);
                    historialvacunasCollectionNewHistorialvacunas = em.merge(historialvacunasCollectionNewHistorialvacunas);
                    if (oldVeterinarioOfHistorialvacunasCollectionNewHistorialvacunas != null && !oldVeterinarioOfHistorialvacunasCollectionNewHistorialvacunas.equals(veterinario)) {
                        oldVeterinarioOfHistorialvacunasCollectionNewHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionNewHistorialvacunas);
                        oldVeterinarioOfHistorialvacunasCollectionNewHistorialvacunas = em.merge(oldVeterinarioOfHistorialvacunasCollectionNewHistorialvacunas);
                    }
                }
            }
            for (Contraindicaciones contraindicacionesCollectionNewContraindicaciones : contraindicacionesCollectionNew) {
                if (!contraindicacionesCollectionOld.contains(contraindicacionesCollectionNewContraindicaciones)) {
                    Veterinario oldVeterinarioOfContraindicacionesCollectionNewContraindicaciones = contraindicacionesCollectionNewContraindicaciones.getVeterinario();
                    contraindicacionesCollectionNewContraindicaciones.setVeterinario(veterinario);
                    contraindicacionesCollectionNewContraindicaciones = em.merge(contraindicacionesCollectionNewContraindicaciones);
                    if (oldVeterinarioOfContraindicacionesCollectionNewContraindicaciones != null && !oldVeterinarioOfContraindicacionesCollectionNewContraindicaciones.equals(veterinario)) {
                        oldVeterinarioOfContraindicacionesCollectionNewContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionNewContraindicaciones);
                        oldVeterinarioOfContraindicacionesCollectionNewContraindicaciones = em.merge(oldVeterinarioOfContraindicacionesCollectionNewContraindicaciones);
                    }
                }
            }
            for (Desparacitaciones desparacitacionesCollectionNewDesparacitaciones : desparacitacionesCollectionNew) {
                if (!desparacitacionesCollectionOld.contains(desparacitacionesCollectionNewDesparacitaciones)) {
                    Veterinario oldEspecialistaOfDesparacitacionesCollectionNewDesparacitaciones = desparacitacionesCollectionNewDesparacitaciones.getEspecialista();
                    desparacitacionesCollectionNewDesparacitaciones.setEspecialista(veterinario);
                    desparacitacionesCollectionNewDesparacitaciones = em.merge(desparacitacionesCollectionNewDesparacitaciones);
                    if (oldEspecialistaOfDesparacitacionesCollectionNewDesparacitaciones != null && !oldEspecialistaOfDesparacitacionesCollectionNewDesparacitaciones.equals(veterinario)) {
                        oldEspecialistaOfDesparacitacionesCollectionNewDesparacitaciones.getDesparacitacionesCollection().remove(desparacitacionesCollectionNewDesparacitaciones);
                        oldEspecialistaOfDesparacitacionesCollectionNewDesparacitaciones = em.merge(oldEspecialistaOfDesparacitacionesCollectionNewDesparacitaciones);
                    }
                }
            }
            for (Farmacos farmacosCollectionNewFarmacos : farmacosCollectionNew) {
                if (!farmacosCollectionOld.contains(farmacosCollectionNewFarmacos)) {
                    Veterinario oldVeterinarioOfFarmacosCollectionNewFarmacos = farmacosCollectionNewFarmacos.getVeterinario();
                    farmacosCollectionNewFarmacos.setVeterinario(veterinario);
                    farmacosCollectionNewFarmacos = em.merge(farmacosCollectionNewFarmacos);
                    if (oldVeterinarioOfFarmacosCollectionNewFarmacos != null && !oldVeterinarioOfFarmacosCollectionNewFarmacos.equals(veterinario)) {
                        oldVeterinarioOfFarmacosCollectionNewFarmacos.getFarmacosCollection().remove(farmacosCollectionNewFarmacos);
                        oldVeterinarioOfFarmacosCollectionNewFarmacos = em.merge(oldVeterinarioOfFarmacosCollectionNewFarmacos);
                    }
                }
            }
            for (Procedimientos procedimientosCollectionNewProcedimientos : procedimientosCollectionNew) {
                if (!procedimientosCollectionOld.contains(procedimientosCollectionNewProcedimientos)) {
                    Veterinario oldVeterinarioOfProcedimientosCollectionNewProcedimientos = procedimientosCollectionNewProcedimientos.getVeterinario();
                    procedimientosCollectionNewProcedimientos.setVeterinario(veterinario);
                    procedimientosCollectionNewProcedimientos = em.merge(procedimientosCollectionNewProcedimientos);
                    if (oldVeterinarioOfProcedimientosCollectionNewProcedimientos != null && !oldVeterinarioOfProcedimientosCollectionNewProcedimientos.equals(veterinario)) {
                        oldVeterinarioOfProcedimientosCollectionNewProcedimientos.getProcedimientosCollection().remove(procedimientosCollectionNewProcedimientos);
                        oldVeterinarioOfProcedimientosCollectionNewProcedimientos = em.merge(oldVeterinarioOfProcedimientosCollectionNewProcedimientos);
                    }
                }
            }
            for (Hospitalizacion hospitalizacionCollectionNewHospitalizacion : hospitalizacionCollectionNew) {
                if (!hospitalizacionCollectionOld.contains(hospitalizacionCollectionNewHospitalizacion)) {
                    Veterinario oldVeterinarioOfHospitalizacionCollectionNewHospitalizacion = hospitalizacionCollectionNewHospitalizacion.getVeterinario();
                    hospitalizacionCollectionNewHospitalizacion.setVeterinario(veterinario);
                    hospitalizacionCollectionNewHospitalizacion = em.merge(hospitalizacionCollectionNewHospitalizacion);
                    if (oldVeterinarioOfHospitalizacionCollectionNewHospitalizacion != null && !oldVeterinarioOfHospitalizacionCollectionNewHospitalizacion.equals(veterinario)) {
                        oldVeterinarioOfHospitalizacionCollectionNewHospitalizacion.getHospitalizacionCollection().remove(hospitalizacionCollectionNewHospitalizacion);
                        oldVeterinarioOfHospitalizacionCollectionNewHospitalizacion = em.merge(oldVeterinarioOfHospitalizacionCollectionNewHospitalizacion);
                    }
                }
            }
            for (AgendaDetalle agendaDetalleCollectionNewAgendaDetalle : agendaDetalleCollectionNew) {
                if (!agendaDetalleCollectionOld.contains(agendaDetalleCollectionNewAgendaDetalle)) {
                    Veterinario oldVeterinarioOfAgendaDetalleCollectionNewAgendaDetalle = agendaDetalleCollectionNewAgendaDetalle.getVeterinario();
                    agendaDetalleCollectionNewAgendaDetalle.setVeterinario(veterinario);
                    agendaDetalleCollectionNewAgendaDetalle = em.merge(agendaDetalleCollectionNewAgendaDetalle);
                    if (oldVeterinarioOfAgendaDetalleCollectionNewAgendaDetalle != null && !oldVeterinarioOfAgendaDetalleCollectionNewAgendaDetalle.equals(veterinario)) {
                        oldVeterinarioOfAgendaDetalleCollectionNewAgendaDetalle.getAgendaDetalleCollection().remove(agendaDetalleCollectionNewAgendaDetalle);
                        oldVeterinarioOfAgendaDetalleCollectionNewAgendaDetalle = em.merge(oldVeterinarioOfAgendaDetalleCollectionNewAgendaDetalle);
                    }
                }
            }
            for (Anamnesis anamnesisCollectionNewAnamnesis : anamnesisCollectionNew) {
                if (!anamnesisCollectionOld.contains(anamnesisCollectionNewAnamnesis)) {
                    Veterinario oldVeterinarioOfAnamnesisCollectionNewAnamnesis = anamnesisCollectionNewAnamnesis.getVeterinario();
                    anamnesisCollectionNewAnamnesis.setVeterinario(veterinario);
                    anamnesisCollectionNewAnamnesis = em.merge(anamnesisCollectionNewAnamnesis);
                    if (oldVeterinarioOfAnamnesisCollectionNewAnamnesis != null && !oldVeterinarioOfAnamnesisCollectionNewAnamnesis.equals(veterinario)) {
                        oldVeterinarioOfAnamnesisCollectionNewAnamnesis.getAnamnesisCollection().remove(anamnesisCollectionNewAnamnesis);
                        oldVeterinarioOfAnamnesisCollectionNewAnamnesis = em.merge(oldVeterinarioOfAnamnesisCollectionNewAnamnesis);
                    }
                }
            }
            for (Patologias patologiasCollectionNewPatologias : patologiasCollectionNew) {
                if (!patologiasCollectionOld.contains(patologiasCollectionNewPatologias)) {
                    Veterinario oldVeterinarioOfPatologiasCollectionNewPatologias = patologiasCollectionNewPatologias.getVeterinario();
                    patologiasCollectionNewPatologias.setVeterinario(veterinario);
                    patologiasCollectionNewPatologias = em.merge(patologiasCollectionNewPatologias);
                    if (oldVeterinarioOfPatologiasCollectionNewPatologias != null && !oldVeterinarioOfPatologiasCollectionNewPatologias.equals(veterinario)) {
                        oldVeterinarioOfPatologiasCollectionNewPatologias.getPatologiasCollection().remove(patologiasCollectionNewPatologias);
                        oldVeterinarioOfPatologiasCollectionNewPatologias = em.merge(oldVeterinarioOfPatologiasCollectionNewPatologias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = veterinario.getIdVeterinario();
                if (findVeterinario(id) == null) {
                    throw new NonexistentEntityException("The veterinario with id " + id + " no longer exists.");
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
            Veterinario veterinario;
            try {
                veterinario = em.getReference(Veterinario.class, id);
                veterinario.getIdVeterinario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veterinario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Examenes> examenesCollectionOrphanCheck = veterinario.getExamenesCollection();
            for (Examenes examenesCollectionOrphanCheckExamenes : examenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Examenes " + examenesCollectionOrphanCheckExamenes + " in its examenesCollection field has a non-nullable veterinario field.");
            }
            Collection<Historialvacunas> historialvacunasCollectionOrphanCheck = veterinario.getHistorialvacunasCollection();
            for (Historialvacunas historialvacunasCollectionOrphanCheckHistorialvacunas : historialvacunasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Historialvacunas " + historialvacunasCollectionOrphanCheckHistorialvacunas + " in its historialvacunasCollection field has a non-nullable veterinario field.");
            }
            Collection<Contraindicaciones> contraindicacionesCollectionOrphanCheck = veterinario.getContraindicacionesCollection();
            for (Contraindicaciones contraindicacionesCollectionOrphanCheckContraindicaciones : contraindicacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Contraindicaciones " + contraindicacionesCollectionOrphanCheckContraindicaciones + " in its contraindicacionesCollection field has a non-nullable veterinario field.");
            }
            Collection<Desparacitaciones> desparacitacionesCollectionOrphanCheck = veterinario.getDesparacitacionesCollection();
            for (Desparacitaciones desparacitacionesCollectionOrphanCheckDesparacitaciones : desparacitacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Desparacitaciones " + desparacitacionesCollectionOrphanCheckDesparacitaciones + " in its desparacitacionesCollection field has a non-nullable especialista field.");
            }
            Collection<Farmacos> farmacosCollectionOrphanCheck = veterinario.getFarmacosCollection();
            for (Farmacos farmacosCollectionOrphanCheckFarmacos : farmacosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Farmacos " + farmacosCollectionOrphanCheckFarmacos + " in its farmacosCollection field has a non-nullable veterinario field.");
            }
            Collection<Procedimientos> procedimientosCollectionOrphanCheck = veterinario.getProcedimientosCollection();
            for (Procedimientos procedimientosCollectionOrphanCheckProcedimientos : procedimientosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Procedimientos " + procedimientosCollectionOrphanCheckProcedimientos + " in its procedimientosCollection field has a non-nullable veterinario field.");
            }
            Collection<Hospitalizacion> hospitalizacionCollectionOrphanCheck = veterinario.getHospitalizacionCollection();
            for (Hospitalizacion hospitalizacionCollectionOrphanCheckHospitalizacion : hospitalizacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Hospitalizacion " + hospitalizacionCollectionOrphanCheckHospitalizacion + " in its hospitalizacionCollection field has a non-nullable veterinario field.");
            }
            Collection<AgendaDetalle> agendaDetalleCollectionOrphanCheck = veterinario.getAgendaDetalleCollection();
            for (AgendaDetalle agendaDetalleCollectionOrphanCheckAgendaDetalle : agendaDetalleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the AgendaDetalle " + agendaDetalleCollectionOrphanCheckAgendaDetalle + " in its agendaDetalleCollection field has a non-nullable veterinario field.");
            }
            Collection<Anamnesis> anamnesisCollectionOrphanCheck = veterinario.getAnamnesisCollection();
            for (Anamnesis anamnesisCollectionOrphanCheckAnamnesis : anamnesisCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Anamnesis " + anamnesisCollectionOrphanCheckAnamnesis + " in its anamnesisCollection field has a non-nullable veterinario field.");
            }
            Collection<Patologias> patologiasCollectionOrphanCheck = veterinario.getPatologiasCollection();
            for (Patologias patologiasCollectionOrphanCheckPatologias : patologiasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veterinario (" + veterinario + ") cannot be destroyed since the Patologias " + patologiasCollectionOrphanCheckPatologias + " in its patologiasCollection field has a non-nullable veterinario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sucursal sucursal = veterinario.getSucursal();
            if (sucursal != null) {
                sucursal.getVeterinarioCollection().remove(veterinario);
                sucursal = em.merge(sucursal);
            }
            em.remove(veterinario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Veterinario> findVeterinarioEntities() {
        return findVeterinarioEntities(true, -1, -1);
    }

    public List<Veterinario> findVeterinarioEntities(int maxResults, int firstResult) {
        return findVeterinarioEntities(false, maxResults, firstResult);
    }

    private List<Veterinario> findVeterinarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Veterinario.class));
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

    public Veterinario findVeterinario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Veterinario.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeterinarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Veterinario> rt = cq.from(Veterinario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
