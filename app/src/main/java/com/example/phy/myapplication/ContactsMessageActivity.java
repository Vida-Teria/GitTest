package com.example.phy.myapplication;
import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class ContactsMessageActivity extends Activity {
    /** Called when the activity is first created. */
    //���������
    private  TextView nameTextView;
    //�ֻ������
    private  TextView mobileTextView;
    //qq
    private  TextView qqTextView;
    //��λ
    private  TextView danweiTextView;
    //��ַ
    private  TextView addressTextView;
    //�޸ĵ���ϵ��
    private  User user;
   // private EditText mobile,danwei,qq;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  //
        setTitle("联系人信息");

        //�������õ�ҳ�沼�ֲ��Ҷ�Ӧ�Ŀؼ�
        nameTextView=(TextView)findViewById(R.id.name);
        mobileTextView=(TextView)findViewById(R.id.mobile);
        danweiTextView=(TextView)findViewById(R.id.danwei);
        qqTextView=(TextView)findViewById(R.id.qq);
        addressTextView=(TextView)findViewById(R.id.address);

        //��Ҫ�޸ĵ���ϵ�����ݸ�ֵ���û�������ʾ
        Bundle localBundle = getIntent().getExtras();
        String id=localBundle.getString("myPhone");
        String name=localBundle.getString("name");
        ContactsTable ct=new ContactsTable(this);
        user =ct.getUserByMyPhone(id,name);
        nameTextView.setText("姓名："+user.getName());
        mobileTextView.setText("电话："+user.getMoblie());
        qqTextView.setText("QQ:"+user.getQq());
        danweiTextView.setText("单位："+user.getDanwei());
        addressTextView.setText("地址："+user.getAddress());
    }
    /**
     * �����˵�
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "返回");
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * �˵��¼�
     */
    public boolean onOptionsItemSelected(MenuItem item){
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1://����
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}