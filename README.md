# 使用 `SharedPreferences` 进行数据的读写操作

## 读操作

```java
String userNameStr = sharedPreferences.getString(UserName, null);
String loginDateStr = sharedPreferences.getString(LoginDate, null);
if (userNameStr != null && loginDateStr != null) {
    Toast.makeText(MainActivity.this, "用户名为：" + userNameStr + "\n" + "登录时间为：" + loginDateStr, Toast.LENGTH_SHORT).show();
} else {
    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_SHORT).show();
}
```

## 写操作

```java
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String LoginDateStr = simpleDateFormat.format(new Date());

editor.putString(UserName, "张三");
editor.putString(LoginDate, LoginDateStr);
editor.apply();
```

总结：在向edit中put完数据后一定要记得调用apply或者commit方法，提交你对数据的更改。
这个数据是写在物理存储上的，所以重启应用后写入的数据还是存在的。


效果图：

![](./imgs/zbochw.png)

![](./imgs/MXu6ts.png)



