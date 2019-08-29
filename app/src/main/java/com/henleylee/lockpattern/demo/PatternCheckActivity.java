package com.henleylee.lockpattern.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.henleylee.lockpattern.Cell;
import com.henleylee.lockpattern.OnPatternChangedListener;
import com.henleylee.lockpattern.PatternHelper;
import com.henleylee.lockpattern.PatternIndicatorView;
import com.henleylee.lockpattern.PatternLockerView;

import java.util.List;

/**
 * 创建密码
 */
public class PatternCheckActivity extends AppCompatActivity {

    private static final int MAX_RETRY_COUNT = 4;

    private int retryCount = MAX_RETRY_COUNT;
    private String password;
    private TextView tvMessage;
    private PatternIndicatorView indicatorView;
    private PatternLockerView lockerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_locker);

        password = Utility.getPatternPassword(this);

        tvMessage = findViewById(R.id.pattern_message);
        indicatorView = findViewById(R.id.pattern_indicator_view);
        lockerView = findViewById(R.id.pattern_locker_view);
        lockerView.setOnPatternChangedListener(new OnPatternChangedListener() {
            @Override
            public void onPatternStart() {
                indicatorView.setSelectedCells(null);
            }

            @Override
            public void onPatternChange(PatternLockerView view, List<Cell> cells) {

            }

            @Override
            public void onPatternComplete(PatternLockerView view, List<Cell> cells) {
                handlePatternPassword(view.getSide(), cells);
            }

            @Override
            public void onPatternClear() {

            }
        });
    }

    private void handlePatternPassword(int side, List<Cell> cells) {
        indicatorView.setSelectedCells(cells);
        if (!TextUtils.equals(password, PatternHelper.patternToString(side, cells))) {
            retryCount--;
            tvMessage.setTextColor(Color.RED);
            if (retryCount > 0) {
                tvMessage.setText("密码错误，还可以再输入" + retryCount + "次");
            } else {
                retryCount = MAX_RETRY_COUNT;
                tvMessage.setText("密码错误，请重新输入");
            }
            return;
        }
        Utility.showToast(this, "密码正确");
        finish();
    }

}
