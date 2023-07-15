package Archivo;

import java.io.IOException;

public class Nodo extends Archivo {

    private String clave;
    private int referencia; //.ind

    public Nodo(String nombreArchivo, String extension) {
        super(nombreArchivo, extension);
    }

    public Nodo(String clave, int referencia) {
        this.clave = clave;
        this.referencia = referencia;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int tamañoRegistro() {
        return 20 + 2 + 8;
    }

    @Override
    public String toString() {
        return "Nodo{" + "clave=" + clave + ", referencia=" + referencia + '}';
    }

    @Override
    public void Posicionar(int i) throws IOException {
        long NRR = tamañoRegistro() * i;
        getIA().seek(NRR);
    }

}
