/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cine;

import Archivo.Cabecera;
import Archivo.Nodo;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

/**
 *
 * @author Sando
 */
public class Cine extends Archivo.Archivo {

    private String Nombre;      //22 bytes = 20 longitud + 2 tamaño
    private String Ciudad;      //22 bytes    
    private String Direccion;   //32 bytes
    private byte Activo;        //1 byte
    private int NRR_Eliminado;  //4 bytes
    private Nodo[] IndicePrimario;

    public Cine(String NombreArchivo, String Extension) {
        super(NombreArchivo, Extension);
        this.NRR_Eliminado = -1;
        getCab().setTamañoRegistro(getSize());
    }

    public Cine(String Nombre, String Ciudad, String Direccion) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Direccion = Direccion;
        this.Activo = (byte) 1;
        this.NRR_Eliminado = -1;
        getCab().setTamañoRegistro(getSize());
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

    public int getNRR_Eliminado() {
        return NRR_Eliminado;
    }

    public void setNRR_Eliminado(int NRR_Eliminado) {
        this.NRR_Eliminado = NRR_Eliminado;
    }

    public Nodo[] getIndicePrimario() {
        return IndicePrimario;
    }

    public void setIndicePrimario(Nodo[] IndicePrimario) {
        this.IndicePrimario = IndicePrimario;
    }

    @Override
    public String toString() {
        return "Cine{" + "Nombre=" + Nombre + ", Ciudad=" + Ciudad + ", Direccion=" + Direccion + ", Activo=" + Activo + '}';
    }

    public final int getSize() {
        return 81;
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
        getIA().writeInt(getNRR_Eliminado());
    }

    @Override
    public void Leer() throws IOException {
        setNombre(getIA().readUTF().trim());
        setCiudad(getIA().readUTF().trim());
        setDireccion(getIA().readUTF().trim());
        setActivo(getIA().readByte());
        setNRR_Eliminado(getIA().readInt());
    }

    public void IngresarRegistro() throws IOException {
        //Posicionar(getCab().getNumeroRegistros());
        Cine aux = new Cine(getNombre(), getCiudad(), getDireccion());
        Nodo n = new Nodo(aux.getNombre(), -1);
        InsertarNodo(n);
        PosicionarLugarAdecuadoInsercion();
        setNombre(aux.getNombre());
        setCiudad(aux.getCiudad());
        setDireccion(aux.getDireccion());
        setActivo(aux.getActivo());
        Escribir();
        getCab().setModificado((byte) 1);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public void PosicionarLugarAdecuadoInsercion() throws IOException {
        if (getCab().getNRR_Eliminado() == -1) {
            int a = getCab().getNumeroRegistros();
            getCab().setNumeroRegistros(getCab().getNumeroRegistros() + 1);
            getCab().Posicionar();
            getCab().Escribir();
            Posicionar(a);
        } else {
            int NRR_Eliminado = getCab().getNRR_Eliminado();
            Posicionar(NRR_Eliminado);
            Leer();
            getCab().setNRR_Eliminado(getNRR_Eliminado());
            getCab().setNumeroRegistrosEliminados(getCab().getNumeroRegistrosEliminados() - 1);
            getCab().Posicionar();
            getCab().Escribir();
            Posicionar(NRR_Eliminado);
        }
    }

    public Cine[] ListadoSecuencial() throws IOException {
        int TamañoArreglo = getIndicePrimario().length;
        Cine[] c = new Cine[TamañoArreglo];
        Cine cine;
        int i = 0;
        while (i < TamañoArreglo) {
            Posicionar(getIndicePrimario()[i].getReferencia());
            Leer();
            cine = new Cine(getNombre(), getCiudad(), getDireccion());
            c[i] = cine;
            i++;
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
//        Posicionar(respuesta);
        PosicionarLugarAdecuadoEliminacion(respuesta);
        setActivo((byte) 0);
        Escribir();
        getCab().setNumeroRegistrosEliminados(getCab().getNumeroRegistrosEliminados() + 1);
        getCab().Posicionar();
        getCab().Escribir();
        EliminarNodo(respuesta);
        getCab().setModificado((byte) 1);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public void PosicionarLugarAdecuadoEliminacion(int NRR_Eliminado) throws IOException {
        setNRR_Eliminado(getCab().getNRR_Eliminado());
        getCab().setNRR_Eliminado(getIndicePrimario()[NRR_Eliminado].getReferencia());
        getCab().Posicionar();
        getCab().Escribir();
        Posicionar(getIndicePrimario()[NRR_Eliminado].getReferencia());
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
        return getCab().getNumeroRegistrosEliminados() * getSize();
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

    public void CompactacionCopia() throws IOException {
        boolean flag = true;
        RandomAccessFile auxIA;
        Cerrar();
        ReadModeIA();
        Cine c = new Cine("Cines", "tmp");
        c.CrearArchivo();
        c.Cerrar();
        c.ReadWriteModeIA();
        auxIA = getIA();
        Posicionar(0);
        c.getCab().setTamañoRegistro(getSize());
        c.Posicionar(0);
        try {
            Leer();
        } catch (EOFException ex) {
            flag = false;
        }
        while (flag) {
            try {
                if (getActivo() == 1) {
                    setIA(c.getIA());
                    Escribir();
                }
                setIA(auxIA);
                Leer();
            } catch (EOFException ex) {
                flag = false;
                JOptionPane.showMessageDialog(null, "Compactacion lista.");
            }
        }

        c.getCab().setNumeroRegistros(getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados());
        c.getCab().setCompactado((byte) 1);
        c.getCab().setIA(c.getIA());
        c.getCab().Posicionar();
        c.getCab().Escribir();
        Cerrar();
        c.Cerrar();
        getFile().delete();
        c.getFile().renameTo(getFile());
        CrearArchivo();
        ReconstruccionIndicePrimario();
        getCab().setModificado((byte) 0);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public void CompactacionInSitu() throws IOException {
        boolean flag = true;
        RandomAccessFile auxIA;
        Cerrar();
        ReadModeIA();
        Cine c = new Cine("Cines", "dat");
        c.CrearArchivo();
        c.Cerrar();
        c.ReadWriteModeIA();
        c.getCab().setIA(c.getIA());
        c.getCab().setTamañoRegistro(getSize());
        Posicionar(0);
        Leer();
        auxIA = c.getIA();
        c.Posicionar(0);
        c.Leer();
        boolean flagIA = true;
        while (flag) {
            try {
                if (getActivo() == 0) {
                    flagIA = false;
                }
                if (flagIA) {
                    c.Leer();
                } else if (getActivo() == 1) {
                    c.setNombre(getNombre());
                    c.setCiudad(getCiudad());
                    c.setDireccion(getDireccion());
                    c.setActivo(getActivo());
                    c.Escribir();
                }
                Leer();
            } catch (EOFException e) {
                flag = false;
                c.Leer();
                c.setActivo((byte) 0);
                c.Escribir();
            }
        }
        c.getCab().setNumeroRegistros(getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados());
        c.getCab().setNumeroRegistrosEliminados(0);
        c.getCab().setCompactado((byte) 1);
        c.getCab().Posicionar();
        c.getCab().Escribir();
        Cerrar();
        c.Cerrar();
        CrearArchivo();
        ReconstruccionIndicePrimario();
        getCab().setModificado((byte) 0);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public int BusquedaBinaria(String nombreCine) throws IOException {
        int pos = -1;
        int a = 0;
        int b = (int) (getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados());
        while (a <= b && pos == -1) {
            int i = Math.abs((a + b) / 2);
            Posicionar(i);
            Leer();
            if (nombreCine.trim().equals(getNombre())) {
                pos = i;
            } else {
                if (nombreCine.compareTo(getNombre()) < 0) {
                    b = i - 1;
                } else {
                    a = i + 1;
                }
            }
        }
        return pos;
    }

    public void OrdenamientoPorInsecion(Cine[] listaCines) {
        for (int i = 1; i < listaCines.length; i++) {
            Cine X = listaCines[i];
            int j = i - 1;
            while (j >= 0 && X.getNombre().compareTo(listaCines[j].getNombre()) < 0) {
                listaCines[j + 1] = listaCines[j];
                j = j - 1;
            }
            listaCines[j + 1] = X;
        }
    }

    public void ClasificacionEnRAM() throws IOException {
        int cantidadRegistros = (int) (getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados());
        Cine[] listaCines = new Cine[cantidadRegistros];
        listaCines = ListadoSecuencial();
        OrdenamientoPorInsecion(listaCines);

        Cine c = new Cine("Cines", "tmp");
        c.CrearArchivo();
        Cabecera cab = c.getCab();
        cab.Posicionar();
        cab.setNumeroRegistros(getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados());
        cab.setNumeroRegistrosEliminados(0);
        cab.setTamañoRegistro(getSize());
        cab.setNRR_Eliminado(-1);
        cab.setCompactado((byte) 1);
        cab.setOrdenado((byte) 1);
        cab.Escribir();
        c.Posicionar(0);

        for (Cine cine : listaCines) {
            cine.setIA(c.getIA());
            cine.Escribir();
        }

        Cerrar();
        c.Cerrar();
        getFile().delete();
        c.getFile().renameTo(getFile());
        CrearArchivo();
        ReconstruccionIndicePrimario();
        getCab().setModificado((byte) 0);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public void OrdenamientoPorInsecionNodo(Nodo[] listanodos) {
        for (int i = 1; i < listanodos.length; i++) {
            Nodo x = listanodos[i];
            int j = i - 1;
            while (j >= 0 && x.getClave().compareTo(listanodos[j].getClave()) < 0) {
                listanodos[j + 1] = listanodos[j];
                j = j - 1;
            }
            listanodos[j + 1] = x;
        }
    }

    public void ClasificacionPorNodos() throws IOException {
        int RegistrosActivos = getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados();
        Cine[] ArregloCines = new Cine[RegistrosActivos];
        Nodo[] ArregloNodos = new Nodo[RegistrosActivos];
        Cine c = new Cine("Cines", "tmp");
        c.CrearArchivo();
        c.getCab().setTamañoRegistro(getSize());
        ArregloCines = ListadoSecuencial();

        for (int i = 0; i < RegistrosActivos; i++) {
            ArregloNodos[i] = new Nodo(ArregloCines[i].getNombre(), i);
        }
        OrdenamientoPorInsecionNodo(ArregloNodos);
        c.Posicionar(0);
        for (int i = 0; i < RegistrosActivos; i++) {
            ArregloCines[ArregloNodos[i].getReferencia()].setIA(c.getIA());
            ArregloCines[ArregloNodos[i].getReferencia()].Escribir();
        }
        c.getCab().setNumeroRegistros(RegistrosActivos);
        c.getCab().setCompactado((byte) 1);
        c.getCab().setOrdenado((byte) 1);
        c.getCab().Posicionar();
        c.getCab().Escribir();
        Cerrar();
        c.Cerrar();
        getFile().delete();
        c.getFile().renameTo(getFile());
        CrearArchivo();
        ReconstruccionIndicePrimario();
        getCab().setModificado((byte) 0);
        getCab().Posicionar();
        getCab().Escribir();
    }

    public void ClasificacionIndirecciones() throws IOException {
        int RegistrosActivos = getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados();
        Cine[] ArregloCines = new Cine[RegistrosActivos];
        String[] ArregloClaves = new String[RegistrosActivos];
        int[] ArregloReferencias = new int[RegistrosActivos];

        Cine c = new Cine("Cines", "tmp");
        c.CrearArchivo();
        c.getCab().setTamañoRegistro(getSize());
        c.getCab().setIA(c.getIA());
        ArregloCines = ListadoSecuencial();

        //Cargar Arreglo de Claves
        for (int i = 0; i < RegistrosActivos; i++) {
            ArregloClaves[i] = ArregloCines[i].getNombre();
        }

        //Cargar Arreglo de Referencias
        for (int i = 0; i < RegistrosActivos; i++) {
            ArregloReferencias[i] = i;
        }

        for (int i = 1; i < RegistrosActivos; i++) {
            String X = ArregloClaves[i];
            int ref = ArregloReferencias[i];
            int j = i - 1;
            while (j >= 0 && X.compareTo(ArregloClaves[j]) < 0) {
                ArregloReferencias[j + 1] = ArregloReferencias[j];
                j = j - 1;
            }
            ArregloReferencias[j + 1] = ref;
        }

        c.Posicionar(0);
        for (int i = 0; i < RegistrosActivos; i++) {
            ArregloCines[ArregloReferencias[i]].setIA(c.getIA());
            ArregloCines[ArregloReferencias[i]].Escribir();
        }
        c.getCab().setNumeroRegistros(RegistrosActivos);
        c.getCab().setCompactado((byte) 1);
        c.getCab().setOrdenado((byte) 1);
        c.getCab().Posicionar();
        c.getCab().Escribir();
        Cerrar();
        c.Cerrar();
        getFile().delete();
        c.getFile().renameTo(getFile());
        CrearArchivo();
        ReconstruccionIndicePrimario();
        getCab().setModificado((byte) 0);
        getCab().Posicionar();
        getCab().Escribir();
    }

    @Override
    public void CargarIndicePrimario() throws IOException {
        int RegistrosActivos = getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados();
        IndicePrimario = new Nodo[RegistrosActivos];
        boolean flag = true;
        int i = 0;
        Nodo nodo = getNodo();
        nodo.Posicionar(0);
        try {
            nodo.Leer();
            while (flag) {
                try {
                    IndicePrimario[i] = new Nodo(nodo.getClave().trim(), nodo.getReferencia());
                    nodo.Leer();
                    Leer();
                } catch (EOFException e) {
                    flag = false;
                }
                i++;
            }
        } catch (EOFException e) {
            flag = false;
        }

        nodo.Cerrar();
    }

    public void ReescrituraIndice() throws FileNotFoundException, IOException {
        getFileIndicePrimario().delete();
        getFileIndicePrimario().createNewFile();
        ReadWriteModeIAIndicePrimario();
        getNodo().Posicionar(0);
        for (Nodo nodo : getIndicePrimario()) {
            getNodo().setClave(nodo.getClave());
            getNodo().setReferencia(nodo.getReferencia());
            getNodo().Escribir();
        }
        getNodo().Cerrar();
        getCab().setModificado((byte) 0);
        getCab().Posicionar();
        getCab().Posicionar();
    }

    public int BusquedaBinariaIndice(String clave) throws IOException {
        int pos = -1;
        int a = 0;
        int b = getIndicePrimario().length - 1;
        while (a <= b && pos == -1) {
            int i = Math.abs((a + b) / 2);
            Nodo nodo = getIndicePrimario()[i];
            if (clave.trim().equals(nodo.getClave())) {
                pos = i;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    b = i - 1;
                } else {
                    a = i + 1;
                }
            }
        }
        return pos;
    }

    public int BusquedaPorIndice(String clave) throws IOException {
        int pos = BusquedaBinariaIndice(clave);
        if (pos != -1) {
            Posicionar(getIndicePrimario()[pos].getReferencia());
            Leer();
        }
        return pos;
    }

    public int PosicionarNuevoNodo(String clave) {
        int pos = -1;
        int a = 0;
        int b = getIndicePrimario().length - 1;
        while (a <= b && pos == -1) {
            int i = Math.abs((a + b) / 2);
            Nodo nodo = getIndicePrimario()[i];
            if (clave.trim().equals(nodo.getClave())) {
                pos = i;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    b = i - 1;
                } else {
                    a = i + 1;
                }
            }
        }
        return a;
    }

    public void ReconstruccionIndicePrimario() throws IOException {
        int RegistrosActivos = getCab().getNumeroRegistros() - getCab().getNumeroRegistrosEliminados();
        boolean flag = true;
        IndicePrimario = new Nodo[RegistrosActivos];
        Posicionar(0);
        int i = 0;
        int j = 0;
        try {
            Leer();
            while (flag) {
                try {
                    if (getActivo() == 1) {
                        IndicePrimario[i] = new Nodo(getNombre(), j);
                        i++;
                    }
                    j++;
                    Leer();
                } catch (EOFException e) {
                    flag = false;
                }
            }
        } catch (EOFException e) {
            flag = false;
        }
        OrdenamientoPorInsecionNodo(IndicePrimario);
        ReescrituraIndice();
    }

    public void InsertarNodo(Nodo nodo) {
        if (getCab().getNRR_Eliminado() == -1) {
            nodo.setReferencia(getCab().getNumeroRegistros());
        } else {
            nodo.setReferencia(getCab().getNRR_Eliminado());
        }

        int pos = PosicionarNuevoNodo(nodo.getClave());
        Nodo[] nuevaListaindicesPrimarios = new Nodo[getIndicePrimario().length + 1];
        // Copiamos los elementos desde el arreglo original hasta la posición donde se insertará el nuevo número
        System.arraycopy(getIndicePrimario(), 0, nuevaListaindicesPrimarios, 0, pos);
        // Insertamos el nuevo número en la posición adecuada
        nuevaListaindicesPrimarios[pos] = nodo;
        // Copiamos los elementos desde la posición de inserción hasta el final del arreglo original
        System.arraycopy(getIndicePrimario(), pos, nuevaListaindicesPrimarios, pos + 1, getIndicePrimario().length - pos);
        setIndicePrimario(nuevaListaindicesPrimarios);
    }

    public void EliminarNodo(int posicion) {
        int pos = posicion;

        // Creamos un nuevo arreglo con una longitud reducida en 1
        Nodo[] nuevoArreglo = new Nodo[getIndicePrimario().length - 1];

        // Copiamos los elementos del arreglo original hasta el índice a eliminar
        System.arraycopy(getIndicePrimario(), 0, nuevoArreglo, 0, pos);

        // Copiamos los elementos desde la posición siguiente al índice a eliminar hasta el final del arreglo original
        System.arraycopy(getIndicePrimario(), pos + 1, nuevoArreglo, pos, getIndicePrimario().length - pos - 1);

        setIndicePrimario(nuevoArreglo);
    }
}
