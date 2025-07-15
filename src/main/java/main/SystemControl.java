package main;

import residenciasunalhash.Estudiante;
import residenciasunalhash.ResidenciasUnalHash;

public class SystemControl {
    Estudiante[] estudiantes;
    boolean arrayOrdenado;
    
    ResidenciasUnalHash hashMap = new ResidenciasUnalHash();
    
    public SystemControl(int capacidad_max){
        estudiantes = new Estudiante[capacidad_max];
        arrayOrdenado = true;
    }
    
    public boolean insertarEstudiante(long id, String nombre, int puntaje){
        boolean insertado = hashMap.registrarUsuario(id, nombre, puntaje);
        if(insertado){
            //Insertar en heap también
        }
        
        arrayOrdenado = false;
        return insertado;
    }
    
    public boolean eliminarEstudiante(long id){
        boolean eliminado = hashMap.borrarUsuario(id);
        if(eliminado){
            //"Borrar" en heap también
        } 
        
        arrayOrdenado = false;
        return eliminado;
    }
    
    public Estudiante buscarEstudiante(long id){
        return hashMap.buscarUsuario(id);
    }
    
    public void actualizarPuntaje(long id, int puntaje){
        hashMap.cambiarPuntaje(id, puntaje);
        //Change priority con el heap
    }
    
    
}
