package com.sbnri.components;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.sbnri.R;
import com.sbnri.util.CommonUtil;

public class RepoListView extends LinearLayout {


    TextView titleTextView;
    TextView descriptionTextView;
    TextView licenseTextView;
    TextView permissionsTextView;
    TextView issueCountTextView;


    public RepoListView(Context context) {
        super(context);
        initLayout(context, null);
    }


    public RepoListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, attrs);


    }

    public RepoListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RepoListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout(context, attrs);

    }

    private void initLayout(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.components_repo_list, this);
        titleTextView = view.findViewById(R.id.title);
        descriptionTextView = view.findViewById(R.id.description);
        licenseTextView = view.findViewById(R.id.license);
        permissionsTextView = view.findViewById(R.id.permissions);
        issueCountTextView = view.findViewById(R.id.issueCount);

    }

    public void setTitle(String title) {
        if (CommonUtil.isString(title))
            titleTextView.setText(title);
    }

    public void setDescription(String description) {
        if(CommonUtil.isString(description)){
            if (description.length()>26)
                description = description.substring(0,25)+"..";
            descriptionTextView.setText(description);
        }

    }

    public void setLicense(String license) {
        if (CommonUtil.isString(license))
            licenseTextView.setText(license);
    }

    public void setPermissions(Boolean permissions) {
           if(permissions)
               permissionsTextView.setText("True");
           else
               permissionsTextView.setText("False");
    }

    public void setIssueCount(int issueCount) {
        issueCountTextView.setText(String.valueOf(issueCount));
    }

}
