package com.wf.testaidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.wf.testaidl.PersonAidl;
import com.wf.testaidl.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {

    private List<Person> mPersons = new ArrayList<>();
    private IBinder mIBinder = new PersonAidl.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
