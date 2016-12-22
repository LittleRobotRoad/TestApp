package lol.niconico.dev.ui.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lol.niconico.dev.R;

/**
 * Created by ZhangQianqian on 2016/11/9  23:05.
 * email 415692689@qq.com
 */

public class ChoosePicDialog extends DialogFragment implements View.OnClickListener {

    private ChoosePicListener l;

    public static ChoosePicDialog newInstance() {
        Bundle args = new Bundle();
        ChoosePicDialog fragment = new ChoosePicDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        //dialog 占满屏幕
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.AnimBottom); //设置窗口弹出动画

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View layout = inflater.inflate(R.layout.dialog_choose_pic, container, false);
        RelativeLayout rlBackground = (RelativeLayout) layout.findViewById(R.id.rlBackground);
        RelativeLayout llShareBg = (RelativeLayout) layout.findViewById(R.id.llShareBg);
        TextView tvChoosePic = (TextView) layout.findViewById(R.id.tvChoosePic);
        TextView tvTakePhoto = (TextView) layout.findViewById(R.id.tvTakePhoto);

        tvTakePhoto.setOnClickListener(this);
        tvChoosePic.setOnClickListener(this);
        rlBackground.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvTakePhoto) {
            l.takePhoto();

        } else if (i == R.id.tvChoosePic) {
            l.ChoosePic();

        } else if (i == R.id.rlBackground) {
        }
        dismiss();
    }

    public ChoosePicDialog setChoosePicListener(ChoosePicListener l) {
        this.l = l;
        return this;
    }

    public interface ChoosePicListener {
        void takePhoto();

        void ChoosePic();
    }
}
