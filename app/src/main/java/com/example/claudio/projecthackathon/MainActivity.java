package com.example.claudio.projecthackathon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Claudio on 04/03/2017.
 */

public class MainActivity extends AppCompatActivity {
    private ListView ListaFor;
    private ArrayList<Formulario> formularios = new ArrayList<Formulario>();
    private MyAdapter adapter1;
    private SearchView sear;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList necesario=new ArrayList();
        necesario.add("DNI");
        necesario.add("Cuenta Bancaria");
        Formulario a=new Formulario("Transferencia", necesario, R.drawable.ic_explore_black_24dp);
        formularios.add(a);
        a=new Formulario("Cobrar Cheque", necesario, R.drawable.ic_explore_black_24dp);
        formularios.add(a);
        a=new Formulario("Prestamo", necesario, R.drawable.ic_explore_black_24dp);
        formularios.add(a);
        a=new Formulario("Abrir cuenta", necesario, R.drawable.ic_explore_black_24dp);

        initialize();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint("Buscar");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);



                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        adapter1.getFilter().filter(query);

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter1.getFilter().filter(newText);

                        return false;
                    }
                });
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter1.getFilter().filter(newText);

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void initialize() {
        ListaFor = (ListView)findViewById(R.id.LV);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();




        adapter1 = new MyAdapter(MainActivity.this, formularios);
        ListaFor.setAdapter(adapter1);
    }


    // Adapter Class
    public class MyAdapter extends BaseAdapter implements Filterable {

        private ArrayList<Formulario> ListaOriginal;
        private ArrayList<Formulario> ListaActualizada;
        LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<Formulario> ListaFormularios) {
            this.ListaOriginal = ListaFormularios;
            this.ListaActualizada = ListaFormularios;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return ListaActualizada.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class VistaList {
            LinearLayout LinearContenedor;
            TextView tvName;
            ImageView icono;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            VistaList holder = null;

            if (convertView == null) {

                holder = new VistaList();
                convertView = inflater.inflate(R.layout.guia, null);
                holder.LinearContenedor = (LinearLayout) convertView.findViewById(R.id.Linea);
                holder.tvName = (TextView) convertView.findViewById(R.id.textView);
                holder.icono=(ImageView)convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);

            } else {
                holder = (VistaList) convertView.getTag();
            }
            holder.tvName.setText(ListaActualizada.get(position).getNombre());
            holder.icono.setImageResource(R.drawable.ic_explore_black_24dp);


            return convertView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,Filter.FilterResults results) {

                    ListaActualizada = (ArrayList<Formulario>) results.values;
                    notifyDataSetChanged();
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    ArrayList<Formulario> FilteredArrList = new ArrayList<Formulario>();
                    if (ListaOriginal == null) {
                        ListaOriginal = new ArrayList<Formulario>(ListaActualizada);
                    }
                    if (constraint == null || constraint.length() == 0) {

                        results.count = ListaOriginal.size();
                        results.values = ListaOriginal;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < ListaOriginal.size(); i++) {
                            String data = ListaOriginal.get(i).getNombre();
                            if (data.toLowerCase().startsWith(constraint.toString())) {
                                FilteredArrList.add(new Formulario(ListaOriginal.get(i).getNombre(), ListaOriginal.get(i).getDocumentosNecesarios(), ListaOriginal.get(i).getImg()));
                            }
                        }
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
            return filter;
        }
    }

}