package br.usjt.ucsist.cadbasico.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.ranieri.cadastrodedp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(R.id.frameLayoutMain,
                HomeFragment.newInstance("","")
                ,"HOMEFRAGMENT",
                "HOME");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        replaceFragment(R.id.frameLayoutMain,
                                HomeFragment.newInstance("", "")
                                , "HOMEFRAGMENT",
                                "HOME");
                        return true;

                    case R.id.contato:
                        replaceFragment(R.id.frameLayoutMain,
                                DisciplinaFragment.newInstance("", "")
                                , "DISCIPLINAFRAGMENT",
                                "DISCIPLINA");
                        return true;

                    case R.id.perfil:
                        replaceFragment(R.id.frameLayoutMain,
                                PerfilFragment.newInstance(false, "")
                                , "PERFILFRAGMENT",
                                "PERFIL");
                        return true;

                    case R.id.config:
                        replaceFragment(R.id.frameLayoutMain,
                                ConfiguracaoFragment.newInstance("", "")
                                , "CONFIGFRAGMENT",
                                "CONFIG");
                        return true;

                    case R.id.mapa:
                        replaceFragment(R.id.frameLayoutMain,
                                new MapaFragment()
                                , "MAPAFRAGMENT",
                                "MAPA");
                        return true;
                }
            }
            );
            }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.editar:
                Intent intent = new Intent(this,CadastroActivity.class);
                startActivity(intent);
            case R.id.sair:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

                protected void replaceFragment(@IdRes int containerViewId,
                @NonNull Fragment fragment,
                @NonNull String fragmentTag,
                @Nullable String backStackStateName) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(containerViewId, fragment, fragmentTag)
                            .addToBackStack(backStackStateName)
                            .commit();
                }
