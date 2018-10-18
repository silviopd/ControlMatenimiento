package pe.edu.usat.diaz.control1.controlmatenimiento.negocio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.edu.usat.diaz.control1.controlmatenimiento.datos.AccesoDatos;

/**
 * Created by oscar fernando diaz on 06/10/2016.
 */

public class curso extends AccesoDatos {
    private String nombre;
    private String profesor;
    private String codigoC;
    private String codigoE;

    public String getCodigoF() {
        return codigoF;
    }

    public void setCodigoF(String codigoF) {
        this.codigoF = codigoF;
    }

    private String codigoF;
    private int ciclo;
    private double horas;
    private int creditos;
    private String estado;
    public static ArrayList<curso> listaC=new ArrayList<curso>();

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getCodigoC() {
        return codigoC;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public long Agregar(){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put("codigoC",this.getCodigoC());
        valores.put("nombre",this.getNombre());
        valores.put("profesor",this.getProfesor());
        valores.put("codigoE",this.getCodigoE());
        valores.put("codigoF",this.getCodigoF());
        valores.put("estado",this.getEstado());
        valores.put("ciclo",this.getCiclo());
        valores.put("creditos",this.getCreditos());
        valores.put("horas",this.getHoras());

        long resultado=db.insert("curso",null,valores);
        return resultado;
    }

    public long Editar(){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues valores=new ContentValues();
        //valores.put("codigoC",this.getCodigoC());
        valores.put("nombre",this.getNombre());
        valores.put("profesor",this.getProfesor());
        valores.put("ciclo",this.getCiclo());
        valores.put("creditos",this.getCreditos());
        valores.put("horas",this.getHoras());
        valores.put("codigoE",this.getCodigoE());
        valores.put("codigoF",this.getCodigoF());
        String condi="codigoC='"+this.getCodigoC()+"'";

        long resultado=db.update("curso",valores,condi,null);
        return resultado;
    }
    public void cargarLista(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor resutado=db.rawQuery("select * from curso order by nombre",null);
        listaC.clear();
        while (resutado.moveToNext()){
            curso obj=new curso();
            obj.setCodigoC(resutado.getString(0));
            obj.setCodigoE(resutado.getString(1));
            obj.setCodigoF(resutado.getString(2));
            obj.setNombre(resutado.getString(3));
            obj.setEstado(resutado.getString(4));
            obj.setHoras(resutado.getDouble(5));
            obj.setProfesor(resutado.getString(6));
            obj.setCreditos(resutado.getInt(7));
            obj.setCiclo(resutado.getInt(8));
            listaC.add(obj);
        }
    }

    public long eliminar(String dni){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("curso","codigoC= '"+dni+"'",null);
    }
    public Cursor leerDatos(String codigoCur){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql =                 "select  " +
                "                c.codigoC,  " +
                "                c.nombre,  " +
                "                c.profesor,  " +
                "                e.nombre,  " +
                "                f.nombre,  " +
                "                c.horas," +
                "                c.ciclo," +
                "                c.creditos," +
                "                c.estado," +
                "                e.codigoE," +
                "                f.codigoF  " +
                "                from curso c  " +
                "                inner join escuela e on c.codigoE=e.codigoE" +
                "                inner join facultad f on c.codigoF=f.codigoF" +
                "                where c.codigoC='"+codigoCur+"'";;
        Cursor  resultado=db.rawQuery(sql,null);
        return resultado;
    }
}
