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
public abstract class animal {
    int id;
    String name;
    String type;
    String race;
    int age;
    float weight;
    Date entryingDate;
    Date leavingDate;

    public animal(int id, String name, String type, String race, int age, float weight, Date entryingDate, Date leavingDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.race = race;
        this.age = age;
        this.weight = weight;
        this.entryingDate = entryingDate;
        this.leavingDate = leavingDate;
    }

    public animal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getEntryingDate() {
        return entryingDate;
    }

    public void setEntryingDate(Date entryingDate) {
        this.entryingDate = entryingDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    @Override
    public String toString() {
        return "animal{" + "id=" + id + ", name=" + name + ", type=" + type + ", race=" + race + ", age=" + age + ", weight=" + weight + ", entryingDate=" + entryingDate + ", leavingDate=" + leavingDate + '}';
    }

    
    
    
}
