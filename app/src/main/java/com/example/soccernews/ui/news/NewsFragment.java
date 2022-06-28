package com.example.soccernews.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.soccernews.MainActivity;
import com.example.soccernews.data.local.AppDatabase;
import com.example.soccernews.databinding.FragmentNewsBinding;
import com.example.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        findUpdatedNews(newsViewModel);

        getState(newsViewModel);

        return binding.getRoot();
    }

    private void getState(NewsViewModel newsViewModel) {
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    //TODO iniciar SwipeRefreshLayout (carregando)
                    break;
                case DONE:
                    //TODO finalizar SwipeRefreshLayout
                    break;
                case ERROR:
                    //TODO finalizar SwipeRefreshLayout e mostrar um  genérico
                    break;
            }
        });
    }

    private void findUpdatedNews(NewsViewModel newsViewModel) {

        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> binding.rvNews.setAdapter(new NewsAdapter(news, requireContext(), updatedNews -> {
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.getDb().newsDao().save(updatedNews);
            }
        })));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}