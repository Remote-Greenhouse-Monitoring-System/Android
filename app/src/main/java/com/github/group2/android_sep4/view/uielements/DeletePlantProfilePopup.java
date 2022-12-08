package com.github.group2.android_sep4.view.uielements;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;

public class DeletePlantProfilePopup {


    Button buttonConfirm, buttonCancel;
    private PlantProfileViewModel plantProfileViewModel = new PlantProfileViewModel();

    //PopupWindow display method
    public void showPopupWindow(final View view,long id) {
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_delete_plant_profile, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        buttonConfirm = (Button) popupView.findViewById(R.id.confirmDeleteButtonPlantProfile);
        buttonCancel = (Button) popupView.findViewById(R.id.cancelDeleteButton);


        buttonConfirm.setOnClickListener(v -> {
            Toast.makeText(view.getContext(), "Deleted the plant profile", Toast.LENGTH_SHORT).show();
            plantProfileViewModel.deletePlantProfile(id);
            popupWindow.dismiss();
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }


}
