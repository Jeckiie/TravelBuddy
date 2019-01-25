package com.travelbuddy.jerko.travelbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ListFragment extends Fragment implements ItemRecyclerAdapter.OnButtonClicked{


    public interface ListFragmentListener{
        void sendItemName(String input);
    }

    ListFragmentListener mListFragmentListener;
    private RecyclerView recview;
    private ItemRecyclerAdapter adapter;
    private ImageView icon;
    private TextView tv;
    private ImageView addNewItemIv;
    private List<String> data = new ArrayList<>();
    private List<String> dataHolder = new ArrayList<>();
    private ArrayList<String> userDocuments = new ArrayList<>();
    private ArrayList<String> userHygiene = new ArrayList<>();
    private ArrayList<String> userSummerClothes = new ArrayList<>();
    private ArrayList<String> userWinterClothes = new ArrayList<>();
    private ArrayList<String> userElectronics = new ArrayList<>();
    private ArrayList<String> userCosmetics = new ArrayList<>();
    private int noOfDefaultItems = 8;
    private View view;
    private int i;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Button dodajBtn;
    private EditText popupEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        icon = view.findViewById(R.id.iconImageView);
        tv = view.findViewById(R.id.listTextView);
        addNewItemIv = view.findViewById(R.id.addImageView);
        recview= view.findViewById(R.id.recViewList);
        recview.setHasFixedSize(false);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ItemRecyclerAdapter(this);
        recview.setAdapter(adapter);
        icon.setImageResource(R.drawable.documents);
        tv.setText(R.string.dokumenti);
        data.clear();
        data = Arrays.asList(getResources().getStringArray(R.array.documents));
        loadItems();
        dataHolder.addAll(data);
        dataHolder.addAll(userDocuments);
        adapter.addData(dataHolder);
        popupWindow();
        return view;
    }

    public void getIndex(int index)
    {
       this.i = index;
       setupRecyclerData(index);
    }

    private void setupRecyclerData(int i){
        dataHolder.clear();
        switch(i)
        {
            case 0:
                icon.setImageResource(R.drawable.documents);
                tv.setText(R.string.dokumenti);
                data = Arrays.asList(getResources().getStringArray(R.array.documents));
                dataHolder.addAll(data);
                dataHolder.addAll(userDocuments);
                break;
            case 1:
                icon.setImageResource(R.drawable.hygiene);
                tv.setText(R.string.higijena);
                data = Arrays.asList(getResources().getStringArray(R.array.hygiene));
                dataHolder.addAll(data);
                dataHolder.addAll(userHygiene);
                break;
            case 2:
                icon.setImageResource(R.drawable.summer_clothes);
                tv.setText(R.string.ljetna_odjeca);
                data = Arrays.asList(getResources().getStringArray(R.array.summer_clothes));
                dataHolder.addAll(data);
                dataHolder.addAll(userSummerClothes);
                break;
            case 3:
                icon.setImageResource(R.drawable.winter_clothes);
                tv.setText(R.string.zimska_odjeca);
                data = Arrays.asList(getResources().getStringArray(R.array.winter_clothes));
                dataHolder.addAll(data);
                dataHolder.addAll(userWinterClothes);
                break;
            case 4:
                icon.setImageResource(R.drawable.electronics);
                tv.setText(R.string.elektronika);
                data = Arrays.asList(getResources().getStringArray(R.array.electronics));
                dataHolder.addAll(data);
                dataHolder.addAll(userElectronics);
                break;
            case 5:
                icon.setImageResource(R.drawable.cosmetics);
                tv.setText(R.string.kozmetika);
                data = Arrays.asList(getResources().getStringArray(R.array.cosmetics));
                dataHolder.addAll(data);
                dataHolder.addAll(userCosmetics);
                break;
            default:
                icon.setImageResource(R.drawable.documents);
                tv.setText(R.string.dokumenti);
                data = Arrays.asList(getResources().getStringArray(R.array.documents));
                dataHolder.addAll(data);
                dataHolder.addAll(userDocuments);
                break;
        }

        adapter.addData(dataHolder);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentListener) {
            this.mListFragmentListener = (ListFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListFragmentListener = null;
    }

    @Override
    public void itemClicked(String text) {
        mListFragmentListener.sendItemName(text);
    }

    @Override
    public void removeItemClicked(int position) {
            switch(i)
            {
                case 0:
                    if(position > noOfDefaultItems-1)
                    {
                        adapter.removeCell(position);
                        dataHolder.remove(position);
                        userDocuments.remove(position-noOfDefaultItems);
                        saveArrayList(userDocuments, "userDocuments");
                    }
                    break;
                case 1:
                    if(position > noOfDefaultItems-1)
                    {
                        adapter.removeCell(position);
                        dataHolder.remove(position);
                        userHygiene.remove(position-noOfDefaultItems);
                        saveArrayList(userHygiene, "userHygiene");
                    }
                    break;
                case 2:
                    if(position > noOfDefaultItems-1)
                    {
                        adapter.removeCell(position);
                        dataHolder.remove(position);
                        userSummerClothes.remove(position-noOfDefaultItems);
                        saveArrayList(userSummerClothes, "userSummerClothes");
                    }
                    break;
                case 3:
                    if(position > noOfDefaultItems-1)
                    {
                        adapter.removeCell(position);
                        dataHolder.remove(position);
                        userWinterClothes.remove(position-noOfDefaultItems);
                        saveArrayList(userWinterClothes, "userWinterClothes");
                    }
                    break;
                case 4:
                    if(position > noOfDefaultItems-1)
                    {
                        adapter.removeCell(position);
                        dataHolder.remove(position);
                        userElectronics.remove(position-noOfDefaultItems);
                        saveArrayList(userElectronics, "userElectronics");
                    }
                    break;
                case 5:
                    if(position > noOfDefaultItems-1)
                    {
                        adapter.removeCell(position);
                        dataHolder.remove(position);
                        userCosmetics.remove(position-noOfDefaultItems);
                        saveArrayList(userCosmetics, "userCosmetics");
                    }
                    break;
                default:
                    break;
            }
    }


    public void popupWindow()
    {
        addNewItemIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(view);
            }
        });
    }

    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        dodajBtn = popupView.findViewById(R.id.dodajBtn);
        popupEt = popupView.findViewById(R.id.popupEt);
        popupEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(20);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        dodajBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(i)
                {
                    case 0:
                        userDocuments.add(popupEt.getText().toString());
                        dataHolder.add(popupEt.getText().toString());
                        adapter.addData(dataHolder);
                        saveArrayList(userDocuments, "userDocuments");
                        popupWindow.dismiss();
                        break;
                    case 1:
                        userHygiene.add(popupEt.getText().toString());
                        dataHolder.add(popupEt.getText().toString());
                        adapter.addData(dataHolder);
                        saveArrayList(userHygiene, "userHygiene");
                        popupWindow.dismiss();
                        break;
                    case 2:
                        userSummerClothes.add(popupEt.getText().toString());
                        dataHolder.add(popupEt.getText().toString());
                        adapter.addData(dataHolder);
                        saveArrayList(userSummerClothes, "userSummerClothes");
                        popupWindow.dismiss();
                        break;
                    case 3:
                        userWinterClothes.add(popupEt.getText().toString());
                        dataHolder.add(popupEt.getText().toString());
                        adapter.addData(dataHolder);
                        saveArrayList(userWinterClothes, "userWinterClothes");
                        popupWindow.dismiss();
                        break;
                    case 4:
                        userElectronics.add(popupEt.getText().toString());
                        dataHolder.add(popupEt.getText().toString());
                        adapter.addData(dataHolder);
                        saveArrayList(userElectronics, "userElectronics");
                        popupWindow.dismiss();
                        break;
                    case 5:
                        userCosmetics.add(popupEt.getText().toString());
                        dataHolder.add(popupEt.getText().toString());
                        adapter.addData(dataHolder);
                        saveArrayList(userCosmetics, "userCosmetics");
                        popupWindow.dismiss();
                        break;
                    default:
                        popupWindow.dismiss();
                        break;
                }
            }
        });

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
           }
        });
    }

    public void saveArrayList(ArrayList<String> list, String key){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void loadItems()
    {
        if(getArrayList("userDocuments") != null) {
            userDocuments.addAll(getArrayList("userDocuments"));
        }
        if(getArrayList("userHygiene") != null) {
            userHygiene.addAll(getArrayList("userHygiene"));
        }
        if(getArrayList("userSummerClothes") != null) {
            userSummerClothes.addAll(getArrayList("userSummerClothes"));
        }
        if(getArrayList("userWinterClothes") != null) {
            userWinterClothes.addAll(getArrayList("userWinterClothes"));
        }
        if(getArrayList("userElectronics") != null) {
            userElectronics.addAll(getArrayList("userElectronics"));
        }
        if(getArrayList("userCosmetics") != null) {
            userCosmetics.addAll(getArrayList("userCosmetics"));
        }
    }

}
