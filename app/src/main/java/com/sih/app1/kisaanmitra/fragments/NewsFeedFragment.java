package com.sih.app1.kisaanmitra.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.activities.MainActivity;
import com.sih.app1.kisaanmitra.model.News.NewsResponse;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFeedFragment extends Fragment {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private TextView tvNewsCategory;
    private RelativeLayout rlparent;
    private NewsResponse newsResponse = new NewsResponse();
    private String query = "farmer";

    public NewsFeedFragment() {
        // Required empty public constructor
    }


    public static NewsFeedFragment newInstance() {
        NewsFeedFragment fragment = new NewsFeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        dotsLayout = view.findViewById(R.id.layoutDots);
        tvNewsCategory = view.findViewById(R.id.tvNewsCategory);
        rlparent = view.findViewById(R.id.rlParent);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.news_slide_farmer,
                R.layout.news_slide_agriculture,
                R.layout.news_slide_crop,
                R.layout.news_slide_crop_price};

        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        rlparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked=====", "true");
                apiCall();
            }
        });

        return view;
    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            switch (position){
                case 0:
                    tvNewsCategory.setText("Farmer");
                    rlparent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            query = "farmer";
                        }
                    });
                    break;
                case 1:
                    tvNewsCategory.setText("Agriculture");
                    rlparent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            query = "agriculture";
                        }
                    });
                    break;
                case 2:
                    tvNewsCategory.setText("Crop");
                    rlparent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            query = "crops";
                        }
                    });
                    break;
                case 3:
                    tvNewsCategory.setText("Prices");
                    rlparent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            query = "crop-prices";
                        }
                    });
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void apiCall(){
        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, getContext());
        Call<NewsResponse> call = services.getNews(query);
        Log.e("Api call ====", query);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("Api call ====", query);
                    Log.e("Api call ====", response.toString());
                    if(null != response.body()){
                        if(response.isSuccessful()){
                            NewsFragment newsFragment = NewsFragment.newInstance(response.body());
                            ((MainActivity)getActivity()).createFragment(newsFragment, "Open News", true);
                        }
                    }
                }
                else if (getContext() != null) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiCall();
                }
            });
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
