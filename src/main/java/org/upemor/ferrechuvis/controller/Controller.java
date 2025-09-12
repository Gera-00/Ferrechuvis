package org.upemor.ferrechuvis.controller;

import java.util.List;


import lombok.Getter;
import org.upemor.ferrechuvis.model.entity.Entity;
import org.upemor.ferrechuvis.model.repository.Repository;

public abstract class Controller<R extends Repository<E>,E extends Entity> {
    
    @Getter
    protected R repository;
    
    protected abstract boolean validate(E obj) throws Exception;

    public boolean guardar(E obj) throws Exception {
        try{
            if(validate(obj)){
                if(obj.getId() == 0){
                    return repository.create(obj);
                }
                return repository.update(obj);
            }
            return false;
        }
        catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: guardar");
            throw e;
        }
    }

    public List<E> getAll() throws Exception {
        try {
                return repository.readAll();
            }catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: getAll");
            throw e;
        }
    } 

    public E getById(long id) throws Exception {
        try {
                return repository.read(id);
            }catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: getById ");
            throw e;
        }
    } 
    
    public List<E> getByName(String name) throws Exception {
        try {
                return repository.readByName(name);
            }catch (Exception e) {
            System.out.println("Error: "+e.getMessage()
            +" in class"+this.getClass().getName()
            +" in method: getByName ");
            throw e;
        }
    } 
    
    public boolean delete(long id) throws Exception{
        try {
            E obj = getById(id);
            if(obj != null){
                return repository.delete(id);
            } else {
                System.out.println("Object with id " + id + " not found");
                return false;
            }   
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()
            + " in class " + this.getClass().getName()
            + " in method: delete");
            throw e;
        }
    }  
}
