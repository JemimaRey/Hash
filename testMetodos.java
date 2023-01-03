/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ED;

/**
 *
 * @author Jemima
 */
public class testMetodos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arreglo array = new Arreglo(false, true,false,true);
        array.guardarElemento("1212");
        array.guardarElemento("1313");
        array.guardarElemento("1414");
        array.guardarElemento("1313");
        array.guardarElemento("9999");
        
        /*System.out.println(array.buscar("1212"));
        System.out.println(array.buscar("1313"));
        System.out.println(array.buscar("1414"));
        System.out.println(array.buscar("9999"));
*/
    }
    
    
}
