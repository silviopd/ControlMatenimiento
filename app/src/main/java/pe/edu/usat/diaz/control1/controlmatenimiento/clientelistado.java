package pe.edu.usat.diaz.control1.controlmatenimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.usat.diaz.control1.controlmatenimiento.datos.AccesoDatos;
import pe.edu.usat.diaz.control1.controlmatenimiento.negocio.*;
import pe.edu.usat.diaz.control1.controlmatenimiento.util.Funciones;

public class clientelistado extends AppCompatActivity implements View.OnClickListener {
FloatingActionButton fab;
    ListView lvLista;
    ArrayList<curso> listaCmain;
    ClienteAdaptaador adap;
    int accion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientelistado);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(this);
        AccesoDatos.aplicacion=this;

        lvLista=(ListView)findViewById(R.id.lvLista);
        registerForContextMenu(lvLista);
        listar();
    }
    private void listar(){
        new curso().cargarLista();
        listaCmain=new ArrayList<curso>();
        listaCmain=curso.listaC;
        adap=new ClienteAdaptaador(this,listaCmain);
        lvLista.setAdapter(adap);
    }
    @Override
    public void onClick(View v) {
        this.setAccion(0);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onResume(){
        super.onResume();
        listar();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.lvLista){
            AdapterView.AdapterContextMenuInfo info
                    = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle( curso.listaC.get(info.position).getNombre() );
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for(int i=0; i < menuItems.length; i++){
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        switch (menuItemIndex){
            case 0: //Editar
                /*String dni1=Cliente.listaC.get(info.position).getCodigoC();
                String nombre=Cliente.listaC.get(info.position).getNombre();
                String telefono=Cliente.listaC.get(info.position).getProfesor();
                String dep=Cliente.listaC.get(info.position).getCodigoE();
                String prov=Cliente.listaC.get(info.position).getCiclo();
                String dis=Cliente.listaC.get(info.position).getCodigoDistrito();

                System.out.println("dni "+dni1+"nombre "+nombre+" dep"+dep);

                Bundle parametros=new Bundle();
                parametros.putString("nombre",nombre);
                parametros.putString("telefono",telefono);
                parametros.putString("dni",dni1);
                System.out.println(this.getAccion()+"insdoinasodinasoidnasondosindoasndoisan");
                this.setAccion(1);
                System.out.println(this.getAccion()+"insdoinasodinasoidnasondosindoasndoisan");

                Intent pantallaSitio=new Intent(this,MainActivity.class);
                pantallaSitio.putExtras(parametros);
                startActivity(pantallaSitio);*/
                Intent i=new Intent(this,MainActivity.class);
                Bundle parametros=new Bundle();
                parametros.putInt("pos",info.position);
                i.putExtras(parametros);
                startActivity(i);

                break;
            case 1: //Eliminar
                boolean r = Funciones.mensajeConfirmacion(this, "Confirme", "Desea eliminar");
                if (r == false){
                    return false;
                }
                String dni = curso.listaC.get(info.position).getCodigoC();
                long resultado = new curso().eliminar(dni);
                if (resultado > 0){
                    Toast.makeText(this, "Registro eliminado", Toast.LENGTH_LONG).show();
                    listar();
                }
                break;

        }
        return true;
    }
}
