package com.dipakkr.github.moviesapi.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.adapter.PersonAdapter;
import com.dipakkr.github.moviesapi.model.Celebrity;

import com.dipakkr.github.moviesapi.model.PopularCelebrity;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;
import com.dipakkr.github.moviesapi.utils.RecyclerViewClickListener;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularPersonActivity extends AppCompatActivity {

    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";

    RecyclerView mPersonRecycler;

    List<Celebrity> celebrities;

    ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_person);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPersonRecycler = (RecyclerView) findViewById(R.id.recyler_view_person);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PopularPersonActivity.this, 2);
        mPersonRecycler.setLayoutManager(gridLayoutManager);
        mPersonRecycler.setHasFixedSize(true);
        mPersonRecycler.setClipToPadding(true);
        mPersonRecycler.addItemDecoration(new ItemDecoration(2,dpToPx(3),true));

        fetchDataFromApi();
        handleItemClick();

    }

    public void fetchDataFromApi(){

        Call<PopularCelebrity> popularCelebrityCall = apIinterface.getPopCelebrity(API_KEY);
        popularCelebrityCall.enqueue(new Callback<PopularCelebrity>() {
            @Override
            public void onResponse(Call<PopularCelebrity> call, Response<PopularCelebrity> response) {
                celebrities = response.body().getCelebrities();
                mPersonRecycler.setAdapter(new PersonAdapter(celebrities,PopularPersonActivity.this,R.layout.card_item_person));
            }

            @Override
            public void onFailure(Call<PopularCelebrity> call, Throwable t) {
                String url = apIinterface.getPopCelebrity(API_KEY).request().url().toString();
                Log.v("URL=====>",url);
                mPersonRecycler.setAdapter(new PersonAdapter(celebrities,PopularPersonActivity.this,R.layout.card_item_person));
                Toast.makeText(getApplicationContext(), "FAiled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleItemClick(){
        mPersonRecycler.addOnItemTouchListener(new RecyclerViewClickListener(PopularPersonActivity.this,
                mPersonRecycler, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(PopularPersonActivity.this,CelebrityProfile.class);
                String person_id = celebrities.get(position).getId();
                String person_name = celebrities.get(position).getName();
                intent.putExtra("person_name",person_name);
                intent.putExtra("person_id",person_id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    public static class ItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public ItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }

    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}

