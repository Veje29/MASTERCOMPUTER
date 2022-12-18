package umn.ac.id.uas.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import umn.ac.id.uas.project.R;

public class CustomVgaCardAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    String[] vgaCards;
    int[] imageIcons;

    public CustomVgaCardAdapter(Context applicationContext, String[] vgaCards, int[] imageIcons) {
        this.context = applicationContext;
        this.vgaCards = vgaCards;
        this.imageIcons = imageIcons;
        inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return vgaCards.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.processor_spinner_item, null);
        TextView processorName = view.findViewById(R.id.brand_processor);
        ImageView processorIcon = view.findViewById(R.id.icon_processor);
        processorName.setText(vgaCards[i]);
        processorIcon.setImageResource(imageIcons[i]);
        return view;
    }
}
