package pe.edu.usat.diaz.control1.controlmatenimiento;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.usat.diaz.control1.controlmatenimiento.datos.AccesoDatos;
import pe.edu.usat.diaz.control1.controlmatenimiento.negocio.*;
import pe.edu.usat.diaz.control1.controlmatenimiento.util.Funciones;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,View.OnTouchListener {
Button btnGuardar;
    boolean touch;
    TextView txtNombre,txtProfesor,txthoras,txtcreditos,txtCiclo,txtCodigoCurso;
    Spinner spFacultad,spEscuela;
    RadioButton rdbActivo,rdbInactivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCiclo=(TextView)findViewById(R.id.txtCiclo);
        txtcreditos=(TextView)findViewById(R.id.txtCreditos);
        txtCodigoCurso=(TextView)findViewById(R.id.txtCodigoC);
        txthoras=(TextView)findViewById(R.id.txthoras);
        txtProfesor=(TextView)findViewById(R.id.txtProfesor);
        txtNombre=(TextView)findViewById(R.id.txtNombre);
        rdbActivo=(RadioButton)findViewById(R.id.rdbActivo);
        rdbInactivo=(RadioButton)findViewById(R.id.rdbInactivo);
        spEscuela=(Spinner)findViewById(R.id.spEscuela);
        spFacultad=(Spinner)findViewById(R.id.spFacultad);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);
        spFacultad.setOnItemSelectedListener(this);
        this.touch=true;
        AccesoDatos.aplicacion=this;
        CargarSpinerFacultad();
        Bundle p=this.getIntent().getExtras();
        if (p!=null){
            int pos=p.getInt("pos");
            curso item=curso.listaC.get(pos);
            //llamara al metodo para leer los datos
            this.leerDatos(item.getCodigoC());
        }
    }
    private void leerDatos(String dni){
        Cursor cur=new curso().leerDatos(dni);
        if (cur.moveToNext()){
            txtCodigoCurso.setText(cur.getString(0));
            txtNombre.setText(cur.getString(1));
            txtProfesor.setText(cur.getString(2));
            txthoras.setText(cur.getString(5));
            txtCiclo.setText(cur.getString(6));
            txtcreditos.setText(cur.getString(7));
            String es=cur.getString(8);
            if (es.equalsIgnoreCase("A")){
               rdbActivo.setChecked(true);
            }else{
                rdbInactivo.setChecked(true);
            }
            txtCodigoCurso.setEnabled(false);
            this.cargarSpennerEscuela(cur.getString(9));
            Funciones.selectedItemSpinner(spFacultad,cur.getString(4));
            Funciones.selectedItemSpinner(spEscuela,cur.getString(3));
        }
    }

    private void CargarSpinerFacultad() {
        String listaNombreFacultad[]=new facultad().listaFacultad();
        ArrayAdapter<String> adap=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaNombreFacultad);
        spFacultad.setAdapter(adap);
    }

    private void cargarSpennerEscuela(String cod) {
        String listaNombresEscuela[]=new escuela().listassEscuela(cod);
        ArrayAdapter<String> adap=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaNombresEscuela);
        spEscuela.setAdapter(adap);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnGuardar){
           String codigoCur=this.txtCodigoCurso.getText().toString();
            String nombre=this.txtNombre.getText().toString();
            String ciclo=this.txtCiclo.getText().toString();
            String horas=this.txthoras.getText().toString();
            String creditos=this.txtcreditos.getText().toString();
            String profe=this.txtProfesor.getText().toString();

            if (nombre.isEmpty()){
                Toast.makeText(this,"Ingrese un Nombre Porfavor",Toast.LENGTH_LONG).show();
                txtNombre.requestFocus();
                return;       }
            if (ciclo.isEmpty()){
                Toast.makeText(this,"Ingrese un Telefono Porfavor",Toast.LENGTH_LONG).show();
                txtCiclo.requestFocus();
                return;       }
            if (!(rdbInactivo.isChecked()||rdbActivo.isChecked())){
                Toast.makeText(this,"Ingrese un Telefono Porfavorasdnasndoansdo",Toast.LENGTH_LONG).show();
                rdbActivo.requestFocus();
                return;       }
            String est;
            if (rdbActivo.isChecked()){
                est="A";
            }else{
                est="I";
            }
            String codigoFacul=escuela.listaEscu.get(spEscuela.getSelectedItemPosition()).getCodigoF();
            String codigoEsc=escuela.listaEscu.get(spEscuela.getSelectedItemPosition()).getCodigoE();
            curso objCliente=new curso();
            objCliente.setCodigoC(codigoCur);
            objCliente.setCiclo(Integer.parseInt(ciclo));
            objCliente.setNombre(nombre);
            objCliente.setHoras(Double.parseDouble(horas));
            objCliente.setCiclo(Integer.parseInt(ciclo));
            objCliente.setCreditos(Integer.parseInt(creditos));
            objCliente.setProfesor(profe);
            objCliente.setCodigoF(codigoFacul);
            objCliente.setCodigoE(codigoEsc);
            objCliente.setEstado(est);
            long resultado=-1;
            if (txtCodigoCurso.isEnabled()){
                resultado=objCliente.Agregar();

            }else{
                resultado=objCliente.Editar();
            }
            if (resultado!=-1){
                Toast.makeText(this,"Grabado OK",Toast.LENGTH_LONG).show();
                this.finish();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()){
                case R.id.spFacultad:
                    String codi=facultad.listaFa.get(position).getCodigoF();
                    System.out.println("=======================codigo   "+codi);
                    cargarSpennerEscuela(codi);
                    break;
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
