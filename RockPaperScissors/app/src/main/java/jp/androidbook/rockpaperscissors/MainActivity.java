package jp.androidbook.rockpaperscissors;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

//==メインシーンのアクティビティー
public class MainActivity extends AppCompatActivity {
    /*public enum KindOfJannkenn {
        ROCK,
        SCISSORS,
        PAPER,
    }*/
    final int ROCK = 0;
    final int SCISSORS = 1;
    final int PAPER = 2;
    final int COUNT_OF_RPS = 3;    //じゃんけんの種類数(RPSはRockPaperScissorsの略)
    public static final int NEXT = 0;
    public static final int BACK = 1;
    public static final int RESULT_BUTTON_COUNT = 2;  //勝敗結果ボタン配列の要素数
    final int WIN = 0;
    final int LOSE = 1;
    final int DORW = 2;
    //UI関連===========================================================================
    private ImageButton[] _RPSbuttons;   //じゃんけんボタン配列
    private TextView _textView;       //画面上部のテキスト
    private ViewGroup viewGroup;
    private ResultSurfaceView _resultSurfaceView;   //じゃんけん結果表示画面View
    private TextView _resultTextView;               //勝敗を表示するテキストView
    private TextView _countOfWinTextView;           //連勝回数を表示するテキストView
    private Button[] _resultButtons;                  //勝敗結果画面で表示するボタン配列
    private Button _rankingButton;                 //ランキング登録ボタン
    //=================================================================================
    private int _countOfWin;        //勝利回数
    private int _result;            //じゃんけんの勝敗(0:勝ち, 1:負け, 2:引き分け)
    private String _url = "http://gameprogram.shop/insert_table_form.html";   //登録フォームサイト



    //オーバーライド================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _RPSbuttons = new ImageButton[COUNT_OF_RPS];   //配列のメモリ確保
        _RPSbuttons[ROCK] = (ImageButton) this.findViewById(R.id.RockButton);
        _RPSbuttons[SCISSORS] = (ImageButton)this.findViewById(R.id.ScissorsButton);
        _RPSbuttons[PAPER] = (ImageButton)this.findViewById(R.id.PaperButton);
        _textView = (TextView) this.findViewById(R.id.RPSTextView);
        viewGroup = (ViewGroup) _textView.getParent();
        _countOfWin = 0;    //勝利回数を0にする
        _resultTextView = (TextView)this.findViewById(R.id.ResultTextView);
        _resultTextView.setText("");
        _countOfWinTextView = (TextView)this.findViewById(R.id.CountOfWinTextView);
        _countOfWinTextView.setText("");
        _resultButtons = new Button[RESULT_BUTTON_COUNT];   //配列のメモリ確保
        _resultButtons[NEXT] = (Button)this.findViewById(R.id.Nextbutton);
        _resultButtons[NEXT].setVisibility(View.INVISIBLE);
        _resultButtons[BACK] = (Button)this.findViewById(R.id.Backbutton);
        _resultButtons[BACK].setVisibility(View.INVISIBLE);
        _result = -1;   //初期値は-1
        _rankingButton = (Button)findViewById(R.id.RankingButton);
        _rankingButton.setVisibility(View.INVISIBLE);
    }
    //=============================================================

    //--じゃんけんボタンをクリックした時の共通処理(RPSはRockPaperScissorsの略)(返り値：CPUの出したじゃんけんのIndex番号)
    private int RPSButtonClicked() {
        _textView.setText("ポン！！");
        TextView textViewYou = (TextView)this.findViewById(R.id.TextViewYou);
        textViewYou.setText("YOU");
        ImageView cpuHand  = (ImageView)this.findViewById(R.id.CPUHandImageView);
        //CPUのじゃんけん処理-------------------------------------------------------------------------------
        Random random = new Random();
        int cpuRPSIndex = random.nextInt(COUNT_OF_RPS);   //CPUが出したじゃんけんのIndex番号
        switch(cpuRPSIndex) {
            case ROCK://0
                cpuHand.setImageResource(R.drawable.rock_small);
                break;
            case SCISSORS://1
                cpuHand.setImageResource(R.drawable.scissor_small);
                break;
            case PAPER://2
                cpuHand.setImageResource(R.drawable.paper_small);
                break;
            default:
                break;
        }
        //-------------------------------------------------------------------------------------------------
        TextView textViewCPU = (TextView)this.findViewById(R.id.TextViewCPU);
        textViewCPU.setText("CPU");

        return cpuRPSIndex;
    }


    //--勝敗を判定する関数(0:勝ち, 1:負け, 2:引き分け)
    private int Judge( int playerRPSIndex, int cpuRPSIndex ) {
        int returnVal;
        if ( playerRPSIndex == ((cpuRPSIndex + 2) % 3) ) {
            returnVal = WIN;//勝利
            _countOfWin++;
        } else if ( playerRPSIndex == ((cpuRPSIndex + 1) % 3) ) {
            returnVal = LOSE;//敗北
        } else {
            returnVal = DORW;//引き分け
        }
        return returnVal;
    }

    //public関数===========================================================================
    public void RockButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageResource(R.drawable.rock_small);
        int cpuRPSIndex = RPSButtonClicked();
        for(int i = 0; i < _RPSbuttons.length; i++) {
            //viewGroup.removeView(_RPSbuttons[i]);
            _RPSbuttons[i].setVisibility(View.INVISIBLE);
        }
        _result = Judge(ROCK, cpuRPSIndex);
        _resultSurfaceView = new ResultSurfaceView( this, _result, _resultTextView, _countOfWinTextView, _countOfWin, _resultButtons, _rankingButton);
        _resultSurfaceView.setBackgroundColor(Color.WHITE);
        viewGroup.addView(_resultSurfaceView, 0);
    }


    public void ScissorsButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageResource(R.drawable.scissor_small);
        int cpuRPSIndex = RPSButtonClicked();
        for(int i = 0; i < _RPSbuttons.length; i++) {
            _RPSbuttons[i].setVisibility(View.INVISIBLE);
        }
        _result = Judge(SCISSORS, cpuRPSIndex);
        _resultSurfaceView = new ResultSurfaceView( this.getApplicationContext(), _result, _resultTextView, _countOfWinTextView, _countOfWin, _resultButtons, _rankingButton);
        _resultSurfaceView.setBackgroundColor(Color.WHITE);
        viewGroup.addView(_resultSurfaceView, 0);
    }


    public void PaperButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageResource(R.drawable.paper_small);
        int cpuRPSIndex = RPSButtonClicked();
        for(int i = 0; i < _RPSbuttons.length; i++) {
            _RPSbuttons[i].setVisibility(View.INVISIBLE);
        }
        _result = Judge(PAPER, cpuRPSIndex);
        _resultSurfaceView = new ResultSurfaceView( this.getApplicationContext(), _result, _resultTextView, _countOfWinTextView, _countOfWin, _resultButtons, _rankingButton);
        _resultSurfaceView.setBackgroundColor(Color.WHITE);
        viewGroup.addView(_resultSurfaceView, 0);
    }


    public void NextButtonClicked(View view) {
        for (int i = 0; i < _resultButtons.length; i++) {
            _resultButtons[i].setVisibility(View.INVISIBLE);
        }
        _resultTextView.setText("");
        _countOfWinTextView.setText("");
        _textView.setText("じゃんけん…");
        TextView textViewYou = (TextView)this.findViewById(R.id.TextViewYou);
        textViewYou.setText("");
        TextView textViewCPU = (TextView)this.findViewById(R.id.TextViewCPU);
        textViewCPU.setText("");
        for (int i = 0; i < _RPSbuttons.length; i++) {
            _RPSbuttons[i].setVisibility(View.VISIBLE);
        }
        if( _result == LOSE ) {//負けていたら勝敗回数をリセット
            _countOfWin = 0;
        }
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageBitmap(null);
        ImageView cpuHand  = (ImageView)this.findViewById(R.id.CPUHandImageView);
        cpuHand.setImageBitmap(null);
        _rankingButton.setVisibility(View.INVISIBLE);   //ランキング登録ボタンを非表示
    }


    public void BackButtonClicked(View view) {
        Intent intent = new Intent(this, TitleActivity.class);
        startActivity(intent);
    }


    public void RankingButtonClicked(View view) {
        //ランキング表示サイトへアクセス--------------------
        Uri uri = Uri.parse(_url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        //-------------------------------------------------
        /*
        //データをアプリ側からサーバーに送ることが出来なかったので、とりあえすサイトへ移動する仕様で代用しました...
        Intent intent = new Intent(this, RegisterRankingActivity.class);
        intent.putExtra("countOfWin", _countOfWin); //勝利回数の引継ぎ
        startActivity(intent);
        */
    }
    //======================================================================================
}
