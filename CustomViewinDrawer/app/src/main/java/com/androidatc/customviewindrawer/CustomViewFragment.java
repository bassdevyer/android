package com.androidatc.customviewindrawer;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewFragment extends Fragment {

    // The custom view
    private AndroidATCView myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_custom_view, container, false);

        // get reference to the custom view
        myView = (AndroidATCView) rootView.findViewById(R.id.androidATCView1);
        myView.setSquareCol(Color.BLUE);
        myView.setSquareText("Press Me");
        myView.setLabelCol(Color.YELLOW);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setSquareCol(Color.GREEN);
                myView.setLabelCol(Color.MAGENTA);
                myView.setSquareText("Android ATC");
            }
        });
        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
