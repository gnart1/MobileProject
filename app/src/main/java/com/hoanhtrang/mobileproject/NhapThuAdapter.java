package com.hoanhtrang.mobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NhapThuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NhapThu> listNhapThu;

    public NhapThuAdapter(Context context, int layout, List<NhapThu> listNhapThu) {
        this.context = context;
        this.layout = layout;
        this.listNhapThu = listNhapThu;
    }

    public NhapThuAdapter(ListNhapThuActivity context, ArrayList<NhapChi> m_nhapchi) {
    }

    @Override
    public int getCount() {
        return this.listNhapThu.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtName, txtNgay, txtSoTien;
        ImageView imgEdit, imgDelete, imgAdd;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtName =(TextView) view.findViewById(R.id.txtName);
            holder.txtNgay =(TextView) view.findViewById(R.id.txtNgay);
            holder.txtSoTien =(TextView) view.findViewById(R.id.txtSoTien);
            holder.imgEdit = view.findViewById(R.id.imgEdit);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            view.setTag(holder);

        }else {
            holder =(ViewHolder) view.getTag();
        }
        NhapThu nhapThu = listNhapThu.get(i);
        holder.txtName.setText(nhapThu.getGhichu());
        holder.txtNgay.setText(nhapThu.getNgay());
        holder.txtSoTien.setText(nhapThu.getSotien()+"VND");

        return view;
    }
}
