package com.star.fragmentbestpractice;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsTitleFragment extends Fragment {

    private ListView newsTitltListView;
    private NewsAdapter newsAdapter;

    private List<News> newsList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        newsAdapter = new NewsAdapter(getActivity(), R.layout.news_item, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_title, container, false);

        newsTitltListView = (ListView) view.findViewById(R.id.news_title_list_view);

        newsTitltListView.setAdapter(newsAdapter);

        newsTitltListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);

                if (getActivity().findViewById(R.id.news_content_layout) != null) {

                    NewsContentFragment newsContentFragment =
                            (NewsContentFragment) getActivity().getSupportFragmentManager().
                                    findFragmentById(R.id.news_content_fragment);

                    newsContentFragment.refresh(news.getTitle(), news.getContent());

                } else {
                    NewsContentActivity.actionStart(getActivity(),
                            news.getTitle(), news.getContent());
                }
            }
        });

        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();

        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar dining hall food. Students with learning disabilities will face these transitions while also grappling with a few more hurdles."); newsList.add(news1);

        News news2 = new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker."); newsList.add(news2);

        return newsList;
    }
}
