package com.example.phy.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phy.myapplication.ContactsTable;
import com.example.phy.myapplication.User;

public class UpdateContactsActivity extends Activity {
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
    //�޸ĵ���ϵ��
    private  User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("修改联系人");

        //�������õ�ҳ�沼�ֲ��Ҷ�Ӧ�Ŀؼ�
        nameEditText=(EditText)findViewById(R.id.name);
        mobileEditText=(EditText)findViewById(R.id.mobile);
        danweiEditText=(EditText)findViewById(R.id.danwei);
        qqEditText=(EditText)findViewById(R.id.qq);
        addressEditText=(EditText)findViewById(R.id.address);

        //��Ҫ�޸ĵ���ϵ�����ݸ�ֵ���û�������ʾ
        Bundle localBundle = getIntent().getExtras();

        String id=localBundle.getString("myPhone");
        String name=localBundle.getString("name");
        ContactsTable ct=new ContactsTable(this);
        user =ct.getUserByMyPhone(id,name);
        nameEditText.setText(user.getName());
        mobileEditText.setText(user.getMoblie());
        qqEditText.setText(user.getQq());
        danweiEditText.setText(user.getDanwei());
        addressEditText.setText(user.getAddress());
    }
    /**
     * �����˵�
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "保存");
        menu.add(Menu.NONE, 2, Menu.NONE, "返回");
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
                    user.setName(nameEditText.getText().toString());
                    user.setMoblie(mobileEditText.getText().toString());
                    user.setDanwei(danweiEditText.getText().toString());
                    user.setQq(qqEditText.getText().toString());
                    user.setAddress(addressEditText.getText().toString());
                    ContactsTable ct=
                            new ContactsTable(UpdateContactsActivity.this);
                    //�޸����ݿ���ϵ����Ϣ
                    if(ct.updateUser(user))
                    {
                        Toast.makeText(UpdateContactsActivity.this, "修改成功！",
                                Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(UpdateContactsActivity.this, "修改失败！",
                                Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(UpdateContactsActivity.this, "数据不能为空！",
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