package com.github.group2.android_sep4.view.uielements;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.group2.android_sep4.R;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.viewmodel.UserViewModel;

public class DeleteAccountPopup {
    Button buttonConfirm, buttonCancel;
    boolean deleteAccount = false;
    private UserViewModel userViewModel;

    //PopupWindow display method
    public void showPopupWindow(final View view, UserViewModel viewModel) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.delete_account_popup, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        buttonConfirm = popupView.findViewById(R.id.confirmDeleteButton);
        buttonCancel = popupView.findViewById(R.id.cancelDeleteButton);


        buttonConfirm.setOnClickListener(v -> {
            viewModel.deleteUser(viewModel.getCurrentUser().getValue().getId());
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

    public boolean isDeleteAccount() {
        return deleteAccount;
    }
}
