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
    private File FileIndicePrimario;
    private File FileIndiceSecundario;
    private RandomAccessFile IA;
    private RandomAccessFile IAIndicePrimario;
    private RandomAccessFile IAIndiceSecundario;
    private Cabecera cab = new Cabecera();
    private Nodo nodoP;
    private Nodo nodoS;
    private Nodo[] IndicePrimario;
    private Nodo[] IndiceSecundario;

    public Archivo(String NombreArchivo, String extension) {
        this.nombreArchivo = NombreArchivo;
        this.extension = extension;
        this.File = new File(this.ruta + NombreArchivo + "." + extension);
        this.FileIndicePrimario = new File(ruta + "IndicePrimario" + nombreArchivo + ".ind");
        this.FileIndiceSecundario = new File(ruta + "IndiceSecundario" + nombreArchivo + ".ind");
        this.nodoP = new Nodo();
        this.nodoS = new Nodo();
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

    public Nodo getNodoP() {
        return nodoP;
    }

    public void setNodoP(Nodo nodoP) {
        this.nodoP = nodoP;
    }

    public Nodo[] getIndicePrimario() {
        return IndicePrimario;
    }

    public void setIndicePrimario(Nodo[] IndicePrimario) {
        this.IndicePrimario = IndicePrimario;
    }

    public File getFileIndicePrimario() {
        return FileIndicePrimario;
    }

    public void setFileIndicePrimario(File FileIndicePrimario) {
        this.FileIndicePrimario = FileIndicePrimario;
    }

    public RandomAccessFile getIAIndicePrimario() {
        return IAIndicePrimario;
    }

    public void setIAIndicePrimario(RandomAccessFile IAIndicePrimario) {
        this.IAIndicePrimario = IAIndicePrimario;
    }

    public void ReadModeIAIndicePrimario() throws FileNotFoundException, IOException {
        if (getIAIndicePrimario() != null) {
            getNodoP().Cerrar();
        }
        IAIndicePrimario = new RandomAccessFile(getFileIndicePrimario(), "r");
        getNodoP().setIA(getIAIndicePrimario());
    }

    public void ReadWriteModeIAIndicePrimario() throws FileNotFoundException, IOException {
        if (getIAIndicePrimario() != null) {
            getNodoP().Cerrar();
        }
        IAIndicePrimario = new RandomAccessFile(getFileIndicePrimario(), "rw");
        getNodoP().setIA(getIAIndicePrimario());
    }

    public Nodo getNodoS() {
        return nodoS;
    }

    public void setNodoS(Nodo nodoS) {
        this.nodoS = nodoS;
    }

    public File getFileIndiceSecundario() {
        return FileIndiceSecundario;
    }

    public void setFileIndiceSecundario(File FileIndiceSecundario) {
        this.FileIndiceSecundario = FileIndiceSecundario;
    }

    public RandomAccessFile getIAIndiceSecundario() {
        return IAIndiceSecundario;
    }

    public void setIAIndiceSecundario(RandomAccessFile IAIndiceSecundario) {
        this.IAIndiceSecundario = IAIndiceSecundario;
    }

    public Nodo[] getIndiceSecundario() {
        return IndiceSecundario;
    }

    public void setIndiceSecundario(Nodo[] IndiceSecundario) {
        this.IndiceSecundario = IndiceSecundario;
    }

    public void ReadModeIAIndiceSecundario() throws FileNotFoundException, IOException {
        if (getIAIndiceSecundario() != null) {
            getNodoS().Cerrar();
        }
        IAIndiceSecundario = new RandomAccessFile(getFileIndiceSecundario(), "r");
        getNodoS().setIA(getIAIndiceSecundario());
    }

    public void ReadWriteModeIAIndiceSecundario() throws FileNotFoundException, IOException {
        if (getIAIndiceSecundario() != null) {
            getNodoS().Cerrar();
        }
        IAIndiceSecundario = new RandomAccessFile(getFileIndiceSecundario(), "rw");
        getNodoS().setIA(getIAIndiceSecundario());
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

    public long FragmentacionExterna1() throws IOException {
        return -1;
    }

    public long FragmentacionExterna2() throws IOException {
        return -1;
    }

    public long FragmentacionInterna() throws IOException {
        return -1;
    }

    public void CrearArchivoIdices() throws IOException {

        if (getFileIndicePrimario().exists()) {
            ReadModeIAIndicePrimario();
            ReadModeIAIndiceSecundario();
        } else {
            getFileIndicePrimario().createNewFile();
            getFileIndiceSecundario().createNewFile();
            ReadWriteModeIAIndicePrimario();
            ReadWriteModeIAIndiceSecundario();
        }
    }

    public void CargarIndicePrimario() throws IOException {

    }

}
