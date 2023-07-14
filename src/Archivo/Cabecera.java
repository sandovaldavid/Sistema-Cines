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
    private byte compactado;                     // 1 byte

    public Cabecera() {
        this.numeroRegistros = 0;
        this.numeroRegistrosEliminados = 0;
        this.tamañoRegistro = 0;
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

    @Override
    public String toString() {
        return "Cabecera{" + "IACab=" + IA + ", numeroRegistros=" + numeroRegistros + ", numeroRegistrosEliminados=" + numeroRegistrosEliminados + ", tama\u00f1oRegistro=" + tamañoRegistro + '}';
    }

    public int getSize() {
        return 12;
    }

    public void Posicionar() throws IOException {
        getIA().seek(0);
    }

    public void Escribir() throws IOException {
        getIA().writeInt(getNumeroRegistros());
        getIA().writeInt(getNumeroRegistrosEliminados());
        getIA().writeInt(getTamañoRegistro());
    }

    public void Leer() throws IOException {
        setNumeroRegistros(getIA().readInt());
        setNumeroRegistrosEliminados(getIA().readInt());
        setTamañoRegistro(getIA().readInt());
    }
}
