package com.tistory.soodev.mnist_android;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.fingerpaintview.FingerPaintView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.fpv_paint) FingerPaintView mFpvPaint;
    @BindView(R.id.tv_prediction) TextView mTvPrediction;
    @BindView(R.id.tv_probability) TextView mTvProbability;
    @BindView(R.id.tv_timecost) TextView mTvTimeCost;

    private Classifier mClassifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.btn_detect)
    void onDetectClick() {
        if (mClassifier == null) {
            Log.e(LOG_TAG, "onDetectClick(): Classifier is not initialized");
            return;
        } else if (mFpvPaint.isEmpty()) {
            Toast.makeText(this, R.string.please_write_a_digit, Toast.LENGTH_SHORT).show();
            return;
        }
//        canvas.drawColor(Color.WHITE)
        Bitmap image = mFpvPaint.exportToBitmap(
                Classifier.DIM_IMG_SIZE_WIDTH, Classifier.DIM_IMG_SIZE_HEIGHT);
        // The model is trained on images with black background and white font
//        Bitmap inverted = ImageUtil.invert(image);
        Result result = mClassifier.classify(image);
        renderResult(result);
    }

    @OnClick(R.id.btn_clear)
    void onClearClick() {
        mFpvPaint.clear();
        mTvPrediction.setText(R.string.empty);
        mTvProbability.setText(R.string.empty);
        mTvTimeCost.setText(R.string.empty);
    }

    private void init() {
        try {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
//            paint.setTypeface(Typeface.DEFAULT_BOLD);
            mFpvPaint.setPen(paint);
            mFpvPaint.setBackgroundColor(Color.BLACK);
            mClassifier = new Classifier(this);
        } catch (IOException e) {
            Toast.makeText(this, R.string.failed_to_create_classifier, Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "init(): Failed to create tflite model", e);
        }
    }

    private void renderResult(Result result) {
        mTvPrediction.setText(String.valueOf(result.getNumber()));
        mTvProbability.setText(String.valueOf(result.getProbability()));
        mTvTimeCost.setText(String.format(getString(R.string.timecost_value),
                result.getTimeCost()));
    }

}