package com.company;

import java.io.*;

public class Puerto implements Serializable{
    boolean[] ocupado;
    public Hub[] puerto;
    public Puerto(){
        puerto=new Hub[3];
        puerto[0]=new Hub();
        puerto[1]=new Hub();
        puerto[2]=new Hub();
        ocupado=new boolean[3];
    }
    public boolean apilarPuerto(Contenedor c1){
        boolean resultado=false;
        for (int i=0;i<puerto.length;i++){

            resultado=puerto[i].apilar(c1);
            if (resultado==true) break;

        }
        return resultado;
    }

    public Contenedor desapilarPuerto(int columna){
        Contenedor desapilado;
        for (int i=puerto.length-1;i>-1;i--){
            desapilado=puerto[i].desapilar(columna);
            if (desapilado != null) return desapilado;
        }
        return null;
    }

    public int cantidadPaisPuerto(String pais){
        int numero=0;
        for (int i=0;i<puerto.length;i++){
            numero=numero+puerto[i].cantidadPais(pais);
        }
        return numero;
    }



    public static void guardarPuerto(Puerto puerto) {
        try {
            FileOutputStream fileOut = new FileOutputStream("puerto.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(puerto);
            out.close();
            fileOut.close();
            System.out.println("El objeto Puerto se ha guardado en puerto.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Puerto cargarPuerto() {
        Puerto puerto = null;
        try {
            FileInputStream fileIn = new FileInputStream("puerto.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            puerto = (Puerto) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Se ha cargado el objeto Puerto desde puerto.ser");
        } catch (IOException i) {
            // Si no se puede leer el archivo, se crea un nuevo objeto Puerto
            System.out.println("No se pudo cargar el objeto Puerto desde puerto.ser, se creará uno nuevo.");
            puerto = new Puerto();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return puerto;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}


