/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author neong
 */
public class RegistroVacuna {
    int id_vacuna;
    int animal_id;
    Date fechaVacunacion;
    int duracion;
    Date nextVacuna;

    public RegistroVacuna(int id_vacuna, int animal_id,Date fechaVacunacion, int duracion) {
        this.id_vacuna = id_vacuna;
        this.animal_id = animal_id;
    
        this.fechaVacunacion = fechaVacunacion;   
        
        
        nextVacuna = Date.valueOf(LocalDate.now().plusDays(duracion));
        
        
        this.duracion = duracion;
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getNextVacuna() {
        return nextVacuna;
    }

    public void setNextVacuna(Date nextVacuna) {
        this.nextVacuna = nextVacuna;
    }
    
    
    
    
    
    
    
    
}
