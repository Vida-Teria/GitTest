package com.example.phy.myapplication;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText name;
    private EditText pwd;
    private Button register;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String pwdStr = pwd.getText().toString();
                if (!nameStr.equals("") && !pwdStr.equals("")) {
                    User user = new User();
                    user.setName(nameStr);
                    user.setPwd(pwdStr);
                    ContactsTable ct = new ContactsTable(LoginActivity.this);
                    if (ct.addUserData(user)) {
                        Toast.makeText(LoginActivity.this, "添加成功！",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "添加失败！",
                                Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(LoginActivity.this, "信息为空！",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
        login.setOnClickListener(new OnClickListener(){
            public void onClick(View v)
            {
                String nameStr=name.getText().toString();
                String pwdStr=pwd.getText().toString();
                ContactsTable ut=new ContactsTable(LoginActivity.this);
                User[] users=ut.checkUser(nameStr, pwdStr);
                if(users[0]!=null)
                {
                    Toast.makeText(LoginActivity.this, "登录成功！",
                            Toast.LENGTH_SHORT).show();
                    Intent i=new Intent();
                    i.setClass(LoginActivity.this, MyContactsActivity.class);
                    i.putExtra("myPhone", nameStr);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "登录失败！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
