package com.example.phy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phy.myapplication.ContactsTable;
import com.example.phy.myapplication.User;

public class AddContactsActivity extends Activity {
    /** Called when the activity is first created. */
    //���������
    private  EditText nameEditText;
    //�ֻ������
    private  EditText mobileEditText;
    //qq
    private  EditText qqEditText;
    //��λ
    private  EditText danweiEditText;
    //��ַ
    private  EditText addressEditText;
    private String myPhone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("添加联系人");
        Intent i=getIntent();
        myPhone=i.getStringExtra("myPhone");
        //�������õ�ҳ�沼�ֲ��Ҷ�Ӧ�Ŀؼ�
        nameEditText=(EditText)findViewById(R.id.name);
        mobileEditText=(EditText)findViewById(R.id.mobile);
        danweiEditText=(EditText)findViewById(R.id.danwei);
        qqEditText=(EditText)findViewById(R.id.qq);
        addressEditText=(EditText)findViewById(R.id.address);
    }
    /**
     * �����˵�
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE,1, Menu.NONE, "保存");
        menu.add(Menu.NONE,2, Menu.NONE, "返回");
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * �˵��¼�
     */
    public boolean onOptionsItemSelected(MenuItem item){
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1://����
                if(!nameEditText.getText().toString().equals(""))
                {
                    User user=new User();
                    user.setMyPhone(myPhone);
                    user.setName(nameEditText.getText().toString());
                    user.setMoblie(mobileEditText.getText().toString());
                    user.setDanwei(danweiEditText.getText().toString());
                    user.setQq(qqEditText.getText().toString());
                    user.setAddress(addressEditText.getText().toString());
                    ContactsTable ct=
                            new ContactsTable(AddContactsActivity.this);
                    if(ct.addData(user))
                    {
                        Toast.makeText(AddContactsActivity.this, "添加成功！",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else
                    {
                        Toast.makeText(AddContactsActivity.this, "添加失败！",
                                Toast.LENGTH_SHORT).show();

                    }
                }else
                {
                    Toast.makeText(AddContactsActivity.this, "请先输入数据！",
                            Toast.LENGTH_SHORT).show();
                }

                break;
            case 2://����
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
