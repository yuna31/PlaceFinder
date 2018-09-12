package com.place.finder;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class FullBottomSheetDialogFragment extends BottomSheetDialogFragment{

    private BottomSheetBehavior mBehavior;
    private RecyclerView recyclerView;
    private View view;
    private PointAdapter adapter;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        view = View.inflate(getContext(), R.layout.base_recyclerview, null);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());

        adapter = new PointAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.base_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);



        return dialog;
    }

    public void setLocation(double latitude, double longitude) {
        adapter.setPoint(latitude, longitude);
    }

    @Override
    public void onStart() {
        super.onStart();
        view.post(new Runnable() {
            @Override
            public void run() {
                //LinearLayout l = view.findViewById(R.id.station_preview_info);
                mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                //mBehavior.setPeekHeight(100);
            }
        });
    }

    private LatLng getLatLng(String title, double latitude, double longitude) {
        LatLng l = new LatLng(latitude, longitude);

        MarkerOptions m = new MarkerOptions();
        m.position(l);
        m.title(title);

        return l;
    }


}
