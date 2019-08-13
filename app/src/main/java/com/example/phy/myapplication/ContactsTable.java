package com.example.phy.myapplication;

import java.util.Vector;
import com.example.phy.myapplication.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;



public class ContactsTable {
    private   final static String TABLENAME="contactsTable";//����
    private   MyDB   db;//���ݿ����

    public    ContactsTable(Context context)
    {
        db=new  MyDB(context);
        if(!db.isTableExits(TABLENAME))
        {
            String   createTableSql="CREATE TABLE IF NOT EXISTS " +
                    TABLENAME + " (  myPhone  VARCHAR   " +
                    "primary key , " +
                    User.NAME     + "  VARCHAR," +
                    User.PWD+"  VARCHAR,"+
                    User.MOBLIE    + "  VARCHAR,"+
                    User.QQ    + "  VARCHAR,"+
                    User.DANWEI    + "  VARCHAR,"+
                    User.ADDRESS+ " VARCHAR)";
            //������
            db.creatTable(createTableSql);
        }
    }
    /**
     * �������ݵ���ϵ�˱�
     //* @param name
     //* @param moblie
     //* @param phone
     * @return
     */
    public  boolean  addData(User user)
    {

        ContentValues values = new ContentValues();
        values.put("myPhone", user.getMyPhone());
        values.put(User.NAME, user.getName());
        values.put(User.MOBLIE, user.getMoblie());
        values.put(User.DANWEI, user.getDanwei());
        values.put(User.QQ, user.getQq());
        values.put(User.ADDRESS, user.getAddress());
        return	db.save(TABLENAME, values);
    }
    public  boolean  addUserData(User user)
    {
        ContentValues values = new ContentValues();
        values.put("myPhone", user.getName());
        values.put(User.PWD, user.getPwd());
        return	db.save(TABLENAME, values);
    }
    public  User[]  checkUser(String name,String pwd)
    {
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find(
                    "select * from " + TABLENAME +" where myPhone='"+name+"' and pwd='"+pwd+"'",null);

            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setMyPhone(cursor.getString(
                        cursor.getColumnIndex("myPhone")));

                temp.setPwd(cursor.getString(
                        cursor.getColumnIndex(User.PWD)));
                v.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[] {});
        }else
        {
            User[]  users=new User[1];
            return users;
        }
    }
    /**
     * ��ȡ��ϵ�˱�����
     * @return
     */
    public  User[] getAllUser(String myPhone)
    {
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find("select * from " + TABLENAME+
                    "where myPhone='aa'", null);
            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setName(cursor.getString(
                        cursor.getColumnIndex(User.NAME)));
                temp.setMoblie(cursor.getString(
                        cursor.getColumnIndex(User.MOBLIE)));
                temp.setDanwei(cursor.getString(
                        cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(
                        cursor.getColumnIndex(User.QQ)));
                temp.setAddress(cursor.getString(
                        cursor.getColumnIndex(User.ADDRESS)));

                v.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[] {});
        }else
        {
            User[]  users=new User[1];
            User  user=new User();
            user.setName("无结果");
            users[0]=user;
            return users;
        }
    }
    public  User[]  findUserByKey(String myPhone,String name)
    {
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find(
                    "select * from " + TABLENAME +" where myPhone='"+myPhone+" amd name='"+name+"'",null);

            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setName(cursor.getString(
                        cursor.getColumnIndex("myphone")));
                temp.setPwd(cursor.getString(
                        cursor.getColumnIndex(User.PWD)));
                v.add(temp);
                System.out.println(temp.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[] {});
        }else
        {
            User[]  users=new User[1];
//        	User  user=new User();
//        	user.setName("无结果");
//        	users[0]=user;
            return users;
        }
    }
    /**
     * �������ݿ��ID��������ȡ��ϵ��
     * @param id
     * @return
     */
    public User getUserByMyPhone(String myPhone,String name)
    {
        Cursor cursor = null;
        try {
            cursor = db.find(
                    "select * from " + TABLENAME +"   where  "
                            +"myPhone="+"'"+myPhone+"' and name='"+name+"'",null);
            User temp = new User();
            if(cursor.moveToNext())
            {
                temp.setName(cursor.getString(
                        cursor.getColumnIndex(User.NAME)));
                temp.setMoblie(cursor.getString(
                        cursor.getColumnIndex(User.MOBLIE)));
                temp.setDanwei(cursor.getString(
                        cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(
                        cursor.getColumnIndex(User.QQ)));
                temp.setAddress(cursor.getString(
                        cursor.getColumnIndex(User.ADDRESS)));
            }

            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }
    /**
     * ��ȡ��ϵ�˱�����
     * @return
     */
    public  User[] findUserByKey(String key)
    {
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find(
                    "select * from " + TABLENAME +"   where  "
                            +User.NAME+" like '%"+key+"%' " +
                            " or "+User.MOBLIE+" like '%"+key+"%' " +
                            " or "+User.QQ+" like  '%"+key+"%' "
                    , null);
            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setName(cursor.getString(
                        cursor.getColumnIndex(User.NAME)));
                temp.setMoblie(cursor.getString(
                        cursor.getColumnIndex(User.MOBLIE)));
                temp.setDanwei(cursor.getString(
                        cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(
                        cursor.getColumnIndex(User.QQ)));
                temp.setAddress(cursor.getString(
                        cursor.getColumnIndex(User.ADDRESS)));
                v.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[] {});
        }else
        {
            User[]  users=null;
            return users;
        }
    }
    /**
     * �޸���ϵ����Ϣ
     */
    public  boolean updateUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(User.NAME, user.getName());
        values.put(User.MOBLIE, user.getMoblie());
        values.put(User.DANWEI, user.getDanwei());
        values.put(User.QQ, user.getQq());
        values.put(User.ADDRESS, user.getAddress());
        return db.update(TABLENAME,
                values, "  myphone=? ", new String[]{user.getMyPhone()+""});
    }
    /**
     * ɾ����ϵ��
     * @param user
     * @return
     */
    public  boolean  deleteByUser(User user)
    {
        return db.delete(TABLENAME,
                "  myphone=?", new String[]{user.getMyPhone()+""});
    }

}
