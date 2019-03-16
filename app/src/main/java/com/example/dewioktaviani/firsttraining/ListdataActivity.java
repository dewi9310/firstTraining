package com.example.dewioktaviani.firsttraining;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListdataActivity extends AppCompatActivity {

    AdapterList adapterList;
    List<People> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        final ListView listView = (ListView) findViewById(R.id.lv_listPeople);

        itemList.clear();
        List<People> peopleList = new PeopleBL().GetAllData();
        if (peopleList!=null){
            for (People people : peopleList){
                People pp = new People();
                pp.setIntId(people.getIntId());
                pp.setTxtName(people.getTxtName());
                pp.setTxtGender(people.getTxtGender());
                pp.setTxtAddress(people.getTxtAddress());
                itemList.add(people);
            }
        }

        adapterList = new AdapterList(getApplicationContext(), itemList);
        listView.setAdapter(adapterList);
        listView.setDivider(null);
        adapterList.setOnItemClickListener(new AdapterList.onItemClickListener() {
            @Override
            public void onItemClick(View view, final People obj, int position) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ListdataActivity.this);

                builder.setTitle("Alert");
                builder.setMessage("Edit or Delete?");

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
//                        finishAffinity();
//                        System.exit(0);
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PeopleBL().DeletePeople(obj.getIntId());
                        itemList.remove(obj);
                        adapterList.notifyDataSetChanged();
                        listView.setAdapter(adapterList);
                        dialog.dismiss();
                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

//    public void showCustomDialog(Activity activity) {
//        Dialog dialogCustom = new Dialog(activity);
//        dialogCustom.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
//        dialogCustom.setContentView(R.layout.dialog_checkout);
//        dialogCustom.setCancelable(true);
//
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialogCustom.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        TextView tv_title = (TextView)dialogCustom.findViewById(R.id.cd_title);
//        TextView tv_subtitle = (TextView)dialogCustom.findViewById(R.id.cd_subtitle);
//        final EditText et_userName = (EditText) dialogCustom.findViewById(R.id.et_int_number_realisasi);
//        ((AppCompatButton) dialogCustom.findViewById(R.id.btn_submit_realisasi)).setText("SAVE");
//        if (isEdit){
////            new ToastCustom().showToasty(activity,"Yeaaay Edit...",4);
//            this.dtCheckinActive = dtCheckinActive;
//            this.dataPlan = dataPlan;
//            this.IntSubSubActivityid = intSubSubActivityid;
//            this.txtSubSubActivity = txtSubSubActivity;
//            this.dtDetail = detail;
//            this.isEditDetail = isEdit;
//            et_userName.setText(detail.getTxtNoDoc());
//        }
//        tv_title.setText(txtSubSubActivity);
//        tv_subtitle.setText("Please fill number document");
//        et_userName.setHint("AA.2018.07");
////        et_userName.setInputType(InputType.TYPE_CLASS_TEXT);
//        char[] chars = {'.', '-', '/', ','};
//        new InputFilters().etCapsTextWatcherNoSpace(et_userName, null, chars);
//        ((AppCompatButton) dialogCustom.findViewById(R.id.btn_cancel_realisasi)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialogCustom.dismiss();
//            }
//        });
//
//        ((AppCompatButton) dialogCustom.findViewById(R.id.btn_submit_realisasi)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txtNoDoc = et_userName.getText().toString().trim();
//                if (txtNoDoc.equals("")) {
//                    new ToastCustom().showToasty(activity,"Please fill number document...",4);
//                } else {
//                    saveData(activity);
//                }
//            }
//        });
//
//        dialogCustom.show();
//        dialogCustom.getWindow().setAttributes(lp);
//    }
@Override
public void onBackPressed() {
    Intent parentIntent = NavUtils.getParentActivityIntent(this);
    parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    startActivity(parentIntent);
    finish();
}
}
