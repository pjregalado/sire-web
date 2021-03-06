/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sire.ws.service;

import com.sire.entities.PrySupervisorUsuario;
import com.sire.entities.PrySupervisorUsuarioPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("com.sire.entities.prysupervisorusuario")
public class PrySupervisorUsuarioFacadeREST extends AbstractFacade<PrySupervisorUsuario> {

    @PersistenceContext(unitName = "com.sire_SIRE-WS_war_1.0.0PU")
    private EntityManager em;

    private PrySupervisorUsuarioPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codEmpresa=codEmpresaValue;codSupervisor=codSupervisorValue;nombreUsuario=nombreUsuarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.sire.entities.PrySupervisorUsuarioPK key = new com.sire.entities.PrySupervisorUsuarioPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codEmpresa = map.get("codEmpresa");
        if (codEmpresa != null && !codEmpresa.isEmpty()) {
            key.setCodEmpresa(codEmpresa.get(0));
        }
        java.util.List<String> codSupervisor = map.get("codSupervisor");
        if (codSupervisor != null && !codSupervisor.isEmpty()) {
            key.setCodSupervisor(new java.lang.Integer(codSupervisor.get(0)));
        }
        java.util.List<String> nombreUsuario = map.get("nombreUsuario");
        if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
            key.setNombreUsuario(nombreUsuario.get(0));
        }
        return key;
    }

    public PrySupervisorUsuarioFacadeREST() {
        super(PrySupervisorUsuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PrySupervisorUsuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, PrySupervisorUsuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.sire.entities.PrySupervisorUsuarioPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PrySupervisorUsuario find(@PathParam("id") PathSegment id) {
        com.sire.entities.PrySupervisorUsuarioPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Path("{codEmpresa}&{nombreUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PrySupervisorUsuario find(@PathParam("codEmpresa") String codEmpresa, @PathParam("nombreUsuario") String nombreUsuario) {
        TypedQuery<PrySupervisorUsuario> query = em.createNamedQuery("PrySupervisorUsuario.findByCodEmpresaNombreUsuario", PrySupervisorUsuario.class);
        query.setParameter("codEmpresa", codEmpresa);
        query.setParameter("nombreUsuario", nombreUsuario.toUpperCase());
        PrySupervisorUsuario prySupervisorUsuario = query.getSingleResult();
        return prySupervisorUsuario;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PrySupervisorUsuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PrySupervisorUsuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
