package ucalgary.stbig.com.ucalgary.fragments;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjie.androidbucket.utils.ABTextUtil;
import com.wangjie.androidbucket.utils.imageprocess.ABShape;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

import ucalgary.stbig.com.ucalgary.R;

/**
 * Created by helbert on 14/09/15.
 */
public class FragmentInfo extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {
    Context context;
    Application application;
    View view;

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaButton;

    private RapidFloatingActionHelper rfabHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getActivity().getApplication();
        context = application.getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_info, container , false);

        rfaLayout = (RapidFloatingActionLayout) view.findViewById(R.id.label_list_sample_rfal);
        rfaButton = (RapidFloatingActionButton) view.findViewById(R.id.label_list_sample_rfab);

        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(context);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);

        List<RFACLabelItem> items = new ArrayList<>();

        items.add(new RFACLabelItem<Integer>()
                        .setLabel(getString(R.string.opt1))
//                        .setResId(R.mipmap.ico_test_c)
                        .setDrawable(getResources().getDrawable(R.drawable.ic_user))
                        .setIconNormalColor(getResources().getColor(R.color.primary))
                        .setIconPressedColor(getResources().getColor(R.color.primary))
                        .setLabelColor(getResources().getColor(R.color.half_black))
                        .setLabelSizeSp(14)
                        .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(Color.WHITE, ABTextUtil.dip2px(context, 4)))
                        .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                        .setLabel(getString(R.string.opt2))
                        .setResId(R.drawable.ic_email)
                        .setIconNormalColor(getResources().getColor(R.color.primary))
                        .setIconPressedColor(getResources().getColor(R.color.primary))
                        .setLabelColor(getResources().getColor(R.color.half_black))
                        .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(Color.WHITE, ABTextUtil.dip2px(context, 4)))
                        .setWrapper(2)
        );
        items.add(new RFACLabelItem<Integer>()
                        .setLabel(getString(R.string.opt3))
                        .setResId(R.drawable.ic_carrear)
                        .setIconNormalColor(getResources().getColor(R.color.primary))
                        .setIconPressedColor(getResources().getColor(R.color.primary))
                        .setLabelColor(getResources().getColor(R.color.half_black))
                        .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(Color.WHITE, ABTextUtil.dip2px(context, 4)))
                        .setWrapper(3)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(ABTextUtil.dip2px(context, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(ABTextUtil.dip2px(context, 5))
        ;

        rfabHelper = new RapidFloatingActionHelper(
                context,
                rfaLayout,
                rfaButton,
                rfaContent
        ).build();


        return view;
    }


    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {

    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {

    }
}
