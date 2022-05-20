package com.hoanhtrang.mobileproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanhtrang.mobileproject.fragment.nhapchiFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NhapChiAdapter extends BaseAdapter {
    private ListNhapChiActivity context;
    private int layout;
    private List<NhapChi> listNhapChi;

    public NhapChiAdapter(ListNhapChiActivity context, int layout, List<NhapChi> listNhapChi) {
        this.context = context;
        this.layout = layout;
        this.listNhapChi = listNhapChi;
    }

//    public NhapChiAdapter(ListNhapChiActivity listNhapChiActivity, ArrayList<NhapChi> m_nhapchi) {
//    }

//    public NhapChiAdapter(nhapchiFragment nhapchiFragment, int activity_nhapchi_chitiet, View listNhapChi) {
//    }

    @Override
    public int getCount() {
        return this.listNhapChi.size();
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
        ViewHolder holder = null;

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

        NhapChi nhapChi = listNhapChi.get(i);
        holder.txtName.setText(nhapChi.getGhichu());
        holder.txtNgay.setText(nhapChi.getNgay());
        holder.txtSoTien.setText(nhapChi.getSotien()+"VND");

//        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ListNhapChiActivity._Sua = "M";
//                Intent Chuyen = new Intent(context,NhapChiActivity.class);
//                context.startActivity(Chuyen);
//                Toast.makeText(context, "Thêm !", Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListNhapChiActivity._Sua = "S";
                Intent it = new Intent(context,NhapChiActivity.class);
                it.putExtra("date",listNhapChi.get(i).getNgay());
                it.putExtra("note",listNhapChi.get(i).getGhichu());
                it.putExtra("amount",listNhapChi.get(i).getSotien());
                it.putExtra("type",listNhapChi.get(i).getDanhmuc());

                context.startActivity(it);

                Toast.makeText(context, "Sửa !", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
