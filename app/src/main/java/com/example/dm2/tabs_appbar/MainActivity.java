package com.example.dm2.tabs_appbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Menu menu_main;

    private ItemLlamada[] llamadas =
            new ItemLlamada[]{
                    new ItemLlamada("Juan", "548274264", R.drawable.user, "09-12-2015"),
                    new ItemLlamada("Marta", "256864855", R.drawable.user, "23-07-2015")
            };

    private ItemChat[] chats =
            new ItemChat[]{
                    new ItemChat("Yolanda", "http://www.google.es", R.drawable.user),
                    new ItemChat("Joseba", "http://www.yahoo.es", R.drawable.user),
                    new ItemChat("Borja", "http://www.google.es", R.drawable.user),
                    new ItemChat("Leticia", "http://www.yahoo.es", R.drawable.user)
            };

    private ItemContacto[] contactos =
            new ItemContacto[]{
                    new ItemContacto("Jaime", "http://www.google.es", R.drawable.user),
                    new ItemContacto("David", "http://www.yahoo.es", R.drawable.user),
                    new ItemContacto("Nerea", "http://www.yahoo.es", R.drawable.user)
            };

    AdaptadorItemLlamada adaptador_llamadas;
    AdaptadorItemChat adaptador_chats;
    AdaptadorItemContacto adaptador_contactos;

    ListView lst_llamadas;
    ListView lst_chats;
    ListView lst_contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("llamadas");
        spec.setContent(R.id.llamadas);
        spec.setIndicator("Llamadas",
                ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("chats");
        spec.setContent(R.id.chats);
        spec.setIndicator("Chats",
                ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("contactos");
        spec.setContent(R.id.contactos);
        spec.setIndicator("Contactos",
                ContextCompat.getDrawable(this, android.R.drawable.ic_menu_directions));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //el item que nos interesa es el tercero (el que ocupa la posicion 2)
                MenuItem mi_accion = menu_main.getItem(2);

                switch (tabId) {
                    case "llamadas":
                        mi_accion.setIcon(R.drawable.llamada);
                        break;
                    case "chats":
                        mi_accion.setIcon(R.drawable.chat);
                        break;
                    case "contactos":
                        mi_accion.setIcon(R.drawable.contacto);
                        break;
                }
            }
        });

        adaptador_llamadas = new AdaptadorItemLlamada(this, llamadas);

        adaptador_chats = new AdaptadorItemChat(this, chats);

        adaptador_contactos = new AdaptadorItemContacto(this, contactos);

        lst_llamadas = (ListView)findViewById(R.id.Lst_llamadas);

        lst_chats = (ListView)findViewById(R.id.Lst_chats);

        lst_contactos = (ListView)findViewById(R.id.Lst_contactos);

        lst_llamadas.setAdapter(adaptador_llamadas);
        lst_chats.setAdapter(adaptador_chats);
        lst_contactos.setAdapter(adaptador_contactos);

        lst_llamadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tel = ((TextView) view.findViewById(R.id.telefono)).getText().toString();

                //hay que concatenar un tel: delante para que lo entienda como un numero de telefono
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));

                startActivity(intent);
            }
        });

        lst_chats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String URL_destino = ((TextView) view.findViewById(R.id.enlace)).getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_destino));

                startActivity(intent);
            }
        });

        lst_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String URL_destino = ((TextView) view.findViewById(R.id.enlace)).getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_destino));

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //recogemos el menu main
        menu_main = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AdaptadorItemLlamada extends ArrayAdapter<ItemLlamada> {

        private Context context;

        public AdaptadorItemLlamada(Context context, ItemLlamada[] llamadas) {
            super(context, R.layout.list_itemllamada, llamadas);

            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolderLlamada holder;

            if(item == null){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                item = inflater.inflate(R.layout.list_itemllamada, null);

                holder = new ViewHolderLlamada();

                holder.nombre = (TextView)item.findViewById(R.id.nombre);
                holder.telefono = (TextView)item.findViewById(R.id.telefono);
                holder.foto = (ImageView)item.findViewById(R.id.foto);
                holder.fecha = (TextView)item.findViewById(R.id.fecha);

                item.setTag(holder);
            }else{
                holder = (ViewHolderLlamada)item.getTag();
            }

            //asignando valores al final evitamos que el ViewHolder duplique valores
            holder.nombre.setText(llamadas[position].getNombre());
            holder.telefono.setText(llamadas[position].getTelefono());
            holder.foto.setImageResource(llamadas[position].getFoto());
            holder.fecha.setText(llamadas[position].getFecha());

            return(item);
        }
    }

    class AdaptadorItemChat extends ArrayAdapter<ItemChat> {

        private Context context;

        public AdaptadorItemChat(Context context, ItemChat[] chats) {
            super(context, R.layout.list_itemchat, chats);

            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolderChat holder;

            if(item == null){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                item = inflater.inflate(R.layout.list_itemchat, null);

                holder = new ViewHolderChat();

                holder.titulo = (TextView)item.findViewById(R.id.titulo);
                holder.enlace = (TextView)item.findViewById(R.id.enlace);
                holder.logo = (ImageView)item.findViewById(R.id.logo);


                item.setTag(holder);
            }else{
                holder = (ViewHolderChat)item.getTag();
            }

            holder.titulo.setText(chats[position].getTitulo());
            holder.enlace.setText(chats[position].getEnlace());
            holder.logo.setImageResource(chats[position].getLogo());

            return(item);
        }
    }

    class AdaptadorItemContacto extends ArrayAdapter<ItemContacto> {

        private Context context;

        public AdaptadorItemContacto(Context context, ItemContacto[] contactos) {
            super(context, R.layout.list_itemcontacto, contactos);

            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolderContacto holder;

            if(item == null){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                item = inflater.inflate(R.layout.list_itemcontacto, null);

                holder = new ViewHolderContacto();

                holder.titulo = (TextView)item.findViewById(R.id.titulo);
                holder.enlace = (TextView)item.findViewById(R.id.enlace);
                holder.logo = (ImageView)item.findViewById(R.id.logo);


                item.setTag(holder);
            }else{
                holder = (ViewHolderContacto)item.getTag();
            }

            holder.titulo.setText(contactos[position].getTitulo());
            holder.enlace.setText(contactos[position].getEnlace());
            holder.logo.setImageResource(contactos[position].getLogo());

            return(item);
        }
    }

    static class ViewHolderLlamada{
        TextView nombre;
        TextView telefono;
        ImageView foto;
        TextView fecha;
    }

    static class ViewHolderChat{
        TextView titulo;
        TextView enlace;
        ImageView logo;
    }

    static class ViewHolderContacto{
        TextView titulo;
        TextView enlace;
        ImageView logo;
    }
}
