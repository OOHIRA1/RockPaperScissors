package jp.androidbook.rockpaperscissors;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//==ランキング登録画面アクティビティー
public class RegisterRankingActivity extends AppCompatActivity {
    private EditText _editText; //入力された文字テキスト
    private String _url = "http://gameprogram.shop/show_ranking.html";   //ランキング表示サイト
    private Button _browserButton;  //ブラウザ表示ボタン

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ranking);
        _editText = (EditText)findViewById(R.id.editText);
        _browserButton = (Button)findViewById(R.id.BrowserButton);
        _browserButton.setVisibility(View.INVISIBLE);
    }


    //public関数=======================================================
    public void ReturnTitleButtonClicked(View view) {
        Intent intent = new Intent(this, TitleActivity.class);
        startActivity(intent);
    }


    public void RegisterButtonClicked(View view) {
        String userName = _editText.getText().toString();
        int countOfWin = getIntent().getIntExtra("countOfWin", 0);

        //アップロード開始処理-----------------------------------------------------
        if ( userName.length() != 0 ) {
            UploadTask uploadTask = new UploadTask( _url, userName, countOfWin, _browserButton );
            uploadTask.execute(_url);

        }
        //------------------------------------------------------------------------
    }


    public void BrowserButtonClicked(View view) {
        //ランキング表示サイトへアクセス--------------------
        Uri uri = Uri.parse(_url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        //-------------------------------------------------
    }
    //=================================================================
}
