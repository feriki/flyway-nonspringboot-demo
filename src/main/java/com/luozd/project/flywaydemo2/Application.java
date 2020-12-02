package com.luozd.project.flywaydemo2;

import com.luozd.project.flywaydemo2.flyway.MigrationMySql;

public class Application {

    public static void main(String[] args) {
        MigrationMySql mms=new MigrationMySql();
        mms.migrate();
    }
}
