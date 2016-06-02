/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Farmacos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Hospitalizacion;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.TipoFarmaco;
import cl.starlabs.modelo.Veterinario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cetecom
 */
public class FarmacosJpaController implements Serializable {

    public FarmacosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Farmacos farmacos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hospitalizacion hospitalizacion = farmacos.getHospitalizacion();
            if (hospitalizacion != null) {
                hospitalizacion = em.getReference(hospitalizacion.getClass(), hospitalizacion.getIdHospitalizacion());
                farmacos.setHospitalizacion(hospitalizacion);
            }
            Mascota mascota = farmacos.getMascota();
            if (mascota != null) {
                mascota = em.getReference(mascota.getClass(), mascota.getIdMascota());
                farmacos.setMascota(mascota);
            }
            TipoFarmaco farmaco = farmacos.getFarmaco();
            if (farmaco != null) {
                farmaco = em.getReference(farmaco.getClass(), farmaco.getIdFarmaco());
                farmacos.setFarmaco(farmaco);
            }
            Veterinario veterinario = farmacos.getVeterinario();
            if (veterinario != null) {
                veterinario = em.getReference(veterinario.getClass(), veterinario.getIdVeterinario());
                farmacos.setVeterinario(veterinario);
            }
            em.persist(farmacos);
            if (hospitalizacion != null) {
                hospitalizacion.getFarmacosCollection().add(farmacos);
                hospitalizacion = em.merge(hospitalizacion);
            }
            if (mascota != null) {
                mascota.getFarmacosCollection().add(farmacos);
                mascota = em.merge(mascota);
            }
            if (farmaco != null) {
                farmaco.getFarmacosCollection().add(farmacos);
                farmaco = em.merge(farmaco);
            }
            if (veterinario != null) {
                veterinario.getFarmacosCollection().add(farmacos);
                veterinario = em.merge(veterinario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFarmacos(farmacos.getIdFarmaco()) != null) {
                throw new PreexistingEntityException("Farmacos " + farmacos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Farmacos farmacos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Farmacos persistentFarmacos = em.find(Farmacos.class, farmacos.getIdFarmaco());
            Hospitalizacion hospitalizacionOld = persistentFarmacos.getHospitalizacion();
            Hospitalizacion hospitalizacionNew = farmacos.getHospitalizacion();
            Mascota mascotaOld = persistentFarmacos.getMascota();
            Mascota mascotaNew = farmacos.getMascota();
            TipoFarmaco farmacoOld = persistentFarmacos.getFarmaco();
            TipoFarmaco farmacoNew = farmacos.getFarmaco();
            Veterinario veterinarioOld = persistentFarmacos.getVeterinario();
            Veterinario veterinarioNew = farmacos.getVeterinario();
            if (hospitalizacionNew != null) {
                hospitalizacionNew = em.getReference(hospitalizacionNew.getClass(), hospitalizacionNew.getIdHospitalizacion());
                farmacos.setHospitalizacion(hospitalizacionNew);
            }
            if (mascotaNew != null) {
                mascotaNew = em.getReference(mascotaNew.getClass(), mascotaNew.getIdMascota());
                farmacos.setMascota(mascotaNew);
            }
            if (farmacoNew != null) {
                farmacoNew = em.getReference(farmacoNew.getClass(), farmacoNew.getIdFarmaco());
                farmacos.setFarmaco(farmacoNew);
            }
            if (veterinarioNew != null) {
                veterinarioNew = em.getReference(veterinarioNew.getClass(), veterinarioNew.getIdVeterinario());
                farmacos.setVeterinario(veterinarioNew);
            }
            farmacos = em.merge(farmacos);
            if (hospitalizacionOld != null && !hospitalizacionOld.equals(hospitalizacionNew)) {
                hospitalizacionOld.getFarmacosCollection().remove(farmacos);
                hospitalizacionOld = em.merge(hospitalizacionOld);
            }
            if (hospitalizacionNew != null && !hospitalizacionNew.equals(hospitalizacionOld)) {
                hospitalizacionNew.getFarmacosCollection().add(farmacos);
                hospitalizacionNew = em.merge(hospitalizacionNew);
            }
            if (mascotaOld != null && !mascotaOld.equals(mascotaNew)) {
                mascotaOld.getFarmacosCollection().remove(farmacos);
                mascotaOld = em.merge(mascotaOld);
            }
            if (mascotaNew != null && !mascotaNew.equals(mascotaOld)) {
                mascotaNew.getFarmacosCollection().add(farmacos);
                mascotaNew = em.merge(mascotaNew);
            }
            if (farmacoOld != null && !farmacoOld.equals(farmacoNew)) {
                farmacoOld.getFarmacosCollection().remove(farmacos);
                farmacoOld = em.merge(farmacoOld);
            }
            if (farmacoNew != null && !farmacoNew.equals(farmacoOld)) {
                farmacoNew.getFarmacosCollection().add(farmacos);
                farmacoNew = em.merge(farmacoNew);
            }
            if (veterinarioOld != null && !veterinarioOld.equals(veterinarioNew)) {
                veterinarioOld.getFarmacosCollection().remove(farmacos);
                veterinarioOld = em.merge(veterinarioOld);
            }
            if (veterinarioNew != null && !veterinarioNew.equals(veterinarioOld)) {
                veterinarioNew.getFarmacosCollection().add(farmacos);
                veterinarioNew = em.merge(veterinarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = farmacos.getIdFarmaco();
                if (findFarmacos(id) == null) {
                    throw new NonexistentEntityException("The farmacos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Farmacos farmacos;
            try {
                farmacos = em.getReference(Farmacos.class, id);
                farmacos.getIdFarmaco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The farmacos with id " + id + " no longer exists.", enfe);
            }
            Hospitalizacion hospitalizacion = farmacos.getHospitalizacion();
            if (hospitalizacion != null) {
                hospitalizacion.getFarmacosCollection().remove(farmacos);
                hospitalizacion = em.merge(hospitalizacion);
            }
            Mascota mascota = farmacos.getMascota();
            if (mascota != null) {
                mascota.getFarmacosCollection().remove(farmacos);
                mascota = em.merge(mascota);
            }
            TipoFarmaco farmaco = farmacos.getFarmaco();
            if (farmaco != null) {
                farmaco.getFarmacosCollection().remove(farmacos);
                farmaco = em.merge(farmaco);
            }
            Veterinario veterinario = farmacos.getVeterinario();
            if (veterinario != null) {
                veterinario.getFarmacosCollection().remove(farmacos);
                veterinario = em.merge(veterinario);
            }
            em.remove(farmacos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Farmacos> findFarmacosEntities() {
        return findFarmacosEntities(true, -1, -1);
    }

    public List<Farmacos> findFarmacosEntities(int maxResults, int firstResult) {
        return findFarmacosEntities(false, maxResults, firstResult);
    }

    private List<Farmacos> findFarmacosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Farmacos.class));
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

    public Farmacos findFarmacos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Farmacos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFarmacosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Farmacos> rt = cq.from(Farmacos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
