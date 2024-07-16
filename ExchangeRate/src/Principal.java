

import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    //public int opcionMenu = 1;
    public static Scanner lectura = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        int opcionMenu = 1;
        String[] arrCodigosDeMonedas = null;
        double montoAConvertir= 0;

        while(opcionMenu != 0){
            opcionMenu = mostrarPantalla(); //retorna la opcion seleccionada por usuario

            if(opcionMenu == 0){ //condición para evitar que, en caso de salir, se ejecute el resto del código. Asi como esta manda un NullPointerException
                System.out.println("Hata Luego\nVuelva Pronto");
                continue;
            }

            arrCodigosDeMonedas = getCurrenciesNames(opcionMenu);
            //printArray(arrCodigosDeMonedas);

            if(arrCodigosDeMonedas == null){ //si la opcion de usuario no es válida, continue
                System.out.println("opción no válida");
                continue;
            }

            System.out.println("Ingrese la cantidad que desea convertir");
            montoAConvertir = lectura.nextInt();

            TasaDeCambio tdc = new TasaDeCambio(arrCodigosDeMonedas);
            //System.out.println("objeto creado");
            tdc.convertir(montoAConvertir);
            //cambiar código para que desde el constructor se inicialice tambien la cantidad a cambiar
            imprimirResultado(tdc);

        }



    }

    public static int mostrarPantalla(){
            int opcion = 0;
            System.out.println("------------------------------------");
            System.out.println("Bienvenido al convertidor de moneda");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Dólar =>> Peso mexicano");
            System.out.println("0) Salir  ");


            System.out.println("Seleccione una opción");
            opcion = lectura.nextInt();
            //System.out.println(opcion);



        return opcion;
    }

    public static String[] getCurrenciesNames(int datoIndice){
        String indice = String.valueOf(datoIndice); //convertir la opcion del usuario a cadena para poder buscarla en el arreglo matrizMonedas
        String[] monedas = new String[2];

        //[opcion del menu, moneda base, moneda a convertir]
        String[][] matrizMonedas = {
                {"1", "USD", "ARS"},
                {"2", "ARS", "USD"},
                {"3", "USD", "BRL"},
                {"4", "BRL", "USD"},
                {"5", "USD", "COP"},
                {"6", "COP", "USD"},
                {"7", "USD", "MXN"}
        };
        //System.out.println(matrizMonedas.length);
        for(int i = 0; i < matrizMonedas.length; i++){
            if(indice.equals(matrizMonedas[i][0])){
                //System.out.println(matrizMonedas[i][1] + " - " + matrizMonedas[i][2]);
                /*
                monedas[0] = matrizMonedas[i][1];
                monedas[1] = matrizMonedas[i][2];
                return monedas;
                */
                return matrizMonedas[i];
            }
        }

        return null;
    }

    public static void printArray(String[] arreglo){
        System.out.println(Arrays.toString(arreglo));
    }

    public static void imprimirResultado(TasaDeCambio objTdc){
        System.out.println(objTdc.getMontoAConvertir() + " [" + objTdc.getMonedaBase() + "] equivalen a " +
                String.format("%.2f", objTdc.getTotal()) + " [" + objTdc.getMonedaObjetivo() + "]");
    }
}
