package com.wf.testaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wf.testaidl.bean.Person;
import com.wf.testaidl.service.AidlService;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTvShow;
    private PersonAidl mPersonAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binder服务
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mPersonAidl = PersonAidl.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mPersonAidl = null;
            }
        }, BIND_AUTO_CREATE);

        //调用服务接口
        mTvShow = findViewById(R.id.tv_show);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Person person = new Person("wf" + random.nextInt(10));
                try {
                    mPersonAidl.addPerson(person);
                    List<Person> personList = mPersonAidl.getPersonList();
                    mTvShow.setText(personList.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
