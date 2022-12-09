package com.example.api_list_test_disease;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {

    EditText edit;
    TextView text;


    String key="sX%2B6FXImlrlIbU8MqOFRGgXE0iVhxhVraBqBjW27gOMmvTIXNFpzKw6%2Bre0wm7x%2FaeiM5iyLXODaxuX%2BV99TQg%3D%3D";

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit= (EditText)findViewById(R.id.edit);
        text= (TextView)findViewById(R.id.text);

        final Button list = (Button) findViewById(R.id.list);
        final EditText edit = (EditText)findViewById(R.id.edit);

        list.setOnClickListener(new View.OnClickListener(){

            //목록 보기 코드
            public void onClick(View view){

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("감염병 목록 선택하기"); //제목
                final String[] versionArray = new String[] {"결핵","B형간염","디프테리아","파상풍","백일해","폴리오","B형 헤모필루스 인플루엔자","폐렴구균","홍역","유행성이하선염","풍진","수두","일본뇌염","인플루엔자","장티푸스","신증후군출혈열","A형간염","로타바이러스","사람유두종바이러스","수막구균","대상포진"};
                dlg.setIcon(R.drawable.disease_panda); // 아이콘 설정

                dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit.setText(versionArray[which]);
                    }

                });
//                버튼 클릭시 동작
                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(MainActivity.this,"확인을 누르셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

    }

    //Button을 클릭했을 때 자동으로 호출되는 callback method
    public void mOnClick(View v){

        switch( v.getId() ){
            case R.id.search_disease:

                // 급하게 짜느라 소스가 지저분해요..
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data= getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기


                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                text.setText(data); //TextView에 문자열  data 출력
                            }
                        });

                    }
                }).start();

                break;
        }

    }

    String getXmlData(){

        StringBuffer buffer=new StringBuffer();

        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
        String vcnCd = URLEncoder.encode(str);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding


        if(str.equals("결핵")){ //결핵
            vcnCd = "01";
        }
        else if(str.equals("B형간염")){ //B형간염
            vcnCd = "02";
        }
        else if(str.equals("디프테리아")){ //디프테리아
            vcnCd = "03";
        }
        else if(str.equals("폴리오")){ //폴리오
            vcnCd = "04";
        }
        else if(str.equals("B형 헤모필루스 인플루엔자")){ //B형 헤모필루스 인플루엔자
            vcnCd = "05";
        }
        else if(str.equals("폐렴구균")){ //폐렴구균
            vcnCd = "06";
        }
        else if(str.equals("홍역")){ //홍역
            vcnCd = "07";
        }
        else if(str.equals("수두")){ //수두
            vcnCd = "08";
        }
        else if(str.equals("일본뇌염")){ //일본뇌염
            vcnCd = "09";
        }
        else if(str.equals("인플루엔자")){ //인플루엔자
            vcnCd = "10";
        }
        else if(str.equals("장티푸스")){ //장티푸스
            vcnCd = "11";
        }
        else if(str.equals("신증후군출혈열")){ //신증후군출혈열
            vcnCd = "12";
        }
        else if(str.equals("A형간염")){ //A형간염
            vcnCd = "13";
        }
        else if(str.equals("로타바이러스")){ //로타바이러스
            vcnCd = "14";
        }
        else if(str.equals("사람유두종바이러스")){ //사람유두종바이러스
            vcnCd = "15";
        }
        else if(str.equals("수막구균")){ //수막구균
            vcnCd = "16";
        }
        else if(str.equals("대상포진")){ //대상포진
            vcnCd = "17";
        }
        else if(str.equals("파상풍")){ //파상풍
            vcnCd = "18";
        }
        else if(str.equals("백일해")){ //백일해
            vcnCd = "19";
        }
        else if(str.equals("유행성이하선염")){ //유행성이하선염
            vcnCd = "20";
        }
        else if(str.equals("풍진")){ //풍진
            vcnCd = "21";
        }
        else{
            buffer.append("지원하지않는 감염병 입니다.\n");
        }

        /*
        if(vcnCd.equals("%EA%B2%B0%ED%95%B5")){ //결핵
            vcnCd = "01";
        }
        if(vcnCd.equals("%EA%B2%B0%ED%95%B5")){ //결핵
            vcnCd = "01";
        }
        else if(vcnCd.equals("B%ED%98%95%EA%B0%84%EC%97%BC")){ //B형간염
            vcnCd = "02";
        }
        else if(vcnCd.equals("%EB%94%94%ED%94%84%ED%85%8C%EB%A6%AC%EC%95%84")){ //디프테리아
            vcnCd = "03";
        }
        else if(vcnCd.equals("%ED%8F%B4%EB%A6%AC%EC%98%A4")){ //폴리오
            vcnCd = "04";
        }
        else if(vcnCd.equals("B%ED%98%95%20%ED%97%A4%EB%AA%A8%ED%95%84%EB%A3%A8%EC%8A%A4%20%EC%9D%B8%ED%94%8C%EB%A3%A8%EC%97%94%EC%9E%90")){ //B형 헤모필루스 인플루엔자
            vcnCd = "05";
        }
        else if(vcnCd.equals("%ED%8F%90%EB%A0%B4%EA%B5%AC%EA%B7%A0")){ //폐렴구균
            vcnCd = "06";
        }
        else if(vcnCd.equals("%ED%99%8D%EC%97%AD")){ //홍역
            vcnCd = "07";
        }
        else if(vcnCd.equals("%EC%88%98%EB%91%90")){ //수두
            vcnCd = "08";
        }
        else if(vcnCd.equals("%EC%9D%BC%EB%B3%B8%EB%87%8C%EC%97%BC")){ //일본뇌염
            vcnCd = "09";
        }
        else if(vcnCd.equals("%EC%9D%B8%ED%94%8C%EB%A3%A8%EC%97%94%EC%9E%90")){ //인플루엔자
            vcnCd = "10";
        }
        else if(vcnCd.equals("%EC%9E%A5%ED%8B%B0%ED%91%B8%EC%8A%A4")){ //장티푸스
            vcnCd = "11";
        }
        else if(vcnCd.equals("%EC%8B%A0%EC%A6%9D%ED%9B%84%EA%B5%B0%EC%B6%9C%ED%98%88%EC%97%B4")){ //신증후군출혈열
            vcnCd = "12";
        }
        else if(vcnCd.equals("A%ED%98%95%EA%B0%84%EC%97%BC")){ //A형간염
            vcnCd = "13";
        }
        else if(vcnCd.equals("%EB%A1%9C%ED%83%80%EB%B0%94%EC%9D%B4%EB%9F%AC%EC%8A%A4")){ //로타바이러스
            vcnCd = "14";
        }
        else if(vcnCd.equals("%EC%82%AC%EB%9E%8C%EC%9C%A0%EB%91%90%EC%A2%85%EB%B0%94%EC%9D%B4%EB%9F%AC%EC%8A%A4")){ //사람유두종바이러스
            vcnCd = "15";
        }
        else if(vcnCd.equals("%EC%88%98%EB%A7%89%EA%B5%AC%EA%B7%A0")){ //수막구균
            vcnCd = "16";
        }
        else if(vcnCd.equals("%EB%8C%80%EC%83%81%ED%8F%AC%EC%A7%84")){ //대상포진
            vcnCd = "17";
        }
        else if(vcnCd.equals("%ED%8C%8C%EC%83%81%ED%92%8D")){ //파상풍
            vcnCd = "18";
        }
        else if(vcnCd.equals("%EB%B0%B1%EC%9D%BC%ED%95%B4")){ //백일해
            vcnCd = "19";
        }
        else if(vcnCd.equals("%EC%9C%A0%ED%96%89%EC%84%B1%EC%9D%B4%ED%95%98%EC%84%A0%EC%97%BC")){ //유행성이하선염
            vcnCd = "20";
        }
        else if(vcnCd.equals("%ED%92%8D%EC%A7%84")){ //풍진
            vcnCd = "21";
        }
        else{
            buffer.append("지원하지않는 감염병 입니다.\n");
        }
        */

        String queryUrl="http://apis.data.go.kr/1790387/vcninfo/getVcnInfo?ServiceKey="//요청 URL
                + key + "&vcnCd=" + vcnCd;


        try {


            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag = null;



            xpp.next();
            int eventType= xpp.getEventType();
//eventType != XmlPullParser.END_DOCUMENT
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("title")){
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가//R.id.disease_title
                            buffer.append("\n\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("message")){
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기

                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈

                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
//            buffer.append(queryUrl);
            buffer.append("존재하지 않는 감염병 입니다.\n");
            // TODO Auto-generated catch blocke.printStackTrace();
        }

        return buffer.toString();//StringBuffer 문자열 객체 반환

    }
}
