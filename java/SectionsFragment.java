package com.travelbuddy.jerko.travelbuddy;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;


public class SectionsFragment extends Fragment{


    public interface sectionsFragmentListener
    {
        void sendIndex(int index);
    }

    private sectionsFragmentListener mSectionsFragmentListener;

    private GridLayout gridLayout;
    private TabLayout tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sections, container, false);

        gridLayout=view.findViewById(R.id.mainGrid);
        tabs = ((MainActivity)getActivity()).findViewById(R.id.tablayout);

        setSingleEvent(gridLayout);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof sectionsFragmentListener) {
            this.mSectionsFragmentListener = (sectionsFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mSectionsFragmentListener = null;
    }



    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            final LinearLayout linearLayout=(LinearLayout) gridLayout.getChildAt(i);
            final int finalI= i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(finalI)
                    {
                        case 0:
                            tabs.getTabAt(1).select();
                            mSectionsFragmentListener.sendIndex(0);
                            break;
                        case 1:
                            tabs.getTabAt(1).select();
                            mSectionsFragmentListener.sendIndex(1);
                            break;
                        case 2:
                            tabs.getTabAt(1).select();
                            mSectionsFragmentListener.sendIndex(2);
                            break;
                        case 3:
                            tabs.getTabAt(1).select();
                            mSectionsFragmentListener.sendIndex(3);
                            break;
                        case 4:
                            tabs.getTabAt(1).select();
                            mSectionsFragmentListener.sendIndex(4);
                            break;
                        case 5:
                            tabs.getTabAt(1).select();
                            mSectionsFragmentListener.sendIndex(5);
                            break;
                    }
                }
            });
        }
    }

}
