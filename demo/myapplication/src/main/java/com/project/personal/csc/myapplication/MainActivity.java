package com.project.personal.csc.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

import nativesdk.ad.adsdk.AdSdk;
import nativesdk.ad.adsdk.app.Constants;
import nativesdk.ad.adsdk.common.utils.L;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private final String MARKET_TEST_FB_ID = "1713507248906238_1787756514814644";
    private final String MARKTE_TEST_ADMOB_ID = "ca-app-pub-6857009064962881/7734086652";
    private final String MARKTE_TEST_VK_ID = "61035";
    private final String MARKET_TEST_SOURD_ID = randomSourceId();
    private Map<String, Integer> marketStyle = new HashMap<>();
    private Spinner coutSpinner;
    private ArrayAdapter<CharSequence> countryAdapter;
    private String[] countries = {"AE","AF", "AL", "AM", "AO", "AR", "AT", "AU", "AZ", "BD", "BE", "BF",
            "BG", "BH", "BI", "BJ", "BL", "BN", "BO", "BR", "BW", "BY", "CA", "CF", "CG", "CH", "CL", "CM",
            "CN", "CO", "CR", "CS", "CU", "CY", "CN", "DE", "DK", "DO", "DZ", "EC", "EE", "EG", "ES", "ET",
            "FI", "FJ", "FR", "GB", "GD", "GE", "GH", "GN", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IL",
            "IN", "IQ", "IR", "IS", "IT", "JM", "JO", "JP", "KG", "KH", "KP", "KR", "KT", "KW", "KZ", "LA",
            "LB", "LC", "LI", "LK", "LR", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "MG", "ML", "MM", "MN",
            "MO", "MT", "MU", "MW", "MX", "MY", "MZ", "NA", "NE", "NG", "NI", "NL", "NO", "NP", "NZ", "OM",
            "PA", "PE", "PG", "PH", "PK", "PL", "PT", "PY", "QA", "RO", "RU", "SA", "SC", "SD", "SE", "SG",
            "SI", "SK", "SM", "SN", "SO", "SY", "SZ", "TD", "TG", "TH", "TJ", "TM", "TN","TR", "TW", "TZ",
            "UA", "UG", "US", "UY", "UZ", "VC", "VE", "VN", "YE", "YU", "ZA", "ZM", "ZR", "ZW"};


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
        AdSdk.initialize(this, MARKET_TEST_SOURD_ID); // 也可以在Application 里面初始化
        setMarketStyle();  // 若不设置，采取默认样式


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

        findViewById(R.id.market).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdSdk.enableFacebookAdInMarket(MainActivity.this, MARKET_TEST_FB_ID);   // FB, 不需要不设
                AdSdk.enableAdmobInMarket(MainActivity.this, MARKTE_TEST_ADMOB_ID);     // Admob，不需要不设
                AdSdk.enableVkInMarket(MainActivity.this, MARKTE_TEST_VK_ID);           // VK，不需要不设
                AdSdk.showAppMarket(MainActivity.this);
            }
        });
        findViewById(R.id.market_fragmet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdSdk.enableFacebookAdInMarket(MainActivity.this, MARKET_TEST_FB_ID);
                AdSdk.enableAdmobInMarket(MainActivity.this, MARKTE_TEST_ADMOB_ID);
                AdSdk.setAppMarketFragmentMode(MainActivity.this, true);
                Intent i = new Intent(MainActivity.this, ViewpagerActivity.class);
                startActivity(i);
            }
        });

        // country spinner
        coutSpinner = (Spinner) findViewById(R.id.countries);
        coutSpinner.setVisibility(View.GONE);
        countryAdapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coutSpinner.setAdapter(countryAdapter);
        coutSpinner.setOnItemSelectedListener(this);
        SharedPreferences sp = getSharedPreferences("country", MODE_PRIVATE);
        String ct = sp.getString("country", "US");
        int position = getIndex(countries, ct);
        if (position >= 0) {
            coutSpinner.setSelection(position);
        }
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

    private String randomSourceId() {
        int random = (int) (Math.random() * 100);
        int i = random % 2;
        if (i == 0) {
            return "25173";
        } else if (i == 1) {
            return "18001";
        }
        return "25173";
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences sp = getSharedPreferences("country", MODE_PRIVATE);
        sp.edit().putString("country", countries[position]).apply();
        L.e("set country: " + countries[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private int getIndex(String[] arr, String item) {
        for (int i = 0; i < arr.length; i++) {
            if (countries[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
}
