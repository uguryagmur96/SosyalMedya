/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sosyalmedya.util;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ugur
 */
@RequiredArgsConstructor
public class ServiceManager<T,ID> implements IService<T, ID>{
private final JpaRepository<T,ID> repository;

  /**
     * DIKKAT !!!
     * update ve save islemleri repository save (T entity) seklinde tanimlidir.
     * peki update ile save nasil ayirt ediliyor?
     * Bir orm aracı kayıtları varliklarla eslestirirken id yi kullanir. yani varlik tablo iliskisini id ile kurar.
     * Bu neden eger eger kayit ettiginiz entity icinde id bilgisi null ise save metodu kayit islemi yapar
     * eger id bilgisi dolu ise guncelleme islemi yapar.
     */

    @Override
    public T save(T entity) {
      return repository.save(entity);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entityList) {
       return repository.saveAll(entityList);
        
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
       repository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findByAllIds(List<ID> ids) {
        return repository.findAllById(ids);
             
    }
    
}
