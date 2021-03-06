package com.project.personal.csc.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import nativesdk.ad.rw.IRewardedVideoAd;
import nativesdk.ad.rw.RewardedVideoAd;
import nativesdk.ad.rw.RewardedVideoAdListener;
import nativesdk.ad.rw.mediation.RewardItem;

/**
 * Created by hongwu on 5/16/17.
 */

public class RewardVideoActivity extends Activity implements View.OnClickListener {

    private static final String unitId = "3fih8h8ihgfedc1"; // avazu
    private IRewardedVideoAd rewardedVideoAd;
    private Button load, show;
    private static final String TAG = "RewardVideoActivity: ";
    private RelativeLayout progressbar;
    private boolean mIsInited = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_video);

        progressbar = (RelativeLayout) findViewById(R.id.reward_pb);
        load = (Button) findViewById(R.id.load);
        load.setOnClickListener(this);
        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(this);

        load.setEnabled(false);
        show.setEnabled(false);

        initReward();
    }

    private void initReward() {
        rewardedVideoAd = new RewardedVideoAd(this, unitId, new RewardedVideoAdListener() {
            @Override
            public void onInitSuccess() { // load ad after init success
                Toast.makeText(RewardVideoActivity.this, "init success! please load", Toast.LENGTH_SHORT).show();
                mIsInited = true;
                load.setEnabled(true);
                show.setEnabled(true);
            }

            @Override
            public void onInitFailed() {
                Toast.makeText(RewardVideoActivity.this, "init failed!", Toast.LENGTH_SHORT).show();
                mIsInited = false;
            }

            @Override
            public void onRewardedVideoAdLoaded() { // show ad after ad loaded
                Log.e(TAG, "onRewardedVideoAdLoaded");
                hideProgressbar();
                Toast.makeText(RewardVideoActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Log.e(TAG, "onRewardedVideoAdOpened");
            }

            @Override
            public void onRewardedVideoStarted() {
                Log.e(TAG, "onRewardedVideoStarted");
            }

            @Override
            public void onRewardedVideoAdClosed() {
                Log.e(TAG, "onRewardedVideoAdClosed");
            }

            @Override
            public void onRewarded(RewardItem var1) {
                Log.e(TAG, "onRewarded: rewardType: " + var1.getType() + ", amount: " + var1.getAmount());
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Log.e(TAG, "onRewardedVideoAdLeftApplication");
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int var1) {
                Log.e(TAG, "onRewardedVideoAdFailedToLoad");
                hideProgressbar();
                Toast.makeText(RewardVideoActivity.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        rewardedVideoAd.pause(this);
        super.onPause();
    }

    private void loadAd() {
        if (mIsInited) {
            showProgressbar();
            rewardedVideoAd.loadAd();
        }
    }

    private void show() {
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load:
                loadAd();
                break;
            case R.id.show:
                show();
                break;
        }
    }

    private void showProgressbar() {
        if (!progressbar.isShown()) {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressbar() {
        if (progressbar.isShown()) {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }
}
