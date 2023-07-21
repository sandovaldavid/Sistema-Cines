package Archivo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Nodo {

    // [Nombre indice].ind
    private RandomAccessFile IA;
    private String clave;   //22 bytes
    private int referencia; //4 bytes

    public Nodo() {
        this.clave = "";
        this.referencia = -1;
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

    public RandomAccessFile getIA() {
        return IA;
    }

    public void setIA(RandomAccessFile IA) {
        this.IA = IA;
    }

    public int getSize() {
        return 26;
    }

    @Override
    public String toString() {
        return "Nodo{" + "clave=" + clave + ", referencia=" + referencia + '}';
    }

    public void Posicionar(int i) throws IOException {
        int NRR = getSize() * i;
        getIA().seek(NRR);
    }

    public void Cerrar() throws IOException {
        getIA().close();
    }

    public void Escribir() throws IOException {
        getIA().writeUTF(String.format("%20.20s", getClave()));
        getIA().writeInt(getReferencia());
    }

    public void Leer() throws IOException {
        setClave(getIA().readUTF());
        setReferencia(getIA().readInt());
    }

}
