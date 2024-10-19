package com.example.testbansach;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText tenSach, soLuong;
    Button btn_thuc_hien, btn_reset;
    CheckBox vip;
    ListView lv;
    Spinner tuoi;

    ArrayList<Sach> mylist;
    ArrayAdapter<Sach> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.hehe_layout);

        //anh xa
        tenSach = findViewById(R.id.ten_sach);
        soLuong = findViewById(R.id.so_luong);
        btn_thuc_hien = findViewById(R.id.btn_thuc_hien);
        btn_reset = findViewById(R.id.reset);
        vip = findViewById(R.id.vip);
        lv = findViewById(R.id.list);
        tuoi = findViewById(R.id.tuoi);

        // Tạo danh sách độ tuổi
        String[] tuoiArray = {"16-20", "21-25", "26-30", "Trên 30"};

        // Tạo adapter cho Spinner
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tuoiArray);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tuoi.setAdapter(adapterSpinner);

        // Thiết lập sự kiện chọn item
        tuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAgeRange = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Bạn đã chọn: " + selectedAgeRange, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mylist = new ArrayList<>();
        mylist.add(new Sach("Toán", 10, "Tuổi: [16-20]", true));
        mylist.add(new Sach("Văn", 6, "Tuổi: [16-20]", false));

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(adapter);

        btn_thuc_hien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ EditText
                String tenSachValue = tenSach.getText().toString().trim();
                String soLuongValue = soLuong.getText().toString().trim();
                String tuoiValue = "Tuổi: [" + tuoi.getSelectedItem().toString() + "]";

                // Kiểm tra nếu các trường trống
                if (tenSachValue.isEmpty() || soLuongValue.isEmpty()) {
                    // Hiển thị alert nếu có trường trống
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Xử lý logic tại đây
                    Sach sach = new Sach(tenSachValue, Integer.parseInt(soLuongValue), tuoiValue, vip.isChecked());
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Thông báo")
                            .setMessage("Bạn có muốn thêm các thông tin sau: " + "\n" + sach.showAlert())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mylist.add(sach);
                                    adapter.notifyDataSetChanged();
                                    tenSach.setText("");
                                    soLuong.setText("");
                                    vip.setChecked(false);
                                    //Dong dialog
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton(" Hủy", null)
                            .show();
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenSach.setText("");
                soLuong.setText("");
                vip.setChecked(false);
            }
        });
    }
}