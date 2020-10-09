package br.usjt.ucsist.cadbasico.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.com.ranieri.cadastrodedp.R;
import br.usjt.ucsist.cadbasico.model.Usuario;
import br.usjt.ucsist.cadbasico.model.UsuarioViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewCadastreSuaDP;
    private Button buttonLogin;
    private br.usjt.ucsist.cadbasico.model.UsuarioViewModel usuarioViewModel;
    private br.usjt.ucsist.cadbasico.model.Usuario usuarioCorrente;
    private EditText editTextUsuario;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
        textViewCadastreSuaDP = findViewById(R.id.CadastreSuaDP);


        buttonLogin = findViewById(R.id.buttonLogin);

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateUsuario(usuario);
            }
        });
    }

    private void updateUsuario(Usuario usuario){
        usuarioCorrente = usuario;
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
        if(usuarioCorrente != null){
            if(usuarioCorrente.getNome().equalsIgnoreCase(editTextUsuario.getText().toString())
                    && usuarioCorrente.getSenha().equals(editTextSenha.getText().toString())){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Usuário ou senha invalidos!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void salvar(View view) {
        Hawk.put("tem)cadastro", true);
        finish();
    }

    public void setTextViewCadastreSuaDP(TextView textViewCadastreSuaDP) {
        this.textViewCadastreSuaDP = textViewCadastreSuaDP;
    }
}