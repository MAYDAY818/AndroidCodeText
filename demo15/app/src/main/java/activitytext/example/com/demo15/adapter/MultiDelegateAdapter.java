package activitytext.example.com.demo15.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

import activitytext.example.com.demo15.R;
import activitytext.example.com.demo15.bean.Model;

public class MultiDelegateAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {

    public MultiDelegateAdapter(@Nullable List<Model> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<Model>() {
            @Override
            protected int getItemType(Model entity) {
                //根据你的实体类来判断布局类型
                return entity.type;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(Model.FIRST_TYPE, R.layout.first_type_layout)
                .registerItemType(Model.SECOND_TYPE, R.layout.second_type_layout)
                .registerItemType(Model.NORMAL_TYPE, R.layout.item_rv);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model item) {
        switch (helper.getItemViewType()) {
            case Model.FIRST_TYPE:
                Log.i("tag", "FIRST_TYPE===============" + helper.getLayoutPosition());
                break;
            case Model.SECOND_TYPE:
                Log.i("tag", "SECOND_TYPE===============" + helper.getLayoutPosition());
                break;
            case Model.NORMAL_TYPE:
                helper.setImageResource(R.id.iv_img, R.mipmap.ic_launcher)
                        .setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_content, item.getContent());
                break;
        }
    }
}
