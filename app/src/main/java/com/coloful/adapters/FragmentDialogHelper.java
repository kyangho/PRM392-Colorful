package com.coloful.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.coloful.R;

public class FragmentDialogHelper extends DialogFragment implements View.OnClickListener {

    String layoutName;
    Button btnCancle;
    Button btnOk;

    EditText editText;

    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static FragmentDialogHelper newInstance(String layoutName) {
        FragmentDialogHelper dialog = new FragmentDialogHelper();
        Bundle args = new Bundle();
        args.putString("idPopUpLayout", layoutName);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutName = getArguments().getString("idPopUpLayout");
        View view = null;
        if (layoutName.equals("username")) {
            view = inflater.inflate(R.layout.popup_forgot_username, container);
        } else if (layoutName.equals("password")) {
            view = inflater.inflate(R.layout.popup_forgot_password, container);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lấy giá trị tự bundle
        if (layoutName.equals("password")) {
            btnOk = (Button) view.findViewById(R.id.btn_fg_pass_ok);
            btnCancle = (Button) view.findViewById(R.id.btn_fg_pass_cancle);
            editText = view.findViewById(R.id.edt_username);
        } else if (layoutName.equals("username")) {
            btnOk = (Button) view.findViewById(R.id.btn_fg_username_ok);
            btnCancle = (Button) view.findViewById(R.id.btn_fg_username_cancle);
            editText = view.findViewById(R.id.edt_email);
        }

        btnCancle.setOnClickListener(this::onClick);
        btnOk.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fg_pass_ok:
                break;
            case R.id.btn_fg_username_ok:
                break;
            default:
                getDialog().dismiss();
                break;
        }
    }
}
