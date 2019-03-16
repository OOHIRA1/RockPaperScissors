package jp.androidbook.rockpaperscissors;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


//==じゃんけんの結果画面のSurfaceView
public class ResultSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    int _result;    //じゃんけんの結果(0:勝ち, 1:負け, 2:引き分け)
    TextView _resultTextView;   //結果を表示するテキストView
    TextView _countOfWinTextView;   //連勝回数を表示するテキストView
    int _countOfWin;                  //勝利回数
    Button[] _resultButtons;          //勝敗結果画面のボタン配列
    private Button _rankingButton;                 //ランキング登録ボタン

    //コンストラクタ==============================================================================
    @Deprecated
    public ResultSurfaceView(Context context) {
        super(context);
    }

    public ResultSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResultSurfaceView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }


    //--プログラム上ではこちらのコンストラクタを使用する
    public ResultSurfaceView(Context context, int result, TextView resultTextView, TextView countOfWinTextView, int countOfWin, Button[] resultButtons, Button rankingButton ) {
        super(context);
        _result = result;
        _resultTextView = resultTextView;
        _countOfWinTextView = countOfWinTextView;
        _countOfWin = countOfWin;
        _resultButtons = new Button[MainActivity.RESULT_BUTTON_COUNT];  //配列のメモリ確保
        for ( int i = 0; i < _resultButtons.length; i++ ) {
            _resultButtons[i] = resultButtons[i];
        }
        _rankingButton = rankingButton;
    }
    //============================================================================================


    //オーバーライド===============================================================================
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch( _result ) {
            case 0:
                _resultTextView.setText("あなたの勝ち");
                _countOfWinTextView.setText(String.valueOf(_countOfWin) + "連勝中");
                break;
            case 1:
                _resultTextView.setText("あなたの負け");
                _countOfWinTextView.setText(String.valueOf(_countOfWin) + "連勝でした…");
                break;
            case 2:
                _resultTextView.setText("引き分け");
                _countOfWinTextView.setText(String.valueOf(_countOfWin) + "連勝中");
                break;
            default:
                break;
        }

        for ( int i = 0; i < _resultButtons.length; i++ ) {
            _resultButtons[i].setVisibility(View.VISIBLE);
        }
        _rankingButton.setVisibility(View.VISIBLE);
        ViewGroup viewGroup = (ViewGroup) this.getParent();
        viewGroup.removeView(this); //自身を取り除く処理
        return super.onTouchEvent(motionEvent);
    }
    //============================================================================================
}
