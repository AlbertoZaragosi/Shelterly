/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

/**
 *
 * @author neong
 */
public class Persona {
    String nombre;
    String email;
    String number;
    String ID;
    int num_animales_adoptados;

    public Persona(String nombre, String email, String number, String ID, int num_animales_adoptados) {
        this.nombre = nombre;
        this.email = email;
        this.number = number;
        this.ID = ID;
        this.num_animales_adoptados = num_animales_adoptados;
    }

    public Persona(String nombre, String email, String number, String ID) {
        this.nombre = nombre;
        this.email = email;
        this.number = number;
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getNum_animales_adoptados() {
        return num_animales_adoptados;
    }

    public void setNum_animales_adoptados(int num_animales_adoptados) {
        this.num_animales_adoptados = num_animales_adoptados;
    }
    
    
}
