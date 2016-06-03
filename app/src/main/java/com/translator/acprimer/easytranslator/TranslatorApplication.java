package com.translator.acprimer.easytranslator;

import android.app.Application;
import android.content.Context;

import ww.ee.ff.Wnn;

/**
 * Created by yaodh on 2016/6/2.
 */
public class TranslatorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initAd();
    }

    private void initAd() {
        Wnn pm = Wnn.getInstance(getApplicationContext(), Constants.DYD_AD_APP_ID);
        pm.c(true, true, true, true); //内弹,浮窗,外弹,退弹广告配置
        pm.w(10, 0); //外弹间隔10分钟,首次无延迟
        pm.s(); //激活插屏广告
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ww.ee.ff.Wbb.i(this);
    }
}
