// PersonAidl.aidl
package com.wf.testaidl;

// Declare any non-default types here with import statements
import com.wf.testaidl.bean.Person;

interface PersonAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    /*void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/

    void addPerson(in Person person);
    List<Person> getPersonList();
}