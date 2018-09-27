package com.example.astronout.mydictionary.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_ENGLISH = "table_english";
    static String TABLE_INDONESIA = "table_indonesia";

    static final class KamusColumns implements BaseColumns {

        static String KATA = "kata";
        static String TRANSLATE = "translate";

    }
}
