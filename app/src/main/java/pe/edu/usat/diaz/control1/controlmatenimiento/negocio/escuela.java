package pe.edu.usat.diaz.control1.controlmatenimiento.negocio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.edu.usat.diaz.control1.controlmatenimiento.datos.AccesoDatos;

/**
 * Created by oscar fernando diaz on 06/10/2016.
 */

public class escuela extends AccesoDatos {
    public static ArrayList<escuela> listaEscu =new ArrayList<escuela>();
    private String codigoF;
    private String codigoE;
    private String nombre;

    public String getCodigoF() {
        return codigoF;
    }

    public void setCodigoF(String codigoF) {
        this.codigoF = codigoF;
    }

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void cargarDatosEscuela( String codigoFac){
        SQLiteDatabase bd=this.getReadableDatabase();
        System.out.println("asndiansidnasnjdsa "+codigoFac);
        String sql="select * from escuela where codigoF='"+codigoFac+"' order by 2";
        Cursor resultado=bd.rawQuery(sql,null);
        listaEscu.clear();
        while(resultado.moveToNext()){
            escuela objpro=new escuela();
            objpro.setCodigoE(resultado.getString(0));
            objpro.setCodigoF(resultado.getString(1));
            objpro.setNombre(resultado.getString(2));
            listaEscu.add(objpro);
        }
    }


    public String[] listassEscuela(String cod){
        cargarDatosEscuela(cod);
        String listanombresProvincia[]=new String[listaEscu.size()];
        for (int i=0;i<listanombresProvincia.length;i++){
            escuela item= listaEscu.get(i);
            listanombresProvincia[i]=item.getNombre();

        }
        return listanombresProvincia;
    }

}
