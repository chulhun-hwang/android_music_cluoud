package com.hch.hooney.musiccloudproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hch.hooney.musiccloudproject.Do.TalKData;
import com.hch.hooney.musiccloudproject.listPack.TalkListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TalkActivity extends AppCompatActivity {
    private final String TAG = TalkActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private EditText editText;
    private Button sendBtn;
    private TextView progressText;

    private ArrayList<TalKData> list;
    private TalkListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        init();
    }

    private void init() {
        list = new ArrayList<>();
        adapter = new TalkListAdapter();

        progressText = findViewById(R.id.item_talk_progress_text);
        if(getIntent().getStringExtra("progress") != null){
            progressText.setText(getIntent().getStringExtra("progress"));
        }

        recyclerView = findViewById(R.id.talk_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getApplicationContext(), RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
        editText = findViewById(R.id.talk_edit);
        sendBtn = findViewById(R.id.talk_send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().trim().isEmpty()){
                    TalKData item = new TalKData();
                    item.setUserName("Root");
                    item.setUserId("0001");
                    item.setMsg(editText.getText().toString());
                    item.setCreateDate(getNowDateTime());
                    list.add(item);

                    adapter.setList(list);

                    editText.setText("");
                    recyclerView.scrollToPosition(list.size()-1);
                }
            }
        });
    }

    private String getNowDateTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA);
        return simple.format(date);
    }


}
