/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

import java.sql.Date;

/**
 *
 * @author neong
 */
public class Reptile extends animal{
    

    public Reptile(int id, String name, String type, String race, int age, float weight, Date entryingDate, Date leavingDate) {
        super(id, name, "reptile", race, age, weight, entryingDate, leavingDate);
    }
public Reptile(int id, String name, String type, String race, int age, float weight, Date entryingDate, Date leavingDate,String owner) {
        super(id, name, "reptile", race, age, weight, entryingDate, leavingDate,owner);
    }
    
    
}
