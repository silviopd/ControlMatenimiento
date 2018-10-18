package pe.edu.usat.diaz.control1.controlmatenimiento.negocio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.edu.usat.diaz.control1.controlmatenimiento.datos.AccesoDatos;

/**
 * Created by oscar fernando diaz on 06/10/2016.
 */

public class facultad extends AccesoDatos {
    public static ArrayList<facultad> listaFa =new ArrayList<facultad>();
    private String codigoF;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoF() {
        return codigoF;
    }

    public void setCodigoF(String codigoF) {
        this.codigoF = codigoF;
    }

    private void cargarDatosDepartamento(){
        SQLiteDatabase bd=this.getReadableDatabase();
        String sql="select * from facultad order by 2";
        Cursor resultado=bd.rawQuery(sql,null);
        listaFa.clear();
        while(resultado.moveToNext()){
            facultad objdep=new facultad();
            objdep.setCodigoF(resultado.getString(0));
            objdep.setNombre(resultado.getString(1));
            listaFa.add(objdep);
        }
    }

    public String[] listaFacultad(){
        cargarDatosDepartamento();

        String listanombresDepartamento[]=new String[listaFa.size()];
        for (int i=0;i<listanombresDepartamento.length;i++){
            facultad item= listaFa.get(i);
            listanombresDepartamento[i]=item.getNombre();
        }
        return listanombresDepartamento;
    }
}
