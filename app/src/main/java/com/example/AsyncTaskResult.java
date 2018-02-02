package com.example;

/**
 * Created by qaz35 on 2018/2/2/002.
 */

public interface AsyncTaskResult<String> {
    // T是執行結果的物件型態

    public void taskFinish( String result );
}