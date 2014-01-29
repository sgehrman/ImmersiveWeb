package com.sgehrman.immersive;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {
  private MyAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .add(R.id.container, new MainFragment())
          .commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.sample_action) {

    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public class MainFragment extends Fragment {
    public MainFragment() {
      super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);

      GridView gridView = (GridView) rootView.findViewById(R.id.grid);
      mAdapter = new MyAdapter(getActivity(), R.layout.list_item);
      gridView.setAdapter(mAdapter);

      gridView.setOnItemClickListener(mAdapter);


      return rootView;
    }
  }

  private class MyAdapter extends ArrayAdapter<String> implements AdapterView.OnItemClickListener {
    Context mContext;

    public MyAdapter(Context context, int resource) {
      super(context, resource);


      for (int i = 0; i < 20; i++)
        add(Integer.toString(i + 1));
    }

    private void duh() {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long row) {
      Intent intent = new Intent();
      intent.putExtra("index", position + 1);
      intent.setClass(MainActivity.this, WebActivity.class);
      startActivity(intent);

    }

  }
}
