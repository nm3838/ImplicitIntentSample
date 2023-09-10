package com.example.implicitintentsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    //緯度フィールド
    private double _latitude = 0;
    //経度フィールド
    private double _longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMapSearchButtonClick(View view) {
        //入力欄に入力されたキーワード文字列を取得
        EditText etSearchWord = findViewById(R.id.etSearchWord);
        String searchWord = etSearchWord.getText().toString();

        try {
            //入力されたキーワードをURLエンコード
            searchWord = URLEncoder.encode(searchWord, "UTF-8");
            //マップアプリと連携するURL文字列を生成
            String uriStr = "geo:0,0?q=" + searchWord;
            //URI文字列からURIオブジェクトを取得
            Uri uri = Uri.parse(uriStr);
            //Intentオブジェクトを取得
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //アクティビティを起動
            startActivity(intent);
        } catch (UnsupportedEncodingException ex) {
            Log.e("MainActivity", "検索キーワード変換失敗", ex);
        }
    }

    public void onMapCurrentButtonClic(View view) {
        //フィールドの緯度と経度を元にマップアプリと連携するURI文字列を生成
        String uriStr = "geo:" + _latitude + "," + _longitude;
        //URI文字列からURIオブジェクトを生成
        Uri uri = Uri.parse(uriStr);
        //Intentオブジェクトを生成
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //アクティビティを起動
        startActivity(intent);
    }
}