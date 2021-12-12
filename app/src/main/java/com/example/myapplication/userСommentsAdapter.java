package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class userСommentsAdapter extends ArrayAdapter<userСomments> {

    private LayoutInflater inflater;
    private int layout;
    private List<userСomments> states2;

    public userСommentsAdapter(Context context, int resource, List<userСomments> states2) {
        super(context, resource, states2);
        this.states2 = states2;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView nameUserView = view.findViewById(R.id.nameUser);
        TextView textCommentUserView = view.findViewById(R.id.textCommentUser);
        TextView timeCommentView = view.findViewById(R.id.timeComment);

        userСomments state2 = states2.get(position);

        nameUserView.setText(state2.getNameUser());
        textCommentUserView.setText(state2.getTextCommentUser());
        timeCommentView.setText(state2.getTimeComment());

        return view;
    }
}
