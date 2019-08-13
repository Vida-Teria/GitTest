package com.example.phy.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.phy.myapplication.ContactsTable;
import com.example.phy.myapplication.User;
/******************** (C) COPYRIGHT 2012********************
 *������
 ************************************************************/
public class MyContactsActivity extends Activity {
    //
    private  ListView listView;
    //ListView
    private BaseAdapter  listViewAdapter;
    //ͨѶ¼�û�
    private  User users[];
    //��ǰѡ��
    private  int selecteItem=0;

    private String myPhone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("ͨ通讯录");

        listView = (ListView) findViewById(R.id.listView);
        Intent i=getIntent();
        myPhone=i.getStringExtra("myPhone");
        loadContacts();
    }
    /**
     * ������ϵ���б�
     */
    private void  loadContacts()
    {
        //��ȡ����ͨѶ¼��ϵ��
        ContactsTable ct=new ContactsTable(this);
        users=ct.getAllUser(myPhone);
        //listView�б���ʵ������
        listViewAdapter=new BaseAdapter() {
            @Override
            public View getView(int position,
                                View convertView, ViewGroup parent) {
                if(convertView==null)
                {
                    TextView textView =
                            new TextView(MyContactsActivity.this);
                    textView.setTextSize(22);
                    convertView=textView;
                }
                String moblie=users[position].getMoblie()==null?""
                        :users[position].getMoblie();
                ((TextView)convertView).setText(users[position]
                        .getName()+"---"+moblie);
                if(position==selecteItem)
                {
                    convertView.setBackgroundColor(Color.YELLOW);
                }else
                {
                    convertView.setBackgroundColor(0);
                }
                return convertView;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public Object getItem(int position) {
                return users[position];
            }
            @Override
            public int getCount() {
                return users.length;
            }
        };
        //����listView�ؼ���������
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                //��¼�����
                selecteItem=arg2;
                //ˢ���б�
                listViewAdapter.notifyDataSetChanged();
            }

        });
    }

    /**
     * �����˵�
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "添加");
        menu.add(Menu.NONE, 2, Menu.NONE, "编辑");
        menu.add(Menu.NONE, 3, Menu.NONE, "查看信息");
        menu.add(Menu.NONE, 4, Menu.NONE, "删除");
        menu.add(Menu.NONE, 5, Menu.NONE, "查询");
        menu.add(Menu.NONE, 6, Menu.NONE, "导入到手机电话簿");
        menu.add(Menu.NONE, 7, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * �˵��¼�
     */
    public boolean onOptionsItemSelected(MenuItem item){
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1://����
                Intent intent = new Intent(MyContactsActivity.this,AddContactsActivity.class);
                intent.putExtra("myPhone", myPhone);
                startActivity(intent);
                break;
            case 2://�༭
                if(users[selecteItem].getName()!="")//�������ݿ�ID�жϵ�ǰ��¼�Ƿ���Բ���
                {
                    intent = new Intent(MyContactsActivity.this,UpdateContactsActivity.class);
                    intent.putExtra("myPhone", myPhone);
                    intent.putExtra("name", users[selecteItem].getName());
                    startActivity(intent);
                }else
                {
                    Toast.makeText(this, "无结果记录，无法操作！",Toast.LENGTH_SHORT).show();
                }
                break;
            case 3://�鿴��Ϣ
                if(users[selecteItem].getName()!="")
                {
                    intent = new Intent(MyContactsActivity.this,ContactsMessageActivity.class);
                    intent.putExtra("myPhone", myPhone);
                    intent.putExtra("name", users[selecteItem].getName());
                    startActivity(intent);
                }else
                {
                    Toast.makeText(this, "无结果记录，无法操作！",Toast.LENGTH_SHORT).show();
                }
                break;
            case 4://ɾ��
                if(users[selecteItem].getName()!="")
                {
                    delete();
                }else
                {
                    Toast.makeText(this, "无结果记录，无法操作！",Toast.LENGTH_SHORT).show();
                }
                break;
            case 5://��ѯ
                new FindDialog(this).show();
                break;
            case 6://���뵽�ֻ��绰��
                if(users[selecteItem].getName()!="")
                {
                    importPhone(users[selecteItem].getName(),users[selecteItem].getMoblie());
                    Toast.makeText(this, "已经成功导入到"+users[selecteItem].getName()+"到手机电话簿！",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(this, "无结果记录，无法操作！",Toast.LENGTH_SHORT).show();
                }
                break;
            case 7://�˳�
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //���¼�������
        ContactsTable ct=new ContactsTable(this);
        users=ct.getAllUser(myPhone);
        //ˢ���б�
        listViewAdapter.notifyDataSetChanged();
    }
    /**
     * ��ѯ
     */
    public class FindDialog extends Dialog{

        public FindDialog(Context context) {
            super(context);

        }
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.find);
            setTitle("联系人查询");
            Button find=(Button)findViewById(R.id.find);
            Button cancel=(Button)findViewById(R.id.cancel);
            find.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    EditText value=(EditText)findViewById(R.id.value);
                    ContactsTable ct=new ContactsTable(MyContactsActivity.this);
                    users=ct.findUserByKey(value.getText().toString());
                    for(int i=0;i<users.length;i++)
                    {
                        System.out.println("姓名是"+users[i].getName()+
                                "电话是" +users[i].getMoblie());
                    }
                    listViewAdapter.notifyDataSetChanged();
                    selecteItem=0;
                    dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dismiss();
                }
            });
        }
    }
    /**
     * ɾ����ϵ��
     */
    public void delete()
    {
        Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("系统信息");
        alert.setMessage("是否要删除联系人？");
        alert.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ContactsTable ct=new ContactsTable(MyContactsActivity.this);
                        //ɾ����ϵ����Ϣ
                        if(ct.deleteByUser(users[selecteItem]))
                        {
                            //���»�ȡ����
                            users=ct.getAllUser(myPhone);
                            //ˢ���б�
                            listViewAdapter.notifyDataSetChanged();
                            selecteItem=0;
                            Toast.makeText(MyContactsActivity.this, "删除成功！",
                                    Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(MyContactsActivity.this, "删除失败！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        alert.setNegativeButton("否",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        alert.show();
    }
    ///���뵽�ֻ��绰��
    public  void importPhone(String name,String phone)
    {
        //ϵͳͨ��¼ContentProvider��URI
        Uri  phoneURL=android.provider.ContactsContract.Data.CONTENT_URI;
        ContentValues values = new ContentValues();
        //������RawContacts.CONTENT_URIִ��һ����ֵ���룬Ŀ���ǻ�ȡϵͳ���ص�rawContactId
        Uri rawContactUri = this.getContentResolver().
                insert(RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        //��data��������������
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        values.put(StructuredName.GIVEN_NAME, name);
        this.getContentResolver().insert(phoneURL, values);
        //��data������绰����
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, phone);
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        this.getContentResolver().insert(phoneURL, values);
    }
}

