package jp.androidbook.rockpaperscissors;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;


//==じゃんけんの結果画面のSurfaceView
public class ResultSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    int _result;    //じゃんけんの結果(0:勝ち, 1:負け, 2:引き分け)
    TextView _resultTextView;   //結果を表示するテキストView
    TextView _countOfWinTextView;   //連勝回数を表示するテキストView
    int _countOfWin;                  //勝利回数

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
    public ResultSurfaceView(Context context, int result, TextView resultTextView, TextView countOfWinTextView, int countOfWin ) {
        super(context);
        _result = result;
        _resultTextView = resultTextView;
        _countOfWinTextView = countOfWinTextView;
        _countOfWin = countOfWin;
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
                break;
            default:
                break;
        }
        //ViewGroup viewGroup = (ViewGroup) this.getParent();
        //viewGroup.addView(_resultTextView);
        return super.onTouchEvent(motionEvent);
    }
    //============================================================================================
}
