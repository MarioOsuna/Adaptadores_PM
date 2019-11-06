package com.example.usandoadaptadores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {
    Spinner spinner;
    Spinner spinnerPueblos;
    Spinner spinnerCiudades;
    Spinner spinnerPais;
    String[] titulosCiudades = {"Madrid", "Sevilla", "Toledo"};
    String[] subtitulosCiudades = {"La capital de España", "La capital de Andalucía", "Muy buenas espadas"};
    int[] imagenesCiudades = {R.drawable.madrid, R.drawable.sevilla, R.drawable.toledo};

    String[] titulosPaises = {"ESPAÑA", "UK", "URSS"};
    String[] subtitulosPaises = {"Reino de España", "Reino unido", "Unión de republicas socialistas soviéticas"};
    int[] imagenesPaises = {R.drawable.spain, R.drawable.uk, R.drawable.urss};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*Poner a pantalla completa
    *
    * View vistaventana=getWindow().getDecorView();
    * vistaventana.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    *
    *
    *
    * */
        spinner = findViewById(R.id.spinner);
        spinnerPueblos = findViewById(R.id.spinnerPueblos);
        spinnerCiudades = findViewById(R.id.spinnerCiudades);
        spinnerPais = findViewById(R.id.spinnerPaises);

        String[] aldeas = {"Argallón", "Piconcillo", "Maruana", "Huertos familiares", "El porvenir"};
        String[] pueblos = {"Belmez", "Peñarroya-Pueblonuevo", "Pozoblanco", "Fuenteovejuna", "Espiel"};


        ArrayAdapter<String> adaptador;
        ArrayAdapter<String> adaptador2;
        //  ArrayAdapter<String> adaptadorCiudades;

        adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, aldeas);
        adaptador2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pueblos);
        //adaptadorCiudades = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ciudades);

        AdaptadorParaCiudades adaptadorCiudades = new AdaptadorParaCiudades(this, R.layout.ciudades, titulosCiudades);
        AdaptadorParaPaises adaptadorPaises= new AdaptadorParaPaises(this, R.layout.paises, titulosPaises);

        spinner.setAdapter(adaptador);
        spinnerPueblos.setAdapter(adaptador2);
        spinnerCiudades.setAdapter(adaptadorCiudades);
        spinnerPais.setAdapter(adaptadorPaises);

        spinner.setOnItemSelectedListener(this);
        spinnerPueblos.setOnItemSelectedListener(this);
        spinnerCiudades.setOnItemSelectedListener(this);
        spinnerPais.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (parent.getId() == spinner.getId()) {
            Toast.makeText(this, "Aldea seleccionada: " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        } else {
            if (parent.getId() == spinnerPueblos.getId()) {
                Toast.makeText(this, "Pueblo seleccionado: " + spinnerPueblos.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            } else if(parent.getId() == spinnerCiudades.getId()) {
                Toast.makeText(this, "La ciudad seleccionada es: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "No he seleccionado nada", Toast.LENGTH_SHORT).show();
    }
//--------------------------------------
    private class AdaptadorParaCiudades extends ArrayAdapter<String> {
        public AdaptadorParaCiudades(@NonNull Context context, int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return crearFila(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return crearFila(position, convertView, parent);
    }

    public View crearFila(int posicion, View vista, ViewGroup padre){

            LayoutInflater inflater=getLayoutInflater();
            View mifila=inflater.inflate(R.layout.ciudades,padre,false);


            TextView titulo=mifila.findViewById(R.id.Titulo);
            titulo.setText(titulosCiudades[posicion]);

            TextView subtitulo=mifila.findViewById(R.id.Subtitulo);
            subtitulo.setText(subtitulosCiudades[posicion]);

            ImageView foto=mifila.findViewById(R.id.imageView);
            foto.setImageResource(imagenesCiudades[posicion]);

            return mifila;
        }

    }

    private class AdaptadorParaPaises extends ArrayAdapter<String> {

        public AdaptadorParaPaises(@NonNull Context context, int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila2(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila2(position, convertView, parent);
        }


        public View crearFila2(int posicion, View vista, ViewGroup padre) {
            LayoutInflater inflater = getLayoutInflater();
            View mifila = inflater.inflate(R.layout.paises, padre, false);


            TextView titulo = mifila.findViewById(R.id.tituloPais);
            titulo.setText(titulosPaises[posicion]);

            TextView subtitulo = mifila.findViewById(R.id.subtituloPais);
            subtitulo.setText(subtitulosPaises[posicion]);

            ImageView foto = mifila.findViewById(R.id.imageViewPais);
            foto.setImageResource(imagenesPaises[posicion]);

            return mifila;
        }
    }
}
