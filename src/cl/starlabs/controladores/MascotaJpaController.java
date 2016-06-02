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
import cl.starlabs.modelo.Habitad;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.Propietario;
import cl.starlabs.modelo.Raza;
import cl.starlabs.modelo.Examenes;
import java.util.ArrayList;
import java.util.Collection;
import cl.starlabs.modelo.Historialvacunas;
import cl.starlabs.modelo.Contraindicaciones;
import cl.starlabs.modelo.Desparacitaciones;
import cl.starlabs.modelo.Alergias;
import cl.starlabs.modelo.Farmacos;
import cl.starlabs.modelo.Hospitalizacion;
import cl.starlabs.modelo.AgendaDetalle;
import cl.starlabs.modelo.Anamnesis;
import cl.starlabs.modelo.Patologias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class MascotaJpaController implements Serializable {

    public MascotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascota) throws PreexistingEntityException, Exception {
        if (mascota.getExamenesCollection() == null) {
            mascota.setExamenesCollection(new ArrayList<Examenes>());
        }
        if (mascota.getHistorialvacunasCollection() == null) {
            mascota.setHistorialvacunasCollection(new ArrayList<Historialvacunas>());
        }
        if (mascota.getContraindicacionesCollection() == null) {
            mascota.setContraindicacionesCollection(new ArrayList<Contraindicaciones>());
        }
        if (mascota.getDesparacitacionesCollection() == null) {
            mascota.setDesparacitacionesCollection(new ArrayList<Desparacitaciones>());
        }
        if (mascota.getAlergiasCollection() == null) {
            mascota.setAlergiasCollection(new ArrayList<Alergias>());
        }
        if (mascota.getMascotaCollection() == null) {
            mascota.setMascotaCollection(new ArrayList<Mascota>());
        }
        if (mascota.getMascotaCollection1() == null) {
            mascota.setMascotaCollection1(new ArrayList<Mascota>());
        }
        if (mascota.getFarmacosCollection() == null) {
            mascota.setFarmacosCollection(new ArrayList<Farmacos>());
        }
        if (mascota.getHospitalizacionCollection() == null) {
            mascota.setHospitalizacionCollection(new ArrayList<Hospitalizacion>());
        }
        if (mascota.getAgendaDetalleCollection() == null) {
            mascota.setAgendaDetalleCollection(new ArrayList<AgendaDetalle>());
        }
        if (mascota.getAnamnesisCollection() == null) {
            mascota.setAnamnesisCollection(new ArrayList<Anamnesis>());
        }
        if (mascota.getPatologiasCollection() == null) {
            mascota.setPatologiasCollection(new ArrayList<Patologias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitad habitad = mascota.getHabitad();
            if (habitad != null) {
                habitad = em.getReference(habitad.getClass(), habitad.getIdHabitad());
                mascota.setHabitad(habitad);
            }
            Mascota madre = mascota.getMadre();
            if (madre != null) {
                madre = em.getReference(madre.getClass(), madre.getIdMascota());
                mascota.setMadre(madre);
            }
            Mascota padre = mascota.getPadre();
            if (padre != null) {
                padre = em.getReference(padre.getClass(), padre.getIdMascota());
                mascota.setPadre(padre);
            }
            Propietario propietario = mascota.getPropietario();
            if (propietario != null) {
                propietario = em.getReference(propietario.getClass(), propietario.getIdPropietario());
                mascota.setPropietario(propietario);
            }
            Raza raza = mascota.getRaza();
            if (raza != null) {
                raza = em.getReference(raza.getClass(), raza.getIdRaza());
                mascota.setRaza(raza);
            }
            Collection<Examenes> attachedExamenesCollection = new ArrayList<Examenes>();
            for (Examenes examenesCollectionExamenesToAttach : mascota.getExamenesCollection()) {
                examenesCollectionExamenesToAttach = em.getReference(examenesCollectionExamenesToAttach.getClass(), examenesCollectionExamenesToAttach.getIdExamen());
                attachedExamenesCollection.add(examenesCollectionExamenesToAttach);
            }
            mascota.setExamenesCollection(attachedExamenesCollection);
            Collection<Historialvacunas> attachedHistorialvacunasCollection = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionHistorialvacunasToAttach : mascota.getHistorialvacunasCollection()) {
                historialvacunasCollectionHistorialvacunasToAttach = em.getReference(historialvacunasCollectionHistorialvacunasToAttach.getClass(), historialvacunasCollectionHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollection.add(historialvacunasCollectionHistorialvacunasToAttach);
            }
            mascota.setHistorialvacunasCollection(attachedHistorialvacunasCollection);
            Collection<Contraindicaciones> attachedContraindicacionesCollection = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionContraindicacionesToAttach : mascota.getContraindicacionesCollection()) {
                contraindicacionesCollectionContraindicacionesToAttach = em.getReference(contraindicacionesCollectionContraindicacionesToAttach.getClass(), contraindicacionesCollectionContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollection.add(contraindicacionesCollectionContraindicacionesToAttach);
            }
            mascota.setContraindicacionesCollection(attachedContraindicacionesCollection);
            Collection<Desparacitaciones> attachedDesparacitacionesCollection = new ArrayList<Desparacitaciones>();
            for (Desparacitaciones desparacitacionesCollectionDesparacitacionesToAttach : mascota.getDesparacitacionesCollection()) {
                desparacitacionesCollectionDesparacitacionesToAttach = em.getReference(desparacitacionesCollectionDesparacitacionesToAttach.getClass(), desparacitacionesCollectionDesparacitacionesToAttach.getIdDesparacitacion());
                attachedDesparacitacionesCollection.add(desparacitacionesCollectionDesparacitacionesToAttach);
            }
            mascota.setDesparacitacionesCollection(attachedDesparacitacionesCollection);
            Collection<Alergias> attachedAlergiasCollection = new ArrayList<Alergias>();
            for (Alergias alergiasCollectionAlergiasToAttach : mascota.getAlergiasCollection()) {
                alergiasCollectionAlergiasToAttach = em.getReference(alergiasCollectionAlergiasToAttach.getClass(), alergiasCollectionAlergiasToAttach.getIdAlergia());
                attachedAlergiasCollection.add(alergiasCollectionAlergiasToAttach);
            }
            mascota.setAlergiasCollection(attachedAlergiasCollection);
            Collection<Mascota> attachedMascotaCollection = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionMascotaToAttach : mascota.getMascotaCollection()) {
                mascotaCollectionMascotaToAttach = em.getReference(mascotaCollectionMascotaToAttach.getClass(), mascotaCollectionMascotaToAttach.getIdMascota());
                attachedMascotaCollection.add(mascotaCollectionMascotaToAttach);
            }
            mascota.setMascotaCollection(attachedMascotaCollection);
            Collection<Mascota> attachedMascotaCollection1 = new ArrayList<Mascota>();
            for (Mascota mascotaCollection1MascotaToAttach : mascota.getMascotaCollection1()) {
                mascotaCollection1MascotaToAttach = em.getReference(mascotaCollection1MascotaToAttach.getClass(), mascotaCollection1MascotaToAttach.getIdMascota());
                attachedMascotaCollection1.add(mascotaCollection1MascotaToAttach);
            }
            mascota.setMascotaCollection1(attachedMascotaCollection1);
            Collection<Farmacos> attachedFarmacosCollection = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionFarmacosToAttach : mascota.getFarmacosCollection()) {
                farmacosCollectionFarmacosToAttach = em.getReference(farmacosCollectionFarmacosToAttach.getClass(), farmacosCollectionFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollection.add(farmacosCollectionFarmacosToAttach);
            }
            mascota.setFarmacosCollection(attachedFarmacosCollection);
            Collection<Hospitalizacion> attachedHospitalizacionCollection = new ArrayList<Hospitalizacion>();
            for (Hospitalizacion hospitalizacionCollectionHospitalizacionToAttach : mascota.getHospitalizacionCollection()) {
                hospitalizacionCollectionHospitalizacionToAttach = em.getReference(hospitalizacionCollectionHospitalizacionToAttach.getClass(), hospitalizacionCollectionHospitalizacionToAttach.getIdHospitalizacion());
                attachedHospitalizacionCollection.add(hospitalizacionCollectionHospitalizacionToAttach);
            }
            mascota.setHospitalizacionCollection(attachedHospitalizacionCollection);
            Collection<AgendaDetalle> attachedAgendaDetalleCollection = new ArrayList<AgendaDetalle>();
            for (AgendaDetalle agendaDetalleCollectionAgendaDetalleToAttach : mascota.getAgendaDetalleCollection()) {
                agendaDetalleCollectionAgendaDetalleToAttach = em.getReference(agendaDetalleCollectionAgendaDetalleToAttach.getClass(), agendaDetalleCollectionAgendaDetalleToAttach.getIdDetalle());
                attachedAgendaDetalleCollection.add(agendaDetalleCollectionAgendaDetalleToAttach);
            }
            mascota.setAgendaDetalleCollection(attachedAgendaDetalleCollection);
            Collection<Anamnesis> attachedAnamnesisCollection = new ArrayList<Anamnesis>();
            for (Anamnesis anamnesisCollectionAnamnesisToAttach : mascota.getAnamnesisCollection()) {
                anamnesisCollectionAnamnesisToAttach = em.getReference(anamnesisCollectionAnamnesisToAttach.getClass(), anamnesisCollectionAnamnesisToAttach.getIdAnamnesis());
                attachedAnamnesisCollection.add(anamnesisCollectionAnamnesisToAttach);
            }
            mascota.setAnamnesisCollection(attachedAnamnesisCollection);
            Collection<Patologias> attachedPatologiasCollection = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionPatologiasToAttach : mascota.getPatologiasCollection()) {
                patologiasCollectionPatologiasToAttach = em.getReference(patologiasCollectionPatologiasToAttach.getClass(), patologiasCollectionPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollection.add(patologiasCollectionPatologiasToAttach);
            }
            mascota.setPatologiasCollection(attachedPatologiasCollection);
            em.persist(mascota);
            if (habitad != null) {
                habitad.getMascotaCollection().add(mascota);
                habitad = em.merge(habitad);
            }
            if (madre != null) {
                madre.getMascotaCollection().add(mascota);
                madre = em.merge(madre);
            }
            if (padre != null) {
                padre.getMascotaCollection().add(mascota);
                padre = em.merge(padre);
            }
            if (propietario != null) {
                propietario.getMascotaCollection().add(mascota);
                propietario = em.merge(propietario);
            }
            if (raza != null) {
                raza.getMascotaCollection().add(mascota);
                raza = em.merge(raza);
            }
            for (Examenes examenesCollectionExamenes : mascota.getExamenesCollection()) {
                Mascota oldMascotaOfExamenesCollectionExamenes = examenesCollectionExamenes.getMascota();
                examenesCollectionExamenes.setMascota(mascota);
                examenesCollectionExamenes = em.merge(examenesCollectionExamenes);
                if (oldMascotaOfExamenesCollectionExamenes != null) {
                    oldMascotaOfExamenesCollectionExamenes.getExamenesCollection().remove(examenesCollectionExamenes);
                    oldMascotaOfExamenesCollectionExamenes = em.merge(oldMascotaOfExamenesCollectionExamenes);
                }
            }
            for (Historialvacunas historialvacunasCollectionHistorialvacunas : mascota.getHistorialvacunasCollection()) {
                Mascota oldMascotaOfHistorialvacunasCollectionHistorialvacunas = historialvacunasCollectionHistorialvacunas.getMascota();
                historialvacunasCollectionHistorialvacunas.setMascota(mascota);
                historialvacunasCollectionHistorialvacunas = em.merge(historialvacunasCollectionHistorialvacunas);
                if (oldMascotaOfHistorialvacunasCollectionHistorialvacunas != null) {
                    oldMascotaOfHistorialvacunasCollectionHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionHistorialvacunas);
                    oldMascotaOfHistorialvacunasCollectionHistorialvacunas = em.merge(oldMascotaOfHistorialvacunasCollectionHistorialvacunas);
                }
            }
            for (Contraindicaciones contraindicacionesCollectionContraindicaciones : mascota.getContraindicacionesCollection()) {
                Mascota oldMascotaOfContraindicacionesCollectionContraindicaciones = contraindicacionesCollectionContraindicaciones.getMascota();
                contraindicacionesCollectionContraindicaciones.setMascota(mascota);
                contraindicacionesCollectionContraindicaciones = em.merge(contraindicacionesCollectionContraindicaciones);
                if (oldMascotaOfContraindicacionesCollectionContraindicaciones != null) {
                    oldMascotaOfContraindicacionesCollectionContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionContraindicaciones);
                    oldMascotaOfContraindicacionesCollectionContraindicaciones = em.merge(oldMascotaOfContraindicacionesCollectionContraindicaciones);
                }
            }
            for (Desparacitaciones desparacitacionesCollectionDesparacitaciones : mascota.getDesparacitacionesCollection()) {
                Mascota oldMascotaOfDesparacitacionesCollectionDesparacitaciones = desparacitacionesCollectionDesparacitaciones.getMascota();
                desparacitacionesCollectionDesparacitaciones.setMascota(mascota);
                desparacitacionesCollectionDesparacitaciones = em.merge(desparacitacionesCollectionDesparacitaciones);
                if (oldMascotaOfDesparacitacionesCollectionDesparacitaciones != null) {
                    oldMascotaOfDesparacitacionesCollectionDesparacitaciones.getDesparacitacionesCollection().remove(desparacitacionesCollectionDesparacitaciones);
                    oldMascotaOfDesparacitacionesCollectionDesparacitaciones = em.merge(oldMascotaOfDesparacitacionesCollectionDesparacitaciones);
                }
            }
            for (Alergias alergiasCollectionAlergias : mascota.getAlergiasCollection()) {
                Mascota oldMascotaOfAlergiasCollectionAlergias = alergiasCollectionAlergias.getMascota();
                alergiasCollectionAlergias.setMascota(mascota);
                alergiasCollectionAlergias = em.merge(alergiasCollectionAlergias);
                if (oldMascotaOfAlergiasCollectionAlergias != null) {
                    oldMascotaOfAlergiasCollectionAlergias.getAlergiasCollection().remove(alergiasCollectionAlergias);
                    oldMascotaOfAlergiasCollectionAlergias = em.merge(oldMascotaOfAlergiasCollectionAlergias);
                }
            }
            for (Mascota mascotaCollectionMascota : mascota.getMascotaCollection()) {
                Mascota oldMadreOfMascotaCollectionMascota = mascotaCollectionMascota.getMadre();
                mascotaCollectionMascota.setMadre(mascota);
                mascotaCollectionMascota = em.merge(mascotaCollectionMascota);
                if (oldMadreOfMascotaCollectionMascota != null) {
                    oldMadreOfMascotaCollectionMascota.getMascotaCollection().remove(mascotaCollectionMascota);
                    oldMadreOfMascotaCollectionMascota = em.merge(oldMadreOfMascotaCollectionMascota);
                }
            }
            for (Mascota mascotaCollection1Mascota : mascota.getMascotaCollection1()) {
                Mascota oldPadreOfMascotaCollection1Mascota = mascotaCollection1Mascota.getPadre();
                mascotaCollection1Mascota.setPadre(mascota);
                mascotaCollection1Mascota = em.merge(mascotaCollection1Mascota);
                if (oldPadreOfMascotaCollection1Mascota != null) {
                    oldPadreOfMascotaCollection1Mascota.getMascotaCollection1().remove(mascotaCollection1Mascota);
                    oldPadreOfMascotaCollection1Mascota = em.merge(oldPadreOfMascotaCollection1Mascota);
                }
            }
            for (Farmacos farmacosCollectionFarmacos : mascota.getFarmacosCollection()) {
                Mascota oldMascotaOfFarmacosCollectionFarmacos = farmacosCollectionFarmacos.getMascota();
                farmacosCollectionFarmacos.setMascota(mascota);
                farmacosCollectionFarmacos = em.merge(farmacosCollectionFarmacos);
                if (oldMascotaOfFarmacosCollectionFarmacos != null) {
                    oldMascotaOfFarmacosCollectionFarmacos.getFarmacosCollection().remove(farmacosCollectionFarmacos);
                    oldMascotaOfFarmacosCollectionFarmacos = em.merge(oldMascotaOfFarmacosCollectionFarmacos);
                }
            }
            for (Hospitalizacion hospitalizacionCollectionHospitalizacion : mascota.getHospitalizacionCollection()) {
                Mascota oldMascotaOfHospitalizacionCollectionHospitalizacion = hospitalizacionCollectionHospitalizacion.getMascota();
                hospitalizacionCollectionHospitalizacion.setMascota(mascota);
                hospitalizacionCollectionHospitalizacion = em.merge(hospitalizacionCollectionHospitalizacion);
                if (oldMascotaOfHospitalizacionCollectionHospitalizacion != null) {
                    oldMascotaOfHospitalizacionCollectionHospitalizacion.getHospitalizacionCollection().remove(hospitalizacionCollectionHospitalizacion);
                    oldMascotaOfHospitalizacionCollectionHospitalizacion = em.merge(oldMascotaOfHospitalizacionCollectionHospitalizacion);
                }
            }
            for (AgendaDetalle agendaDetalleCollectionAgendaDetalle : mascota.getAgendaDetalleCollection()) {
                Mascota oldMascotaOfAgendaDetalleCollectionAgendaDetalle = agendaDetalleCollectionAgendaDetalle.getMascota();
                agendaDetalleCollectionAgendaDetalle.setMascota(mascota);
                agendaDetalleCollectionAgendaDetalle = em.merge(agendaDetalleCollectionAgendaDetalle);
                if (oldMascotaOfAgendaDetalleCollectionAgendaDetalle != null) {
                    oldMascotaOfAgendaDetalleCollectionAgendaDetalle.getAgendaDetalleCollection().remove(agendaDetalleCollectionAgendaDetalle);
                    oldMascotaOfAgendaDetalleCollectionAgendaDetalle = em.merge(oldMascotaOfAgendaDetalleCollectionAgendaDetalle);
                }
            }
            for (Anamnesis anamnesisCollectionAnamnesis : mascota.getAnamnesisCollection()) {
                Mascota oldMascotaOfAnamnesisCollectionAnamnesis = anamnesisCollectionAnamnesis.getMascota();
                anamnesisCollectionAnamnesis.setMascota(mascota);
                anamnesisCollectionAnamnesis = em.merge(anamnesisCollectionAnamnesis);
                if (oldMascotaOfAnamnesisCollectionAnamnesis != null) {
                    oldMascotaOfAnamnesisCollectionAnamnesis.getAnamnesisCollection().remove(anamnesisCollectionAnamnesis);
                    oldMascotaOfAnamnesisCollectionAnamnesis = em.merge(oldMascotaOfAnamnesisCollectionAnamnesis);
                }
            }
            for (Patologias patologiasCollectionPatologias : mascota.getPatologiasCollection()) {
                Mascota oldMascotaOfPatologiasCollectionPatologias = patologiasCollectionPatologias.getMascota();
                patologiasCollectionPatologias.setMascota(mascota);
                patologiasCollectionPatologias = em.merge(patologiasCollectionPatologias);
                if (oldMascotaOfPatologiasCollectionPatologias != null) {
                    oldMascotaOfPatologiasCollectionPatologias.getPatologiasCollection().remove(patologiasCollectionPatologias);
                    oldMascotaOfPatologiasCollectionPatologias = em.merge(oldMascotaOfPatologiasCollectionPatologias);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMascota(mascota.getIdMascota()) != null) {
                throw new PreexistingEntityException("Mascota " + mascota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascota) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota persistentMascota = em.find(Mascota.class, mascota.getIdMascota());
            Habitad habitadOld = persistentMascota.getHabitad();
            Habitad habitadNew = mascota.getHabitad();
            Mascota madreOld = persistentMascota.getMadre();
            Mascota madreNew = mascota.getMadre();
            Mascota padreOld = persistentMascota.getPadre();
            Mascota padreNew = mascota.getPadre();
            Propietario propietarioOld = persistentMascota.getPropietario();
            Propietario propietarioNew = mascota.getPropietario();
            Raza razaOld = persistentMascota.getRaza();
            Raza razaNew = mascota.getRaza();
            Collection<Examenes> examenesCollectionOld = persistentMascota.getExamenesCollection();
            Collection<Examenes> examenesCollectionNew = mascota.getExamenesCollection();
            Collection<Historialvacunas> historialvacunasCollectionOld = persistentMascota.getHistorialvacunasCollection();
            Collection<Historialvacunas> historialvacunasCollectionNew = mascota.getHistorialvacunasCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionOld = persistentMascota.getContraindicacionesCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionNew = mascota.getContraindicacionesCollection();
            Collection<Desparacitaciones> desparacitacionesCollectionOld = persistentMascota.getDesparacitacionesCollection();
            Collection<Desparacitaciones> desparacitacionesCollectionNew = mascota.getDesparacitacionesCollection();
            Collection<Alergias> alergiasCollectionOld = persistentMascota.getAlergiasCollection();
            Collection<Alergias> alergiasCollectionNew = mascota.getAlergiasCollection();
            Collection<Mascota> mascotaCollectionOld = persistentMascota.getMascotaCollection();
            Collection<Mascota> mascotaCollectionNew = mascota.getMascotaCollection();
            Collection<Mascota> mascotaCollection1Old = persistentMascota.getMascotaCollection1();
            Collection<Mascota> mascotaCollection1New = mascota.getMascotaCollection1();
            Collection<Farmacos> farmacosCollectionOld = persistentMascota.getFarmacosCollection();
            Collection<Farmacos> farmacosCollectionNew = mascota.getFarmacosCollection();
            Collection<Hospitalizacion> hospitalizacionCollectionOld = persistentMascota.getHospitalizacionCollection();
            Collection<Hospitalizacion> hospitalizacionCollectionNew = mascota.getHospitalizacionCollection();
            Collection<AgendaDetalle> agendaDetalleCollectionOld = persistentMascota.getAgendaDetalleCollection();
            Collection<AgendaDetalle> agendaDetalleCollectionNew = mascota.getAgendaDetalleCollection();
            Collection<Anamnesis> anamnesisCollectionOld = persistentMascota.getAnamnesisCollection();
            Collection<Anamnesis> anamnesisCollectionNew = mascota.getAnamnesisCollection();
            Collection<Patologias> patologiasCollectionOld = persistentMascota.getPatologiasCollection();
            Collection<Patologias> patologiasCollectionNew = mascota.getPatologiasCollection();
            List<String> illegalOrphanMessages = null;
            for (Examenes examenesCollectionOldExamenes : examenesCollectionOld) {
                if (!examenesCollectionNew.contains(examenesCollectionOldExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Examenes " + examenesCollectionOldExamenes + " since its mascota field is not nullable.");
                }
            }
            for (Historialvacunas historialvacunasCollectionOldHistorialvacunas : historialvacunasCollectionOld) {
                if (!historialvacunasCollectionNew.contains(historialvacunasCollectionOldHistorialvacunas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialvacunas " + historialvacunasCollectionOldHistorialvacunas + " since its mascota field is not nullable.");
                }
            }
            for (Contraindicaciones contraindicacionesCollectionOldContraindicaciones : contraindicacionesCollectionOld) {
                if (!contraindicacionesCollectionNew.contains(contraindicacionesCollectionOldContraindicaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contraindicaciones " + contraindicacionesCollectionOldContraindicaciones + " since its mascota field is not nullable.");
                }
            }
            for (Desparacitaciones desparacitacionesCollectionOldDesparacitaciones : desparacitacionesCollectionOld) {
                if (!desparacitacionesCollectionNew.contains(desparacitacionesCollectionOldDesparacitaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Desparacitaciones " + desparacitacionesCollectionOldDesparacitaciones + " since its mascota field is not nullable.");
                }
            }
            for (Alergias alergiasCollectionOldAlergias : alergiasCollectionOld) {
                if (!alergiasCollectionNew.contains(alergiasCollectionOldAlergias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alergias " + alergiasCollectionOldAlergias + " since its mascota field is not nullable.");
                }
            }
            for (Farmacos farmacosCollectionOldFarmacos : farmacosCollectionOld) {
                if (!farmacosCollectionNew.contains(farmacosCollectionOldFarmacos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Farmacos " + farmacosCollectionOldFarmacos + " since its mascota field is not nullable.");
                }
            }
            for (Hospitalizacion hospitalizacionCollectionOldHospitalizacion : hospitalizacionCollectionOld) {
                if (!hospitalizacionCollectionNew.contains(hospitalizacionCollectionOldHospitalizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Hospitalizacion " + hospitalizacionCollectionOldHospitalizacion + " since its mascota field is not nullable.");
                }
            }
            for (AgendaDetalle agendaDetalleCollectionOldAgendaDetalle : agendaDetalleCollectionOld) {
                if (!agendaDetalleCollectionNew.contains(agendaDetalleCollectionOldAgendaDetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AgendaDetalle " + agendaDetalleCollectionOldAgendaDetalle + " since its mascota field is not nullable.");
                }
            }
            for (Anamnesis anamnesisCollectionOldAnamnesis : anamnesisCollectionOld) {
                if (!anamnesisCollectionNew.contains(anamnesisCollectionOldAnamnesis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Anamnesis " + anamnesisCollectionOldAnamnesis + " since its mascota field is not nullable.");
                }
            }
            for (Patologias patologiasCollectionOldPatologias : patologiasCollectionOld) {
                if (!patologiasCollectionNew.contains(patologiasCollectionOldPatologias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Patologias " + patologiasCollectionOldPatologias + " since its mascota field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (habitadNew != null) {
                habitadNew = em.getReference(habitadNew.getClass(), habitadNew.getIdHabitad());
                mascota.setHabitad(habitadNew);
            }
            if (madreNew != null) {
                madreNew = em.getReference(madreNew.getClass(), madreNew.getIdMascota());
                mascota.setMadre(madreNew);
            }
            if (padreNew != null) {
                padreNew = em.getReference(padreNew.getClass(), padreNew.getIdMascota());
                mascota.setPadre(padreNew);
            }
            if (propietarioNew != null) {
                propietarioNew = em.getReference(propietarioNew.getClass(), propietarioNew.getIdPropietario());
                mascota.setPropietario(propietarioNew);
            }
            if (razaNew != null) {
                razaNew = em.getReference(razaNew.getClass(), razaNew.getIdRaza());
                mascota.setRaza(razaNew);
            }
            Collection<Examenes> attachedExamenesCollectionNew = new ArrayList<Examenes>();
            for (Examenes examenesCollectionNewExamenesToAttach : examenesCollectionNew) {
                examenesCollectionNewExamenesToAttach = em.getReference(examenesCollectionNewExamenesToAttach.getClass(), examenesCollectionNewExamenesToAttach.getIdExamen());
                attachedExamenesCollectionNew.add(examenesCollectionNewExamenesToAttach);
            }
            examenesCollectionNew = attachedExamenesCollectionNew;
            mascota.setExamenesCollection(examenesCollectionNew);
            Collection<Historialvacunas> attachedHistorialvacunasCollectionNew = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunasToAttach : historialvacunasCollectionNew) {
                historialvacunasCollectionNewHistorialvacunasToAttach = em.getReference(historialvacunasCollectionNewHistorialvacunasToAttach.getClass(), historialvacunasCollectionNewHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollectionNew.add(historialvacunasCollectionNewHistorialvacunasToAttach);
            }
            historialvacunasCollectionNew = attachedHistorialvacunasCollectionNew;
            mascota.setHistorialvacunasCollection(historialvacunasCollectionNew);
            Collection<Contraindicaciones> attachedContraindicacionesCollectionNew = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionNewContraindicacionesToAttach : contraindicacionesCollectionNew) {
                contraindicacionesCollectionNewContraindicacionesToAttach = em.getReference(contraindicacionesCollectionNewContraindicacionesToAttach.getClass(), contraindicacionesCollectionNewContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollectionNew.add(contraindicacionesCollectionNewContraindicacionesToAttach);
            }
            contraindicacionesCollectionNew = attachedContraindicacionesCollectionNew;
            mascota.setContraindicacionesCollection(contraindicacionesCollectionNew);
            Collection<Desparacitaciones> attachedDesparacitacionesCollectionNew = new ArrayList<Desparacitaciones>();
            for (Desparacitaciones desparacitacionesCollectionNewDesparacitacionesToAttach : desparacitacionesCollectionNew) {
                desparacitacionesCollectionNewDesparacitacionesToAttach = em.getReference(desparacitacionesCollectionNewDesparacitacionesToAttach.getClass(), desparacitacionesCollectionNewDesparacitacionesToAttach.getIdDesparacitacion());
                attachedDesparacitacionesCollectionNew.add(desparacitacionesCollectionNewDesparacitacionesToAttach);
            }
            desparacitacionesCollectionNew = attachedDesparacitacionesCollectionNew;
            mascota.setDesparacitacionesCollection(desparacitacionesCollectionNew);
            Collection<Alergias> attachedAlergiasCollectionNew = new ArrayList<Alergias>();
            for (Alergias alergiasCollectionNewAlergiasToAttach : alergiasCollectionNew) {
                alergiasCollectionNewAlergiasToAttach = em.getReference(alergiasCollectionNewAlergiasToAttach.getClass(), alergiasCollectionNewAlergiasToAttach.getIdAlergia());
                attachedAlergiasCollectionNew.add(alergiasCollectionNewAlergiasToAttach);
            }
            alergiasCollectionNew = attachedAlergiasCollectionNew;
            mascota.setAlergiasCollection(alergiasCollectionNew);
            Collection<Mascota> attachedMascotaCollectionNew = new ArrayList<Mascota>();
            for (Mascota mascotaCollectionNewMascotaToAttach : mascotaCollectionNew) {
                mascotaCollectionNewMascotaToAttach = em.getReference(mascotaCollectionNewMascotaToAttach.getClass(), mascotaCollectionNewMascotaToAttach.getIdMascota());
                attachedMascotaCollectionNew.add(mascotaCollectionNewMascotaToAttach);
            }
            mascotaCollectionNew = attachedMascotaCollectionNew;
            mascota.setMascotaCollection(mascotaCollectionNew);
            Collection<Mascota> attachedMascotaCollection1New = new ArrayList<Mascota>();
            for (Mascota mascotaCollection1NewMascotaToAttach : mascotaCollection1New) {
                mascotaCollection1NewMascotaToAttach = em.getReference(mascotaCollection1NewMascotaToAttach.getClass(), mascotaCollection1NewMascotaToAttach.getIdMascota());
                attachedMascotaCollection1New.add(mascotaCollection1NewMascotaToAttach);
            }
            mascotaCollection1New = attachedMascotaCollection1New;
            mascota.setMascotaCollection1(mascotaCollection1New);
            Collection<Farmacos> attachedFarmacosCollectionNew = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionNewFarmacosToAttach : farmacosCollectionNew) {
                farmacosCollectionNewFarmacosToAttach = em.getReference(farmacosCollectionNewFarmacosToAttach.getClass(), farmacosCollectionNewFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollectionNew.add(farmacosCollectionNewFarmacosToAttach);
            }
            farmacosCollectionNew = attachedFarmacosCollectionNew;
            mascota.setFarmacosCollection(farmacosCollectionNew);
            Collection<Hospitalizacion> attachedHospitalizacionCollectionNew = new ArrayList<Hospitalizacion>();
            for (Hospitalizacion hospitalizacionCollectionNewHospitalizacionToAttach : hospitalizacionCollectionNew) {
                hospitalizacionCollectionNewHospitalizacionToAttach = em.getReference(hospitalizacionCollectionNewHospitalizacionToAttach.getClass(), hospitalizacionCollectionNewHospitalizacionToAttach.getIdHospitalizacion());
                attachedHospitalizacionCollectionNew.add(hospitalizacionCollectionNewHospitalizacionToAttach);
            }
            hospitalizacionCollectionNew = attachedHospitalizacionCollectionNew;
            mascota.setHospitalizacionCollection(hospitalizacionCollectionNew);
            Collection<AgendaDetalle> attachedAgendaDetalleCollectionNew = new ArrayList<AgendaDetalle>();
            for (AgendaDetalle agendaDetalleCollectionNewAgendaDetalleToAttach : agendaDetalleCollectionNew) {
                agendaDetalleCollectionNewAgendaDetalleToAttach = em.getReference(agendaDetalleCollectionNewAgendaDetalleToAttach.getClass(), agendaDetalleCollectionNewAgendaDetalleToAttach.getIdDetalle());
                attachedAgendaDetalleCollectionNew.add(agendaDetalleCollectionNewAgendaDetalleToAttach);
            }
            agendaDetalleCollectionNew = attachedAgendaDetalleCollectionNew;
            mascota.setAgendaDetalleCollection(agendaDetalleCollectionNew);
            Collection<Anamnesis> attachedAnamnesisCollectionNew = new ArrayList<Anamnesis>();
            for (Anamnesis anamnesisCollectionNewAnamnesisToAttach : anamnesisCollectionNew) {
                anamnesisCollectionNewAnamnesisToAttach = em.getReference(anamnesisCollectionNewAnamnesisToAttach.getClass(), anamnesisCollectionNewAnamnesisToAttach.getIdAnamnesis());
                attachedAnamnesisCollectionNew.add(anamnesisCollectionNewAnamnesisToAttach);
            }
            anamnesisCollectionNew = attachedAnamnesisCollectionNew;
            mascota.setAnamnesisCollection(anamnesisCollectionNew);
            Collection<Patologias> attachedPatologiasCollectionNew = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionNewPatologiasToAttach : patologiasCollectionNew) {
                patologiasCollectionNewPatologiasToAttach = em.getReference(patologiasCollectionNewPatologiasToAttach.getClass(), patologiasCollectionNewPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollectionNew.add(patologiasCollectionNewPatologiasToAttach);
            }
            patologiasCollectionNew = attachedPatologiasCollectionNew;
            mascota.setPatologiasCollection(patologiasCollectionNew);
            mascota = em.merge(mascota);
            if (habitadOld != null && !habitadOld.equals(habitadNew)) {
                habitadOld.getMascotaCollection().remove(mascota);
                habitadOld = em.merge(habitadOld);
            }
            if (habitadNew != null && !habitadNew.equals(habitadOld)) {
                habitadNew.getMascotaCollection().add(mascota);
                habitadNew = em.merge(habitadNew);
            }
            if (madreOld != null && !madreOld.equals(madreNew)) {
                madreOld.getMascotaCollection().remove(mascota);
                madreOld = em.merge(madreOld);
            }
            if (madreNew != null && !madreNew.equals(madreOld)) {
                madreNew.getMascotaCollection().add(mascota);
                madreNew = em.merge(madreNew);
            }
            if (padreOld != null && !padreOld.equals(padreNew)) {
                padreOld.getMascotaCollection().remove(mascota);
                padreOld = em.merge(padreOld);
            }
            if (padreNew != null && !padreNew.equals(padreOld)) {
                padreNew.getMascotaCollection().add(mascota);
                padreNew = em.merge(padreNew);
            }
            if (propietarioOld != null && !propietarioOld.equals(propietarioNew)) {
                propietarioOld.getMascotaCollection().remove(mascota);
                propietarioOld = em.merge(propietarioOld);
            }
            if (propietarioNew != null && !propietarioNew.equals(propietarioOld)) {
                propietarioNew.getMascotaCollection().add(mascota);
                propietarioNew = em.merge(propietarioNew);
            }
            if (razaOld != null && !razaOld.equals(razaNew)) {
                razaOld.getMascotaCollection().remove(mascota);
                razaOld = em.merge(razaOld);
            }
            if (razaNew != null && !razaNew.equals(razaOld)) {
                razaNew.getMascotaCollection().add(mascota);
                razaNew = em.merge(razaNew);
            }
            for (Examenes examenesCollectionNewExamenes : examenesCollectionNew) {
                if (!examenesCollectionOld.contains(examenesCollectionNewExamenes)) {
                    Mascota oldMascotaOfExamenesCollectionNewExamenes = examenesCollectionNewExamenes.getMascota();
                    examenesCollectionNewExamenes.setMascota(mascota);
                    examenesCollectionNewExamenes = em.merge(examenesCollectionNewExamenes);
                    if (oldMascotaOfExamenesCollectionNewExamenes != null && !oldMascotaOfExamenesCollectionNewExamenes.equals(mascota)) {
                        oldMascotaOfExamenesCollectionNewExamenes.getExamenesCollection().remove(examenesCollectionNewExamenes);
                        oldMascotaOfExamenesCollectionNewExamenes = em.merge(oldMascotaOfExamenesCollectionNewExamenes);
                    }
                }
            }
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunas : historialvacunasCollectionNew) {
                if (!historialvacunasCollectionOld.contains(historialvacunasCollectionNewHistorialvacunas)) {
                    Mascota oldMascotaOfHistorialvacunasCollectionNewHistorialvacunas = historialvacunasCollectionNewHistorialvacunas.getMascota();
                    historialvacunasCollectionNewHistorialvacunas.setMascota(mascota);
                    historialvacunasCollectionNewHistorialvacunas = em.merge(historialvacunasCollectionNewHistorialvacunas);
                    if (oldMascotaOfHistorialvacunasCollectionNewHistorialvacunas != null && !oldMascotaOfHistorialvacunasCollectionNewHistorialvacunas.equals(mascota)) {
                        oldMascotaOfHistorialvacunasCollectionNewHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionNewHistorialvacunas);
                        oldMascotaOfHistorialvacunasCollectionNewHistorialvacunas = em.merge(oldMascotaOfHistorialvacunasCollectionNewHistorialvacunas);
                    }
                }
            }
            for (Contraindicaciones contraindicacionesCollectionNewContraindicaciones : contraindicacionesCollectionNew) {
                if (!contraindicacionesCollectionOld.contains(contraindicacionesCollectionNewContraindicaciones)) {
                    Mascota oldMascotaOfContraindicacionesCollectionNewContraindicaciones = contraindicacionesCollectionNewContraindicaciones.getMascota();
                    contraindicacionesCollectionNewContraindicaciones.setMascota(mascota);
                    contraindicacionesCollectionNewContraindicaciones = em.merge(contraindicacionesCollectionNewContraindicaciones);
                    if (oldMascotaOfContraindicacionesCollectionNewContraindicaciones != null && !oldMascotaOfContraindicacionesCollectionNewContraindicaciones.equals(mascota)) {
                        oldMascotaOfContraindicacionesCollectionNewContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionNewContraindicaciones);
                        oldMascotaOfContraindicacionesCollectionNewContraindicaciones = em.merge(oldMascotaOfContraindicacionesCollectionNewContraindicaciones);
                    }
                }
            }
            for (Desparacitaciones desparacitacionesCollectionNewDesparacitaciones : desparacitacionesCollectionNew) {
                if (!desparacitacionesCollectionOld.contains(desparacitacionesCollectionNewDesparacitaciones)) {
                    Mascota oldMascotaOfDesparacitacionesCollectionNewDesparacitaciones = desparacitacionesCollectionNewDesparacitaciones.getMascota();
                    desparacitacionesCollectionNewDesparacitaciones.setMascota(mascota);
                    desparacitacionesCollectionNewDesparacitaciones = em.merge(desparacitacionesCollectionNewDesparacitaciones);
                    if (oldMascotaOfDesparacitacionesCollectionNewDesparacitaciones != null && !oldMascotaOfDesparacitacionesCollectionNewDesparacitaciones.equals(mascota)) {
                        oldMascotaOfDesparacitacionesCollectionNewDesparacitaciones.getDesparacitacionesCollection().remove(desparacitacionesCollectionNewDesparacitaciones);
                        oldMascotaOfDesparacitacionesCollectionNewDesparacitaciones = em.merge(oldMascotaOfDesparacitacionesCollectionNewDesparacitaciones);
                    }
                }
            }
            for (Alergias alergiasCollectionNewAlergias : alergiasCollectionNew) {
                if (!alergiasCollectionOld.contains(alergiasCollectionNewAlergias)) {
                    Mascota oldMascotaOfAlergiasCollectionNewAlergias = alergiasCollectionNewAlergias.getMascota();
                    alergiasCollectionNewAlergias.setMascota(mascota);
                    alergiasCollectionNewAlergias = em.merge(alergiasCollectionNewAlergias);
                    if (oldMascotaOfAlergiasCollectionNewAlergias != null && !oldMascotaOfAlergiasCollectionNewAlergias.equals(mascota)) {
                        oldMascotaOfAlergiasCollectionNewAlergias.getAlergiasCollection().remove(alergiasCollectionNewAlergias);
                        oldMascotaOfAlergiasCollectionNewAlergias = em.merge(oldMascotaOfAlergiasCollectionNewAlergias);
                    }
                }
            }
            for (Mascota mascotaCollectionOldMascota : mascotaCollectionOld) {
                if (!mascotaCollectionNew.contains(mascotaCollectionOldMascota)) {
                    mascotaCollectionOldMascota.setMadre(null);
                    mascotaCollectionOldMascota = em.merge(mascotaCollectionOldMascota);
                }
            }
            for (Mascota mascotaCollectionNewMascota : mascotaCollectionNew) {
                if (!mascotaCollectionOld.contains(mascotaCollectionNewMascota)) {
                    Mascota oldMadreOfMascotaCollectionNewMascota = mascotaCollectionNewMascota.getMadre();
                    mascotaCollectionNewMascota.setMadre(mascota);
                    mascotaCollectionNewMascota = em.merge(mascotaCollectionNewMascota);
                    if (oldMadreOfMascotaCollectionNewMascota != null && !oldMadreOfMascotaCollectionNewMascota.equals(mascota)) {
                        oldMadreOfMascotaCollectionNewMascota.getMascotaCollection().remove(mascotaCollectionNewMascota);
                        oldMadreOfMascotaCollectionNewMascota = em.merge(oldMadreOfMascotaCollectionNewMascota);
                    }
                }
            }
            for (Mascota mascotaCollection1OldMascota : mascotaCollection1Old) {
                if (!mascotaCollection1New.contains(mascotaCollection1OldMascota)) {
                    mascotaCollection1OldMascota.setPadre(null);
                    mascotaCollection1OldMascota = em.merge(mascotaCollection1OldMascota);
                }
            }
            for (Mascota mascotaCollection1NewMascota : mascotaCollection1New) {
                if (!mascotaCollection1Old.contains(mascotaCollection1NewMascota)) {
                    Mascota oldPadreOfMascotaCollection1NewMascota = mascotaCollection1NewMascota.getPadre();
                    mascotaCollection1NewMascota.setPadre(mascota);
                    mascotaCollection1NewMascota = em.merge(mascotaCollection1NewMascota);
                    if (oldPadreOfMascotaCollection1NewMascota != null && !oldPadreOfMascotaCollection1NewMascota.equals(mascota)) {
                        oldPadreOfMascotaCollection1NewMascota.getMascotaCollection1().remove(mascotaCollection1NewMascota);
                        oldPadreOfMascotaCollection1NewMascota = em.merge(oldPadreOfMascotaCollection1NewMascota);
                    }
                }
            }
            for (Farmacos farmacosCollectionNewFarmacos : farmacosCollectionNew) {
                if (!farmacosCollectionOld.contains(farmacosCollectionNewFarmacos)) {
                    Mascota oldMascotaOfFarmacosCollectionNewFarmacos = farmacosCollectionNewFarmacos.getMascota();
                    farmacosCollectionNewFarmacos.setMascota(mascota);
                    farmacosCollectionNewFarmacos = em.merge(farmacosCollectionNewFarmacos);
                    if (oldMascotaOfFarmacosCollectionNewFarmacos != null && !oldMascotaOfFarmacosCollectionNewFarmacos.equals(mascota)) {
                        oldMascotaOfFarmacosCollectionNewFarmacos.getFarmacosCollection().remove(farmacosCollectionNewFarmacos);
                        oldMascotaOfFarmacosCollectionNewFarmacos = em.merge(oldMascotaOfFarmacosCollectionNewFarmacos);
                    }
                }
            }
            for (Hospitalizacion hospitalizacionCollectionNewHospitalizacion : hospitalizacionCollectionNew) {
                if (!hospitalizacionCollectionOld.contains(hospitalizacionCollectionNewHospitalizacion)) {
                    Mascota oldMascotaOfHospitalizacionCollectionNewHospitalizacion = hospitalizacionCollectionNewHospitalizacion.getMascota();
                    hospitalizacionCollectionNewHospitalizacion.setMascota(mascota);
                    hospitalizacionCollectionNewHospitalizacion = em.merge(hospitalizacionCollectionNewHospitalizacion);
                    if (oldMascotaOfHospitalizacionCollectionNewHospitalizacion != null && !oldMascotaOfHospitalizacionCollectionNewHospitalizacion.equals(mascota)) {
                        oldMascotaOfHospitalizacionCollectionNewHospitalizacion.getHospitalizacionCollection().remove(hospitalizacionCollectionNewHospitalizacion);
                        oldMascotaOfHospitalizacionCollectionNewHospitalizacion = em.merge(oldMascotaOfHospitalizacionCollectionNewHospitalizacion);
                    }
                }
            }
            for (AgendaDetalle agendaDetalleCollectionNewAgendaDetalle : agendaDetalleCollectionNew) {
                if (!agendaDetalleCollectionOld.contains(agendaDetalleCollectionNewAgendaDetalle)) {
                    Mascota oldMascotaOfAgendaDetalleCollectionNewAgendaDetalle = agendaDetalleCollectionNewAgendaDetalle.getMascota();
                    agendaDetalleCollectionNewAgendaDetalle.setMascota(mascota);
                    agendaDetalleCollectionNewAgendaDetalle = em.merge(agendaDetalleCollectionNewAgendaDetalle);
                    if (oldMascotaOfAgendaDetalleCollectionNewAgendaDetalle != null && !oldMascotaOfAgendaDetalleCollectionNewAgendaDetalle.equals(mascota)) {
                        oldMascotaOfAgendaDetalleCollectionNewAgendaDetalle.getAgendaDetalleCollection().remove(agendaDetalleCollectionNewAgendaDetalle);
                        oldMascotaOfAgendaDetalleCollectionNewAgendaDetalle = em.merge(oldMascotaOfAgendaDetalleCollectionNewAgendaDetalle);
                    }
                }
            }
            for (Anamnesis anamnesisCollectionNewAnamnesis : anamnesisCollectionNew) {
                if (!anamnesisCollectionOld.contains(anamnesisCollectionNewAnamnesis)) {
                    Mascota oldMascotaOfAnamnesisCollectionNewAnamnesis = anamnesisCollectionNewAnamnesis.getMascota();
                    anamnesisCollectionNewAnamnesis.setMascota(mascota);
                    anamnesisCollectionNewAnamnesis = em.merge(anamnesisCollectionNewAnamnesis);
                    if (oldMascotaOfAnamnesisCollectionNewAnamnesis != null && !oldMascotaOfAnamnesisCollectionNewAnamnesis.equals(mascota)) {
                        oldMascotaOfAnamnesisCollectionNewAnamnesis.getAnamnesisCollection().remove(anamnesisCollectionNewAnamnesis);
                        oldMascotaOfAnamnesisCollectionNewAnamnesis = em.merge(oldMascotaOfAnamnesisCollectionNewAnamnesis);
                    }
                }
            }
            for (Patologias patologiasCollectionNewPatologias : patologiasCollectionNew) {
                if (!patologiasCollectionOld.contains(patologiasCollectionNewPatologias)) {
                    Mascota oldMascotaOfPatologiasCollectionNewPatologias = patologiasCollectionNewPatologias.getMascota();
                    patologiasCollectionNewPatologias.setMascota(mascota);
                    patologiasCollectionNewPatologias = em.merge(patologiasCollectionNewPatologias);
                    if (oldMascotaOfPatologiasCollectionNewPatologias != null && !oldMascotaOfPatologiasCollectionNewPatologias.equals(mascota)) {
                        oldMascotaOfPatologiasCollectionNewPatologias.getPatologiasCollection().remove(patologiasCollectionNewPatologias);
                        oldMascotaOfPatologiasCollectionNewPatologias = em.merge(oldMascotaOfPatologiasCollectionNewPatologias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mascota.getIdMascota();
                if (findMascota(id) == null) {
                    throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.");
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
            Mascota mascota;
            try {
                mascota = em.getReference(Mascota.class, id);
                mascota.getIdMascota();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Examenes> examenesCollectionOrphanCheck = mascota.getExamenesCollection();
            for (Examenes examenesCollectionOrphanCheckExamenes : examenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Examenes " + examenesCollectionOrphanCheckExamenes + " in its examenesCollection field has a non-nullable mascota field.");
            }
            Collection<Historialvacunas> historialvacunasCollectionOrphanCheck = mascota.getHistorialvacunasCollection();
            for (Historialvacunas historialvacunasCollectionOrphanCheckHistorialvacunas : historialvacunasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Historialvacunas " + historialvacunasCollectionOrphanCheckHistorialvacunas + " in its historialvacunasCollection field has a non-nullable mascota field.");
            }
            Collection<Contraindicaciones> contraindicacionesCollectionOrphanCheck = mascota.getContraindicacionesCollection();
            for (Contraindicaciones contraindicacionesCollectionOrphanCheckContraindicaciones : contraindicacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Contraindicaciones " + contraindicacionesCollectionOrphanCheckContraindicaciones + " in its contraindicacionesCollection field has a non-nullable mascota field.");
            }
            Collection<Desparacitaciones> desparacitacionesCollectionOrphanCheck = mascota.getDesparacitacionesCollection();
            for (Desparacitaciones desparacitacionesCollectionOrphanCheckDesparacitaciones : desparacitacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Desparacitaciones " + desparacitacionesCollectionOrphanCheckDesparacitaciones + " in its desparacitacionesCollection field has a non-nullable mascota field.");
            }
            Collection<Alergias> alergiasCollectionOrphanCheck = mascota.getAlergiasCollection();
            for (Alergias alergiasCollectionOrphanCheckAlergias : alergiasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Alergias " + alergiasCollectionOrphanCheckAlergias + " in its alergiasCollection field has a non-nullable mascota field.");
            }
            Collection<Farmacos> farmacosCollectionOrphanCheck = mascota.getFarmacosCollection();
            for (Farmacos farmacosCollectionOrphanCheckFarmacos : farmacosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Farmacos " + farmacosCollectionOrphanCheckFarmacos + " in its farmacosCollection field has a non-nullable mascota field.");
            }
            Collection<Hospitalizacion> hospitalizacionCollectionOrphanCheck = mascota.getHospitalizacionCollection();
            for (Hospitalizacion hospitalizacionCollectionOrphanCheckHospitalizacion : hospitalizacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Hospitalizacion " + hospitalizacionCollectionOrphanCheckHospitalizacion + " in its hospitalizacionCollection field has a non-nullable mascota field.");
            }
            Collection<AgendaDetalle> agendaDetalleCollectionOrphanCheck = mascota.getAgendaDetalleCollection();
            for (AgendaDetalle agendaDetalleCollectionOrphanCheckAgendaDetalle : agendaDetalleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the AgendaDetalle " + agendaDetalleCollectionOrphanCheckAgendaDetalle + " in its agendaDetalleCollection field has a non-nullable mascota field.");
            }
            Collection<Anamnesis> anamnesisCollectionOrphanCheck = mascota.getAnamnesisCollection();
            for (Anamnesis anamnesisCollectionOrphanCheckAnamnesis : anamnesisCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Anamnesis " + anamnesisCollectionOrphanCheckAnamnesis + " in its anamnesisCollection field has a non-nullable mascota field.");
            }
            Collection<Patologias> patologiasCollectionOrphanCheck = mascota.getPatologiasCollection();
            for (Patologias patologiasCollectionOrphanCheckPatologias : patologiasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Patologias " + patologiasCollectionOrphanCheckPatologias + " in its patologiasCollection field has a non-nullable mascota field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Habitad habitad = mascota.getHabitad();
            if (habitad != null) {
                habitad.getMascotaCollection().remove(mascota);
                habitad = em.merge(habitad);
            }
            Mascota madre = mascota.getMadre();
            if (madre != null) {
                madre.getMascotaCollection().remove(mascota);
                madre = em.merge(madre);
            }
            Mascota padre = mascota.getPadre();
            if (padre != null) {
                padre.getMascotaCollection().remove(mascota);
                padre = em.merge(padre);
            }
            Propietario propietario = mascota.getPropietario();
            if (propietario != null) {
                propietario.getMascotaCollection().remove(mascota);
                propietario = em.merge(propietario);
            }
            Raza raza = mascota.getRaza();
            if (raza != null) {
                raza.getMascotaCollection().remove(mascota);
                raza = em.merge(raza);
            }
            Collection<Mascota> mascotaCollection = mascota.getMascotaCollection();
            for (Mascota mascotaCollectionMascota : mascotaCollection) {
                mascotaCollectionMascota.setMadre(null);
                mascotaCollectionMascota = em.merge(mascotaCollectionMascota);
            }
            Collection<Mascota> mascotaCollection1 = mascota.getMascotaCollection1();
            for (Mascota mascotaCollection1Mascota : mascotaCollection1) {
                mascotaCollection1Mascota.setPadre(null);
                mascotaCollection1Mascota = em.merge(mascotaCollection1Mascota);
            }
            em.remove(mascota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascota> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Mascota> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascota.class));
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

    public Mascota findMascota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascota> rt = cq.from(Mascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
