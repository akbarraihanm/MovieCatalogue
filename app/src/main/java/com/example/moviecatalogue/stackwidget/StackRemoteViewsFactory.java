package com.example.moviecatalogue.stackwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.favorites.db2.FavMovieHelper;
import com.example.moviecatalogue.model.FavoriteMovie;

import java.sql.SQLException;
import java.util.ArrayList;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<Bitmap> bitmapStackWidget = new ArrayList<>();
    private final Context context;
    private FavMovieHelper dbHan;

    public StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        dbHan = new FavMovieHelper(context);
        try {
            dbHan.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<FavoriteMovie> stackWidgetItems = dbHan.query();

        int count = stackWidgetItems.size();

        dbHan.close();

        for (int i=0; i<count; i++){

            Glide.with(context)
                    .asBitmap()
                    .load(stackWidgetItems.get(i).getLink_poster()+stackWidgetItems.get(i).getPoster_path())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            bitmapStackWidget.add(resource);
                        }
                    });
        }

    }

    private ArrayList<Bitmap> addArrayList(Context context) {
        final ArrayList<Bitmap> asdf= new ArrayList<>();
        try {
            dbHan.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final ArrayList<FavoriteMovie> itemFavorite = dbHan.query();

        int count = itemFavorite.size();

        dbHan.close();

        for(int i=0; i<count; i++){
            asdf.add(LoadBitmap.INSTANCE.loadBit(context, itemFavorite.get(i).getLink_poster()+itemFavorite.get(i).getPoster_path()));
        }

        return asdf;
    }

    @Override
    public void onDataSetChanged() {

        bitmapStackWidget.clear();
        bitmapStackWidget.addAll(addArrayList(context));

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return bitmapStackWidget.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.iv_widget, bitmapStackWidget.get(i));

        Bundle extras = new Bundle();
        extras.putInt(StackWidget.EXTRA_ITEM, i);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(fillIntent);

        rv.setOnClickFillInIntent(R.id.iv_widget, fillIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
