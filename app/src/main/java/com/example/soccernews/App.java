package com.example.soccernews;

import android.app.Application;

/* FIXME |Centralizamos uma instância do contexto em nosso {@link App} (mesmo sendo um "anti-pattern") para extrairmos o máximo dos nossos
   FIXME |ViewModels e camada de acesso à dados. Entretanto, apesar das nossas camadas ficarem mais coesas, o ideal seria usar uma solução de
   FIXME |injeção de dependências (como o Dagger ou o Hilt).
   FIXME |@see <a href="https://stackoverflow.com/a/14057777/3072570">Android Singleton with Global Context</a>
*/

public class App  extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
