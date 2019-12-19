package com.example.android.desafio_fluxit.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.desafio_fluxit.R;
import com.example.android.desafio_fluxit.controller.UserController;
import com.example.android.desafio_fluxit.model.Results;
import com.example.android.desafio_fluxit.model.User;
import com.example.android.desafio_fluxit.utils.ResultListener;
import com.example.android.desafio_fluxit.view.adapter.RecyclerMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerMainAdapter.RecyclerMainListener {

    @BindView(R.id.mainRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.mainSwipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.mainProgressBar)
    ProgressBar progressBar;
    private RecyclerMainAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private UserController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new RecyclerMainAdapter(this);
        controller = new UserController();
        initRecycler();
        setData();
        setRecyclerData();
        onSwipe();
    }

    private void initRecycler() {
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setData() {
        progressBar.setVisibility(View.VISIBLE);
        controller.getUsersController(new ResultListener<Results>() {
            @Override
            public void onFinish(Results result) {
                if (result != null) {
                    adapter.setUserList(result.getResults());
                    adapter.setNewUserList(result.getResults());
                    Toast.makeText(MainActivity.this, "Exito..!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "¡Fallo!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void setRecyclerData() {
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastViewPosition = linearLayoutManager.findLastVisibleItemPosition();
                int lastRecyclerItem = linearLayoutManager.getItemCount();

                if (lastViewPosition >= lastRecyclerItem - 5) {
                    newUsers();
                }
            }
        });

    }

    private void newUsers() {
        controller.getUsersController(new ResultListener<Results>() {
            @Override
            public void onFinish(Results result) {
                adapter.addUserList(result.getResults());
                if (result != null) {
                    Toast.makeText(MainActivity.this, "Cargados nuevos usuarios..", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error en la busqueda de nuevos usuarios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onSwipe() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.toolbar_search).getActionView();
        menu.findItem(R.id.toolbar_search);
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchView(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                SearchView(query);
                return false;
            }
        });
        return true;
    }

    public void SearchView(String userNameSearch) {

        List<User> search = adapter.getUserList();
        List<User> searchUsers = new ArrayList<>();

        for (User user : search) {
            String username = user.getLogin().getUsername().toLowerCase();
            if (username.contains(userNameSearch)) {
                searchUsers.add(user);
            }
            RecyclerMainAdapter userAdapter = new RecyclerMainAdapter(MainActivity.this);
            userAdapter.setUserList(searchUsers);
            recyclerView.setAdapter(userAdapter);
            recyclerView.setHasFixedSize(true);
        }
    }

    @Override
    public void userSelect(User user) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsActivity.KEY_USERNAME, user);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
