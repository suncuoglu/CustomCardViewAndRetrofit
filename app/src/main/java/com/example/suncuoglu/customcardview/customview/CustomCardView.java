package com.example.suncuoglu.customcardview.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.suncuoglu.customcardview.R;
import com.example.suncuoglu.customcardview.model.User;


public class CustomCardView extends CardView {
    private Context mContext;
    private OnCustomListener onCustomListener;
    private ImageView image;
    private TextView email;
    private TextView name;
    private Button buton;
    private ProgressBar progressBar;

    public CustomCardView(@NonNull Context context) {
        super(context);
        mContext = context;
        init(null);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs);
    }

    public void setOnCustomListener(OnCustomListener onCustomListener) {
        this.onCustomListener = onCustomListener;
    }

    public void init(@Nullable AttributeSet set) {
        LayoutInflater.from(mContext).inflate(R.layout.custom_view, this);
        name = findViewById(R.id.textView);
        email = findViewById(R.id.textView2);
        buton = findViewById(R.id.button);
        image = findViewById(R.id.imageView);
        progressBar = new ProgressBar(mContext);
        addView(progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(GONE);

        buton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onCustomListener.onButtonClicked();
                StartProgress();
            }
        });
    }

    public void setData(String url, User user) {
        Glide.with(mContext)
                .load(url)
                .into(image);

        name.setText(user.getName());
        email.setText(user.getEmail());
        PauseProgress();
        buton.setEnabled(false);
        Toast.makeText(mContext, "Data is set", Toast.LENGTH_SHORT).show();

    }

    public interface OnCustomListener {
        void onButtonClicked();
    }
    public void StartProgress(){
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(VISIBLE);
    }
    public void PauseProgress(){
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminate(false);
    }
}

