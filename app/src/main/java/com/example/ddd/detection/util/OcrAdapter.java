package com.example.ddd.detection.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ddd.detection.R;

import java.util.List;

public class OcrAdapter extends ArrayAdapter<OcrResult> {

    private int resourceId;

    public OcrAdapter(Context context, int textViewResourceId,
                      List<OcrResult> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        OcrResult ocrResult = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageview = (ImageView) view.findViewById(R.id.image);
        TextView textview = (TextView) view.findViewById(R.id.ocrResult);
        if(!ocrResult.getImpagePath().isEmpty())
            imageview.setImageBitmap(ocrResult.GetBitmap());
        textview.setText(ocrResult.getOcrResult());
        return view;
    }
}
