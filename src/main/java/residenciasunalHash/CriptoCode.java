/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package residenciasunalhash;

/**
 *
 * @author Jthom
 */
public class CriptoCode {

    private static final String CLAVE = "XLR8";

    //Encriptar: XOR simple con clave + conversión a ASCII extendido
    public static String encriptar(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            char k = CLAVE.charAt(i % CLAVE.length());
            char e = (char) (c ^ k); // XOR
            resultado.append((char)(e + 30)); // Desplazamiento visible
        }
        return resultado.toString();
    }

    //Desencriptar: inverso del proceso anterior
    public static String desencriptar(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char e = (char)(texto.charAt(i) - 30); // Revertir desplazamiento
            char k = CLAVE.charAt(i % CLAVE.length());
            char d = (char)(e ^ k); // XOR inverso
            resultado.append(d);
        }
        return resultado.toString();
    }
}
