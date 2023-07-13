/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Sando
 */
public class Archivo {

    private final String ruta = "C:\\Metodos de Acceso\\Archivos\\";
    private String nombreArchivo;
    private String extension;
    private File File;
    private RandomAccessFile IA;
    private Cabecera cab;

    public Archivo(String NombreArchivo, String extension) {
        this.nombreArchivo = NombreArchivo;
        this.extension = extension;
        this.File = new File(this.ruta + NombreArchivo + "." + extension);
        cab = new Cabecera();
    }

    public Archivo() {
        this.nombreArchivo = null;
        this.File = null;
        this.File = null;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public File getFile() {
        return File;
    }

    public void setFile(File File) {
        this.File = File;
    }

    public RandomAccessFile getIA() {
        return IA;
    }

    public void setIA(RandomAccessFile IA) {
        this.IA = IA;
    }

    public Cabecera getCab() {
        return cab;
    }

    public void setCab(Cabecera cab) {
        this.cab = cab;
    }

    public void ReadModeIA() throws FileNotFoundException {
        IA = new RandomAccessFile(getFile(), "r");
    }

    public void ReadWriteModeIA() throws FileNotFoundException {
        IA = new RandomAccessFile(getFile(), "rw");
    }

    public void CrearArchivo() throws IOException {
        if (!File.exists()) {
            File.createNewFile();
            ReadWriteModeIA();
            cab.setIA(getIA());
            cab.Posicionar();
            cab.Escribir();
        } else {
            ReadWriteModeIA();
            cab.setIA(getIA());
            cab.Posicionar();
            cab.Leer();
        }

    }

    public void Cerrar() throws IOException {
        getIA().close();
    }

    public void Escribir() throws IOException {

    }

    public void Leer() throws IOException {

    }

    public void Posicionar(int pos) throws IOException {

    }

}
