package br.com.ranieri.cadastrodedp.model.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import br.com.ranieri.cadastrodedp.R;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewCadastreSuaDP;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        textViewCadastreSuaDP = findViewById(R.id.textViewCadastreSuaDP);
        buttonLogin = findViewById(R.id.buttonLogin);
    }


    @Override
    public void onResume(){
        super.onResume();
        if(Hawk.contains("tem_cadastro")){

            habilitarLogin();

            if(Hawk.get("tem_cadastro")){

                desabilitarLogin();

            }
        }else{

            desabilitarLogin();
        }

    }

    private void habilitarLogin(){
        textViewCadastreSuaDP.setVisibility(View.GONE);
        buttonLogin.setEnabled(true);
        buttonLogin.setBackgroundColor(getResources().getColor(R.color.colorLaranja));
    }
    private void desabilitarLogin(){
        textViewCadastreSuaDP.setVisibility(View.VISIBLE);
        buttonLogin.setEnabled(false);
        buttonLogin.setBackgroundColor(getResources().getColor(R.color.colorCinza));
    }

    public void novoCadastro(View view) {
        Intent intent = new Intent (this, CadastroActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void salvar(View view) {
        Hawk.put("tem)cadastro", true);
        finish();
    }

    public void setTextViewCadastreSuaDP(TextView textViewCadastreSuaDP) {
        this.textViewCadastreSuaDP = textViewCadastreSuaDP;
    }
}