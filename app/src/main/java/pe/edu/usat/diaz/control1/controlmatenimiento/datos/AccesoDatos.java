package pe.edu.usat.diaz.control1.controlmatenimiento.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oscar fernando diaz on 15/09/2016.
 */
public class AccesoDatos extends SQLiteOpenHelper {

    private static String nombreBD="bd_empresa";
    private static int versionBD=1;

    /*referenciar la aplicacion donde se instalará  la BD*/
    public static Context aplicacion;

    private static String tablaFacultad="create table facultad(" +
                                            "codigoF character(2) primary key," +
                                            "nombre varchar(200) )" ;
    private static String tablaEscuela="create table escuela(" +
                                        "codigoE character(2) primary key," +
                                        "codigoF character(2)," +
                                        "nombre varchar(200)," +
                                        "foreign key(codigoF)references facultad(codigoF))";
    private static String tablaCurso="create table curso(" +
                                        "codigoC character(2)," +
                                        "codigoE character(2)," +
                                        "codigoF character(2)," +
                                        "nombre varchar(200) ," +
                                        "estado character(1)," +
                                        "horas numeric(8,2) ," +
                                        "profesor varchar(200) ," +
                                        "creditos integer ," +
                                        "ciclo integer ," +
                                        "foreign key(codigoE)references escuela(codigoE)," +
                                        "foreign key(codigoF)references facultad(codigoF)" +
                                            ")";
    private static String tablaFacultadDatos[]={
            "insert into facultad values('01','F. Ingenieria y Arquitectura')",
            "insert into facultad values('02','F. Medicina')",
                };
    private static String tablaEscuelaDatos[]={
            "insert into escuela values('01','01','Ingenieria de Sistemas')",
            "insert into escuela values('02','01','Ingenieria Mecanica')",
            "insert into escuela values('03','02','Psicologia')",
    };
    private static String tablaCursoDatos[]={
            "insert into curso values('01','01','01','Programacion 1','A',120.4,'Huilder Mera',10,5)",
            "insert into curso values('02','01','01','Dinamica de sistemas','A',120.2,'Aranguri',8,5)",
            "insert into curso values('03','02','02','Psicologia medica','A',100.5,'Diana',8,3)",
    };



    public AccesoDatos(){
        super(aplicacion,nombreBD,null,versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Se ejecuta cuando la aplicacion es instalada y cargada por primera vez*/
        db.execSQL(tablaFacultad);
        db.execSQL(tablaEscuela);
        db.execSQL(tablaCurso);
        for (int i=0;i<tablaFacultadDatos.length;i++){
            db.execSQL(tablaFacultadDatos[i]);
        }

        for (int i=0;i<tablaEscuelaDatos.length;i++){
            db.execSQL(tablaEscuelaDatos[i]);
        }
        for (int i=0;i<tablaCursoDatos.length;i++){
            db.execSQL(tablaCursoDatos[i]);

    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
