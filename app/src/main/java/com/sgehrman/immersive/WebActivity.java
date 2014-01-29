package com.sgehrman.immersive;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


public class WebActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      int index = getIntent().getExtras().getInt("index");

      Fragment f = new ImmersiveModeFragment();
      Bundle args = new Bundle();
      args.putInt("index", index);
      f.setArguments(args);

      getFragmentManager().beginTransaction()
          .add(R.id.container, f)
          .commit();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    boolean kitkat = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
    int newUiOptions = 0;

    if (kitkat) {
      newUiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
          View.SYSTEM_UI_FLAG_FULLSCREEN |
          View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
//        View.SYSTEM_UI_FLAG_IMMERSIVE |
//        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |

          0;
    } else {
      newUiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE |

          0;

    }

    getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
  }
}
