package com.hosplanet.main.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.hosplanet.common.util.CommonUtil;
import com.hosplanet.hosinfo.view.HosMainActivity;
import com.hosplanet.main.presenter.MainPresenter;
import com.hosplanet.main.presenter.MainPresenterImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter mainPresenter;
    private ListView hosListView;
    private HospitalListAdapter hospitalListAdapter;
    private SearchView searchView;
    private Button btnAllList;
    private boolean listLock;
    private Integer paramPageNo;
    private boolean scrollLock;
    private LayoutInflater footerInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonUtil.contextObj = this;
        paramPageNo = 1;
        hospitalListAdapter = new HospitalListAdapter(getApplicationContext(),R.layout.hoslist_info);
        footerInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hosListView = (ListView)findViewById(R.id.hosListView);
        hosListView.setAdapter(hospitalListAdapter);
        hosListView.addFooterView(footerInflater.inflate(R.layout.footer, null));

        Log.i("packageName", getPackageName());
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        hBean.setPageNo(1);
        mainPresenter = new MainPresenterImpl(MainActivity.this);
        mainPresenter.setView(this);
        mainPresenter.getList(hBean);

        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.searchVIewHint));
        searchView.setQuery("대", true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                paramPageNo = 1;
                hospitalListAdapter = new HospitalListAdapter(getApplicationContext(), R.layout.hoslist_info);
                hosListView = (ListView) findViewById(R.id.hosListView);
                hosListView.setAdapter(hospitalListAdapter);

                HospitalInfoApiBean hBean = new HospitalInfoApiBean();
                hBean.setYadmnm(query);
                hBean.setPageNo(paramPageNo);

                mainPresenter.getList(hBean);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnAllList = (Button)findViewById(R.id.btnAllList);
        btnAllList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HospitalInfoApiBean hBean = new HospitalInfoApiBean();
                hBean.setPageNo(1);
                searchView.setQuery("", false);

                mainPresenter.getList(hBean);
            }
        });
         hosListView.setOnScrollListener(new AbsListView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(AbsListView view, int scrollState) {

             }

             @Override
             public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                 int count = totalItemCount - visibleItemCount;

                 if (totalItemCount != 0 && ((firstVisibleItem + visibleItemCount) >= totalItemCount - 1) && firstVisibleItem >= count && listLock == false) {
                     HospitalInfoApiBean hospitalInfoApiBean = new HospitalInfoApiBean();
                     hospitalInfoApiBean.setPageNo(++paramPageNo);
                     if (searchView.getQuery() != null)
                         hospitalInfoApiBean.setYadmnm(searchView.getQuery().toString());

                     addItems(hospitalInfoApiBean);
                 }
             }
         });
        hosListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HospitalInfoApiBean hBean = hospitalListAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, HosMainActivity.class);
                intent.putExtra("com.hoplanet.yadmNm", hBean.getYadmnm());
                intent.putExtra("com.hoplanet.clCd", hBean.getClCd());
                intent.putExtra("com.hoplanet.addr", hBean.getAddr());
                intent.putExtra("com.hoplanet.clCdNm", hBean.getClCdNm());
                intent.putExtra("com.hoplanet.xPos", hBean.getxPos());
                intent.putExtra("com.hoplanet.yPos", hBean.getyPos());

                startActivity(intent);
            }
        });
    }

    @Override
    public void getList(final HospitalInfoApiBean object) {
        HospitalInfoApiBean hospitalInfoApiBean = new HospitalInfoApiBean();
        hospitalInfoApiBean =  object;

        HospitalInfoAsyncTask h = new HospitalInfoAsyncTask(new AsyncResponse(){
            @Override
            public void processFinish(JSONObject jsonObject) throws Exception {
                JSONObject header = jsonObject.getJSONObject("response").getJSONObject("header");
                String resCode  = header.get("resultCode").toString();
                String resMsg = header.get("resultMsg").toString();

                if("00".equals(resCode)){
                    JSONObject body = jsonObject.optJSONObject("response").optJSONObject("body");
                    JSONObject items = body.optJSONObject("items");
                    JSONArray jsonArray = null;
                    if(items == null){
                        Toast.makeText(getApplicationContext(), R.string.noList, Toast.LENGTH_LONG).show();
                    }else{
                        jsonArray = items.optJSONArray("item");
                    }

                    String numOfRows = body.get("numOfRows").toString();
                    String pageNo = body.get("pageNo").toString();
                    String totalCount = body.get("totalCount").toString();

                    hospitalListAdapter.setMainPresenter(mainPresenter);

                    if(jsonArray == null){
                        JSONObject item = null;
                        if (items != null) {
                            item = items.optJSONObject("item");
                        }
                        hospitalListAdapter.add(HospitalInfoApiClient.getJObjectFromHBean(item));
                    }else{

                        for(int i=0; i<jsonArray.length(); i++) {
                            JSONObject item = jsonArray.optJSONObject(i);
                            hospitalListAdapter.add(HospitalInfoApiClient.getJObjectFromHBean(item));
                        }

                    }

                }else{
                    Toast.makeText(getApplicationContext(), resMsg, Toast.LENGTH_LONG).show();
                }
            }
        });
        h.execute(hospitalInfoApiBean);
    }

    @Override
    public void goUrl(String url) {

        if(url.equals("") || url == null){
            Toast.makeText(getApplicationContext(),"NOT FOUND URL.",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this,HospUrlActivity.class);
            intent.putExtra("com.hosplanet.HOSPURL", url);
            startActivity(intent);
        }
    }

    private void addItems(final HospitalInfoApiBean hBean){
        listLock = true;
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Wait while loading....");

        Runnable run = new Runnable() {
            @Override
            public synchronized void run() {
                mainPresenter.getList(hBean);
                hospitalListAdapter.notifyDataSetChanged();
                listLock = false;

                dialog.dismiss();
            }
        };
        dialog.show();;
        // 속도의 딜레이를 구현하기 위한 꼼수

        Handler handler = new Handler();
        handler.postDelayed(run, 3000);
    }

}
