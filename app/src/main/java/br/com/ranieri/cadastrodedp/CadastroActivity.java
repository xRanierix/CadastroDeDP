package br.com.ranieri.cadastrodedp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.hawk.Hawk;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Hawk.init(this).build();
    }

}