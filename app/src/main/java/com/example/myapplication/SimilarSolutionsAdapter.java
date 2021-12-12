package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SimilarSolutionsAdapter extends ArrayAdapter<SimilarSolutions> {

    private LayoutInflater inflater;
    private int layout;
    private List<SimilarSolutions> states;

    public SimilarSolutionsAdapter(Context context, int resource, List<SimilarSolutions> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.nameUser);
        TextView timeView = view.findViewById(R.id.timeComment);

        SimilarSolutions state = states.get(position);

        nameView.setText(state.getName());
        timeView.setText(state.getTime());

        return view;
    }
}