package com.example.khimi.igustipututresnanata_1202150025_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Khimi on 3/25/2018.
 */

public class Adapter extends RecyclerView.Adapter <Adapter.holder> {
    //deklarasi variabel
    private Context mContex;
    private List<AddData> mlist;
    int mcolor;

    public Adapter(Context context, List<AddData> list, int color){
        this.mContex=context;
        this.mlist=list;
        this.mcolor=color;
    }

    //viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //view baru
        View view = LayoutInflater.from(mContex).inflate(R.layout.cardview, parent, false);
        holder Mhldr = new holder(view);
        return Mhldr;
    }

    //melakukan set nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = mlist.get(position);
        holder.mToDo.setText(data.getTodo());
        holder.mDescription.setText(data.getDesccription());
        holder.mPriority.setText(data.getPriority());
        holder.mCardview.setCardBackgroundColor(mContex.getResources().getColor(this.mcolor));
    }

    //melakukan get jumlah list
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    //melakukan get list dari adapter
    public AddData getData(int position){
        return mlist.get(position);
    }

    // menghapus list pada todolist
    public void deleteData(int i){
        //hapus item yang terpilih
        mlist.remove(i);
        //notifikasi
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, mlist.size());
    }

    //kelas untuk recyclerview
    class holder extends RecyclerView.ViewHolder{
        //deklarasi variable objek
        public TextView mToDo, mDescription, mPriority;
        public CardView mCardview;
        public holder(View itemView){
            super(itemView);

            //ambil data dari cardview
            mToDo = itemView.findViewById(R.id.todo);
            mDescription = itemView.findViewById(R.id.description);
            mPriority = itemView.findViewById(R.id.number);
            mCardview = itemView.findViewById(R.id.cardview);
        }
    }
}


