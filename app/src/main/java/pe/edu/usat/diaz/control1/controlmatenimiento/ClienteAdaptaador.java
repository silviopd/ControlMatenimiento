package pe.edu.usat.diaz.control1.controlmatenimiento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.usat.diaz.control1.controlmatenimiento.negocio.*;

/**
 * Created by oscar fernando diaz on 22/09/2016.
 */

public class ClienteAdaptaador extends BaseAdapter {
    public static ArrayList<curso> listaCurso;
    private LayoutInflater layaInfla;

    @Override
    public int getCount() {
        return listaCurso.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCurso.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final holderr holder;
        if (convertView==null){
            convertView=layaInfla.inflate(R.layout.cliente_item,null);
            holder=new holderr();
            holder.lblNombre=(TextView)convertView.findViewById(R.id.lblNombre);
            holder.lblTelefono=(TextView)convertView.findViewById(R.id.lblTelefono);
            holder.imgImagen=(ImageView)convertView.findViewById(R.id.imgImagen);
            convertView.setTag(holder);

        }else{
            holder=(holderr)convertView.getTag();
        }

        curso item=curso.listaC.get(position);
        holder.lblNombre.setText(item.getNombre());
        holder.lblTelefono.setText(item.getProfesor());
        holder.imgImagen.setImageResource(R.drawable.foto);

        return convertView;
    }

    public ClienteAdaptaador(Context contexto, ArrayList<curso> listaa){
        this.layaInfla= LayoutInflater.from(contexto);
        this.listaCurso =listaa;

    }

    private class holderr{
        TextView lblNombre;
        TextView lblTelefono;
        ImageView imgImagen;
    }
}
