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
public class PatternCreateActivity extends AppCompatActivity {

    private static final int MIN_PASSWORD_LENGTH = 4;

    private String password;
    private TextView tvMessage;
    private PatternIndicatorView indicatorView;
    private PatternLockerView lockerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_locker);

        tvMessage = findViewById(R.id.pattern_message);
        indicatorView = findViewById(R.id.pattern_indicator_view);
        lockerView = findViewById(R.id.pattern_locker_view);
        lockerView.setOnPatternChangedListener(new OnPatternChangedListener() {
            @Override
            public void onPatternStart() {
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
        if (TextUtils.isEmpty(password)) {
            if (cells == null || cells.size() < 4) {
                tvMessage.setTextColor(Color.RED);
                tvMessage.setText("最少连接" + MIN_PASSWORD_LENGTH + "个点，请重新绘制");
                return;
            }
            indicatorView.setSelectedCells(cells);
            password = PatternHelper.patternToString(side, cells);
            tvMessage.setTextColor(Color.DKGRAY);
            tvMessage.setText("再次绘制解锁图案");
        } else {
            if (!TextUtils.equals(password, PatternHelper.patternToString(side, cells))) {
                tvMessage.setTextColor(Color.RED);
                tvMessage.setText("与上一次绘制不一致，请重新绘制");
                return;
            }
            indicatorView.setSelectedCells(cells);
            Utility.savePatternPassword(this, password);
            Utility.showToast(this, "密码设置成功");
            finish();
        }
    }

}
