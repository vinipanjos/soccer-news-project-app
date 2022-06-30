package com.example.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernews.ui.MainActivity;
import com.example.soccernews.data.local.AppDatabase;
import com.example.soccernews.databinding.FragmentNewsBinding;
import com.example.soccernews.ui.adapter.NewsAdapter;
import com.google.android.material.snackbar.Snackbar;

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
                    binding.srlNews.setRefreshing(true);
                    break;
                case DONE:
                    binding.srlNews.setRefreshing(false);
                    break;
                case ERROR:
                    binding.srlNews.setRefreshing(false);
                    Snackbar.make(binding.srlNews, "Erro de conexão", Snackbar.LENGTH_SHORT).show();
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