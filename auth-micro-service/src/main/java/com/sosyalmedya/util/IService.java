/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sosyalmedya.util;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ugur
 */
public interface IService<T,ID> {
    T save(T entity);
    Iterable<T> saveAll(Iterable<T> entityList);
    T update(T entity);
    void delete (T entity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findByAllIds(List<ID> ids);
}
