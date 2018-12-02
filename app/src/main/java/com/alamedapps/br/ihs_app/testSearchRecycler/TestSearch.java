package com.alamedapps.br.ihs_app.testSearchRecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.alamedapps.br.ihs_app.R;

import java.util.ArrayList;
import java.util.List;

public class TestSearch extends AppCompatActivity {

    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_search);
        fillExampleList();
        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_access_time_black_24dp, "One", "Ten"));
        exampleList.add(new ExampleItem(R.drawable.color_cursor_white, "Two", "Eleven"));
        exampleList.add(new ExampleItem(R.drawable.color_cursor_white, "Three", "Twelve"));
        exampleList.add(new ExampleItem(R.drawable.color_cursor_white, "Four", "Thirteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_access_time_black_24dp, "Five", "Fourteen"));
        exampleList.add(new ExampleItem(R.drawable.color_cursor_white, "Six", "Fifteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_access_time_black_24dp, "Seven", "Sixteen"));
        exampleList.add(new ExampleItem(R.drawable.color_cursor_white, "Eight", "Seventeen"));
        exampleList.add(new ExampleItem(R.drawable.ic_access_time_black_24dp, "Nine", "Eighteen"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
