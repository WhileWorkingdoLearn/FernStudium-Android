package com.example.praxisphase_tagvier;

import android.content.Context;
import android.database.Cursor;

import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class ToDoAdapter extends SimpleCursorAdapter {

    public ToDoAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
}
