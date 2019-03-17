package jp.androidbook.rockpaperscissors;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//==データをアップロードするクラス
public class UploadTask extends AsyncTask<String, Void, String> {
    private String _urlStr;    //アップロード先のURL文字列
    private String _userName;   //ユーザー名
    private int _countOfWin;    //勝利回数
    private Button _browserButton;  //ブラウザ表示ボタン

    //コンストラクタ=======================================================
    public UploadTask( String urlStr, String userName, int countOfWin, Button browserButton ) {
        //_urlStr = urlStr;
        _urlStr = "http://gameprogram.shop/show_ranking.php";
        _userName = userName;
        _countOfWin = countOfWin;
        _browserButton = browserButton;
    }
    //====================================================================


    //オーバーライド========================================================
    @Override
    protected String doInBackground(String... param) {
        String statusCode = "";
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(_urlStr);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST"); //POSTリクエストメゾット
            httpURLConnection.setInstanceFollowRedirects(false);    //no Redirects
            httpURLConnection.setDoOutput(true);    //データを書き込む
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(50000);
            httpURLConnection.connect();
            statusCode = String.valueOf( httpURLConnection.getResponseCode() );
            //POSTデータ送信処理----------------------------------------
            OutputStream outputStream = null;
            try {
                outputStream = httpURLConnection.getOutputStream();         //なぜかここでoutputStreamがnullになってエラーになってしまう。（データを送信できない…）
                String sendUserName = "user_name=" + _userName;
                String sendCountOfWin = "count_of_win=" + _countOfWin;
                outputStream.write(sendUserName.getBytes("UTF-8"));
                outputStream.write(sendCountOfWin.getBytes("UTF-8"));
                outputStream.flush();
            } catch ( IOException ioException ) {
                ioException.printStackTrace();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            //----------------------------------------------------------
        } catch ( IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return statusCode;
    }


    @Override
    public void onPostExecute(String result) {
        super.onPostExecute(result);
        if ( result == String.valueOf(HttpURLConnection.HTTP_OK ) ) {
            _browserButton.setVisibility(View.VISIBLE); //最後まで出来たらブラウザボタン表示
        }
    }
    //=====================================================================
}
