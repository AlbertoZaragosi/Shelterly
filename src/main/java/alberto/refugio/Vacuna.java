/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

/**
 *
 * @author neong
 */
public class Vacuna {
    int id;
    String vname;
    String vanimal_type;
    int duracionDias;

    public Vacuna(int id, String vname, String vanimal_type, int duracionDias) {
        this.id = id;
        this.vname = vname;
        this.vanimal_type = vanimal_type;
        this.duracionDias = duracionDias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVanimal_type() {
        return vanimal_type;
    }

    public void setVanimal_type(String vanimal_type) {
        this.vanimal_type = vanimal_type;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }
    
    
    
}
