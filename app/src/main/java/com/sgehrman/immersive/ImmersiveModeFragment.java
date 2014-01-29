package com.sgehrman.immersive;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ImmersiveModeFragment extends Fragment {

  public static final String TAG = "ImmersiveModeFragment";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_web, container, false);

    Bundle bundle = getArguments();
    int index = bundle.getInt("index");

    final WebView webView = (WebView) rootView.findViewById(R.id.webview);
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
    webView.setWebViewClient(new MyWebViewClient());

    CookieManager.getInstance().setAcceptCookie(true);
    webView.loadUrl("http://learn.code.org/hoc/" + index);

    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    final View decorView = getActivity().getWindow().getDecorView();

    decorView.setOnSystemUiVisibilityChangeListener(
        new View.OnSystemUiVisibilityChangeListener() {

          @Override
          public void onSystemUiVisibilityChange(int i) {
            int height = decorView.getHeight();
            Log.i(TAG, "Current height: " + height);
          }

        });
  }

  private class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      if (Uri.parse(url).getHost().equals("learn.code.org")) {
        // This is my web site, so do not override; let my WebView load the page
        return false;
      }
      // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
      startActivity(intent);
      return true;
    }
  }

}
