package br.usjt.ucsist.cadbasico.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.ucsist.cadbasico.R;
import br.usjt.ucsist.cadbasico.model.Usuario;
import br.usjt.ucsist.cadbasico.model.UsuarioViewModel;

import static android.os.Build.VERSION_CODES.R;

public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;
    private EditText editTextNome;
    private EditText editTextCPF;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonSalvar;

    private boolean mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(boolean param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getBoolean(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Hawk.init(getActivity()).build();

        editTextNome = view.findViewById(R.id.editTextNomeF);
        editTextCPF = view.findViewById(R.id.editTextCpfF);
        editTextEmail = view.findViewById(R.id.editTextEmailF);
        editTextSenha = view.findViewById(R.id.editTextSenhaF);
        buttonSalvar= view.findViewById(R.id.buttonSalvarF);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });


        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(getActivity(), new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }
        });
    }

    private void updateView(Usuario usuario){
        if(usuario != null && usuario.getId() > 0){
            usuarioCorrente = usuario;
            editTextNome.setText(usuario.getNome());
            editTextSenha.setText(usuario.getSenha());
        }
    }


    public void salvar() {

        if(usuarioCorrente == null){
            usuarioCorrente = new Usuario();
        }
        usuarioCorrente.setNome(editTextNome.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);
        Toast.makeText(getActivity(),"Usuário salvo com sucesso",
                Toast.LENGTH_SHORT).show();
        Hawk.put("tem_cadastro",true);
        if(mParam1){
            getActivity().finish();
        }

    }
}