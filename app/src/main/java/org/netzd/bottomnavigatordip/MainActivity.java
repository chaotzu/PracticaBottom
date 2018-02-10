package org.netzd.bottomnavigatordip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.netzd.bottomnavigatordip.ListaHistorial.ListaFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainBottomNavigationView = null;
    private ViewPager mainViewPager = null;
    private MusicAdapter musicAdapter = null;

    //Atributo auxiliar saber que elemento fue seleccionado previamente y cual es el actual
    private MenuItem itemSelectedPrev = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Enlazar
        mainBottomNavigationView = (BottomNavigationView) findViewById(R.id.mainBottomNavigator);
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);

        //Generar adaptador basado en fragmentos para view pager



        BottomNavigationHelper.removeShiftMode(mainBottomNavigationView);

        //Configurar view pager
        musicAdapter = new MusicAdapter(getSupportFragmentManager());
        //Agregar fragmentos que manejara
        musicAdapter.addFragment(HomeFragment.newInstance("", ""));
        musicAdapter.addFragment(LibraryFragment.newInstance("", ""));
        musicAdapter.addFragment(ListaFragment.newInstance("", ""));
        //musicAdapter.addFragment(CategoryFragment.newInstance("", ""));
        musicAdapter.addFragment(RecordFragment.newInstance("", ""));

        mainViewPager.setAdapter(musicAdapter);

        //Implementar su interface para cuando hagan clic
        mainBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Implementar cuando esten dando click

                switch (item.getItemId()){
                    case R.id.home_item:
                        mainViewPager.setCurrentItem(0);
                        return true;
                    case R.id.library_item:
                        mainViewPager.setCurrentItem(1);
                        return true;
                    case R.id.category_item:
                        mainViewPager.setCurrentItem(2);
                        return true;
                    case R.id.record_item:
                        mainViewPager.setCurrentItem(3);
                        return true;
                    default:
                        mainViewPager.setCurrentItem(0);
                        return true;
                }
            }
        });
        //Para  que al deslizar la pantalla cambien el menu de abajo
        //Escuchador de eventos cuando se desliza
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(itemSelectedPrev != null){
                    itemSelectedPrev.setChecked(false);
                }else{
                    mainBottomNavigationView.getMenu().getItem(0).setChecked(false);

                }
                mainBottomNavigationView.getMenu().getItem(position).setChecked(true);
                itemSelectedPrev=mainBottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
