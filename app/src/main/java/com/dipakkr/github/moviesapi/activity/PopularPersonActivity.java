package com.dipakkr.github.moviesapi.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.adapter.PersonAdapter;

import java.util.ArrayList;
import java.util.List;

public class PopularPersonActivity extends AppCompatActivity {

    RecyclerView mPersonRecycler;
    Context mContext;

    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_person);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String str = "Deepak Kumar";

        mPersonRecycler = (RecyclerView) findViewById(R.id.recyler_view_person);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PopularPersonActivity.this, 2);
        mPersonRecycler.setLayoutManager(gridLayoutManager);
        mPersonRecycler.setHasFixedSize(true);
        mPersonRecycler.setClipToPadding(true);
        mPersonRecycler.addItemDecoration(new ItemDecoration(2,dpToPx(3),true));
        mPersonRecycler.setAdapter(new PersonAdapter(str,PopularPersonActivity.this,R.layout.card_item_person));

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

