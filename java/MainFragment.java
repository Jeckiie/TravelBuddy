package com.travelbuddy.jerko.travelbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class MainFragment extends Fragment implements MyListRecyclerAdapter.RemoveItemListener, MyListRecyclerAdapter.ItemCheckListener {

    RecyclerView recview;
    MyListRecyclerAdapter adapter;
    List<MyListObject> data = new ArrayList<>();
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Boolean> itemCheck = new ArrayList<>();
    int noOfItems = 0;
    boolean executed = false;
    private long mLastClickTime;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    public void removeCellItem(int position) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 200){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if(data.size() > position)
        {
            adapter.removeCell(position);
            data.remove(position);
            itemNames.remove(position);
            itemCheck.remove(position);
            noOfItems--;
            saveArrayList(itemNames, "itemNames", noOfItems);
            saveBooleanList(itemCheck, "itemCheck", noOfItems);
            saveNoOfItems(noOfItems, "noOfItems");
        }
    }


     private void loadItems()
    {
        noOfItems = getNoOfItems("noOfItems");
        if(getArrayList("itemNames", noOfItems) != null) {
            itemNames.addAll(getArrayList("itemNames", noOfItems));
        }
        if(getBooleanList("itemCheck", noOfItems) != null) {
            itemCheck.addAll(getBooleanList("itemCheck", noOfItems));
        }
        for(int i = 0; i < noOfItems; i++)
        {
            data.add(i, new MyListObject(itemNames.get(i), itemCheck.get(i)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recview= view.findViewById(R.id.myListRecyclerView);
        recview.setHasFixedSize(false);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyListRecyclerAdapter(this, this);
        recview.setAdapter(adapter);
        if(!executed)
        {
            loadItems();
            executed = true;
        }
        adapter.addData(data);
        return view;
    }

    public void addData(String newText){
        if (SystemClock.elapsedRealtime() - mLastClickTime < 200){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        data.add(new MyListObject(newText, false));
        adapter.addData(data);
        itemNames.add(newText);
        itemCheck.add(false);
        noOfItems++;
        saveArrayList(itemNames, "itemNames", noOfItems);
        saveBooleanList(itemCheck, "itemCheck", noOfItems);
        saveNoOfItems(noOfItems, "noOfItems");
    }


    public void saveArrayList(ArrayList<String> list, String key, int i){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        for(int j = 0; j < i; j++)
        {
            editor.putString(key+j, list.get(j));
        }
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key, int i){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ArrayList<String> list = new ArrayList<>();
        for(int j = 0; j < i; j++)
        {
            list.add(j, prefs.getString(key+j, "-"));
        }
        return list;
    }

    public void saveBooleanList(ArrayList<Boolean> bool, String key, int i){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        for(int j = 0; j < i; j++)
        {
            editor.putBoolean(key+j, bool.get(j));
        }
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<Boolean> getBooleanList(String key, int i){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ArrayList<Boolean> list = new ArrayList<>();
        for(int j = 0; j < i; j++)
        {
            list.add(j, prefs.getBoolean(key+j, false));
        }
        return list;
    }

    public void saveNoOfItems(int noOfItems, String key){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        editor.putInt(key, noOfItems);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public int getNoOfItems(String key)
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return prefs.getInt(key, 0);
    }




    @Override
    public void itemCheckPosition(int position, Boolean bool) {
        itemCheck.set(position, bool);
        saveBooleanList(itemCheck, "itemCheck", noOfItems);
    }
}