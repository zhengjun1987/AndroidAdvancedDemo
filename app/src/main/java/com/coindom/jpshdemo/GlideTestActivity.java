package com.coindom.jpshdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.socks.library.KLog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideTestActivity extends AppCompatActivity {

    @BindView(R.id.iv_test)
    ImageView ivTest;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        ButterKnife.bind(this);
    }

    private static final String TAG = "GlideTestActivity";

    @Override
    protected void onResume() {
        super.onResume();
//        Glide.with(GlideTestActivity.this)
//                .load("https://pic1.zhimg.com/50/v2-c15b63c8ac55a4d198e77dad6592d18f_hd.jpg")
////                .load(R.mipmap.ic_launcher_round)
//                .thumbnail(0.5f)
//                .override(400,300)
//                .skipMemoryCache(false)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
////                .placeholder(R.mipmap.ic_launcher)
////                .error(R.drawable.sample_footer_loading)
//                .into(ivTest);

        String gifUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561869828919&di=6cd6622fa517ee1978f481b8f967e013&imgtype=0&src=http%3A%2F%2Fimg0.ph.126.net%2Fwgg2Ow6-oIljhMzhLwnmfQ%3D%3D%2F6630685135653058572.jpg";
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                KLog.d(TAG, "resource = [" + resource + "], glideAnimation = [" + glideAnimation + "]");
                if (resource != null) {
                    bitmap = resource;
                    ivTest.setImageBitmap(bitmap);
                }
            }
        };
        Glide.with(GlideTestActivity.this)
                .load(gifUrl)
//                .asGif()

//                .downloadOnly(new SimpleTarget<File>() {
//                    @Override
//                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
//                        KLog.d(TAG, "resource = [" + resource.getAbsolutePath() + "], glideAnimation = [" + glideAnimation + "]");
//
//                    }
//                });

                .asBitmap()
                .override(600,450)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.sample_footer_loading)
//                .preload();

                .into(ivTest);
    }
}
