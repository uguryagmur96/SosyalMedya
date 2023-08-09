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
    public static final String AUTH=API+VERSION+"/auth";
    public static final String LOGIN="/login";
    public static final String REGISTER="/register";
    public static final String PROD="/prod";
    public static final String TEST="/test";
    public static final String SAVE="/save";
    public static final String UPDATE="/update";
    public static final String DELETE="/delete";
    public static final String FINDALL="/findall";
    public static final String FINDALLVWPERSONEL="/findallvwpersonel";
    public static final String SAVEDTO="/savedto";
    public static final String FINDALLVWDOKTORMAPPER="/findallvwdoktormapper";
    public static final String SAVEDTOMAPPER="/savedtomapper";
    public static final String SAVEDTOMAPPER2="/savedtomapper2"; 
    public static final String FINDBYAD="/findbyad";
    public static final String FINDALLVWDOKTORDTOMAPPER="/findallvwdoktodtormapper";
    
    
}
