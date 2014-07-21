package com.littleturn.todo;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{

	String[] Title, Detail,Priority,Category;
    int[] imge;
    Context myContext;
    LayoutInflater inflater;
    List<RowItem> rowItems;
    
    CustomAdapter() {
        Title = null;
        Detail = null;
        imge=null;
        Priority=null;
        Category=null;
    }

    public CustomAdapter(Context myContext,List<RowItem> items) {
       
        inflater= LayoutInflater.from(myContext);
        myContext=this.myContext;
        this.rowItems = items;
    }
    
    
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return rowItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return rowItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return rowItems.indexOf(getItem(position));
	}
	
	/*private view holder class*/
    public class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        TextView txtPriority;
        TextView txtCategory;
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.timeText);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.taskText);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.txtPriority = (TextView) convertView.findViewById(R.id.priorityText);
            holder.txtCategory = (TextView) convertView.findViewById(R.id.categoryText);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
         
        RowItem rowItem = (RowItem) getItem(position);
         
        holder.txtDesc.setText(rowItem.getDesc());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getImageId());
        holder.txtPriority.setText(rowItem.getPriority());
        holder.txtCategory.setText(rowItem.getCategory());
        return convertView;

	}
	
}
