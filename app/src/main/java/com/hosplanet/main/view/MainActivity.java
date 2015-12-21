package com.hosplanet.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hosplanet.api.AsyncResponse;
import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.api.HospitalInfoApiClient;
import com.hosplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.R;
import com.hosplanet.hosinfo.view.HosMainActivity;
import com.hosplanet.main.presenter.MainPresenter;
import com.hosplanet.main.presenter.MainPresenterImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{
    private MainPresenter mainPresenter;
    private ListView hosListView;
    private HospitalListAdapter hospitalListAdapter;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(MainActivity.this);
        mainPresenter.setView(this);
        mainPresenter.getList(null);

        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.searchVIewHint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mainPresenter.getList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void getList(Object object) {
        HospitalInfoApiBean hospitalInfoApiBean= new HospitalInfoApiBean();
        if(object != null){
            Log.i("SEARCHVIEWTEXT",(String)object);
            hospitalInfoApiBean.setYadmnm((String) object);
        }
        HospitalInfoAsyncTask h = new HospitalInfoAsyncTask(new AsyncResponse(){
            @Override
            public void processFinish(JSONObject jsonObject) throws Exception {

                Log.i("JSONOBJECT",jsonObject.toString());
                JSONObject header = jsonObject.getJSONObject("response").getJSONObject("header");
                String resCode  = header.get("resultCode").toString();
                String resMsg = header.get("resultMsg").toString();

                if("00".equals(resCode)){
                    JSONObject body = jsonObject.optJSONObject("response").optJSONObject("body");
                    Log.i("itemlength",Integer.toString(body.optJSONObject("items").length()));
                    JSONArray jsonArray = body.optJSONObject("items").getJSONArray("item");
                    String numOfRows = body.get("numOfRows").toString();
                    String pageNo = body.get("pageNo").toString();
                    String totalCount = body.get("totalCount").toString();
                    Log.i("arraLength",Integer.toString(jsonArray.length()));

                    hosListView = (ListView)findViewById(R.id.hosListView);
                    hospitalListAdapter = new HospitalListAdapter(getApplicationContext(),R.layout.hoslist_info);
                    hospitalListAdapter.setMainPresenter(mainPresenter);
                    hosListView.setAdapter(hospitalListAdapter);
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        hospitalListAdapter.add(HospitalInfoApiClient.getJObjectFromHBean(item));
                    }
                    hosListView.setOnItemClickListener(new ListView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            HospitalInfoApiBean hBean = hospitalListAdapter.getItem(position);
                            Log.i("요양번호", hBean.getYkiho());
                            Intent intent = new Intent(MainActivity.this, HosMainActivity.class);
                            intent.putExtra("com.hoplanet.ykiho",hBean.getYkiho());
                            startActivity(intent);
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), resMsg, Toast.LENGTH_LONG).show();

                }

            }
        });
        h.execute(hospitalInfoApiBean);
    }

    @Override
    public void goUrl(String url) {
        Log.i("MAINGOURL",url);
        if(url.equals("") || url == null){
            Toast.makeText(getApplicationContext(),"NOT FOUND URL.",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this,HospUrlActivity.class);
            intent.putExtra("com.hosplanet.HOSPURL", url);
            startActivity(intent);
        }
    }
}
