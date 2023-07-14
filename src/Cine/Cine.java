/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cine;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Sando
 */
public class Cine extends Archivo.Archivo {

    private String Nombre;      //22 bytes = 20 longitud + 2 tamaño
    private String Ciudad;      //22 bytes    
    private String Direccion;   //32 bytes
    private byte Activo;        //1 byte

    public Cine(String NombreArchivo, String Extension) {
        super(NombreArchivo, Extension);
    }

    public Cine(String Nombre, String Ciudad, String Direccion) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Direccion = Direccion;
        this.Activo = (byte) 1;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public byte getActivo() {
        return Activo;
    }

    public void setActivo(byte Activo) {
        this.Activo = Activo;
    }

    @Override
    public String toString() {
        return "Cine{" + "Nombre=" + Nombre + ", Ciudad=" + Ciudad + ", Direccion=" + Direccion + ", Activo=" + Activo + '}';
    }

    public int getSize() {
        return 77;
    }

    @Override
    public void Posicionar(int NRR) throws IOException {
        long pos = getCab().getSize() + NRR * getSize();
        getIA().seek(pos);
    }

    @Override
    public void Escribir() throws IOException {
        getIA().writeUTF(String.format("%20.20s", getNombre()));
        getIA().writeUTF(String.format("%20.20s", getCiudad()));
        getIA().writeUTF(String.format("%30.30s", getDireccion()));
        getIA().writeByte(getActivo());
    }

    @Override
    public void Leer() throws IOException {
        setNombre(getIA().readUTF().trim());
        setCiudad(getIA().readUTF().trim());
        setDireccion(getIA().readUTF().trim());
        setActivo(getIA().readByte());
    }

    public void IngresarRegistro() throws IOException {
        Posicionar(getCab().getNumeroRegistros());
        Escribir();
        getCab().setNumeroRegistros(getCab().getNumeroRegistros() + 1);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public Cine[] ListadoSecuencial() throws IOException {
        int TamañoArreglo = (int) (getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados());
        Cine[] c = new Cine[TamañoArreglo];
        Cine cine;
        int i = 0;
        boolean flag = true;
        Posicionar(0);
        while (flag) {
            try {
                Leer();
                if (getActivo() == 1) {
                    cine = new Cine(getNombre(), getCiudad(), getDireccion());
                    c[i] = cine;
                    i++;
                }
            } catch (EOFException ex) {
                flag = false;
            }
        }
        return c;
    }

    public int BusquedaSecuencial(String Nombre) throws IOException {
        int answer = -1;
        int i = 0;
        boolean flag = true;
        Posicionar(0);
        while (flag && answer == -1) {
            try {
                Leer();
                if (getActivo() == 1) {
                    if (Nombre.equals(getNombre())) {
                        answer = i;
                        flag = false;
                    }
                }
                i++;
            } catch (EOFException e) {
                flag = false;
            }
        }
        return answer;
    }

    public void EliminarRegistro(int respuesta) throws IOException {
        setActivo((byte) 0);
        Posicionar(respuesta);
        Escribir();
        getCab().setNumeroRegistrosEliminados(getCab().getNumeroRegistrosEliminados() + 1);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public void Modificar(int respuesta) throws IOException {
        Posicionar(respuesta);
        Escribir();
    }

    @Override
    public long FragmentacionExterna1() throws IOException {
        boolean flag = true;
        int contador = 0;
        Posicionar(0);
        while (flag) {
            try {
                Leer();
                if (getActivo() == 0) {
                    contador++;
                }
            } catch (EOFException ex) {
                flag = false;
            }
        }
        return (contador * getSize());
    }

    @Override
    public long FragmentacionExterna2() {
        return getCab().getNumeroRegistros() * getSize();
    }

    @Override
    public long FragmentacionInterna() throws IOException {
        boolean flag = true;
        long totalbytes = 0;
        Posicionar(0);
        while (flag) {
            try {
                Leer();
                Cine c = new Cine(getNombre(), getCiudad(), getDireccion());
                if (c.getActivo() == 1) {
                    byte[] bytes = c.getNombre().getBytes(StandardCharsets.UTF_8);
                    int numBytes = bytes.length;

                    byte[] bytes1 = c.getCiudad().getBytes(StandardCharsets.UTF_8);
                    int numBytes1 = bytes1.length;

                    byte[] bytes2 = c.getDireccion().getBytes(StandardCharsets.UTF_8);
                    int numBytes2 = bytes2.length;

                    totalbytes = totalbytes + numBytes + numBytes1 + numBytes2;

                }
            } catch (EOFException ex) {
                flag = false;
            }
        }
        return (getCab().getNumeroRegistros() * (getSize() - 1) - totalbytes);
    }

}
