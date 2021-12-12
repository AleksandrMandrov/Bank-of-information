package com.example.myapplication;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {

    private LayoutInflater inflater;
    private int layout;
    private List<SearchResult> states;

    public SearchResultAdapter(Context context, int resource, List<SearchResult> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView mainTextView = view.findViewById(R.id.mainText);
        TextView additionalTextView = view.findViewById(R.id.additionalText);

        SearchResult state = states.get(position);

        mainTextView.setText(state.getMainText());
        additionalTextView.setText(state.getAdditionalText());

        return view;
    }
}
