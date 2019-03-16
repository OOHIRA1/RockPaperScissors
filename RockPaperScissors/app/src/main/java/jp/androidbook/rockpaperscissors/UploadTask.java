package jp.androidbook.rockpaperscissors;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//==データをアップロードするクラス
public class UploadTask extends AsyncTask<String, Void, String> {
    private String _urlStr;    //アップロード先のURL文字列
    private String _userName;   //ユーザー名
    private int _countOfWin;    //勝利回数

    //コンストラクタ=======================================================
    public UploadTask( String urlStr, String userName, int countOfWin ) {
        _urlStr = urlStr;
        _userName = userName;
        _countOfWin = countOfWin;
    }
    //====================================================================


    //オーバーライド========================================================
    @Override
    protected String doInBackground(String... param) {
        String statusCode = "";

        try {
            URL url = new URL(_urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST"); //POSTリクエストメゾット
            httpURLConnection.setInstanceFollowRedirects(false);    //no Redirects
            httpURLConnection.setDoOutput(true);    //データを書き込む
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.connect();
            statusCode = String.valueOf( httpURLConnection.getResponseCode() );
            //POSTデータ送信処理----------------------------------------
            OutputStream outputStream = null;
            try {
                outputStream = httpURLConnection.getOutputStream();
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
        }
        return statusCode;
    }
    //=====================================================================
}
