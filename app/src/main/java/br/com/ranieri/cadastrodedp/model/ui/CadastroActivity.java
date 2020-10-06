package br.com.ranieri.cadastrodedp.model.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.orhanobut.hawk.Hawk;

import br.com.ranieri.cadastrodedp.R;
import br.usjt.ucsist.cadbasico.model.Usuario;
import br.usjt.ucsist.cadbasico.model.UsuarioViewModel;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;
    private EditText editTextUsuario;
    private EditText editTextCurso;
    private EditText editTextDisciplina;
    private EditText editTextSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Hawk.init(this).build();

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextCurso = findViewById(R.id.editTextCurso);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextSenha = findViewById(R.id.editTextSenha);

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }
        });
    }
    private void updateView (Usuario usuario){
        if (usuario != null && usuario.getId() > 0) {
            usuarioCorrente = usuario;
            editTextUsuario.setText(usuario.getNome());
            editTextCurso.setText(usuario.getCurso());
            editTextDisciplina.setText(usuarioCorrente.getDisciplina());
            editTextSenha.setText(usuario.getSenha());
        }
    }

    public void salvar (View view){
        if (usuarioCorrente == null) {
            usuarioCorrente = new Usuario();
        }
        usuarioCorrente.setNome(editTextUsuario.getText().toString());
        usuarioCorrente.setCurso(editTextCurso.getText().toString());
        usuarioCorrente.setDisciplina(editTextDisciplina.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);
        Toast.makeText(this, "Usuário Salvo com Sucesso", Toast.LENGTH_SHORT).show();
        Hawk.put("tem_cadastro", true);
        finish();
    }

}

