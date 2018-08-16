package com.github.pdfviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class PDFView {

    public static abstract class BaseBuilder {

        protected PDFConfig config;

        public BaseBuilder(Context context) {
            this.config = new PDFConfig();
        }
    }

    public static abstract class Builder extends BaseBuilder {

        public Builder(Activity activity) {
            super(activity);
        }

        public Builder(Fragment fragment) {
            super(fragment.getActivity());
        }

        public Builder fromfilepath(String filepath) {
            config.setFilepath(filepath);
            return this;
        }


        public Builder swipeHorizontal(boolean swipeOrientation) {
            config.setSwipeorientation(swipeOrientation ? 0 : 1);
            return this;
        }

//        public Builder fromurl(String url) {
//            config.setNetwork_url(url);
//            return this;
//        }

        public abstract void start();

    }

    static class ActivityBuilder extends Builder {
        private Activity activity;

        public ActivityBuilder(Activity activity) {
            super(activity);
            this.activity = activity;
        }

        @Override
        public void start() {
            Intent intent = new Intent(activity, PDFViewActivity.class);
            intent.putExtra(PDFConfig.EXTRA_CONFIG, config);
            activity.startActivity(intent);
        }
    }

    public static Builder with(Activity activity) {
        return new ActivityBuilder(activity);
    }

}
