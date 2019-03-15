package jp.androidbook.rockpaperscissors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
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
    //UI関連===========================================================================
    private ImageButton[] _buttons;   //じゃんけんボタン配列
    private TextView _textView;       //画面上部のテキスト
    private ViewGroup viewGroup;
    private int _countOfWin;        //勝利回数
    private ResultSurfaceView _resultSurfaceView;   //じゃんけん結果表示画面View
    private TextView _resultTextView;               //勝敗を表示するテキストView
    private TextView _countOfWinTextView;           //連勝回数を表示するテキストView
    //=================================================================================



    //オーバーライド================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _buttons = new ImageButton[COUNT_OF_RPS];   //配列のメモリ確保
        _buttons[ROCK] = (ImageButton) this.findViewById(R.id.RockButton);
        _buttons[SCISSORS] = (ImageButton)this.findViewById(R.id.ScissorsButton);
        _buttons[PAPER] = (ImageButton)this.findViewById(R.id.PaperButton);
        _textView = (TextView) this.findViewById(R.id.RPSTextView);
        viewGroup = (ViewGroup) _textView.getParent();
        _countOfWin = 0;    //勝利回数を0にする
        _resultTextView = (TextView)this.findViewById(R.id.ResultTextView);
        _resultTextView.setText("");
        _countOfWinTextView = (TextView)this.findViewById(R.id.CountOfWinTextView);
        _countOfWinTextView.setText("");
    }
    //=============================================================

    //--じゃんけんボタンをクリックした時の共通処理(RPSはRockPaperScissorsの略)(返り値：CPUの出したじゃんけんのIndex番号)
    private int RPSButtonClicked() {
        _textView.setText("ポン！！");
        TextView textViewYou = (TextView)this.findViewById(R.id.TextViewYou);
        textViewYou.setText("あなた");
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
            returnVal = 0;//勝利
            _countOfWin++;
        } else if ( playerRPSIndex == ((cpuRPSIndex + 1) % 3) ) {
            returnVal = 1;//敗北
        } else {
            returnVal = 2;//引き分け
        }
        return returnVal;
    }

    //public関数===========================================================================
    public void RockButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageResource(R.drawable.rock_small);
        int cpuRPSIndex = RPSButtonClicked();
        for(int i = 0; i < _buttons.length; i++) {
            //viewGroup.removeView(_buttons[i]);
            _buttons[i].setVisibility(View.INVISIBLE);
        }
        _resultSurfaceView = new ResultSurfaceView( this, Judge(ROCK, cpuRPSIndex), _resultTextView, _countOfWinTextView, _countOfWin );
        _resultSurfaceView.setBackgroundColor(Color.WHITE);
        viewGroup.addView(_resultSurfaceView, 0);
    }


    public void ScissorsButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageResource(R.drawable.scissor_small);
        int cpuRPSIndex = RPSButtonClicked();
        for(int i = 0; i < _buttons.length; i++) {
            _buttons[i].setVisibility(View.INVISIBLE);
        }
        _resultSurfaceView = new ResultSurfaceView( this.getApplicationContext(), Judge(SCISSORS, cpuRPSIndex), _resultTextView, _countOfWinTextView, _countOfWin);
        _resultSurfaceView.setBackgroundColor(Color.WHITE);
        viewGroup.addView(_resultSurfaceView, 0);
    }


    public void PaperButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.YourHandImageView);
        yourHand.setImageResource(R.drawable.paper_small);
        int cpuRPSIndex = RPSButtonClicked();
        for(int i = 0; i < _buttons.length; i++) {
            _buttons[i].setVisibility(View.INVISIBLE);
        }
        _resultSurfaceView = new ResultSurfaceView( this.getApplicationContext(), Judge(PAPER, cpuRPSIndex), _resultTextView, _countOfWinTextView, _countOfWin);
        _resultSurfaceView.setBackgroundColor(Color.WHITE);
        viewGroup.addView(_resultSurfaceView, 0);
    }
    //======================================================================================
}
