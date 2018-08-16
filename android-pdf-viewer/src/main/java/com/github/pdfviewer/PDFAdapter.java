package com.github.pdfviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PDFAdapter extends PagerAdapter {

    private int page_count;
    private Context context;
    private IShowPage listener;


    public PDFAdapter(Context context, IShowPage listener, int page_count) {
        this.context = context;
        this.listener = listener;
        this.page_count = page_count;
    }

    @Override
    public int getCount() {
        return page_count;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.each_page, container, false);

        PDFZoomImageView imageView = (PDFZoomImageView ) itemView.findViewById(R.id.image);

        imageView.setImageBitmap(listener.showPage(position));

        container.addView(itemView);

        return itemView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
