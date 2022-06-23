package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        mNews = new MutableLiveData<>();


        //TODO remover mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("America é campeão da Copa do Brasil", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo."));
        news.add(new News("Cruzeiro bate recorde mundial de vitorias consecutivas", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga."));
        news.add(new News("URT supera Manchester City no Mundial de Clubes", "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment"));

        this.mNews.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}