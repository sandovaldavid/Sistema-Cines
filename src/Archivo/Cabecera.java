/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Sando
 */
public class Cabecera {

    private RandomAccessFile IA;
    private int numeroRegistros;                //4 bytes
    private int numeroRegistrosEliminados;      //4 bytes
    private int tamañoRegistro;                 //4 bytes
    private byte compactado;                    //1 byte    1 --> compactado || 0 --> no compactado
    private byte Ordenado;                      //1 byte    1 --> ordenado || 0 --> ordenado
    private int NRR_Eliminado;                  //4 bytes
    private byte Modificado;                    //1 byte    1 --> Modificado || 0 --> Sin cambios

    public Cabecera() {
        this.numeroRegistros = 0;
        this.numeroRegistrosEliminados = 0;
        this.tamañoRegistro = 0;
        this.compactado = 0;
        this.Ordenado = 0;
        this.NRR_Eliminado = -1;
        this.Modificado = 0;
    }

    public RandomAccessFile getIA() {
        return IA;
    }

    public void setIA(RandomAccessFile IA) {
        this.IA = IA;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public int getNumeroRegistrosEliminados() {
        return numeroRegistrosEliminados;
    }

    public void setNumeroRegistrosEliminados(int numeroRegistrosEliminados) {
        this.numeroRegistrosEliminados = numeroRegistrosEliminados;
    }

    public int getTamañoRegistro() {
        return tamañoRegistro;
    }

    public void setTamañoRegistro(int tamañoRegistro) {
        this.tamañoRegistro = tamañoRegistro;
    }

    public byte getCompactado() {
        return compactado;
    }

    public void setCompactado(byte compactado) {
        this.compactado = compactado;
    }

    public byte getOrdenado() {
        return Ordenado;
    }

    public void setOrdenado(byte Ordenado) {
        this.Ordenado = Ordenado;
    }

    public int getNRR_Eliminado() {
        return NRR_Eliminado;
    }

    public void setNRR_Eliminado(int NRR_Eliminado) {
        this.NRR_Eliminado = NRR_Eliminado;
    }

    public byte getModificado() {
        return Modificado;
    }

    public void setModificado(byte Modificado) {
        this.Modificado = Modificado;
    }

    @Override
    public String toString() {
        return "Cabecera{" + "IA=" + IA + ", numeroRegistros=" + numeroRegistros + ", numeroRegistrosEliminados=" + numeroRegistrosEliminados + ", tama\u00f1oRegistro=" + tamañoRegistro + ", compactado=" + compactado + ", Ordenado=" + Ordenado + ", NRR_Eliminado=" + NRR_Eliminado + ", Modificado=" + Modificado + '}';
    }

    public int getSize() {
        return 19;
    }

    public void Posicionar() throws IOException {
        getIA().seek(0);
    }

    public void Escribir() throws IOException {
        getIA().writeInt(getNumeroRegistros());
        getIA().writeInt(getNumeroRegistrosEliminados());
        getIA().writeInt(getTamañoRegistro());
        getIA().writeByte(getCompactado());
        getIA().writeByte(getOrdenado());
        getIA().writeInt(getNRR_Eliminado());
        getIA().writeByte(getModificado());
    }

    public void Leer() throws IOException {
        setNumeroRegistros(getIA().readInt());
        setNumeroRegistrosEliminados(getIA().readInt());
        setTamañoRegistro(getIA().readInt());
        setCompactado(getIA().readByte());
        setOrdenado(getIA().readByte());
        setNRR_Eliminado(getIA().readInt());
        setModificado(getIA().readByte());
    }
}
