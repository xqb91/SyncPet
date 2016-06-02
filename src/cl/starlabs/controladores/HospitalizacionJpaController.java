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
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.Veterinario;
import cl.starlabs.modelo.Examenes;
import java.util.ArrayList;
import java.util.Collection;
import cl.starlabs.modelo.Historialvacunas;
import cl.starlabs.modelo.Contraindicaciones;
import cl.starlabs.modelo.Desparacitaciones;
import cl.starlabs.modelo.Farmacos;
import cl.starlabs.modelo.Procedimientos;
import cl.starlabs.modelo.Anamnesis;
import cl.starlabs.modelo.Hospitalizacion;
import cl.starlabs.modelo.Patologias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class HospitalizacionJpaController implements Serializable {

    public HospitalizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hospitalizacion hospitalizacion) throws PreexistingEntityException, Exception {
        if (hospitalizacion.getExamenesCollection() == null) {
            hospitalizacion.setExamenesCollection(new ArrayList<Examenes>());
        }
        if (hospitalizacion.getHistorialvacunasCollection() == null) {
            hospitalizacion.setHistorialvacunasCollection(new ArrayList<Historialvacunas>());
        }
        if (hospitalizacion.getContraindicacionesCollection() == null) {
            hospitalizacion.setContraindicacionesCollection(new ArrayList<Contraindicaciones>());
        }
        if (hospitalizacion.getDesparacitacionesCollection() == null) {
            hospitalizacion.setDesparacitacionesCollection(new ArrayList<Desparacitaciones>());
        }
        if (hospitalizacion.getFarmacosCollection() == null) {
            hospitalizacion.setFarmacosCollection(new ArrayList<Farmacos>());
        }
        if (hospitalizacion.getProcedimientosCollection() == null) {
            hospitalizacion.setProcedimientosCollection(new ArrayList<Procedimientos>());
        }
        if (hospitalizacion.getAnamnesisCollection() == null) {
            hospitalizacion.setAnamnesisCollection(new ArrayList<Anamnesis>());
        }
        if (hospitalizacion.getPatologiasCollection() == null) {
            hospitalizacion.setPatologiasCollection(new ArrayList<Patologias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota mascota = hospitalizacion.getMascota();
            if (mascota != null) {
                mascota = em.getReference(mascota.getClass(), mascota.getIdMascota());
                hospitalizacion.setMascota(mascota);
            }
            Veterinario veterinario = hospitalizacion.getVeterinario();
            if (veterinario != null) {
                veterinario = em.getReference(veterinario.getClass(), veterinario.getIdVeterinario());
                hospitalizacion.setVeterinario(veterinario);
            }
            Collection<Examenes> attachedExamenesCollection = new ArrayList<Examenes>();
            for (Examenes examenesCollectionExamenesToAttach : hospitalizacion.getExamenesCollection()) {
                examenesCollectionExamenesToAttach = em.getReference(examenesCollectionExamenesToAttach.getClass(), examenesCollectionExamenesToAttach.getIdExamen());
                attachedExamenesCollection.add(examenesCollectionExamenesToAttach);
            }
            hospitalizacion.setExamenesCollection(attachedExamenesCollection);
            Collection<Historialvacunas> attachedHistorialvacunasCollection = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionHistorialvacunasToAttach : hospitalizacion.getHistorialvacunasCollection()) {
                historialvacunasCollectionHistorialvacunasToAttach = em.getReference(historialvacunasCollectionHistorialvacunasToAttach.getClass(), historialvacunasCollectionHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollection.add(historialvacunasCollectionHistorialvacunasToAttach);
            }
            hospitalizacion.setHistorialvacunasCollection(attachedHistorialvacunasCollection);
            Collection<Contraindicaciones> attachedContraindicacionesCollection = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionContraindicacionesToAttach : hospitalizacion.getContraindicacionesCollection()) {
                contraindicacionesCollectionContraindicacionesToAttach = em.getReference(contraindicacionesCollectionContraindicacionesToAttach.getClass(), contraindicacionesCollectionContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollection.add(contraindicacionesCollectionContraindicacionesToAttach);
            }
            hospitalizacion.setContraindicacionesCollection(attachedContraindicacionesCollection);
            Collection<Desparacitaciones> attachedDesparacitacionesCollection = new ArrayList<Desparacitaciones>();
            for (Desparacitaciones desparacitacionesCollectionDesparacitacionesToAttach : hospitalizacion.getDesparacitacionesCollection()) {
                desparacitacionesCollectionDesparacitacionesToAttach = em.getReference(desparacitacionesCollectionDesparacitacionesToAttach.getClass(), desparacitacionesCollectionDesparacitacionesToAttach.getIdDesparacitacion());
                attachedDesparacitacionesCollection.add(desparacitacionesCollectionDesparacitacionesToAttach);
            }
            hospitalizacion.setDesparacitacionesCollection(attachedDesparacitacionesCollection);
            Collection<Farmacos> attachedFarmacosCollection = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionFarmacosToAttach : hospitalizacion.getFarmacosCollection()) {
                farmacosCollectionFarmacosToAttach = em.getReference(farmacosCollectionFarmacosToAttach.getClass(), farmacosCollectionFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollection.add(farmacosCollectionFarmacosToAttach);
            }
            hospitalizacion.setFarmacosCollection(attachedFarmacosCollection);
            Collection<Procedimientos> attachedProcedimientosCollection = new ArrayList<Procedimientos>();
            for (Procedimientos procedimientosCollectionProcedimientosToAttach : hospitalizacion.getProcedimientosCollection()) {
                procedimientosCollectionProcedimientosToAttach = em.getReference(procedimientosCollectionProcedimientosToAttach.getClass(), procedimientosCollectionProcedimientosToAttach.getIdProcedimiento());
                attachedProcedimientosCollection.add(procedimientosCollectionProcedimientosToAttach);
            }
            hospitalizacion.setProcedimientosCollection(attachedProcedimientosCollection);
            Collection<Anamnesis> attachedAnamnesisCollection = new ArrayList<Anamnesis>();
            for (Anamnesis anamnesisCollectionAnamnesisToAttach : hospitalizacion.getAnamnesisCollection()) {
                anamnesisCollectionAnamnesisToAttach = em.getReference(anamnesisCollectionAnamnesisToAttach.getClass(), anamnesisCollectionAnamnesisToAttach.getIdAnamnesis());
                attachedAnamnesisCollection.add(anamnesisCollectionAnamnesisToAttach);
            }
            hospitalizacion.setAnamnesisCollection(attachedAnamnesisCollection);
            Collection<Patologias> attachedPatologiasCollection = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionPatologiasToAttach : hospitalizacion.getPatologiasCollection()) {
                patologiasCollectionPatologiasToAttach = em.getReference(patologiasCollectionPatologiasToAttach.getClass(), patologiasCollectionPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollection.add(patologiasCollectionPatologiasToAttach);
            }
            hospitalizacion.setPatologiasCollection(attachedPatologiasCollection);
            em.persist(hospitalizacion);
            if (mascota != null) {
                mascota.getHospitalizacionCollection().add(hospitalizacion);
                mascota = em.merge(mascota);
            }
            if (veterinario != null) {
                veterinario.getHospitalizacionCollection().add(hospitalizacion);
                veterinario = em.merge(veterinario);
            }
            for (Examenes examenesCollectionExamenes : hospitalizacion.getExamenesCollection()) {
                Hospitalizacion oldHospitalizacionOfExamenesCollectionExamenes = examenesCollectionExamenes.getHospitalizacion();
                examenesCollectionExamenes.setHospitalizacion(hospitalizacion);
                examenesCollectionExamenes = em.merge(examenesCollectionExamenes);
                if (oldHospitalizacionOfExamenesCollectionExamenes != null) {
                    oldHospitalizacionOfExamenesCollectionExamenes.getExamenesCollection().remove(examenesCollectionExamenes);
                    oldHospitalizacionOfExamenesCollectionExamenes = em.merge(oldHospitalizacionOfExamenesCollectionExamenes);
                }
            }
            for (Historialvacunas historialvacunasCollectionHistorialvacunas : hospitalizacion.getHistorialvacunasCollection()) {
                Hospitalizacion oldHospitalizacionOfHistorialvacunasCollectionHistorialvacunas = historialvacunasCollectionHistorialvacunas.getHospitalizacion();
                historialvacunasCollectionHistorialvacunas.setHospitalizacion(hospitalizacion);
                historialvacunasCollectionHistorialvacunas = em.merge(historialvacunasCollectionHistorialvacunas);
                if (oldHospitalizacionOfHistorialvacunasCollectionHistorialvacunas != null) {
                    oldHospitalizacionOfHistorialvacunasCollectionHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionHistorialvacunas);
                    oldHospitalizacionOfHistorialvacunasCollectionHistorialvacunas = em.merge(oldHospitalizacionOfHistorialvacunasCollectionHistorialvacunas);
                }
            }
            for (Contraindicaciones contraindicacionesCollectionContraindicaciones : hospitalizacion.getContraindicacionesCollection()) {
                Hospitalizacion oldHospitalizacionOfContraindicacionesCollectionContraindicaciones = contraindicacionesCollectionContraindicaciones.getHospitalizacion();
                contraindicacionesCollectionContraindicaciones.setHospitalizacion(hospitalizacion);
                contraindicacionesCollectionContraindicaciones = em.merge(contraindicacionesCollectionContraindicaciones);
                if (oldHospitalizacionOfContraindicacionesCollectionContraindicaciones != null) {
                    oldHospitalizacionOfContraindicacionesCollectionContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionContraindicaciones);
                    oldHospitalizacionOfContraindicacionesCollectionContraindicaciones = em.merge(oldHospitalizacionOfContraindicacionesCollectionContraindicaciones);
                }
            }
            for (Desparacitaciones desparacitacionesCollectionDesparacitaciones : hospitalizacion.getDesparacitacionesCollection()) {
                Hospitalizacion oldHospitalizacionOfDesparacitacionesCollectionDesparacitaciones = desparacitacionesCollectionDesparacitaciones.getHospitalizacion();
                desparacitacionesCollectionDesparacitaciones.setHospitalizacion(hospitalizacion);
                desparacitacionesCollectionDesparacitaciones = em.merge(desparacitacionesCollectionDesparacitaciones);
                if (oldHospitalizacionOfDesparacitacionesCollectionDesparacitaciones != null) {
                    oldHospitalizacionOfDesparacitacionesCollectionDesparacitaciones.getDesparacitacionesCollection().remove(desparacitacionesCollectionDesparacitaciones);
                    oldHospitalizacionOfDesparacitacionesCollectionDesparacitaciones = em.merge(oldHospitalizacionOfDesparacitacionesCollectionDesparacitaciones);
                }
            }
            for (Farmacos farmacosCollectionFarmacos : hospitalizacion.getFarmacosCollection()) {
                Hospitalizacion oldHospitalizacionOfFarmacosCollectionFarmacos = farmacosCollectionFarmacos.getHospitalizacion();
                farmacosCollectionFarmacos.setHospitalizacion(hospitalizacion);
                farmacosCollectionFarmacos = em.merge(farmacosCollectionFarmacos);
                if (oldHospitalizacionOfFarmacosCollectionFarmacos != null) {
                    oldHospitalizacionOfFarmacosCollectionFarmacos.getFarmacosCollection().remove(farmacosCollectionFarmacos);
                    oldHospitalizacionOfFarmacosCollectionFarmacos = em.merge(oldHospitalizacionOfFarmacosCollectionFarmacos);
                }
            }
            for (Procedimientos procedimientosCollectionProcedimientos : hospitalizacion.getProcedimientosCollection()) {
                Hospitalizacion oldHospitalizacionOfProcedimientosCollectionProcedimientos = procedimientosCollectionProcedimientos.getHospitalizacion();
                procedimientosCollectionProcedimientos.setHospitalizacion(hospitalizacion);
                procedimientosCollectionProcedimientos = em.merge(procedimientosCollectionProcedimientos);
                if (oldHospitalizacionOfProcedimientosCollectionProcedimientos != null) {
                    oldHospitalizacionOfProcedimientosCollectionProcedimientos.getProcedimientosCollection().remove(procedimientosCollectionProcedimientos);
                    oldHospitalizacionOfProcedimientosCollectionProcedimientos = em.merge(oldHospitalizacionOfProcedimientosCollectionProcedimientos);
                }
            }
            for (Anamnesis anamnesisCollectionAnamnesis : hospitalizacion.getAnamnesisCollection()) {
                Hospitalizacion oldHospitalizacionOfAnamnesisCollectionAnamnesis = anamnesisCollectionAnamnesis.getHospitalizacion();
                anamnesisCollectionAnamnesis.setHospitalizacion(hospitalizacion);
                anamnesisCollectionAnamnesis = em.merge(anamnesisCollectionAnamnesis);
                if (oldHospitalizacionOfAnamnesisCollectionAnamnesis != null) {
                    oldHospitalizacionOfAnamnesisCollectionAnamnesis.getAnamnesisCollection().remove(anamnesisCollectionAnamnesis);
                    oldHospitalizacionOfAnamnesisCollectionAnamnesis = em.merge(oldHospitalizacionOfAnamnesisCollectionAnamnesis);
                }
            }
            for (Patologias patologiasCollectionPatologias : hospitalizacion.getPatologiasCollection()) {
                Hospitalizacion oldHospitalizacionOfPatologiasCollectionPatologias = patologiasCollectionPatologias.getHospitalizacion();
                patologiasCollectionPatologias.setHospitalizacion(hospitalizacion);
                patologiasCollectionPatologias = em.merge(patologiasCollectionPatologias);
                if (oldHospitalizacionOfPatologiasCollectionPatologias != null) {
                    oldHospitalizacionOfPatologiasCollectionPatologias.getPatologiasCollection().remove(patologiasCollectionPatologias);
                    oldHospitalizacionOfPatologiasCollectionPatologias = em.merge(oldHospitalizacionOfPatologiasCollectionPatologias);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHospitalizacion(hospitalizacion.getIdHospitalizacion()) != null) {
                throw new PreexistingEntityException("Hospitalizacion " + hospitalizacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hospitalizacion hospitalizacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hospitalizacion persistentHospitalizacion = em.find(Hospitalizacion.class, hospitalizacion.getIdHospitalizacion());
            Mascota mascotaOld = persistentHospitalizacion.getMascota();
            Mascota mascotaNew = hospitalizacion.getMascota();
            Veterinario veterinarioOld = persistentHospitalizacion.getVeterinario();
            Veterinario veterinarioNew = hospitalizacion.getVeterinario();
            Collection<Examenes> examenesCollectionOld = persistentHospitalizacion.getExamenesCollection();
            Collection<Examenes> examenesCollectionNew = hospitalizacion.getExamenesCollection();
            Collection<Historialvacunas> historialvacunasCollectionOld = persistentHospitalizacion.getHistorialvacunasCollection();
            Collection<Historialvacunas> historialvacunasCollectionNew = hospitalizacion.getHistorialvacunasCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionOld = persistentHospitalizacion.getContraindicacionesCollection();
            Collection<Contraindicaciones> contraindicacionesCollectionNew = hospitalizacion.getContraindicacionesCollection();
            Collection<Desparacitaciones> desparacitacionesCollectionOld = persistentHospitalizacion.getDesparacitacionesCollection();
            Collection<Desparacitaciones> desparacitacionesCollectionNew = hospitalizacion.getDesparacitacionesCollection();
            Collection<Farmacos> farmacosCollectionOld = persistentHospitalizacion.getFarmacosCollection();
            Collection<Farmacos> farmacosCollectionNew = hospitalizacion.getFarmacosCollection();
            Collection<Procedimientos> procedimientosCollectionOld = persistentHospitalizacion.getProcedimientosCollection();
            Collection<Procedimientos> procedimientosCollectionNew = hospitalizacion.getProcedimientosCollection();
            Collection<Anamnesis> anamnesisCollectionOld = persistentHospitalizacion.getAnamnesisCollection();
            Collection<Anamnesis> anamnesisCollectionNew = hospitalizacion.getAnamnesisCollection();
            Collection<Patologias> patologiasCollectionOld = persistentHospitalizacion.getPatologiasCollection();
            Collection<Patologias> patologiasCollectionNew = hospitalizacion.getPatologiasCollection();
            List<String> illegalOrphanMessages = null;
            for (Examenes examenesCollectionOldExamenes : examenesCollectionOld) {
                if (!examenesCollectionNew.contains(examenesCollectionOldExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Examenes " + examenesCollectionOldExamenes + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Historialvacunas historialvacunasCollectionOldHistorialvacunas : historialvacunasCollectionOld) {
                if (!historialvacunasCollectionNew.contains(historialvacunasCollectionOldHistorialvacunas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialvacunas " + historialvacunasCollectionOldHistorialvacunas + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Contraindicaciones contraindicacionesCollectionOldContraindicaciones : contraindicacionesCollectionOld) {
                if (!contraindicacionesCollectionNew.contains(contraindicacionesCollectionOldContraindicaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contraindicaciones " + contraindicacionesCollectionOldContraindicaciones + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Desparacitaciones desparacitacionesCollectionOldDesparacitaciones : desparacitacionesCollectionOld) {
                if (!desparacitacionesCollectionNew.contains(desparacitacionesCollectionOldDesparacitaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Desparacitaciones " + desparacitacionesCollectionOldDesparacitaciones + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Farmacos farmacosCollectionOldFarmacos : farmacosCollectionOld) {
                if (!farmacosCollectionNew.contains(farmacosCollectionOldFarmacos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Farmacos " + farmacosCollectionOldFarmacos + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Procedimientos procedimientosCollectionOldProcedimientos : procedimientosCollectionOld) {
                if (!procedimientosCollectionNew.contains(procedimientosCollectionOldProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimientos " + procedimientosCollectionOldProcedimientos + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Anamnesis anamnesisCollectionOldAnamnesis : anamnesisCollectionOld) {
                if (!anamnesisCollectionNew.contains(anamnesisCollectionOldAnamnesis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Anamnesis " + anamnesisCollectionOldAnamnesis + " since its hospitalizacion field is not nullable.");
                }
            }
            for (Patologias patologiasCollectionOldPatologias : patologiasCollectionOld) {
                if (!patologiasCollectionNew.contains(patologiasCollectionOldPatologias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Patologias " + patologiasCollectionOldPatologias + " since its hospitalizacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (mascotaNew != null) {
                mascotaNew = em.getReference(mascotaNew.getClass(), mascotaNew.getIdMascota());
                hospitalizacion.setMascota(mascotaNew);
            }
            if (veterinarioNew != null) {
                veterinarioNew = em.getReference(veterinarioNew.getClass(), veterinarioNew.getIdVeterinario());
                hospitalizacion.setVeterinario(veterinarioNew);
            }
            Collection<Examenes> attachedExamenesCollectionNew = new ArrayList<Examenes>();
            for (Examenes examenesCollectionNewExamenesToAttach : examenesCollectionNew) {
                examenesCollectionNewExamenesToAttach = em.getReference(examenesCollectionNewExamenesToAttach.getClass(), examenesCollectionNewExamenesToAttach.getIdExamen());
                attachedExamenesCollectionNew.add(examenesCollectionNewExamenesToAttach);
            }
            examenesCollectionNew = attachedExamenesCollectionNew;
            hospitalizacion.setExamenesCollection(examenesCollectionNew);
            Collection<Historialvacunas> attachedHistorialvacunasCollectionNew = new ArrayList<Historialvacunas>();
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunasToAttach : historialvacunasCollectionNew) {
                historialvacunasCollectionNewHistorialvacunasToAttach = em.getReference(historialvacunasCollectionNewHistorialvacunasToAttach.getClass(), historialvacunasCollectionNewHistorialvacunasToAttach.getIdEvento());
                attachedHistorialvacunasCollectionNew.add(historialvacunasCollectionNewHistorialvacunasToAttach);
            }
            historialvacunasCollectionNew = attachedHistorialvacunasCollectionNew;
            hospitalizacion.setHistorialvacunasCollection(historialvacunasCollectionNew);
            Collection<Contraindicaciones> attachedContraindicacionesCollectionNew = new ArrayList<Contraindicaciones>();
            for (Contraindicaciones contraindicacionesCollectionNewContraindicacionesToAttach : contraindicacionesCollectionNew) {
                contraindicacionesCollectionNewContraindicacionesToAttach = em.getReference(contraindicacionesCollectionNewContraindicacionesToAttach.getClass(), contraindicacionesCollectionNewContraindicacionesToAttach.getIdContraindicacion());
                attachedContraindicacionesCollectionNew.add(contraindicacionesCollectionNewContraindicacionesToAttach);
            }
            contraindicacionesCollectionNew = attachedContraindicacionesCollectionNew;
            hospitalizacion.setContraindicacionesCollection(contraindicacionesCollectionNew);
            Collection<Desparacitaciones> attachedDesparacitacionesCollectionNew = new ArrayList<Desparacitaciones>();
            for (Desparacitaciones desparacitacionesCollectionNewDesparacitacionesToAttach : desparacitacionesCollectionNew) {
                desparacitacionesCollectionNewDesparacitacionesToAttach = em.getReference(desparacitacionesCollectionNewDesparacitacionesToAttach.getClass(), desparacitacionesCollectionNewDesparacitacionesToAttach.getIdDesparacitacion());
                attachedDesparacitacionesCollectionNew.add(desparacitacionesCollectionNewDesparacitacionesToAttach);
            }
            desparacitacionesCollectionNew = attachedDesparacitacionesCollectionNew;
            hospitalizacion.setDesparacitacionesCollection(desparacitacionesCollectionNew);
            Collection<Farmacos> attachedFarmacosCollectionNew = new ArrayList<Farmacos>();
            for (Farmacos farmacosCollectionNewFarmacosToAttach : farmacosCollectionNew) {
                farmacosCollectionNewFarmacosToAttach = em.getReference(farmacosCollectionNewFarmacosToAttach.getClass(), farmacosCollectionNewFarmacosToAttach.getIdFarmaco());
                attachedFarmacosCollectionNew.add(farmacosCollectionNewFarmacosToAttach);
            }
            farmacosCollectionNew = attachedFarmacosCollectionNew;
            hospitalizacion.setFarmacosCollection(farmacosCollectionNew);
            Collection<Procedimientos> attachedProcedimientosCollectionNew = new ArrayList<Procedimientos>();
            for (Procedimientos procedimientosCollectionNewProcedimientosToAttach : procedimientosCollectionNew) {
                procedimientosCollectionNewProcedimientosToAttach = em.getReference(procedimientosCollectionNewProcedimientosToAttach.getClass(), procedimientosCollectionNewProcedimientosToAttach.getIdProcedimiento());
                attachedProcedimientosCollectionNew.add(procedimientosCollectionNewProcedimientosToAttach);
            }
            procedimientosCollectionNew = attachedProcedimientosCollectionNew;
            hospitalizacion.setProcedimientosCollection(procedimientosCollectionNew);
            Collection<Anamnesis> attachedAnamnesisCollectionNew = new ArrayList<Anamnesis>();
            for (Anamnesis anamnesisCollectionNewAnamnesisToAttach : anamnesisCollectionNew) {
                anamnesisCollectionNewAnamnesisToAttach = em.getReference(anamnesisCollectionNewAnamnesisToAttach.getClass(), anamnesisCollectionNewAnamnesisToAttach.getIdAnamnesis());
                attachedAnamnesisCollectionNew.add(anamnesisCollectionNewAnamnesisToAttach);
            }
            anamnesisCollectionNew = attachedAnamnesisCollectionNew;
            hospitalizacion.setAnamnesisCollection(anamnesisCollectionNew);
            Collection<Patologias> attachedPatologiasCollectionNew = new ArrayList<Patologias>();
            for (Patologias patologiasCollectionNewPatologiasToAttach : patologiasCollectionNew) {
                patologiasCollectionNewPatologiasToAttach = em.getReference(patologiasCollectionNewPatologiasToAttach.getClass(), patologiasCollectionNewPatologiasToAttach.getIdPatologia());
                attachedPatologiasCollectionNew.add(patologiasCollectionNewPatologiasToAttach);
            }
            patologiasCollectionNew = attachedPatologiasCollectionNew;
            hospitalizacion.setPatologiasCollection(patologiasCollectionNew);
            hospitalizacion = em.merge(hospitalizacion);
            if (mascotaOld != null && !mascotaOld.equals(mascotaNew)) {
                mascotaOld.getHospitalizacionCollection().remove(hospitalizacion);
                mascotaOld = em.merge(mascotaOld);
            }
            if (mascotaNew != null && !mascotaNew.equals(mascotaOld)) {
                mascotaNew.getHospitalizacionCollection().add(hospitalizacion);
                mascotaNew = em.merge(mascotaNew);
            }
            if (veterinarioOld != null && !veterinarioOld.equals(veterinarioNew)) {
                veterinarioOld.getHospitalizacionCollection().remove(hospitalizacion);
                veterinarioOld = em.merge(veterinarioOld);
            }
            if (veterinarioNew != null && !veterinarioNew.equals(veterinarioOld)) {
                veterinarioNew.getHospitalizacionCollection().add(hospitalizacion);
                veterinarioNew = em.merge(veterinarioNew);
            }
            for (Examenes examenesCollectionNewExamenes : examenesCollectionNew) {
                if (!examenesCollectionOld.contains(examenesCollectionNewExamenes)) {
                    Hospitalizacion oldHospitalizacionOfExamenesCollectionNewExamenes = examenesCollectionNewExamenes.getHospitalizacion();
                    examenesCollectionNewExamenes.setHospitalizacion(hospitalizacion);
                    examenesCollectionNewExamenes = em.merge(examenesCollectionNewExamenes);
                    if (oldHospitalizacionOfExamenesCollectionNewExamenes != null && !oldHospitalizacionOfExamenesCollectionNewExamenes.equals(hospitalizacion)) {
                        oldHospitalizacionOfExamenesCollectionNewExamenes.getExamenesCollection().remove(examenesCollectionNewExamenes);
                        oldHospitalizacionOfExamenesCollectionNewExamenes = em.merge(oldHospitalizacionOfExamenesCollectionNewExamenes);
                    }
                }
            }
            for (Historialvacunas historialvacunasCollectionNewHistorialvacunas : historialvacunasCollectionNew) {
                if (!historialvacunasCollectionOld.contains(historialvacunasCollectionNewHistorialvacunas)) {
                    Hospitalizacion oldHospitalizacionOfHistorialvacunasCollectionNewHistorialvacunas = historialvacunasCollectionNewHistorialvacunas.getHospitalizacion();
                    historialvacunasCollectionNewHistorialvacunas.setHospitalizacion(hospitalizacion);
                    historialvacunasCollectionNewHistorialvacunas = em.merge(historialvacunasCollectionNewHistorialvacunas);
                    if (oldHospitalizacionOfHistorialvacunasCollectionNewHistorialvacunas != null && !oldHospitalizacionOfHistorialvacunasCollectionNewHistorialvacunas.equals(hospitalizacion)) {
                        oldHospitalizacionOfHistorialvacunasCollectionNewHistorialvacunas.getHistorialvacunasCollection().remove(historialvacunasCollectionNewHistorialvacunas);
                        oldHospitalizacionOfHistorialvacunasCollectionNewHistorialvacunas = em.merge(oldHospitalizacionOfHistorialvacunasCollectionNewHistorialvacunas);
                    }
                }
            }
            for (Contraindicaciones contraindicacionesCollectionNewContraindicaciones : contraindicacionesCollectionNew) {
                if (!contraindicacionesCollectionOld.contains(contraindicacionesCollectionNewContraindicaciones)) {
                    Hospitalizacion oldHospitalizacionOfContraindicacionesCollectionNewContraindicaciones = contraindicacionesCollectionNewContraindicaciones.getHospitalizacion();
                    contraindicacionesCollectionNewContraindicaciones.setHospitalizacion(hospitalizacion);
                    contraindicacionesCollectionNewContraindicaciones = em.merge(contraindicacionesCollectionNewContraindicaciones);
                    if (oldHospitalizacionOfContraindicacionesCollectionNewContraindicaciones != null && !oldHospitalizacionOfContraindicacionesCollectionNewContraindicaciones.equals(hospitalizacion)) {
                        oldHospitalizacionOfContraindicacionesCollectionNewContraindicaciones.getContraindicacionesCollection().remove(contraindicacionesCollectionNewContraindicaciones);
                        oldHospitalizacionOfContraindicacionesCollectionNewContraindicaciones = em.merge(oldHospitalizacionOfContraindicacionesCollectionNewContraindicaciones);
                    }
                }
            }
            for (Desparacitaciones desparacitacionesCollectionNewDesparacitaciones : desparacitacionesCollectionNew) {
                if (!desparacitacionesCollectionOld.contains(desparacitacionesCollectionNewDesparacitaciones)) {
                    Hospitalizacion oldHospitalizacionOfDesparacitacionesCollectionNewDesparacitaciones = desparacitacionesCollectionNewDesparacitaciones.getHospitalizacion();
                    desparacitacionesCollectionNewDesparacitaciones.setHospitalizacion(hospitalizacion);
                    desparacitacionesCollectionNewDesparacitaciones = em.merge(desparacitacionesCollectionNewDesparacitaciones);
                    if (oldHospitalizacionOfDesparacitacionesCollectionNewDesparacitaciones != null && !oldHospitalizacionOfDesparacitacionesCollectionNewDesparacitaciones.equals(hospitalizacion)) {
                        oldHospitalizacionOfDesparacitacionesCollectionNewDesparacitaciones.getDesparacitacionesCollection().remove(desparacitacionesCollectionNewDesparacitaciones);
                        oldHospitalizacionOfDesparacitacionesCollectionNewDesparacitaciones = em.merge(oldHospitalizacionOfDesparacitacionesCollectionNewDesparacitaciones);
                    }
                }
            }
            for (Farmacos farmacosCollectionNewFarmacos : farmacosCollectionNew) {
                if (!farmacosCollectionOld.contains(farmacosCollectionNewFarmacos)) {
                    Hospitalizacion oldHospitalizacionOfFarmacosCollectionNewFarmacos = farmacosCollectionNewFarmacos.getHospitalizacion();
                    farmacosCollectionNewFarmacos.setHospitalizacion(hospitalizacion);
                    farmacosCollectionNewFarmacos = em.merge(farmacosCollectionNewFarmacos);
                    if (oldHospitalizacionOfFarmacosCollectionNewFarmacos != null && !oldHospitalizacionOfFarmacosCollectionNewFarmacos.equals(hospitalizacion)) {
                        oldHospitalizacionOfFarmacosCollectionNewFarmacos.getFarmacosCollection().remove(farmacosCollectionNewFarmacos);
                        oldHospitalizacionOfFarmacosCollectionNewFarmacos = em.merge(oldHospitalizacionOfFarmacosCollectionNewFarmacos);
                    }
                }
            }
            for (Procedimientos procedimientosCollectionNewProcedimientos : procedimientosCollectionNew) {
                if (!procedimientosCollectionOld.contains(procedimientosCollectionNewProcedimientos)) {
                    Hospitalizacion oldHospitalizacionOfProcedimientosCollectionNewProcedimientos = procedimientosCollectionNewProcedimientos.getHospitalizacion();
                    procedimientosCollectionNewProcedimientos.setHospitalizacion(hospitalizacion);
                    procedimientosCollectionNewProcedimientos = em.merge(procedimientosCollectionNewProcedimientos);
                    if (oldHospitalizacionOfProcedimientosCollectionNewProcedimientos != null && !oldHospitalizacionOfProcedimientosCollectionNewProcedimientos.equals(hospitalizacion)) {
                        oldHospitalizacionOfProcedimientosCollectionNewProcedimientos.getProcedimientosCollection().remove(procedimientosCollectionNewProcedimientos);
                        oldHospitalizacionOfProcedimientosCollectionNewProcedimientos = em.merge(oldHospitalizacionOfProcedimientosCollectionNewProcedimientos);
                    }
                }
            }
            for (Anamnesis anamnesisCollectionNewAnamnesis : anamnesisCollectionNew) {
                if (!anamnesisCollectionOld.contains(anamnesisCollectionNewAnamnesis)) {
                    Hospitalizacion oldHospitalizacionOfAnamnesisCollectionNewAnamnesis = anamnesisCollectionNewAnamnesis.getHospitalizacion();
                    anamnesisCollectionNewAnamnesis.setHospitalizacion(hospitalizacion);
                    anamnesisCollectionNewAnamnesis = em.merge(anamnesisCollectionNewAnamnesis);
                    if (oldHospitalizacionOfAnamnesisCollectionNewAnamnesis != null && !oldHospitalizacionOfAnamnesisCollectionNewAnamnesis.equals(hospitalizacion)) {
                        oldHospitalizacionOfAnamnesisCollectionNewAnamnesis.getAnamnesisCollection().remove(anamnesisCollectionNewAnamnesis);
                        oldHospitalizacionOfAnamnesisCollectionNewAnamnesis = em.merge(oldHospitalizacionOfAnamnesisCollectionNewAnamnesis);
                    }
                }
            }
            for (Patologias patologiasCollectionNewPatologias : patologiasCollectionNew) {
                if (!patologiasCollectionOld.contains(patologiasCollectionNewPatologias)) {
                    Hospitalizacion oldHospitalizacionOfPatologiasCollectionNewPatologias = patologiasCollectionNewPatologias.getHospitalizacion();
                    patologiasCollectionNewPatologias.setHospitalizacion(hospitalizacion);
                    patologiasCollectionNewPatologias = em.merge(patologiasCollectionNewPatologias);
                    if (oldHospitalizacionOfPatologiasCollectionNewPatologias != null && !oldHospitalizacionOfPatologiasCollectionNewPatologias.equals(hospitalizacion)) {
                        oldHospitalizacionOfPatologiasCollectionNewPatologias.getPatologiasCollection().remove(patologiasCollectionNewPatologias);
                        oldHospitalizacionOfPatologiasCollectionNewPatologias = em.merge(oldHospitalizacionOfPatologiasCollectionNewPatologias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospitalizacion.getIdHospitalizacion();
                if (findHospitalizacion(id) == null) {
                    throw new NonexistentEntityException("The hospitalizacion with id " + id + " no longer exists.");
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
            Hospitalizacion hospitalizacion;
            try {
                hospitalizacion = em.getReference(Hospitalizacion.class, id);
                hospitalizacion.getIdHospitalizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospitalizacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Examenes> examenesCollectionOrphanCheck = hospitalizacion.getExamenesCollection();
            for (Examenes examenesCollectionOrphanCheckExamenes : examenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Examenes " + examenesCollectionOrphanCheckExamenes + " in its examenesCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Historialvacunas> historialvacunasCollectionOrphanCheck = hospitalizacion.getHistorialvacunasCollection();
            for (Historialvacunas historialvacunasCollectionOrphanCheckHistorialvacunas : historialvacunasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Historialvacunas " + historialvacunasCollectionOrphanCheckHistorialvacunas + " in its historialvacunasCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Contraindicaciones> contraindicacionesCollectionOrphanCheck = hospitalizacion.getContraindicacionesCollection();
            for (Contraindicaciones contraindicacionesCollectionOrphanCheckContraindicaciones : contraindicacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Contraindicaciones " + contraindicacionesCollectionOrphanCheckContraindicaciones + " in its contraindicacionesCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Desparacitaciones> desparacitacionesCollectionOrphanCheck = hospitalizacion.getDesparacitacionesCollection();
            for (Desparacitaciones desparacitacionesCollectionOrphanCheckDesparacitaciones : desparacitacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Desparacitaciones " + desparacitacionesCollectionOrphanCheckDesparacitaciones + " in its desparacitacionesCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Farmacos> farmacosCollectionOrphanCheck = hospitalizacion.getFarmacosCollection();
            for (Farmacos farmacosCollectionOrphanCheckFarmacos : farmacosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Farmacos " + farmacosCollectionOrphanCheckFarmacos + " in its farmacosCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Procedimientos> procedimientosCollectionOrphanCheck = hospitalizacion.getProcedimientosCollection();
            for (Procedimientos procedimientosCollectionOrphanCheckProcedimientos : procedimientosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Procedimientos " + procedimientosCollectionOrphanCheckProcedimientos + " in its procedimientosCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Anamnesis> anamnesisCollectionOrphanCheck = hospitalizacion.getAnamnesisCollection();
            for (Anamnesis anamnesisCollectionOrphanCheckAnamnesis : anamnesisCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Anamnesis " + anamnesisCollectionOrphanCheckAnamnesis + " in its anamnesisCollection field has a non-nullable hospitalizacion field.");
            }
            Collection<Patologias> patologiasCollectionOrphanCheck = hospitalizacion.getPatologiasCollection();
            for (Patologias patologiasCollectionOrphanCheckPatologias : patologiasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hospitalizacion (" + hospitalizacion + ") cannot be destroyed since the Patologias " + patologiasCollectionOrphanCheckPatologias + " in its patologiasCollection field has a non-nullable hospitalizacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Mascota mascota = hospitalizacion.getMascota();
            if (mascota != null) {
                mascota.getHospitalizacionCollection().remove(hospitalizacion);
                mascota = em.merge(mascota);
            }
            Veterinario veterinario = hospitalizacion.getVeterinario();
            if (veterinario != null) {
                veterinario.getHospitalizacionCollection().remove(hospitalizacion);
                veterinario = em.merge(veterinario);
            }
            em.remove(hospitalizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hospitalizacion> findHospitalizacionEntities() {
        return findHospitalizacionEntities(true, -1, -1);
    }

    public List<Hospitalizacion> findHospitalizacionEntities(int maxResults, int firstResult) {
        return findHospitalizacionEntities(false, maxResults, firstResult);
    }

    private List<Hospitalizacion> findHospitalizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hospitalizacion.class));
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

    public Hospitalizacion findHospitalizacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hospitalizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospitalizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hospitalizacion> rt = cq.from(Hospitalizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
