package com.dipakkr.github.moviesapi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.fragment.HighRatedMovies;
import com.dipakkr.github.moviesapi.fragment.NowPlayingMovies;
import com.dipakkr.github.moviesapi.fragment.PopularMovies;
import com.dipakkr.github.moviesapi.fragment.UpcomingMovies;

/**
 * Created by root on 6/3/17.
 */

public class SimplePagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    public SimplePagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new HighRatedMovies();
        }
        else if(position == 1){
            return new PopularMovies();
        }
        else if(position == 2){
            return new UpcomingMovies();
        }else if(position == 3 )
        {
            return new NowPlayingMovies();
        }
        else {
            return  new HighRatedMovies();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 : return mContext.getResources().getString(R.string.top_movies);

            case 1 : return mContext.getResources().getString(R.string.pop_movies);

            case 2 : return mContext.getResources().getString(R.string.upc_movies);

            case 3 : return mContext.getResources().getString(R.string.now_movies);

        }
        return super.getPageTitle(position);
    }
}
