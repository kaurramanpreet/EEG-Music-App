package com.example.youtbe;

import android.provider.BaseColumns;

public final class database {
    public database(){}
    public static class happy implements BaseColumns{
        public static final String TABLE_NAME = "happy";
        public static final String COL_1 = "id";
        public static final String COL_2 = "url";
    }
    public static class sad implements BaseColumns{
        public static final String TABLE_NAME="sad";
        public static final String COL_1="id";
        public static final String COL_2="url";

    }
    public static class users implements BaseColumns{
        public static final String TABLE_NAME="users";
        public static final String COL_1="username";
        public static final String COL_2="name";
        public static final String COL_3="password";

    }
}

