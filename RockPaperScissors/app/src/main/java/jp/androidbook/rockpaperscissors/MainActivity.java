package jp.androidbook.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
    final int COUNT_OF_JANNKENN = 3;    //じゃんけんの種類数
    private ImageButton[] buttons;   //じゃんけんボタン配列
    private TextView textView;
    private ViewGroup viewGroup;


    //オーバーライド================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons = new ImageButton[COUNT_OF_JANNKENN];   //配列のメモリ確保
        buttons[ROCK] = (ImageButton) this.findViewById(R.id.RockButton);
        buttons[SCISSORS] = (ImageButton)this.findViewById(R.id.ScissorsButton);
        buttons[PAPER] = (ImageButton)this.findViewById(R.id.PaperButton);
        textView = (TextView) this.findViewById(R.id.jannkennTextView);
        viewGroup = (ViewGroup) textView.getParent();
    }
    //=============================================================

    //--じゃんけんボタンをクリックした時の共通処理
    private void JannkennButtonClicked() {
        textView.setText("ポン！！");
        TextView textViewYou = (TextView)this.findViewById(R.id.textViewYou);
        textViewYou.setText("あなた");
        ImageView cpuHand  = (ImageView)this.findViewById(R.id.cpuHandImageView);
        cpuHand.setImageResource(R.drawable.rock_small);
        TextView textViewCPU = (TextView)this.findViewById(R.id.textViewCPU);
        textViewCPU.setText("CPU");
    }

    //public関数===========================================================================
    public void RockButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.yourHandImageView);
        yourHand.setImageResource(R.drawable.rock_small);
        JannkennButtonClicked();
        for(int i = 0; i < buttons.length; i++) {
            viewGroup.removeView(buttons[i]);
        }
    }


    public void ScissorsButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.yourHandImageView);
        yourHand.setImageResource(R.drawable.scissor_small);
        JannkennButtonClicked();
        for(int i = 0; i < buttons.length; i++) {
            viewGroup.removeView(buttons[i]);
        }
    }


    public void PaperButtonClicked(View view) {
        ImageView yourHand = (ImageView)this.findViewById(R.id.yourHandImageView);
        yourHand.setImageResource(R.drawable.paper_small);
        JannkennButtonClicked();
        for(int i = 0; i < buttons.length; i++) {
            viewGroup.removeView(buttons[i]);
        }
    }
    //======================================================================================
}
