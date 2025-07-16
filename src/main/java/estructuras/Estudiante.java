package estructuras;


public class Estudiante{
    long id;
    String nombre;
    int puntaje;
    public boolean tieneApoyo;
    public boolean eliminado = false;

    public Estudiante(){
        this.id=9999999999L;
        this.nombre="XXXXXX XXXXX XXXXXX XXXXX";
        this.puntaje=0;
        this.tieneApoyo=false;
    }

    public Estudiante(long id, String nombre, int puntaje){
        this.id=id;
        this.nombre=nombre;
        this.puntaje=puntaje;
        this.tieneApoyo=false;
    }

    public long getID(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setPuntaje(int n){
        this.puntaje=n;
    }
    public int getPuntaje(){
        return this.puntaje;
    }

    public void setApoyo(boolean b){
        this.tieneApoyo=b;
    }

    public boolean getApoyo(){
        return this.tieneApoyo;
    }

    public int compareTo(Estudiante otro){
        return Integer.compare(this.puntaje, otro.puntaje);
    }

    public boolean isRemove(){
        return eliminado;
    }

    public void setRemove(){
        this.eliminado = true;
    }
}
