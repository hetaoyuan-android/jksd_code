package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.RepairSelectActivity;
import com.glens.jksd.bean.repair_bean.EquipmentBean;

import java.util.List;

public class RepairTowerSelectAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<EquipmentBean.RecordsBean> groupTitle;
    //子项是一个map，key是group的id，每一个group对应一个ChildItem的list
    private List<List<EquipmentBean.RecordsBean.TowerBean>> childMap;
    private RepairSelectActivity activity;

    public RepairTowerSelectAdapter(RepairSelectActivity activity, Context context, List<EquipmentBean.RecordsBean> groupTitle, List<List<EquipmentBean.RecordsBean.TowerBean>> childMap) {
        this.activity = activity;
        this.mContext = context;
        this.groupTitle = groupTitle;
        this.childMap = childMap;

    }

    @Override
    public int getGroupCount() {
        return groupTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childMap.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMap.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tower_group, null);
            groupHolder = new GroupHolder();
            groupHolder.groupText = convertView.findViewById(R.id.tv_tower_group);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.groupText.setText(groupTitle.get(groupPosition).getLineName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_repair_tower_select, null);
            childHolder = new ChildHolder();
            childHolder.childText = (TextView) convertView.findViewById(R.id.tv_tower_select);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        EquipmentBean.RecordsBean.TowerBean item = childMap.get(groupPosition).get(childPosition);
        if (item != null) {
            String itemTowerName = item.getTowerNo();
            childHolder.childText.setText(String.valueOf(itemTowerName + "杆"));
//            childHolder.childText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    activity.sendData(groupTitle.get(groupPosition), itemTowerName);
//                }
//            });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * show the text on the child and group item
     */
    private class GroupHolder {
        TextView groupText;
    }

    private class ChildHolder {
        TextView childText;
    }

}
