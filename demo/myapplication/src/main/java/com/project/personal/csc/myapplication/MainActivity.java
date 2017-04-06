package com.project.personal.csc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import nativesdk.ad.adsdk.AdSdk;
import nativesdk.ad.adsdk.app.Constants;


public class MainActivity extends ActionBarActivity {

    private final String MARKET_TEST_FB_ID = "1713507248906238_1787756514814644";
    private final String MARKTE_TEST_ADMOB_ID = "ca-app-pub-6857009064962881/7734086652";
    private final String MARKTE_TEST_VK_ID = "61035";
    private Map<String, Integer> marketStyle = new HashMap<>();

    @Override
    protected void onResume() {
        super.onResume();
        preloadMarketWall();
    }

    private void preloadMarketWall() {
        AdSdk.preloadMarketData(this.getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMarketStyle();  // 若不设置，采取默认样式

        findViewById(R.id.market).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AdSdk.enableFacebookAdInMarket(MainActivity.this, MARKET_TEST_FB_ID);   // FB, 不需要不设
//                AdSdk.enableAdmobInMarket(MainActivity.this, MARKTE_TEST_ADMOB_ID);     // Admob，不需要不设
//                AdSdk.enableVkInMarket(MainActivity.this, MARKTE_TEST_VK_ID);           // VK，不需要不设
                AdSdk.showAppMarket(MainActivity.this);
            }
        });
        findViewById(R.id.market_fragmet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AdSdk.enableFacebookAdInMarket(MainActivity.this, MARKET_TEST_FB_ID);
//                AdSdk.enableAdmobInMarket(MainActivity.this, MARKTE_TEST_ADMOB_ID);
                AdSdk.setAppMarketFragmentMode(MainActivity.this, true);
                Intent i = new Intent(MainActivity.this, ViewpagerActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdSdk.enableFacebookIntertitialInNewsFeed(MainActivity.this,"1447264415577075_1488443168125866");
                AdSdk.enableFacebookBannerInNewsFeed(MainActivity.this, "1447264415577075_1488443071459209");
                AdSdk.enableFacebookNativeAdInNewsFeed(MainActivity.this, "1638954679682946_1718740125037734");
                AdSdk.setNewsFeedFragmentMode(MainActivity.this, false);
                AdSdk.enableApxNativeAdInNewsFeed(MainActivity.this,"18000");
                AdSdk.showNewsFeed(MainActivity.this,false);
            }
        });
        findViewById(R.id.news_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdSdk.enableFacebookIntertitialInNewsFeed(MainActivity.this, "1447264415577075_1488443168125866");
                AdSdk.enableFacebookBannerInNewsFeed(MainActivity.this, "1447264415577075_1488443071459209");
                AdSdk.enableFacebookNativeAdInNewsFeed(MainActivity.this, "1638954679682946_1718740125037734");
                AdSdk.setNewsFeedFragmentMode(MainActivity.this, true);
                AdSdk.enableApxNativeAdInNewsFeed(MainActivity.this, "18000");
                AdSdk.showNewsFeed(MainActivity.this,false);
            }
        });
    }

    /*
     * Market style settings HashMap<name, resId> (若不设置或设置为0, 表示用默认配置)
     *
     * TITLE_BACKGROUND_COLOR                   标题背景颜色
     * TITLE_TEXT_COLOR                         标题字体颜色
     * TITLE_BAR_HEIGHT                         标题栏高度
     * TABLE_BACKGROUND_COLOR                   TAB标签背景颜色
     * TABLE_TEXT_COLOR                         TAB标签字体颜色
     * TABLE_BAR_HEIGHT                         TAB标签栏高度
     * TABLE_INDICATOR_COLOR                    TAB标签指示器颜色
     * DK_BUTTON_BACKGROUND_COLOR               大卡按钮背景颜色
     * DK_BUTTON_TEXT_COLOR                     大卡按钮字体颜色
     * INSTALL_TEXT_BACKGROUND_DRAWABLE         安装按钮自定义图片
     * INSTALL_TEXT_COLOR                       安装按钮字体颜色
     * WALL_STATUS_COLOR                        状态栏颜色
     * WALL_NAVIGATION_COLOR                    导航栏颜色
     * BACK_BUTTON_DRAWABLE                     返回按钮自定义图片
     * APPWALL_BACKGROUND_COLOR                 应用墙内容背景颜色
     * SUBTITLE_TEXT_COLOR                      广告分类标题字体颜色
     * AD_TITLE_TEXT_COLOR                      广告标题字体颜色
     * AD_DESCRIPTION_TEXT_COLOR                广告描述字体颜色
     *
     */
    private void setMarketStyle() {
        marketStyle.put(Constants.MarketStyle.TITLE_BACKGROUND_COLOR, R.color.white);
        marketStyle.put(Constants.MarketStyle.TITLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.TABLE_BACKGROUND_COLOR, R.color.white);
        marketStyle.put(Constants.MarketStyle.TABLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.DK_BUTTON_BACKGROUND_COLOR, R.color.white);
        marketStyle.put(Constants.MarketStyle.DK_BUTTON_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.INSTALL_TEXT_BACKGROUND_DRAWABLE, R.drawable.apx_appwall_adress_button_type1);
        marketStyle.put(Constants.MarketStyle.INSTALL_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.BACK_BUTTON_DRAWABLE, R.drawable.apx_appwall_adrss_ic_back);
        marketStyle.put(Constants.MarketStyle.WALL_STATUS_COLOR, 0);
        marketStyle.put(Constants.MarketStyle.WALL_NAVIGATION_COLOR, 0);
        marketStyle.put(Constants.MarketStyle.APPWALL_BACKGROUND_COLOR, 0);
        marketStyle.put(Constants.MarketStyle.TITLE_BAR_HEIGHT, 0);
        marketStyle.put(Constants.MarketStyle.TABLE_BAR_HEIGHT, 0);
        marketStyle.put(Constants.MarketStyle.TABLE_INDICATOR_COLOR, R.color.light_gray);
        marketStyle.put(Constants.MarketStyle.SUBTITLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.AD_TITLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.AD_DESCRIPTION_TEXT_COLOR, R.color.light_gray);

        AdSdk.setMarketStyle(this, marketStyle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
