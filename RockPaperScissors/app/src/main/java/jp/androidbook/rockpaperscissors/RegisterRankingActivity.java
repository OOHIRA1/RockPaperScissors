package jp.androidbook.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//==ランキング登録画面アクティビティー
public class RegisterRankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ranking);
    }


    //public関数=======================================================
    public void ReturnTitleButtonClicked(View view) {
        Intent intent = new Intent(this, TitleActivity.class);
        startActivity(intent);
    }


    public void RegisterButtonClicked(View view) {

    }
    //=================================================================
}
