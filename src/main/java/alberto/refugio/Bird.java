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
public class Bird extends animal{
    
    public Bird(int id, String name, String type, String race, int age, float weight, Date entryingDate, Date leavingDate) {
        super(id, name, "bird", race, age, weight, entryingDate, leavingDate);
    }
    
}
