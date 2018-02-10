package org.netzd.bottomnavigatordip;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by Alumno12 on 10/02/18.
 */

public class BottomNavigationHelper {
    @SuppressLint("RestrictedApi")
    public static void removeShiftMode(BottomNavigationView bottomNavigationView){
        //Maneja internamente bottom navigation (es cada pestaña), obtiene de la vista la vista en la posicion cero
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);

        try{
            Field shiftMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftMode.setAccessible(true);
            shiftMode.setBoolean(menuView,false);
            shiftMode.setAccessible(false);
            //Cambia el atributo de cada elemento a false para que no haga la animacion
            for (int indice=0;indice<menuView.getChildCount();indice++){
                BottomNavigationItemView itemView=(BottomNavigationItemView) menuView
                        .getChildAt(indice);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        }catch (NoSuchFieldException e){

        }catch(IllegalAccessException f){

        }
    }
}
