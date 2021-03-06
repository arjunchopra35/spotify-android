package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.MenuAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.MenuItemSelected;
import com.example.sergio.spotify_angular.models.MenuAppItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 13/05/2016.
 */
public class MenuFragment extends Fragment implements RecyclerViewBaseAdapter.OnItemClickListener<MenuAppItem> {

    private MenuAdapter adapter;
    private EventBus bus = EventBus.getDefault();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        RecyclerView recyclerList = (RecyclerView) view.findViewById(R.id.menu_explorer_recyclerview);
        recyclerList.setLayoutManager( new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapter =  new MenuAdapter(getActivity(),  new ArrayList<MenuAppItem>());
        adapter.setOnItemClickListener(this);
        recyclerList.setAdapter(adapter);
        return view;
    }


    @Override
    public void onItemClick(MenuAppItem item) {
        bus.post(new MenuItemSelected(item.getId()));
    }

    public void setMenu(List<MenuAppItem> menu) {
        adapter.setData(menu);
    }
}
