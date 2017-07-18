package com.project.personal.csc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import nativesdk.ad.common.AdSdk;
import nativesdk.ad.common.app.Constants;


public class MainActivity extends ActionBarActivity {

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

//        setMarketStyle();  // 若不设置，采取默认样式

        findViewById(R.id.market).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdSdk.showAppMarket(MainActivity.this);
            }
        });
        findViewById(R.id.market_fragmet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdSdk.setAppMarketFragmentMode(MainActivity.this, true);
                Intent i = new Intent(MainActivity.this, ViewpagerActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.nativeAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NativeAdActivity.class);
                startActivity(i);
            }
        });
    }


    /*
     * Market style settings HashMap<name, resId> (设置为0或者null, 表示用默认配置)
     *
     * TITLE_BACKGROUND_COLOR                   标题栏背景颜色
     * TITLE_TEXT_COLOR                         标题栏字体颜色
     * TITLE_TEXT_SIZE                          标题栏字体大小      (单位：sp)
     * TITLE_BAR_HEIGHT                         标题栏高度          (单位：dp)
     * TITLE_BACK_DRAWABLE                      标题栏返回按钮图片   (默认图片size 20x36 pixel)
     * TABLE_BACKGROUND_COLOR                   Table栏背景颜色
     * TABLE_TEXT_COLOR                         Table栏字体颜色
     * TABLE_BAR_HEIGHT                         Table栏高度         (单位：dp)
     * TABLE_INDICATOR_COLOR                    Table栏指示器颜色
     * DK_BUTTON_BACKGROUND_COLOR               大卡广告按钮背景颜色
     * DK_BUTTON_TEXT_COLOR                     大卡广告按钮字体颜色
     * INSTALL_TEXT_BACKGROUND_DRAWABLE         安装按钮图片          (默认图片size 140x60 pixel)
     * INSTALL_TEXT_COLOR                       安装按钮字体颜色
     * STATUS_COLOR                             状态栏颜色
     * NAVIGATION_COLOR                         导航栏颜色
     * WALL_BACKGROUND_COLOR                    应用墙内容背景颜色
     * AD_TITLE_TEXT_COLOR                      广告标题字体颜色
     * AD_DESCRIPTION_TEXT_COLOR                广告描述字体颜色
     * CATEGORY_TEXT_COLOR                      广告分类标题字体颜色
     * CATEGORY_OF_RECOMMEND                    广告推荐分类标题
     * CATEGORY_OF_POPULAR                      广告流行分类标题
     * CATEGORY_OF_LIKE                         广告喜欢分类标题
     * AD_CLICK_COVER_LAYER_TRANS_BACKGROUND    广告点击时蒙层的颜色
     *
     */
    private void setMarketStyle() {
        marketStyle.put(Constants.MarketStyle.TITLE_BACKGROUND_COLOR, R.color.white);
        marketStyle.put(Constants.MarketStyle.TITLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.TITLE_BAR_HEIGHT, R.dimen.title_bar_height);
        marketStyle.put(Constants.MarketStyle.TITLE_TEXT_SIZE, R.dimen.anative_appwall_title_text_size);
        marketStyle.put(Constants.MarketStyle.TITLE_BACK_DRAWABLE, R.drawable.apx_appwall_adrss_ic_back);
        marketStyle.put(Constants.MarketStyle.TABLE_BACKGROUND_COLOR, R.color.white);
        marketStyle.put(Constants.MarketStyle.TABLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.DK_BUTTON_BACKGROUND_COLOR, R.color.black);
        marketStyle.put(Constants.MarketStyle.DK_BUTTON_TEXT_COLOR, R.color.white);
        marketStyle.put(Constants.MarketStyle.INSTALL_TEXT_BACKGROUND_DRAWABLE, R.drawable.anative_appwall_adress_button_type1);
        marketStyle.put(Constants.MarketStyle.INSTALL_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.STATUS_COLOR, null);
        marketStyle.put(Constants.MarketStyle.NAVIGATION_COLOR, null);
        marketStyle.put(Constants.MarketStyle.WALL_BACKGROUND_COLOR, null);
        marketStyle.put(Constants.MarketStyle.TABLE_BAR_HEIGHT, R.dimen.table_bar_height);
        marketStyle.put(Constants.MarketStyle.TABLE_INDICATOR_COLOR, R.color.light_gray);
        marketStyle.put(Constants.MarketStyle.CATEGORY_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.AD_TITLE_TEXT_COLOR, R.color.gray);
        marketStyle.put(Constants.MarketStyle.AD_DESCRIPTION_TEXT_COLOR, R.color.light_gray);
        marketStyle.put(Constants.MarketStyle.AD_CLICK_COVER_LAYER_TRANS_BACKGROUND, R.color.cover_layer_background);
        marketStyle.put(Constants.MarketStyle.CATEGORY_OF_RECOMMEND, R.string.recommend);
        marketStyle.put(Constants.MarketStyle.CATEGORY_OF_POPULAR, R.string.popular);
        marketStyle.put(Constants.MarketStyle.CATEGORY_OF_LIKE, R.string.like);

        AdSdk.setMarketStyle(this, marketStyle);
//        AdSdk.setMarketStyle(this, null); // restore default style
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
