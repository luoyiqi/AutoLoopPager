package com.loopeer.android.librarys.cyclepager.cyclepager;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.loopeer.android.librarys.autolooppager.AutoLoopLayout;
import com.loopeer.android.librarys.autolooppager.ILoopAdapter;
import com.loopeer.android.librarys.autolooppager.LoopPageChangeListener;
import com.loopeer.android.librarys.autolooppager.PageIndicator;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LoopPageChangeListener {

    private AutoLoopLayout<Integer> mAutoLoopLayout;
    private PageIndicator mPageIndicator;

    private final Integer[] COLOR = new Integer[]{
            Integer.valueOf(android.R.color.holo_blue_light),
            Integer.valueOf(android.R.color.holo_red_light),
            Integer.valueOf(android.R.color.holo_green_light),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAutoLoopLayout = (AutoLoopLayout<Integer>) findViewById(R.id.pager_main);
        mAutoLoopLayout.setILoopImage(new ILoopAdapter<Integer>() {
            @Override
            public View createView(ViewGroup viewGroup) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.view_pager_item, viewGroup, false);
                return layout;
            }

            @Override
            public void bindItem(View view, int position, Integer s) {
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), s));
                ((TextView) view.findViewById(R.id.text_pager_item_title)).setText(String.valueOf(position + 1));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAutoLoopLayout.updateData(Arrays.asList(COLOR));
                    }
                });
            }
        });
        mAutoLoopLayout.setLoopPageChangeListener(this);
        mAutoLoopLayout.updateData(Arrays.asList(COLOR));
        mAutoLoopLayout.startLoop();

        //mPageIndicator = (PageIndicator) findViewById(R.id.indicator_main_pager);
        //mAutoLoopLayout.setPageIndicator(mPageIndicator);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("11111", "---" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}