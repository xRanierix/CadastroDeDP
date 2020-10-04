package br.com.ranieri.cadastrodedp.model.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.hawk.Hawk;

import br.com.ranieri.cadastrodedp.R;


public class CadastroActivity extends AppCompatActivity {

    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Hawk.init(this).build();
    }

}