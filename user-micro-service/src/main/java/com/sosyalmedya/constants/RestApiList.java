/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sosyalmedya.constants;

/**
 *
 * @author Ugur
 */
public class RestApiList {
    /**
     * Projeler genellikle belli sunucular ve belli portlar uzerinden calisirlar
     * Buyuk projelerde ekipler ayrisir ve farkli ekipler farkli endpointler ile istek atarlar. Bu ayrismayi daha 
     * kontrollu yapmak icin sabitleri yonetmek iyi bir fikirdir.
     */
    public static final String API="/api";
    public static final String VERSION="/v1";
    public static final String USER=API+VERSION+"/user";
    public static final String SAVE="/save";
    public static final String FINDALL="/findall";

    
}
