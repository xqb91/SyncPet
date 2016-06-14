/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.starlabs.controladores;

import cl.starlabs.controladores.exceptions.NonexistentEntityException;
import cl.starlabs.controladores.exceptions.PreexistingEntityException;
import cl.starlabs.modelo.Alergias;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import cl.starlabs.modelo.Mascota;
import cl.starlabs.modelo.TipoAlergia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Manuel Araya
 */
public class AlergiasJpaController implements Serializable {

    public AlergiasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alergias alergias) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota mascota = alergias.getMascota();
            if (mascota != null) {
                mascota = em.getReference(mascota.getClass(), mascota.getIdMascota());
                alergias.setMascota(mascota);
            }
            TipoAlergia idTipoAlergia = alergias.getIdTipoAlergia();
            if (idTipoAlergia != null) {
                idTipoAlergia = em.getReference(idTipoAlergia.getClass(), idTipoAlergia.getIdTipoAlergia());
                alergias.setIdTipoAlergia(idTipoAlergia);
            }
            em.persist(alergias);
            if (mascota != null) {
                mascota.getAlergiasList().add(alergias);
                mascota = em.merge(mascota);
            }
            if (idTipoAlergia != null) {
                idTipoAlergia.getAlergiasList().add(alergias);
                idTipoAlergia = em.merge(idTipoAlergia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlergias(alergias.getIdAlergia()) != null) {
                throw new PreexistingEntityException("Alergias " + alergias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alergias alergias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alergias persistentAlergias = em.find(Alergias.class, alergias.getIdAlergia());
            Mascota mascotaOld = persistentAlergias.getMascota();
            Mascota mascotaNew = alergias.getMascota();
            TipoAlergia idTipoAlergiaOld = persistentAlergias.getIdTipoAlergia();
            TipoAlergia idTipoAlergiaNew = alergias.getIdTipoAlergia();
            if (mascotaNew != null) {
                mascotaNew = em.getReference(mascotaNew.getClass(), mascotaNew.getIdMascota());
                alergias.setMascota(mascotaNew);
            }
            if (idTipoAlergiaNew != null) {
                idTipoAlergiaNew = em.getReference(idTipoAlergiaNew.getClass(), idTipoAlergiaNew.getIdTipoAlergia());
                alergias.setIdTipoAlergia(idTipoAlergiaNew);
            }
            alergias = em.merge(alergias);
            if (mascotaOld != null && !mascotaOld.equals(mascotaNew)) {
                mascotaOld.getAlergiasList().remove(alergias);
                mascotaOld = em.merge(mascotaOld);
            }
            if (mascotaNew != null && !mascotaNew.equals(mascotaOld)) {
                mascotaNew.getAlergiasList().add(alergias);
                mascotaNew = em.merge(mascotaNew);
            }
            if (idTipoAlergiaOld != null && !idTipoAlergiaOld.equals(idTipoAlergiaNew)) {
                idTipoAlergiaOld.getAlergiasList().remove(alergias);
                idTipoAlergiaOld = em.merge(idTipoAlergiaOld);
            }
            if (idTipoAlergiaNew != null && !idTipoAlergiaNew.equals(idTipoAlergiaOld)) {
                idTipoAlergiaNew.getAlergiasList().add(alergias);
                idTipoAlergiaNew = em.merge(idTipoAlergiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alergias.getIdAlergia();
                if (findAlergias(id) == null) {
                    throw new NonexistentEntityException("The alergias with id " + id + " no longer exists.");
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
            Alergias alergias;
            try {
                alergias = em.getReference(Alergias.class, id);
                alergias.getIdAlergia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alergias with id " + id + " no longer exists.", enfe);
            }
            Mascota mascota = alergias.getMascota();
            if (mascota != null) {
                mascota.getAlergiasList().remove(alergias);
                mascota = em.merge(mascota);
            }
            TipoAlergia idTipoAlergia = alergias.getIdTipoAlergia();
            if (idTipoAlergia != null) {
                idTipoAlergia.getAlergiasList().remove(alergias);
                idTipoAlergia = em.merge(idTipoAlergia);
            }
            em.remove(alergias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alergias> findAlergiasEntities() {
        return findAlergiasEntities(true, -1, -1);
    }

    public List<Alergias> findAlergiasEntities(int maxResults, int firstResult) {
        return findAlergiasEntities(false, maxResults, firstResult);
    }

    private List<Alergias> findAlergiasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alergias.class));
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

    public Alergias findAlergias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alergias.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlergiasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alergias> rt = cq.from(Alergias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
