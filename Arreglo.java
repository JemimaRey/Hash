/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ED;

import javax.swing.JOptionPane;

/**
 *
 * @author Jemima
 */
public class Arreglo {
    // Atributos

    int cantidad_elementos = 101;
    String[] arreglo = new String[cantidad_elementos];

    boolean mod, cuadrado, truncamiento, ddh;

    public Arreglo(boolean mod, boolean ddh, boolean cuadrado, boolean truncamiento) {
        this.mod = mod;
        this.cuadrado = cuadrado;
        this.truncamiento = truncamiento;
        this.ddh = ddh;

    }

    /*
    Métodos
    
    Los métodos aquí serán muy variados.
    Para crear direcciones serán
    - Módulo
    - truncamiento
    Estos métodos funcionarán tomando como parámetro el número que se quiera guardar
    o buscar, luego devolverá la dirección que creó
    
    Para resolver colisiones
    - Doble dirección hash
    - Prueba lineal
     */
    // Módulo
    public void guardarElemento(String elemento) {
        int direccion;

        direccion = metodoDirecciones(elemento);
        // si el lugar que se sacó no está vacío...

        if (this.arreglo[direccion] == null ? elemento == null : this.arreglo[direccion].equals(elemento)) {
            System.out.println("Elemento repetido");
            JOptionPane.showMessageDialog(null, "Elemento repetido");
        } else {
            if (this.arreglo[direccion] != null) {
                System.out.println("Has caído en una colision en la posición " + direccion);
                JOptionPane.showMessageDialog(null, "Has caído en una colision en la posición " + direccion);
                direccion = metodoColisiones(direccion, null, elemento);
            }

            try {
                this.arreglo[direccion] = elemento;
                System.out.println("Has guardado el elemento en la posición " + direccion);
                JOptionPane.showMessageDialog(null, "Has guardado el elemento en la posición " + direccion);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No se pudo encontrar un lugar para ese número");
                JOptionPane.showMessageDialog(null, "No se pudo encontrar un lugar para ese número");
            }
        }
    }

    public void eliminar(String elemento) {
        int posicion = this.buscar(elemento);
        try {
            this.arreglo[posicion] = null;
            JOptionPane.showMessageDialog(null, "Elemento eliminado");
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No existe ese elemento");
        }
    }

    public int dobleDireccionHash(int direccion_inicial, String buscado, String value) {
        // revisar si el número 0 era la dirección repetida, y si es así, se le puede
        // hacer con los dos ultimos digitos del número, o bien usar el método de
        // prueba lineal
        int nueva_direccion;
        if (direccion_inicial == 0) {
            String cadena = value.substring(value.length() - 2);
            System.out.println(cadena);
            nueva_direccion = Integer.valueOf(cadena);
            nueva_direccion = nueva_direccion * nueva_direccion;
            nueva_direccion = metodoDirecciones(String.valueOf(nueva_direccion));
        } else {

            nueva_direccion = direccion_inicial;

        }
        // si el método de direcciones seleccionado es el del módulo ...

        int x = 0;
        while (x < 99 && (this.arreglo[nueva_direccion] == null ? buscado != null : !this.arreglo[nueva_direccion].equals(buscado))) {
            if (nueva_direccion % this.cantidad_elementos == nueva_direccion && mod) {
                nueva_direccion = (nueva_direccion * nueva_direccion) + 1013;
            }
            
            nueva_direccion = metodoDirecciones(String.valueOf(nueva_direccion));
            System.out.println("Nueva direccion: "+nueva_direccion);
            
            if (buscado == null && (this.arreglo[nueva_direccion] == null ? value == null : this.arreglo[nueva_direccion].equals(value))) {
                System.out.println("Elemento repetido");
                JOptionPane.showMessageDialog(null, "Elemento repetido");
                return -1;
            }

            x++;
        }
        System.out.println(x);
        // si no se encontró lo que se buscaba, devolverá -1
        if (this.arreglo[nueva_direccion] == null ? buscado != null : !this.arreglo[nueva_direccion].equals(buscado)) {
            return -1;
        } else {
            return nueva_direccion;
        }
    }

    public int pruebaLineal(int direccion_inicial, String buscado) {
        /*
    Este método deberá hacer una busqueda, primero con los elementos de arriba, luego los de abajo    
         */
        int nueva_direccion = -1;

        for (int x = direccion_inicial + 1; x < this.arreglo.length; x++) {

            if (this.arreglo[x] == null ? buscado == null : this.arreglo[x].equals(buscado)) {
                nueva_direccion = x;
                return nueva_direccion;
            }
        }

        for (int x = direccion_inicial - 1; x >= 0; x--) {
            if (this.arreglo[x] == null ? buscado == null : this.arreglo[x].equals(buscado)) {
                nueva_direccion = x;
                return nueva_direccion;
            }
        }

        return nueva_direccion;
    }

    public int truncamiento(String valor) {
        //Este método crea una dirección tomando algunos dígitos de la clave
        //Se toman el primer y tercer digíto
        char n1, n2;
        String dt;
        int direccion;

        int x = 0;
        /*
        Primero se asegura que el valor con el que se va a trabar tenga una longitud
        de al menos 4
         */
        while (valor.length() < 4 && x < 5) {
            int valor_int = Integer.valueOf(valor);
            valor_int = (valor_int * valor_int) + 100;
            valor = String.valueOf(valor_int);
            x++;
        }
        /*
        Se toma el primero y el tercero, si llegará a haber una excepcion devolverá -1
         */
        try {
            n1 = valor.charAt(0);
            n2 = valor.charAt(2);
            dt = Character.toString(n1) + Character.toString(n2);
            System.out.println(dt);
            direccion = Integer.parseInt(dt);
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            direccion = -1;
        }

        return direccion;

    }

    public int modulo(String valor) {
        // Este método crea una dirección sacando el módulo
        // convertir el valor (que está en string) a int, para luego
        // hacer la division y devolver esa dirección
        // devuelve la dirección
        int direccion;
        int valor2 = Integer.parseInt(valor);
        direccion = valor2 % this.cantidad_elementos;
        System.out.println("Dirección modulo: " + direccion);
        /*try{
            direccion= Integer.parseInt(valor);
        }catch(NumberFormatException nfe){
            return -1;
        }*/

        return direccion;
    }

    public int cuadrado(String valor) { //FUNCIONA
        // Este método crea una dirección elevando al cuadrado y tomando los dígitos centrales
        // devuelve la dirección
        int valor_int = Integer.valueOf(valor);
        valor_int = valor_int * valor_int;
        String cadena = String.valueOf(valor_int);
        return Integer.valueOf(cadena.substring((cadena.length() - 1) / 2, (cadena.length() - 1) / 2 + 2));
    }

    public int buscar(String valor) { //MODIFICAR ESTE
        int direccion;
        direccion = metodoDirecciones(valor);
        // si el lugar que se sacó no tiene el valor que se está buscando...
        if (this.arreglo[direccion] == null ? valor != null : !this.arreglo[direccion].equals(valor)) {
            direccion = metodoColisiones(direccion, valor, valor);
        }
        // OJO, PUEDE DEVOLVER UN -1
        return direccion;
    }

    public int metodoDirecciones(String valor) {
        int direccion;
        if (this.mod) {
            direccion = modulo(valor);

        } else if (this.cuadrado) {

            direccion = cuadrado(valor);
        } else {
            direccion = truncamiento(valor);
        }
        return direccion;
    }

    public int metodoColisiones(int direccion_inicial, String buscado, String value) {
        int nueva_direccion;
        if (ddh) {
            nueva_direccion = this.dobleDireccionHash(direccion_inicial, buscado, value);
        } else {
            nueva_direccion = this.pruebaLineal(direccion_inicial, buscado);
        }
        return nueva_direccion;
    }
}
