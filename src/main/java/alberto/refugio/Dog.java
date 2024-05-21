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
public class Dog extends animal{
    public Dog(int id, String name, String type, String race, int age, float weight, Date entryingDate, Date leavingDate) {
        super(id, name, "dog", race, age, weight, entryingDate, leavingDate);
        
    }
    public Dog(int id, String name, String type, String race, int age, float weight, Date entryingDate, Date leavingDate,String owner) {
        super(id, name, "dog", race, age, weight, entryingDate, leavingDate,owner);
    }
    
}
