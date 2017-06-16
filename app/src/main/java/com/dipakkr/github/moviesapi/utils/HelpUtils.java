package com.dipakkr.github.moviesapi.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.dipakkr.github.moviesapi.MainActivity;
import com.dipakkr.github.moviesapi.R;


/**
 * Created by root on 6/16/17.
 */

public class HelpUtils {

    public static void showAboutus(AppCompatActivity activity){
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = fm.findFragmentByTag("about_dialog");

        if(fragment !=null){
            ft.remove(fragment);
        }
        ft.addToBackStack(null);

        new AboutUsDialog().show(ft,"about_dialog");
    }

    public static class AboutUsDialog extends DialogFragment{

        public AboutUsDialog(){
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LinearLayout ll = (LinearLayout)layoutInflater.inflate(R.layout.aboutus_dialog,null);
            return new AlertDialog.Builder(getActivity()).setView(ll)
                    .create();
        }
    }
}
